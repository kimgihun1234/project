package cse.knu.cdp1.controller;

import cse.knu.cdp1.dto.LocationDTO;
import cse.knu.cdp1.dto.WarehouseDTO;
import cse.knu.cdp1.service.CustomerService;
import cse.knu.cdp1.service.LocationService;
import cse.knu.cdp1.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LocationController {

    @Autowired
    WarehouseService warehouseService;

    @Autowired
    LocationService locationService;

    @GetMapping("/locationList")
    public List<String> locationListReturn() {
        ArrayList<String> result = new ArrayList<>();

        List<LocationDTO> locationList = locationService.locationList();
        List<WarehouseDTO> warehouseList = warehouseService.warehouseList();

        for(WarehouseDTO warehouseData : warehouseList) {
            for(LocationDTO locationData : locationList) {
                if(warehouseData.getStor_cd().equals(locationData.getStor_cd())) {
                    result.add(warehouseData.getStor_cd() + "/" + warehouseData.getStor_nm() + "-" + locationData.getLoca_nm() + "/" + locationData.getLoca_cd());
                }
            }
        }

        /* 창고이름/창고번호/위치이름/위치번호 */
        return result;
    }
}
