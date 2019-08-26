package com.iprismtech.studentvarsity.pojos;

import java.util.ArrayList;

public class SemisterModel  {
    String semester;
    ArrayList<SubjectsModel> subjectsModels;

    public SemisterModel(String semester, ArrayList<SubjectsModel> subjectsModels) {
        this.semester = semester;
        this.subjectsModels = subjectsModels;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public ArrayList<SubjectsModel> getSubjectsModels() {
        return subjectsModels;
    }

    public void setSubjectsModels(ArrayList<SubjectsModel> subjectsModels) {
        this.subjectsModels = subjectsModels;
    }
}
