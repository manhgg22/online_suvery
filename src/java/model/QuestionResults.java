/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Norttie
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionResults {

    private int questionId;
    private String questionText;
    private String questionType;
    private List<String> textAnswers;
    private Map<String, Integer> optionCounts;

    public QuestionResults(int questionId, String questionText, String questionType) {
        this.questionId = questionId;
        this.questionText = questionText;
        this.questionType = questionType;
        this.textAnswers = new ArrayList<>();
        this.optionCounts = new HashMap<>();
    }

    public int getQuestionId() {
        return questionId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getQuestionType() {
        return questionType;
    }

    public List<String> getTextAnswers() {
        return textAnswers;
    }

    public Map<String, Integer> getOptionCounts() {
        return optionCounts;
    }

    public void addAnswer(String answerText) {
        if (questionType.equals("text")) {
            textAnswers.add(answerText);
        } else {
            optionCounts.put(answerText, optionCounts.getOrDefault(answerText, 0) + 1);
        }
    }
}
