package vl.data.collectionservice.init.album;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import vl.data.collectionservice.beans.AlbumBean;
import vl.data.collectionservice.beans.CommentBean;
import vl.data.collectionservice.entities.Album;
import vl.data.collectionservice.entities.Comment;
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
public class AlbumLoaderImpl implements AlbumLoader{


    private String albumsUrl = "https://jsonplaceholder.typicode.com/albums";

    private final AppUserRepository appUserRepository;


    @Override
    public void loadAlbums() {
        RestTemplate restTemplate = new RestTemplate();
        log.info("Starting loading albums...");
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            ResponseEntity<String> response = restTemplate.getForEntity(albumsUrl, String.class);
            List<AlbumBean> commentBeans = objectMapper.readValue(response.getBody(), new TypeReference<List<AlbumBean>>(){});
            List<Album> albumEntities = commentBeans.stream().map(x -> {
                AppUser appUser = appUserRepository.findById(x.getUserId()).orElse(null);
                Album album = new Album();
                album.setId(x.getId());
                album.setUserId(appUser);
                album.setTitle(x.getTitle());
                appUser.getAlbums().add(album);
                return album;
            }).collect(Collectors.toList());
            log.info("Loaded: {} albums", albumEntities.size());
        }catch (Exception e){
            log.error("error: {}",e.getMessage());
        }
        log.info("Finished loading albums!");
    }


}
