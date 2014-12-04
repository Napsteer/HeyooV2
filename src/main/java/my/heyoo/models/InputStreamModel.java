/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package my.heyoo.models;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Observable;
import javax.swing.JOptionPane;

/**
 *
 * @author AdministratorJa
 */
public class InputStreamModel extends Observable implements Runnable{
    
    private ObjectInputStream objectInputStream;
    private Object inputLine = null;
    private boolean active;
    
    /**
     * Creates input stream using given socket.
     * Has to be run as new thread.
     * 
     * @param socket socket used to generate input stream.
     */
    protected InputStreamModel(Socket socket)
    {
        try
        {
            this.objectInputStream = new ObjectInputStream(socket.getInputStream());
        }
        catch(IOException e)
        {
            System.out.println("IO error while creating input stream!");
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Połączenie zerwane. Spróbuj uruchomić aplikację ponownie.");
            System.exit(10);
        }
        active = true;
    }    
    
    public void setActive(boolean active) {
        this.active = active;
    }
    
    @Override
    public void run()
    {
        while(true)
        {
            try
            {
                inputLine = objectInputStream.readObject();
            }
            catch(IOException | ClassNotFoundException e)
            {
                System.out.println("IO error while reading from socket!");
                System.out.println(e.getMessage());
                JOptionPane.showMessageDialog(null, "Połączenie zerwane. Spróbuj uruchomić aplikację ponownie.");
                System.exit(10);
            }
            setChanged();
            notifyObservers(inputLine);
            if (active == false) return;
        }
    }
}
