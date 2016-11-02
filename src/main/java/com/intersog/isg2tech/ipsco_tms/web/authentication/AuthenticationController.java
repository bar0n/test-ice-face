package com.intersog.isg2tech.ipsco_tms.web.authentication;

import com.intersog.isg2tech.ipsco_tms.web.utils.FacesUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Component;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.IOException;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: aolenyik
 * Date: 19.05.2010
 * Time: 14:19:43
 * <p/>
 * The class has a set of methods to determine which page is displayed.
 */
@Component
@Scope("session")
public class AuthenticationController {
    public static final String LOGIN_PAGE = "login";
    public static final String MAIN_PAGE = "main";


  /**
     * The method determines the name of the displayed page.
     *
     * @return
     * page name
     *
     * */
    public String getCurrentDisplayingPage() {
        if (isAuthorizedUser()) {
            return MAIN_PAGE;
        }
        return LOGIN_PAGE;
    }

    /**
     * The method determines if the user is logged in.
     *
     * @return
     * True - if the user is logged in, false - otherwise.
     *
     * */
    public boolean isAuthorizedUser() {
        SecurityContext context = (SecurityContext) FacesUtils.getRequest().getSession().getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
        if(context==null){
            context = SecurityContextHolder.getContext();
        }
        if(context!=null && context.getAuthentication()!=null
                && context.getAuthentication().isAuthenticated()
                && !context.getAuthentication().getPrincipal().equals("anonymousUser")){
            /*if (StringUtils.trimToNull(loggedUserInfo.getLogin())==null || !loggedUserInfo.getLogin().equals(context.getAuthentication().getCredentials())) {
                loggedUserInfo.setLogin(((org.springframework.security.userdetails.User)context.getAuthentication().getPrincipal()).getUsername());
                User user = userLogic.getUsrByLogin(loggedUserInfo.getLogin());
                loggedUserInfo.setUserId(user == null ? null : user.getId());
                loggedUserInfo.setUsrRole(context.getAuthentication().getAuthorities()[0].getAuthority());
            }*/
            return  true;
        }
        return false;
    }

    public String getPutUrlInSession(){
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        String url = FacesUtils.getRequest().getServletPath().substring(1);
        if(StringUtils.trimToNull(FacesUtils.getRequest().getQueryString())!=null){
            url = url + "?"+FacesUtils.getRequest().getQueryString();
        }
        sessionMap.put(LoginRedirectFilter.LAST_URL_REDIRECT_KEY, url);
        return null;
    }


    public String getRedirect() {
        try {
            String url = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(LoginRedirectFilter.LAST_URL_REDIRECT_KEY);
            if (StringUtils.trimToNull(url) == null) {
                url = "";
            }
            FacesUtils.sendRedirect(url);
        } catch (IOException e) {
            //logger.error("Can not do redirect", e);
        }
        return null;
    }

      public String getError() throws IOException {

        Exception ex = (Exception) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(WebAttributes.AUTHENTICATION_EXCEPTION);

        if (ex != null) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(WebAttributes.AUTHENTICATION_EXCEPTION);
            return "USERNAME_PASSWORD_INCORRECT";
        }
        return "";
    }

    @SuppressWarnings("unused")
    public void logout(ActionEvent e) throws IOException {
        SecurityContext context = getSecurityContext();
        UserDetails springUser = (UserDetails) context.getAuthentication().getPrincipal();

        FacesUtils.sendRedirect("j_spring_security_logout");
    }

    private SecurityContext getSecurityContext() {
        SecurityContext context = (SecurityContext) FacesUtils.getRequest().getSession().getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
        if (context == null) {
            context = SecurityContextHolder.getContext();
        }
        return context;
    }

}
