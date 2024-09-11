/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.DBContext;
import dao.SurveyDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import model.Question;
import model.Survey;
import model.User;

/**
 *
 * @author Norttie
 */
@WebServlet(name = "Answersurvey", urlPatterns = {"/Answersurvey"})
public class Answersurvey extends HttpServlet {

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
        int surveyId = Integer.parseInt(request.getParameter("surveyid"));
        List<Question> questions = new ArrayList<>();
        HttpSession session = request.getSession();
        User acc = (User) session.getAttribute("acc");
        SurveyDAO dao = new SurveyDAO();
        Survey survey = dao.getSurveyByid(""+surveyId);
        boolean checkAnsweredSurvey = dao.checkAnsweredSurvey("" + surveyId, "" + acc.getUser_id());
        try {
            if (!checkAnsweredSurvey) {
                Connection conn = new DBContext().getConnection();
                // Lấy các câu hỏi từ cơ sở dữ liệu
                String queryQuestionsSQL = "SELECT question_id, question_text, question_type FROM Questions WHERE survey_id = ?";
                PreparedStatement questionStmt = conn.prepareStatement(queryQuestionsSQL);
                questionStmt.setInt(1, surveyId);
                ResultSet rsQuestions = questionStmt.executeQuery();

                while (rsQuestions.next()) {
                    String questionId = "" + rsQuestions.getInt("question_id");
                    String questionText = rsQuestions.getString("question_text");
                    String questionType = rsQuestions.getString("question_type");

                    // Tạo đối tượng Question và thêm vào danh sách
                    Question question = new Question(questionId, questionText, questionType);

                    // Lấy các tùy chọn cho câu hỏi nếu loại câu hỏi là radio hoặc checkbox
                    if (questionType.equals("radio") || questionType.equals("checkbox")) {
                        String queryOptionsSQL = "SELECT option_text FROM Options WHERE question_id = ?";
                        PreparedStatement optionStmt = conn.prepareStatement(queryOptionsSQL);
                        optionStmt.setString(1, questionId);
                        ResultSet rsOptions = optionStmt.executeQuery();
                        while (rsOptions.next()) {
                            question.addOption(rsOptions.getString("option_text"));
                        }
                        optionStmt.close();
                    }

                    questions.add(question);
                }

                // Đóng kết nối
                rsQuestions.close();
                questionStmt.close();
                conn.close();
            }

        } catch (Exception e) {
            response.sendRedirect("error.jsp");
            return;
        }

        // Đặt các câu hỏi vào request và chuyển tiếp đến trang JSP
            request.setAttribute("check", checkAnsweredSurvey);
            request.setAttribute("surveyId", surveyId);
            request.setAttribute("survey", survey);
            request.setAttribute("questions", questions);
            request.getRequestDispatcher("answer.jsp").forward(request, response);

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
        response.setContentType("text/html;charset=UTF-8");
        int surveyId = Integer.parseInt(request.getParameter("surveyId"));
        HttpSession sesstion = request.getSession();
        User acc = (User) sesstion.getAttribute("acc");
        int userId = acc.getUser_id(); // Giả sử ID của người dùng hiện tại là 1, bạn có thể thay thế bằng cách lấy ID từ session hoặc cookie.
        try {
            Connection conn = new DBContext().getConnection();
            Enumeration<String> parameterNames = request.getParameterNames();
            while (parameterNames.hasMoreElements()) {
                String paramName = parameterNames.nextElement();
                if (paramName.startsWith("answers_")) {
                    int questionId = Integer.parseInt(paramName.substring(8));
                    String[] answers = request.getParameterValues(paramName);

                    for (String answer : answers) {
                        // Lưu câu trả lời vào cơ sở dữ liệu
                        String insertAnswerSQL = "INSERT INTO Answers (user_id, survey_id, question_id, answer_text) VALUES (?, ?, ?, ?)";
                        PreparedStatement answerStmt = conn.prepareStatement(insertAnswerSQL);
                        answerStmt.setInt(1, userId);
                        answerStmt.setInt(2, surveyId);
                        answerStmt.setInt(3, questionId);
                        answerStmt.setString(4, answer);
                        answerStmt.executeUpdate();
                        answerStmt.close();
                    }
                }
            }

            // Đóng kết nối
            conn.close();

            // Chuyển hướng người dùng đến trang xác nhận
            response.sendRedirect("Answersurvey?surveyid="+surveyId);
        } catch (Exception e) {
        }
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
