
package AssignmentOne;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Saif Asad
 */
public class Draw extends JPanel {
    
    public void drawObject(){
        repaint();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fill3DRect(10, 20, 200, 300, true);
    }
    
    
}
