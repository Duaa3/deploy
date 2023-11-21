package com.hotels.com.hotels.serviceImpl;


import com.hotels.com.hotels.JWT.CustomerUserDetailsService;
import com.hotels.com.hotels.JWT.JwtFilter;
import com.hotels.com.hotels.JWT.JwtUtil;
import com.hotels.com.hotels.POJO.Hotel;
import com.hotels.com.hotels.POJO.room;
import com.hotels.com.hotels.constents.HotelsConstants;
import com.hotels.com.hotels.dao.roomDao;
import com.hotels.com.hotels.service.roomService;
import com.hotels.com.hotels.utils.EmailUtil;
import com.hotels.com.hotels.utils.hotelsUtils;
import com.hotels.com.hotels.wrapper.roomWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class roomServiceImpl implements roomService {
    @Autowired
    roomDao RoomDao;

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    JwtFilter jwtFilter;
    @Autowired
    CustomerUserDetailsService customerUserDetailsService;

    @Autowired
    EmailUtil emailUtil;

    @Override
    public ResponseEntity<String> addNewRoom(Map<String, String> requestMap) {
        log.info("Inside addNewRoom{}", requestMap);
        try {
            if (jwtFilter.isAdmin()) {
                if (validateRoomMap(requestMap, false)) {
                    RoomDao.save(getRoomFromMap(requestMap, false));
                    return hotelsUtils.getResponeEntity("Room Added Successfully", HttpStatus.OK);
                }
            } else {
                return hotelsUtils.getResponeEntity(HotelsConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //System.out.println(CafeConstants.SOMETHING_WENT_WRONG);
        return hotelsUtils.getResponeEntity(HotelsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<List<roomWrapper>> getAllRoom() {
        try {
            return new ResponseEntity<>(RoomDao.getAllRoom(), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Override
    public ResponseEntity<String> update(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin()) {
                if (validateRoomMap(requestMap, true)) {
                    Optional optional = RoomDao.findById(Integer.parseInt(requestMap.get("id")));
                    if (!optional.isEmpty()) {
                        RoomDao.save(getRoomFromMap(requestMap, true));
                        return hotelsUtils.getResponeEntity("Room is updated successfully", HttpStatus.OK);

                    } else {
                        return hotelsUtils.getResponeEntity("Room id doesn't exist", HttpStatus.OK);
                    }

                }
                return hotelsUtils.getResponeEntity(HotelsConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            } else {
                return hotelsUtils.getResponeEntity(HotelsConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return hotelsUtils.getResponeEntity(HotelsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> delete(Integer id) {
        try {
            if (jwtFilter.isAdmin()) {
                Optional optional = RoomDao.findById(id);
                if (!optional.isEmpty()) {
                    RoomDao.deleteById(id);
                    //System.out.println("Room is deleted successfully");
                    return hotelsUtils.getResponeEntity("Room is deleted successfully", HttpStatus.OK);
                }
                //System.out.println("Room id doesn't exist");
                return hotelsUtils.getResponeEntity("Room id doesn't exist", HttpStatus.OK);
            } else {
                return hotelsUtils.getResponeEntity(HotelsConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //System.out.println(CafeConstants.SOMETHING_WENT_WRONG);
        return hotelsUtils.getResponeEntity(HotelsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<List<roomWrapper>> getByHotel(Integer id) {
        try {
            return new ResponseEntity<>(RoomDao.getByHotel(id), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<roomWrapper> getRoomById(Integer id) {
        try {
            return new ResponseEntity<>(RoomDao.getRoomById(id), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new roomWrapper(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Modifying
    @Transactional
    @Override
    public ResponseEntity<String> updateRoomStatus(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin()) {
                Optional optional = RoomDao.findById(Integer.parseInt(requestMap.get("id")));
                if (!optional.isEmpty()) {
                    RoomDao.updateRoomStatus(requestMap.get("status"), Integer.parseInt(requestMap.get("id")));
                    return hotelsUtils.getResponeEntity("Room status is updated successfully", HttpStatus.OK);
                }
                return hotelsUtils.getResponeEntity("Room id doesn't exist", HttpStatus.OK);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return hotelsUtils.getResponeEntity(HotelsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private boolean validateRoomMap(Map<String, String> requestMap, boolean validateId) {
        if (requestMap.containsKey("name")) {
            if (requestMap.containsKey("id") && validateId) {
                return true;
            } else if (!validateId) {
                return true;
            }
        }
        return false;
    }

    private room getRoomFromMap(Map<String, String> requestMap, boolean isAdd) {
        room Room = new room();
        Hotel hotel = new Hotel();
        hotel.setId(Integer.parseInt(requestMap.get("hotelId")));

        if (isAdd) {
            Room.setId(Integer.parseInt(requestMap.get("id")));
        } else {
            Room.setstatus("true");
        }
        Room.setHotel(hotel);
        Room.setName(requestMap.get("name"));
        Room.setDescription(requestMap.get("description"));
        Room.setPrice(Integer.parseInt(requestMap.get("price")));
        Room.setstatus(String.valueOf(isAdd));

        return Room;
    }
}
