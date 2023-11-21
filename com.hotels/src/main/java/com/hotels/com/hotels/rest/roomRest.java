package com.hotels.com.hotels.rest;

import com.hotels.com.hotels.wrapper.roomWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@RequestMapping(path = "/room")
public interface roomRest {
    @PostMapping(path = "/add")
    public ResponseEntity<String> addNewRoom(@RequestBody Map<String, String> requestMap);

    @GetMapping(path = "/get")
    public ResponseEntity<List<roomWrapper>> getAllRoom();

    @PostMapping(path = "/update")
    public ResponseEntity<String> update(@RequestBody(required = true) Map<String, String> requestMap);

    @PostMapping(path = "/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id);

    @GetMapping(path = "/getByHotel/{id}")
    public ResponseEntity<List<roomWrapper>> getByHotel(@PathVariable Integer id);

    @GetMapping(path = "/getRoomById/{id}")
    public ResponseEntity<roomWrapper> getRoomById(@PathVariable Integer id);

    ResponseEntity<roomWrapper> getRoomtById(Integer id);

    @PostMapping(path = "/updateRoomStatus")
    public ResponseEntity<String> updateRoomStatus(@RequestBody(required = true) Map<String, String> requestMap);

    /*
    @PostMapping(path = "/updateProductStatus")
    public ResponseEntity<String> updateProductStatus(@RequestBody Map<String, String> requestMap);
    */
}