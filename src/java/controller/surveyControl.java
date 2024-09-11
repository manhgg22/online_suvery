/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.SurveyDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Survey;
import model.User;

/**
 *
 * @author Norttie
 */
@WebServlet(name = "surveyControl", urlPatterns = {"/surveymanage"})
public class surveyControl extends HttpServlet {

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
        String index = request.getParameter("index");
        String searchValue = request.getParameter("search");
        String surveyid = request.getParameter("surveyId");
        String ac = request.getParameter("ac");
        HttpSession session = request.getSession();
        User acc = (User) session.getAttribute("acc");
        String accid = acc.getUser_id() + "";
        if (searchValue == null || searchValue.trim().isEmpty()) {
            searchValue = "";
        } else {
            searchValue = searchValue.trim();
        }
        SurveyDAO dao = new SurveyDAO();
        List<Survey> list = new ArrayList<>();
        List<Integer> listNumQues = new ArrayList<>();
        List<Integer> listAnswer = new ArrayList<>();
        int numPage = 0;
        int idx = 1;

        if (index != null && !index.equals("")) {

            idx = Integer.parseInt(index);
        }
        if (ac != null && ac.equals("del")) {
            dao.deleteSurveyByid(surveyid);
        }

        list = dao.getSurveyByUserid(accid, idx, searchValue);
        numPage = dao.getNumberPageAcc(accid, searchValue);

        for (Survey survey : list) {
            listNumQues.add(dao.getNumOfQuestion(survey.getSurvey_id()));
            listAnswer.add(dao.getNumOfAnswer(survey.getSurvey_id()));
        }

        request.setAttribute("numPage", numPage);
        request.setAttribute("listS", list);
        request.setAttribute("search", searchValue);
        request.setAttribute("accid", accid);
        request.setAttribute("pageIndex", idx);
        request.setAttribute("listQ", listNumQues);
        request.setAttribute("listA", listAnswer);

        request.getRequestDispatcher("yoursurvey.jsp").forward(request, response);

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
        processRequest(request, response);
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
        processRequest(request, response);
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
