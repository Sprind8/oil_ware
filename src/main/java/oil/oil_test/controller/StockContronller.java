package oil.oil_test.controller;

import oil.oil_test.POJO.Stock;
import oil.oil_test.POJO.data.PieChart;
import oil.oil_test.POJO.data.StockAll;
import oil.oil_test.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


@Controller
public class StockContronller {

    @Autowired
    private StockService stockService;

    /**
     * 现有库存表入库操作
     */
    @ResponseBody
    @RequestMapping("/stockInput")
    public String stockInput(Long storeOutID) {
        int a = stockService.stockInput(storeOutID);
        if (a == 1) {
            return "已经提交过，不能在更改库存";
        }
        return "入库成功";
    }

    /**
     * 现有库存表出库操作
     */
    @ResponseBody
    @RequestMapping("/stockOutput")
    public String stockOutput(Long storeOutID) {
        int a = stockService.stockOutput(storeOutID);
        if (a == 1) {
            return "已经提交过，不能在更改库存";
        } else if (a == 2) {
            return "库存没有对应条目，无法出库";
        }
        return "出库成功";
    }


    /**
     * 删除条目
     */
    @ResponseBody
    @RequestMapping("/stockDelete")
    public String stockDelete(Long StockID) {
        try {
            stockService.stockDelete(StockID);
        } catch (Exception e) {
            return "删除失败";
        }
        return "删除成功";
    }

    /**
     * 更新条目（能不能更新）
     */


    /**
     * 盘点信息更改
     */
    @ResponseBody
    @RequestMapping("/stockCheck")
    public String stockCheck(Long checkMainID) {
        try {
            int a = stockService.stockCheck(checkMainID);
            if (a == 0) {
                return "盘点油库,更新库存成功";
            }
            return "更新库存失败";
        } catch (Exception e) {
            System.out.println(e);
            return "更新库存错误";
        }
    }

    /**
     * 油库
     * 油库盘点时候显示
     * 油库库存查看显示
     */
    @ResponseBody
    @RequestMapping("/stockShow")
    public List<StockAll> stockShow(Long wareID) {
        return stockService.stockShow(wareID);
    }


    /**
     * 柱状图
     */
    @ResponseBody
    @RequestMapping("/stockColumn")
    public List<StockAll> stockLook(String zqName, String jtName, String ltName, Long oilTypeID)
    {
        return stockService.stockLook(zqName,jtName,ltName,oilTypeID);
    }

    /**
     * 饼状图
     */
    @ResponseBody
    @RequestMapping("/stockPie")
    public List<PieChart> stockPie(String zqName, String jtName, String ltName)
    {
        return  stockService.stockPie(zqName, jtName, ltName);
    }
}
