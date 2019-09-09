package com.example.abhishek.stock;

import android.net.UrlQuerySanitizer;

import java.net.URL;

public class Member {

    private String name;
    private String brokerage;
    private String intra;
    private String account;
    private String annual;

    public Member() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrokerage() {
        return brokerage;
    }

    public void setBrokerage(String brokerage) {
        this.brokerage = brokerage;
    }

    public String getIntra() {
        return intra;
    }

    public void setIntra(String intra) {
        this.intra = intra;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAnnual() {
        return annual;
    }

    public void setAnnual(String annual) {
        this.annual = annual;
    }


}
