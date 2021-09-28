package co.uk.gel.models;

import java.util.ArrayList;
import java.util.List;

public class ReferralsList {
    private List<ReferralDataModel> referralsList = new ArrayList<>();


    public List<ReferralDataModel> getReferralsList() {
        return referralsList;
    }

    public void setReferralsList(List<ReferralDataModel> referralsList) {
        this.referralsList = referralsList;
    }

    public void addReferralsInList(ReferralDataModel referralsData) {
        this.referralsList.add(referralsData);
    }
}
