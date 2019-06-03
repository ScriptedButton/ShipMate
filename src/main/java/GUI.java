/**
 * Created by 21brooksc on 5/23/2019.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class GUI extends JApplet implements ActionListener {
    private JTabbedPane tabs = new JTabbedPane();
    private JPanel mainMenu = new JPanel();
    private JTextField shippingInput = new JTextField(22);
    private JButton track = new JButton("Track");
    private HashMap<JButton, JList> refreshButtons = new HashMap<JButton, JList>();

    private void addNumber(TrackingNumber trackingNumber){
        JPanel newTab = new JPanel();
        newTab.setLayout(new BorderLayout());
        JList tracking = new JList(trackingNumber.getTrackingInfo().toArray());
        JButton refresh = new JButton("Refresh");
        newTab.add(tracking, BorderLayout.CENTER);
        newTab.add(refresh, BorderLayout.PAGE_END);
        refreshButtons.put(refresh, tracking);
        tabs.add(newTab, trackingNumber.getNumber());
    }

    public void init(){
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
        for(JButton button: refreshButtons.keySet()){
            if (e.getSource() == button){
                refreshButtons.get(button).
            }
        }
    }

}
