package org.example.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet("/uploaded_images/*")
public class ImageServlet extends HttpServlet {

    private static final String IMAGES_DIRECTORY = "C:\\uploaded_images";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid image path.");
            return;
        }

        String imageName = pathInfo.substring(1);

        File imageFile = new File(IMAGES_DIRECTORY, imageName);

        if (imageFile.exists() && imageFile.isFile()) {
            String contentType = getServletContext().getMimeType(imageFile.getName());
            response.setContentType(contentType != null ? contentType : "image/jpeg");

            try (InputStream in = new FileInputStream(imageFile);
                 OutputStream out = response.getOutputStream()) {

                byte[] buffer = new byte[4096];
                int length;
                while ((length = in.read(buffer)) != -1) {
                    out.write(buffer, 0, length);
                }
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Image not found.");
        }
    }
}
