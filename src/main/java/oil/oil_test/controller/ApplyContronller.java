package oil.oil_test.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import oil.oil_test.POJO.Apply;
import oil.oil_test.POJO.data.ApplyAll;
import oil.oil_test.POJO.data.PageEntity;
import oil.oil_test.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class ApplyContronller {

    @Autowired
    private ApplyService applyService;

    //State:   1、已保存、2、已提交(待审核) 3、已审核（不能操作）
    @ResponseBody
    @RequestMapping("/applyAdd")
    /**
     * 申请信息填写
     */
    public String applyAdd(Apply applyWeb)
    {
        try {
            if (applyService.applyAdd(applyWeb) !=0)
            {
                return "申请成功";
            }
            return "申请失败";
        }
        catch (Exception e)
        {
            return e.toString();
        }
    }
    @ResponseBody
    @RequestMapping("/applyChange")
    /**
     * 审核未通过的状态改写
     */
    public String applyChange(Long manageID)
    {
        try {
            if (applyService.applyChange(manageID) !=0)
            {
                return "更改成功";
            }
            return "更改失败";
        }
        catch (Exception e)
        {
            return e.toString();
        }
    }
    @ResponseBody
    @RequestMapping("/applySubmit")
    /**
     * 申请提交
     */
    public String applySubmit(Long appID)
    {
        try {
            if (applyService.applySubmit(appID) != 0) {
                return "提交成功";
            }
            return "提交失败";
        }
        catch (Exception e)
        {
            return e.toString();
        }

    }


    /**
     * 申请修改
     */
    @ResponseBody
    @RequestMapping("/applyUpdate")
    public String applyUpdate(Apply applyWeb)
    {
        try{
            if (applyService.applyUpdate(applyWeb) != 0)
            {
                return "修改成功";
            }
            return "修改失败";
        }
        catch (Exception e)
        {
            return e.toString();
        }
    }


    /**
     * 申请删除
     */
    @ResponseBody
    @RequestMapping("/applyDelete")
    public String applyDelete(Long appID,int appState)
    {
        try{
            if (applyService.applyDelete(appID,appState) != 0)
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
     * 申请记录查看（未处理的或者未通过审核的）
     * 只有旅部才能看
     */
    @ResponseBody
    @RequestMapping("/applyShow")
    public List<ApplyAll> applyShow(Long wareID)
    {
        return applyService.applyShow(wareID);
    }


    /**
     * 申请记录查看（所有记录）
     * 只有旅部才能看
     * 需要分页
     */
    @ResponseBody
    @RequestMapping("/applyShowAll")
    public List<ApplyAll> applyShowAll(Long wareID,int pageNum)
    {
        PageEntity pageEntity = new PageEntity();
        PageHelper.startPage(pageNum,pageEntity.getPageSize());
        return applyService.applyShowAll(wareID);
//        List<ApplyAll> applyall = applyService.applyShowAll(wareID);
//        PageInfo<Apply>  pageInfo=new PageInfo(applyall);
//        return pageInfo;
    }



    /**
     * 油料处审核时的查看这个（看所有未审核的）
     * 需要分页
     */
    @ResponseBody
    @RequestMapping("/applyNeedManage")
    public List<ApplyAll> applyNeedManage(int pageNum)
    {
        PageEntity pageEntity = new PageEntity();
        PageHelper.startPage(pageNum,pageEntity.getPageSize());
        return applyService.applyNeedManage();
    }


    /**
     * 审核记录查询的时候
     * 划拨的时候看这个
     */
    @ResponseBody
    @RequestMapping("/applyNeedDis")
    public List<ApplyAll> applyNeedDis(Long appID)
    {
        return applyService.applyNeedDis(appID);
    }
}