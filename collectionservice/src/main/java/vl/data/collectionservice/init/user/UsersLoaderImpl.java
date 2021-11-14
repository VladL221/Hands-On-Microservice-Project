package vl.data.collectionservice.init.user;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import vl.data.collectionservice.entities.user.AppUser;
import vl.data.collectionservice.repo.AppUserRepository;


import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class UsersLoaderImpl implements UserLoader {

    private String userUrl = "https://jsonplaceholder.typicode.com/users";

    private final AppUserRepository appUserRepository;


    @Override
    public void loadUsers() {
        RestTemplate restTemplate = new RestTemplate();
        log.info("Please wait for the database to load with data!");
        try{
                ObjectMapper objectMapper = new ObjectMapper();
                ResponseEntity<String> response = restTemplate.getForEntity(userUrl, String.class);
                List<AppUser> users = objectMapper.readValue(response.getBody(), new TypeReference<List<AppUser>>(){});
                appUserRepository.saveAll(users);
            log.info("Loaded: {} users", users.size());
            } catch (Exception e) {
            log.error("error: {}", e.getMessage());
        }
        log.info("Finished loading users!");
    }


}
