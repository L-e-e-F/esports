package com.example.config.shiro;

import com.example.entity.User;
import com.example.service.RoleService;
import com.example.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    @Lazy
    private UserService userService;

    //@Autowired
    //@Lazy
    //private MenuService menuService;

    @Autowired
    @Lazy
    private RoleService roleService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user = (User) principals.getPrimaryPrincipal();
        // 普通用户，查询用户的角色
//        Long userId = user.getUserId();
        String role = roleService.selectByPrimaryKey(user.getRole());
        authorizationInfo.addRole(role);
        //if(null != role)
            //List<Menu> menus = menuService.listMenuByRoleId(user.getRole());
            //if(null != menus && menus.size() > 0){
            //    for(Menu menu : menus){
            //        authorizationInfo.addStringPermission(menu.getName());
            //    }
            //}
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
//        System.out.println(userService.getUserByName((String) token.getPrincipal()));
//        System.out.println(userService.getUserByName("LEEF"));
        User user = userService.getUserByName((String) token.getPrincipal());
        if (user == null){
            return null;//UnknownAccountException
        }
        ByteSource credentialsSalt = ByteSource.Util.bytes(user.getName());//使用账号作为盐值
//            return new SimpleAuthenticationInfo(user,user.getPassword(),user.getNickName());
            return new SimpleAuthenticationInfo(user,user.getPassword(),credentialsSalt, user.getNickName());

    }
}
