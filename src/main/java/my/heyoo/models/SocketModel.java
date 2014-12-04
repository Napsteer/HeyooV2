/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.heyoo.models;

import java.io.IOException;
import java.net.Socket;
import java.util.Observable;
import javax.swing.JOptionPane;
import my.heyoo.messages.Message;
import my.heyoo.messages.SystemMessage;

/**
 *
 * @author AdministratorJa
 */
public class SocketModel extends AbstractSocketModel {

    private final int portNumber;
    private final String hostName;

    public SocketModel(int portNumber, String hostName) {
        this.portNumber = portNumber;
        this.hostName = hostName;
    }
    
    /**
     * Tries to connect to server.
     * 
     * @throws IOException
     */
    @Override
    public void Connect() throws IOException
    {
        socket = new Socket(hostName, portNumber);
        System.out.println("Successfully connected to the server!");
        CreateStreams();
    }
    
    private void ClearSocket()
    {
            socket = null;
            inputStreamModel.setActive(false);
            inputStreamModel = null;
            outputStreamModel = null;
            System.out.println("Socket and streams cleared!");
    }
    
    @Override
    public void update(Observable observable, Object object)
    {
        Message message = (Message) object;
        setChanged();
        if (Message.MESSAGE_TYPE_TEXT.equals(message.getMessageType()))
        {
            notifyObservers(object);
        }
        if (Message.MESSAGE_TYPE_SYSTEM.equals(message.getMessageType()))
        {
            switch(message.getMessage())
            {
                case Message.MESSAGE_CLOSE_SOCKET:
                    ClearSocket();
                    notifyObservers(object);
                    break;
                case Message.MESSAGE_VERIFICATION:
                    SendMessage(Message.MESSAGE_TYPE_SYSTEM, Message.MESSAGE_VERIFICATION, null);
                    System.out.println("Returned verification message!");
                    notifyObservers(new SystemMessage(Message.MESSAGE_OPEN_SOCKET));
                    break;
                default:
                    System.out.println("Unknown error from " + observable.toString() + " shutting down the application!");
                    JOptionPane.showMessageDialog(null, "Nieoczekiwany błąd.");
                    System.exit(1);
            }
        }
    }
}
