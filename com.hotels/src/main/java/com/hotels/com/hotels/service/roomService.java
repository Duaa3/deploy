package com.hotels.com.hotels.service;


import com.hotels.com.hotels.wrapper.roomWrapper;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Map;

public interface roomService {
    ResponseEntity<String> addNewRoom(Map<String, String> requestMap);

    ResponseEntity<List<roomWrapper>> getAllRoom();

    ResponseEntity<String> update(Map<String, String> requestMap);

    ResponseEntity<String> delete(Integer id);

    ResponseEntity<List<roomWrapper>> getByHotel(Integer id);

    ResponseEntity<roomWrapper> getRoomById(Integer id);
    @Modifying
    @Transactional
    ResponseEntity<String> updateRoomStatus(Map<String, String> requestMap);

}
