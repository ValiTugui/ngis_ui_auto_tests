package co.uk.gel.proj.TestDataProvider;

import co.uk.gel.proj.util.TestUtils;

public class SpinePatientTwo {

    public static final String FIRST_NAME = "GILLIAN";
    public static final String LAST_NAME = "O'HERN";
    public static final String TITLE = "MS";
    public static final String FULL_NAME = LAST_NAME + ", " + FIRST_NAME + " (" + TITLE +  ")";
    public static final String DAY_OF_BIRTH = "07";
    public static final String MONTH_OF_BIRTH = "03";
    public static final String YEAR_OF_BIRTH = "1997";
    public static final String DATE_OF_BIRTH = DAY_OF_BIRTH + "-" + TestUtils.convertMonthNumberToMonthForm(MONTH_OF_BIRTH)
                                            + "-" + YEAR_OF_BIRTH;
    public static final String GENDER = "Female";
    public static final String NHS_NUMBER = "9449303592";
    public static final String ADDRESS_LINE1 = "2 WOODLANDS CLOSE";
    public static final String ADDRESS_LINE2 = "CLAYGATE";
    public static final String ADDRESS_LINE3 = "ESHER";
    public static final String ADDRESS_LINE4 = "SURREY";
    public static final String ADDRESS_LINE5 = "";
    public static final String POST_CODE = "KT10 0JF";
    public static final String FULL_ADDRESS = "Address " + ADDRESS_LINE1 + ", " + ADDRESS_LINE2 +
                               ", " + ADDRESS_LINE3 + ", " + ADDRESS_LINE4 + ", " + POST_CODE;

}
