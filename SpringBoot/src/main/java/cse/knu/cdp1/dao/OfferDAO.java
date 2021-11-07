package cse.knu.cdp1.dao;

import cse.knu.cdp1.dto.OfferDTO;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

public interface OfferDAO {
    public abstract List<OfferDTO> offerList(SqlSessionTemplate session);
    public abstract OfferDTO getOfferInfo(SqlSessionTemplate session, String ex_requ_no);
}
