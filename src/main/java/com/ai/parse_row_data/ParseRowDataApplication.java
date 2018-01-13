package com.ai.parse_row_data;

import com.ai.parse_row_data.entity.MailConfigure;
import com.ai.parse_row_data.entity.MailInfo;
import com.ai.parse_row_data.service.impl.Bean2DbServiceImpl;
import com.ai.parse_row_data.service.impl.ParseMail2BeanServerImpl;
import com.ai.parse_row_data.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;


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
                            String[] str = content.split("\\r\\n");
                            List<String> portfolios=new ArrayList<>();
                            List<String> userTrades=new ArrayList<>();
                            String userAction="";
                            for(String p : str){
                                if(p.contains("baseline (market index)   date")){
                                    //TODO 此处需要根据类型进行修改，假设下一次传过来的数据包含之前的历史数据，就需要进行放重读存放
                                    parseMail2BeanServer.parse2BaseLines(StringUtil.clearEmpty(p));
                                }else if(p.contains("Portfolio for")){
                                    portfolios.add(StringUtil.clearEmpty(p));
                                }else if(p.contains("Details for closed")){
                                    userTrades.add(StringUtil.clearEmpty(p));
                                }else if(p.contains("Tomorrows Plan")){
                                    parseMail2BeanServer.parse2Plans(StringUtil.clearEmpty(p));
                                }else if(p.contains("Todays Trades ------date")){
                                    userAction=StringUtil.clearEmpty(p);
                                }
                            }
                            parseMail2BeanServer.parse2UserTrades(userTrades,userAction);
                            parseMail2BeanServer.parse2UserPortfolios(portfolios);
                            System.out.println("解析已经完成");
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
