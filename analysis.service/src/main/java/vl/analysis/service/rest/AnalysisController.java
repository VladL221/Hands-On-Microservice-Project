package vl.analysis.service.rest;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vl.analysis.service.beans.Album;
import vl.analysis.service.beans.Post;
import vl.analysis.service.beans.Todo;
import vl.analysis.service.beans.user.AppUser;
import vl.analysis.service.service.AnalysisService;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class AnalysisController {


    private final AnalysisService analysisService;


    // For all the uncompleted todos of all users http://localhost:8082/analysis-service/api/todos/uncompleted
    @GetMapping("/todos/uncompleted")
    public ResponseEntity<List<Todo>> getAllUncompletedTodos(){
        try {
            return ResponseEntity.status(200).body(analysisService.getAllUncompletedTodos());
        }catch (Exception e){
            log.error("Exception: {}",e.getMessage());
            return null;
        }
    }

    // For all the uncompleted todos of a specified user id http://localhost:8082/analysis-service/api/todos/uncompleted/2
    @GetMapping("/todos/uncompleted/{userId}")
    public ResponseEntity<List<Todo>> getAllUncompletedTodosByUserId(@PathVariable Long userId){
        try {
            return ResponseEntity.status(200).body(analysisService.getAllUncompletedTodosOfUser(userId));
        }catch (Exception e){
            log.error("Exception: {}",e.getMessage());
            return null;
        }
    }

    //For all the posts without the comments of the specified user id http://localhost:8082/analysis-service/api/posts/2
    @GetMapping("/posts/{userId}")
    public ResponseEntity<List<Post>> getAllPostsWithoutCommentsOfUserId(@PathVariable Long userId){
        try {
            return ResponseEntity.status(200).body(analysisService.getAllPostsWithoutComments(userId));
        }catch (Exception e){
            log.error("Exception: {}",e.getMessage());
            return null;
        }
    }

    //For all users albums objects below threshold http://localhost:8082/analysis-service/api/albums/50
    //For all users albums objects above threshold http://localhost:8082/analysis-service/api/albums/51
    @GetMapping("/albums/{threshold}")
    public ResponseEntity<List<Album>> getAllUserAlbumsPhotosWithAmountBiggerThanThresholdAsAlbums(@PathVariable Long threshold){
        try {
            return ResponseEntity.status(200).body(analysisService.getAllUserAlbumsPhotosWithAmountBiggerThanThresholdAsAlbums(threshold));
        }catch (Exception e){
            log.error("Exception: {}",e.getMessage());
            return null;
        }
    }

    //For all users objects with the albums above threshold http://localhost:8082/analysis-service/api/users/albums/51
    //For all users objects with the albums below threshold http://localhost:8082/analysis-service/api/users/albums/50
    @GetMapping("/users/albums/{threshold}")
    public ResponseEntity<List<AppUser>> getAllUserAlbumsPhotosWithAmountBiggerThanThresholdAsUsers(@PathVariable Long threshold){
        try {
            return ResponseEntity.status(200).body(analysisService.getAllUserAlbumsPhotosWithAmountBiggerThanThresholdAsUsers(threshold));
        }catch (Exception e){
            log.error("Exception: {}",e.getMessage());
            return null;
        }
    }


}
