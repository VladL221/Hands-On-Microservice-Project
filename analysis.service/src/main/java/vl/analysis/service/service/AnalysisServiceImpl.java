package vl.analysis.service.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import vl.analysis.service.beans.Album;
import vl.analysis.service.beans.Post;
import vl.analysis.service.beans.Todo;
import vl.analysis.service.beans.user.AppUser;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class AnalysisServiceImpl implements AnalysisService{

    private final String collectionServiceApi = "http://localhost:8082/collection-service/api/";

    @Override
    public List<Todo> getAllUncompletedTodos() {
        RestTemplate restTemplate = new RestTemplate();
        log.info("Fetching todos...");
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            ResponseEntity<String> response = restTemplate.getForEntity(collectionServiceApi+"todos", String.class);
            List<Todo> todos = objectMapper.readValue(response.getBody(), new TypeReference<List<Todo>>(){});
            List<Todo> sortedList = todos.stream().filter(todo -> !todo.getCompleted()).collect(Collectors.toList());
            log.info("Finished loading todos!");
            return sortedList;
        } catch (Exception e) {
            log.error("error: {}", e.getMessage());
            return null;
        }
    }


    @Override
    public List<Todo> getAllUncompletedTodosOfUser(Long userId) {
        RestTemplate restTemplate = new RestTemplate();
        log.info("Fetching todos...");
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            ResponseEntity<String> response = restTemplate.getForEntity(collectionServiceApi+"todos", String.class);
            List<Todo> todos = objectMapper.readValue(response.getBody(), new TypeReference<List<Todo>>(){});
            List<Todo> sortedList = todos.stream()
                    .filter(todo -> todo.getUserId()==userId)
                    .filter(todo2 -> !todo2.getCompleted())
                    .collect(Collectors.toList());
            log.info("Loaded: {} todos by userid COUNT", sortedList.size());
            return sortedList;
        } catch (Exception e) {
            log.error("error: {}", e.getMessage());
            return null;
        }
    }

    @Override
    public List<Post> getAllPostsWithoutComments(Long userId) {
        RestTemplate restTemplate = new RestTemplate();
        log.info("Fetching posts...");
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            ResponseEntity<String> response = restTemplate.getForEntity(collectionServiceApi+"posts", String.class);
            List<Post> posts = objectMapper.readValue(response.getBody(), new TypeReference<List<Post>>(){});
            List<Post> sortedList = posts.stream()
                    .filter(post -> post.getUserId()!=userId)
                    .collect(Collectors.toList());
            log.info("Loaded: {} posts by userid COUNT", sortedList.size());
            return sortedList;
        } catch (Exception e) {
            log.error("error: {}", e.getMessage());
            return null;
        }
    }

    @Override
    public List<AppUser> getAllUserAlbumsPhotosWithAmountBiggerThanThresholdAsUsers(Long threshold) {
        RestTemplate restTemplate = new RestTemplate();
        log.info("Fetching users with albums...");
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            ResponseEntity<String> response = restTemplate.getForEntity(collectionServiceApi+"users", String.class);
            List<AppUser> appUsers = objectMapper.readValue(response.getBody(), new TypeReference<List<AppUser>>(){});
            List<Album> albumList = appUsers.stream().flatMap(user -> user.getAlbums().stream()).filter(album -> album.getPhotos().size()>= threshold).collect(Collectors.toList());
            appUsers.stream().forEach(user -> user.getAlbums().retainAll(albumList));
            appUsers.removeIf(user -> user.getAlbums().isEmpty());
            log.info("Loaded: {} Users with albums by threshold COUNT", appUsers.size());
            return appUsers;
        } catch (Exception e) {
            log.error("error: {}", e.getMessage());
            return null;
        }

    }



    @Override
    public List<Album> getAllUserAlbumsPhotosWithAmountBiggerThanThresholdAsAlbums(Long threshold) {
        RestTemplate restTemplate = new RestTemplate();
        log.info("Fetching Albums...");
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            ResponseEntity<String> response = restTemplate.getForEntity(collectionServiceApi+"users", String.class);
            List<AppUser> appUsers = objectMapper.readValue(response.getBody(), new TypeReference<List<AppUser>>(){});
            List<Album> albumList = appUsers.stream().flatMap(user -> user.getAlbums().stream()).filter(album -> album.getPhotos().size()>= threshold).collect(Collectors.toList());
            log.info("Loaded: {} albums by threshold COUNT", albumList.size());
            return albumList;
        } catch (Exception e) {
            log.error("error: {}", e.getMessage());
            return null;
        }

    }
}
