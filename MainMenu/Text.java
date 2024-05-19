package MainMenu;

import CS_Project_Profile.Profile;
import FaceDiaryLoginIlbey.src.loginandsignup.Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.File;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Text extends JPanel
{
    private ArrayList<String> text = new ArrayList<String>();
    private ArrayList<String> people = new ArrayList<String>();

    JFrame frameToOpen = MenuFrame.facediary;
    PopupFactory popupMenu;
    private Timer timer = new Timer(500, new TimerListener());

    private File textFile;
    private File accessedPeopleFile;
    private boolean justLooking = false;

    private boolean changeable;
    private int[] date = new int[3];
    private boolean isSpecial;
    private boolean isGroup;
    private boolean shown = false;
    private Profile profile;

    private JFrame frame;
    private JTextPane textArea;
    private JScrollPane textPanel;
    private JButton backButton;
    private JButton saveButton;
    private JButton accessButton;
    private JLabel dateLabel;

    public Text(int year, int month, int day, Profile profile)
    {
        File textFolder = new File(MenuFrame.pathString + "\\" + profile.getName());
        textFolder.mkdirs();

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

        dateLabel = new JLabel( day + "/" + month + "/" + year);
        dateLabel.setVerticalAlignment(SwingConstants.TOP);
        textArea = new JTextPane();
        textArea.setText(getText());
        textPanel = new JScrollPane(textArea);

        setText(getText());
        setShownText();

        this.setLayout(null);

        backButton = new BackButtonListener();
        saveButton = new SaveButtonListener();
        timer.start();

        if (!isGroup)
        {
            accessButton = new accessButton();

            add(accessButton);
        }

        add(dateLabel);
        add(backButton);
        add(textPanel);

        if (setChangeable())
        {
            add(saveButton);
        }
    }

    public void setFriendText(JFrame frame)
    {
        remove(saveButton);

        if (accessButton != null)
        {
            remove(accessButton);
        }
        textPanel.remove(textArea);

        JLabel label = new JLabel(getText());

        textPanel.add(label);

        frameToOpen = frame;

        justLooking = true;
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

    public void takeAccess(String friend)
    {
        try {
            Scanner scan = new Scanner(accessedPeopleFile);

            boolean hasGiven = false;

            while (scan.hasNextLine())
            {
                if (scan.nextLine().equals(friend))
                {
                    hasGiven = true;
                    break;
                }
            }

            if (hasGiven)
            {
                people.remove(friend);

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

    public void giveAccess(String friend)
    {
        if (friend.equals(profile.getName()))
        {
            return;
        }

        try {
            Scanner scan = new Scanner(accessedPeopleFile);

            boolean hasGiven = false;

            while (scan.hasNextLine())
            {
                if (scan.nextLine().equals(friend))
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
        text.set(0, (isSpecial? 0: 1) + "1");
        setDayText(textArea.getText());

        for(int i = 0; i < people.size(); i++)
        {
            giveAccess(people.get(i));

            Text friendText = new Text(date[0], date[1], date[2], new Controller().getProfileByName(people.get(i)));

            friendText.giveAccess(profile.getName());

            for (int j = 0; j < people.size(); j++)
            {
                if (i != j)
                {
                    friendText.giveAccess(people.get(j));
                }
            }

            friendText.makeGroup();

            if(accessButton != null){
                remove(accessButton);
            }
        }
    }

    public void makeGroup()
    {
        if(accessButton != null){
            remove(accessButton);
        }

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
        frame.addComponentListener(new resizeListener(this));
        frame.setVisible(true);
        frame.setSize(new Dimension(1000,800));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        if(isGroup && !setChangeable() && !shown)
        {
            shown = true;
            StyledDocument doc = textArea.getStyledDocument();
            Style style = textArea.addStyle("", null);

            for(int i = 0; i < people.size(); i++)
            {
                StyleConstants.setForeground(style, new Color((int)(Math.random() * 160 + 40), (int)(Math.random() * 160 + 40), (int)(Math.random() * 160 + 40)));

                try{
                        Scanner accessScanner = new Scanner(new File(MenuFrame.pathString + "\\" +
                                people.get(i) + "\\" + date[0] +  "_" + date[1] + "_" + date[2] + "access"));

                        while(accessScanner.hasNextLine())
                        {
                            if (accessScanner.nextLine().equals(profile.getName()))
                            {

                                try{
                                    Scanner peopleScanner = new Scanner(new File(MenuFrame.pathString + "\\" +
                                            people.get(i) + "\\" + date[0] +  "_" + date[1] + "_" + date[2]));

                                    if (peopleScanner.nextLine().substring(1).equals("1"))
                                    {
                                        if (peopleScanner.hasNextLine())
                                        {
                                            doc.insertString(doc.getLength(),"\n" + people.get(i) + ":      ", style);
                                        }
                                        while(peopleScanner.hasNextLine())
                                        {
                                            doc.insertString(doc.getLength(), peopleScanner.nextLine() + "\n", style);
                                        }
                                    }
                                }
                                catch (FileNotFoundException e)
                                {
                                    continue;
                                } catch (BadLocationException e) {
                                    throw new RuntimeException(e);
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

            if (saveButton != null)
            {
                add(saveButton);
            }
        }
        else
        {
            changeable = false;

            if (saveButton != null)
            {
                remove(saveButton);
            }
        }

        textArea.setEditable(changeable);
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

    class SaveButtonListener extends JButton implements ActionListener
    {
        public SaveButtonListener()
        {
            super("Save");
            super.setAlignmentY(BOTTOM_ALIGNMENT);
            setBackground(Color.GREEN);
            super.addActionListener(this);
        }

        public void actionPerformed(ActionEvent e) 
        {
            setDayText(textArea.getText());
            MenuFrame.facediary.setVisible(true);
            MenuFrame.facediary.getDiary().updateCalendar();
            frame.dispose();
        }
    }

    class specialButtonListener extends JButton implements ActionListener
    {
        public specialButtonListener()
        {
            super(isSpecial ? "Undo marking": "Mark as a special day");
            super.addActionListener(this);            
        }

        public void actionPerformed(ActionEvent e)
        {
            super.setText(changeSpeciality() ? "Undo marking": "Mark as a special day");
        }
    }

    class BackButtonListener extends JButton implements  ActionListener
    {
        BackButtonListener()
        {
            super("<--");
            addActionListener(this);
            super.setBackground(Color.RED);
        }

        public void  actionPerformed(ActionEvent e)
        {
            if (setChangeable() && !justLooking)
            {
                if(JOptionPane.showConfirmDialog(this, "Are you sure you don't want to save") == 0)
                {
                    frameToOpen.setVisible(true);
                    frame.dispose();
                }
            }
            else
            {
                frameToOpen.setVisible(true);
                frame.dispose();
            }
        }
    }

    class accessButton extends JButton implements ActionListener
    {
        checkBoxFrame checkFrame;

        accessButton()
        {
            super("...");
            setBackground(Color.MAGENTA);
            super.addActionListener(this);
        }

        public Popup getPopUp()
        {
           return checkFrame.getPopUp();
        }


        public void actionPerformed(ActionEvent e)
        {
            if(checkFrame != null)
            {
                checkFrame.getPopUp().hide();
                checkFrame = null;
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

                checkFrame = new checkBoxFrame(friends,new Dimension(accessButton.getX() - 100, accessButton.getY() + 50));
                checkFrame.getPopUp().show();
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

        checkBoxFrame(ArrayList<String> strings, Dimension offSet)
        {
            popupMenu = new PopupFactory();
            setContent(strings);
            popup = popupMenu.getPopup(accessButton, bigPanel, offSet.width, offSet.height);
        }

        public void setContent(ArrayList<String> strings)
        {
            panel = new JPanel();
            panel.setLayout(new GridLayout(0,1));
            scrollPane = new JScrollPane(panel);
            bigPanel.add(scrollPane);
            checkBoxSetUp(strings);
            panel.add(new ButtonListener());
            panel.add(new specialButtonListener());
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

                if (people.contains(strings.get(i)))
                {
                    checkBox.setSelected(true);
                }

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

        private ArrayList<String> GetUnSelected()
        {
            ArrayList<String> returns = new ArrayList<String>();

            for (int i = 0; i < checkBoxes.size(); i++)
            {
                if (!checkBoxes.get(i).isSelected())
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
                super("Give/Take access");
                this.addActionListener(this);
            }

            public void actionPerformed(ActionEvent e)
            {
                Controller con = new Controller();
                for (String people: GetSelected())
                {
                    giveAccess(people);
                    con.shareDay(profile.getID(), con.getProfileByName(people).getID(), date[0] + "_" + date[1] + "_" + date[2]);
                }
                for (String people: GetUnSelected())
                {
                    takeAccess(people);
                    con.UnshareDay(profile.getID(), con.getProfileByName(people).getID(), date[0] + "_" + date[1] + "_" + date[2]);
                }
                getPopUp().hide();
            }
        }
    }

    class resizeListener implements ComponentListener
    {

        JPanel text;

        resizeListener(JPanel panel)
        {
            text = panel;
        }

        public void componentResized(ComponentEvent e)
        {
            text.setBounds(0,0,frame.getWidth(),frame.getHeight());
            backButton.setBounds(0,0,100,25);
            saveButton.setBounds(text.getWidth() - 110, text.getHeight() - 80,100,50);
            textPanel.setBounds(0,25, text.getWidth(), text.getHeight() - 100);
            dateLabel.setBounds(130,5,100,50);

            if(accessButton != null){
                accessButton.setBounds(text.getWidth() - 80 ,0,70,25);
            }
        }

        @Override
        public void componentMoved(ComponentEvent e) {}
        @Override
        public void componentShown(ComponentEvent e)
        {
            text.setBounds(0,0,frame.getWidth(),frame.getHeight());
            backButton.setBounds(0,0,100,25);
            saveButton.setBounds(text.getWidth() - 110, text.getHeight() - 80,100,50);
            textPanel.setBounds(0,25, text.getWidth(), text.getHeight() - 100);
            dateLabel.setBounds(130,5,100,50);

            if(accessButton != null){
                accessButton.setBounds(text.getWidth() - 80 ,0,70,25);
            }
        }
        @Override
        public void componentHidden(ComponentEvent e) {}
    }

    class TimerListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Scanner scan = new Scanner(textFile);
                isGroup = scan.nextLine().substring(1).equals("1");
                scan.close();

                if (isGroup)
                {
                    makeGroup();

                    if (!shown)
                    {
                        Scanner scaner = new Scanner(accessedPeopleFile);

                        people.clear();
                        while(scaner.hasNextLine())
                        {
                            people.add(scaner.nextLine());
                        }
                    }
                    setText(getText());
                    setShownText();
                }
            }
            catch (FileNotFoundException ex)
            {
                throw new RuntimeException(ex);
            }
        }
    }
}