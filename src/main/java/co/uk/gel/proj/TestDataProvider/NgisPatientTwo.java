package co.uk.gel.proj.TestDataProvider;

import co.uk.gel.proj.util.TestUtils;

public class NgisPatientTwo {

    public static final String FIRST_NAME = "Bén";
    public static final String LAST_NAME = "O'MÜLLER";
    public static final String TITLE = "Mr";
    public static final String FULL_NAME = LAST_NAME + ", " + FIRST_NAME + " (" + TITLE +  ")";
    public static final String DAY_OF_BIRTH = "12";
    public static final String MONTH_OF_BIRTH = "12";
    public static final String YEAR_OF_BIRTH = "2012";
    public static final String DATE_OF_BIRTH = DAY_OF_BIRTH + "-" + TestUtils.convertMonthNumberToMonthForm(MONTH_OF_BIRTH)
                                            + "-" + YEAR_OF_BIRTH;
    public static final String GENDER = "Male";
    public static final String LIFE_STATUS = "Alive";
    public static final String NHS_NUMBER = "9437139229";
    public static final String ADDRESS_LINE1 = "1 Primrose St";
    public static final String ADDRESS_LINE2 = "Spitalfields";
    public static final String ADDRESS_LINE3 = "London";
    public static final String ADDRESS_LINE4 = "England";
    public static final String ADDRESS_LINE5 = "United Kingdom";
    public static final String POST_CODE = "EC2A 2EX";
    public static final String FULL_ADDRESS = "Address " + ADDRESS_LINE1 + ", " + ADDRESS_LINE2 +
                               ", " + ADDRESS_LINE3 + ", " + ADDRESS_LINE4 + ", " + ADDRESS_LINE5 + ", " + POST_CODE;
}
