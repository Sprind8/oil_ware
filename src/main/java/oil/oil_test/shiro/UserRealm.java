package oil.oil_test.shiro;

import oil.oil_test.POJO.User;
import oil.oil_test.POJO.data.UserAll;
import oil.oil_test.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


public class UserRealm  extends AuthorizingRealm {

    @Autowired
    private UserService userService;


    /**
     * 执行授权逻辑
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();//注意不一样
        //添加授权字符串
        Subject subject = SecurityUtils.getSubject();
        UserAll userAll = (UserAll) subject.getPrincipal();
        List<String> powers = new ArrayList<>(userAll.getUserPowerCode().length);
        for(Integer powerInt:userAll.getUserPowerCode())
        {
            String s = String.valueOf(powerInt);
            powers.add(s);
        }
        info.addStringPermissions(powers);
        return info;
    }


    /**
     * 执行认证逻辑
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //1.取出token的值,放入user对象
        User user = new User();

        String userAcount = token.getPrincipal().toString();
        String userPassword = new String((char[])token.getCredentials());
        user.setUserAcount(userAcount);
        user.setUserPassword(userPassword);

        UserAll userAll = userService.userLogin(user);
        if (userAll != null)
        {
            return new SimpleAuthenticationInfo(userAll,userPassword,"UserRealm");
        }
        return null;
    }
}
