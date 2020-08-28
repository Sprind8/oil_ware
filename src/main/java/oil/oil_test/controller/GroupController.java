package oil.oil_test.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import oil.oil_test.POJO.Group;
import oil.oil_test.POJO.data.PageEntity;
import oil.oil_test.POJO.data.UserAll;
import oil.oil_test.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class GroupController {
    @Autowired
    private GroupService groupService;

    @ResponseBody
    @RequestMapping("/groupAdd")
    public String groupAdd(Group group)
    {
       try {
           if (groupService.groupAdd(group) == 3) {
               return "添加成功";
           }
           return "添加失败,或许不存在此上级组织";
       }catch (Exception e)
       {
           return e.toString();
       }
    }

    @ResponseBody
    @RequestMapping("/groupDelete")
    public String groupDelete(Long groupID)
    {
        try {
            if(groupService.groupDelete(groupID)==2)
            {
                return "删除失败,该组织已经被使用";
            }
            return "删除成功";
        }
        catch (Exception e)
        {
            return "删除出错";
        }

    }

    @ResponseBody
    @RequestMapping("/groupUpdate")
    public String groupUpdate(Group group)
    {
       try {
           if (groupService.groupUpdate(group) != 0) {
               return "更新成功";
           }
           return "更新失败,或许不存在此上级组织";
       }catch (Exception e)
       {
           return  e.toString();
       }
    }

    /**
     *  用户所属组织信息显示
     */
    @ResponseBody
    @RequestMapping("/groupLook")
    public Group groupLook(HttpSession session)
    {
        UserAll userAll = (UserAll) session.getAttribute("session_user");
        return groupService.groupLook(userAll.getGroupID());
    }

    /**
     *  全体组织信息查询功能
     */
    @ResponseBody
    @RequestMapping("/groupCheck")
    public List<Group> groupCheck(String zqName, String jtName, String ltName,Integer level
    )
    {
        return groupService.groupCheck(zqName,jtName,ltName,level);
    }

    /**
     * 组织信息查询功能（下拉框）
     * ZQ可以直接写死在前端里
     */
    @ResponseBody
    @RequestMapping("/findJTJ")
    public List<Group> findJTJ(String zqName)
    {
        return groupService.findJTJ(zqName);
    }

    @ResponseBody
    @RequestMapping("/findLT")
    public List<Group> findLT(String jtName)
    {
        return groupService.findLT(jtName);
    }



}
