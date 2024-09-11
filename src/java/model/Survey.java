/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Norttie
 */
public class Survey {
    private String survey_id;
    private String title;
    private String create_by;
    private Date create_at;


    public Survey(String survey_id, String title, String create_by, Date create_at) {
        this.survey_id = survey_id;
        this.title = title;
        this.create_by = create_by;
        this.create_at = create_at;
    }

    public String getSurvey_id() {
        return survey_id;
    }

    public void setSurvey_id(String survey_id) {
        this.survey_id = survey_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreate_by() {
        return create_by;
    }

    public void setCreate_by(String create_by) {
        this.create_by = create_by;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    @Override
    public String toString() {
        return "Survey{" + "survey_id=" + survey_id + ", title=" + title + ", create_by=" + create_by + ", create_at=" + create_at + '}';
    }
    
    
}
