package oil.oil_test.controller;

import oil.oil_test.POJO.CheckDetail;
import oil.oil_test.POJO.data.CheckDetailAll;
import oil.oil_test.service.CheckDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CheckDetailContronller  {

    @Autowired
    private CheckDetailService checkDetailService;

    /**
     * 添加详情记录
     */
    @ResponseBody
    @RequestMapping("/checkDetailAdd")
    public String checkDetailAdd(CheckDetail checkDetail,String stockBarCode) //已经写入了库存ID和盘点主表ID（下拉即选择）
    {
        try {
            if (checkDetailService.checkDetailAdd(checkDetail, stockBarCode) != 0) {
                return "记录成功";
            }
            return "记录失败";
        }catch (Exception e)
        {
            return "记录出错";
        }
    }



    /**
     * 详情记录更新
     */
    @ResponseBody
    @RequestMapping("/checkDetailUpdate")
    public String checkDetailUpdate(CheckDetail checkDetail) //已经写入了库存ID和盘点主表ID（下拉即选择）
    {
        try {
            if (checkDetailService.checkDetailUpdate(checkDetail) != 0) {
                return "更新成功";
            }
            return "更新失败";
        }catch (Exception e)
        {
            return "更新出错";
        }
    }

    /**
     * 详情记录删除
     */
    @ResponseBody
    @RequestMapping("/checkDetailDelete")
    public String checkDetailDelete(Long checkDetailID,Long checkMainID)
    {
        try {
            if (checkDetailService.checkDetailDelete(checkDetailID,checkMainID) !=0 )
            {
                return "删除成功";
            }
            else {
                return "删除出错";
            }

        }catch (Exception e)
        {
            return "删除出错";
        }
    }


    /**
     * 盘点详情记录查看
     */
    @ResponseBody
    @RequestMapping("/checkDetailShow")
    public List<CheckDetailAll> checkDetailShow(Long checkMainID)
    {
     return checkDetailService.checkDetailShow(checkMainID);
    }


}
