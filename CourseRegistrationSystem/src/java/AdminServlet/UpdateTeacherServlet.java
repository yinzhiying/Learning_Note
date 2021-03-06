/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminServlet;

import DAOFactory.DAOFactory;
import DAOFactory.Teacher;
import DAOFactory.TeacherDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author josephstalin
 */
public class UpdateTeacherServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdateTeacherServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateTeacherServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession(true);

        int uuid = Integer.parseInt(request.getParameter("uuid").trim());
        String tname = new String(request.getParameter("tname").trim().getBytes("ISO-8859-1"), "UTF-8");
        int sex = Integer.parseInt(request.getParameter("sex").trim());
        int did = Integer.parseInt(request.getParameter("did").trim());
        int spid = Integer.parseInt(request.getParameter("spid").trim());
        int proid = Integer.parseInt(request.getParameter("proid").trim());
        String bir = request.getParameter("birthday").trim();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟 
        java.util.Date brithday = new Date();

        try {
            brithday = sdf.parse(bir);
        } catch (ParseException ex) {
            Logger.getLogger(AddStudentServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        DAOFactory cloudFactory = DAOFactory.getDAOFactory();
        TeacherDAO teaDAO = cloudFactory.getTeacherDAO();

        Teacher tea = teaDAO.findTeacher(uuid);

        tea.setTname(tname);
        tea.setSex(sex);
        tea.setDid(did);
        tea.setSpid(spid);
        tea.setBirthday(brithday);
        tea.setProid(proid);

        boolean i = updateTeacher(tea);
        if (i) {
            session.setAttribute("state", "成功修改一条教师基本信息.");
        } else {
            session.setAttribute("state", "修改教师基本信息失败.");
        }

        response.sendRedirect("admin/getTeacher.jsp");
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
        doGet(request, response);
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

    private static boolean updateTeacher(Teacher tea) {
        DAOFactory cloudFactory = DAOFactory.getDAOFactory();

        TeacherDAO teaDAO = cloudFactory.getTeacherDAO();

        boolean i = teaDAO.updateTeacher(tea);

        return i;
    }

}
