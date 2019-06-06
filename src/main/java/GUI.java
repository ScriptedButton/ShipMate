/**
 * Created by 21brooksc on 5/23/2019.
 */

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GUI extends JApplet implements ActionListener {
    private JTabbedPane tabs = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
    private JPanel mainMenu = new JPanel();
    private JPanel manage = new JPanel();
    private JTextField shippingInput = new JTextField(22);
    private JButton track = new JButton("Track");
    private HashMap<JButton, JList> refreshButtons = new HashMap<JButton, JList>();
    private JSONArray trackingNums = new JSONArray();
    private JButton deleteSelected = new JButton("Delete");
    private JList manageArray;

    private void loadData(){
        JSONParser jsonParser = new JSONParser();
        String path = Test.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        try (FileReader reader = new FileReader(path + "\\data.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            ArrayList<String> data = new ArrayList<>();

            JSONArray trackingList = (JSONArray) obj;
            for(Object tnum: trackingList){
                System.out.println(tnum.toString());
                JSONObject tnum2 = (JSONObject) tnum;
                data.add(tnum2.get("number").toString());
                addNumber(new TrackingNumber(tnum2.get("number").toString()));
            }
            manageArray = new JList(data.toArray());
            manage.add(manageArray, BorderLayout.PAGE_START);
            manage.add(deleteSelected, BorderLayout.PAGE_END);
            deleteSelected.addActionListener(this);
            System.out.println(trackingList);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void addNumber(TrackingNumber trackingNumber) {
        if (trackingNumber.getTrackingInfo().toArray().length > 1) {
            JSONObject trackingNumJson = new JSONObject();
            trackingNumJson.put("number", trackingNumber.getNumber());
            JPanel newTab = new JPanel();
            newTab.setLayout(new BorderLayout());
            JList tracking = new JList(trackingNumber.getTrackingInfo().toArray());
            JButton refresh = new JButton("Refresh");
            refresh.addActionListener(this);
            newTab.add(tracking, BorderLayout.CENTER);
            newTab.add(refresh, BorderLayout.PAGE_END);
            refreshButtons.put(refresh, tracking);
            tabs.add(newTab, trackingNumber.getNumber());
            trackingNums.add(trackingNumJson);
            String path = Test.class.getProtectionDomain().getCodeSource().getLocation().getPath();

            try (FileWriter file = new FileWriter(path + "\\data.json")) {

                file.write(trackingNums.toJSONString());
                file.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void init() {
        mainMenu.add(shippingInput);
        mainMenu.add(track);
        track.addActionListener(this);
        shippingInput.addActionListener(this);
        tabs.add(mainMenu, "Main Menu");
        tabs.add(manage, "Manage");

        add(tabs);
        loadData();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == track || e.getSource() == shippingInput) {
            //System.out.println("Tracking number inputted: " + shippingInput.getText());
            TrackingNumber trackingNum = new TrackingNumber(shippingInput.getText());
            System.out.println(trackingNum.getTrackingInfo());
            addNumber(trackingNum);
        }
        else if (e.getSource() == deleteSelected){
            System.out.println(manageArray.getSelectedValue());
            trackingNums.remove(new JSONObject().put("number", manageArray.getSelectedValue()));
            String path = Test.class.getProtectionDomain().getCodeSource().getLocation().getPath();

            try (FileWriter file = new FileWriter(path + "\\data.json")) {

                file.write(trackingNums.toJSONString());
                file.flush();

            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        else {
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
