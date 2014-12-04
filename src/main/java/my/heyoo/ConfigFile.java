/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package my.heyoo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author AdministratorJa
 */
public class ConfigFile {
    private final File file;
    private Properties properties = new Properties();
    private static final ConfigFile instance = new ConfigFile();
    
    private ConfigFile()
    {
        file = new File("config/config.cfg");
        getConfiguration();
    }
    
    public static ConfigFile GetInstance()
    {
        return instance;
    }
    
    private void getConfiguration()
    {
        File directory = new File(file.getParent());
        directory.mkdir();
        if (file.exists())
        {
            ReadConfigFile(file.getPath());
        }
        else
        {
            CreateDefaultConfigFile(file.getPath());
            ReadConfigFile(file.getPath());
        }
    }
    
    
    private void CreateDefaultConfigFile(String filePath)
    {
        try (BufferedWriter output = new BufferedWriter(new FileWriter(filePath));)
        {
            properties.setProperty("port", "50000");
            properties.setProperty("host", "localhost");
            properties.setProperty("userName", "MuszeSobieUstawicNazweUzytkownika");
            properties.store(output, null);
        }
        catch(IOException e)
        {
            System.out.println("IO error while writing to config file!");
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Błąd podczas tworzenia pliku z ustawieniami.");
            System.exit(15);
        }
    }
    
    private void ReadConfigFile(String filePath)
    {
        try (BufferedReader input = new BufferedReader(new FileReader(filePath));)
        {
            properties.load(input);
        }
        catch(IOException e)
        {
            System.out.println("IO error while reading from config file!");
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Błąd podczas odczytywania pliku z ustawieniami.");
            System.exit(16);
        }
    }

    public Properties getProperties() {
        return properties;
    }
}
