package com.hotels.com.hotels.rest;

import com.hotels.com.hotels.POJO.Hotel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/hotel")
public interface HotelRest {
    @PostMapping(path = "/add")
    public ResponseEntity<String> addNewHotel(@RequestBody(required = true) Map<String, String> requestMap);

    @GetMapping(path = "/get")
    public ResponseEntity<List<Hotel>> getAllHotel(@RequestParam(required = false) String Value);

    @PostMapping(path = "/update")
    public ResponseEntity<String> update(@RequestBody(required = true) Map<String, String> requestMap);

}