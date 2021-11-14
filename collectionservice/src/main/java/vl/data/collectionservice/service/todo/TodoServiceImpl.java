package vl.data.collectionservice.service.todo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vl.data.collectionservice.beans.TodoBean;
import vl.data.collectionservice.entities.Todo;
import vl.data.collectionservice.entities.user.AppUser;
import vl.data.collectionservice.repo.AppUserRepository;
import vl.data.collectionservice.repo.TodoRepository;
import vl.data.collectionservice.utils.AppUtils;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class TodoServiceImpl implements TodoService{


    private final TodoRepository todoRepository;
    private final AppUserRepository appUserRepository;

    @Override
    public List<TodoBean> getTodos() {
        try {
            List<Todo> todos = todoRepository.findAll();
            List<TodoBean> todoBeanList = todos.stream().map(AppUtils::todoEntityToDto).collect(Collectors.toList());
            log.info("todos: {}",todoBeanList);
            return todoBeanList;
        }catch (Exception e){
            log.error("Exception: {}",e.getMessage());
            return null;
        }
    }

    @Override
    public Todo getTodo(Long id) {
        try {
            return todoRepository.findById(id).orElse(null);
        }catch (Exception e) {
            log.error("Exception: {}",e.getMessage());
            return null;
        }
    }

    @Override
    public void deleteTodo(Long id) {
        try {
            Todo todo = todoRepository.findById(id).orElse(null);
            AppUser appUser = appUserRepository.findById(todo.getUserId().getId()).orElse(null);
            todoRepository.delete(todo);
            log.info("todo deleted?: {}", todoRepository.findById(id).orElse(null));
            log.info("user deleted?: {}", appUserRepository.findById(appUser.getId()).orElse(null));
        }catch (Exception e) {
            log.error("Exception: {}",e.getMessage());
        }

    }
}
