package com.codeWithProject.hotelServer.controller.customer;


import com.codeWithProject.hotelServer.entity.Room;
import com.codeWithProject.hotelServer.services.customer.room.RoomService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class RoomController {
    private final RoomService roomService;

    @GetMapping("/rooms/{pageNumber}")
    public ResponseEntity<?> getAvailableRooms(@PathVariable int pageNumber){
        return ResponseEntity.ok(roomService.getAvailableRooms(pageNumber));
    }

    @GetMapping("/room/{id}")
    public ResponseEntity<?> getRoomById(@PathVariable Long id){
        try{
            return ResponseEntity.ok(roomService.getRoomById(id));
        }
        catch (EntityNotFoundException e )
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        catch (Exception e ){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("some went wrong.");
        }
    }
    @GetMapping("/search")
    public ResponseEntity<Page<Room>> searchRooms(@RequestParam(required = false) String name, Pageable pageable) {
        Page<Room> roomPage = roomService.searchRoomsByName(name, pageable);
        return ResponseEntity.ok(roomPage);
        }
}
