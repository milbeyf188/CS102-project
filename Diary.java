import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Diary extends JPanel
{
    public static Calendar currentDate = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), 
        Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

    public static String[] DAYS = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    public static String[] MONTHS = new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    ArrayList<Text> days = new ArrayList<Text>();
    ArrayList<String> specialDays = new ArrayList<String>();
    private String profile; 

    private File speciaFile;

    private int month;
    private int year;

    private JFrame frame;

    public Diary(String profile, JFrame frame)
    {
        this.profile = profile;
        File textFolder = new File(FaceDiary.pathString + "\\" + profile);
        textFolder.mkdirs();
        this.frame = frame;
        this.speciaFile = new File(FaceDiary.pathString + "\\" + profile + "\\special");

        try{
            Scanner specialScanner = new Scanner(this.speciaFile);

            while (specialScanner.hasNextLine()) 
            {
                specialDays.add(specialScanner.nextLine());
            }

            specialScanner.close();
        }
        catch(FileNotFoundException e)
        {
            try{
                this.speciaFile.createNewFile();
            }
            catch(IOException io)
            {
                System.out.println("F");
            }
        }

        this.setLayout(new GridLayout(6,7));

        showMonth(currentDate.get(Calendar.MONTH), currentDate.get(Calendar.YEAR));
    }

    public void MonthForwardOrBack(boolean forward)
    {
        this.removeAll();

        if(forward)
        {
            showMonth(++month, year);
        }
        else
        {
            showMonth(--month, year);
        }
    }

    public void showMonth(int month, int year)
    {
        if(month < 0)
        {
            month = 12;
            year--;
        }
        else if(month > 12)
        {
            month = 1;
            year++;
        }

        this.month = month;
        this.year = year;

        Calendar timeToShow = new GregorianCalendar(this.year, this.month, 1);

        this.removeAll();

        for(int i = 0; i < Calendar.DAY_OF_WEEK; i++) 
        {
            JLabel dayLabel = new JLabel(DAYS[i], (int)JLabel.CENTER_ALIGNMENT);
            dayLabel.setSize(10, 100);
            add(dayLabel);
        }

        for(int i = 2; i < timeToShow.get(Calendar.DAY_OF_WEEK); i++) 
        {
            add(new JPanel());
        }

        for(int i = 1; i < timeToShow.getActualMaximum(Calendar.DAY_OF_MONTH) + 1; i++)
        {
            add(new buttonListener(year, month + 1, i, this));
        }

        for(int i = 0; i < 35 - timeToShow.getActualMaximum(Calendar.DAY_OF_MONTH) - timeToShow.get(Calendar.DAY_OF_WEEK); i++ )
        {
            add(new JPanel());
        }
    }

    class buttonListener extends JButton implements ActionListener
    {
        private Text text;

        public buttonListener(int year, int month, int day, Diary diary)
        {
            super(day + "");
            super.addActionListener(this);

            text = new Text(year, month, day, profile, diary, specialDays.contains(year + "_" + month + "_" + day));
            this.setBackground(text.getColor());

            if(!days.contains(text)) 
            {
                days.add(text);
            }
        }

        public void actionPerformed(ActionEvent e)
        {
            frame.setVisible(false);
            JFrame textFrame = new JFrame();
            text.setFrame(textFrame);
        }
    }

    public void addSpecial(String date)
    {
        specialDays.add(date);

        try{
            Files.write(Paths.get(speciaFile.getAbsolutePath()), specialDays, StandardCharsets.UTF_8);
        }
        catch(IOException ex)
        {
            System.out.println("cant change special day folder");
        }
    }

    public void removeSpecial(String date)
    {
        specialDays.remove(date);
    } 

    public String getMonth()
    {
        return MONTHS[month];
    }

    public int getYear()
    {
        return year;
    }
}