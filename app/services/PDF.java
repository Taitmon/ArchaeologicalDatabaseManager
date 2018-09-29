package services;


import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.layout.Document;

import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Tab;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import models.FinalReportTable;
import models.FinalSiteInfo;


import java.io.ByteArrayOutputStream;
import java.util.List;

import static com.itextpdf.io.font.constants.StandardFonts.HELVETICA_BOLD;

public class PDF
{
    public static byte[] getPDF(List<FinalReportTable> finalReportValues, FinalSiteInfo finalSiteInfo)
    {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(output);
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument, PageSize.A4.rotate());

        Text reportHeader = new Text("Final Artifact Table");
        reportHeader.setFontSize(20);
        reportHeader.setBold();
        Text space = new Text("\n");


        Paragraph paragraph = new Paragraph();
        paragraph.add(reportHeader);
        paragraph.add(space);
        //paragraph.setTextAlignment(TextAlignment.CENTER);


        Text projectNumber = new Text("Project Number: " + finalSiteInfo.getProjectNumber());
        projectNumber.setFontSize(15);
        paragraph.add(projectNumber);

        Text stateSiteNumber = new Text("State Site Number: " + finalSiteInfo.getStateSiteNumber());
        stateSiteNumber.setFontSize(15);
        paragraph.add(space);
        paragraph.add(stateSiteNumber);



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
        table.addHeaderCell("Lab Technician");

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
            table.addCell(finalReportTable.getCatalogSuffix());
            String quantityString = Integer.toString(finalReportTable.getQuantity());
            table.addCell(quantityString);
            String weightString = finalReportTable.getWeight().toString();
            table.addCell(weightString);
            String dateString = finalReportTable.getDateAnalyzed().toString();
            table.addCell(dateString);
            table.addCell(finalReportTable.getLabTechnicianInitials());


        }





       /* try
        {
            PdfFont font = PdfFontFactory.createFont(HELVETICA_BOLD);
            paragraph.setFont(font);
        }catch (Exception e)
        {
            String blah = "blah";
        }*/

        document.setFontSize(8);
        document.add(paragraph);
        document.add(table);
        document.close();



        return output.toByteArray();
    }


}
