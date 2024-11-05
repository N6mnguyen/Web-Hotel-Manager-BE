package com.codeWithProject.hotelServer.services.auth;


import com.codeWithProject.hotelServer.dto.SignupRequest;
import com.codeWithProject.hotelServer.dto.UserDto;
import com.codeWithProject.hotelServer.entity.User;
import org.springframework.data.domain.Page;

public interface AuthService {

    UserDto createUser(SignupRequest signupRequest);

//    List<User> getUser();

    Page<User> getByUser (int page, int size, String name );

    UserDto getUserById(Long id );

    boolean updateUser(Long id, UserDto userDto);

    void deleteUser(Long id );

}
