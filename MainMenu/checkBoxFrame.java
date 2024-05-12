package MainMenu;

/*
* Use this for group diary and giving access in diary
*
* */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class checkBoxFrame extends JPanel
{
    public static void main(String[] args)
    {
        ArrayList<String> str = new ArrayList<String>();
        str.add("sds");
        str.add("sdsa");
        str.add("sdsss");

        new checkBoxFrame(str, null, new Dimension(0,0));
    }
    JPanel panel;
    JScrollPane scrollPane;
    ArrayList<JCheckBox> checkBoxes;
    JComponent location;
    Dimension offSet;
    Timer timer;

    checkBoxFrame(ArrayList<String> strings, JComponent component, Dimension offSet)
    {
        location = component;
        this.offSet = offSet;

        super.setPreferredSize(new Dimension(100,500));
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(100, strings.size() > 5 ? 50: strings.size() * 10));
        panel.setLayout(new GridLayout(0,1));
        scrollPane = new JScrollPane(panel);
        this.add(scrollPane);

        checkBoxSetUp(strings);

        panel.add(new ButtonListener());
        setVisible(true);
    }

    private void checkBoxSetUp(ArrayList<String> strings)
    {
        checkBoxes = new ArrayList<>();
        for (int i = 0; i < strings.size(); i++)
        {
            JCheckBox checkBox = new JCheckBox(strings.get(i));
            panel.add(checkBox);
            checkBoxes.add(checkBox);
        }
    }

    private ArrayList<String> GetSelected()
    {
        ArrayList<String> returns = new ArrayList<String>();

        for (int i = 0; i < checkBoxes.size(); i++)
        {
            if (checkBoxes.get(i).isSelected())
            {
                returns.add(checkBoxes.get(i).getText());
            }
        }

        return returns;
    }

    class TimerListener implements ActionListener
    {
        JFrame frame;
        TimerListener(JFrame frame)
        {
            this.frame = frame;
        }

        public void actionPerformed(ActionEvent e)
        {
            Point finalLocation = new Point(location.getLocationOnScreen().x + (int)offSet.getWidth(), location.getLocationOnScreen().y + (int)offSet.getHeight());
            frame.setLocation(finalLocation);
        }
    }

    class ButtonListener extends JButton implements ActionListener
    {
        ButtonListener()
        {
            super("Make Group Diary");
        }

        public void actionPerformed(ActionEvent e)
        {

        }
    }
}
