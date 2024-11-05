package com.codeWithProject.hotelServer.repository;

import com.codeWithProject.hotelServer.entity.Reservation;
import com.codeWithProject.hotelServer.entity.Room;
import com.codeWithProject.hotelServer.entity.User;
import com.codeWithProject.hotelServer.enums.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findFirstByEmail(String email);

    Optional<User> findByUserRole(UserRole userRole);

    Page<User> findByNameContaining(String name, Pageable pageable);

}
