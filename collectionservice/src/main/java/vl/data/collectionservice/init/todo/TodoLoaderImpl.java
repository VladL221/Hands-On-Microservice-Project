package vl.data.collectionservice.init.todo;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import vl.data.collectionservice.beans.TodoBean;
import vl.data.collectionservice.entities.Todo;
import vl.data.collectionservice.entities.user.AppUser;
import vl.data.collectionservice.repo.AppUserRepository;
import vl.data.collectionservice.repo.TodoRepository;
import vl.data.collectionservice.utils.AppUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class TodoLoaderImpl implements TodoLoader{



    private String todosUrl = "https://jsonplaceholder.typicode.com/todos";

    private final AppUserRepository appUserRepository;

    @Override
    public void loadTodos() {
        RestTemplate restTemplate = new RestTemplate();
        log.info("Starting loading todos...");
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            ResponseEntity<String> response = restTemplate.getForEntity(todosUrl, String.class);
            List<TodoBean> todos = objectMapper.readValue(response.getBody(), new TypeReference<List<TodoBean>>(){});
            List<Todo> todosEntity = todos.stream().map(x -> {
                AppUser appUser = appUserRepository.findById(x.getUserId()).orElse(null);
                Todo todo = new Todo();
                todo.setId(x.getId());
                todo.setUserId(appUser);
                todo.setTitle(x.getTitle());
                todo.setCompleted(x.getCompleted());
                appUser.getTodos().add(todo);
                return todo;
            }).collect(Collectors.toList());
            log.info("Loaded: {} todos", todosEntity.size());
        }catch (Exception e){
            log.error("error: {}",e.getMessage());
        }
        log.info("Finished loading todos!");
    }


}
