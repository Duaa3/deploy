package com.hotels.com.hotels.serviceImpl;



import com.hotels.com.hotels.dao.BillDao;
import com.hotels.com.hotels.dao.HotelDao;
import com.hotels.com.hotels.dao.roomDao;
import com.hotels.com.hotels.service.DashboardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    HotelDao hotelDao;

    @Autowired
    roomDao RoomDao;

    @Autowired
    BillDao billDao;


    @Override
    public ResponseEntity<Map<String, Object>> getCount() {
        System.out.println("inside getCount");

        Map<String , Object> map = new HashMap<>();
        map.put("hotel" , hotelDao.count());
        map.put("room" , RoomDao.count());
        map.put("bill" , billDao.count());
        return new ResponseEntity<>(map , HttpStatus.OK);
    }
}
