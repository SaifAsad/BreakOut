/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AssignmentOne;

import javax.swing.JFrame;
import laboratories.lab2.BouncingBalls;

/**
 *
 * @author Saif Asad
 */
public class BreakOut {
    private static final int DEFALUT_WIDTH = 600;
    private static final int DEFAULT_HEIGHT = 600;
    
    
    public static void main(String[] args){
        JFrame frame = new JFrame("Assignment One");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BouncingBalls bouncingBalls = new BouncingBalls();

        frame.setContentPane(bouncingBalls);

        frame.pack();
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }
}
