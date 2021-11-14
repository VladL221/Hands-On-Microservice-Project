package vl.data.collectionservice.init.photo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import vl.data.collectionservice.beans.PhotoBean;
import vl.data.collectionservice.beans.PostBean;
import vl.data.collectionservice.entities.Album;
import vl.data.collectionservice.entities.Photo;
import vl.data.collectionservice.entities.Post;
import vl.data.collectionservice.entities.user.AppUser;
import vl.data.collectionservice.repo.AlbumRepository;
import vl.data.collectionservice.repo.AppUserRepository;
import vl.data.collectionservice.repo.PostRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class PhotoLoaderImpl implements PhotoLoader{

    private String photosUrl = "https://jsonplaceholder.typicode.com/photos";

    private final AlbumRepository albumRepository;

    @Override
    public void loadPhotos() {
        RestTemplate restTemplate = new RestTemplate();
        log.info("Starting loading photos...");
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            ResponseEntity<String> response = restTemplate.getForEntity(photosUrl, String.class);
            List<PhotoBean> photoBeans = objectMapper.readValue(response.getBody(), new TypeReference<List<PhotoBean>>(){});
            List<Photo> photoEntities = photoBeans.stream().map(x -> {
                Album album = albumRepository.findById(x.getAlbumId()).orElse(null);
                Photo photo = new Photo();
                photo.setId(x.getId());
                photo.setTitle(x.getTitle());
                photo.setUrl(x.getUrl());
                photo.setThumbnailUrl(x.getThumbnailUrl());
                photo.setAlbumId(album);
                album.getPhotos().add(photo);
                return photo;
            }).collect(Collectors.toList());
            log.info("Loaded: {} photos", photoEntities.size());
        }catch (Exception e){
            log.error("error: {}",e.getMessage());
        }
        log.info("Finished loading photos!");
        log.info("All resources finished loading...");
    }



}
