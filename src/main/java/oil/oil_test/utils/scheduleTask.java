package oil.oil_test.utils;


import oil.oil_test.POJO.Stock;
import oil.oil_test.POJO.StockMain;
import oil.oil_test.dao.StockMainMapper;
import oil.oil_test.dao.StockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Component
@Configuration
@EnableScheduling
public class scheduleTask {

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private StockMainMapper stockMainMapper;

    /**
     * 定时任务
     * 触发器不能本表进行insert、update和delete操作
     * 删除库存表中,库存为0的条目
     */
    @Transactional
    public void deleteStockTask ()
    {
        try
        {
        List<Stock> stocks = stockMapper.selectInfo();
        for(int i = 0; i < stocks.size();i++)
        {
            if(stocks.get(i).getStockNumber() <= 0)
            {
                stockMapper.deleteByPrimaryKey(stocks.get(i).getStockID());
            }
        }}
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    @Transactional
    public void deleteStockMainTask ()
    {
        try
        {
            List<StockMain> stockMains = stockMainMapper.selectInfo();
            for(int i = 0; i < stockMains.size();i++)
            {
                if(stockMains.get(i).getStockMainNumber() <= 0)
                {
                    stockMainMapper.deleteByPrimaryKey(stockMains.get(i).getStockMainID());
                }
            }}
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
