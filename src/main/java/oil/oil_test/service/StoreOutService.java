package oil.oil_test.service;

import oil.oil_test.POJO.StoreOut;
import oil.oil_test.POJO.data.StoreOutAll;
import oil.oil_test.POJO.data.UserAll;
import oil.oil_test.dao.StoreOutMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class StoreOutService {

    @Autowired
    private StoreOutMapper storeOutMapper;

    public int receiptAdd(StoreOut storeOutWeb, UserAll userAll)
    {
        storeOutWeb.setUserID(userAll.getUserID());
        storeOutWeb.setStoreOutState(0);
        Date date = new Date();
       java.sql.Date transDate = new java.sql.Date(date.getTime());
        storeOutWeb.setStoreOutDate(transDate);
        return storeOutMapper.insertSelective(storeOutWeb);
    }


    public int receiptDelete(Long receiptID,int storeOutState)
    {
        if (storeOutState == 0) {
            storeOutMapper.deleteByPrimaryKey(receiptID);
            return 1;
        }
        else {
            return 2;
        }
    }

    public int receiptUpdate(StoreOut storeOutWeb)
    {
        return storeOutMapper.updateByPrimaryKeySelective(storeOutWeb);
    }



    public List<StoreOutAll> receiptShow(Long wareID,boolean storeOutType,int state)
    {
        if (storeOutType == true) {
            return storeOutMapper.selectByWareIDIn(wareID,state);
        }
        else {
            return storeOutMapper.selectByWareIDOut(wareID,state);
        }
    }



    public List<StoreOutAll> receiptShowTestIn(Long wareID,int state)//test
    {
        return storeOutMapper.selectByWareIDIn(wareID,state);
    }

}


