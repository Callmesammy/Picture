
package Components;

import java.awt.Component;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;


public class Sliderframe extends javax.swing.JLayeredPane {

  private final JPanel panel;
  private final Animator animate;
  private final MigLayout layout;
  private Component componentShow;
  private Component componentOut;
  private int selectedIndex;
  private boolean selected;
  
   
    public Sliderframe() {
        initComponents();
        setOpaque(false);
        layout = new MigLayout("inset 0");
        panel = new JPanel();
        TimingTarget target = new TimingTargetAdapter(){};
        animate = new Animator(0, target);
        animate.setResolution(0);
        animate.setAcceleration(0.5f);
        animate.setDeceleration(0.5f);
        panel.setLayout(layout);
        setLayout(new MigLayout("inset, fill 0", "[fill center]", "3[fill]3"));
        add(panel, "w 60");
    }

    public void addslider (Component ...com){
        if (com.length >= 2) {
            for(Component coms: com){
                coms.setVisible(false);
                add(coms, "pos 0 0 0 0");
            }
            
        }
        if (panel.getComponentCount()>0) {
            
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 591, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 334, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
