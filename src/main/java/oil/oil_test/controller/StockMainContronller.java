package oil.oil_test.controller;

import oil.oil_test.POJO.StockMain;
import oil.oil_test.POJO.data.StockMainAll;
import oil.oil_test.dao.StockMainMapper;
import oil.oil_test.service.StockMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class StockMainContronller {

    @Autowired
    private StockMainService stockMainService;



    /**
     * 主库存表入库操作
     */
    @ResponseBody
    @RequestMapping("/stockMainInput")
    public String stockMainInput(Long storeOutID)
    {
        int a = stockMainService.stockMainInput(storeOutID);
        if(a == 1)
        {
            return "已经提交过，不能在更改库存";
        }
        return "入库成功";
    }

    /**
     * 现有库存表出库操作
     */
    @ResponseBody
    @RequestMapping("/stockMainOutput")
    public String stockMainOutput(Long storeOutID)
    {
        int a = stockMainService.stockMainOutput(storeOutID);
        if(a == 1)
        {
            return "已经提交过，不能在更改库存";
        }
        else if (a == 2)
        {
            return "库存没有对应条目，无法出库";
        }
        return "出库成功";
    }


    /**
     * 删除条目
     */
    @ResponseBody
    @RequestMapping("/stockMainDelete")
    public String stockMainDelete(Long StockID)
    {
        try{
            stockMainService.stockMainDelete(StockID);
        }
        catch (Exception e)
        {
            return "删除失败";
        }
        return "删除成功";
    }

    /**
     * 库存显示，选择主存储库的ID；
     */
    @ResponseBody
    @RequestMapping("/stockMainShow")
    public List<StockMainAll> stockMainShow(Long wareID)
    {
        return stockMainService.stockMainShow(wareID);
    }

    /**
     * 盘点信息更改
     */
    @ResponseBody
    @RequestMapping("/stockMainCheck")
    public String stockMainCheck(Long checkMainID)
    {
        try {
            int a = stockMainService.stockCheck(checkMainID);
            if (a != 0)
            {
                return "盘点油库,更新库存成功";
            }
        }
        catch (Exception e)
        {
            return "盘点失败";
        }
        return "盘点错误";
    }



}
