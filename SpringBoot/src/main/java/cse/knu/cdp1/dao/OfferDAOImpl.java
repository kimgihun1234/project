package cse.knu.cdp1.dao;

import cse.knu.cdp1.dto.OfferDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OfferDAOImpl implements OfferDAO{
    @Override
    public List<OfferDTO> offerList(SqlSessionTemplate session) { return session.selectList("mapper.offerList"); }

    @Override
    public OfferDTO getOfferInfo(SqlSessionTemplate session, String ex_requ_no) {
        return session.selectOne("mapper.getOfferInfo", ex_requ_no);
    }
}
