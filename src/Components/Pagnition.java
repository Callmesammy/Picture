
package Components;

import java.awt.AlphaComposite;
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
    private int index;
    
    public void addPagnision(Pagnission event){
        this.event = event;
    }
    
   
    
    public void addcurrentindex(int currentindex){
        this.currentindex = currentindex;
        item tem = (item) getComponent(currentindex);
        tem.setAlpha(1f);
    }
    
    public  void setIndex(int index){
        this.index = index;
    }
   
    public void totalPage(int totalpage){
        removeAll();
        for (int i = 0; i < totalpage; i++) {
            add(new item(i, event));
            
        }
        repaint();
        revalidate();
        
    }
    public void setAnimation (float alpha){
        item tem = (item) getComponent(index);
        tem.setAlpha(alpha);
        item temp = (item) getComponent(currentindex);
        temp.setAlpha(1f -alpha);
        if (alpha ==1) {
            currentindex = index;
        }
    }
    public Pagnition() {
        setLayout(new MigLayout("inset 15", "10[center]10[center]10"));
        
    }
    
    // adding button and setting for the button
    public class item extends JButton{
        
               
        private float alpha;
        private void setAlpha(float alpha){
            this.alpha = alpha;
        }

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
           
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
            g2.setColor(getBackground());
            g2.fillOval(0, 0, width, height);
            
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            g2.setColor(new Color(11,122,130));
            g2.fillOval(0, 0, width, height);
            g2.dispose();
        }
        
        
    }
    
}
