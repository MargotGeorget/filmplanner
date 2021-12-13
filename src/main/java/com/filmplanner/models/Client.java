package com.filmplanner.models;

import com.filmplanner.exceptions.InvalidValuesClientException;
import utils.InputVerification;

public class Client {

    //Attributes:
    private long idClient;
    private String companyName;
    private String description;
    private String refereeName;
    private String refereeEmail;
    private String refereeTel;

    //Constructor:
    public Client(long idClient, String companyName, String description, String refereeName, String refereeEmail, String refereeTel) throws InvalidValuesClientException {
        this.setIdClient(idClient);
        this.setCompanyName(companyName);
        this.setDescription(description);
        this.setRefereeName(refereeName);
        this.setRefereeEmail(refereeEmail);
        this.setRefereeTel(refereeTel);
    }

    public Client(String companyName, String description, String refereeName, String refereeEmail, String refereeTel) throws InvalidValuesClientException {
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

    public void setCompanyName(String companyName) throws InvalidValuesClientException {
        if(companyName.length()<2){
            throw new InvalidValuesClientException("Error : Company Name too short!");
        }else {
            this.companyName = companyName;
        }
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

    public void setRefereeName(String refereeName) throws InvalidValuesClientException {
        if (!this.isValidString(refereeName, new InputVerification())){
            throw new InvalidValuesClientException("Error: Referee name contains special symbol!");
        } else if (refereeName.length()<2){
            throw new InvalidValuesClientException("Error: Referee name too short!");
        } else if (refereeName.length()>30){
            throw new InvalidValuesClientException("Error: Referee name too long!");
        } else {
            this.refereeName = refereeName;
        }
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

    public void setRefereeTel(String refereeTel) throws InvalidValuesClientException {
        if(!this.isPhoneNumber(refereeTel, new InputVerification())){
            throw new InvalidValuesClientException("Error: Referee tel is not a phone number");
        }else {
            this.refereeTel = refereeTel;
        }
    }

    //Functions:
    public void setAllInformation(String companyName, String description, String refereeName, String refereeEmail, String refereeTel) throws InvalidValuesClientException {
        this.setCompanyName(companyName);
        this.setDescription(description);
        this.setRefereeName(refereeName);
        this.setRefereeEmail(refereeEmail);
        this.setRefereeTel(refereeTel);
    }

    private boolean isValidString(String str, InputVerification verificator){
        return verificator.isStringWithoutSpecialSymbol(str);
    }

    private boolean isPhoneNumber(String str, InputVerification verificator){
        return verificator.isPhoneNumber(str);
    }

    @Override
    public String toString() {
        return companyName + " -- " + "Referee: " + refereeName + " ";
    }
}
