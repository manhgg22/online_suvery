/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AccountDao;
import dao.SurveyDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.User;

/**
 *
 * @author Norttie
 */
@WebServlet(name = "admin", urlPatterns = {"/admin"})
public class admin extends HttpServlet {

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
        AccountDao dao = new AccountDao();
        List<User> listU = dao.getAllUser();
        request.setAttribute("listU", listU);
        request.getRequestDispatcher("admin.jsp").forward(request, response);
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
        response.setContentType("text/html;charset=UTF-8");
        String ac = request.getParameter("ac");
        String accid = request.getParameter("accid");
        AccountDao dao = new AccountDao();
        User acc = dao.getUserById(accid);
        if (ac != null && accid != null) {
            if (ac.equals("del")) {
                dao.deleteAcc(accid);
            }
            if (ac.equals("upd")) {
                request.setAttribute("userid", acc.getUser_id());
                request.setAttribute("username", acc.getUsername());
                request.setAttribute("password", acc.getPassword());
                request.setAttribute("displayname", acc.getDisplayname());
                request.setAttribute("address", acc.getAddress());
                request.setAttribute("desc", acc.getDescription());
            }
        }

        List<User> listU = dao.getAllUser();
        request.setAttribute("listU", listU);
        request.getRequestDispatcher("admin.jsp").forward(request, response);
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
        String userid = request.getParameter("userid");
        String username = request.getParameter("username");
        String displayname = request.getParameter("displayname");
        String password = request.getParameter("password");
        String desc = request.getParameter("desc");
        String address = request.getParameter("address");

        AccountDao dao = new AccountDao();
        User acc = dao.getUserById(userid);
        if (acc == null) {
            request.setAttribute("userid", userid);
            request.setAttribute("username", username);
            request.setAttribute("displayname", displayname);
            request.setAttribute("password", password);
            request.setAttribute("address", address);
            request.setAttribute("desc", desc);
            request.setAttribute("error", "account not exist");
        } else {

            dao.updateUserByAdmin(userid, username, password, displayname, desc, address);
        }

//       response.sendRedirect("admin");
        List<User> listU = dao.getAllUser();
        request.setAttribute("listU", listU);

        request.getRequestDispatcher("admin.jsp").forward(request, response);

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

}
