package bdh.spring.boot.toDoApp.service;

import bdh.spring.boot.toDoApp.dto.TodoDto;
import bdh.spring.boot.toDoApp.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TodoService {

    TodoDto addTodo(TodoDto todoDto);

    TodoDto getTodo(Long id);

//    List<TodoDto> getAllTodos();
    Page<TodoDto> getAllTodos(Pageable pageable);


    TodoDto updateTodo(TodoDto todoDto, Long id);

    void deleteTodo(Long id);

    TodoDto completeTodo(Long id);

    TodoDto inCompleteTodo(Long id);

    Page<TodoDto> searchTodos(String searchProperty, Pageable pageable);

}
