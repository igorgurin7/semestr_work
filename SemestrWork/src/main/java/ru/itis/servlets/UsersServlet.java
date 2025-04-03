package ru.itis.servlets;

import ru.itis.dto.UserDto;
import ru.itis.repositories.UsersRepository;
import ru.itis.repositories.impl.UsersRepositoryJdbcImpl;
import ru.itis.servises.UsersService;
import ru.itis.servises.UsersServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {

    private UsersService usersService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        DataSource dataSource = (DataSource) context.getAttribute("dataSource");
        UsersRepository usersRepository = new UsersRepositoryJdbcImpl(dataSource);
        this.usersService = new UsersServiceImpl(usersRepository);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("jsp/users.jsp").forward(request, response);
        List<UserDto> users = usersService.getAll();
        request.setAttribute("users", users);
    }

}
