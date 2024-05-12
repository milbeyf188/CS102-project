package MainMenu;



import CS_Project_Profile.Profile;
import FaceDiaryLoginIlbey.src.loginandsignup.Controller;
import FaceDiaryLoginIlbey.src.loginandsignup.FDController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;
import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Text extends JPanel
{
    private ArrayList<String> text = new ArrayList<String>();
    private ArrayList<String> people = new ArrayList<String>();
    private File textFile;
    private File accessedPeopleFile;
    private boolean changeable;
    private JTextPane textArea;
    private int[] date = new int[3];
    private JFrame frame;
    private boolean isSpecial;
    private boolean isGroup;
    private Profile profile;

    public Text(int year, int month, int day, Profile profile)
    {
        textFile = new File(test.pathString + "\\" + profile.getName() + "\\" + year +  "_" + month + "_" + day);
        accessedPeopleFile = new File(textFile.getAbsolutePath() + "access");

        date[0] = year; date[1] = month; date[2] = day;
        this.profile = profile;

        try {
            Scanner accessFile = new Scanner(accessedPeopleFile);

            while (accessFile.hasNextLine()) {
                people.add(accessFile.nextLine());
            }

            accessFile.close();
        }
        catch(FileNotFoundException e)
        {
            try{
                accessedPeopleFile.createNewFile();
            }
            catch(IOException a)
            {
                System.out.println("couldn't create access file");
            }
        }

        try{
            Scanner scan = new Scanner(textFile);

            while(scan.hasNextLine()){
                text.add(scan.nextLine());
            }

            scan.close();
        }
        catch(FileNotFoundException e)
        {
            try{
                textFile.createNewFile();
            }
            catch(IOException a)
            {
                System.out.println("couldn't create text file");
            }
        }

        if(text.isEmpty())
        {
            text.add("00");
        }

        isSpecial = text.get(0).substring(0,1).equals("1");
        isGroup = text.get(0).substring(1).equals("1");

        setShownText();

        textArea = new JTextPane();
        textArea.setText(getText());
        textArea.setPreferredSize(new Dimension(800,600));
        textArea.setEditable(setChangeable());
        JScrollPane scrollbar = new JScrollPane(textArea);
        add(scrollbar);
        add(new buttonListener());
        add(new specialButtonListener());
        add(new accessButton());
    }

    public Text(String date, Profile profile)
    {
        this(Integer.parseInt(date.split("_")[0]), Integer.parseInt(date.split("_")[1]), Integer.parseInt(date.split("_")[2]), profile);
    }

    public ArrayList<String> getAccessed()
    {
        return people;
    }

    public void setDayText(String str)
    {
        if (changeable)
        {
            Scanner scanner = new Scanner(str);

            text.clear();
            text.add((isSpecial? "1": "0") + (isGroup? "1": "0"));
            while(scanner.hasNextLine()) 
            {
                text.add(scanner.nextLine());
            }
            
            try{
                Files.write(Paths.get(textFile.getAbsolutePath()), text, StandardCharsets.UTF_8);
            }
            catch(IOException e)
            {
                System.out.println("error on text to file operation");
            }
    
            scanner.close();
        }
        else
        {
            System.out.println("not changeable");
        }
    }

    public void setGroup()
    {
        isGroup = true;
        text.set(0, isSpecial + "1");
        setDayText(textArea.getText());
    }

    public void setFrame(JFrame frame)
    {
        this.frame  = frame;
        frame.add(this);
        frame.setVisible(true);
        frame.pack();
    }

    public String getText()
    {
        String str = "";

        for(int i = 1; i < text.size(); i++)
        {
            str += text.get(i) + "\n";
        }

        return str;
    }

    public void setShownText()
    {
        if(isGroup && !setChangeable())
        {
            for(int i = 0; i < people.size(); i++)
            {
                text.add("\n" + people.get(i) + "\n");

                try{
                    Scanner peopleScanner = new Scanner(new File(test.pathString + "\\" + people.get(i) +
                        "\\" + date[0] +  "_" + date[1] + "_" + date[2]));

                    peopleScanner.nextLine();

                    while(peopleScanner.hasNextLine())
                    {
                        text.add(peopleScanner.nextLine());
                    }

                }
                catch (FileNotFoundException e)
                {
                    continue;
                }

            }
        }
    }

    private void setText(String str)
    {
        Scanner scanner = new Scanner(str);

        text.clear();
        text.add((isSpecial? "1": "0") + (isGroup? "1": "0"));

        while(scanner.hasNextLine())
        {
            text.add(scanner.nextLine());
        }

        try{
            Files.write(Paths.get(textFile.getAbsolutePath()), text, StandardCharsets.UTF_8);
        }
        catch(IOException e)
        {
            System.out.println("error on text to file operation");
        }

        scanner.close();
    }

    public boolean setChangeable()
    {
        if(Diary.currentDate.compareTo(new GregorianCalendar(date[0], date[1] - 1, date[2])) == 0)
        {
            changeable = true;
        }
        else
        {
            changeable = false;
        }

        return changeable;
    }

    public boolean changeSpeciality()
    {
        isSpecial = !isSpecial;
        text.set(0, (isSpecial? "1": "0") + text.get(0).substring(1));
        setText(getText());
        return isSpecial;
    }

    public boolean hasWritten()
    {
        return text.size() > 1;
    }

    public Color getColor()
    {
        if(setChangeable())
        {
            return Color.YELLOW;
        }

        if(isGroup)
        {
            return Color.MAGENTA;
        }

        if(isSpecial)
        {
            return Color.CYAN;    
        }

        if(Diary.currentDate.compareTo(new GregorianCalendar(date[0], date[1] - 1, date[2])) > 0) 
        {
            return hasWritten() ? Color.GREEN: Color.RED;
        }

        return Color.GRAY;
    }

    class buttonListener extends JButton implements ActionListener
    {
        public buttonListener()
        {
            super("Save");
            super.setAlignmentY(BOTTOM_ALIGNMENT);
            super.addActionListener(this);
        }

        public void actionPerformed(ActionEvent e) 
        {
            setDayText(textArea.getText());
            test.facediary.setVisible(true);
            frame.dispose();
        }
    }

    class specialButtonListener extends JButton implements ActionListener
    {
        public specialButtonListener()
        {
            super(isSpecial ? "Unmake It Special": "Make It Special");
            super.addActionListener(this);            
        }

        public void actionPerformed(ActionEvent e)
        {
            super.setText(changeSpeciality() ? "Unmake It Special": "Make It Special");
        }
    }

    class accessButton extends JButton implements ActionListener
    {
        checkBoxFrame frame;

        accessButton()
        {
            super("...");
            super.setAlignmentY(TOP_ALIGNMENT);
            super.addActionListener(this);
        }

        public void actionPerformed(ActionEvent e)
        {
            if(frame != null)
            {
                frame.disable();
            }
            else
            {
                Controller con = new Controller();
                ArrayList<Integer> friendIDs = con.getFriendsArray(profile.getID());

                ArrayList<String> friends = new ArrayList<>();

                for (int i = 0; i < friendIDs.size(); i++)
                {
                    friends.add(con.getNameById(friendIDs.get(i)));
                }

                frame = new checkBoxFrame(friends,this,new Dimension(10,10));
            }
        }
    }

    class checkBoxFrame extends JFrame
    {
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
}