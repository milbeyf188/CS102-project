package MainMenu;

import static javax.swing.JOptionPane.ERROR_MESSAGE;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.*;
import CS_Project_Profile.*;
import FaceDiaryLoginIlbey.src.loginandsignup.*;

public class MenuFrame extends JFrame {
    private Profile profile;
    private JLabel month;
    private JPanel monthpanel;

    private Diary diary;

    public static String pathString;
    public static MenuFrame facediary;
    private RoundedButton groupdiarybutton;
    private JTextField searchfriend;
    private JTextField searchadd;
    private ResultPanel resultpanel;
    private FriendPanel2 friendPanel2;
    private JScrollPane scrollPane;
    private JScrollPane scrollPane2;
    private static Controller con = new Controller();

    private MenuFrame frame = this;

    protected Color lightblue = new Color(62, 128, 168);
    protected Color backgroundColor = new Color(8, 32, 45);
    protected Font buttonfont = new Font("Messi", 0, 30);

    public MenuFrame(Profile profile) {
        facediary = this;
        File myfile = new File("FaceDiary");
        myfile.mkdir();
        pathString = myfile.getAbsolutePath();

        this.profile = profile;
        diary = new Diary(profile, this);// Diary burda çağrılıyor
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setName("Main menu");
        Panel panel = new Panel();
        this.add(panel);
        pack();

        setVisible(true);
    }

    class Panel extends JPanel {
        public Panel() {
            setLayout(new GridBagLayout());
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
            JButton buttonnext = new JButton("Next month");
            buttonnext.addActionListener(new Listener1());
            panel.add(buttonprev);

            monthpanel = new JPanel();
            monthpanel.setLayout(new BorderLayout());
            month = new JLabel(diary.getMonth() + " / " + diary.getYear(), (int) JLabel.CENTER_ALIGNMENT);

            monthpanel.add(month, BorderLayout.CENTER);
            panel.add(monthpanel);// Month will be displayed
            panel.add(buttonnext);
            panel.setPreferredSize(new Dimension(400, 100));
            this.add(panel, c);
            diary.setPreferredSize(new Dimension(400, 300));
            c.gridx = 0;
            c.gridy = 1;
            c.weightx = 0;
            c.weighty = 1;
            this.add(diary, c);
            c.gridx = 0;
            c.gridy = 2;
            c.weightx = 0;
            c.weighty = 1;
            JPanel badgepanel = new JPanel();
            badgepanel.setBackground(backgroundColor);
            badgepanel.setPreferredSize(new Dimension(400, 200));
            badgePanel badgesPanel = new badgePanel();// a panel to add badges
            badgepanel.add(badgesPanel);
            add(badgepanel, c);
            c.gridx = 0;
            c.gridy = 3;
            c.weightx = 0;
            c.weighty = 1;
            MoneyPanel moneyPanel = new MoneyPanel();
            moneyPanel.setPreferredSize(new Dimension(400, 100));
            add(moneyPanel, c);
            RoundedButton button = new RoundedButton(300, 75, "Badge Shop", null);
            button.addActionListener(new Listener8());
            c.gridx = 0;
            c.gridy = 4;
            c.weighty = 1;
            c.weightx = 1;
            add(button, c);
            RoundedButton button2 = new RoundedButton(300, 75, "New Diary", null);
            button2.setPreferredSize(new Dimension(300, 75));
            c.gridx = 1;
            c.gridy = 1;
            c.anchor = GridBagConstraints.WEST;
            add(button2, c);
            groupdiarybutton = new RoundedButton(300, 75, "New Group Diary", null);
            groupdiarybutton.addActionListener(new Listener9());
            c.gridx = 1;
            c.gridy = 2;
            c.weighty = 0;
            c.weightx = 0;
            c.anchor = GridBagConstraints.NORTHWEST;
            add(groupdiarybutton, c);
            c.gridx = 1;
            c.gridy = 3;
            c.weightx = 0;
            c.weighty = 0;
            RoundedButton button4 = new RoundedButton(300, 75, "Remove Friend", null);
            button4.addActionListener(new Listener5());
            add(button4, c);
            StreakPanel streakPanel = new StreakPanel();
            streakPanel.setPreferredSize(new Dimension(200, 100));
            c.gridx = 1;
            c.gridy = 0;
            c.weightx = 1;
            c.weighty = 1;
            c.anchor = GridBagConstraints.NORTH;
            add(streakPanel, c);
            FriendPanel friendPanel = new FriendPanel();
            c.gridx = 2;
            c.gridheight = 5;
            c.anchor = GridBagConstraints.NORTHEAST;
            c.fill = GridBagConstraints.VERTICAL;
            add(friendPanel, c);
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
            ArrayList<Integer> results = new ArrayList<Integer>();
            for (int i = badges.length - 1; i > 0; i--) {
                if (badges[i]) {
                    counter++;
                    results.add(i);
                }
            }
            if (counter == 0) {
                return;
            } else if (counter <= 3) {
                setLayout(new GridLayout(1, counter));
                ArrayList<JLabel> labels = new ArrayList<JLabel>();

                for (int i = 0; i < counter; i++) {
                    labels.add(new JLabel());
                }
                for (int i = 0; i < counter; i++) {
                    ImageIcon badgeImage = new ImageIcon(getClass().getResource(badgeFilenames[results.get(i)]));
                    Image scaledBadgeImage = badgeImage.getImage().getScaledInstance(140, 140, Image.SCALE_SMOOTH);
                    ImageIcon scaledBadgeIcon = new ImageIcon(scaledBadgeImage);
                    add(new JLabel(scaledBadgeIcon));
                }
            } else if (counter > 3) {
                setLayout(new GridLayout(1, 3));
                ArrayList<JLabel> labels = new ArrayList<JLabel>();
                for (int i = 0; i < 3; i++) {
                    labels.add(new JLabel());
                }
                for (int i = 0; i < 3; i++) {
                    ImageIcon badgeImage = new ImageIcon(getClass().getResource(badgeFilenames[results.get(i)]));
                    Image scaledBadgeImage = badgeImage.getImage().getScaledInstance(140, 140, Image.SCALE_SMOOTH);
                    ImageIcon scaledBadgeIcon = new ImageIcon(scaledBadgeImage);
                    add(new JLabel(scaledBadgeIcon));
                }
            }
        }

    }

    /*
     * overrides Jpanel class to add money image and display money amount of user in
     * main menu
     */

    class MoneyPanel extends JPanel {
        public void paintComponent(Graphics g) {
            ImageIcon image = new ImageIcon(getClass().getResource("/MainMenu/Money Image.png"));
            super.paintComponent(g);
            setBackground(backgroundColor);
            Graphics2D g2d = (Graphics2D) g;
            image.paintIcon(this, g2d, 10, 10);
            g.setFont(new Font("Comic sans", 0, 30));
            g.setColor(Color.WHITE);
            g.drawString(String.valueOf(profile.getMoney()), 100, 50);
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
            // ImageIcon image = new
            // ImageIcon(getClass().getResource("/CS_Project_Profile/Imagess/ProjectImages/Ekran
            // görüntüsü 2024-05-07 135630."));//DENİZ KOD BURDA
            super.paintComponent(g);
            setBackground(backgroundColor);
            Graphics2D g2d = (Graphics2D) g;
            // image.paintIcon(this, g2d, 20,20 );
            Font f = buttonfont.deriveFont(50);
            g.setFont(f);
            g.setColor(Color.WHITE);
            g.drawString(String.valueOf(profile.getStreak()), 100, 50);
        }
    }

    class FriendPanel extends JPanel// A general panel at the right of the screen
    {

        public void createPanel() {
            setLayout(new GridLayout(7, 1));

            FriendButton button4 = new FriendButton(profile.getName(), profile.getID(), true);
            button4.setPreferredSize(new Dimension(75, 25));
            button4.setFont(buttonfont);
            add(button4);// button to return our profile
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
            panel5.add(button1, con);
            add(panel5);
            friendPanel2 = new FriendPanel2();
            scrollPane2 = new JScrollPane(friendPanel2);// searching among friends
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
            panel4.add(button3, a);
            add(panel4);
            resultpanel = new ResultPanel();// result area for adding friends
            scrollPane = new JScrollPane();
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            add(scrollPane);

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
                    if (search.get(i).contains(searchadd.getText())) {
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
        }

        public void actionPerformed(ActionEvent event)// action listener for adding friends with ıd giver
        {
            con.setFriend(profile.getID(), ID);
            JOptionPane.showMessageDialog(frame, "User is added successfully", "Friend adding",
                    JOptionPane.INFORMATION_MESSAGE);
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
        }

        public void actionPerformed(ActionEvent e) {// yiğitin profil guisine gidecek
            Profile profile = new Profile(ID, con.getNameById(ID), con.getUserStreakById(ID), con.getStatue(ID),
                    con.getBirthday(ID), con.getUserPointsById(ID));
            Profile_GUI profilepage = new Profile_GUI(isuser, profile, frame);
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

    class Listener5 implements ActionListener// Action listener of removeframe buttton
    {
        public void actionPerformed(ActionEvent event) {
            PopUp up = new PopUp(frame);
            up.setVisible(true);
        }
    }

    class PopUp extends JDialog {
        private JTextField searcharea;
        private JButton cancel;
        private JButton remove;
        private ResultPanel result;
        private JButton searchButton;
        private PopUp popUp = this;

        public PopUp(JFrame parent) {
            super(parent, "Custom Popup", true);
            JPanel panel = new JPanel(new GridLayout(4, 1));
            searcharea = new JTextField(20);
            panel.add(searcharea);
            JPanel panel2 = new JPanel(new GridBagLayout());
            GridBagConstraints a = new GridBagConstraints();
            a.anchor = GridBagConstraints.CENTER;
            searchButton = new JButton("search");
            searchButton.addActionListener(new Listener6());
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
                addActionListener(this);
                this.ID = ID;
            }

            public void actionPerformed(ActionEvent event) {
                con.removeFriend(profile.getID(), ID);
                JOptionPane.showMessageDialog(frame, "Friend removed successfully", "Friend removal",
                        JOptionPane.INFORMATION_MESSAGE);
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
                check = new checkBoxFrame(arr, groupdiarybutton, new Dimension(0, 0));
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

        checkBoxFrame(ArrayList<String> strings, JComponent component, Dimension offSet) {
            PopupFactory factory = new PopupFactory();
            setContent(strings, component, offSet);
            popup = factory.getPopup(component, bigPanel, offSet.width, offSet.height);
        }

        public void setContent(ArrayList<String> strings, JComponent component, Dimension offSet) {
            location = component;
            this.offSet = offSet;

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
                super("give access");
                this.addActionListener(this);
            }

            public void actionPerformed(ActionEvent e) {
                diary.GroupDiary(GetSelected());
                getPopUp().hide();
            }
        }
    }

}
