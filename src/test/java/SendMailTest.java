import cn.tekin.utils.Mail;
import org.junit.Before;
import org.junit.Test;

import java.security.GeneralSecurityException;

public class SendMailTest {


    @Test
    public void sendMail(){

        try {
            Mail mail=new Mail();
            mail.sendEmail("tekin@vip.qq.com","yck","ok");
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }

    }
}
