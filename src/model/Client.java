package model;

import java.time.LocalDate;

public class Client extends User {

    private String jobInfo;
    private Double annualIncome;
    private Integer creditNote;
    private LocalDate birthDateField;
    private String maritalStatus;
    private Integer yearsInCanada;

    public Client(String fullName, String email, String password, String phoneNumber, String jobInfo, Double annualIncome, Integer creditNote, LocalDate birthDateField, String maritalStatus, int yearsInCanada) {
        super(fullName, email, password, phoneNumber);
        this.jobInfo = jobInfo;
        this.annualIncome = annualIncome;
        this.creditNote = creditNote;
        this.birthDateField = birthDateField;
        this.maritalStatus = maritalStatus;
        this.yearsInCanada = yearsInCanada;
    }

    public Client() {
        super();
    }
    public Client(String email, String password) {
        super(email, password);
    }


    public String getJobInfo() {
        return jobInfo;
    }

    public void setJobInfo(String jobInfo) {
        this.jobInfo = jobInfo;
    }

    public Double getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(Double annualIncome) {
        this.annualIncome = annualIncome;
    }

    public Integer getCreditNote() {
        return creditNote;
    }

    public void setCreditNote(Integer creditNote) {
        this.creditNote = creditNote;
    }

    public LocalDate getBirthDateField() {
        return birthDateField;
    }

    public void setBirthDateField(LocalDate birthDateField) {
        this.birthDateField = birthDateField;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Integer getYearsInCanada() {
        return yearsInCanada;
    }

    public void setYearsInCanada(Integer yearsInCanada) {
        this.yearsInCanada = yearsInCanada;
    }

    @Override
    public String toString() {
        return "Client{" +
                ", fullName='" + getFullName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", password=" + getPassword() + '\'' +
                ", phoneNumber=" + getPhoneNumber() + '\'' +
                ", jobInfo='" + jobInfo + '\'' +
                ", annualIncome=" + annualIncome + '\'' +
                ", creditNote=" + creditNote + '\'' +
                ", birthDateField=" + birthDateField + '\'' +
                ", maritalStatus='" + maritalStatus + '\'' +
                ", yearsInCanada=" + yearsInCanada +
                '}';
    }
}
