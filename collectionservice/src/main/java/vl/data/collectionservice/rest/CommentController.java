package vl.data.collectionservice.rest;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vl.data.collectionservice.entities.Album;
import vl.data.collectionservice.entities.Comment;
import vl.data.collectionservice.service.comment.CommentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
@Slf4j
public class CommentController {

    private final CommentService commentService;

    // For fetching all comments http://localhost:8082/collection-service/api/comments
    @GetMapping
    public ResponseEntity<List<Comment>> getComments(){
        try {
            return ResponseEntity.status(200).body(commentService.getComments());
        }catch (Exception e){
            log.error("Exception: {}",e.getMessage());
        }
        return null;
    }

    // For fetching a specific comment http://localhost:8082/collection-service/api/comments/2
    @GetMapping("/{id}")
    public ResponseEntity<Comment> getComment(@PathVariable Long id){
        try {
            return ResponseEntity.status(200).body(commentService.getComment(id));
        }catch (Exception e){
            log.error("Exception: {}", e.getMessage());
        }
        return null;
    }



}
