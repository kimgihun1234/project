package cse.knu.cdp1.dao;

import cse.knu.cdp1.dto.OfferDetailDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OfferDetailDAOImpl implements OfferDetailDAO {
    @Override
    public List<OfferDetailDTO> offerDetailList(SqlSessionTemplate session) { return session.selectList("mapper.offerDetailList"); }
}
