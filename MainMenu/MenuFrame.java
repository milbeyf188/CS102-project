package MainMenu;

import static javax.swing.JOptionPane.ERROR_MESSAGE;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import CS_Project_Profile.*;
import FaceDiaryLoginIlbey.src.loginandsignup.*;

public class MenuFrame extends JFrame{
    private Profile profile;
    private JLabel month;
    private JPanel monthpanel;

    private Diary diary; 

   
    private JTextField searchfriend;
    private JTextField searchadd;
    private ResultPanel resultpanel;
    private FriendPanel2 friendPanel2;
    private JScrollPane scrollPane;
    private JScrollPane scrollPane2;
    private static Controller con = new Controller();
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
        RoundedButton button = new RoundedButton(300, 75, "Badge Shop","/MainMenu/Shopping Cart.png" );
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
        c.gridx = 1;
        c.gridy = 3;
        c.weightx = 0;
        c.weighty = 0;
        RoundedButton button4 = new RoundedButton(300, 75, "Remove Friend", null);
        button4.addActionListener(new Listener5());
        add(button4,c);
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
            ImageIcon image = new ImageIcon(getClass().getResource("/MainMenu/Money Image.png"));
            super.paintComponent(g);
            setBackground(backgroundColor);
            Graphics2D g2d = (Graphics2D) g;
            image.paintIcon(this, g2d, 10, 10);
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
            ImageIcon image =  new ImageIcon(getClass().getResource("/MainMenu/FireImage.png"));//DENİZ KOD BURDA
            super.paintComponent(g);
            setBackground(backgroundColor);
            Graphics2D g2d = (Graphics2D) g;
            image.paintIcon(this, g2d, 10,10 );
            Font f = buttonfont.deriveFont(50);
            g.setFont(f);
            g.setColor(Color.WHITE);
            g.drawString(String.valueOf(profile.getStreak()),100,50);
        }
    }
    class FriendPanel extends JPanel//A general panel at the right of the screen
    {

        public void createPanel()
        {
            setLayout(new GridLayout(7,1));
            
            FriendButton button4 = new FriendButton(profile.getName(), profile.getID(), true);
            button4.setPreferredSize(new Dimension(75, 25));
            button4.setFont(buttonfont);
            add(button4);//button to return our profile
            JPanel panel = new JPanel(new GridLayout(1, 2));
            panel.add(new JLabel("Enter a name for searching in your friends!"));
            searchfriend = new JTextField(30);
            panel.add(searchfriend);
            add(panel);
            JPanel panel5 = new JPanel(new GridBagLayout());
            GridBagConstraints con = new GridBagConstraints();
            con.anchor = GridBagConstraints.CENTER;
            JButton button1 = new JButton("Search");
            button1.addActionListener(new Listener4());
            panel5.add(button1,con);
            add(panel5);
            friendPanel2 = new FriendPanel2();
            scrollPane2 = new JScrollPane(friendPanel2);//searching among friends
            scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            add(scrollPane2);
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
            FDController con = new FDController();
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
        private int ID;
        public ProfileButton(String name,int ID)
        {
            super(name);
            this.ID = ID;
            addActionListener(this);
        }
        public void actionPerformed(ActionEvent event)//action listener for adding friends with ıd giver        
        {
            con.setFriend(profile.getID(), ID);
        }
    }
    class FriendButton extends JButton implements ActionListener
    {
        private int ID;
        private boolean isuser;
        public FriendButton(String s,int id, boolean isuser)
        {
            super(s);
            this.ID = id;
            this.isuser = isuser;
            addActionListener(this);
        }
        public void actionPerformed(ActionEvent e) {//yiğitin profil guisine gidecek
            Profile profile = new Profile(ID,con.getNameById(ID) , con.getUserStreakById(ID), con.getStatue(ID), con.getBirthday(ID),con.getUserPointsById(ID));
            Profile_GUI profilepage =  new Profile_GUI(isuser, profile);
            frame.setVisible(false);
            profilepage.setVisible(true);
        }
    }
    
    public class FriendPanel2 extends JPanel
    {
        private ArrayList<Integer> results;
        private JFrame displayframe;
        public void changeresult(ArrayList<Integer> arr)
        {
            results = arr;
        }
        public void printusers()
        {
            displayframe = frame;
            setLayout(new GridLayout(0,1));
            if(results.size() == 0)
            {
                JOptionPane.showMessageDialog(displayframe, "You haven't got any friends yet.");
            }
            for(int i = 0;i<results.size();i++)
            {
                add(new FriendButton(con.getNameById(results.get(i)),results.get(i),false));
            }
            frame.setVisible(true);
        }   
        
    }
    class Listener4 implements ActionListener//Action listener for searching user among friends
    {
        public void actionPerformed(ActionEvent event)
        {
            scrollPane2.setViewportView(null);
            ArrayList<Integer> friends = con.getFriendsArray(profile.getID());
            ArrayList<Integer> results = new ArrayList<Integer>();
            for(int i = 0;i<friends.size();i++)
            {
                if(con.getNameById(friends.get(i)).contains(searchfriend.getText()))
                {
                    results.add(friends.get(i));
                }
            }
            friendPanel2.removeAll();
            friendPanel2.changeresult(results);
            friendPanel2.printusers();
            scrollPane2.setViewportView(friendPanel2);

        }
    }
    class Listener5 implements ActionListener//Action listener of removeframe buttton
    {
        public void actionPerformed(ActionEvent event)
        {
            RemoveFrame frame2 = new RemoveFrame(profile.getID());
            frame2.setVisible(true);
            frame.setVisible(false);

        }
    }
    class PopUp extends JDialog
    {
        private JFrame frame;
        private JTextField searcharea;
        private JButton cancel;
        private JButton remove;
        private JPanel result;
        private JButton searchButton;
        public PopUp(JFrame parent)
        {
            super(parent, "Custom Popup", true);
            JPanel panel = new JPanel(new GridLayout(3,1));
            searcharea = new JTextField(20);
            panel.add(searcharea);
            JPanel panel2 = new JPanel(new GridBagLayout());
            


        }
    }
}

