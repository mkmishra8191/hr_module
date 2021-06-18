package tech.getarrays.employeemanager.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.itextpdf.text.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import tech.getarrays.employeemanager.model.Employee;


@Component
public class PDFGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(PDFGenerator.class);

    public Document writePDF(Employee employee, OutputStream outputStream) throws DocumentException {
        LOGGER.info("Inside generateItinerary()");
        PdfPTable table = generateTable(employee);
        table.setTotalWidth(200);
        table.setLockedWidth(true);
        Rectangle one = new Rectangle(table.getTotalWidth()+16,table.getTotalHeight()+40);




        Document document = new Document(one,8,8,20,20);

            PdfWriter.getInstance(document, outputStream);
            document.open();
            document.add(table);
            document.close();

            return document;

    }

    public PdfPTable generateTable(Employee employee) {

        PdfPTable table = new PdfPTable(2);
        table.setTotalWidth(400f);
        table.setLockedWidth( true);

        PdfPCell cell;

        cell = new PdfPCell(new Phrase("Employee Details"));

        cell.setColspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        table.addCell("Employee ID");
        table.addCell(String.valueOf(employee.getId()));

        table.addCell("Employee Name");
        table.addCell(employee.getName());

        table.addCell("salary");
        table.addCell("?");



        return table;
    }

}