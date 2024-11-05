package com.codeWithProject.hotelServer.services.auth;
import com.codeWithProject.hotelServer.dto.RoomDto;
import com.codeWithProject.hotelServer.dto.SignupRequest;
import com.codeWithProject.hotelServer.dto.UserDto;
import com.codeWithProject.hotelServer.entity.Room;
import com.codeWithProject.hotelServer.entity.User;
import com.codeWithProject.hotelServer.enums.UserRole;
import com.codeWithProject.hotelServer.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    @PostConstruct
    public void createAnAdminAccount(){
        Optional<User>adminAccount = userRepository.findByUserRole(UserRole.ADMIN);
        if (adminAccount.isEmpty()){
            User user = new User();
            user.setEmail("admin@test.com");
            user.setName("Admin");
            user.setUserRole(UserRole.ADMIN);
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            userRepository.save(user);
            System.out.println("Admin account created successfully");
        }
        else {
            System.out.println("Admin account already exist");
        }
    }

    public UserDto createUser(SignupRequest signupRequest){
        if (userRepository.findFirstByEmail(signupRequest.getEmail()).isPresent()){
            throw new EntityExistsException("User Already Present With Email"+ signupRequest.getEmail());
        }
        User user   = new User();
        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());
        user.setUserRole(UserRole.CUSTOMER);
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        User createUser = userRepository.save(user);
        return createUser.getUserDto();
    }

    @Override
    public Page<User> getByUser(int page, int size, String name) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findByNameContaining(name, pageable);
    }

    public UserDto getUserById(Long id ){
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            return optionalUser.get().getUserDto();
        }
        else {
            throw new EntityNotFoundException("Room not present");
        }
    }

    public boolean updateUser(Long id, UserDto userDto){
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setName(userDto.getName());
            user.setEmail(userDto.getEmail());
            userRepository.save(user);
            return  true;
        }else {
            return false;
        }
    }
    public void deleteUser(Long id ){
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            userRepository.deleteById(id);
        }else {
            throw new EntityNotFoundException("Room not present");
        }
    }
}
