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
    private boolean changable;
    private Diary diary;
    private boolean isSpecial;
    private JTextArea textArea;
    private int[] date = new int[3];
    private JFrame frame;

    public Text(int year, int month, int day, String profile, Diary diary, boolean isSpecial)
    {
        textFile = new File(FaceDiary.pathString + "\\" + profile + "\\" + year +  "_" + month + "_" + day);
        this.diary = diary;
        date[0] = year; date[1] = month; date[2] = day;
        this.isSpecial = isSpecial;
        
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

        textArea = new JTextArea(getText(), 50, 50);
        textArea.setEditable(setChangable());
        add(textArea);
        add(new buttonListener());
        add(new specialButtonListener());
        
    }
    
    public void setDayText(String str)
    {
        if (changable) 
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
            System.out.println("not changable");
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

        for(int i = 0; i < text.size(); i++)
        {
            str += text.get(i) + "\n";
        }

        return str;
    }

    public Diary getDiary()
    {
        return diary;
    }

    public void makeSpecial()
    {
        isSpecial = true;

        diary.addSpecial(date[0] + "_" + date[1] + "_" + date[2]);
    }

    public boolean setChangable()
    {
        if(Diary.currentDate.compareTo(new GregorianCalendar(date[0], date[1] - 1, date[2])) == 0)
        {
            changable = true;
        }
        else
        {
            changable = false;
        }

        return changable;
    }

    public boolean changeSpeciality()
    {
        if (isSpecial) 
        {
            isSpecial = false;
            diary.removeSpecial(date[0] +  "_" + date[1] + "_" + date[2]);
        }
        else
        {
            isSpecial = true;
            diary.addSpecial(date[0] +  "_" + date[1] + "_" + date[2]);
        }

        return isSpecial;
    }

    public Color getColor()
    {
        if(setChangable()) 
        {
            return Color.YELLOW;
        }

        if(isSpecial) 
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
            FaceDiary.facediary.setVisible(true);
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
}