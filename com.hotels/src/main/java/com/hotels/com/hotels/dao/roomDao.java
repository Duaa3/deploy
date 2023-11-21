package com.hotels.com.hotels.dao;

import com.hotels.com.hotels.POJO.room;
import com.hotels.com.hotels.wrapper.roomWrapper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface roomDao extends JpaRepository<room, Integer> {

    List<roomWrapper> getAllRoom();

    List<roomWrapper> getByHotel(@Param("id") Integer id);

    roomWrapper getRoomById(@Param("id") Integer id);

    @Modifying
    @Transactional
    void updateRoomStatus(@Param("status") String status, @Param("id") Integer id);

}