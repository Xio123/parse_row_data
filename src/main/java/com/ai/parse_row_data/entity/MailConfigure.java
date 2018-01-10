package com.ai.parse_row_data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import java.util.Properties;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 13:42 2018/1/9
 * @Modified By:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Configuration
public class MailConfigure {

    @Value("${mail.host}")
    String host;
    @Value("${mail.user}")
    String username;
    @Value("${mail.password}")
    String password;

    Message[] message;
    Properties properties;
    Session session;
    Store store;



    public void init() throws MessagingException {
        properties = new Properties();
        properties.setProperty("mail.pop3.host", "pop.qq.com");
        properties.setProperty("mail.pop3.port", "995");
        properties.setProperty("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.pop3.socketFactory.fallback", "true");
        properties.setProperty("mail.pop3.socketFactory.port", "995");
        session = Session.getDefaultInstance(properties, null);
        store = session.getStore("pop3");
        store.connect(host, username, password);
    }

    public Message[] createMessage() throws MessagingException {
        Folder folder = store.getFolder("INBOX");
        folder.open(Folder.READ_ONLY);
        message = folder.getMessages();
        return message;
    }

}
