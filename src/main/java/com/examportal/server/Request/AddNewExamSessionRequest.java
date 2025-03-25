package com.examportal.server.Request;

import java.sql.Timestamp;

public class AddNewExamSessionRequest {
    private String exam_sessions_description;
    private Timestamp exam_sessions_end_date;
    private String exam_sessions_name;
    private String exam_sessions_password;
    private Timestamp exam_sessions_start_date;

    public AddNewExamSessionRequest() {
    }

    public AddNewExamSessionRequest(String exam_sessions_description, Timestamp exam_sessions_end_date, String exam_sessions_name, String exam_sessions_password, Timestamp exam_sessions_start_date) {
        this.exam_sessions_description = exam_sessions_description;
        this.exam_sessions_end_date = exam_sessions_end_date;
        this.exam_sessions_name = exam_sessions_name;
        this.exam_sessions_password = exam_sessions_password;
        this.exam_sessions_start_date = exam_sessions_start_date;
    }

    public String getExam_sessions_description() {
        return exam_sessions_description;
    }

    public void setExam_sessions_description(String exam_sessions_description) {
        this.exam_sessions_description = exam_sessions_description;
    }

    public Timestamp getExam_sessions_end_date() {
        return exam_sessions_end_date;
    }

    public void setExam_sessions_end_date(Timestamp exam_sessions_end_date) {
        this.exam_sessions_end_date = exam_sessions_end_date;
    }

    public String getExam_sessions_name() {
        return exam_sessions_name;
    }

    public void setExam_sessions_name(String exam_sessions_name) {
        this.exam_sessions_name = exam_sessions_name;
    }

    public String getExam_sessions_password() {
        return exam_sessions_password;
    }

    public void setExam_sessions_password(String exam_sessions_password) {
        this.exam_sessions_password = exam_sessions_password;
    }

    public Timestamp getExam_sessions_start_date() {
        return exam_sessions_start_date;
    }

    public void setExam_sessions_start_date(Timestamp exam_sessions_start_date) {
        this.exam_sessions_start_date = exam_sessions_start_date;
    }
}