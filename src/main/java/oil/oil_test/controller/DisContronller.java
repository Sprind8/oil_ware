package oil.oil_test.controller;

import com.github.pagehelper.PageHelper;
import oil.oil_test.POJO.Dis;
import oil.oil_test.POJO.data.DisAll;
import oil.oil_test.POJO.data.PageEntity;
import oil.oil_test.service.DisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DisContronller {

    @Autowired
    private DisService disService;

    /**
     *确认调拨完成，更改申请状态
     */
    @ResponseBody
    @RequestMapping("/disFinish")
    public String disFinish(Long appID)
    {
        try{
            if (disService.disFinish(appID)!=0) {
                return "调拨成功";
            }
            return "调拨失败";
        }
        catch (Exception e)
        {
            return e.toString();
        }

    }


    /**
     * 进行调拨
     */
    @ResponseBody
    @RequestMapping("/disAdd")
    public String disAdd(Dis dis)
    {
        try{
            disService.disAdd(dis);
        }
        catch (Exception e)
        {
            return "error";
        }
        return "sucess";
    }

    @ResponseBody
    @RequestMapping("/disDelete")
    public String disDelete(Long disID,Long appID)
    {
        try{
           if (disService.disDelete(disID,appID)!= 0)
           {
            return "删除成功";
           }
           return "删除失败";
        }
        catch (Exception e)
        {
            return e.toString();
        }
    }

    /**
     * 申请人和审核人查看划拨信息
     */
    @ResponseBody
    @RequestMapping("/disShow")
    public List<DisAll> disShow(Long appID)
    {
        return  disService.disShow(appID);
    }


    /**
     * 划拨记录查看
     * 需要分页
     */
    @ResponseBody
    @RequestMapping("/disLookHis")
    public List<DisAll> disLookHis(int pageNum)
    {
        PageEntity pageEntity = new PageEntity();
        PageHelper.startPage(pageNum,pageEntity.getPageSize());
        return disService.disLookHis();
    }

}

