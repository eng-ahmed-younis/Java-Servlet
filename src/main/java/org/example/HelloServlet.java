package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;


@WebServlet(name = "HelloServlet", urlPatterns = { "", "/", "/hello", "/hello-servlet" })
public class HelloServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("HelloServlet initialized.");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // 1. Create a session object if it does not already exist
        HttpSession session = request.getSession(true);

        // 2. Get session creation time and last accessed time
        Date createTime = new Date(session.getCreationTime());
        Date lastAccessTime = new Date(session.getLastAccessedTime());

        String userIDKey = "userID";
        String userID = "ABCD";
        String visitCountKey = "visitCount";
        Integer visitCount = 0;

        // 3. Track number of visits and user ID in session
        if (session.isNew()) {
            session.setAttribute(userIDKey, userID);
        } else {
            visitCount = (Integer) session.getAttribute(visitCountKey);
            if (visitCount == null) {
                visitCount = 0;
            }
            visitCount = visitCount + 1;
            userID = (String) session.getAttribute(userIDKey);
            if (userID == null) {
                userID = "ABCD";
            }
        }
        session.setAttribute(visitCountKey, visitCount);

        // 4. Render HTML response matching the Session Information table
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("    <meta charset=\"UTF-8\">");
            out.println("    <title>Session Information</title>");
            out.println("</head>");
            out.println("<body bgcolor=\"#f0f0f0\">");
            out.println("    <h2 align=\"center\">Session Infomation</h2>");
            out.println("    <table border=\"1\" align=\"center\">");
            out.println("        <tr bgcolor=\"#949494\">");
            out.println("            <th>Session info</th>");
            out.println("            <th>value</th>");
            out.println("        </tr>");
            out.println("        <tr>");
            out.println("            <td>id</td>");
            out.println("            <td>" + session.getId() + "</td>");
            out.println("        </tr>");
            out.println("        <tr>");
            out.println("            <td>Creation Time</td>");
            out.println("            <td>" + createTime + "</td>");
            out.println("        </tr>");
            out.println("        <tr>");
            out.println("            <td>Time of Last Access</td>");
            out.println("            <td>" + lastAccessTime + "</td>");
            out.println("        </tr>");
            out.println("        <tr>");
            out.println("            <td>User ID</td>");
            out.println("            <td>" + userID + "</td>");
            out.println("        </tr>");
            out.println("        <tr>");
            out.println("            <td>Number of visits</td>");
            out.println("            <td>" + visitCount + "</td>");
            out.println("        </tr>");
            out.println("    </table>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("HelloServlet destroyed.");
        super.destroy();
    }
}
