package cse.knu.cdp1.service;

import cse.knu.cdp1.dto.BarcodeDTO;
import cse.knu.cdp1.dto.CustomerDTO;

public interface BarcodeService {
    public abstract BarcodeDTO getBarcodeInfo(String barcode);
}
