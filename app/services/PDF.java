package services;


import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;

import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Tab;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;
import models.FinalReportTable;
import models.FinalSiteInfo;


import java.io.ByteArrayOutputStream;
import java.util.List;


public class PDF
{
    public static byte[] getPDF(List<FinalReportTable> finalReportValues, FinalSiteInfo finalSiteInfo)
    {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(output);
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument, PageSize.A4.rotate());

        Text space = new Text("\n");
        Tab tab = new Tab();

        Text reportHeader = new Text("Final Artifact Table");
        reportHeader.setFontSize(20);
        reportHeader.setBold();

        Paragraph paragraph = new Paragraph();
        paragraph.add(reportHeader);
        paragraph.add(space);

        Text projectNumberHeading = new Text("Project Number:  ");
        projectNumberHeading.setFontSize(12);
        projectNumberHeading.setBold();
        paragraph.add(projectNumberHeading);
        Text projectNumber = new Text(finalSiteInfo.getProjectNumber());
        projectNumber.setFontSize(12);
        paragraph.add(projectNumber);
        paragraph.add(tab);
        paragraph.add(tab);
        paragraph.add(tab);

        Text stateSiteNumberHeader = new Text("State Site Number:  ");
        stateSiteNumberHeader.setFontSize(12);
        stateSiteNumberHeader.setBold();
        paragraph.add(stateSiteNumberHeader);
        Text stateSiteNumber = new Text(finalSiteInfo.getStateSiteNumber());
        stateSiteNumber.setFontSize(12);
        paragraph.add(stateSiteNumber);
        paragraph.add(space);

        Text accessionNumberHeader = new Text("Accession Number:  ");
        accessionNumberHeader.setFontSize(12);
        accessionNumberHeader.setBold();
        paragraph.add(accessionNumberHeader);
        Text accessionNumber = new Text(finalSiteInfo.getAccessionNumber());
        accessionNumber.setFontSize(12);
        paragraph.add(accessionNumber);
        paragraph.add(tab);
        paragraph.add(tab);


        Text countyHeader = new Text("County:  ");
        countyHeader.setFontSize(12);
        countyHeader.setBold();
        paragraph.add(countyHeader);
        Text county = new Text(finalSiteInfo.getCountyName());
        county.setFontSize(12);
        paragraph.add(county);
        paragraph.add(tab);
        paragraph.add(tab);
        paragraph.add(tab);


        Text stateHeader = new Text("State:  ");
        stateHeader.setFontSize(12);
        stateHeader.setBold();
        paragraph.add(stateHeader);
        Text state = new Text(finalSiteInfo.getStateId());
        state.setFontSize(12);
        paragraph.add(state);


        Table table = new Table(13);
        table.setTextAlignment(TextAlignment.CENTER);
        table.addHeaderCell("FSN");
        table.addHeaderCell("Area");
        table.addHeaderCell("Unit Number");
        table.addHeaderCell("Depth\n(cmbd)");
        table.addHeaderCell("LSN");
        table.addHeaderCell("ASN1");
        table.addHeaderCell("ASN2");
        table.addHeaderCell("ASN3");
        table.addHeaderCell("Catalog Number");
        table.addHeaderCell("Quantity");
        table.addHeaderCell("Weight\n(g)");
        table.addHeaderCell("Date Analyzed");
        table.addHeaderCell("Lab\nTechnician");

        for(FinalReportTable finalReportTable : finalReportValues)
        {
            String fsnString = Integer.toString(finalReportTable.getFieldSiteNumber());
            table.addCell(fsnString);
            table.addCell(finalReportTable.getAreaLetter());
            String unitNumberString = Integer.toString(finalReportTable.getUnitNumber());
            table.addCell(unitNumberString);
            String depth = Integer.toString(finalReportTable.getStartDepth()) + "-" + Integer.toString(finalReportTable.getEndDepth());
            table.addCell(depth);
            table.addCell(finalReportTable.getLSNName());
            table.addCell(finalReportTable.getASN1Name());
            table.addCell(finalReportTable.getASN2Name());
            if(finalReportTable.getASN3id() == null)
            {
                table.addCell("");
            }
            else
            {
                table.addCell(finalReportTable.getASN3Name());
            }
            table.addCell(finalSiteInfo.getAccessionNumber() + "-" + finalReportTable.getCatalogSuffix());
            String quantityString = Integer.toString(finalReportTable.getQuantity());
            table.addCell(quantityString);
            String weightString = finalReportTable.getWeight().toString();
            table.addCell(weightString);
            String dateString = finalReportTable.getDateAnalyzed().toString();
            table.addCell(dateString);
            table.addCell(finalReportTable.getLabTechnicianInitials());


        }


        document.setFontSize(8);
        document.add(paragraph);
        document.add(table);
        document.close();



        return output.toByteArray();
    }


}
