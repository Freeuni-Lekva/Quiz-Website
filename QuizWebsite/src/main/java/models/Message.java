package models;

import java.util.ArrayList;
import java.util.Date;

public interface Message {

    String getMessage(int messageId);

    void deleteMessage(int messageId);

    ArrayList<String> getMessages(String username);

}
