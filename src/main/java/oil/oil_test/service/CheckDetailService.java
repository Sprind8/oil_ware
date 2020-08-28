package oil.oil_test.service;

import oil.oil_test.POJO.CheckDetail;
import oil.oil_test.POJO.CheckMain;
import oil.oil_test.POJO.Stock;
import oil.oil_test.POJO.data.CheckDetailAll;
import oil.oil_test.POJO.data.StockAll;
import oil.oil_test.dao.CheckDetailMapper;
import oil.oil_test.dao.CheckMainMapper;
import oil.oil_test.dao.StockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckDetailService {

    @Autowired
    private CheckDetailMapper checkDetailMapper;

    @Autowired
    private CheckMainMapper checkMainMapper;
    @Autowired
    private StockMapper stockMapper;

    public int checkDetailAdd(CheckDetail checkDetail,String barCode)
    {
        CheckMain checkMain = checkMainMapper.selectByPrimaryKey(checkDetail.getCheckMainID());

        List<StockAll> stockAlls = stockMapper.selectByWareID(checkMain.getWareID());
        for(int i = 0; i < stockAlls.size();i++)
        {
            if (barCode.equals(stockAlls.get(i).getStockBarCode()) == true)
            {
                checkDetail.setStockID(stockAlls.get(i).getStockID());
                checkDetail.setCheckChangeNum(checkDetail.getStockRealNum() - checkDetail.getCheckNum());
                return checkDetailMapper.insertSelective(checkDetail);
            }
        }
        return 0;
    }


    public int checkDetailUpdate(CheckDetail checkDetail)
    {
        CheckMain checkMain = checkMainMapper.selectByPrimaryKey(checkDetail.getCheckMainID());
        if(checkMain.getCheckState() == 1 || checkMain.getCheckState() == 3)
        {
            checkDetail.setCheckChangeNum(checkDetail.getStockRealNum() - checkDetail.getCheckNum());
            return checkDetailMapper.updateByPrimaryKeySelective(checkDetail);
        }
        else
        {
            return 0;
        }
    }



    public int checkDetailDelete(Long checkDetailID,Long checkMainID)
    {
        CheckMain checkMain = checkMainMapper.selectByPrimaryKey(checkMainID);
        if (checkMain.getCheckState() == 1 || checkMain.getCheckState() == 3)
        {
            return checkDetailMapper.deleteByPrimaryKey(checkDetailID);
        }
        else {
            return 0;
        }
    }

    public List<CheckDetailAll> checkDetailShow(Long checkMainID)
    {
        return checkDetailMapper.checkDetailShow(checkMainID);
    }

}
