package bdh.spring.boot.toDoApp.service;

import bdh.spring.boot.toDoApp.dto.JwtAuthResponse;
import bdh.spring.boot.toDoApp.dto.LoginDto;
import bdh.spring.boot.toDoApp.dto.RegisterDto;

public interface AuthService {

    String register(RegisterDto registerDto);

    JwtAuthResponse login(LoginDto loginDto);
}
