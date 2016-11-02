package com.intersog.isg2tech.ipsco_tms.web.utils;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: aolenyik
 * Date: 20.05.2010
 * Time: 14:06:21
 * To change this template use File | Settings | File Templates.
 */
public class FacesUtils
{
    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public static void sendRedirect(String redirectUrl) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect(redirectUrl);
    }
}
