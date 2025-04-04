package org.example.listener;

import org.example.DAO.DAOImpl.UserDAOImpl;
import org.example.DAO.DAOImpl.CarDAOImpl;
import org.example.DAO.DAOImpl.OrderDAOImpl;
import org.example.service.AuthenticationService;
import org.example.service.CarService;
import org.example.service.OrderService;
import org.example.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ServletContext context = sce.getServletContext();

        UserDAOImpl userDAO = new UserDAOImpl();
        CarDAOImpl carDAO = new CarDAOImpl();
        OrderDAOImpl orderDAO = new OrderDAOImpl();

        AuthenticationService authenticationService = new AuthenticationService(userDAO);
        CarService carService = new CarService(carDAO);
        OrderService orderService = new OrderService(orderDAO);
        UserService userService = new UserService(userDAO);

        context.setAttribute("authenticationService", authenticationService);
        context.setAttribute("carService", carService);
        context.setAttribute("orderService", orderService);
        context.setAttribute("userService", userService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}

