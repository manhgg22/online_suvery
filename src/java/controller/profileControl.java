/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AccountDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import model.User;

/**
 *
 * @author Norttie
 */
@MultipartConfig
@WebServlet(name = "profileControl", urlPatterns = {"/profile"})
public class profileControl extends HttpServlet {

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
            out.println("<title>Servlet profileControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet profileControl at " + request.getContextPath() + "</h1>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("profile.jsp");
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
        String id = request.getParameter("id");
        String displayname = request.getParameter("displayname");
        String desc = request.getParameter("desc");
        String address = request.getParameter("address");
        String filename = "";

        try {
            // Lấy phần file từ request
            Part part = request.getPart("file");
            if (part != null && part.getSize() > 0) {
                // Lấy đường dẫn thư mục gốc của ứng dụng
                String appPath = request.getServletContext().getRealPath("/");
                // Đường dẫn tới thư mục mong muốn (cùng cấp với thư mục chứa servlet)
                String path = "web/assets/image";
                Path uploadDir = Paths.get(appPath).getParent().getParent().resolve(path);

                // Kiểm tra và tạo thư mục nếu chưa tồn tại
                if (!Files.exists(uploadDir)) {
                    Files.createDirectories(uploadDir);
                }

                // Lấy tên file và đường dẫn file đầy đủ
                filename = "id" + id + "_" + Path.of(part.getSubmittedFileName()).getFileName().toString();
                Path filePath = uploadDir.resolve(filename);

                // Kiểm tra nếu file đã tồn tại, thì ghi đè lên file đó
                if (Files.exists(filePath)) {
                    Files.copy(part.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                } else {
                    // Nếu file chưa tồn tại, ghi file mới vào
                    part.write(filePath.toString());
                }

            } else {
                // Xử lý khi không có file được upload
            }
            HttpSession session = request.getSession();
            User acc = (User) session.getAttribute("acc");

            if (filename.isEmpty()) {
                filename = acc.getAvatar();
            }
            AccountDao dao = new AccountDao();
            dao.updateUser(id, displayname,filename , desc, address);
            User account = dao.getUserById(id);
            session.setAttribute("acc", account);
            request.getRequestDispatcher("profile.jsp").forward(request, response);

        } catch (IOException | ServletException e) {
            // Xử lý ngoại lệ
            e.printStackTrace();
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
