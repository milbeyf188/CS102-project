package MainMenu;

import CS_Project_Profile.Profile;
import FaceDiaryLoginIlbey.src.loginandsignup.Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
    private Profile profile;

    private int month;
    private int year;

    private JFrame frame;
    private ArrayList<buttonListener> currentButtons;

    public Diary(Profile profile, JFrame frame)
    {
        this.profile = profile;
        File textFolder = new File(MenuFrame.pathString + "\\" + profile.getName());
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
                days.get(i).setGroup(people);
                return;
            }
        }
    }

    public static ArrayList<Text> getDaysOf(Profile profile, String[] days)
    {
        ArrayList<Text> daysOf = new ArrayList<>();

        for (int i = 0; i < days.length; i++)
        {
            daysOf.add(new Text(days[i], profile));
        }

        return daysOf;
    }

    public int addStreak()
    {
        Controller con = new Controller();

        Calendar yesterDate = Calendar.getInstance();
        yesterDate.add(Calendar.DAY_OF_MONTH, -1);

        boolean streaking = !new Text(yesterDate.get(Calendar.YEAR), yesterDate.get(Calendar.MONTH) + 1, yesterDate.get(Calendar.DAY_OF_MONTH), profile).getColor().equals(Color.RED);

        if(!streaking){return -1;}

        String yesterday = yesterDate.get(Calendar.YEAR) + "_" + (yesterDate.get(Calendar.MONTH) + 1) + "_" + yesterDate.get(Calendar.DAY_OF_MONTH);

        if (con.getLastDay(profile.getID()) == null)
        {
            return 1;
        }

        return !con.getLastDay(profile.getID()).equals(yesterday)? 1: 0;
    }

    public static String getToday()
    {
        return currentDate.get(Calendar.YEAR) + "_" + (currentDate.get(Calendar.MONTH) + 1 )+ "_" + currentDate.get(Calendar.DAY_OF_MONTH);
    }

    public static String getYesterday()
    {
        Calendar yesterDate = Calendar.getInstance();
        yesterDate.add(Calendar.DAY_OF_MONTH, -1);

        return yesterDate.get(Calendar.YEAR) + "_" + (yesterDate.get(Calendar.MONTH) + 1 )+ "_" + yesterDate.get(Calendar.DAY_OF_MONTH);
    }
}