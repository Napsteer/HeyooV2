/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package my.heyoo.models;

/**
 *
 * @author AdministratorJa
 */

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Observable;
import javax.swing.JOptionPane;
import my.heyoo.messages.Message;
import my.heyoo.messages.SystemMessage;

public class ServerSocketModel extends AbstractSocketModel{
    
    private ServerSocket serverSocket = null;
    private final int portNumber;

    public ServerSocketModel(int portNumber) {
        this.portNumber = portNumber;
    }
    
    /**
     * Creates server socket and awaits connection.
     * 
     * @throws IOException
     */
    @Override
    public void Connect() throws IOException
    {
        serverSocket = new ServerSocket(portNumber);
        System.out.println("Server socket has been set up.");
        WaitForClient();
    }
    
    private void WaitForClient()
    {
        System.out.println("Waiting for client!");
        try
        {
        socket = serverSocket.accept();
        }
        catch(IOException e)
        {
            System.out.println("IO error while accepting socket connection!");
            System.out.println(e.getMessage());
        }
        CreateStreams();
        SendVerificationMessage();
    }
    
    private void ClearSocket()
    {
            socket = null;
            inputStreamModel.setActive(false);
            inputStreamModel = null;
            outputStreamModel = null;
            System.out.println("Socket and streams cleared!");
    }
    
    private void SendVerificationMessage()
    {
         SendMessage(Message.MESSAGE_TYPE_SYSTEM, Message.MESSAGE_VERIFICATION, null);
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
                    notifyObservers(object);
                    ClearSocket();
                    WaitForClient();
                    break;
                case Message.MESSAGE_VERIFICATION:
                    System.out.println("Verification succesfull!");
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
