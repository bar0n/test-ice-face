package com.intersog.isg2tech.ipsco_tms.web.security;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * User: vlotar emailTo: vlotar@intersog.com
 * Date: Dec 1, 2009
 * Time: 2:34:53 PM
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserDao userDao;

    public UserDetailsServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException, DataAccessException {
        AppUser user = userDao.findUser(username);
        if (user == null)
            throw new UsernameNotFoundException("User not found: " + username);
        else {
            return makeUser(user);
        }
    }

    private org.springframework.security.core.userdetails.User makeUser(AppUser user) {
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), true, true, true, true,
                makeGrantedAuthorities(user));
    }

    private List<GrantedAuthority> makeGrantedAuthorities(AppUser user) {
        List<GrantedAuthority> result = new ArrayList<GrantedAuthority>();
        for (String role : user.getRoles()) {
            result.add(new SimpleGrantedAuthority(role));
        }
        return result;
    }

}
