package com.intersog.isg2tech.ipsco_tms.web.authentication;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * User: vlotar
 * MailTo: vlotar@intersog.com
 * Date: May 21, 2010
 * Time: 11:18:58 AM
 */
@Component
public class LoginRedirectFilter implements Filter
{
    public static final String LAST_URL_REDIRECT_KEY = LoginRedirectFilter.class.getName() + "LAST_URL_REDIRECT_KEY";


    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
            throws IOException, ServletException
    {
        HttpSession session = ((HttpServletRequest) request).getSession();
        String redirectUrl = (String) session.getAttribute(LAST_URL_REDIRECT_KEY);
        if (isAuthenticated() && (redirectUrl != null) && !redirectUrl.isEmpty())
        {
            session.removeAttribute(LAST_URL_REDIRECT_KEY);
            HttpServletResponse resp = (HttpServletResponse) response;
            resp.sendRedirect(redirectUrl);
        }
        else
        {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }

    private boolean isAuthenticated()
    {
        boolean result = false;
        SecurityContext context = SecurityContextHolder.getContext();
        if (context instanceof SecurityContext)
        {
            Authentication authentication = context.getAuthentication();
            if (authentication instanceof AnonymousAuthenticationToken)
            {
                // not authenticated
            }
            else if (authentication instanceof Authentication)
            {
                result = true;
            }
        }
        return result;
    }

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException
    {}
}
