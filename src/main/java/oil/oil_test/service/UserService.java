package oil.oil_test.service;

import oil.oil_test.POJO.*;
import oil.oil_test.POJO.data.UserAll;
import oil.oil_test.POJO.data.UserPowerAll;
import oil.oil_test.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private PowerMapper powerMapper;

    @Autowired
    private UserPowerMapper userPowerMapper;

    @Autowired
    private HouseMapper houseMapper;
    /**
     * 用户登录
     */
    public UserAll userLogin(User user)
    {

        UserAll userAll = userMapper.userLogin(user);
        //取出结果集
        if(userAll != null)
        {
            List<UserPowerAll> userPowerList = userPowerMapper.selectPowerCode(userAll.getUserID());
            List<Power> powerList = userPowerList.stream().map(UserPowerAll::getPower).collect(Collectors.toList());
            List<Integer> powerListCode = powerList.stream().map(Power::getPowerCode).collect(Collectors.toList());

            Integer[] powerArrCode = powerListCode.toArray(new Integer[0]);
            userAll.setUserPowerCode(powerArrCode);
            return userAll;
        }
        return userAll;
    }


    /**
     * 用户添加
     */
    @Transactional
    public String userAdd(User userWeb, Integer[] powerCodeWeb)
    {

            if (userMapper.insertSelective(userWeb) != 0) //向数据库插入user信息
            {
                Integer[] a = powerCodeWeb;
                for (int i = 0; i < a.length; i++) {
                    if (powerMapper.selectByCode(a[i]) == null) //根据接收的powerCodeWeb，查询是否对应的信息
                    {
                        return "权限中不存在该权限";
                    }
                    Long powerID = powerMapper.selectByCode(a[i]).getPowerID(); //得到Code的对应ID

                    UserPower userPower = new UserPower();
                    userPower.setUserID(userWeb.getUserID());
                    userPower.setPowerID(powerID);
                    if (userPowerMapper.insertSelective(userPower) == 0) //插入用户权限表
                    {
                        return "添加用户权限失败";
                    }
                }
                return "添加用户成功";
            }
        return "添加用户信息失败";
    }


    /**
     * 用户信息删除
     */
    public int userDelete(Long userID)
    {
        return  userMapper.deleteByPrimaryKey(userID);
    }

    /**
     * 用户信息更新
     */
    @Transactional
    public String userUpdate(User userWeb, Integer[] powerCodeWeb)
    {
        if (userMapper.updateByPrimaryKeySelective(userWeb) != 0)
        {
            userPowerMapper.deleteByUserID(userWeb.getUserID()); //删除该用户之前的权限
            for (int i = 0; i <powerCodeWeb.length; i++)
            {
                if (powerMapper.selectByCode(powerCodeWeb[i]) == null) //根据接收的powerCodeWeb，查询是否对应的信息
                {
                    return "权限中不存在该权限";
                }
                Long powerID = powerMapper.selectByCode(powerCodeWeb[i]).getPowerID(); //得到Code的对应ID
                UserPower userPower = new UserPower();
                userPower.setUserID(userWeb.getUserID());
                userPower.setPowerID(powerID);
                if (userPowerMapper.insertSelective(userPower) == 0) //插入用户权限表
                {
                    return "更新用户权限失败";
                }
            }
            return "更新成功";
        }
        return "更新失败";
    }

    /**
     * 用户信息显示功能,显示所在组织的人员信息
     */
    public List<UserAll> userShow(Long groupID)
    {
        List<UserAll> userAllList = userMapper.selectByGroupID(groupID);
        addUserAllInfo(userAllList);
        return userAllList;
    }

    /**
     * 用户信息查询功能,查询框可根据组织机构搜索显示
     */
    public List<UserAll> userCheck(String zqName,String jtName,String ltName)
    {
        List<UserAll> userAllList = userMapper.selectByGroupName(zqName,jtName,ltName);
        addUserAllInfo(userAllList);
        return userAllList;
    }



    /**
     * 赋值权限代号和权限名称给userAll的函数
     */
    public List<UserAll> addUserAllInfo(List<UserAll> userAllList)
    {
        //附上权限代号、权限名称
        for (int i = 0; i < userAllList.size(); i++)
        {
            UserAll userAll = userAllList.get(i);
            List<UserPowerAll> userPowerList = userPowerMapper.selectPowerCode(userAll.getUserID());
            List<Power> powerList = userPowerList.stream().map(UserPowerAll::getPower).collect(Collectors.toList());
            List<String> powerListName = powerList.stream().map(Power::getPowerNote).collect(Collectors.toList());
            List<Integer> powerListCode = powerList.stream().map(Power::getPowerCode).collect(Collectors.toList());

            String[] powerArrName = powerListName.toArray(new String[0]);
            Integer[] powerArrCode = powerListCode.toArray(new Integer[0]);

            userAll.setUserPowerName(powerArrName);
            userAll.setUserPowerCode(powerArrCode);
        }
        return userAllList;
    }
}

