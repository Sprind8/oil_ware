package oil.oil_test.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import oil.oil_test.POJO.House;
import oil.oil_test.POJO.StoreOut;
import oil.oil_test.POJO.data.StoreOutAll;
import oil.oil_test.POJO.data.UserAll;
import oil.oil_test.service.HouseService;
import oil.oil_test.service.StoreOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.List;


@Controller
public class StoreOutController {

    @Autowired
    private StoreOutService storeOutService;

    /**
     * 填写出入库主单
     */
    @ResponseBody
    @RequestMapping("/receiptAdd")
    public String receiptAdd(StoreOut storeOutWeb, HttpSession httpSession)
    {
        UserAll userAll = (UserAll) httpSession.getAttribute("session_user");
        if (storeOutService.receiptAdd(storeOutWeb,userAll)!= 0)
        {
            return "填写成功";
        }
        return "填写失败";
    }

    /**
     * 删除出入库单
     */
    @ResponseBody
    @RequestMapping("/receiptDelete")
    public String receiptDelete(Long receiptID,int storeOutState)
    {
            try {
                if (storeOutService.receiptDelete(receiptID,storeOutState) == 1)
                {
                    return "删除成功";
                }
                else
                {
                    return "删除失败,已经提交无法删除";
                }
            }
            catch (Exception e)
            {
                return "删除失败";
            }

    }

    /**
     * 更新出入库主单（不允许更新出入库类型和单号）
     */
    @ResponseBody
    @RequestMapping("/receiptUpdate")
    public String receiptUpdate(StoreOut storeOutWeb)
    {
        if (storeOutService.receiptUpdate(storeOutWeb)!= 0)
        {
            return "更新成功";
        }
        return "更新失败";
    }

    /**
     * 出入库主单信息查看
     */
    @ResponseBody
    @RequestMapping("/receiptShow")
    public PageInfo<StoreOutAll> receiptShow(Long wareID, boolean storeOutType, int state,int pageNum)
    {    //pagehelper  mapper接口方式的调用；
        PageHelper.startPage(pageNum,5);// 当pagesize=0时，配置文件有page-size-zero，全部查出
        List<StoreOutAll> storeOuts = storeOutService.receiptShow(wareID,storeOutType,state);
       PageInfo<StoreOutAll> pageInfo = new PageInfo<>(storeOuts);
      //  PageInfo<StoreOutAll> pageInfo = new PageInfo(storeOuts);
        return pageInfo;
    }




    @ResponseBody
    @RequestMapping({"/receiptShowTest1","/receiptShowTest2"})
    public List<StoreOutAll> receiptShowTest(Long wareID,int state)
    {
        List<StoreOutAll> storeOuts = storeOutService.receiptShowTestIn(wareID,state);

        return storeOuts;
    }
}
