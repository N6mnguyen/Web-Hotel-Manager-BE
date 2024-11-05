package com.codeWithProject.hotelServer.repository;

import com.codeWithProject.hotelServer.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository< Room,Long> {

    Page<Room> findByAvailable(boolean available, Pageable pageable);

    //search
    @Query("SELECT r FROM Room r WHERE LOWER(r.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<Room> findByName(@Param("name") String name, Pageable pageable);

    //phan

    // List<Room> findByNameContainingIgnoreCase(String name);

}
