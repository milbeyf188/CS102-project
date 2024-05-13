package CS_Project_Profile;

import FaceDiaryLoginIlbey.src.loginandsignup.Controller;
//import FaceDiaryLoginIlbey.*;
import FaceDiaryLoginIlbey.src.loginandsignup.FDController;

import javax.imageio.ImageIO;
import javax.swing.*;

import MainMenu.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Profile_GUI extends JFrame {
    protected Color backgroundColor = new Color(8, 32, 45);
    protected Color lightblue = new Color(62, 128, 168);
    protected Font buttonfont = new Font("Messi", 0, 20);
    private JPanel badgesPanel;
    private Profile profile;
    private JLabel streakLabel;
    Controller cont = new Controller();
    private String[] arr;
    private int lastday;
    

    // public boolean friendOrUser = true;
    // Controller cont = new Controller();

    // String statue = cont.getStatue(UserID?);
    // String userName = cont.getNameById(UserID?);
    // int streak = cont.getUserStreakById(/*UserID?);

    public Profile_GUI(boolean friendOrUser, Profile profile, JFrame Menuframe,int userid) {

        this.profile = profile;
        arr = cont.getSharedDaysArray(userid, profile.getID());
        lastday = arr.length-1;
        setTitle("Profile");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        // getContentPane().setBackground(new Color(0, 0, 102));
        // setBackground(new Color(0, 0, 102));

        JPanel contentPane = new JPanel();
        contentPane.setBackground(backgroundColor); // Panelin arka planını koyu mavi yapar
        setContentPane(contentPane);

        setLayout(new GridLayout(6, 1));

        // 1. satır: Geri tuşu
        // setLayout(new GridLayout(1, 20));
        JButton backButton = new JButton("Back");
        backButton.setBackground(Color.RED);
        backButton.setPreferredSize(new Dimension(120, 80)); // Geri tuşunun genişliğini ayarlar

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setVisible(false);
                Menuframe.setVisible(true);

            }
        });

        JPanel Mainpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        Mainpanel.setBackground(backgroundColor);
        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Geri tuşunu sola hizalar
        // backButton.setBackground(new Color(0, 0, 102));

        if (friendOrUser == true) {
            JPanel changePasPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            changePasPanel.setBackground(backgroundColor);

            JButton PasswordButton = new JButton("Change Password");
            PasswordButton.setBackground(Color.GREEN);
            PasswordButton.setPreferredSize(new Dimension(120, 80));

            PasswordButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    setVisible(false);
                    createNewPanel();

                }
            });

            changePasPanel.add(PasswordButton);
            Mainpanel.add(changePasPanel);
        }

        backButtonPanel.add(backButton);
        Mainpanel.add(backButtonPanel);

        add(Mainpanel);

        // 2. satır: İsim textbox'u
        JPanel NamePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        NamePanel.setBackground(backgroundColor);
        JLabel NameLabel = new JLabel(profile.getName());
        NameLabel.setForeground(Color.WHITE);
        NameLabel.setFont(NameLabel.getFont().deriveFont(Font.PLAIN, 5 * NameLabel.getFont().getSize()));
        NamePanel.add(NameLabel);
        add(NamePanel);

        // 3. satır: Ateş image'i ve Streak yazısı
        if(!friendOrUser)
        {
        JPanel panel = new JPanel(new GridLayout(1, 3));
        JPanel panel4 = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 2;
        ButtonPanel buttonPanel = new ButtonPanel();
        panel4.setBackground(backgroundColor);
        panel4.add(buttonPanel,c);
        JLabel label = new JLabel("Use these button to access " + profile.getName() +  "'s diaries.");
        label.setVerticalAlignment(SwingConstants.TOP);
        label.setBackground(lightblue);
        label.setForeground(Color.WHITE);
        label.setFont(buttonfont);
        c.gridy = 0;
        JPanel panel5 = new JPanel(new GridLayout(2, 1));
        panel5.add(label);
        panel5.setBackground(backgroundColor);
        panel4.add(panel5,c);
        panel.add(panel4);
        
        JPanel streakPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        streakPanel.setBackground(backgroundColor);
        streakLabel = new JLabel("Streak: " + profile.getStreak());
        streakLabel.setForeground(Color.WHITE);
        streakLabel.setFont(streakLabel.getFont().deriveFont(Font.PLAIN, 5 * streakLabel.getFont().getSize())); // Yazı
                                                                                                                // büyüklüğünü
                                                                                                              // 5 kat
                                                                                                                // artırır
        ImageIcon fireIcon = new ImageIcon("/MainMenu/FireImage.png"); // Ateş image dosyasının yolunu belirtin
        JLabel fireLabel = new JLabel(fireIcon);
        streakPanel.add(fireLabel);
        streakPanel.add(streakLabel);
        JPanel panel2 = new JPanel();
        panel2.setBackground(backgroundColor);
        panel.add(streakPanel);
        panel.add(panel2);
        add(panel);
        }
        else
        {
        JPanel streakPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        streakPanel.setBackground(backgroundColor);
        streakLabel = new JLabel("Streak: " + profile.getStreak());
        streakLabel.setForeground(Color.WHITE);
        streakLabel.setFont(streakLabel.getFont().deriveFont(Font.PLAIN, 5 * streakLabel.getFont().getSize())); // Yazı
                                                                                                                // büyüklüğünü
                                                                                                              // 5 kat
                                                                                                                // artırır
        ImageIcon fireIcon = new ImageIcon("/MainMenu/FireImage.png"); // Ateş image dosyasının yolunu belirtin
        JLabel fireLabel = new JLabel(fireIcon);
        streakPanel.add(fireLabel);
        streakPanel.add(streakLabel);
        add(streakPanel);
        }
        

        // 4. satır: Badges yazısı
        JLabel badgesLabel = new JLabel("Badges");

        badgesLabel.setHorizontalAlignment(JLabel.CENTER); // Badges yazısını ortalar
        badgesLabel.setFont(badgesLabel.getFont().deriveFont(Font.PLAIN, 5 * badgesLabel.getFont().getSize())); // Yazı
                                                                                                                // büyüklüğünü
                                                                                                                // 5 kat
                                                                                                                // artırır
        badgesLabel.setForeground(Color.WHITE);
        add(badgesLabel);

        // 5. satır: 3 tane resim image
        if (friendOrUser == false) // arkadaşın profili
        {
            badgesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            badgesPanel.setBackground(backgroundColor);
            ImageIcon badge1Icon = new ImageIcon("badge1.png"); // Badge 1 image dosyasının yolunu belirtin
            ImageIcon badge2Icon = new ImageIcon("badge2.png"); // Badge 2 image dosyasının yolunu belirtin
            ImageIcon badge3Icon = new ImageIcon("badge3.png"); // Badge 3 image dosyasının yolunu belirtin
            JLabel badge1Label = new JLabel(badge1Icon);
            JLabel badge2Label = new JLabel(badge2Icon);
            JLabel badge3Label = new JLabel(badge3Icon);
            badgesPanel.add(badge1Label);
            badgesPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Boşluk eklemek için
            badgesPanel.add(badge2Label);
            badgesPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Boşluk eklemek için
            badgesPanel.add(badge3Label);
            add(badgesPanel);
        } else// Kendi profilimiz
        {
            JPanel badgesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            badgesPanel.setBackground(backgroundColor);

            //ArrayList<Badge> sortedBadgeList = bubbleSort(BadgeShopGUI.badgeList);
            //displayBadgeList(sortedBadgeList);

            /*
             * ImageIcon badge1Icon = new ImageIcon("badge1.png"); // Badge 1 image
             * dosyasının yolunu belirtin
             * ImageIcon badge2Icon = new ImageIcon("badge2.png"); // Badge 2 image
             * dosyasının yolunu belirtin
             * ImageIcon badge3Icon = new ImageIcon("badge3.png"); // Badge 3 image
             * dosyasının yolunu belirtin
             * JLabel badge1Label = new JLabel(badge1Icon);
             * JLabel badge2Label = new JLabel(badge2Icon);
             * JLabel badge3Label = new JLabel(badge3Icon);
             * badgesPanel.add(badge1Label);
             * badgesPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Boşluk eklemek
             * için
             * badgesPanel.add(badge2Label);
             * badgesPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Boşluk eklemek
             * için
             * badgesPanel.add(badge3Label);
             * 
             */
            JButton editButton = new JButton("EDİT");
            editButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Edit butonuna tıklandığında badgePanel'i gizle ve yeniPanel'i göster
                    setVisible(false);
                    createNewPanel();

                }
            });

            editButton.setBackground(Color.GREEN);
            editButton.setPreferredSize(new Dimension(120, 80)); // Geri tuşunun genişliğini ayarlar
            badgesPanel.add(editButton);

            add(badgesPanel);
        }

        /*
         * private void createNewPanel()
         * {
         * JPanel newPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
         * newPanel.setBackground(new Color(0, 0, 102));
         * 
         * // Örnek olarak 5 resmi burada ekleyebilirsiniz
         * ImageIcon image4 = new ImageIcon("image4.png");
         * ImageIcon image5 = new ImageIcon("image5.png");
         * ImageIcon image6 = new ImageIcon("image6.png");
         * ImageIcon image7 = new ImageIcon("image7.png");
         * ImageIcon image8 = new ImageIcon("image8.png");
         * 
         * JLabel label4 = new JLabel(image4);
         * JLabel label5 = new JLabel(image5);
         * JLabel label6 = new JLabel(image6);
         * JLabel label7 = new JLabel(image7);
         * JLabel label8 = new JLabel(image8);
         * 
         * newPanel.add(label4);
         * newPanel.add(label5);
         * newPanel.add(label6);
         * newPanel.add(label7);
         * newPanel.add(label8);
         * 
         * JButton kaydetButton = new JButton("Kaydet");
         * kaydetButton.addActionListener(new ActionListener() {
         * 
         * @Override
         * public void actionPerformed(ActionEvent e) {
         * // Kaydet butonuna tıklandığında yeniPanel'i gizle ve badgePanel'i göster
         * newPanel.setVisible(false);
         * currentPanel.setVisible(true);
         * }
         * });
         * newPanel.add(kaydetButton);
         * 
         * add(newPanel);
         * 
         * // Yeni paneli currentPanel'e atayarak sakla
         * currentPanel = newPanel;
         * }
         */

        // 6. satır: Status textbox'u ve altındaki status message textbox'u
        JPanel statusPanel = new JPanel(new GridLayout(2, 1));
        statusPanel.setBackground(backgroundColor);
        JLabel label1 = new JLabel("Status");
        label1.setBackground(backgroundColor);
        label1.setFont(streakLabel.getFont().deriveFont(Font.PLAIN, 1 * streakLabel.getFont().getSize()));
        label1.setForeground(Color.WHITE); // Yazı rengini beyaz yapar
        label1.setHorizontalAlignment(JLabel.CENTER); // Metni ortalar
        statusPanel.add(label1);

        if (friendOrUser == false) // arkadaşın profiline giriyorsak
        {
            JLabel label2 = new JLabel(profile.getStatus());
            label2.setForeground(Color.WHITE); // Yazı rengini beyaz yapıyoruz
            label2.setFont(streakLabel.getFont().deriveFont(Font.PLAIN, 1 * streakLabel.getFont().getSize()));
            label2.setHorizontalAlignment(JLabel.CENTER); // Metni ortala
            statusPanel.add(label2);
        } else // kendi profilimize giriyoruz yani statusu güncelleyebiliyoruz
        {
            JTextField statusMessageTextBox = new JTextField(profile.getStatus());
            statusMessageTextBox.setHorizontalAlignment(JTextField.CENTER); // Status message textbox'unu ortalar
            statusMessageTextBox.setFont(statusMessageTextBox.getFont().deriveFont(Font.PLAIN,
                    5 * statusMessageTextBox.getFont().getSize())); // Yazı büyüklüğünü 5 kat artırır
            JPanel Mainpanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
            //Mainpanel2.setBackground(b3);
            statusPanel.add(Mainpanel2);

            // Burada Yeni statusu kaydetmemiz lazım
            String newStatus = statusMessageTextBox.getText();
            cont.setStatue(profile.getID(), newStatus);

        }

        add(statusPanel);

        setVisible(true);
    }

    /*
     * private void createNewPanel()
     * {
     * JFrame newFrame = new JFrame("EDIT BADGES");
     * newFrame.setLayout(new FlowLayout());
     * 
     * // Adding 5 images to the new frame
     * for (int i = 1; i <= 5; i++) {
     * ImageIcon icon = new ImageIcon("path/to/your/image" + i + ".jpg");
     * JLabel imageLabel = new JLabel(icon);
     * newFrame.add(imageLabel);
     * }
     * 
     * JButton saveButton = new JButton("SAVE");
     * saveButton.addActionListener(new ActionListener() {
     * 
     * @Override
     * public void actionPerformed(ActionEvent e) {
     * newFrame.setVisible(false);
     * setVisible(true);
     * }
     * });
     * 
     * newFrame.add(saveButton);
     * newFrame.setSize(600, 400);
     * newFrame.setLocationRelativeTo(null);
     * newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
     * newFrame.setVisible(true);
     * }
     */

    private ArrayList<Badge> bubbleSort(ArrayList<Badge> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).getPrice() > list.get(j + 1).getPrice()) {
                    Badge temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
        return list;
    }

    public void displayBadgeList(ArrayList<Badge> badgeListToDisplay) {
        if (badgeListToDisplay.size() < 3) {
            for (int i = 0; i < badgeListToDisplay.size(); i++) {
                badgesPanel.add(new JLabel(badgeListToDisplay.get(i).getPhoto()));

            }

            // display here
        } else {
            for (int i = 0; i < 3; i++) {
                badgesPanel.add(new JLabel(badgeListToDisplay.get(i).getPhoto()));

            }
        }
    }

    /*
     * public static void main(String[] args) {
     * new Profile_GUI(true);
     * }
     */

    private void createNewPanel() {

        JFrame newFrame = new JFrame("Change Password");
        newFrame.setLayout(new FlowLayout()); // İçerikleri düzenlemek için FlowLayout kullanılıyor

        // Metin kutusu ekleniyor
        JTextField textField = new JTextField(20); // 20 sütun genişliğinde bir metin kutusu
        newFrame.add(textField); // Frame'e metin kutusu ekleniyor

        // "Save" butonu ekleniyor
        JButton saveButton = new JButton("Save");
        saveButton.setBackground(new Color(0, 255, 0)); // Butonun arka plan rengi yeşil olarak ayarlanıyor

        saveButton.addActionListener(new ActionListener() { // Butona basıldığında ne yapılacağını tanımlayan
                                                            // ActionListener
            public void actionPerformed(ActionEvent e) {
                String text = textField.getText(); // Metin kutusundaki metni alıyor
                cont.changePassword(profile.getID(), text);
                newFrame.setVisible(false);
                setVisible(true);

            }
        });
        newFrame.add(saveButton); // Frame'e buton ekleniyor

        // Frame ayarları
        newFrame.setSize(300, 100); // Frame'in boyutu ayarlanıyor
        newFrame.setLocationRelativeTo(null); // Frame ekranın ortasında konumlandırılıyor
        newFrame.setVisible(true); // Frame görünür hale getiriliyor
        newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }
    class ButtonPanel extends JPanel
    {
        private JButton textButton;
        public ButtonPanel()
        {
            setLayout(new GridLayout(1, 3));
            JButton prevButton = new JButton("Prev day");
            prevButton.addActionListener(new Listener1());
            add(prevButton);
            textButton = new JButton(arr[lastday]);
            textButton.addActionListener(new Listener2());
            add(textButton);
            JButton nexButton = new JButton("next day");
            nexButton.addActionListener(new Listener3());
            add(nexButton);

        }
        class Listener1 implements ActionListener//Action listener for prev button
        {
            public void actionPerformed(ActionEvent event)
            {
                if(lastday-1 >= 0)
                {
                    lastday--;
                }
                else
                {
                    lastday = arr.length-1;
                }
                textButton.setText(arr[lastday]);
            }
        }
        class Listener2 implements ActionListener//Action listener for textbutton
        {
            public void actionPerformed(ActionEvent e) {
                String displayedday = arr[lastday];
                Text display = new Text(displayedday, profile);
            }

        }
        class Listener3 implements ActionListener//action listener for next method;
        {

            public void actionPerformed(ActionEvent e) {
                if(lastday + 1 < arr.length)
                {
                    lastday++;
                }
                else{
                    lastday = 0;
                }
                textButton.setText(arr[lastday]);
            }
            
        }
    }


}
