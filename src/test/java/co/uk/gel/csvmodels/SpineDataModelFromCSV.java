package co.uk.gel.csvmodels;

import com.opencsv.bean.CsvBindByName;

public class SpineDataModelFromCSV {

    @CsvBindByName(column = "name")
    private String testref;
    @CsvBindByName(column = "NHS_NUMBER")
    private String NHS_NUMBER;
    @CsvBindByName(column = "DATE_OF_BIRTH")
    private String DATE_OF_BIRTH;
    @CsvBindByName(column = "DATE_OF_DEATH")
    private String DATE_OF_DEATH;
    @CsvBindByName(column = "FAMILY_NAME")
    private String FAMILY_NAME;
    @CsvBindByName(column = "GIVEN_NAME")
    private String GIVEN_NAME;
    @CsvBindByName(column = "OTHER_GIVEN_NAME")
    private String OTHER_GIVEN_NAME;
    @CsvBindByName(column = "TITLE")
    private String TITLE;
    @CsvBindByName(column = "GENDER")
    private String GENDER;
    @CsvBindByName(column = "ADDRESS_LINE_1")
    private String ADDRESS_LINE_1;
    @CsvBindByName(column = "ADDRESS_LINE_2")
    private String ADDRESS_LINE_2;
    @CsvBindByName(column = "ADDRESS_LINE_3")
    private String ADDRESS_LINE_3;
    @CsvBindByName(column = "ADDRESS_LINE_4")
    private String ADDRESS_LINE_4;
    @CsvBindByName(column = "ADDRESS_LINE_5")
    private String ADDRESS_LINE_5;
    @CsvBindByName(column = "PAF_KEY")
    private String PAF_KEY;
    @CsvBindByName(column = "SENSITIVE_FLAG")
    private String SENSITIVE_FLAG;
    @CsvBindByName(column = "PRIMARY_CARE_CODE")
    private String PRIMARY_CARE_CODE;
    @CsvBindByName(column = "REF_ID")
    private String REF_ID;
    @CsvBindByName(column = "POST_CODE")
    private String POST_CODE;
    @CsvBindByName(column = "Notes")
    private String Notes;

    public String getTestref() {
        return testref;
    }

    public void setTestref(String testref) {
        this.testref = testref;
    }

    public String getNHS_NUMBER() {
        return NHS_NUMBER;
    }

    public void setNHS_NUMBER(String NHS_NUMBER) {
        this.NHS_NUMBER = NHS_NUMBER;
    }

    public String getDATE_OF_BIRTH() {
        return DATE_OF_BIRTH;
    }

    public void setDATE_OF_BIRTH(String DATE_OF_BIRTH) {
        this.DATE_OF_BIRTH = DATE_OF_BIRTH;
    }

    public String getDATE_OF_DEATH() {
        return DATE_OF_DEATH;
    }

    public void setDATE_OF_DEATH(String DATE_OF_DEATH) {
        this.DATE_OF_DEATH = DATE_OF_DEATH;
    }

    public String getFAMILY_NAME() {
        return FAMILY_NAME;
    }

    public void setFAMILY_NAME(String FAMILY_NAME) {
        this.FAMILY_NAME = FAMILY_NAME;
    }

    public String getGIVEN_NAME() {
        return GIVEN_NAME;
    }

    public void setGIVEN_NAME(String GIVEN_NAME) {
        this.GIVEN_NAME = GIVEN_NAME;
    }

    public String getOTHER_GIVEN_NAME() {
        return OTHER_GIVEN_NAME;
    }

    public void setOTHER_GIVEN_NAME(String OTHER_GIVEN_NAME) {
        this.OTHER_GIVEN_NAME = OTHER_GIVEN_NAME;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public String getGENDER() {
        return GENDER;
    }

    public void setGENDER(String GENDER) {
        this.GENDER = GENDER;
    }

    public String getADDRESS_LINE_1() {
        return ADDRESS_LINE_1;
    }

    public void setADDRESS_LINE_1(String ADDRESS_LINE_1) {
        this.ADDRESS_LINE_1 = ADDRESS_LINE_1;
    }

    public String getADDRESS_LINE_2() {
        return ADDRESS_LINE_2;
    }

    public void setADDRESS_LINE_2(String ADDRESS_LINE_2) {
        this.ADDRESS_LINE_2 = ADDRESS_LINE_2;
    }

    public String getADDRESS_LINE_3() {
        return ADDRESS_LINE_3;
    }

    public void setADDRESS_LINE_3(String ADDRESS_LINE_3) {
        this.ADDRESS_LINE_3 = ADDRESS_LINE_3;
    }

    public String getADDRESS_LINE_4() {
        return ADDRESS_LINE_4;
    }

    public void setADDRESS_LINE_4(String ADDRESS_LINE_4) {
        this.ADDRESS_LINE_4 = ADDRESS_LINE_4;
    }

    public String getADDRESS_LINE_5() {
        return ADDRESS_LINE_5;
    }

    public void setADDRESS_LINE_5(String ADDRESS_LINE_5) {
        this.ADDRESS_LINE_5 = ADDRESS_LINE_5;
    }

    public String getPAF_KEY() {
        return PAF_KEY;
    }

    public void setPAF_KEY(String PAF_KEY) {
        this.PAF_KEY = PAF_KEY;
    }

    public String getSENSITIVE_FLAG() {
        return SENSITIVE_FLAG;
    }

    public void setSENSITIVE_FLAG(String SENSITIVE_FLAG) {
        this.SENSITIVE_FLAG = SENSITIVE_FLAG;
    }

    public String getPRIMARY_CARE_CODE() {
        return PRIMARY_CARE_CODE;
    }

    public void setPRIMARY_CARE_CODE(String PRIMARY_CARE_CODE) {
        this.PRIMARY_CARE_CODE = PRIMARY_CARE_CODE;
    }

    public String getREF_ID() {
        return REF_ID;
    }

    public void setREF_ID(String REF_ID) {
        this.REF_ID = REF_ID;
    }

    public String getPOST_CODE() {
        return POST_CODE;
    }

    public void setPOST_CODE(String POST_CODE) {
        this.POST_CODE = POST_CODE;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }
}
