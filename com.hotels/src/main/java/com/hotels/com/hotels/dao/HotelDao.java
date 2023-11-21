package com.hotels.com.hotels.dao;


import com.hotels.com.hotels.POJO.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelDao extends JpaRepository<Hotel, Integer> {
    List<Hotel> getAllHotel();

}