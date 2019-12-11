package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.Debugger;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class PrintFormSteps extends Pages {

    public PrintFormSteps(SeleniumDriver driver) {
        super(driver);
    }

     @And("the user is able to download print forms for {string} family members with the below details")
    public void theUserDownloadsPrintFormsForFamilyMembersWithTheBelowDetails(String noParticipant, DataTable inputDetails) {
        try {
            boolean testResult = false;
            int noOfParticipants = Integer.parseInt(noParticipant);
            List<List<String>> memberDetails = inputDetails.asLists();
            if(memberDetails.size() < noOfParticipants){
                Debugger.println("No of Participants mentioned and details provided are not matching.");
                return;
            }

            for (int i = 1; i < memberDetails.size(); i++) {
                Debugger.println("Downloading and Verifying content for :"+memberDetails.get(i).get(0));
                if(!printFormsPage.downloadSpecificPrintForm(memberDetails.get(i).get(0))){
                    Debugger.println("Could not download form for "+memberDetails.get(i).get(0));
                    testResult = false;
                    continue;
                }
                Debugger.println("Downloaded...Verifying content....");
                if(!printFormsPage.openAndVerifyPDFContent(memberDetails.get(i).get(0))){
                    Debugger.println("Could not verify PDF content for "+memberDetails.get(i).get(0));
                    testResult = false;
                    continue;
                }
                testResult = true;
            }//for
            Assert.assertTrue(testResult);
        }catch(Exception exp){
            Debugger.println("PrintFormSteps: Exception in downloading PrintForms: "+exp);
        }
    }

}//end
