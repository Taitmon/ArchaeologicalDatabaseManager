package services;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.*;

public class Email
{
    public static void sendEmail(String message)
    {
        String sender = "bxsullivan91@gmail.com";
        String receiver = "bxsullivan91@gmail.com";

        String subject = "Thank you for signing up!";

        String htmlBody = "<h1>You are awesome!</h1>";

        try
        {
            AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder
                    .standard()
                    .withRegion(Regions.US_WEST_2)
                    //.withRegion(Regions.US_EAST_1)
                    .build();

            SendEmailRequest request = new SendEmailRequest()
                    .withDestination(
                            new Destination().withToAddresses(receiver))
                    .withMessage(new Message()
                            .withBody(new Body()
                                    .withHtml(new Content()
                                            .withCharset("UTF-8").withData(htmlBody)))
                            .withSubject(new Content()
                                    .withCharset("UTF-8").withData(subject)))
                    .withSource(sender);

            client.sendEmail(request);
            System.out.println("Email sent");
        } catch (Exception e)
        {
            System.out.println("Error sending email: " + e.getMessage());
        }

    }
}