package co.uk.gel.models;

import javax.lang.model.element.Name;
import java.util.ArrayList;
import java.util.List;

public class Referrals {

    List<ReferralID> referrals = new ArrayList<>();

    public List<ReferralID> getReferrals() {
        return referrals;
    }
    public void addReferrals(ReferralID referrals) {
        this.referrals.add(referrals);
    }
}
