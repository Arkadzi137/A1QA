package Utils;

import aquality.selenium.core.logging.Logger;
import aquality.selenium.core.utilities.JsonSettingsFile;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.AndTerm;
import javax.mail.search.FlagTerm;
import javax.mail.search.FromTerm;
import javax.mail.search.SearchTerm;
import java.io.IOException;
import java.util.Properties;

public class MailUtils {

    private static final String USERNAME = new JsonSettingsFile("testConfig.json").getValue("/userEmail").toString();
    private static final String PASSWORD = new JsonSettingsFile("testConfig.json").getValue("/userPasswordForEmail").toString();
    private static final String HOST = new JsonSettingsFile("testConfig.json").getValue("/host").toString();
    private static final String MAIL_STORE_TYPE = new JsonSettingsFile("testConfig.json").getValue("/mailStoreType").toString();
    private static final String MAIL_FOLDER = "INBOX";
    private static final String MAIL_FROM = "noreply@mail.my.kaspersky.com";
    
    public static String getTextFromLatestMessage(){
        Properties properties = new Properties();

        properties.put("mail.pop3.host", HOST);
        properties.put("mail.pop3.port", "995");
        properties.put("mail.pop3.starttls.enable", "true");
        Session emailSession = Session.getDefaultInstance(properties);
        String emailText = null;
        Store store = null;
        Folder inboxFolder = null;
        try {
            Logger.getInstance().info("Connecting and opening to email box");
            store = emailSession.getStore(MAIL_STORE_TYPE);
            store.connect(HOST, USERNAME, PASSWORD);
            inboxFolder = store.getFolder(MAIL_FOLDER);
            inboxFolder.open(Folder.READ_ONLY);
            int count = inboxFolder.getMessageCount();
            for (int i=0; i<60;i++){
                Logger.getInstance().debug("Waiting a new messages");
                Thread.sleep(1000);
                if (count!=inboxFolder.getMessageCount()){
                    Logger.getInstance().info("Received new message in folder "+MAIL_FOLDER);
                    break;
                }
            }
            SearchTerm searchTerm = new AndTerm(new FromTerm(new InternetAddress(MAIL_FROM)), new FlagTerm(new Flags(Flags.Flag.SEEN),false));
            Message[] messages = inboxFolder.search(searchTerm);
            if (messages.length!=0) {
                emailText = getTextFromMessage(messages[messages.length - 1]);
            } else {
                Logger.getInstance().error("New messages didn't found");
            }
        } catch (MessagingException | InterruptedException e) {
            Logger.getInstance().error("Email box didn't opened");
            e.printStackTrace();
        } finally {
            try {
                if (inboxFolder != null) {
                    inboxFolder.close(false);
                }
                if (store != null) {
                    store.close();
                }
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
        return emailText;
    }

    private static String getTextFromMessage(Message message) {
        String result = "";
        try {
            if (message.isMimeType("text/plain")) {
                result = message.getContent().toString();
            } else if (message.isMimeType("multipart/*")) {
                MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
                result = getTextFromMimeMultipart(mimeMultipart);
            }
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static String getTextFromMimeMultipart(MimeMultipart mimeMultipart) {
        String result = "";
        try {
            int count = mimeMultipart.getCount();
            for (int i = 0; i < count; i++) {
                BodyPart bodyPart = mimeMultipart.getBodyPart(i);
                if (bodyPart.isMimeType("text/plain")) {
                    result = result + "\n" + bodyPart.getContent();
                } else if (bodyPart.isMimeType("text/html")) {
                    String html = (String) bodyPart.getContent();
                    result = result + "\n" + org.jsoup.Jsoup.parse(html).text();
                } else if (bodyPart.getContent() instanceof MimeMultipart){
                    result = result + getTextFromMimeMultipart((MimeMultipart)bodyPart.getContent());
                }
            }
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
