package controllers;

import models.Artifact;
import models.Unit;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import play.Logger;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.io.Reader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

public class ImportController extends Controller
{

    private JPAApi jpaApi;

    @Inject
    public ImportController(JPAApi jpaApi)
    {
        this.jpaApi = jpaApi;
    }



    @Transactional
    public Result importUnit()
    {

        String returnMessage = "";

        String SAMPLE_CSV_FILE_PATH = "c:\\Users\\brett\\testimport.csv";
        try
        {
            Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);

            for (CSVRecord csvRecord : csvParser)
            {
                // Accessing Values by Column Index
                String unitIdString = csvRecord.get(0);
                int unitId = Integer.parseInt(unitIdString);
                String FSNString = csvRecord.get(1);
                int FSN = Integer.parseInt(FSNString);
                String area = csvRecord.get(2);
                String unitNumberString = csvRecord.get(3);
                int unitNumber = Integer.parseInt(unitNumberString);
                String startDepthString = csvRecord.get(4);
                int startDepth = Integer.parseInt(startDepthString);
                String endDepthString = csvRecord.get(5);
                int endDepth = Integer.parseInt(endDepthString);
                String siteInfoIdString = csvRecord.get(6);
                int siteInfoId = Integer.parseInt(siteInfoIdString);

                Unit unit = new Unit();

                unit.setUnitId(unitId);
                unit.setFieldSiteNumber(FSN);
                unit.setArea(area);
                unit.setUnitNumber(unitNumber);
                unit.setStartDepth(startDepth);
                unit.setEndDepth(endDepth);
                unit.setSiteInfoId(siteInfoId);

                jpaApi.em().persist(unit);

            }

            returnMessage = "success";
        }
        catch (Exception e)
        {
            Logger.error(e.getMessage());
            returnMessage = "failure";
        }

        return ok(returnMessage);
    }

    @Transactional
    public Result importArtifact()
    {
        String returnMessage = "";

        String SAMPLE_CSV_FILE_PATH = "c:\\Users\\brett\\artifacts.csv";
        try
        {
            Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);

            for (CSVRecord csvRecord : csvParser)
            {
                // Accessing Values by Column Index
                String artifactIdString = csvRecord.get(0);
                int artifactId = Integer.parseInt(artifactIdString);
                String unitIdString = csvRecord.get(1);
                int unitId = Integer.parseInt(unitIdString);
                String periodIdString = csvRecord.get(2);
                int periodId = Integer.parseInt(periodIdString);
                String LSNIdString = csvRecord.get(3);
                int LSNId = Integer.parseInt(LSNIdString);
                String ASN1IdString = csvRecord.get(4);
                int ASN1Id = Integer.parseInt(ASN1IdString);
                String ASN2IdString = csvRecord.get(5);
                int ASN2Id = Integer.parseInt(ASN2IdString);

                String ASN3IdString = csvRecord.get(6);
                int ASN3Id;
                if(ASN3IdString.isEmpty())
                {
                     ASN3Id = 22;
                }
                else
                {
                     ASN3Id = Integer.parseInt(ASN3IdString);
                }

                String additionalDescription = csvRecord.get(7);
                if(additionalDescription.isEmpty())
                {
                    additionalDescription = null;
                }
                String quantityString = csvRecord.get(8);
                int quantity = Integer.parseInt(quantityString);
                String weightString = csvRecord.get(9);
                BigDecimal weight = new BigDecimal(weightString);
                String labTechString = csvRecord.get(10);
                int labTechnicianId = Integer.parseInt(labTechString);
                String date = csvRecord.get(11);
                LocalDate dateAnalyzed = LocalDate.parse(date);


                /*
                    private int artifactId;
                    private int unitId;
                    private int periodId;
                    private int LSNId;
                    private int ASN1Id;
                    private int ASN2Id;
                    private int ASN3Id;
                    private String additionalDescription;
                    private int quantity;
                    private int weight;
                    private int labTechnicianId;
                    private LocalDate dateAnalyzed;
                  */

                Artifact artifact = new Artifact();

                artifact.setArtifactId(artifactId);
                artifact.setUnitId(unitId);
                artifact.setPeriodId(periodId);
                artifact.setLSNId(LSNId);
                artifact.setASN1Id(ASN1Id);
                artifact.setASN2Id(ASN2Id);
                artifact.setASN3Id(ASN3Id);
                artifact.setAdditionalDescription(additionalDescription);
                artifact.setQuantity(quantity);
                artifact.setWeight(weight);
                artifact.setLabTechnicianId(labTechnicianId);
                artifact.setDateAnalyzed(dateAnalyzed);


                jpaApi.em().persist(artifact);



                System.out.println("Record No - " + csvRecord.getRecordNumber());
                System.out.println("---------------");
                System.out.println("Artifact Id : " + artifactId);
                System.out.println("Unit : " + unitId);
                System.out.println("Period : " + periodId);
                System.out.println("LSN : " + LSNId);
                System.out.println("ASN1 : " + ASN1Id);
                System.out.println("ASN2 : " + ASN2Id);
                System.out.println("ASN3 : " + ASN3Id);
                System.out.println("Addition : "  + additionalDescription);
                System.out.println("Count : " + quantity);
                System.out.println("Weight : " + weight);
                System.out.println("LabTech : " + labTechnicianId);
                System.out.println("Date : " + dateAnalyzed);
                System.out.println("---------------\n\n");
            }

            returnMessage = "success";
        }
        catch (Exception e)
        {
            Logger.error(e.getMessage());
            returnMessage = "failure";
        }

        return ok(returnMessage);
    }
}
