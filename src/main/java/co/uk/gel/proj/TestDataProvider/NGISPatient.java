package co.uk.gel.proj.TestDataProvider;

import co.uk.gel.proj.util.Debugger;

public class NGISPatient {

    private String FIRST_NAME;
    private String LAST_NAME;
    private String TITLE;
    private String FULL_NAME;
    private String DAY_OF_BIRTH;
    private String MONTH_OF_BIRTH;
    private String YEAR_OF_BIRTH;
    private String DATE_OF_BIRTH;
    private String GENDER;
    private String LIFE_STATUS;
    private String NHS_NUMBER;
    private String ADDRESS_LINE1;
    private String ADDRESS_LINE2;
    private String ADDRESS_LINE3;
    private String ADDRESS_LINE4;
    private String ADDRESS_LINE5;
    private String POST_CODE;
    private String FULL_ADDRESS;
    private String RELATIONSHIP_TO_PROBAND;

    public String getFIRST_NAME() {
        return FIRST_NAME;
    }

    public void setFIRST_NAME(String FIRST_NAME) {
        this.FIRST_NAME = FIRST_NAME;
    }

    public String getLAST_NAME() {
        return LAST_NAME;
    }

    public void setLAST_NAME(String LAST_NAME) {
        this.LAST_NAME = LAST_NAME;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public String getFULL_NAME() {
        FULL_NAME = LAST_NAME + ", " + FIRST_NAME + " (" + TITLE +  ")";
        return FULL_NAME;
    }

    public String getDAY_OF_BIRTH() {
        return DAY_OF_BIRTH;
    }

    public String getMONTH_OF_BIRTH() {
        return MONTH_OF_BIRTH;
    }

    public String getYEAR_OF_BIRTH() {
        return YEAR_OF_BIRTH;
    }

    public String getDATE_OF_BIRTH() {
        return DATE_OF_BIRTH;
    }

    public void setDATE_OF_BIRTH(String DATE_OF_BIRTH) {
        this.DATE_OF_BIRTH = DATE_OF_BIRTH;
        try {
            //Set Day,month and year of birth also.
            this.DAY_OF_BIRTH = DATE_OF_BIRTH.split("-")[0];
            this.MONTH_OF_BIRTH = DATE_OF_BIRTH.split("-")[1];
            this.YEAR_OF_BIRTH = DATE_OF_BIRTH.split("-")[2];
        }catch(Exception exp){
            Debugger.println("Exception in setting DAY,MONTH and YEAR from Given DOB: "+DATE_OF_BIRTH);
        }
    }

    public String getGENDER() {
        return GENDER;
    }

    public void setGENDER(String GENDER) {
        this.GENDER = GENDER;
    }

    public String getLIFE_STATUS() {
        return LIFE_STATUS;
    }

    public void setLIFE_STATUS(String LIFE_STATUS) {
        this.LIFE_STATUS = LIFE_STATUS;
    }

    public String getNHS_NUMBER() {
        return NHS_NUMBER;
    }

    public void setNHS_NUMBER(String NHS_NUMBER) {
        this.NHS_NUMBER = NHS_NUMBER;
    }

    public String getADDRESS_LINE1() {
        return ADDRESS_LINE1;
    }

    public void setADDRESS_LINE1(String ADDRESS_LINE1) {
        this.ADDRESS_LINE1 = ADDRESS_LINE1;
    }

    public String getADDRESS_LINE2() {
        return ADDRESS_LINE2;
    }

    public void setADDRESS_LINE2(String ADDRESS_LINE2) {
        this.ADDRESS_LINE2 = ADDRESS_LINE2;
    }

    public String getADDRESS_LINE3() {
        return ADDRESS_LINE3;
    }

    public void setADDRESS_LINE3(String ADDRESS_LINE3) {
        this.ADDRESS_LINE3 = ADDRESS_LINE3;
    }

    public String getADDRESS_LINE4() {
        return ADDRESS_LINE4;
    }

    public void setADDRESS_LINE4(String ADDRESS_LINE4) {
        this.ADDRESS_LINE4 = ADDRESS_LINE4;
    }

    public String getADDRESS_LINE5() {
        return ADDRESS_LINE5;
    }

    public void setADDRESS_LINE5(String ADDRESS_LINE5) {
        this.ADDRESS_LINE5 = ADDRESS_LINE5;
    }

    public String getPOST_CODE() {
        return POST_CODE;
    }

    public void setPOST_CODE(String POST_CODE) {
        this.POST_CODE = POST_CODE;
    }

    public String getFULL_ADDRESS() {
        FULL_ADDRESS = "Address " + ADDRESS_LINE1 + ", " + ADDRESS_LINE2 + ", " + ADDRESS_LINE3 + ", " + POST_CODE;
        return FULL_ADDRESS;
    }

    public String getRELATIONSHIP_TO_PROBAND() {
        return RELATIONSHIP_TO_PROBAND;
    }

    public void setRELATIONSHIP_TO_PROBAND(String RELATIONSHIP_TO_PROBAND) {
        this.RELATIONSHIP_TO_PROBAND = RELATIONSHIP_TO_PROBAND;
    }
}//end
