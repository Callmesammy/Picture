
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


public class Pagnition extends JComponent{

    private Pagnision event;
    private int index;
    private int selected;
    
    public void addPagnision (Pagnision event){
        this.event = event;
    }
    
    public void setIndex(int index){
        this.index = index;
    }
    
    public void setSelected (int selected){
        this.selected = selected;
        Iteems tem = (Iteems) getComponent(selected);
        tem.setAlpha(1f);
    }
    
    public void addAnimation(float alpha){
        Iteems temp = (Iteems) getComponent(index);
        temp.setAlpha(alpha);
        Iteems tec = (Iteems) getComponent(selected);
        tec.setAlpha(1f-alpha);
        if (alpha==1) {
            selected = index;
        }
    }
    
    public void totalSelected(int total){
        removeAll();
        for (int i = 0; i < total; i++) {
            add(new Iteems(i, event));
        }
        
        repaint();
        revalidate();
        
    }
    public Pagnition() {
    setLayout(new MigLayout("inset 15", "5[center]5[center]5"));
        
    }
    
    
    
    public class Iteems extends JButton{
        
        private float alpha;
        
        private void setAlpha(float alpha){
            this.alpha = alpha;
        }

        public Iteems(int index, Pagnision event) {
            setContentAreaFilled(false);
            setBorder(new EmptyBorder(5,5,5,5));
            setBackground(Color.WHITE);
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
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
            g2.fillOval(0, 0, width, height);
            
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            g2.setColor(new Color(11,148,121));
            g2.fillOval(0, 0, width, height);
     
            g2.dispose();
        }
        
        
    }
}
