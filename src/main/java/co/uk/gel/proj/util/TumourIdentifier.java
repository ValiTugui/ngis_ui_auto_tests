package co.uk.gel.proj.util;

import io.cucumber.java.After;

public class TumourIdentifier {

    private static String tumourDescription;

    public static void setTumourDescriptionForStep(String newTumourDescription) {
        tumourDescription = newTumourDescription;
        Debugger.println("Set new tumour Description: " + tumourDescription);
    }

    public static String getTumourDescriptionForStep() {
        Debugger.println("Get new tumour Description: " + tumourDescription);
        return tumourDescription;
    }


    public static String resetTumourDescriptionForStep() {
        tumourDescription = null;
        Debugger.println("re-set tumour description after test to null: " + tumourDescription);
        return tumourDescription;
    }
}

