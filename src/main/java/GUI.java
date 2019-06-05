/**
 * Created by 21brooksc on 5/23/2019.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.regex.Pattern;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class GUI extends JApplet implements ActionListener {
    private JTabbedPane tabs = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
    private JPanel mainMenu = new JPanel();
    private JTextField shippingInput = new JTextField(22);
    private JButton track = new JButton("Track");
    private HashMap<JButton, JList> refreshButtons = new HashMap<JButton, JList>();

    private void addNumber(TrackingNumber trackingNumber) {
        if (trackingNumber.getTrackingInfo().toArray().length > 1) {
            JPanel newTab = new JPanel();
            newTab.setLayout(new BorderLayout());
            JList tracking = new JList(trackingNumber.getTrackingInfo().toArray());
            JButton refresh = new JButton("Refresh");
            refresh.addActionListener(this);
            newTab.add(tracking, BorderLayout.CENTER);
            newTab.add(refresh, BorderLayout.PAGE_END);
            refreshButtons.put(refresh, tracking);
            tabs.add(newTab, trackingNumber.getNumber());
        }

    }

    public void init() {
        mainMenu.add(shippingInput);
        mainMenu.add(track);
        track.addActionListener(this);
        shippingInput.addActionListener(this);
        tabs.add(mainMenu, "Main Menu");

        add(tabs);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == track || e.getSource() == shippingInput) {
            //System.out.println("Tracking number inputted: " + shippingInput.getText());
            TrackingNumber trackingNum = new TrackingNumber(shippingInput.getText());
            System.out.println(trackingNum.getTrackingInfo());
            addNumber(trackingNum);
        } else {
            for (JButton button : refreshButtons.keySet()) {
                JButton actionButton = (JButton) e.getSource();
                if (actionButton == (button)) {
                    TrackingNumber tracking = new TrackingNumber(tabs.getTitleAt(tabs.getSelectedIndex()));
                    refreshButtons.get(button).setListData(tracking.getTrackingInfo().toArray());
                    System.out.println(tracking.getTrackingInfo().toArray()[0]);
                }
            }
        }
    }
}
