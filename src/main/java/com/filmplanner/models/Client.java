package com.filmplanner.models;

public class Client {

    //Attributes:
    private String companyName;
    private String description;
    private String refereeName;
    private String refereeSurname;
    private String refereeEmail;
    private String refereeTel;

    //Constructor:
    public Client(String companyName, String description, String refereeName, String refereeSurname, String refereeEmail, String refereeTel) {
        this.companyName = companyName;
        this.description = description;
        this.refereeName = refereeName;
        this.refereeSurname = refereeSurname;
        this.refereeEmail = refereeEmail;
        this.refereeTel = refereeTel;
    }

    //Getters and Setters:
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRefereeName() {
        return refereeName;
    }

    public void setRefereeName(String refereeName) {
        this.refereeName = refereeName;
    }

    public String getRefereeSurname() {
        return refereeSurname;
    }

    public void setRefereeSurname(String refereeSurname) {
        this.refereeSurname = refereeSurname;
    }

    public String getRefereeEmail() {
        return refereeEmail;
    }

    public void setRefereeEmail(String refereeEmail) {
        this.refereeEmail = refereeEmail;
    }

    public String getRefereeTel() {
        return refereeTel;
    }

    public void setRefereeTel(String refereeTel) {
        this.refereeTel = refereeTel;
    }
}
