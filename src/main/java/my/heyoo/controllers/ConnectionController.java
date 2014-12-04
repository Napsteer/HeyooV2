/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package my.heyoo.controllers;

import java.io.IOException;
import javax.swing.JOptionPane;
import my.heyoo.ConfigFile;
import my.heyoo.messages.Message;
import my.heyoo.models.AbstractSocketModel;
import my.heyoo.models.ServerSocketModel;
import my.heyoo.models.SocketModel;
import my.heyoo.views.UserGUI;

/**
 *
 * @author AdministratorJa
 */
public class ConnectionController {
    
    private UserGUI userGUI = new UserGUI();
    private AbstractSocketModel socket = null;
    private final ConfigFile config;

    public ConnectionController() {
        config = ConfigFile.GetInstance();
        InitGUI();
        GetConnection();
    }

    private void InitGUI() {
        userGUI.setVisible(true);
        userGUI.setConnectionController(this);
    }
    
    private void GetConnection()
    {
        try
        {
            socket = new SocketModel(Integer.valueOf(config.getProperties().getProperty("port")),config.getProperties().getProperty("host"));
            socket.Connect();
        }
        catch(IOException e)
        {
            System.out.println("Couldn't connect to server. Setting up server socket.");
            System.out.println(e.getMessage());
            socket = new ServerSocketModel(Integer.valueOf(config.getProperties().getProperty("port")));
            try
            {
                socket.Connect();
            }
            catch(IOException ex)
            {
                System.out.println("Couldn't set up a server! Shutting down the application!");
                System.out.println(ex.getMessage());
                JOptionPane.showMessageDialog(null, "Nie można utworzyć servera! Sprawdź czy aplikacja nie jest już włączona!");
                System.exit(10);
            }
        }
        socket.addObserver(userGUI);
    }
    
    public void CheckSocketType()
    {
        if (!(socket instanceof ServerSocketModel))
        {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                
            }
            socket = new ServerSocketModel(Integer.valueOf(config.getProperties().getProperty("port")));
            try
            {
                socket.Connect();
            }
            catch(IOException e)
            {
                System.out.println("Couldn't set up a server! Shutting down the application!");
                System.out.println(e.getMessage());
                JOptionPane.showMessageDialog(null, "Nie można utworzyć servera! Sprawdź czy aplikacja nie jest już włączona!");
                System.exit(10);
            }
            socket.addObserver(userGUI);
        }
    }
    
    public void SendTextMessage(String text, String sender)
    {
        socket.SendMessage(Message.MESSAGE_TYPE_TEXT, text, sender);
    }
    
    public void SendSystemMessage(String text)
    {
        //if (text.equals(Message.MESSAGE_CLOSE_SOCKET) && socket instanceof ServerSocketModel) return;
        socket.SendMessage(Message.MESSAGE_TYPE_SYSTEM, text, null);
    }

}
