import static javax.swing.JOptionPane.ERROR_MESSAGE;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;


import CS_Project_Profile.Profile;
import loginandsignup.Controller;
public class MenuFrame extends JFrame{
    private Profile profile;
    private JLabel month;
    private JPanel monthpanel;

    private Diary diary; 

   
    private JTextField searchfriend;
    private JTextField searchadd;
    private ResultPanel resultpanel;
    private JScrollPane scrollPane;
    private JFrame frame = this;

    protected Color lightblue = new Color(62, 128, 168);
    protected Color backgroundColor = new Color(8, 32, 45);
    protected Font buttonfont = new Font("Messi", 0, 30);
    public MenuFrame(Profile profile)
    {
        this.profile = profile;
        diary = new Diary(profile.getName(), this);
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setName("Main menu");
        Panel panel = new Panel();
        this.add(panel);
        pack();
        
        setVisible(true);
    }
    class Panel extends JPanel
    {
        public Panel()
        {
            setLayout(new GridBagLayout());
            createcomponents();
            repaint();
        }
        private void createcomponents()
        {
        JPanel panel = new JPanel(new GridLayout(1,3));
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.NORTHWEST;
        JButton buttonprev = new JButton("Prev month");
        buttonprev.addActionListener(new Listener2());
        JButton buttonnext = new JButton("Next month");
        buttonnext.addActionListener(new Listener1());
        panel.add(buttonprev);
        
        monthpanel = new JPanel();
        monthpanel.setLayout(new BorderLayout());
        month = new JLabel(diary.getMonth() + " / " + diary.getYear(),(int)JLabel.CENTER_ALIGNMENT);

        monthpanel.add(month,BorderLayout.CENTER);
        panel.add(monthpanel);//Month will be displayed
        panel.add(buttonnext);
        panel.setPreferredSize(new Dimension(400,100));
        this.add(panel,c);
        diary.setPreferredSize(new Dimension(400, 300));
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0;
        c.weighty = 1;
        this.add(diary,c);
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 0;
        c.weighty = 1;
        JPanel badgesPanel = new JPanel();//a panel to add badges
        badgesPanel.setPreferredSize(new Dimension(400, 200));
        add(badgesPanel,c);
        c.gridx = 0;
        c.gridy = 3;
        c.weightx = 0;
        c.weighty = 1;
        MoneyPanel moneyPanel = new MoneyPanel();
        moneyPanel.setPreferredSize(new Dimension(400, 100));
        add(moneyPanel,c);
        RoundedButton button = new RoundedButton(300, 75, "Badge Shop","Shopping Cart.png" );
        c.gridx = 0;
        c.gridy = 4;
        c.weighty = 1;
        c.weightx = 1;
        add(button,c);        
        RoundedButton button2 = new RoundedButton(300, 75, "New Diary", null);
        button2.setPreferredSize(new Dimension(300, 75));
        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.WEST;
        add(button2, c);
        RoundedButton button3 = new RoundedButton(300, 75, "New Group Diary", null);
        c.gridx = 1;
        c.gridy = 2;
        c.weighty = 0;
        c.weightx = 0;
        c.anchor = GridBagConstraints.NORTHWEST;
        add(button3,c);
        StreakPanel streakPanel = new StreakPanel();
        streakPanel.setPreferredSize(new Dimension(200, 100));
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.NORTH;
        add(streakPanel,c);
        FriendPanel friendPanel = new FriendPanel();
        c.gridx = 2;
        c.gridheight = 5;
        c.anchor = GridBagConstraints.NORTHEAST;
        c.fill = GridBagConstraints.VERTICAL;
        add(friendPanel,c);
        friendPanel.createPanel();
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        this.setBackground(backgroundColor);
    }
    }
    /*
     * overrides Jpanel class to add money image and display money amount of user in main menu
     */
    class MoneyPanel extends JPanel
    {
        public void paintComponent(Graphics g)
        {
            Image image = Toolkit.getDefaultToolkit().getImage("Money Image.png");
            super.paintComponent(g);
            setBackground(backgroundColor);
            g.drawImage(image,10,10,this);
            g.setFont(new Font("Comic sans", 0, 30));
            g.setColor(Color.WHITE);
            g.drawString(String.valueOf(profile.getMoney()),100,50);
        }
    }
    class RoundedButton extends JButton
    {
        private int height;
        private String label;
        private int width;
        private final int ovality = 40;
        private Image image;
        public RoundedButton(int width,int height,String label,String filename)
        {
            this.height = height;
            this.width = width;
            this.label = label;
            setPreferredSize(new Dimension(width, height));
            if(filename != null)
            {
                image =  Toolkit.getDefaultToolkit().getImage(filename);
            }
            repaint();
        }
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            setBackground(backgroundColor);
            g.setColor(lightblue);
            g.fillOval(0, 0,ovality ,height);
            g.fillOval(260, 0, ovality,height);
            g.fillRect(ovality/2, 0, width-ovality, height);
            setBorderPainted(false);
            g.drawImage(image,200,5,null);
            g.setColor(Color.WHITE);
            g.setFont(buttonfont);
            g.drawString(label, 10, 40);
            
        }
    }

    //proper actionlistener should be implemented
    class StreakPanel extends JPanel
    {
        public void paintComponent(Graphics g)
        {
            Image image = Toolkit.getDefaultToolkit().getImage("FİreImage.png");
            super.paintComponent(g);
            setBackground(backgroundColor);
            g.drawImage(image,10,10,this);
            Font f = buttonfont.deriveFont(50);
            g.setFont(f);
            g.setColor(Color.WHITE);
            g.drawString(String.valueOf(profile.getStreak()),100,50);
        }
    }
    class FriendPanel extends JPanel
    {

        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            g.setFont(buttonfont);
            g.drawString(profile.getName(), 20, 40);
        }
        public void createPanel()
        {
            setLayout(new GridLayout(7,1));
            JLabel label = new JLabel(profile.getName(),(int)JLabel.CENTER_ALIGNMENT);
            label.setFont(buttonfont);
            add(label);
            JPanel panel = new JPanel(new GridLayout(1, 2));
            panel.add(new JLabel("Enter a name for searching in your friends!"));
            searchfriend = new JTextField(30);
            panel.add(searchfriend);
            add(panel);
            JPanel panel5 = new JPanel(new GridBagLayout());
            GridBagConstraints con = new GridBagConstraints();
            con.anchor = GridBagConstraints.CENTER;
            JButton button1 = new JButton("Search");
            panel5.add(button1,con);
            add(panel5);
            JPanel panel7 = new JPanel();//searching among friends
            
            add(panel7);
            JPanel panel2 = new JPanel(new GridLayout(1, 2));
            panel2.add(new JLabel("Enter a name for searching among users to add friend!"));
            
            searchadd = new JTextField(30);
            panel2.add(searchadd);
            add(panel2);
            JButton button3 = new JButton("Search");
            Listener3 listener3 = new Listener3();
            button3.addActionListener(listener3);
            JPanel panel4 = new JPanel(new GridBagLayout());
            GridBagConstraints a = new GridBagConstraints();
            a.anchor = GridBagConstraints.CENTER;
            panel4.add(button3,a);
            add(panel4);
            resultpanel = new ResultPanel();//result area for adding friends
            scrollPane = new JScrollPane();
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            add(scrollPane);
            
        }
    }
    class Listener1 implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            diary.MonthForwardOrBack(true);
            month.setText(diary.getMonth() + " / " + diary.getYear());
        }
    }
    class Listener2 implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            diary.MonthForwardOrBack(false);
            month.setText(diary.getMonth() + " / " + diary.getYear());
        }
    }
    //Action listener of friend adding searchbar
    class Listener3 implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            scrollPane.setViewportView(null);
            if(searchadd.getText().length() == 0)
            {
                JOptionPane.showMessageDialog(new JFrame(), "Name cannot be empty", "Error", ERROR_MESSAGE);
            }
            else
            {
            Controller con = new Controller();
            ArrayList<String> search = con.getNamesArray();
            ArrayList<Integer> number = con.getIDArray();
            ArrayList<String> result = new ArrayList<String>();
            ArrayList<Integer> numberresult = new ArrayList<Integer>();
            for(int i = 0;i<search.size();i++)
            {
                if(search.get(i).contains(searchadd.getText()))
                {
                    result.add(search.get(i));
                    numberresult.add(number.get(i));
                }
            }
            
            resultpanel.removeAll();
            resultpanel.changeresult(result,numberresult);
            resultpanel.printusers();
            scrollPane.setViewportView(resultpanel);
            }
            
        }
    }
    class ResultPanel extends JPanel
    {
        private ArrayList<String> results;
        private ArrayList<Integer> numbers;
        public void changeresult(ArrayList<String> arr,ArrayList<Integer> arr2)
        {
            results = arr;
            numbers = arr2;
        }
        public void printusers()
        {
            setLayout(new GridLayout(0, 1));
            if(results.size() == 0)
            {
                JOptionPane.showMessageDialog(frame, "There is no such user!");
            }
            else
            {
            for(int i = 0;i<results.size();i++)
            {
                add(new ProfileButton(results.get(i),numbers.get(i)));
            }
            
            frame.setVisible(true);
            }
        }
    }
    
    class ProfileButton extends JButton implements ActionListener
    {
        private String name;
        private int ID;
        public ProfileButton(String name,int ID)
        {
            super(name);
            this.ID = ID;
        }
        public void actionPerformed(ActionEvent event)//action listener for adding friends with ıd giver        
        {
            
        }
    }
}

