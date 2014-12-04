/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package my.heyoo.messages;
import java.io.Serializable;
/**
 *
 * @author AdministratorJa
 */
public class TextMessage implements Serializable, Message{
    private final String messageType;
    private final String message;
    private final String sender;

    public TextMessage(String message, String sender) {
        this.messageType = Message.MESSAGE_TYPE_TEXT;
        this.message = message;
        this.sender = sender;
    }

    @Override
    public String getMessageType() {
        return messageType;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getSender() {
        return sender;
    }
    
}
