package com.hotels.com.hotels.restImpl;

import com.hotels.com.hotels.POJO.Hotel;
import com.hotels.com.hotels.constents.HotelsConstants;
import com.hotels.com.hotels.dao.HotelDao;
import com.hotels.com.hotels.rest.HotelRest;
import com.hotels.com.hotels.service.HotelService;
import com.hotels.com.hotels.utils.hotelsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class HotelRestImpl implements HotelRest {
    @Autowired
    HotelService hotelService;

    @Autowired
    HotelDao hotelDao;
    @Override
    public ResponseEntity<String> addNewHotel(Map<String, String> requestMap) {
        try {
            //System.out.println("inside userRestImpl");
            return hotelService.addNewHotel(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //System.out.println("Before return");
        return hotelsUtils.getResponeEntity(HotelsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<Hotel>> getAllHotel(String Value) {
        try {
            return hotelService.getAllHotel(Value);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> update(Map<String, String> requestMap) {
        try {
            return hotelService.update(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return hotelsUtils.getResponeEntity(HotelsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}