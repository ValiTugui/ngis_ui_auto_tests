package co.uk.gel.models;

import java.util.ArrayList;
import java.util.List;

public class ReferralDataModel {

    private String referralId;
    private String caseType;
    private List<String> sampleWellIdList = new ArrayList<>();
    private String cohortId;
    private String probandId;

    public String getReferralId() {
        return referralId;
    }

    public void setReferralId(String referralId) {
        this.referralId = referralId;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public List<String> getSampleWellIdList() {
        return sampleWellIdList;
    }

    public void setSampleWellIdList(List<String> sampleWellIdList) {
        this.sampleWellIdList = sampleWellIdList;
    }

    public String getCohortId() {
        return cohortId;
    }

    public void setCohortId(String cohortId) {
        this.cohortId = cohortId;
    }

    public String getProbandId() {
        return probandId;
    }

    public void setProbandId(String probandId) {
        this.probandId = probandId;
    }
}
