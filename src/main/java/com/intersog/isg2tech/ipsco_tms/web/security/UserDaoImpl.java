package com.intersog.isg2tech.ipsco_tms.web.security;

/**
 * User: vlotar
 * MailTo: vlotar@intersog.com
 * Date: May 18, 2010
 * Time: 10:51:57 AM
 */
public class UserDaoImpl implements UserDao {
    /*private UserQuery userQuery;
    public void setUserQuery(UserQuery userQuery) {
        this.userQuery = userQuery;
    }*/

    public AppUser findUser(String userName) {
        /*AppUser appUser = null;
        User usr = userQuery.getUsrByLogin(userName);
        if(usr!=null && !usr.isDeleted())
        {
            usr.setRoleCode(userQuery.getRoleByCode(usr.getRoleCode()).getRoleName());
            appUser = new AppUser(usr);
        }*/
        return null;
    }
}
