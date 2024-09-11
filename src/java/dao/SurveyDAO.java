/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Survey;

/**
 *
 * @author Norttie
 */
public class SurveyDAO extends DBContext {

    public List<Survey> getSurveyByUserid(String accountid, int index, String searchValue) {
        String sql = "select * from Surveys s where s.created_by = ?  order by s.survey_id offset ? rows fetch first 5 rows only";
        if (!searchValue.equals("")) {
            sql = "select * from Surveys s where s.created_by = ? and s.title like ?  order by s.survey_id offset ? rows fetch first 5 rows only";
        }

        List<Survey> lr = new ArrayList<>();

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            int parameter = 1;
            st.setString(parameter++, accountid);
            if (!searchValue.equals("")) {
                st.setString(parameter++, "%" + searchValue + "%");
            }
            st.setInt(parameter, (index - 1) * 5);
            ResultSet rs;
            rs = st.executeQuery();
            while (rs.next()) {
                Survey survey = new Survey(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDate(4));

                lr.add(survey);
            }
        } catch (SQLException e) {
            System.out.println("Error cannot get account");
            return null;
        }
        return lr;
    }

    public int getNumberPageAcc(String accountid, String searchValue) {

        String sql = "select count(*) from Surveys s where s.created_by = ?";
        if (!searchValue.equals("")) {
            sql = "select count(*) from Surveys s where s.title like ? and s.created_by = ?";

        }

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            int parameter = 1;
            if (!searchValue.equals("")) {
                st.setString(parameter++, "%" + searchValue + "%");
            }
            st.setString(parameter, accountid);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int total = rs.getInt(1);
                int countPage = total / 5;
                if (total % 5 != 0) {
                    countPage++;
                }
                return countPage;
            }
        } catch (SQLException e) {
        }
        return 0;
    }

    public int getNumOfQuestion(String surveyid) {
        String sql = "select count(*) from Questions q where q.survey_id = ?";
        int result = 0;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            int parameter = 1;

            st.setString(parameter, surveyid);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (SQLException e) {
        }
        return result;
    }

    public int getNumOfAnswer(String surveyid) {
        String sql = "SELECT COUNT(*)\n"
                + "FROM(select a.survey_id,a.user_id from Answers a where a.survey_id=? group by a.survey_id,a.user_id) AS unique_pairs";
        int result = 0;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            int parameter = 1;

            st.setString(parameter, surveyid);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (SQLException e) {
        }
        return result;
    }

    public boolean checkAnsweredSurvey(String survey_id, String user_id) {
        String sql = "select * from Answers a where a.survey_id = ? and a.user_id = ?";
        boolean result = false;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            int parameter = 1;

            st.setString(parameter++, survey_id);
            st.setString(parameter, user_id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                result = true;
            }
        } catch (SQLException e) {
        }
        return result;
    }

    public Survey getSurveyByid(String survey_id) {
        String sql = "select * from Surveys s where s.survey_id = ? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            int parameter = 1;

            st.setString(parameter++, survey_id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                Survey survey = new Survey(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDate(4));
                return survey;
            }
        } catch (SQLException e) {
        }
        return null;
    }
    
     public void deleteSurveyByid(String survey_id) {
        String sql = "delete from Surveys  where survey_id = ? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            int parameter = 1;

            st.setString(parameter++, survey_id);
            st.executeUpdate();


        } catch (SQLException e) {
        }
    }

    public static void main(String[] args) {
        SurveyDAO dao = new SurveyDAO();
          dao.deleteSurveyByid("18");
    }

}
