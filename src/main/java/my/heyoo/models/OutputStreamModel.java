/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package my.heyoo.models;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;
import my.heyoo.messages.Message;

/**
 * @author AdministratorJa
 */
public class OutputStreamModel{
    
    private ObjectOutputStream objectOutputStream;
    
    /**
     * Creates output stream using given socket.
     * 
     * @param socket socket used to generate output stream.
     */
    protected OutputStreamModel(Socket socket)
    {
            try
            {
                this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            }
            catch(IOException e)
            {
                System.out.println("IO error while creating output stream!");
                System.out.println(e.getMessage());
                JOptionPane.showMessageDialog(null, "Połączenie zerwane. Spróbuj uruchomić aplikację ponownie.");
                System.exit(10);
            }
    }
    
    /**
     * Sends specified message object through socket.
     * 
     * @param message Object to send
     * @see TextMessage
     */
    protected void SendMessage(Message message)
    {
        try
        {
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();
            objectOutputStream.reset();
        }
        catch(IOException e)
        {
            System.out.println("IO error while writing to stream!");
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Połączenie zerwane. Spróbuj uruchomić aplikację ponownie.");
            System.exit(10);
        }
    }
}
