package bdh.spring.boot.toDoApp.service.Impl;

import bdh.spring.boot.toDoApp.dto.TodoDto;
import bdh.spring.boot.toDoApp.entity.Todo;
import bdh.spring.boot.toDoApp.exception.ResourceNotFoundException;
import bdh.spring.boot.toDoApp.repository.TodoRepository;
import bdh.spring.boot.toDoApp.service.TodoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;

    private ModelMapper modelMapper;
    @Override
    public TodoDto addTodo(TodoDto todoDto) {
        Todo todo = modelMapper.map(todoDto, Todo.class);

        // Todo Jpa entity
        Todo savedTodo = todoRepository.save(todo);

        // Convert saved Todo Jpa entity object into TodoDto object

        TodoDto savedTodoDto = modelMapper.map(savedTodo, TodoDto.class);

        return savedTodoDto;
    }

    @Override
    public TodoDto getTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id:" + id));

        return modelMapper.map(todo, TodoDto.class);
    }

//    @Override
//    public List<TodoDto> getAllTodos() {
//        List<Todo> todos = todoRepository.findAll();
//
//        return todos.stream().map((todo) -> modelMapper.map(todo, TodoDto.class))
//                .collect(Collectors.toList());
//    }

    @Override
    public Page<TodoDto> getAllTodos(Pageable pageable) {
        Page<Todo> todoPage = todoRepository.findAll(pageable);
        List<TodoDto> todos = todoPage.getContent().stream()
                .map(todo -> modelMapper.map(todo, TodoDto.class))
                .collect(Collectors.toList());

        return new PageImpl<>(todos, pageable, todoPage.getTotalElements());
    }



    @Override
    public TodoDto updateTodo(TodoDto todoDto, Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id : " + id));
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());

        Todo updatedTodo = todoRepository.save(todo);

        return modelMapper.map(updatedTodo, TodoDto.class);
    }

    @Override
    public void deleteTodo(Long id) {

        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id : " + id));

        todoRepository.deleteById(id);

    }

    @Override
    public TodoDto completeTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id : " + id));

        todo.setCompleted(Boolean.TRUE);

        Todo updatedTodo = todoRepository.save(todo);

        return modelMapper.map(updatedTodo, TodoDto.class);
    }

    @Override
    public TodoDto inCompleteTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id : " + id));

        todo.setCompleted(Boolean.FALSE);

        Todo updatedTodo = todoRepository.save(todo);

        return modelMapper.map(updatedTodo, TodoDto.class);
    }

    @Override
    public Page<TodoDto> searchTodos(String searchProperty, Pageable pageable) {
        Page<Todo> todoPage = todoRepository.findByTitleContainingOrDescriptionContaining(searchProperty, searchProperty, pageable);
        List<TodoDto> todos = todoPage.getContent().stream()
                .map(todo -> modelMapper.map(todo, TodoDto.class))
                .collect(Collectors.toList());

        return new PageImpl<>(todos, pageable, todoPage.getTotalElements());
    }
}
