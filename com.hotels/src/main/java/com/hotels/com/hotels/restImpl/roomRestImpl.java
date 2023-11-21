package com.hotels.com.hotels.restImpl;

import com.hotels.com.hotels.constents.HotelsConstants;
import com.hotels.com.hotels.dao.roomDao;
import com.hotels.com.hotels.rest.roomRest;
import com.hotels.com.hotels.service.roomService;
import com.hotels.com.hotels.utils.hotelsUtils;
import com.hotels.com.hotels.wrapper.roomWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

@RestController
public class roomRestImpl implements roomRest {
    @Autowired
    roomService RoomService;

    @Autowired
    roomDao RoomDao;

    @Override
    public ResponseEntity<String> addNewRoom(Map<String, String> requestMap) {
        try {
            //System.out.println("inside userRestImpl");
            return RoomService.addNewRoom(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //System.out.println("Before return");
        return hotelsUtils.getResponeEntity(HotelsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<roomWrapper>> getAllRoom() {
        try {
            return RoomService.getAllRoom();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> update(Map<String, String> requestMap) {
        try {
            return RoomService.update(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return hotelsUtils.getResponeEntity(HotelsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> delete(Integer id) {
        try {
            return RoomService.delete(id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return hotelsUtils.getResponeEntity(HotelsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<List<roomWrapper>> getByHotel(Integer id) {
        try {
            return RoomService.getByHotel(id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<roomWrapper> getRoomById(Integer id) {
        try {
            return RoomService.getRoomById(id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new roomWrapper(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<roomWrapper> getRoomtById(Integer id) {
        return null;
    }


    @Modifying
    @Transactional
    @Override
    public ResponseEntity<String> updateRoomStatus(Map<String, String> requestMap) {
        try {
            return RoomService.updateRoomStatus(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return hotelsUtils.getResponeEntity(HotelsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}