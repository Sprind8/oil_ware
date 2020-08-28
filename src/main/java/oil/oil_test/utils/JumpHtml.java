package oil.oil_test.utils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JumpHtml {


    //首页跳转
    @RequestMapping("/preLogin")
    public String loginHtml()
    {
        return "login.html";
    }

    //用户注册页跳转
    @RequestMapping("/userAddHtml")
    public String registerHtml()
    {
        return "register.html";
    }


    //权限查看页面跳转
    @RequestMapping("/powerLookHtml")
    public String powerLookHtml(){return "powerInfo.html";}

    @RequestMapping("/testHtml")
    public String testHtml(){return "test.html";}

    @RequestMapping("/userInfoHtml")
    public  String userInfoHtml(){return  "userInfo.html";}

    @RequestMapping("/oilTypeHtml")
    public String oilTypeHtml(){return  "oilTypeInfo.html";}

    @RequestMapping("/oilNameHtml")
    public String oilNameHtml(){return  "oilNameInfo.html";}

    @RequestMapping("/groupInfoHtml")
    public String groupInfoHtml(){return  "groupInfo.html";}

    @RequestMapping("/warehouseInfoHtml")
    public String warehouseInfoHtml(){return  "warehouseInfo.html";}

    @RequestMapping("/storeOutHtml")
    public String storeOutHtml(){return  "storeOut.html";}

    @RequestMapping("/storeOutDoneHtml")
    public String storeOutDoneHtml(){return  "storeOutDone.html";}

    @RequestMapping("/outStoreHtml")
    public String outStoreHtml(){return  "outStore.html";}

    @RequestMapping("/outStoreDoneHtml")
    public String outStoreDoneHtml(){return  "outStoreDone.html";}

    @RequestMapping("/storeOutDetailHtml")
    public String storeOutDetailHtml(){return  "storeOutDetail.html";}

    @RequestMapping("/storeOutDoneDetailHtml")
    public String storeOutDoneDetailHtml(){return  "storeOutDoneDetail.html";}

    @RequestMapping("/outStoreDetailHtml")
    public String outStoreDetailHtml(){return  "outStoreDetail.html";}

    @RequestMapping("/outStoreDoneDetailHtml")
    public String outStoreDoneDetailHtml(){return  "outStoreDoneDetail.html";}

    @RequestMapping("/stockHtml")
    public String stockHtml(){return  "stock.html";}

    @RequestMapping("/stockMainHtml")
    public String stockMainHtml(){return  "stockMain.html";}

    @RequestMapping("/checkMainHtml")
    public String checkMainHtml(){return  "checkMain.html";}

    @RequestMapping("/checkDetailHtml")
    public String checkDetailHtml(){return  "checkDetail.html";}

    @RequestMapping("/applyHtml")
    public String applyHtml(){return  "apply.html";}

    @RequestMapping("/applyHistoryHtml")
    public String applyHistoryHtml(){return  "applyHistory.html";}

    @RequestMapping("/manageHtml")
    public String manageHtml(){return  "manage.html";}

    @RequestMapping("/manageHistoryHtml")
    public String manageHistoryHtml(){return  "manageHistory.html";}

    @RequestMapping("/distributeHtml")
    public String distributeHtml(){return  "distribute.html";}

    @RequestMapping("/indexHtml")
    public String indexHtml(){return  "index.html";}


}
