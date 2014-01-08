/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gloskampen.view;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;


/**
 * Latest update: 2014-01-06 21:47
 * @author Fredrik Johansson
 */
public class ButtonStyle 
{    
    private final Color colBtnBg;
    private final Color colBtnTxt;
    private final ArrayList<JPanel> panelList;
    
    
    public ButtonStyle(ArrayList<JPanel> panelList)
    {
        colBtnBg = new Color(106,78,57); // 65,51,38    106,78,57
        colBtnTxt = new Color(255,255,255);
        this.panelList = panelList;
        
        styleButtons();        
    }
    
    private void styleButtons()
    {        
        for (int i = 0; i < panelList.size(); i++)
        {
            JPanel pnl = panelList.get(i);
            Component[] c = pnl.getComponents();   
            
            for (int j = 0; j < c.length; j++)
            {
                if (c[j] instanceof JButton)
                {
                    JButton btn = (JButton)c[j];
                    btn.setBackground(colBtnBg); 
                    btn.setForeground(colBtnTxt);
                    btn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));                    
                    btn.setFocusPainted(false);                   
                }
            }
        }       
    }
}
