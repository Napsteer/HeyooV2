/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package my.heyoo.messages;


/**
 *
 * @author AdministratorJa
 */
public interface Message {
    public static final String MESSAGE_TYPE_TEXT = "text";
    public static final String MESSAGE_TYPE_SYSTEM = "system";
    public static final String MESSAGE_CLOSE_SOCKET = "close";
    public static final String MESSAGE_VERIFICATION = "verification";
    public static final String MESSAGE_OPEN_SOCKET = "open";
    
    public String getMessageType();
    public String getMessage();
}
