package oil.oil_test.controller;



import oil.oil_test.POJO.House;
import oil.oil_test.POJO.data.UserAll;
import oil.oil_test.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class HouseController
{
    @Autowired
    private HouseService houseService;

    @ResponseBody
    @RequestMapping("/houseAdd")
    public String houseAdd(House house,Long zqID,Long jtID,Long ltID) //下拉框绑定了groupID
    {
        if(zqID !=0 && zqID != null)
        {
            if (jtID != 0 && jtID != null)
            {
                if (ltID != 0 && ltID != null)
                {
                    house.setGroupID(ltID);
                }
                else
                {
                    house.setGroupID(jtID);
                }
            }
            else
            {
                house.setGroupID(zqID);
            }
        }
        else
        {
            house.setGroupID(null);
        }

        if (houseService.houseAdd(house) != 0)
        {
            return "添加成功";
        }
        return "添加失败,或许不存在所属组织";
    }


    @ResponseBody
    @RequestMapping("/houseUpdate")
    public String houseUpdate(House house,Long zqID,Long jtID,Long ltID)
    {
        if(zqID !=0 && zqID != null)
        {
            if (jtID != 0 && jtID != null)
            {
                if (ltID != 0 && ltID != null)
                {
                    house.setGroupID(ltID);
                }
                else
                {
                    house.setGroupID(jtID);
                }
            }
            else
            {
                house.setGroupID(zqID);
            }
        }
        else
        {
            house.setGroupID(null);
        }

        if (houseService.houseUpdate(house) != 0)
        {
            return "更新成功";
        }
        return "更新失败,或许不存在所属组织";
    }

    @ResponseBody
    @RequestMapping("/houseDelete")
    public String houseDelete(House house)
    {

        try {
            houseService.houseDelete(house);
        }
        catch (Exception e)
        {
            return "删除失败,该油库信息已经被使用";
        }
        return "删除成功";

    }

    /**
     * 油库信息显示功能,显示所在组织管理的油库信息
     */
    @ResponseBody
    @RequestMapping("/houseShow")
    public List<House> houseShow(HttpSession session)
    {
        UserAll userAll = (UserAll) session.getAttribute("session_user");
        List<House> houses = houseService.houseShow(userAll.getGroupID());
        return houses;
    }


    /**
     * 油库信息查询功能，查询框可根据组织机构搜索显示
     */
    @ResponseBody
    @RequestMapping("/houseCheck")
    public List<House> houseCheck (String zqName, String jtName, String ltName)
    {
        return  houseService.houseCheck(zqName,jtName,ltName);

    }
}
