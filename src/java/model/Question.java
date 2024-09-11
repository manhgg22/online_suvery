/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Norttie
 */
public class Question {

    private String questionId;
    private String surveyId;
    private String questionText;
    private String questionType;
    private List<String> options;

    public Question() {
    }

    public Question(String questionId, String surveyId, String questionText, String questionType) {
        this.questionId = questionId;
        this.surveyId = surveyId;
        this.questionText = questionText;
        this.questionType = questionType;
    }


    public Question(String questionId, String questionText, String questionType) {
        this.questionId = questionId;
        this.questionText = questionText;
        this.questionType = questionType;
        this.options = new ArrayList<>();
    }

    public String getQuestionId() {
        return questionId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getQuestionType() {
        return questionType;
    }

    public List<String> getOptions() {
        return options;
    }

    public void addOption(String option) {
        this.options.add(option);
    }

    @Override
    public String toString() {
        return "Question{" + "questionId=" + questionId + ", surveyId=" + surveyId + ", questionText=" + questionText + ", questionType=" + questionType + '}';
    }

}
