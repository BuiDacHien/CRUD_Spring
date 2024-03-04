package bdh.spring.boot.toDoApp.controller;

import bdh.spring.boot.toDoApp.dto.TodoDto;
import bdh.spring.boot.toDoApp.dto.UserDto;
import bdh.spring.boot.toDoApp.entity.User;
import bdh.spring.boot.toDoApp.repository.UserRepository;
import bdh.spring.boot.toDoApp.security.CustomUserDetailsService;
import bdh.spring.boot.toDoApp.security.JwtAuthenticationFilter;
import bdh.spring.boot.toDoApp.security.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    UserRepository userRepository;
    ModelMapper modelMapper;


    @GetMapping("/current")
    public ResponseEntity<UserDto> getCurrentUser(Authentication authentication) {
        if (authentication == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();

        User user = userRepository.findByEmail(email);
        UserDto userDto = new UserDto(user.getName(), user.getUsername(), user.getEmail());
        return ResponseEntity.ok(userDto);
    }
}
