package vl.data.collectionservice.rest;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vl.data.collectionservice.beans.PostBean;
import vl.data.collectionservice.entities.Post;
import vl.data.collectionservice.service.post.PostService;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@Slf4j
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // For fetching all posts http://localhost:8082/collection-service/api/posts
    @GetMapping
    public ResponseEntity<List<PostBean>> getPosts(){
        try {
            return ResponseEntity.status(200).body(postService.getPosts());
        }catch (Exception e) {
            log.error("Exception: {}", e.getMessage());
        }
        return null;
    }

    // For fetching a specific post http://localhost:8082/collection-service/api/posts/2
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(@PathVariable Long id){
        try {
            return ResponseEntity.status(200).body(postService.getPost(id));
        }catch (Exception e) {
            log.error("Exception: {}", e.getMessage());
        }
        return null;
    }

    // For creating a new post http://localhost:8082/collection-service/api/posts
    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody Post post){
        try {
            return ResponseEntity.status(201).body(postService.createPost(post));
        }catch (Exception e) {
            log.error("Exception: {}", e.getMessage());
        }
        return null;
    }

    // For updating a specific post http://localhost:8082/collection-service/api/posts/update
    @PutMapping("/update")
    public ResponseEntity<?> updatePost(@RequestBody Post post){
        try {
            return ResponseEntity.status(201).body(postService.updatePost(post));
        }catch (Exception e) {
            log.error("Exception: {}", e.getMessage());
        }
        return null;
    }

}
