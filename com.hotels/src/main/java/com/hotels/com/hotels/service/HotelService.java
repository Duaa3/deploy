package com.hotels.com.hotels.service;

import com.hotels.com.hotels.POJO.Hotel;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;


public interface HotelService {
    ResponseEntity<String> addNewHotel(Map<String, String> requestMap);
    ResponseEntity<List<Hotel>> getAllHotel(String Value);

    ResponseEntity<String> update(Map<String, String> requestMap);
}