package vl.data.collectionservice.service.todo;

import vl.data.collectionservice.beans.TodoBean;
import vl.data.collectionservice.entities.Todo;

import java.util.List;

public interface TodoService {


    List<TodoBean> getTodos();
    Todo getTodo(Long id);
    void deleteTodo(Long id);



}
