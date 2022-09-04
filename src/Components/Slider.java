
package Components;

import java.awt.Component;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

/**
 *
 * @author user
 */
public class Slider extends JLayeredPane{

    private final JPanel panel;
    private final MigLayout layout;
    private final Animator animate;
    private Component componentShow;
    private Component componentOut;
    private int selectedIndex;
    private boolean selected;
    
    public Slider() {
        layout = new MigLayout("inset 0");
        panel = new JPanel();
        TimingTarget target = new TimingTargetAdapter(){
            @Override
            public void begin() {
               componentShow.setVisible(true);
               componentOut.setVisible(true);
            }

            @Override
            public void timingEvent(float fraction) {
           double width = panel.getWidth();
           int location = (int) (width * fraction);
           int locationShow = (int) (width * (1f-fraction));
                if (selected) {
                    layout.setComponentConstraints(componentShow, "pos "+locationShow+" 0 100% 100%, w 100%! ");
                    layout.setComponentConstraints(componentOut, "pos -"+location+" 0 " +(width-location)+ " 100%");
                }else{
                    
                }
                panel.revalidate();
            }

            @Override
            public void end() {
               componentOut.setVisible(false);
               layout.setComponentConstraints(componentOut, "pos 0 0 100% 100%, width 100%");
            }
            
        };
        animate = new Animator(1000, target);
        animate.setResolution(0);
        animate.setAcceleration(0.5f);
        animate.setDeceleration(0.5f);
        panel.setLayout(layout);
        setLayout(new MigLayout("fill, inset 0", "[fill, center]", "3[fill]3"));
        add(panel, "w 100%-6!");
        
    }
    
    public void initSlider(Component ...com){
        if (com.length >=2) {
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
    }    
    
    public void next(){
        if (!animate.isRunning()) {
            selected = true;
            selectedIndex = getnext(selectedIndex);
            componentShow = panel.getComponent(selectedIndex);
            componentOut = panel.getComponent(checkNext(selectedIndex -1));
            
            animate.start();
            
        }
    }
    
    private int getnext(int index){
        if (index == panel.getComponentCount()-1) {
            return 0;
        }else{
            return index +1;
        }
        
    }
    
    private int checkNext(int index){
        if (index== -1) {
            return panel.getComponentCount() - 1 ;
            
        }else{
            return index;
        }
    }
}
