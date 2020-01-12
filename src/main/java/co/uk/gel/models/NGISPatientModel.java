package co.uk.gel.models;

import co.uk.gel.proj.util.Debugger;

public class NGISPatientModel {

    private String FIRST_NAME;
    private String LAST_NAME;
    private String TITLE;
    private String FULL_NAME;
    private String DAY_OF_BIRTH;
    private String MONTH_OF_BIRTH;
    private String YEAR_OF_BIRTH;
    private String DATE_OF_BIRTH;
    private String BORN_DATE;
    private String BORN_WITH_AGE;
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
    private String RECORDING_CLINICIAN_NAME;
    private String REFERAL_ID;
    private String NGIS_ID;
    private String PATIENT_TYPE;

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
        return FULL_NAME;
    }

    public void setFULL_NAME(String FULL_NAME) {
        this.FULL_NAME = FULL_NAME;
    }

    public String getDAY_OF_BIRTH() {
        return DAY_OF_BIRTH;
    }

    public void setDAY_OF_BIRTH(String DAY_OF_BIRTH) {
        this.DAY_OF_BIRTH = DAY_OF_BIRTH;
    }

    public String getMONTH_OF_BIRTH() {
        return MONTH_OF_BIRTH;
    }

    public void setMONTH_OF_BIRTH(String MONTH_OF_BIRTH) {
        this.MONTH_OF_BIRTH = MONTH_OF_BIRTH;
    }

    public String getYEAR_OF_BIRTH() {
        return YEAR_OF_BIRTH;
    }

    public void setYEAR_OF_BIRTH(String YEAR_OF_BIRTH) {
        this.YEAR_OF_BIRTH = YEAR_OF_BIRTH;
    }

    public String getDATE_OF_BIRTH() {
        return DATE_OF_BIRTH;
    }

    public void setDATE_OF_BIRTH(String DATE_OF_BIRTH) {
        this.DATE_OF_BIRTH = DATE_OF_BIRTH;
        try {
            //Split and set the DAY, MONTH and YEAR OF BIRTH ALSO
            String[] dobs = DATE_OF_BIRTH.split("-");
            this.DAY_OF_BIRTH = dobs[0];
            this.MONTH_OF_BIRTH = dobs[1];
            this.YEAR_OF_BIRTH = dobs[2];
        }catch(Exception exp){
            Debugger.println("DATE OF BIRTH EXPECTED FORMAT : DD-MM-YYYY. NOT "+DATE_OF_BIRTH);
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
        return FULL_ADDRESS;
    }

    public void setFULL_ADDRESS(String FULL_ADDRESS) {
        this.FULL_ADDRESS = FULL_ADDRESS;
    }

    public String getRELATIONSHIP_TO_PROBAND() {
        return RELATIONSHIP_TO_PROBAND;
    }

    public void setRELATIONSHIP_TO_PROBAND(String RELATIONSHIP_TO_PROBAND) {
        this.RELATIONSHIP_TO_PROBAND = RELATIONSHIP_TO_PROBAND;
    }

    public String getBORN_DATE() {
        return BORN_DATE;
    }

    public void setBORN_DATE(String BORN_DATE) {
        this.BORN_DATE = BORN_DATE;
    }

    public String getRECORDING_CLINICIAN_NAME() {
        return RECORDING_CLINICIAN_NAME;
    }

    public void setRECORDING_CLINICIAN_NAME(String RECORDING_CLINICIAN_NAME) {
        this.RECORDING_CLINICIAN_NAME = RECORDING_CLINICIAN_NAME;
    }

    public String getREFERAL_ID() {
        return REFERAL_ID;
    }

    public void setREFERAL_ID(String REFERAL_ID) {
        this.REFERAL_ID = REFERAL_ID;
    }

    public String getNGIS_ID() {
        return NGIS_ID;
    }

    public void setNGIS_ID(String NGIS_ID) {
        this.NGIS_ID = NGIS_ID;
    }

    public String getBORN_WITH_AGE() {
        return BORN_WITH_AGE;
    }

    public void setBORN_WITH_AGE(String BORN_WITH_AGE) {
        this.BORN_WITH_AGE = BORN_WITH_AGE;
    }

    public String getPATIENT_TYPE() {
        return PATIENT_TYPE;
    }

    public void setPATIENT_TYPE(String PATIENT_TYPE) {
        this.PATIENT_TYPE = PATIENT_TYPE;
    }
}//end
