package tech.getarrays.employeemanager.service;




        import antlr.ASTNULLType;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.scheduling.annotation.EnableScheduling;
        import org.springframework.scheduling.annotation.Scheduled;
        import org.springframework.stereotype.Service;
        import tech.getarrays.employeemanager.model.Employee;
        import tech.getarrays.employeemanager.repo.EmployeeRepo;
        import tech.getarrays.employeemanager.util.EmailUtil;

        import java.io.Serializable;
        import java.util.List;
        import java.util.Properties;

        import javax.mail.Authenticator;
        import javax.mail.Message;
        import javax.mail.PasswordAuthentication;
        import javax.mail.Session;
        import javax.mail.Transport;
        import javax.mail.internet.InternetAddress;
        import javax.mail.internet.MimeMessage;
        import javax.transaction.Transactional;

@Service
@Transactional
@Configuration
@EnableScheduling

public class EmailService implements Serializable {

    private static EmployeeRepo employeeRepo;

    private static EmailUtil pdf;


@Autowired
    public EmailService(EmployeeRepo employeeRepo, EmailUtil pdf) {
        this.employeeRepo = employeeRepo;
        this.pdf = pdf;
    }



    //this is responsible to send the message with attachment
    @Scheduled(cron = "${mailer.job.scheduled.time.cron}")

    public void sendAttach( ) {

        //Variable for gmail
        String host="smtp.gmail.com";
        String subject= "Salary Slip";

        String from="manjeet.eng8191@gmail.com";

        //get the system properties
        Properties properties = System.getProperties();
        System.out.println("PROPERTIES "+properties);

        //setting important information to properties object

        //host set
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port","465");
        properties.put("mail.smtp.ssl.enable","true");
        properties.put("mail.smtp.auth","true");

        //Step 1: to get the session object..
        Session session=Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("manjeet.eng8191@gmail.com", "Jan@2021");
            }



        });

        session.setDebug(true);

        //Step 2 : compose the message [text,multi media]
        MimeMessage m = new MimeMessage(session);
        List<Employee> employees = employeeRepo.findAll();
        employees.forEach(employee -> {

            try {

                //from email
                m.setFrom(from);



                //adding recipient to message
                m.addRecipient(Message.RecipientType.TO, new InternetAddress(employee.getEmail()));

                //adding subject to message
                m.setSubject(subject);



                EmailUtil emailUtil = new EmailUtil();
                m.setContent(emailUtil.sendEmail(employee));


                //send

                //Step 3 : send the message using Transport class
                Transport.send(m);


            } catch (Exception e) {
                e.printStackTrace();
            }


            System.out.println("Sent success...................");

        }  );
    }
    public void RequestStatus(Employee employee ) {

        //Variable for gmail
        String host="smtp.gmail.com";
        String subject= "Leave Request";

        String from="manjeet.eng8191@gmail.com";

        //get the system properties
        Properties properties = System.getProperties();
        System.out.println("PROPERTIES "+properties);

        //setting important information to properties object

        //host set
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port","465");
        properties.put("mail.smtp.ssl.enable","true");
        properties.put("mail.smtp.auth","true");

        //Step 1: to get the session object..
        Session session=Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("manjeet.eng8191@gmail.com", "Army@8191");
            }



        });

        session.setDebug(true);

        //Step 2 : compose the message [text,multi media]
        MimeMessage m = new MimeMessage(session);


            try {

                //from email
                m.setFrom(from);



                //adding recipient to message
                m.addRecipient(Message.RecipientType.TO, new InternetAddress(employee.getReportingTo().getEmail()));

                //adding subject to message
                m.setSubject(subject);



                EmailUtil emailUtil = new EmailUtil();
                m.setContent(emailUtil.sendRequest(employee));


                //send

                //Step 3 : send the message using Transport class
                Transport.send(m);


            } catch (Exception e) {
                e.printStackTrace();
            }


            System.out.println("Sent success...................");


    }

}