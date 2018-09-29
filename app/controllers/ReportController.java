package controllers;

import models.*;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import services.PDF;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class ReportController extends Controller
{
    private JPAApi jpaApi;
    private FormFactory formFactory;

    @Inject
    public ReportController(JPAApi jpaApi, FormFactory formFactory)
    {
        this.formFactory = formFactory;
        this.jpaApi = jpaApi;
    }

    @Transactional(readOnly = true)
    public Result getHeatModifiedChart()
    {
        String sql = "SELECT NEW models.ASN3Count(COALESCE(a.ASN3Id, 0), COALESCE(j.ASN3Name, 'Not modified'), SUM(a.quantity) AS numberOfArtifacts) FROM Artifact a " +
                "LEFT OUTER JOIN ASN3 j ON j.ASN3Id = a.ASN3Id " +
                "WHERE a.LSNId = 1 " +
                "GROUP BY a.ASN3Id";

        List<ASN3Count> asn3Counts = jpaApi.em().createQuery(sql, ASN3Count.class).getResultList();

        ChartValues pieChartValues = new ChartValues();

        String data = asn3Counts.stream()
                .map(asn3Count -> Long.toString(asn3Count.getNumberOfArtifacts()))
                .collect(Collectors.joining(","));
        pieChartValues.setData(data);

        String labels = asn3Counts.stream()
                .map(asn3Count -> asn3Count.getASN3Name())
                .collect(Collectors.joining(","));
        pieChartValues.setLabels(labels);

        String rgb = asn3Counts.stream()
                .map(asn3Count -> asn3Count.getRgb())
                .collect(Collectors.joining("|"));

        pieChartValues.setColors(rgb);

        sql = "SELECT NEW models.DepthCount(u.startDepth, u.endDepth, SUM(a.quantity) AS numberOfArtifacts) FROM Artifact a " +
                "JOIN Unit u ON a.unitId = u.unitId " +
                "GROUP BY u.startDepth, u.endDepth";
        List<DepthCount> depthCounts = jpaApi.em().createQuery(sql, DepthCount.class).getResultList();

        ChartValues lineChartValues = new ChartValues();

        String lineData = depthCounts.stream()
                .map(depthCount -> Long.toString(depthCount.getNumberOfArtifacts()))
                .collect(Collectors.joining(","));
        lineChartValues.setData(lineData);

        String lineLabels = depthCounts.stream()
                .map(depthCount -> depthCount.getDepth())
                .collect(Collectors.joining(","));
        lineChartValues.setLabels(lineLabels);

        sql = "SELECT NEW models.FlakeTypeCount(a.ASN1Id, p.ASN1Name, SUM(a.quantity) AS numberOfArtifacts) FROM Artifact a " +
                "JOIN ASN1 p ON a.ASN1Id = p.ASN1Id " +
                "WHERE a.ASN1Id BETWEEN 39 and 48 " +
                "GROUP BY a.ASN1Id, p.ASN1Name";

        List<FlakeTypeCount> flakeTypeCounts = jpaApi.em().createQuery(sql, FlakeTypeCount.class).getResultList();

        ChartValues flakeChartValues = new ChartValues();

        String barData = flakeTypeCounts.stream()
                .map(flakeTypeCount -> Long.toString(flakeTypeCount.getNumberOfArtifacts()))
                .collect(Collectors.joining(","));

        flakeChartValues.setData(barData);

        String barLabels = flakeTypeCounts.stream()
                .map(flakeTypeCount -> flakeTypeCount.getASN1Name())
                .collect(Collectors.joining(","));

        flakeChartValues.setLabels(barLabels);

        return ok(views.html.reportcharts.render(pieChartValues, lineChartValues, flakeChartValues));
    }

    @Transactional
    public Result getDocument()
    {
        //siteInfo for project/demo purposes assumed 2;
        int siteInfoId = 2;

        String sql = "SELECT NEW models.FinalSiteInfo(s.siteInfoId, s.projectNumber, c.countyName, t.stateId, CONCAT(t.stateNumber, c.countyId, s.siteNumber) AS stateSiteNumber) " +
                "FROM SiteInfo s " +
                "JOIN County c ON c.countyId = s.countyId " +
                "JOIN State t ON t.stateId = c.stateId " +
                "WHERE s.siteInfoId = :siteInfoId";

        FinalSiteInfo finalSiteInfo = jpaApi.em().createQuery(sql, FinalSiteInfo.class).setParameter("siteInfoId", siteInfoId).getSingleResult();



        sql = "SELECT NEW models.FinalReportTable(a.artifactId, u.fieldSiteNumber, u.areaLetter, u.unitNumber, u.startDepth, u.endDepth, a.LSNId, w.LSNName, " +
                "a.ASN1Id, g.ASN1Name, a.ASN2Id, f.ASN2Name, a.ASN3Id, z.ASN3Name, a.quantity, a.weight, a.dateAnalyzed, l.labTechnicianInitials) FROM Artifact a " +
                "JOIN Unit u ON u.unitId = a.unitId " +
                "JOIN LabTechnician l ON l.labTechnicianId = a.labTechnicianId " +
                "JOIN LSN w ON w.LSNId = a.LSNId " +
                "JOIN ASN1 g ON g.ASN1Id = a.ASN1Id " +
                "JOIN ASN2 f ON f.ASN2Id = a.ASN2Id " +
                "LEFT OUTER JOIN ASN3 z ON z.ASN3Id = a.ASN3Id " +
                "ORDER BY u.unitId, u.startDepth";

        List<FinalReportTable> finalReportValues = jpaApi.em().createQuery(sql, FinalReportTable.class).getResultList();


        return ok(PDF.getPDF(finalReportValues, finalSiteInfo)).as("application/pdf");
    }


}
