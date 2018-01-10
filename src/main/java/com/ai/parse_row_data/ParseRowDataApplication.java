package com.ai.parse_row_data;

import com.ai.parse_row_data.dao.entity.BaseLine;
import com.ai.parse_row_data.entity.MailConfigure;
import com.ai.parse_row_data.entity.MailInfo;
import com.ai.parse_row_data.service.impl.Bean2DbServiceImpl;
import com.ai.parse_row_data.service.impl.ParseMail2BeanServerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.math.BigDecimal;


@SpringBootApplication
public class ParseRowDataApplication implements CommandLineRunner {

    @Autowired
    private MailConfigure mailConfigure;

    @Autowired
    private ParseMail2BeanServerImpl parseMail2BeanServer;

    @Autowired
    private Bean2DbServiceImpl bean2DbService;

    public static void main(String[] args) {
        SpringApplication.run(ParseRowDataApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        mailConfigure.init();
        while (true) {
            Message[] messages = mailConfigure.createMessage();
            if (null != messages) {
                for (int i = 0; i < messages.length; i++) {
                    try {
                        MailInfo re = new MailInfo((MimeMessage) messages[i]);
                        // && DateUtil.isSameDay(new Date(), re.getSentDate()) && re.isNew()
                        if (re.getFrom().contains("longjilin")) {
                            re.getMailContent(messages[i]);
                            String content = re.getBodyText();
                            System.out.println(content);
                            BaseLine baseLine=BaseLine.builder().gain(new BigDecimal(0.2)).build();
                            bean2DbService.saveBaseLine(baseLine);
                        }
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                Thread.sleep(100000);
            }
        }
    }
}
