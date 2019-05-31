/**
 * Created by 21brooksc on 5/23/2019.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class GUI extends JApplet implements ActionListener {
    private JTabbedPane tabs = new JTabbedPane();
    private JPanel mainMenu = new JPanel();
    private JTextField shippingInput = new JTextField(22);
    private JButton track = new JButton("Track");

    private void addNumber(TrackingNumber trackingNumber){
        JPanel newTab = new JPanel();
        newTab.setLayout(new GridLayout());
        newTab.add(new JList(trackingNumber.getTrackingInfo().toArray()));
        tabs.add(newTab, trackingNumber.getNumber());
    }

    public void init(){
        mainMenu.setLayout(new GridLayout());
        mainMenu.add(shippingInput);
        mainMenu.add(track);
        track.addActionListener(this);
        tabs.add(mainMenu, "Main Menu");

        add(tabs);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == track){
            //System.out.println("Tracking number inputted: " + shippingInput.getText());
            TrackingNumber trackingNum = new TrackingNumber(shippingInput.getText());
            System.out.println(trackingNum.getTrackingInfo());
            addNumber(trackingNum);
        }

    }

}
