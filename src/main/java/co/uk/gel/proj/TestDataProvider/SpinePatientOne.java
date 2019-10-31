package co.uk.gel.proj.TestDataProvider;

import co.uk.gel.proj.util.TestUtils;

public class SpinePatientOne {

    public static final String FIRST_NAME = "NELLY";;
    public static final String LAST_NAME = "STAMBUKDELIFSCHITZ";
    public static final String TITLE = "MRS";
    public static final String FULL_NAME = LAST_NAME + ", " + FIRST_NAME + " (" + TITLE +  ")";
    public static final String DAY_OF_BIRTH = "23";
    public static final String MONTH_OF_BIRTH = "03";
    public static final String YEAR_OF_BIRTH = "2011";
    public static final String DATE_OF_BIRTH = DAY_OF_BIRTH + "-" + TestUtils.convertMonthNumberToMonthForm(MONTH_OF_BIRTH)
                                            + "-" + YEAR_OF_BIRTH;
    public static final String GENDER = "Female";
    public static final String LIFE_STATUS = "Alive";
    public static final String NHS_NUMBER = "9449310602";
    public static final String ADDRESS_LINE1 = "4 HAYWARD ROAD";
    public static final String ADDRESS_LINE2 = "THAMES DITTON";
    public static final String ADDRESS_LINE3 = "SURREY";
    public static final String ADDRESS_LINE4 = "";
    public static final String ADDRESS_LINE5 = "";
    public static final String POST_CODE = "KT7 0BE";
    public static final String FULL_ADDRESS = "Address " + ADDRESS_LINE1 + ", " + ADDRESS_LINE2 +
                               ", " + ADDRESS_LINE3 + ", " + POST_CODE;
}
