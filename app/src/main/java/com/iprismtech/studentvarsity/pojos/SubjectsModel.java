package com.iprismtech.studentvarsity.pojos;

public class SubjectsModel {
    String id,education_id,stream_id,year,semester,subject,created_on,modified_on;

    Boolean cheked;

    public SubjectsModel(String id,String subject ,Boolean cheked) {
        this.id = id;

        this.subject = subject;

        this.cheked=cheked;
    }

    public Boolean getCheked() {
        return cheked;
    }

    public void setCheked(Boolean cheked) {
        this.cheked = cheked;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEducation_id() {
        return education_id;
    }

    public void setEducation_id(String education_id) {
        this.education_id = education_id;
    }

    public String getStream_id() {
        return stream_id;
    }

    public void setStream_id(String stream_id) {
        this.stream_id = stream_id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCreated_on() {
        return created_on;
    }

    public void setCreated_on(String created_on) {
        this.created_on = created_on;
    }

    public String getModified_on() {
        return modified_on;
    }

    public void setModified_on(String modified_on) {
        this.modified_on = modified_on;
    }
}
