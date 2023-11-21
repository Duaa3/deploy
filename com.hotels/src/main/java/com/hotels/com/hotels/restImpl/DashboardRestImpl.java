package com.hotels.com.hotels.restImpl;


import com.hotels.com.hotels.rest.DashboardRest;
import com.hotels.com.hotels.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DashboardRestImpl implements DashboardRest {

   @Autowired
   DashboardService dashboardService;
    @Override
    public ResponseEntity<Map<String, Object>> getCount() {
        return dashboardService.getCount();
    }
}