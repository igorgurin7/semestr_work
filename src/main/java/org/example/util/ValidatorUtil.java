package org.example.util;

import org.example.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class ValidatorUtil {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
    private static final String USERNAME_REGEX = "^[\\S]+$";


    public static boolean isValidEmail(String email) {
        return email != null && Pattern.matches(EMAIL_REGEX, email);
    }

    public static boolean isValidUsername(String username) {
        return username != null && Pattern.matches(USERNAME_REGEX, username);
    }


    public static boolean isInputValid(HttpServletRequest request, HttpServletResponse response, UserService userService) throws ServletException, IOException, SQLException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");

        if (!ValidatorUtil.isValidUsername(username)) {
            request.setAttribute("errorMessage", "Имя пользователя не должно содержать пробелы.");
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
            return false;
        }

        if (!ValidatorUtil.isValidEmail(email)) {
            request.setAttribute("errorMessage", "Введите корректный адрес электронной почты.");
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
            return false;
        }

        if (userService.getUserByUsername(username) != null) {
            request.setAttribute("errorMessage", "Имя пользователя уже существует.");
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
            return false;
        }

        if (userService.getUserByEmail(email) != null) {
            request.setAttribute("errorMessage", "Email уже существует.");
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
            return false;
        }

        return true;
    }
}
