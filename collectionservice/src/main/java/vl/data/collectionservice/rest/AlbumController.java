package vl.data.collectionservice.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vl.data.collectionservice.entities.Album;
import vl.data.collectionservice.service.album.AlbumService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/albums")
public class AlbumController {


    private final AlbumService albumService;


    // For fetching all albums http://localhost:8082/collection-service/api/albums
    @GetMapping
    public ResponseEntity<List<Album>> getAlbums(){
        try {
            return ResponseEntity.status(200).body(albumService.getAlbums());
        }catch (Exception e){
            log.error("Exception: {}",e.getMessage());
        }
        return null;
    }

    // For fetching a specific album http://localhost:8082/collection-service/api/albums/3
    @GetMapping("/{id}")
    public ResponseEntity<Album> getAlbum(@PathVariable Long id){
        try {
            return ResponseEntity.status(200).body(albumService.getAlbum(id));
        }catch (Exception e){
            log.error("Exception: {}", e.getMessage());
        }
        return null;
    }

    // For creating a new album http://localhost:8082/collection-service/api/albums
    @PostMapping
    public ResponseEntity<?> createAlbum(@RequestBody Album album){
        try {
            return ResponseEntity.status(201).body(albumService.createAlbum(album));
        }catch (Exception e) {
            log.error("Exception: {}", e.getMessage());
        }
        return null;
    }

    // For updating an album http://localhost:8082/collection-service/api/albums/update
    @PutMapping("/update")
    public ResponseEntity<?> updateAlbum(@RequestBody Album album){
        try {
            return ResponseEntity.status(201).body(albumService.updateAlbum(album));
        }catch (Exception e) {
            log.error("Exception: {}",e.getMessage());
        }
        return null;
    }


}
