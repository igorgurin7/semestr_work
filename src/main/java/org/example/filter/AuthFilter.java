package org.example.filter;

import org.example.DAO.DAOImpl.UserDAOImpl;
import org.example.DTO.UserDTO;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {

    private UserDAOImpl userDAO;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        userDAO = new UserDAOImpl();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpRequest.getSession(false);

        String requestURI = httpRequest.getRequestURI();

        if (session == null || session.getAttribute("user") == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
            return;
        }

        UserDTO user = (UserDTO) session.getAttribute("user");

        if (user != null) {
            int roleId = user.getRoleId();

            if (roleId == 1) {
                chain.doFilter(servletRequest, servletResponse);
            } else {

                if (requestURI.contains("/admin/users")) {
                    httpResponse.sendRedirect(httpRequest.getContextPath() + "/access-denied");
                    return;
                }

                if (requestURI.contains("/cars") && (requestURI.contains("/add") || requestURI.contains("/edit") || requestURI.contains("/delete"))) {
                    httpResponse.sendRedirect(httpRequest.getContextPath() + "/access-denied");
                    return;
                }

                chain.doFilter(servletRequest, servletResponse);
            }
        } else {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/access-denied");
        }
    }

    @Override
    public void destroy() {
    }
}