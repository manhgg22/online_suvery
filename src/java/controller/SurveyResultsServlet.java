/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

/**
 *
 * @author Norttie
 */
import dao.DBContext;
import dao.SurveyDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.QuestionResults;
import model.Survey;

@WebServlet(name = "SurveyResultsServlet", urlPatterns = {"/SurveyResults"})
public class SurveyResultsServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SurveyResultsServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SurveyResultsServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int surveyId = Integer.parseInt(request.getParameter("surveyId"));
        Map<Integer, QuestionResults> resultsMap = new HashMap<>();
        SurveyDAO dao = new SurveyDAO();
        Survey survey = dao.getSurveyByid(""+surveyId);
        try {
            // Kết nối đến cơ sở dữ liệu
            Connection conn = new DBContext().getConnection();
            // Lấy các câu hỏi từ cơ sở dữ liệu
            String queryQuestionsSQL = "SELECT question_id, question_text, question_type FROM Questions WHERE survey_id = ?";
            PreparedStatement questionStmt = conn.prepareStatement(queryQuestionsSQL);
            questionStmt.setInt(1, surveyId);
            ResultSet rsQuestions = questionStmt.executeQuery();

            while (rsQuestions.next()) {
                int questionId = rsQuestions.getInt("question_id");
                String questionText = rsQuestions.getString("question_text");
                String questionType = rsQuestions.getString("question_type");

                // Tạo đối tượng QuestionResults và thêm vào map
                QuestionResults questionResults = new QuestionResults(questionId, questionText, questionType);
                resultsMap.put(questionId, questionResults);
            }

            // Lấy các câu trả lời từ cơ sở dữ liệu
            String queryAnswersSQL = "SELECT question_id, answer_text FROM Answers WHERE survey_id = ?";
            PreparedStatement answerStmt = conn.prepareStatement(queryAnswersSQL);
            answerStmt.setInt(1, surveyId);
            ResultSet rsAnswers = answerStmt.executeQuery();

            while (rsAnswers.next()) {
                int questionId = rsAnswers.getInt("question_id");
                String answerText = rsAnswers.getString("answer_text");

                // Thêm câu trả lời vào đối tượng QuestionResults
                QuestionResults questionResults = resultsMap.get(questionId);
                if (questionResults != null) {
                    questionResults.addAnswer(answerText);
                }
            }

            // Đóng kết nối
            rsAnswers.close();
            answerStmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
            return;
        }

        // Đặt kết quả vào request và chuyển tiếp đến trang JSP
        request.setAttribute("surveyId", surveyId);
        request.setAttribute("resultsMap", resultsMap);
        request.setAttribute("survey",survey);
        request.getRequestDispatcher("surveyResults.jsp").forward(request, response);
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
