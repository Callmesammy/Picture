
package Components;

// extends JLayeredpane in other to be able to drag to the main page 

import java.awt.Component;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class SliderPage extends JLayeredPane{
//creation of all th necessary componets needed 
    
    private final JPanel panel;
   
    private final Pagnition pagnition;
    private final Animator animate; 
    private final MigLayout layout;
    private Component componentShow;
    private Component componentOut;
    private boolean selected;
    private int selectedIndex;
    
    // in this segment you fill all the categories, 
    public SliderPage() {
       // bring down the final components
     
       
        layout = new MigLayout("inset 0");
        panel = new JPanel();
          pagnition = new Pagnition();
         pagnition.addPagnision(new Pagnision() {
            @Override
            public void Onclick(int index) {
                
       // insttiazing the timing target and timing adapter 
        TimingTarget target = new TimingTargetAdapter(){
            @Override
            public void begin() {
             componentShow.setVisible(true);
             componentOut.setVisible(true);  
             pagnition.setIndex(selectedIndex);
    }

            @Override
            public void timingEvent(float fraction) {
               double width = panel.getWidth();
               int location = (int) (width * fraction);
               int locationShow = (int) (width * (1f - fraction));
                if (selected) {
                /* adding the layouts for the components, and most importantly the space re very impotant and 
                    and should be taken note of, else the code wont work properly
                    */
                    layout.setComponentConstraints(componentShow, "pos " +locationShow+ " 0 100% 100%, w 100%!");
                    layout.setComponentConstraints(componentOut, "pos -" +location+ " 0 " +(width-location)+ " 100% ");
                }else{
                      layout.setComponentConstraints(componentShow, "pos -" +locationShow+ " 0 " +(width-locationShow)+ " 100% ");
                     layout.setComponentConstraints(componentOut, "pos " +location+ " 0 100% 100%, w 100%!");
                  
                } 
              
                panel.revalidate();
                pagnition.addAnimation(fraction);
              
            }

            @Override
            public void end() {
             componentOut.setVisible(false);
             layout.setComponentConstraints(componentOut, "pos 0 0 100% 100%, width 100%");
            }
            
        };
        // instatiazing the animator and setting attached to it 
     
        animate = new Animator(1000, target);
        animate.setResolution(0);
        animate.setAcceleration(0.5f);
        animate.setDeceleration(0.5f);
        panel.setLayout(layout);
        setLayer(pagnition, JLayeredPane.POPUP_LAYER);
        setLayout(new MigLayout("fill, inset 0", "[fill, center]", "3[fill]3"));
        add(pagnition, "pos 0.5al 1al n n");
        add(panel, "w 100%-6!");
        
    }
    
    // Next instatiazing the necessary components in other to inpute it on other pages 
    public void inisliding(Component ...com){
        if (com.length >= 2) {
          for(Component coms: com){
              coms.setVisible(false);
              panel.add(coms, "pos 0 0 0 0");
          }
            if (panel.getComponentCount()>0) {
                componentShow = panel.getComponent(0);
                componentShow.setVisible(true);
                layout.setComponentConstraints(componentShow, "pos 0 0 100% 100%");
            }
            
        }
        
        pagnition.totalSelected(panel.getComponentCount());
        pagnition.setSelected(0); 
    }
    
    //instatiazing the necessary component for the sliding to be able from segment to the other 
    
    public void next(){
        if (!animate.isRunning()) {
            selected = true;
            selectedIndex = getnext(selectedIndex);
            componentShow = panel.getComponent(selectedIndex);
            componentOut = panel.getComponent(checknext(selectedIndex-1));
            
            animate.start();
            
        }
    }
    // adding the back sliding and its components 
    public void back(){
        if (!animate.isRunning()) {
              if (!animate.isRunning()) {
            selected = false;
            selectedIndex = getback(selectedIndex);
            componentShow = panel.getComponent(selectedIndex);
            componentOut = panel.getComponent(checkback(selectedIndex +1));
            
            animate.start();
              }     
       }
    }
    // instatiazing components attched in other to make the necessary movements 
    private int getnext(int index){
        if (index == panel.getComponentCount()-1) {
            return 0;
        }else{
            return index +1;
        }
    }
    private int checknext(int index){
    if (index == -1) {
    return panel.getComponentCount() -1;
    }else{
    return index;
    }
    }
    
    private int getback(int index){
        if (index == 0) {
               return panel.getComponentCount() -1;
        }else{
            return index -1;
        }
        
    }
    
    private int checkback(int index){
        if (index ==panel.getComponentCount()) {
            return 0;
        }else{
            return index;
        }
    }
    
    

}
