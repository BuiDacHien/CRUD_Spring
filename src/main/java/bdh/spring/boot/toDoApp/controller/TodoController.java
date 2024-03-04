package bdh.spring.boot.toDoApp.controller;

import bdh.spring.boot.toDoApp.dto.TodoDto;
import bdh.spring.boot.toDoApp.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/todos")
@AllArgsConstructor
public class TodoController {

    private TodoService todoService;

    // Build Add Todo REST API

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto) {

        TodoDto savedTodo = todoService.addTodo(todoDto);

        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }

    // Build Get Todo REST API
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable("id") Long todoId) {
        TodoDto todoDto = todoService.getTodo(todoId);
        return new ResponseEntity<>(todoDto, HttpStatus.OK);
    }

    // Build Get All Todos REST API
//    @PreAuthorize("hasAnyRole('ADMIN','USER')")
//    @GetMapping
//    public ResponseEntity<List<TodoDto>> getAllTodos(){
//        List<TodoDto> todos = todoService.getAllTodos();
//        //return new ResponseEntity<>(todos, HttpStatus.OK);
//        return ResponseEntity.ok(todos);
//    }

//    @PreAuthorize("hasAnyRole('ADMIN','USER')")
//    @GetMapping
//    public ResponseEntity<Map<String, Object>> getAllTodos(
//            @RequestParam(value = "page", defaultValue = "0") int page,
//            @RequestParam(value = "size", defaultValue = "10") int size,
//            @RequestParam(value = "sort", defaultValue = "id") String order) {
//
//        Sort.Direction direction = Sort.Direction.ASC;
//        if (order.endsWith("_desc")) {
//            direction = Sort.Direction.DESC;
//            order = order.substring(0, order.length() - 5);
//        } else if (order.endsWith("_asc")){
//            direction = Sort.Direction.ASC;
//            order = order.substring(0, order.length() - 4);
//        }
//
//        Pageable pageable = PageRequest.of(page, size, direction, order);
//
//        Page<TodoDto> todoPage = todoService.getAllTodos(pageable);
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("content", todoPage.getContent());
//        response.put("totalElements", todoPage.getTotalElements());
//        response.put("totalPages", todoPage.getTotalPages());
//        response.put("size", todoPage.getSize());
//        response.put("number", todoPage.getNumber());
//        return ResponseEntity.ok(response);
//    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllTodos(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "id") String order,
            @RequestParam(value = "search", required = false) String searchProperty) {

        Sort.Direction direction = Sort.Direction.ASC;
        if (order.endsWith("_desc")) {
            direction = Sort.Direction.DESC;
            order = order.substring(0, order.length() - 5);
        } else if (order.endsWith("_asc")) {
            direction = Sort.Direction.ASC;
            order = order.substring(0, order.length() - 4);
        }

        Pageable pageable = PageRequest.of(page, size, direction, order);

        Page<TodoDto> todoPage;
        if (searchProperty != null) {
            todoPage = todoService.searchTodos(searchProperty, pageable);
        } else {
            todoPage = todoService.getAllTodos(pageable);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("content", todoPage.getContent());
        response.put("totalElements", todoPage.getTotalElements());
        response.put("totalPages", todoPage.getTotalPages());
        response.put("size", todoPage.getSize());
        response.put("number", todoPage.getNumber());
        return ResponseEntity.ok(response);
    }


    // Build Update Todo REST API
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto, @PathVariable("id") Long todoId) {
        TodoDto updatedTodo = todoService.updateTodo(todoDto, todoId);
        return ResponseEntity.ok(updatedTodo);
    }

    // Build Delete Todo REST API
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long todoId) {
        todoService.deleteTodo(todoId);
        return ResponseEntity.ok("Todo deleted successfully!.");
    }

    // Build Complete Todo REST API
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("{id}/complete")
    public ResponseEntity<TodoDto> completeTodo(@PathVariable("id") Long todoId) {
        TodoDto updatedTodo = todoService.completeTodo(todoId);
        return ResponseEntity.ok(updatedTodo);
    }

    // Build In Complete Todo REST API
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("{id}/in-complete")
    public ResponseEntity<TodoDto> inCompleteTodo(@PathVariable("id") Long todoId) {
        TodoDto updatedTodo = todoService.inCompleteTodo(todoId);
        return ResponseEntity.ok(updatedTodo);
    }

}
