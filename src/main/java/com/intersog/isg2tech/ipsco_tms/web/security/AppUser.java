package com.intersog.isg2tech.ipsco_tms.web.security;

import java.io.Serializable;
import java.util.Set;

/**
 * User: vlotar
 * MailTo: vlotar@intersog.com
 * Date: May 18, 2010
 * Time: 10:54:46 AM
 */
public class AppUser implements Serializable {

    private String firstName;

    private String lastName;

    private String login;

    private String password;

    private Set<String> roles;

    public AppUser() {
    }

    public AppUser(String login, String firstName, String lastName,
                   String password, Set<String> roles) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.roles = roles;
        assert !roles.isEmpty();
    }

    public AppUser(Object usr) {
        /*this.login = usr.getUsr();
        this.firstName = usr.getFullname();
        this.password = usr.getPasswd();
        Set<String> roles = new HashSet<String>();
        roles.add(usr.getRoleCode());
        this.roles = roles;
        assert !roles.isEmpty();*/
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Set<String> getRoles() {
        return roles;
    }

}
