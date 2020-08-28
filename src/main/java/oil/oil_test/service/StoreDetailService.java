package oil.oil_test.service;

import oil.oil_test.POJO.StoreOut;
import oil.oil_test.POJO.StoreOutDetail;
import oil.oil_test.POJO.data.StoreDetailAll;
import oil.oil_test.dao.StoreOutDetailMapper;
import oil.oil_test.dao.StoreOutMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreDetailService {
    @Autowired
    private StoreOutDetailMapper storeOutDetailMapper;

    @Autowired
    private StoreOutMapper storeOutMapper;

    public int storeDetailAdd(StoreOutDetail storeOutDetail)
    {
        return storeOutDetailMapper.insertSelective(storeOutDetail);
    }

    public int storeDetailUpdate(StoreOutDetail storeOutDetail)
    {
        StoreOut storeOut = storeOutMapper.selectByPrimaryKey(storeOutDetail.getStoreOutID());
        if (storeOut.getStoreOutState() == 0) {
            return storeOutDetailMapper.updateByPrimaryKeySelective(storeOutDetail);
        }
        else {
            return 0;
        }
    }

    public int storeDetailDelete(Long storedetailID,Long storeOutID)
    {
        StoreOut storeOut = storeOutMapper.selectByPrimaryKey(storeOutID);
        if (storeOut.getStoreOutState() == 0) {
            return storeOutDetailMapper.deleteByPrimaryKey(storedetailID);
        }
        else
        {
            return 0;
        }
    }

    public List<StoreDetailAll> storeDetailShow(Long storeOutID)
    {
        return storeOutDetailMapper.selectAllByStoreOutID(storeOutID);
    }
}
