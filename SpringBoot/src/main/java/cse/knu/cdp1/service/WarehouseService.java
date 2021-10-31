package cse.knu.cdp1.service;

import cse.knu.cdp1.dto.WarehouseDTO;

import java.util.List;

public interface WarehouseService {
    public abstract List<WarehouseDTO> warehouseList();
}
