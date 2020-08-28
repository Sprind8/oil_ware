package oil.oil_test.controller;

import oil.oil_test.POJO.StoreOutDetail;
import oil.oil_test.POJO.data.StoreDetailAll;
import oil.oil_test.service.StoreDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Date;
import java.util.List;

@Controller
public class StoreDetailContronller {

    @Autowired
    private StoreDetailService storeDetailService;

    @ResponseBody
    @RequestMapping("/storeDetailAdd")
    public String storeDetailAdd(StoreOutDetail storeOutDetail) //已经写入了油库ID和主表ID、油品ID和默认保质期（下拉即选择）
    {
         Date date = storeOutDetail.getStoreDetailProDate();
         java.sql.Date transDate = new java.sql.Date(date.getTime());
         storeOutDetail.setStoreDetailProDate(transDate);
         if (storeDetailService.storeDetailAdd(storeOutDetail) != 0)
         {
             return "记录成功";
         }
         return "记录失败";
    }

    @ResponseBody
    @RequestMapping("/storeDetailUpdate")
    public String storeDetailUpdate(StoreOutDetail storeOutDetail)
    {
        if (storeDetailService.storeDetailUpdate(storeOutDetail) != 0)
        {
            return "更新成功";
        }
        return "更新失败";
    }

    @ResponseBody
    @RequestMapping("/storeDetailDelete")
    public String storeDetailDelete(Long storedetailID,Long storeOutID)
    {
        if (storeDetailService.storeDetailDelete(storedetailID,storeOutID) != 0)
        {
            return "删除成功";
        }
        return "删除失败";
    }


    /**
     * 显示某次出入库的操作记录
     */
    @ResponseBody
    @RequestMapping("/storeDetailShow")
    public List<StoreDetailAll> storeDetailShow(Long storeOutID)
    {
        return storeDetailService.storeDetailShow(storeOutID);
    }
}