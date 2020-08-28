package oil.oil_test.service;



import oil.oil_test.POJO.*;
import oil.oil_test.POJO.data.StockAll;
import oil.oil_test.POJO.data.StockMainAll;
import oil.oil_test.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class StockMainService {


    @Autowired
    private StockMainMapper stockMainMapper;
    @Autowired
    private StoreOutMapper storeOutMapper;
    @Autowired
    private StoreOutDetailMapper storeOutDetailMapper;
    @Autowired
    private CheckMainMapper checkMainMapper;
    @Autowired
    private CheckDetailMapper checkDetailMapper;


    /**
     * 主库存表入库,做系列判断；
     * 向主记录表插入时间；
     */
    @Transactional
    public int stockMainInput(Long storeOutID)
    {
        StoreOut storeOut = storeOutMapper.selectByPrimaryKey(storeOutID);

        if (storeOut.getStoreOutState() == 0)
        {
            List<StoreOutDetail> storeOutDetails = storeOutDetailMapper.selectByStoreOutID(storeOutID);
            Date date = new Date();
            java.sql.Date transDate = new java.sql.Date(date.getTime());

            for(int i=0;i<storeOutDetails.size();i++)
            {
                int code = 0;
                StockMain stockMain = new StockMain();
                stockMain.setWareID(storeOutDetails.get(i).getWareID());
                stockMain.setOilID(storeOutDetails.get(i).getOilID());
                stockMain.setStockMainProduceDate(storeOutDetails.get(i).getStoreDetailProDate());
                stockMain.setStockMainDate(transDate);
                stockMain.setStockMainReGruantee(storeOutDetails.get(i).getStoreDetailGra());
                stockMain.setStockMainNumber(storeOutDetails.get(i).getStoreDetailNum()); //记录的是操作数量
                //时效状态(根据生产日期和实际保质期计算得出的过期时间)
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(stockMain.getStockMainProduceDate());
                calendar.add(Calendar.DATE,stockMain.getStockMainReGruantee());
                SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
                String deadTime = s.format(calendar.getTime());
                stockMain.setStockMainState(deadTime);
                //差条形码

                //判断库存条目是否有对应的库存
                List<StockMain> stockMainResults = stockMainMapper.selectMainByTwoID(stockMain.getOilID(),stockMain.getWareID());

                if (stockMainResults != null) {
                    for (int j = 0; j < stockMainResults.size(); j++) {
                        //有则改变库存量，并更新操作时间
                        System.out.println(stockMainResults.get(j).toString());
                        if (stockMain.getStockMainProduceDate().equals(stockMainResults.get(j).getStockMainProduceDate())
                                && stockMain.getStockMainReGruantee() == stockMainResults.get(j).getStockMainReGruantee())
                        {
                            float num = stockMain.getStockMainNumber() + stockMainResults.get(j).getStockMainNumber();
                            stockMainResults.get(j).setStockMainNumber(num);
                            stockMainResults.get(j).setStockMainDate(transDate);
                            if (stockMainMapper.updateByPrimaryKeySelective(stockMainResults.get(j)) != 0) {
                                code = 1;
                            }
                        }
                    }
                }
                //没有则新增一条记录
                if (code != 1)
                {
                    stockMainMapper.insertSelective(stockMain);
                }
            }
            //操作完毕，更改主记录表信息
            storeOut.setStoreOutDate(transDate);
            storeOut.setStoreOutState(1);
            storeOutMapper.updateByPrimaryKeySelective(storeOut);
            return 0;
        }
        return 1; //已经提交过，不能在更改库存
    }

    /**
     * 库存表出库,做系列判断
     * 向主记录表插入时间；
     */
    @Transactional
    public int stockMainOutput(Long storeOutID)
    {
        StoreOut storeOut = storeOutMapper.selectByPrimaryKey(storeOutID);

        if (storeOut.getStoreOutState() == 0)
        {
            List<StoreOutDetail> storeOutDetails = storeOutDetailMapper.selectByStoreOutID(storeOutID);
            Date date = new Date();
            java.sql.Date transDate = new java.sql.Date(date.getTime());
            for(int i=0;i<storeOutDetails.size();i++)
            {
                int code = 0;
                StockMain stockMain = new StockMain();
                stockMain.setWareID(storeOutDetails.get(i).getWareID());
                stockMain.setOilID(storeOutDetails.get(i).getOilID());
                stockMain.setStockMainProduceDate(storeOutDetails.get(i).getStoreDetailProDate());
                stockMain.setStockMainDate(transDate);
                stockMain.setStockMainReGruantee(storeOutDetails.get(i).getStoreDetailGra());
                stockMain.setStockMainNumber(storeOutDetails.get(i).getStoreDetailNum()); //记录的是操作数量
                //差条形码和时效状态（定时任务）

                //判断库存条目是否有对应的库存
                List<StockMain> stockMainResults = stockMainMapper.selectMainByTwoID(stockMain.getOilID(),stockMain.getWareID());
                for(int j=0;j<stockMainResults.size();j++)
                {
                    //有则改变库存量，并更新操作时间
                    if (stockMain.getStockMainProduceDate().equals(stockMainResults.get(j).getStockMainProduceDate())
                            && stockMain.getStockMainReGruantee() == stockMainResults.get(j).getStockMainReGruantee())
                    {
                        float num = stockMainResults.get(j).getStockMainNumber()- stockMain.getStockMainNumber() ;
                        if (num < 0)
                        {
                            num = 0;
                        }
                        stockMainResults.get(j).setStockMainNumber(num);
                        stockMainResults.get(j).setStockMainDate(transDate);
                        if (stockMainMapper.updateByPrimaryKeySelective(stockMainResults.get(j)) != 0) {
                            code = 1;
                        }
                    }
                }
                //没有则报错
                if (code != 1)
                {
                    return 2;//库存没有对应条目，无法出库
                }
            }
            //操作完毕，更改主记录表信息
            storeOut.setStoreOutDate(transDate);
            storeOut.setStoreOutState(1);
            storeOutMapper.updateByPrimaryKeySelective(storeOut);
            return 0;
        }
        return 1; //已经提交过，不能在更改库存
    }

    /**
     * 删除条目
     */
    public int stockMainDelete(Long StockID)
    {
        return stockMainMapper.deleteByPrimaryKey(StockID);
    }


    /**
     * 盘点信息更改
     */
    @Transactional
    public int stockCheck(Long checkMainID)
    {
        CheckMain checkMain = checkMainMapper.selectByPrimaryKey(checkMainID);
        Date date = new Date();
        java.sql.Date transDate = new java.sql.Date(date.getTime());


        if (checkMain.getCheckState() == 0)
        {
            List<CheckDetail> checkDetails = checkDetailMapper.seletctByCheckMainID(checkMainID);
            for (int i = 0; i < checkDetails.size();i++)
            {
                StockMain stock = stockMainMapper.selectByPrimaryKey(checkDetails.get(i).getStockID());
                float num = checkDetails.get(i).getCheckNum() + stock.getStockMainNumber();
                StockMain result = new StockMain();
                stock.setStockMainID(stock.getStockMainID());
                stock.setStockMainNumber(num);
                stock.setStockMainDate(transDate);
                stockMainMapper.updateByPrimaryKeySelective(result);
            }
            //更新库存以后，更新盘点主表
            checkMain.setCheckDate(transDate);
            checkMain.setCheckState(1);
            checkMainMapper.updateByPrimaryKeySelective(checkMain);
            return 0;
        }
        return 1; //已经提交过，无法更改
    }

    /**
     * 显示库存
     */
    public List<StockMainAll> stockMainShow(Long wareID)
    {
        return stockMainMapper.selectMainByWareID(wareID);
    }
}
