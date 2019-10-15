package co.uk.gel.proj.TestDataProvider;

import co.uk.gel.proj.util.TestUtils;

public class NgisPatientOne {

    public static final String FIRST_NAME = "GORE";
    public static final String LAST_NAME = "PHONANAN";
    public static final String TITLE = "MR";
    public static final String FULL_NAME = LAST_NAME + ", " + FIRST_NAME + " (" + TITLE +  ")";
    public static final String DAY_OF_BIRTH = "14";
    public static final String MONTH_OF_BIRTH = "06";
    public static final String YEAR_OF_BIRTH = "2011";
    public static final String DATE_OF_BIRTH = DAY_OF_BIRTH + "-" + TestUtils.convertMonthNumberToMonthForm(MONTH_OF_BIRTH)
                                            + "-" + YEAR_OF_BIRTH;
    public static final String GENDER = "Male";
    public static final String NHS_NUMBER = "9449306680";
    public static final String ADDRESS_LINE1 = "18 WOODFIELD LANE";
    public static final String ADDRESS_LINE2 = "ASHTEAD";
    public static final String ADDRESS_LINE3 = "SURREY";
    public static final String ADDRESS_LINE4 = "";
    public static final String ADDRESS_LINE5 = "";
    public static final String POST_CODE = "KT21 2BE";
    public static final String FULL_ADDRESS = "Address " + ADDRESS_LINE1 + ", " + ADDRESS_LINE2 +
                               ", " + ADDRESS_LINE3 + ", " + POST_CODE;
}
