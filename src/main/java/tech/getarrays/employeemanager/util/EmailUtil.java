package tech.getarrays.employeemanager.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.getarrays.employeemanager.model.Employee;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.io.ByteArrayOutputStream;
@Component

public class EmailUtil {

    public MimeMultipart sendEmail(Employee employee) {
        MimeMultipart mimeMultipart = new MimeMultipart();

        ByteArrayOutputStream outputStream = null;

        try {
            //construct the text body part
            MimeBodyPart textBodyPart = new MimeBodyPart();

            //now write the PDF content to the output stream
            outputStream = new ByteArrayOutputStream();
            PDFGenerator pdfGenerator = new PDFGenerator();
            pdfGenerator.writePDF(employee,outputStream);
            byte[] bytes = outputStream.toByteArray();

            //construct the pdf body part
            DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
            textBodyPart.setText("PFA");
            MimeBodyPart pdfBodyPart = new MimeBodyPart();
            pdfBodyPart.setDataHandler(new DataHandler(dataSource));
            pdfBodyPart.setFileName("test.pdf");

            //construct the mime multi part

            mimeMultipart.addBodyPart(textBodyPart);
            mimeMultipart.addBodyPart(pdfBodyPart);

            //create the sender/recipient addresses

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            //clean off
            if (null != outputStream) {
                try {
                    outputStream.close();
                    outputStream = null;
                } catch (Exception ex) {
                }
            }
        }
        return mimeMultipart;
    }
    public MimeMultipart sendRequest(Employee employee) {
        MimeMultipart mimeMultipart = new MimeMultipart();

        ByteArrayOutputStream outputStream = null;

        try {
            //construct the text body part
            MimeBodyPart textBodyPart = new MimeBodyPart();

            //now write the PDF content to the output stream

            //construct the pdf body part

            textBodyPart.setText(employee.getName()+" has applied for Leaves");

            //construct the mime multi part

            mimeMultipart.addBodyPart(textBodyPart);


            //create the sender/recipient addresses

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            //clean off
            if (null != outputStream) {
                try {
                    outputStream.close();
                    outputStream = null;
                } catch (Exception ex) {
                }
            }
        }
        return mimeMultipart;
    }
}
