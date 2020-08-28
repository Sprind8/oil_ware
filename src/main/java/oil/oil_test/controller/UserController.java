package oil.oil_test.controller;

import oil.oil_test.POJO.Group;
import oil.oil_test.POJO.User;
import oil.oil_test.POJO.data.UserAll;
import oil.oil_test.service.UserService;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController
{
    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("/getSession")
    public UserAll sendSession(HttpSession session)
    {
        UserAll userAll = (UserAll) session.getAttribute("session_user");
        return userAll;
    }

    /**
     * 用户登录
     */
    @RequestMapping("/userLogin")
    @ResponseBody
    public String UserLogin(User user, HttpSession session)
    {
        /**
         * 编写shiro认证操作
         */
        //1.获取Subject,注意这里是shiro的 Subject，不是javax的。
        Subject subject = SecurityUtils.getSubject();
        //2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserAcount(),user.getUserPassword());
        //3.执行登录方法
        try{
            subject.login(token);
            UserAll userAll = (UserAll) subject.getPrincipal();
            session.setAttribute("session_user", userAll);
        }
        catch (UnknownAccountException e)
        {
            return "登录失败";
        }
        return "登录成功";
    }


    /**
     * 用户添加
     */
    @ResponseBody
    @RequestMapping("/userInfoAdd")
    public String addUser(User userWeb, HttpServletRequest request,Long zqID,Long jtID,Long ltID) //下拉选择组织的时候，就传入ID
    {
        try {
            String[] powerCodeWeb = request.getParameterValues("powerCode");
            Integer[] powerCode = new Integer[]{};

            if(zqID !=0)
            {
                if (jtID != 0)
                {
                    if (ltID != 0)
                    {
                        userWeb.setGroupID(ltID);
                    }
                    else
                    {
                        userWeb.setGroupID(jtID);
                    }
                }
                else
                {
                    userWeb.setGroupID(zqID);
                }
            }
            else
            {
                userWeb.setGroupID(null);
            }


            if (powerCodeWeb != null) {
                powerCode = new Integer[powerCodeWeb.length];
                for (int i = 0; i < powerCodeWeb.length; i++) {
                    powerCode[i] = Integer.parseInt(powerCodeWeb[i]);
                }
            }
            return userService.userAdd(userWeb, powerCode);
        }catch (Exception e)
        {
            return "添加错误，账户可能已存在";
        }
    }



    /**
     * 用户信息更新
     */
    @ResponseBody
    @RequestMapping("/userUpdate")
    public String userUpdate(User userWeb, HttpServletRequest request,Long zqID,Long jtID,Long ltID)
    {
        try {
        String[] powerCodeWeb =  request.getParameterValues("powerCode");
        Integer[] powerCode = new Integer[powerCodeWeb.length];
            if(zqID !=0)
            {
                if (jtID != 0)
                {
                    if (ltID != 0)
                    {
                        userWeb.setGroupID(ltID);
                    }
                    else
                    {
                        userWeb.setGroupID(jtID);
                    }
                }
                else
                {
                    userWeb.setGroupID(zqID);
                }
            }
            else
            {
                userWeb.setGroupID(null);
            }


            for (int i = 0; i < powerCodeWeb.length;i++)
        {
            powerCode[i] = Integer.parseInt(powerCodeWeb[i]);
        }

        String result = userService.userUpdate(userWeb,powerCode);
         if ( result.equals("权限中不存在该权限"))
        {
            return  "权限中不存在该权限";
        }
        else  if (result.equals("更新用户权限失败"))
        {
            return "更新用户权限失败";
        }
        else  if (result.equals("更新失败"))
        {
            return "更新失败";
        }
        return  "更新成功";
    }catch (Exception e)
    {
        return "更新失败，权限不能为空";
    }
    }

    /**
     * 用户信息删除
     */
    @ResponseBody
    @RequestMapping("/userDelete")
    public String userDelete(Long userID)
    {
        try {
            if (userService.userDelete(userID) != 0) {
                return "删除成功";
            }
            return "删除失败，其余记录存在该用户的信息";
        }catch (Exception e)
        {
            return "删除失败";
        }
    }

    /**
     * 用户信息显示功能,并且显示所在组织的人员信息
     */
    @ResponseBody
    @RequestMapping("/userShow")
    public List<UserAll> userShow(HttpSession session)
    {
        UserAll userAll = (UserAll) session.getAttribute("session_user");
        return userService.userShow(userAll.getGroupID());
    }

    /**
     * 油料处用户信息查询功能，查询框可根据组织机构搜索显示
     */
    @ResponseBody
    @RequestMapping("/userCheck")
    public List<UserAll> userCheck (String zqName,String jtName,String ltName)
    {
        List<UserAll> userAll = userService.userCheck(zqName,jtName,ltName);
        return userAll;
    }

}
