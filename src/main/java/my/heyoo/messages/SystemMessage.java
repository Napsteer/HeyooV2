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
public class SystemMessage implements Serializable, Message{
    private final String messageType;
    private final String message;

    public SystemMessage(String message) {
        this.messageType = Message.MESSAGE_TYPE_SYSTEM;
        this.message = message;
    }

    @Override
    public String getMessageType() {
        return messageType;
    }

    @Override
    public String getMessage() {
        return message;
    }
    
}
