import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Text extends JPanel
{
    private ArrayList<String> text = new ArrayList<String>();
    private File textFile;
    private boolean changeable;
    private String specialAndGroup;
    private JTextArea textArea;
    private int[] date = new int[3];
    private JFrame frame;

    public Text(int year, int month, int day, String profile)
    {
        textFile = new File(test.pathString + "\\" + profile + "\\" + year +  "_" + month + "_" + day);
        date[0] = year; date[1] = month; date[2] = day;
        
        try{
            Scanner scan = new Scanner(textFile);
            
            while(scan.hasNextLine())
            {
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

        specialAndGroup = text.size() > 0? text.get(0): "00";
        text.set(0, specialAndGroup);
        textArea = new JTextArea(getText(), 50, 50);
        textArea.setEditable(setChangeable());
        add(textArea);
        add(new buttonListener());
        add(new specialButtonListener());
        
    }
    
    public void setDayText(String str)
    {
        if (changeable)
        {
            Scanner scanner = new Scanner(str);

            text.clear();
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
        if (specialAndGroup.substring(0,1).equals("0"))
        {
            specialAndGroup = "1" + specialAndGroup.substring(1);
            return true;
        }
        else
        {
            specialAndGroup = "0" + specialAndGroup.substring(1);
            return false;
        }
    }

    public Color getColor()
    {
        if(setChangeable())
        {
            return Color.YELLOW;
        }

        if(specialAndGroup.substring(0,1).equals("1"))
        {
            return Color.CYAN;    
        }

        if(Diary.currentDate.compareTo(new GregorianCalendar(date[0], date[1] - 1, date[2])) > 0) 
        {
            return text.size() < 1 ? Color.RED: Color.GREEN;
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
            super(specialAndGroup.substring(0,1).equals("1") ? "Unmake It Special": "Make It Special");
            super.addActionListener(this);            
        }

        public void actionPerformed(ActionEvent e)
        {
            super.setText(changeSpeciality() ? "Unmake It Special": "Make It Special");
        }
    }
}