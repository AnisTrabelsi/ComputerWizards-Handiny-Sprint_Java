import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SmsSender {
  // Your Account SID from twilio.com/console
  public static final String ACCOUNT_SID = "AC2cf3b48480710e75f659adfb8932f0f2";
  // Your Auth Token from twilio.com/console
  public static final String AUTH_TOKEN = "830a31796d5e9844b56c19cb24d4ea09";

  public static void sendSms(String recipient, String messageBody) {
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

    Message message = Message.creator(
            new PhoneNumber("+21624076282"), // To number
            new PhoneNumber("++15673716202"), // From number
            messageBody) // SMS body
        .create();

    System.out.println("Message sent: " + message.getSid());
  }
}
