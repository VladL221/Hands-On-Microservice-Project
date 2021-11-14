package vl.data.collectionservice.rest;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vl.data.collectionservice.entities.Album;
import vl.data.collectionservice.entities.Photo;
import vl.data.collectionservice.entities.Todo;
import vl.data.collectionservice.service.photo.PhotoService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/photos")
public class PhotoController {


    private final PhotoService photoService;


    // For fetching all photos http://localhost:8082/collection-service/api/photos
    @GetMapping
    public ResponseEntity<List<Photo>> getPhotos(){
        try {
            return ResponseEntity.status(200).body(photoService.getPhotos());
        }catch (Exception e) {
            log.error("Exception: {}", e.getMessage());
        }
        return null;
    }

    // For fetching a specific photo http://localhost:8082/collection-service/api/photos/3
    @GetMapping("/{id}")
    public ResponseEntity<Photo> getPhoto(@PathVariable Long id){
        try {
            return ResponseEntity.status(200).body(photoService.getPhoto(id));
        }catch (Exception e) {
            log.error("Exception: {}", e.getMessage());
        }
        return null;
    }


    // For deleting a specific photo by id http://localhost:8082/collection-service/api/photos/delete/3
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePhoto(@PathVariable Long id){
        try {
            photoService.deletePhoto(id);
            return ResponseEntity.status(200).body("Deleted successfully!");
        }catch (Exception e) {
            log.error("Exception: {}", e.getMessage());
        }
        return null;
    }


}
