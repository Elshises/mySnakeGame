import javax.swing.*;
import java.awt.*;
public class JSetup extends JFrame{

JSetup(){
this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//this.setLocationRelativeTo(null);


this.add(new GamePanel());
this.pack();
this.setVisible(true);
//this.setExtendedState(MAXIMIZED_BOTH);


}

} 