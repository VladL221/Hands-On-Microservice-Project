package vl.data.collectionservice.service.post;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vl.data.collectionservice.beans.PostBean;
import vl.data.collectionservice.entities.Post;
import vl.data.collectionservice.repo.PostRepository;
import vl.data.collectionservice.utils.AppUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{


    private final PostRepository postRepository;


    @Override
    public List<PostBean> getPosts() {
        try {
            List<Post> posts = postRepository.findAll();
            List<PostBean> postBeans = posts.stream().map(AppUtils::postEntityToDto).collect(Collectors.toList());
            log.info("Posts beans: {}",postBeans);
            return postBeans;
        }catch (Exception e) {
        log.error("Exception: {}",e.getMessage());
        return null;
        }
    }

    @Override
    public Post getPost(Long id) {
        try {
            return postRepository.findById(id).orElse(null);
        }catch (Exception e) {
            log.error("Exception: {}",e.getMessage());
            return null;
        }
    }

    @Override
    public Post createPost(Post post) {
        try {
            return postRepository.save(post);
        }catch (Exception e) {
            log.error("Exception: {}",e.getMessage());
            return null;
        }
    }

    @Override
    public Post updatePost(Post post) {
        try {
            return postRepository.save(post);
        }catch (Exception e) {
            log.error("Exception: {}",e.getMessage());
            return null;
        }
    }
}
