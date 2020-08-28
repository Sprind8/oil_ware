package oil.oil_test.controller;

import com.github.pagehelper.PageHelper;
import oil.oil_test.POJO.Manage;
import oil.oil_test.POJO.data.ManageAll;
import oil.oil_test.POJO.data.PageEntity;
import oil.oil_test.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class ManageContronller {
    @Autowired
    private ManageService manageService;

    //State:   1、审核未通过、2、已通过（待划拨） 3、已划拨（不能操作） 4、已打回  5、已完成

    /**
     * 审核申请条目
     */
    @ResponseBody
    @RequestMapping("/ManageAdd")
    public String ManageAdd(Manage manageWeb)
    {
        try
        {
            manageService.ManageAdd(manageWeb);
        }
        catch (Exception e)
        {
            return "审核失败";
        }
        return "审核成功";
    }

    /**
     * 审核更新
     */
    @ResponseBody
    @RequestMapping("/ManageUpdate")
    public String ManageUpdate(Manage manageWeb)
    {
        try
        {
            if (manageService.ManageUpade(manageWeb) == 3)
            {
                return "已划拨，不能更改";
            }
        }
        catch (Exception e)
        {
            return "更新失败";
        }
        return "更新成功";
    }


    /**
     * 审核删除
     */
    @ResponseBody
    @RequestMapping("/ManageDelete")
    public String ManageDelete(Long manageID,Long appID)
    {
        try
        {
            manageService.ManageDelete(manageID,appID);
        }
        catch (Exception e)
        {
            return "删除失败，也许该条目已经被调拨";
        }
        return "删除成功";
    }


    /**
     * 申请者
     * 查看审核信息
     */
    @ResponseBody
    @RequestMapping("/ManageShow")
    public List<ManageAll> ManageShow(Long appID)
    {
        return manageService.ManageShow(appID);
    }


    /**
     * 审核记录查看
     * 需要分页
     */
    @ResponseBody
    @RequestMapping("/ManageShowAll")
    public List<ManageAll> ManageShowAll(int pageNum)
    {
        PageEntity pageEntity = new PageEntity();
        PageHelper.startPage(pageNum,pageEntity.getPageSize());
        return manageService.ManageShowAll();
    }


    /**
     * 调拨者看这个,所有未划拨的
     * 需要分页
     */
    @ResponseBody
    @RequestMapping("/ManageShowNeed")
    public List<ManageAll> ManageNeedDis(int pageNum)
    {
        PageEntity pageEntity = new PageEntity();
        PageHelper.startPage(pageNum,pageEntity.getPageSize());
        return manageService.ManageNeedDis();
    }
}
