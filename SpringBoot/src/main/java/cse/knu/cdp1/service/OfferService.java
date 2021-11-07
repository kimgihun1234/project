package cse.knu.cdp1.service;

import cse.knu.cdp1.dto.OfferDTO;

import java.util.List;

public interface OfferService {
    public abstract List<OfferDTO> offerList();
    public abstract OfferDTO getOfferInfo(String ex_requ_no);
}
