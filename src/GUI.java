/**
 * Created by 21brooksc on 5/23/2019.
 */
import javax.swing.*;
import java.awt.Graphics;

public class GUI extends JApplet {
    private JTabbedPane tabs = new JTabbedPane();
    private JPanel mainMenu = new JPanel();

    public void init(){
        mainMenu.add(new JButton("Main Menu"));
        tabs.add(mainMenu, "Main Menu");
        tabs.add(new JButton("idk"), "Test");
        add(tabs);
    }
}
