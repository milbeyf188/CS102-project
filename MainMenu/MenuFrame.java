package MainMenu;

import static javax.swing.JOptionPane.ERROR_MESSAGE;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.*;
import CS_Project_Profile.*;
import FaceDiaryLoginIlbey.src.loginandsignup.*;

public class MenuFrame extends JFrame {
    public static Profile profile;
    private JLabel month;
    private JPanel monthpanel;

    private Diary diary;
    private JButton button1;//search button for searchin among friends
    public static String pathString;
    public static MenuFrame facediary;
    private RoundedButton groupdiarybutton;
    private JTextField searchfriend;//a text field of searhing among friends
    private JTextField searchadd;
    private ResultPanel resultpanel;
    private FriendPanel2 friendPanel2;
    private JScrollPane scrollPane;
    private JScrollPane scrollPane2;
    private JButton searchButton;//searchbutton of removefriend popup
    public static Controller con = new Controller();

    private MenuFrame frame = this;
    private Timer timer = new Timer(1000, new TimerListener());

    protected Color lightblue = new Color(62, 128, 168);
    public static final Color backgroundColor = new Color(8, 32, 45);
    protected Font buttonfont = new Font("Messi", 0, 30);
    String[] badgeFilenames = {
        "",
        "/MainMenu/Badge PNGs/Bronze1.png",
        "/MainMenu/Badge PNGs/Bronze2.png",
        "/MainMenu/Badge PNGs/Bronze3.png",
        "/MainMenu/Badge PNGs/Silver1.png",
        "/MainMenu/Badge PNGs/Silver2.png",
        "/MainMenu/Badge PNGs/Silver3.png",
        "/MainMenu/Badge PNGs/Gold1.png",
        "/MainMenu/Badge PNGs/Gold2.png",
        "/MainMenu/Badge PNGs/Gold3.png",
        "/MainMenu/Badge PNGs/Diamond1.png",
        "/MainMenu/Badge PNGs/Diamond2.png",
        "/MainMenu/Badge PNGs/Diamond3.png",
        "/MainMenu/Badge PNGs/Immortal1.png",
        "/MainMenu/Badge PNGs/Immortal2.png",
        "/MainMenu/Badge PNGs/Immortal3.png"
};
    public MenuFrame(Profile profile) {
        facediary = this;
        File myfile = new File("FaceDiary");
        myfile.mkdir();
        pathString = myfile.getAbsolutePath();
        this.profile = profile;
        diary = new Diary(profile, this);// Diary burda çağrılıyor
        timer.start();

        profile.setStreak();

        setSize(800, 800);
        this.setPreferredSize(new Dimension(1600, 800));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setName("Main menu");
        Panel panel = new Panel();
        this.add(panel);
        pack();

        setVisible(true);
    }

    class Panel extends JPanel {
        public Panel() {
            setLayout(null);
            createcomponents();
            repaint();
        }

        private void createcomponents() {
            JPanel panel = new JPanel(new GridLayout(1, 3));
            GridBagConstraints c = new GridBagConstraints();
            c.weightx = 1;
            c.weighty = 1;
            c.anchor = GridBagConstraints.NORTHWEST;
            JButton buttonprev = new JButton("Prev month");
            buttonprev.addActionListener(new Listener2());
            buttonprev.setForeground(backgroundColor);
            buttonprev.setBackground(Color.YELLOW);
            JButton buttonnext = new JButton("Next month");
            buttonnext.addActionListener(new Listener1());
            buttonnext.setForeground(backgroundColor);
            buttonnext.setBackground(Color.YELLOW);
            panel.add(buttonprev);

            monthpanel = new JPanel();
            monthpanel.setLayout(new BorderLayout());
            monthpanel.setBackground(MenuFrame.backgroundColor);
            month = new JLabel(diary.getMonth() + " / " + diary.getYear(), (int) JLabel.CENTER_ALIGNMENT);
            month.setForeground(Color.WHITE);
            monthpanel.add(month, BorderLayout.CENTER);
            panel.add(monthpanel);// Month will be displayed
            panel.add(buttonnext);
            
            panel.setBounds(0, 0, 400, 30);//Alperen Gardaşım
            this.add(panel);
            //diary.setPreferredSize(new Dimension(400, 300));
            diary.setBounds(0, 30,400,300);
            this.add(diary);
            
            JPanel badgepanel = new JPanel();
            badgepanel.setBackground(backgroundColor);
            boolean[] badges = con.getBadgesArrayById(profile.getID());
            int counter = 0;
            for(int i = 1;i<badges.length;i++)
            {
                if(badges[i])
                {
                    counter++;
                }
            }
            int panelnumber = (int)Math.ceil(counter / 3.0);
            badgePanel badgesPanel = new badgePanel();// a panel to add badges
            badgesPanel.setBounds(0, 330, 420,panelnumber*85);
            badgepanel.add(badgesPanel);
            add(badgesPanel);
            MoneyPanel moneyPanel = new MoneyPanel();
            
            moneyPanel.setBounds(510, 200, 80, 100);
            add(moneyPanel);
            RoundedButton button = new RoundedButton(300, 75, "Badge Shop", null);
            button.addActionListener(new Listener8());
            button.setBounds(600, 200, 300, 75);
            add(button);
            
            c.anchor = GridBagConstraints.WEST;
            groupdiarybutton = new RoundedButton(300, 75, "New Group Diary", null);
            groupdiarybutton.addActionListener(new Listener9());
            groupdiarybutton.setBounds(600, 400, 300, 75);
            c.anchor = GridBagConstraints.NORTHWEST;
            add(groupdiarybutton, c);
            RoundedButton button4 = new RoundedButton(300, 75, "Remove Friend", null);
            button4.addActionListener(new Listener5());
            button4.setBounds(600, 600, 300, 75);
            add(button4);
            StreakPanel streakPanel = new StreakPanel();
            streakPanel.setBounds(400, 0, 200, 100);
            //streakPanel.setPreferredSize(new Dimension(200, 100));

            c.anchor = GridBagConstraints.NORTH;
            add(streakPanel);
            FriendPanel friendPanel = new FriendPanel();
            friendPanel.setBounds(1100, 0, 430, 775);
            c.anchor = GridBagConstraints.NORTHEAST;
            c.fill = GridBagConstraints.VERTICAL;
            add(friendPanel);
            friendPanel.createPanel();
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            this.setBackground(backgroundColor);
        }
    }

    class badgePanel extends JPanel// A panel for displaying badges
    {
        public badgePanel() {
            createcomponents();
        }

        boolean[] badges = con.getBadgesArrayById(profile.getID());
        String[] badgeFilenames = {
                "",
                "/MainMenu/Badge PNGs/Bronze1.png",
                "/MainMenu/Badge PNGs/Bronze2.png",
                "/MainMenu/Badge PNGs/Bronze3.png",
                "/MainMenu/Badge PNGs/Silver1.png",
                "/MainMenu/Badge PNGs/Silver2.png",
                "/MainMenu/Badge PNGs/Silver3.png",
                "/MainMenu/Badge PNGs/Gold1.png",
                "/MainMenu/Badge PNGs/Gold2.png",
                "/MainMenu/Badge PNGs/Gold3.png",
                "/MainMenu/Badge PNGs/Diamond1.png",
                "/MainMenu/Badge PNGs/Diamond2.png",
                "/MainMenu/Badge PNGs/Diamond3.png",
                "/MainMenu/Badge PNGs/Immortal1.png",
                "/MainMenu/Badge PNGs/Immortal2.png",
                "/MainMenu/Badge PNGs/Immortal3.png"
        };

        private void createcomponents() {
            setBackground(backgroundColor);
            int counter = 0;
            int index = 0;
            ArrayList<Integer> results = new ArrayList<Integer>();
            for (int i = badges.length - 1; i > 0; i--) {
                if (badges[i]) {
                    counter++;
                    results.add(i);
                }
            }
            index = results.size()-1;
            if(results.size() == 0)
            {
                return;
            }
            int panelnumber = (int)Math.ceil(counter / 3.0);
            setLayout(new GridLayout(panelnumber,1));
            ArrayList<JPanel> panels = new ArrayList<JPanel>();
            for(int i = 0;i<panelnumber;i++)
            {
                panels.add(new JPanel());
            }
            for(int c = 0;c<panelnumber;c++)
            {
                if(counter >= 3)
                {
                    adjust(panels.get(c), index,3 , results);
                    counter -= 3;
                    index -= 3;
                    add(panels.get(c));
                }
                else
                {
                    adjust(panels.get(c), index, counter, results);
                    counter = 0;
                    index = 0;
                    add(panels.get(c));
                }
            }
        }

    }
    public void adjust(JPanel panel,int lastindex,int number,ArrayList<Integer> results)
    {
                panel.setLayout(new GridLayout(1, number));
                panel.setBackground(backgroundColor);
                for (int i = lastindex; i > lastindex-number; i--) {
                    ImageIcon badgeImage = new ImageIcon(getClass().getResource(badgeFilenames[results.get(i)]));
                    Image scaledBadgeImage = badgeImage.getImage().getScaledInstance(100, 85, Image.SCALE_SMOOTH);
                    ImageIcon scaledBadgeIcon = new ImageIcon(scaledBadgeImage);
                    panel.add(new JLabel(scaledBadgeIcon));
                }
    }
    /*
     * overrides Jpanel class to add money image and display money amount of user in
     * main menu
     */

    class MoneyPanel extends JPanel {
            public MoneyPanel()
            {
            ImageIcon moneyimage= new ImageIcon(getClass().getResource("/MainMenu/MoneyIcon.png"));
            Image scaledMoneyImage = moneyimage.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            ImageIcon scaledBadgeIcon = new ImageIcon(scaledMoneyImage);
            setBackground(backgroundColor);
            setLayout(new GridLayout(2, 1));
            JLabel label = new JLabel(scaledBadgeIcon);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.BOTTOM);
            add(label);
            JLabel label2 = new JLabel(String.valueOf(profile.getMoney()));
            label2.setForeground(Color.WHITE);
            label2.setFont(buttonfont);
            label2.setHorizontalAlignment(SwingConstants.CENTER);
            label2.setVerticalAlignment(SwingConstants.TOP);
            add(label2);
            }
            
        

    }

    class RoundedButton extends JButton {
        private int height;
        private String label;
        private int width;
        private final int ovality = 40;
        private Image image;

        public RoundedButton(int width, int height, String label, String filename) {
            this.height = height;
            this.width = width;
            this.label = label;
            setPreferredSize(new Dimension(width, height));
            if (filename != null) {
                image = Toolkit.getDefaultToolkit().getImage(filename);
            }
            repaint();
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            setBackground(backgroundColor);
            g.setColor(lightblue);
            g.fillOval(0, 0, ovality, height);
            g.fillOval(260, 0, ovality, height);
            g.fillRect(ovality / 2, 0, width - ovality, height);
            setBorderPainted(false);
            g.drawImage(image, 200, 5, null);
            g.setColor(Color.WHITE);
            g.setFont(buttonfont);
            g.drawString(label, 10, 40);

        }
    }

    // proper actionlistener should be implemented
    class StreakPanel extends JPanel {
        public void paintComponent(Graphics g) {
            
            super.paintComponent(g);
            setBackground(backgroundColor);
            ImageIcon fireimage = new ImageIcon(getClass().getResource("/MainMenu/FirePhoto.png"));
            Image scaledfireImage = fireimage.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
            ImageIcon scaledfireicon = new ImageIcon(scaledfireImage);
            Graphics2D g2d = (Graphics2D) g;
            scaledfireicon.paintIcon(this, g2d, 0,0 );
            Font f = new Font("Courier", Font.BOLD | Font.ITALIC ,30);

            g.setFont(f);
            g.setColor(Color.DARK_GRAY);
            String streak = String.valueOf(profile.getStreak());
            g.drawString(streak, 35 - streak.length() * 5, 58);
        }
    }

    class FriendPanel extends JPanel// A general panel at the right of the screen
    {

        public void createPanel() {
            setLayout(new GridLayout(7, 1));

            FriendButton button4 = new FriendButton(profile.getName(), profile.getID(), true);
            button4.setBackground(lightblue);
            button4.setPreferredSize(new Dimension(75, 25));
            button4.setFont(buttonfont);
            add(button4);// button to return our profile
            JPanel panel = new JPanel(new GridLayout(1, 2));
            panel.setBackground(new Color(235,200,178));
            searchfriend = new JTextField(30);
            panel.add(searchfriend);
            add(panel);
            JPanel panel5 = new JPanel(new GridBagLayout());
            panel5.setBackground(new Color(235,200,178));
            GridBagConstraints con1 = new GridBagConstraints();
            con1.anchor = GridBagConstraints.CENTER;
            button1 = new JButton("Search Friend");//button for searching friend
            button1.setBackground(Color.LIGHT_GRAY);
            button1.addActionListener(new Listener4());
            panel.add(button1, con1);

            friendPanel2 = new FriendPanel2();
            scrollPane2 = new JScrollPane(friendPanel2);// searching among friends
            scrollPane2.setBackground(new Color(235,200,178));
            scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            add(scrollPane2);
            add(panel5);

            //JLabel label1 = new JLabel("Find a new Friend");
            //label1.setFont(buttonfont);
            //panel2.add(label1);

            JPanel panel2 = new JPanel(new GridLayout(1, 2));
            searchadd = new JTextField(30);
            panel2.add(searchadd);
            add(panel2);
            JButton button3 = new JButton("Find a new Friend");
            button3.setBackground(Color.LIGHT_GRAY);
            Listener3 listener3 = new Listener3();
            button3.addActionListener(listener3);
            JPanel panel4 = new JPanel(new GridBagLayout());
            panel4.setBackground(new Color(235,200,178));
            GridBagConstraints a = new GridBagConstraints();
            a.anchor = GridBagConstraints.CENTER;
            panel2.add(button3, a);
            resultpanel = new ResultPanel();// result area for adding friends
            resultpanel.setBackground(new Color(235,200,178));
            scrollPane = new JScrollPane();
            scrollPane.setBackground(new Color(235,200,178));
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            add(scrollPane);
            add(panel4);

            if(con.getFriendsArray(profile.getID()).size() != 0)
            {
                searchfriend.setText("");
                button1.doClick();
            }
            

        }
    }

    class Listener1 implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            diary.MonthForwardOrBack(true);
            month.setText(diary.getMonth() + " / " + diary.getYear());
        }
    }

    class Listener2 implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            diary.MonthForwardOrBack(false);
            month.setText(diary.getMonth() + " / " + diary.getYear());
        }
    }

    // Action listener of friend adding searchbar
    class Listener3 implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            scrollPane.setViewportView(null);
            if (searchadd.getText().length() == 0) {
                JOptionPane.showMessageDialog(new JFrame(), "Name cannot be empty", "Error", ERROR_MESSAGE);
            } else {
                Controller con = new Controller();
                ArrayList<String> search = con.getNamesArray();
                ArrayList<Integer> number = con.getIDArray();
                ArrayList<String> result = new ArrayList<String>();
                ArrayList<Integer> numberresult = new ArrayList<Integer>();
                for (int i = 0; i < search.size(); i++) {
                    if (search.get(i).contains(searchadd.getText()) && number.get(i) != profile.getID()) {
                        result.add(search.get(i));
                        numberresult.add(number.get(i));
                    }
                }

                resultpanel.removeAll();
                resultpanel.changeresult(result, numberresult);
                resultpanel.printusers();
                scrollPane.setViewportView(resultpanel);
            }

        }
    }

    class ResultPanel extends JPanel {
        private ArrayList<String> results;
        private ArrayList<Integer> numbers;

        public void changeresult(ArrayList<String> arr, ArrayList<Integer> arr2) {
            results = arr;
            numbers = arr2;
        }

        public void printusers() {
            setLayout(new GridLayout(0, 1));
            if (results.size() == 0) {
                JOptionPane.showMessageDialog(frame, "There is no such user!");
            } else {
                for (int i = 0; i < results.size(); i++) {
                    add(new ProfileButton(results.get(i), numbers.get(i)));
                }

                frame.setVisible(true);
            }
        }
    }

    class ProfileButton extends JButton implements ActionListener {
        private int ID;

        public ProfileButton(String name, int ID) {
            super(name);
            this.ID = ID;
            addActionListener(this);
            setBackground(lightblue);
        }

        public void actionPerformed(ActionEvent event)// action listener for adding friends with ıd giver
        {
            ArrayList<Integer> friends = con.getFriendsArray(profile.getID());
            if(friends.contains(ID))
            {
                JOptionPane.showMessageDialog(frame, "This user is already your friend", "Friend adding",
                    JOptionPane.ERROR_MESSAGE);
            }
            else
            {
            con.setFriend(profile.getID(), ID);
            JOptionPane.showMessageDialog(frame, "User is added successfully", "Friend adding",
                JOptionPane.INFORMATION_MESSAGE);
            }
            searchfriend.setText("");
            button1.doClick();
        }
    }

    class FriendButton extends JButton implements ActionListener {
        private int ID;
        private boolean isuser;

        public FriendButton(String s, int id, boolean isuser) {
            super(s);
            this.ID = id;
            this.isuser = isuser;
            addActionListener(this);
            setBackground(lightblue);
        }

        public void actionPerformed(ActionEvent e) {// yiğitin profil guisine gidecek
            Profile profile1 = new Profile(ID, con.getNameById(ID), con.getUserStreakById(ID), con.getStatue(ID),
                    con.getBirthday(ID), con.getUserPointsById(ID));
            Profile_GUI profilepage = new Profile_GUI(isuser, profile1, frame,profile.getID());
            frame.setVisible(false);
            profilepage.setVisible(true);
        }
    }

    public class FriendPanel2 extends JPanel {
        private ArrayList<Integer> results;
        private JFrame displayframe;

        public void changeresult(ArrayList<Integer> arr) {
            results = arr;
        }

        public void printusers() {
            displayframe = frame;
            setBackground(new Color(235,200,178));
            setLayout(new GridLayout(0, 1));
            if (results.size() == 0) {
                JOptionPane.showMessageDialog(displayframe, "You haven't got such friend!");
            }
            for (int i = 0; i < results.size(); i++) {
                add(new FriendButton(con.getNameById(results.get(i)), results.get(i), false));
            }
            frame.setVisible(true);
        }

    }

    class Listener4 implements ActionListener// Action listener for searching user among friends
    {
        public void actionPerformed(ActionEvent event) {
            scrollPane2.setViewportView(null);
            ArrayList<Integer> friends = con.getFriendsArray(profile.getID());
            ArrayList<Integer> results = new ArrayList<Integer>();
            for (int i = 0; i < friends.size(); i++) {
                if (con.getNameById(friends.get(i)).contains(searchfriend.getText())) {
                    results.add(friends.get(i));
                }
            }
            friendPanel2.removeAll();
            friendPanel2.changeresult(results);
            friendPanel2.printusers();
            scrollPane2.setViewportView(friendPanel2);

        }
    }

    class Listener5 implements ActionListener// Action listener of removefriend buttton
    {
        public void actionPerformed(ActionEvent event) {
            PopUp up = new PopUp(frame);
            up.setVisible(true);
        }
    }

    class PopUp extends JDialog {
        private JTextField searcharea;
        private JButton cancel;
        private ResultPanel result;
        
        private PopUp popUp = this;
        public PopUp(JFrame parent) {
            super(parent, "Remove Friend", true);
            JPanel panel = new JPanel(new GridLayout(4, 1));
            panel.setBackground(new Color(235,200,178));
            searcharea = new JTextField(20);
            panel.add(searcharea);
            JPanel panel2 = new JPanel(new GridBagLayout());
            GridBagConstraints a = new GridBagConstraints();
            a.anchor = GridBagConstraints.CENTER;
            searchButton = new JButton("search");
            searchButton.addActionListener(new Listener6());
            searchButton.setBackground(Color.LIGHT_GRAY);
            panel2.add(searchButton, a);
            panel.add(panel2);
            result = new ResultPanel();
            panel.add(result);
            JPanel panel3 = new JPanel(new GridBagLayout());
            GridBagConstraints d = new GridBagConstraints();
            d.anchor = GridBagConstraints.CENTER;
            cancel = new JButton("Cancel");
            cancel.addActionListener(new Listener7());
            panel3.add(cancel, d);
            panel.add(panel3);
            getContentPane().add(panel);
            setSize(new Dimension(400, 400));
            setLocationRelativeTo(frame);
            setLocation(300, 400);
        }

        class Listener6 implements ActionListener// Action listener for search button in removefriend popup
        {
            public void actionPerformed(ActionEvent event) {
                result.removeAll();
                if (searcharea.getText().length() == 0) {
                    JOptionPane.showMessageDialog(frame, "Searchbar cannot be empty", "Error", ERROR_MESSAGE);
                }
                ArrayList<Integer> friends = con.getFriendsArray(profile.getID());
                ArrayList<Integer> results = new ArrayList<Integer>();
                for (int i = 0; i < friends.size(); i++) {
                    if (con.getNameById(friends.get(i)).contains(searcharea.getText())) {
                        results.add(friends.get(i));
                    }
                }

                result.changeresult(results);
                result.printusers();
                popUp.setVisible(true);
            }
        }

        class ResultPanel extends JPanel {
            private ArrayList<Integer> results;

            public void changeresult(ArrayList<Integer> arr) {
                results = arr;
            }

            public void printusers() {
                if (results.size() == 0) {
                    JOptionPane.showMessageDialog(frame, "User cannot found!", "Error", ERROR_MESSAGE);
                    return;
                }
                setLayout(new GridLayout(0, 1));
                for (int i = 0; i < results.size(); i++) {
                    add(new RemoveButton(results.get(i), con.getNameById(results.get(i))));
                }
            }
        }

        class RemoveButton extends JButton implements ActionListener {
            private int ID;

            public RemoveButton(int ID, String s) {
                super(s);
                setBackground(Color.LIGHT_GRAY);
                addActionListener(this);
                this.ID = ID;
            }

            public void actionPerformed(ActionEvent event) {
                con.removeFriend(profile.getID(), ID);
                JOptionPane.showMessageDialog(frame, "Friend removed successfully", "Friend removal",
                        JOptionPane.INFORMATION_MESSAGE);
                        searchfriend.setText("");
                        button1.doClick();
                        popUp.dispose();

                        
            }
        }

        class Listener7 implements ActionListener// Action listener for closing pop with cancel button
        {
            public void actionPerformed(ActionEvent event) {
                popUp.dispose();
            }
        }

    }

    public Diary getDiary() {
        return diary;
    }

    class Listener8 implements ActionListener// Listener of badgeshop button
    {
        public void actionPerformed(ActionEvent event) {
            frame.setVisible(false);
            new BadgeShopGUI(frame, profile);
        }
    }

    class Listener9 implements ActionListener// Listener for groupdiary button
    {
        checkBoxFrame check;

        public void actionPerformed(ActionEvent event) {
            ArrayList<String> arr = new ArrayList<String>();
            for (int i : con.getFriendsArray(profile.getID())) {
                arr.add(con.getNameById(i));
            }

            if (check != null) {
                check.getPopUp().hide();
                check = null;
            } else {
                check = new checkBoxFrame(arr, groupdiarybutton);
                check.getPopUp().show();
            }
        }
    }

    class checkBoxFrame {
        JPanel bigPanel = new JPanel();
        JPanel panel = new JPanel();
        JScrollPane scrollPane;
        ArrayList<JCheckBox> checkBoxes;
        JComponent location;
        Dimension offSet;
        Popup popup;

        PopupFactory factory;

        checkBoxFrame(ArrayList<String> strings, JComponent component) {
            PopupFactory factory = new PopupFactory();
            setContent(strings, component);
            popup = factory.getPopup(component, bigPanel, groupdiarybutton.getX() + 100, groupdiarybutton.getY() + 100);
        }

        public void setContent(ArrayList<String> strings, JComponent component) {
            location = component;

            panel = new JPanel();
            panel.setLayout(new GridLayout(0, 1));
            scrollPane = new JScrollPane(panel);
            bigPanel.add(scrollPane);
            checkBoxSetUp(strings);
            panel.add(new ButtonListener());
        }

        public Popup getPopUp() {
            return popup;
        }

        private void checkBoxSetUp(ArrayList<String> strings) {
            checkBoxes = new ArrayList<>();
            for (int i = 0; i < strings.size(); i++) {
                JCheckBox checkBox = new JCheckBox(strings.get(i));
                panel.add(checkBox);
                checkBoxes.add(checkBox);
            }
        }

        private ArrayList<String> GetSelected() {
            ArrayList<String> returns = new ArrayList<String>();

            for (int i = 0; i < checkBoxes.size(); i++) {
                if (checkBoxes.get(i).isSelected()) {
                    returns.add(checkBoxes.get(i).getText());
                }
            }

            return returns;
        }

        class ButtonListener extends JButton implements ActionListener {
            ButtonListener() {
                super("Make Group");
                this.addActionListener(this);
                setBackground(Color.LIGHT_GRAY);
            }

            public void actionPerformed(ActionEvent e) {
                diary.GroupDiary(GetSelected());
                getPopUp().hide();
            }
        }
    }

    class TimerListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if (GregorianCalendar.getInstance().get(GregorianCalendar.MONTH) != diary.currentDate.get(GregorianCalendar.MONTH) ||
                    GregorianCalendar.getInstance().get(GregorianCalendar.DAY_OF_MONTH) != diary.currentDate.get(GregorianCalendar.DAY_OF_MONTH) ||
                    GregorianCalendar.getInstance().get(GregorianCalendar.YEAR) != diary.currentDate.get(GregorianCalendar.YEAR))
            {
                Diary.currentDate = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR),
                        Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
            }
            diary.updateCalendar();
        }
    }

}
