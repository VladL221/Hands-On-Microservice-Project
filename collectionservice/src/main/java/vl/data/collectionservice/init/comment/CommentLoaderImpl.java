package vl.data.collectionservice.init.comment;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import vl.data.collectionservice.beans.CommentBean;
import vl.data.collectionservice.beans.PostBean;
import vl.data.collectionservice.entities.Comment;
import vl.data.collectionservice.entities.Post;
import vl.data.collectionservice.entities.user.AppUser;
import vl.data.collectionservice.repo.PostRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class CommentLoaderImpl implements CommentLoader{

    private String commentsUrl = "https://jsonplaceholder.typicode.com/comments";

    private final PostRepository postRepository;


    @Override
    public void loadComments() {
        RestTemplate restTemplate = new RestTemplate();
        log.info("Starting loading comments...");
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            ResponseEntity<String> response = restTemplate.getForEntity(commentsUrl, String.class);
            List<CommentBean> commentBeans = objectMapper.readValue(response.getBody(), new TypeReference<List<CommentBean>>(){});
            List<Comment> commentEntities = commentBeans.stream().map(x -> {
                Post post = postRepository.findById(x.getPostId()).orElse(null);
                Comment comment = new Comment();
                comment.setId(x.getId());
                comment.setPostId(post);
                comment.setName(x.getName());
                comment.setEmail(x.getEmail());
                comment.setBody(x.getBody());
                post.getComments().add(comment);
                return comment;
            }).collect(Collectors.toList());
            log.info("Loaded: {} comment", commentEntities.size());
        }catch (Exception e){
            log.error("error: {}",e.getMessage());
        }
        log.info("Finished loading comments!");
    }


}
