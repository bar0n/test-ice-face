package com.intersog.isg2tech.ipsco_tms.web.security;

/**
 * User: vlotar
 * MailTo: vlotar@intersog.com
 * Date: May 18, 2010
 * Time: 10:54:07 AM
 */
public interface UserDao {
    AppUser findUser(String username);
}
