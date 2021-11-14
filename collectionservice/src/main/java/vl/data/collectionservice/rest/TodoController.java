package vl.data.collectionservice.rest;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vl.data.collectionservice.entities.Post;
import vl.data.collectionservice.entities.Todo;
import vl.data.collectionservice.entities.user.AppUser;
import vl.data.collectionservice.service.todo.TodoService;
import vl.data.collectionservice.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/todos")
@Slf4j
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    // For fetching all todos http://localhost:8082/collection-service/api/todos
    @GetMapping
    public ResponseEntity<?> getTodos(){
        try {
            return ResponseEntity.status(200).body(todoService.getTodos());
        }catch (Exception e) {
            log.error("Exception: {}", e.getMessage());
        }
        return null;
    }

    // For fetching a specific to do by id http://localhost:8082/collection-service/api/todos/2
    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodo(@PathVariable Long id){
        try {
            return ResponseEntity.status(200).body(todoService.getTodo(id));
        }catch (Exception e) {
            log.error("Exception: {}", e.getMessage());
        }
        return null;
    }

    // for deleting a specific task http://localhost:8082/collection-service/api/todos/delete/2
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable Long id){
        try {
            todoService.deleteTodo(id);
            return ResponseEntity.status(200).body("Deleted successfully!");
        }catch (Exception e) {
            log.error("Exception: {}", e.getMessage());
        }
        return null;
    }


}
