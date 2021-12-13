package com.filmplanner.models;

public class Client {

    //Attributes:
    private long idClient;
    private String companyName;
    private String description;
    private String refereeName;
    private String refereeEmail;
    private String refereeTel;

    //Constructor:
    public Client(long idClient, String companyName, String description, String refereeName, String refereeEmail, String refereeTel) {
        this.idClient = idClient;
        this.companyName = companyName;
        this.description = description;
        this.refereeName = refereeName;
        this.refereeEmail = refereeEmail;
        this.refereeTel = refereeTel;
    }

    public Client(String companyName, String description, String refereeName, String refereeEmail, String refereeTel) {
        this(-1, companyName, description, refereeName, refereeEmail, refereeTel);
    }

    //Getters and Setters:
    public long getIdClient() {
        return idClient;
    }

    public void setIdClient(long idClient) {
        this.idClient = idClient;
    }

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

    //Function:
    public void setAllInformation(String companyName, String description, String refereeName, String refereeEmail, String refereeTel){
        this.setCompanyName(companyName);
        this.setDescription(description);
        this.setRefereeName(refereeName);
        this.setRefereeEmail(refereeEmail);
        this.setRefereeTel(refereeTel);
    }

    @Override
    public String toString() {
        return companyName + " -- " + "Referee: " + refereeName + " ";
    }
}
