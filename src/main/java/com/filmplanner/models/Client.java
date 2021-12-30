package com.filmplanner.models;

import com.filmplanner.exceptions.InvalidInputException;
import utils.ValidationUtils;

public class Client {

    //Attributes:
    private long idClient;
    private String companyName;
    private String description;
    private String refereeName;
    private String refereeEmail;
    private String refereeTel;

    //Constructor:
    public Client(long idClient, String companyName, String description, String refereeName, String refereeEmail, String refereeTel) throws InvalidInputException {
        this.setIdClient(idClient);
        this.setCompanyName(companyName);
        this.setDescription(description);
        this.setRefereeName(refereeName);
        this.setRefereeEmail(refereeEmail);
        this.setRefereeTel(refereeTel);
    }

    public Client(String companyName, String description, String refereeName, String refereeEmail, String refereeTel) throws InvalidInputException {
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

    public void setCompanyName(String companyName) throws InvalidInputException {
        if (companyName.length() < 2) {
            throw new InvalidInputException("Company Name too short!");
        } else {
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

    public void setRefereeName(String refereeName) throws InvalidInputException {
        if (!ValidationUtils.isStringWithoutSpecialSymbol(refereeName)) {
            throw new InvalidInputException("Referee name contains special symbol!");
        } else if (refereeName.length() < 2) {
            throw new InvalidInputException("Referee name too short!");
        } else if (refereeName.length() > 30) {
            throw new InvalidInputException("Referee name too long!");
        } else {
            this.refereeName = refereeName;
        }
    }

    public String getRefereeEmail() {
        return refereeEmail;
    }

    public void setRefereeEmail(String refereeEmail) throws InvalidInputException {
        if (!ValidationUtils.isEmail(refereeEmail)) {
            throw new InvalidInputException("Referee email is not an email");
        } else {
            this.refereeEmail = refereeEmail;
        }
    }

    public String getRefereeTel() {
        return refereeTel;
    }

    public void setRefereeTel(String refereeTel) throws InvalidInputException {
        if (!ValidationUtils.isPhoneNumber(refereeTel)) {
            throw new InvalidInputException("Error: Referee tel is not a phone number");
        } else {
            this.refereeTel = refereeTel;
        }
    }

    //Functions:
    public void setAllInformation(String companyName, String description, String refereeName, String refereeEmail, String refereeTel) throws InvalidInputException {
        this.setCompanyName(companyName);
        this.setDescription(description);
        this.setRefereeName(refereeName);
        this.setRefereeEmail(refereeEmail);
        this.setRefereeTel(refereeTel);
    }

    private boolean isValidString(String str, ValidationUtils validator) {
        return validator.isStringWithoutSpecialSymbol(str);
    }


    @Override
    public String toString() {
        return companyName + " -- " + "Referee: " + refereeName + " ";
    }
}
