/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package my.heyoo.views;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author AdministratorJa
 */
public class ActivityView {
    
    private JLabel label;
    private BufferedImage inactiveIcon;
    private BufferedImage activeIcon;
    private boolean active;
    
    protected ActivityView(JLabel label)
    {
        this.label = label;
        try
        {
            inactiveIcon = ImageIO.read(getClass().getResource("/inactive.png"));
            activeIcon = ImageIO.read(getClass().getResource("/active.png"));
//            inactiveIcon = ImageIO.read(new File("src/main/resources/inactive.png"));
//            activeIcon = ImageIO.read(new File("src/main/resources/active.png"));
            label.setIcon(new ImageIcon(inactiveIcon));
            active = false;
        }
        catch(IOException e)
        {
            System.out.println("IO error while reading user activity image!");
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Problem podczas wczytywania widoku!");
            System.exit(10);
        }
    }
    
    public void setActive(boolean active)
    {
        if (active)
        {
            this.active = true;
            label.setIcon(new ImageIcon(activeIcon));
        }
        else
        {
            this.active = false;
            label.setIcon(new ImageIcon(inactiveIcon));
        }
    }
    
    public boolean isActive()
    {
        return active;
    }
}
