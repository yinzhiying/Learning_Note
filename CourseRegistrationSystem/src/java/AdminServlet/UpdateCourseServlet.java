/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminServlet;

import DAOFactory.Course;
import DAOFactory.CourseDAO;
import DAOFactory.DAOFactory;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author josephstalin
 */
public class UpdateCourseServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateCourseServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateCourseServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);

        int cid = Integer.parseInt(request.getParameter("cid").trim());
        String cname = new String(request.getParameter("cname").trim().getBytes("ISO-8859-1"), "UTF-8");
        String cintroduction = new String(request.getParameter("cintroduction").trim().getBytes("ISO-8859-1"), "UTF-8");
        int credit = Integer.parseInt(request.getParameter("credit").trim());

        DAOFactory cloudFactory = DAOFactory.getDAOFactory();
        CourseDAO couDAO = cloudFactory.getCourseDAO();

        Course cou = couDAO.findCourse(cid);

        cou.setCname(cname);
        cou.setCintroduction(cintroduction);
        cou.setCredit(credit);

        boolean i = updateCourse(cou);
        if (i) {
            session.setAttribute("state", "成功修改一条课程基本信息.");
        } else {
            session.setAttribute("state", "修改课程基本信息失败.");
        }

        response.sendRedirect("admin/getCourse.jsp");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private static boolean updateCourse(Course cou) {
        DAOFactory cloudFactory = DAOFactory.getDAOFactory();

        CourseDAO couDAO = cloudFactory.getCourseDAO();

        boolean i = couDAO.updateCourse(cou);

        return i;
    }

}
