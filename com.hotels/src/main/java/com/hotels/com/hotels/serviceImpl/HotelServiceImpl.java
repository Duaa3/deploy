package com.hotels.com.hotels.serviceImpl;


import com.google.common.base.Strings;
import com.hotels.com.hotels.JWT.CustomerUserDetailsService;
import com.hotels.com.hotels.JWT.JwtFilter;
import com.hotels.com.hotels.JWT.JwtUtil;
import com.hotels.com.hotels.POJO.Hotel;
import com.hotels.com.hotels.constents.HotelsConstants;
import com.hotels.com.hotels.dao.HotelDao;
import com.hotels.com.hotels.service.HotelService;
import com.hotels.com.hotels.utils.EmailUtil;
import com.hotels.com.hotels.utils.hotelsUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    HotelDao hotelDao;

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
    public ResponseEntity<String> addNewHotel(Map<String, String> requestMap) {
        log.info("Inside addNewHotel{}", requestMap);
        try {
            if(jwtFilter.isAdmin()){
                if(validateHotelMap(requestMap, false)){
                    hotelDao.save(getHotelFromMap(requestMap , false));
                    return hotelsUtils.getResponeEntity("Hotel Added Successfully", HttpStatus.OK);
                }
            }else{
                return hotelsUtils.getResponeEntity(HotelsConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //System.out.println(CafeConstants.SOMETHING_WENT_WRONG);
        return hotelsUtils.getResponeEntity(HotelsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validateHotelMap(Map<String, String> requestMap, boolean validateId) {
        if (requestMap.containsKey("name")) {
            if(requestMap.containsKey("id") && validateId){
                return true;
            }else if(!validateId){
                return true;
            }
        }
        return false;
    }
    private Hotel getHotelFromMap(Map<String, String> requestMap, boolean isAdd) {
        Hotel hotel = new Hotel();
        if(isAdd){
            hotel.setId(Integer.parseInt(requestMap.get("id")));
        }
        hotel.setName(requestMap.get("name"));
        return hotel;
    }

    @Override
    public ResponseEntity<List<Hotel>> getAllHotel(String Value) {
        try {
            if(!Strings.isNullOrEmpty(Value) && Value.equalsIgnoreCase("true")) {
                return new ResponseEntity<List<Hotel>>(new ArrayList<>(), HttpStatus.OK);
            }
            return new ResponseEntity<>(hotelDao.findAll(), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<List<Hotel>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> update(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin()) {
                if (validateHotelMap(requestMap , true)) {

                    Optional optional = hotelDao.findById(Integer.parseInt(requestMap.get("id")));

                    if (!optional.isEmpty()) {
                       hotelDao.save(getHotelFromMap(requestMap,true));
                        return hotelsUtils.getResponeEntity("Hotel is updated successfully", HttpStatus.OK);

                    } else {
                        return hotelsUtils.getResponeEntity("Hotel id doesn't exist", HttpStatus.OK);
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
}
