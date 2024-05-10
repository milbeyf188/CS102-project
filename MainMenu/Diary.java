package MainMenu;



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
    private String profile;

    private int month;
    private int year;

    private JFrame frame;
    private ArrayList<buttonListener> currentButtons;

    public Diary(String profile, JFrame frame)
    {
        this.profile = profile;
        File textFolder = new File(test.pathString + "\\" + profile);
        textFolder.mkdirs();
        this.frame = frame;

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
            month = 11;
            year--;
        }
        else if(month > 11)
        {
            month = 0;
            year++;
        }

        this.month = month;
        this.year = year;

        Calendar timeToShow = new GregorianCalendar(this.year, this.month, 1);

        this.removeAll();
        this.currentButtons = new ArrayList<buttonListener>();

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
            buttonListener button = new buttonListener(year, month + 1, i);
            currentButtons.add(button);
            add(button);
        }

        for(int i = 0; i < 35 - timeToShow.getActualMaximum(Calendar.DAY_OF_MONTH) - timeToShow.get(Calendar.DAY_OF_WEEK); i++ )
        {
            add(new JPanel());
        }
    }

    class buttonListener extends JButton implements ActionListener
    {
        private Text text;

        public buttonListener(int year, int month, int day)
        {
            super(day + "");
            super.addActionListener(this);

            text = new Text(year, month, day, profile);
            this.setBackground(text.getColor());

            if(!days.contains(text)) 
            {
                days.add(text);
            }
        }

        public void setColor()
        {
            this.setBackground(text.getColor());
        }

        public void actionPerformed(ActionEvent e)
        {
            frame.setVisible(false);
            JFrame textFrame = new JFrame();
            text.setFrame(textFrame);
        }
    }

    public String getMonth()
    {
        return MONTHS[month];
    }

    public int getYear()
    {
        return year;
    }

    public void updateCalendar()
    {
        for(int i = 0; i < currentButtons.size(); i++)
        {
            currentButtons.get(i).setColor();
        }
    }

    public void GroupDiary(ArrayList<String> people)
    {
        for(int i = 0; i < days.size(); i++)
        {
            if(days.get(i).setChangeable())
            {
                days.get(i).setGroup();
                return;
            }
        }
    }
}