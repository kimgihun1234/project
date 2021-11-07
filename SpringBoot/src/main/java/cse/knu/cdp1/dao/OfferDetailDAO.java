package cse.knu.cdp1.dao;

import cse.knu.cdp1.dto.OfferDetailDTO;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

public interface OfferDetailDAO {
    public abstract List<OfferDetailDTO> offerDetailList(SqlSessionTemplate session);
}
