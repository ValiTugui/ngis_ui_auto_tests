package co.uk.gel.proj.TestDataProvider;

import co.uk.gel.proj.util.TestUtils;

public class NgisPatientTwo {

    public static final String FIRST_NAME = "CONNER";
    public static final String LAST_NAME = "DAROLD";
    public static final String TITLE = "MR";
    public static final String FULL_NAME = LAST_NAME + ", " + FIRST_NAME + " (" + TITLE +  ")";
    public static final String DAY_OF_BIRTH = "14";
    public static final String MONTH_OF_BIRTH = "05";
    public static final String YEAR_OF_BIRTH = "2004";
    public static final String DATE_OF_BIRTH = DAY_OF_BIRTH + "-" + TestUtils.convertMonthNumberToMonthForm(MONTH_OF_BIRTH)
                                            + "-" + YEAR_OF_BIRTH;
    public static final String GENDER = "Male";
    public static final String LIFE_STATUS = "Alive";
    public static final String NHS_NUMBER = "9449303924";
    public static final String ADDRESS_LINE1 = "";
    public static final String ADDRESS_LINE2 = "10 LAKESIDE DRIVE";
    public static final String ADDRESS_LINE3 = "";
    public static final String ADDRESS_LINE4 = "ESHER";
    public static final String ADDRESS_LINE5 = "SURREY";
    public static final String POST_CODE = "KT10 9EZ";
    public static final String FULL_ADDRESS = "Address " + ADDRESS_LINE1 + "" + ADDRESS_LINE2 +
                               ", " + ADDRESS_LINE3 + "" + ADDRESS_LINE4 + ", " + ADDRESS_LINE5 + ", " + POST_CODE;
}
