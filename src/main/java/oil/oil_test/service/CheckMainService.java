package oil.oil_test.service;


import oil.oil_test.POJO.CheckMain;
import oil.oil_test.POJO.data.CheckMainAll;
import oil.oil_test.dao.CheckMainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CheckMainService {

    @Autowired
    private CheckMainMapper checkMainMapper;

    public  int checkMainAdd(CheckMain checkMain)
    {
        Date date = new Date();
        java.sql.Date transDate = new java.sql.Date(date.getTime());
        checkMain.setCheckDate(transDate);
        return checkMainMapper.insertSelective(checkMain);
    }

    public int checkMainCheck(CheckMain checkMain)
    {
        Date date = new Date();
        java.sql.Date transDate = new java.sql.Date(date.getTime());
        checkMain.setCheckTime(transDate);
        return checkMainMapper.updateByPrimaryKeySelective(checkMain);
    }

    public  int checkDetailOver(Long checkMainID)
    {
        CheckMain checkMain = new CheckMain();
        checkMain.setCheckMainID(checkMainID);
        checkMain.setCheckState(2);
        return checkMainMapper.updateByPrimaryKeySelective(checkMain);
    }

    public  int checkMainUpadate(CheckMain checkMain)
    {
        return checkMainMapper.updateByPrimaryKeySelective(checkMain);
    }

    public int checkMainDelete(Long checkMainID,int checkState)
    {
        if(checkState == 1) {
           checkMainMapper.deleteByPrimaryKey(checkMainID);
        }
        else if (checkState == 2)
        {
            return 2;
        }
        return 1;
    }

    public List<CheckMainAll> checkMainShow(Long wareID)
    {
        return checkMainMapper.selectByWareID(wareID);
    }
}
