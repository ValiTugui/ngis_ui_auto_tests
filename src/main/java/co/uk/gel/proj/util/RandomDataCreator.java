package co.uk.gel.proj.util;

import co.uk.gel.csvmodels.SpineDataModelFromCSV;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.apache.commons.text.RandomStringGenerator;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class RandomDataCreator {


    public static String getRandomAplhabetsOfGivenSize(int size) {
        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('a', 'z').build();
        String randomWord = "Test" + generator.generate(size);
        return randomWord;
    }

    public static String getRandomNumberOfGivenSize(int size) {
        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('1', '9').build();
        String randomNumbers = generator.generate(size);
        return randomNumbers;
    }


    public static String getRandomPrefix() {
        String[] allPrefix = {"Mr", "Mrs", "Miss", "Master", "Ms"};
        int generator = RandomDataCreator.getRandomNumberRange(0, allPrefix.length - 1);
        String prefix = allPrefix[generator];
        return prefix;
    }


    public static String getRandomPostCode() {
        String[] allPostcode = {"AB1 0AB", "AL10 0QQ", "B1 1TA", "B99 1DY", "CA1 1AA", "CA28 6AQ", "DA1 1AQ", "DA1 1AQ",
                "E1 0SF", "E20 3PS"};
        int generator = RandomDataCreator.getRandomNumberRange(0, allPostcode.length - 1);
        //int index = Integer.parseInt(generator.generate(1));
        String postCode = allPostcode[generator];
        return postCode;
    }

    public static int getRandomNumberRange(int lowerLimit, int upperLimit) {
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(upperLimit) + lowerLimit;
        System.out.println("Random number generated is : " + randomInt);
        return randomInt;
    }


    public static void main(String[] args) {


        System.out.println(RandomDataCreator.getRandomAplhabetsOfGivenSize(10));
        System.out.println(RandomDataCreator.getRandomNumberOfGivenSize(11));
        System.out.println(RandomDataCreator.getRandomPrefix());
        System.out.println(RandomDataCreator.getRandomNumberRange(3, 125));
        //currentDate();
    }


    public static String currentDate(int days) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        //String requiredDate = df.format(new Date((System.currentTimeMillis()-315569260000l))).toString();
        //System.out.println(requiredDate);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, days);
        return df.format(cal.getTime());
    }


    public static String generateRandomNHSNumber() {

        boolean flagOfNHSNUmberValidity = false;
        String generatedNHSNumber = "";
        do {
            generatedNHSNumber = String.valueOf(Long.parseLong(RandomDataCreator.getRandomNumberOfGivenSize(10)));
            flagOfNHSNUmberValidity = checkForValidityOfNHSNumber(generatedNHSNumber);

        } while (!flagOfNHSNUmberValidity);

        System.out.println("Generated Valid NHSNumber " + generatedNHSNumber);
        return generatedNHSNumber;
    }

    public static boolean checkForValidityOfNHSNumber(String baseNHSNumber) {

        List<Long> digitOfNHS = new ArrayList<Long>();
        Long baseNHSNumberL = Long.parseLong(baseNHSNumber.replaceAll("\\s+", ""));
        for (int i = 0; i < 10; i++) {
            digitOfNHS.add(baseNHSNumberL % 10);
            baseNHSNumberL = baseNHSNumberL / 10;
        }
        Collections.reverse(digitOfNHS);
        Long sumOfdigitOfNHS = 0L;
        for (int i = 0, j = 10; i < 9; i++) {
            sumOfdigitOfNHS = sumOfdigitOfNHS + digitOfNHS.get(i) * (j - i);
        }
        Long tenthDigit = digitOfNHS.get(9) * 1;

        Long reminderOfElevenNum = sumOfdigitOfNHS % 11;
        Long substractReminderByEleven = 11 - reminderOfElevenNum;
        if (substractReminderByEleven == 10) {
            return false;
        }
        if (substractReminderByEleven == 11) {
            if (tenthDigit == 0) {
                return true;
            } else {
                return false;
            }
        }
        if (substractReminderByEleven == tenthDigit) {
            return true;
        } else {
            return false;
        }
    }

    public static String getAnyNHSNumberInSpineCSV() throws IOException {
        return getAnyNHSDataFromSpineCSV().getNHS_NUMBER();
    }

    public static SpineDataModelFromCSV getAnyNHSDataFromSpineCSV(String NHSNumberInCSV) throws IOException {
        int randomNum = ThreadLocalRandom.current().nextInt(1, 650 + 1);
        int counterOfRandomNumber = 0;
        SpineDataModelFromCSV myPatientData = null;

        try (
                Reader reader = Files.newBufferedReader(Paths.get("All_PDS_data_InCSVFormat.csv"));
        ) {
            ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
            strategy.setType(SpineDataModelFromCSV.class);
            String[] memberFieldsToBindTo = {"testRef", "NHS_NUMBER", "DATE_OF_BIRTH", "DATE_OF_DEATH", "FAMILY_NAME", "GIVEN_NAME", "OTHER_GIVEN_NAME", "TITLE", "GENDER", "ADDRESS_LINE_1", "ADDRESS_LINE_2", "ADDRESS_LINE_3", "ADDRESS_LINE_4", "ADDRESS_LINE_5", "PAF_KEY", "SENSITIVE_FLAG", "PRIMARY_CARE_CODE", "REF_ID", "POST_CODE", "Notes"
            };
            strategy.setColumnMapping(memberFieldsToBindTo);

            CsvToBean<SpineDataModelFromCSV> csvToBean = new CsvToBeanBuilder(reader)
                    .withMappingStrategy(strategy)
                    .withSkipLines(1)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            Iterator<SpineDataModelFromCSV> mySpineDataIterator = csvToBean.iterator();
            while (mySpineDataIterator.hasNext()) {
                if (NHSNumberInCSV == null || NHSNumberInCSV.isEmpty()) {

                }
                if ((counterOfRandomNumber == randomNum) && (NHSNumberInCSV == null || NHSNumberInCSV.isEmpty())) {
                    myPatientData = mySpineDataIterator.next();
                    break;
                }
                if (!(NHSNumberInCSV == null || NHSNumberInCSV.isEmpty())) {
                    myPatientData = mySpineDataIterator.next();
                    if (myPatientData.getNHS_NUMBER().equalsIgnoreCase(NHSNumberInCSV)) {
                        break;
                    }
                }
                mySpineDataIterator.next();
                counterOfRandomNumber++;
            }
        }

        return myPatientData;
    }

    public static SpineDataModelFromCSV getAnyNHSDataFromSpineCSV() throws IOException {
        return getAnyNHSDataFromSpineCSV("");
    }

}
