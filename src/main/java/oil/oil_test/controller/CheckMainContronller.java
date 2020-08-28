package oil.oil_test.controller;


import oil.oil_test.POJO.CheckMain;
import oil.oil_test.POJO.data.CheckMainAll;
import oil.oil_test.service.CheckMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CheckMainContronller {

    @Autowired
    private CheckMainService checkMainService;

    /**
     * 盘点主表记录添加
     * 盘点状态（1.未提交 2.已完成  3：审核未通过 4：审核通过 5：已提交）
     */
    @ResponseBody
    @RequestMapping("/checkMainAdd")
    public  String checkMainAdd(CheckMain checkMain)
    {
        try{
            checkMain.setCheckState(1);
            checkMainService.checkMainAdd(checkMain);
        }
        catch (Exception e)
        {
            return "添加失败";
        }
        return "添加成功";
    }

    /**
     *  详情添加完毕（更改状态）
     */
    @ResponseBody
    @RequestMapping("/checkDetailOver")
    public  String checkDetailOver(Long checkMainID)
    {
        try{
            checkMainService.checkDetailOver(checkMainID);
    }
        catch (Exception e)
        {
            return "失败";
        }
        return "成功";
    }

    /**
     * 盘点主表审核
     */
    @ResponseBody
    @RequestMapping("/checkMainCheck")
    public String checkMainCheck(CheckMain checkMain)
    {
        try{
            checkMainService.checkMainCheck(checkMain);
            return "审核完成";
        }
        catch (Exception e)
        {
            return "审核出错";
        }
    }



    /**
     * 盘点记录修改
     */
    @ResponseBody
    @RequestMapping("/checkMainUpadate")
    public  String checkMainUpadate(CheckMain checkMain)
    {
        try{
            checkMainService.checkMainUpadate(checkMain);
        }
        catch (Exception e)
        {
            return "更新失败";
        }
        return "更新成功";
    }

    /**
     * 盘点记录删除
     */
    @ResponseBody
    @RequestMapping("/checkMainDelete")
    public  String checkMainDelete(Long checkMainID,int checkState)
    {
        try{
            int a = checkMainService.checkMainDelete(checkMainID,checkState);
            if ( a == 1)
            {
                return "删除成功";
            }
            else
            {
                return "已经提交，无法删除";
            }
        }
        catch (Exception e)
        {
            return "删除失败";
        }
    }

    /**
     * 盘点记录查看
     */
    @ResponseBody
    @RequestMapping("/checkMainShow")
    public List<CheckMainAll> checkMainShow(Long wareID)
    {
        return checkMainService.checkMainShow(wareID);
    }
}
