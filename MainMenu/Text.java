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
        textFile = new File(MenuFrame.pathString + "\\" + profile.getName() + "\\" + year +  "_" + month + "_" + day);
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
                a.printStackTrace();
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
        if (setChangeable())
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

    public void giveAccess(String friend)
    {
        try {
            Scanner scan = new Scanner(accessedPeopleFile);

            boolean hasGiven = false;

            while (scan.hasNextLine())
            {
                if (scan.nextLine().equals(profile.getName()))
                {
                    hasGiven = true;
                    break;
                }
            }

            if (!hasGiven)
            {
                people.add(friend);

                try {
                    Files.write(Paths.get(accessedPeopleFile.getAbsolutePath()), people, StandardCharsets.UTF_8);
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public void setGroup(ArrayList<String> people)
    {
        isGroup = true;
        text.set(0, isSpecial + "1");
        setDayText(textArea.getText());

        for(int i = 0; i < people.size(); i++)
        {
            giveAccess(people.get(i));

            Text friendText = new Text(date[0], date[1], date[2], new Controller().getProfilebyName());

            friendText.giveAccess(profile.getName());
            friendText.makeGroup();
        }
    }

    public void makeGroup()
    {
        if(text.get(0).substring(1).equals("0"))
        {
            text.set(0, text.get(0).substring(0,1) + "1");

            try {
                Files.write(Paths.get(textFile.getAbsolutePath()), text, StandardCharsets.UTF_8);
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
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

                try{
                        Scanner accessScanner = new Scanner(new File(MenuFrame.pathString + "\\" +
                                people.get(i) + "\\" + date[0] +  "_" + date[1] + "_" + date[2] + "access"));

                        while(accessScanner.hasNextLine())
                        {
                            if (accessScanner.nextLine().equals(profile.getName()))
                            {
                                text.add("\n" + people.get(i) + "\n");

                                try{
                                    Scanner peopleScanner = new Scanner(new File(MenuFrame.pathString + "\\" +
                                            people.get(i) + "\\" + date[0] +  "_" + date[1] + "_" + date[2]));

                                    if (peopleScanner.nextLine().substring(1).equals("1"))
                                    {
                                        while(peopleScanner.hasNextLine())
                                        {
                                            text.add(peopleScanner.nextLine());
                                        }
                                    }
                                }
                                catch (FileNotFoundException e)
                                {
                                    continue;
                                }

                            }
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
            MenuFrame.facediary.setVisible(true);
    //        MenuFrame.facediary.getDiary().updateColor();
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
                frame.getPopUp().hide();
                frame = null;
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

                frame = new checkBoxFrame(friends,this,new Dimension(100,100));
                frame.getPopUp().show();
            }
        }
    }

    class checkBoxFrame
    {
        JPanel bigPanel = new JPanel();
        JPanel panel = new JPanel();
        JScrollPane scrollPane;
        ArrayList<JCheckBox> checkBoxes;
        Popup popup;

        PopupFactory factory;

        checkBoxFrame(ArrayList<String> strings, JComponent component, Dimension offSet)
        {
            PopupFactory factory = new PopupFactory();
            setContent(strings, component, offSet);
            popup = factory.getPopup(component, bigPanel, offSet.width, offSet.height);
        }

        public void setContent(ArrayList<String> strings, JComponent component, Dimension offSet)
        {
            panel = new JPanel();
            panel.setLayout(new GridLayout(0,1));
            scrollPane = new JScrollPane(panel);
            bigPanel.add(scrollPane);
            checkBoxSetUp(strings);
            panel.add(new ButtonListener());
        }

        public Popup getPopUp()
        {
            return popup;
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

        class ButtonListener extends JButton implements ActionListener
        {
            ButtonListener()
            {
                super("give access");
                this.addActionListener(this);
            }

            public void actionPerformed(ActionEvent e)
            {
                for (String people: GetSelected())
                {
                    giveAccess(people);

                    getPopUp().hide();
                }
            }
        }
    }
}