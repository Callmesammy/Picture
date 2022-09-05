
package Components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;

/*
this segment further extends to Components, how they functions and how they should be implemented 
*/
public class Pagnition extends JComponent{
//next we add the paganision interface 
    private Pagnission event;
    private int currentindex;
    private int selectindex;
    
    public void addPagnision(Pagnission event){
        this.event = event;
    }
    
    public void addcurrentindex(int currentindex){
        this.currentindex = currentindex;
    }
    
    public  void addselectindex(int index){
        this.selectindex = selectindex;
    }
    
    public void totalPage(int totalpage){
        removeAll();
        for (int i = 0; i < totalpage; i++) {
            add(new item(i, event));
            
        }
        repaint();
        revalidate();
        
    }
    public Pagnition() {
        setLayout(new MigLayout());
    }
    
    // adding button and setting for the button
    public class item extends JButton{

        public item(int index, Pagnission event) {
            setContentAreaFilled(false);
            setBackground(Color.white);
            setBorder(new EmptyBorder(5,5,5,5));
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                   event.Onclick(index);
                }
            });
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g); 
            int width = getWidth();
            int height = getHeight();
            Graphics2D g2 = (Graphics2D)g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillOval(0, 0, width, height);
            g2.dispose();
        }
        
        
    }
    
}
