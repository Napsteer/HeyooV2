/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package my.heyoo.models;

import java.io.IOException;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;
import my.heyoo.messages.Message;
import my.heyoo.messages.SystemMessage;
import my.heyoo.messages.TextMessage;

/**
 * 
 * @author AdministratorJa
 */
public abstract class AbstractSocketModel extends Observable implements Observer{
    
    public InputStreamModel inputStreamModel = null;
    public OutputStreamModel outputStreamModel = null;
    public Socket socket = null;
    
    public AbstractSocketModel(){
    }
    
    public void CreateStreams()
    {
        outputStreamModel = new OutputStreamModel(socket);
        inputStreamModel = new InputStreamModel(socket);
        inputStreamModel.addObserver(this);
        new Thread(inputStreamModel).start();
        System.out.println("Input & output threads have been created!");
    }
    
    public void SendMessage(String type, String text, String sender)
    {
        if (inputStreamModel == null || outputStreamModel == null) return; //TODO zapisywanie wiadomości do wysłania
        Message message;
        if (type.equals(Message.MESSAGE_TYPE_SYSTEM))
        {
            message = new SystemMessage(text);
        }
        else
        {
            message = new TextMessage(text, sender);
        }
        outputStreamModel.SendMessage(message);
    }
    
    public void Connect() throws IOException {

    }

    @Override
    public void update(Observable observable, Object object) {

    }
}
