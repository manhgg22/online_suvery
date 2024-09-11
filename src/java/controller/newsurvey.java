/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.DBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import model.User;

/**
 *
 * @author Norttie
 */
@WebServlet(name="newsurvey", urlPatterns={"/newsurvey"})
public class newsurvey extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("newsurvey.jsp").forward(request, response);
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        PrintWriter out = response.getWriter();
          String surveyTitle = request.getParameter("surveyTitle");
        String[] questions = request.getParameterValues("questions[]");
        String[] questionTypes = request.getParameterValues("questionTypes[]");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("acc");
         try {
            // Kết nối đến cơ sở dữ liệu
             Connection conn = new DBContext().getConnection();

            // Lưu khảo sát vào cơ sở dữ liệu
            String insertSurveySQL = "INSERT INTO Surveys (title, created_by, created_at) VALUES (?, ?, GETDATE())";
            PreparedStatement surveyStmt = conn.prepareStatement(insertSurveySQL, PreparedStatement.RETURN_GENERATED_KEYS);
            surveyStmt.setString(1, surveyTitle);
            surveyStmt.setInt(2, user.getUser_id()); // Thay thế với ID người dùng hiện tại
            surveyStmt.executeUpdate();

            // Lấy ID của khảo sát vừa được lưu
            ResultSet rs = surveyStmt.getGeneratedKeys();
            rs.next();
            int surveyId = rs.getInt(1);

            // Lưu các câu hỏi vào cơ sở dữ liệu
            String insertQuestionSQL = "INSERT INTO Questions (survey_id, question_text, question_type) VALUES (?, ?, ?)";
            PreparedStatement questionStmt = conn.prepareStatement(insertQuestionSQL, PreparedStatement.RETURN_GENERATED_KEYS);

            for (int i = 0; i < questions.length; i++) {
                questionStmt.setInt(1, surveyId);
                questionStmt.setString(2, questions[i]);
                questionStmt.setString(3, questionTypes[i]);
                questionStmt.executeUpdate();

                // Lấy ID của câu hỏi vừa được lưu
                ResultSet rsQuestion = questionStmt.getGeneratedKeys();
                rsQuestion.next();
                int questionId = rsQuestion.getInt(1);

                // Lưu các tùy chọn cho câu hỏi (nếu có)
                if (questionTypes[i].equals("radio") || questionTypes[i].equals("checkbox")) {
                    String[] options = request.getParameterValues("options" + (i + 1) + "[]");
                    if (options != null) {
                        String insertOptionSQL = "INSERT INTO Options (question_id, option_text) VALUES (?, ?)";
                        PreparedStatement optionStmt = conn.prepareStatement(insertOptionSQL);
                        for (String option : options) {
                            optionStmt.setInt(1, questionId);
                            optionStmt.setString(2, option);
                            optionStmt.executeUpdate();
                        }
                        optionStmt.close();
                    }
                }
            }

            // Đóng kết nối
            questionStmt.close();
            surveyStmt.close();
            conn.close();

            response.sendRedirect("home");
        } catch (SQLException e) {
           out.print(e);
        }
    }
    

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
