package com.iprismtech.studentvarsity.pojos;

import java.util.List;

public class Member {
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<String> getMobile_numbers() {
        return mobile_numbers;
    }

    public void setMobile_numbers(List<String> mobile_numbers) {
        this.mobile_numbers = mobile_numbers;
    }

    private String token;
    private List<String> mobile_numbers;

    //getter and setter at lower
}
