package vl.data.collectionservice.init.post;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import vl.data.collectionservice.beans.PostBean;
import vl.data.collectionservice.beans.TodoBean;
import vl.data.collectionservice.entities.Post;
import vl.data.collectionservice.entities.Todo;
import vl.data.collectionservice.entities.user.AppUser;
import vl.data.collectionservice.repo.AppUserRepository;
import vl.data.collectionservice.repo.PostRepository;
import vl.data.collectionservice.repo.TodoRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class PostLoaderImpl implements PostLoader{


    private String postUrl = "https://jsonplaceholder.typicode.com/posts";

    private final PostRepository postRepository;
    private final AppUserRepository appUserRepository;

    @Override
    public void loadPosts() {
        RestTemplate restTemplate = new RestTemplate();
        log.info("Starting loading posts...");
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            ResponseEntity<String> response = restTemplate.getForEntity(postUrl, String.class);
            List<PostBean> postBeans = objectMapper.readValue(response.getBody(), new TypeReference<List<PostBean>>(){});
            List<Post> postEntities = postBeans.stream().map(x -> {
                AppUser appUser = appUserRepository.findById(x.getUserId()).orElse(null);
                Post post = new Post();
                post.setId(x.getId());
                post.setUserId(appUser);
                post.setTitle(x.getTitle());
                post.setBody(x.getBody());
                appUser.getPosts().add(post);
                return post;
            }).collect(Collectors.toList());
            log.info("Loaded: {} posts", postEntities.size());
        }catch (Exception e){
            log.error("error: {}",e.getMessage());
        }
        log.info("Finished loading posts!");
    }
}
