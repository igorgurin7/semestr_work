package ru.itis.servlets;
import ru.itis.dto.SignUpForm;
import ru.itis.repositories.UsersRepository;
import ru.itis.repositories.impl.UsersRepositoryJdbcImpl;
import ru.itis.servises.SignUpService;
import ru.itis.servises.SignUpServiceImpl;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet{

    private SignUpService signUpService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        DataSource dataSource = (DataSource) context.getAttribute("dataSource");
        UsersRepository usersRepository = new UsersRepositoryJdbcImpl(dataSource);
        this.signUpService = new SignUpServiceImpl(usersRepository);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("jsp/signUp.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SignUpForm signUpForm = SignUpForm.builder()
                .firstName(request.getParameter("firstName"))
                .lastName(request.getParameter("lastName"))
                .email(request.getParameter("email"))
                .password(request.getParameter("password"))
                .build();

        signUpService.signUp(signUpForm);

        response.sendRedirect("/signIn");
    }
}
