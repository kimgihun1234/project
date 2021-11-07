package cse.knu.cdp1.controller;

import cse.knu.cdp1.dto.LocationDTO;
import cse.knu.cdp1.dto.WarehouseDTO;
import cse.knu.cdp1.service.LocationService;
import cse.knu.cdp1.service.WarehouseService;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;
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

    @Alias("locationresult")
    @Getter
    @Setter
    @ToString
    public class ResultClass {
        String stor_cd;
        String stor_nm;
        String loca_cd;
        String loca_nm;
    }

    @GetMapping("/locationList")
    public List<ResultClass> locationListReturn() {
        ArrayList<ResultClass> result = new ArrayList<>();

        List<LocationDTO> locationList = locationService.locationList();
        List<WarehouseDTO> warehouseList = warehouseService.warehouseList();

        for(WarehouseDTO warehouseData : warehouseList) {
            for(LocationDTO locationData : locationList) {
                if(warehouseData.getStor_cd().equals(locationData.getStor_cd())) {
                    ResultClass temp = new ResultClass();
                    temp.stor_cd = warehouseData.getStor_cd();
                    temp.stor_nm = warehouseData.getStor_nm();
                    temp.loca_cd = locationData.getLoca_cd();
                    temp.loca_nm = locationData.getLoca_nm();
                    result.add(temp);
                }
            }
        }

        /* 창고이름/창고번호/위치이름/위치번호 */
        return result;
    }
}
