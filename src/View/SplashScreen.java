/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

/**
 *
 * @author Colin
 */
import java.awt.*;
import javax.swing.*;

public class SplashScreen extends JWindow {
    
    private int duration;
    
    public SplashScreen(int d) {
        duration = d;
    }
    
// Este é um método simples para mostrar uma tela de apresentção 

// no centro da tela durante a quantidade de tempo passada no construtor

    public void showSplash() {        
        JPanel content = (JPanel)getContentPane();
        content.setBackground(Color.white);
        
        // Configura a posição e o tamanho da janela
        int width = 450;
        int height =450;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width-width)/2;
        int y = (screen.height-height)/2;
        setBounds(x,y,width,height);
        
        // Constrói o splash screen
        JLabel label = new JLabel(new ImageIcon("src/Imagens/colins3.png"));
        JLabel copyrt = new JLabel
                ("Copyright 2016, Colin's Desenvolvimento de Sistemas", JLabel.CENTER);
        copyrt.setFont(new Font("Sans-Serif", Font.BOLD, 12));
        content.add(label, BorderLayout.CENTER);
        content.add(copyrt, BorderLayout.SOUTH);
        Color oraRed = new Color(30, 50, 50,  255);
        content.setBorder(BorderFactory.createLineBorder(oraRed, 5));        
        // Torna visível
        setVisible(true);
        
        // Espera ate que os recursos estejam carregados
        try { Thread.sleep(duration); } catch (Exception e) {}        
        setVisible(false);        
    }
    
    public void showSplashAndExit() {        
        showSplash();
        
        //System.exit(0);        
    }
    
   /* public static void main(String[] args) {        
        // Mostra uma imagem com o título da aplicação 
        SplashScreen splash = new SplashScreen(8000);
        splash.showSplashAndExit();        
    }
    */
    
} 

