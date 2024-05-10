package CS_Project_Profile;

import loginandsignup.FDController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;



public class Profile_GUI extends JFrame
{

    loginandsignup.FDController cont = new FDController();

    //public boolean friendOrUser = false;
    //Controller cont = new Controller();

    //String statue = cont.getStatue(UserID?);
    //String userName = cont.getNameById(UserID?);
    //int streak = cont.getUserStreakById(/*UserID?);
    
    public Profile_GUI(boolean friendOrUser , Profile profile ) {


        
        

        setTitle("Profile");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        //getContentPane().setBackground(new Color(0, 0, 102));
        //setBackground(new Color(0, 0, 102));

        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 0, 102)); // Panelin arka planını koyu mavi yapar
        setContentPane(contentPane);

        setLayout(new GridLayout(6, 1));

        // 1. satır: Geri tuşu
        //setLayout(new GridLayout(1, 20));
        JButton backButton = new JButton("Back");
        backButton.setBackground(Color.RED);
        backButton.setPreferredSize(new Dimension(120, 80)); // Geri tuşunun genişliğini ayarlar
        JPanel Mainpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        Mainpanel.setBackground(new Color(0, 0, 102));
        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Geri tuşunu sola hizalar
        //backButton.setBackground(new Color(0, 0, 102));
        backButtonPanel.add(backButton);
        Mainpanel.add(backButtonPanel);
    
        
        add(Mainpanel);


        

        // 2. satır: İsim textbox'u
        JPanel NamePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        NamePanel.setBackground(new Color(0, 0, 102));
        JLabel NameLabel = new JLabel(profile.getName());
        NameLabel.setForeground(Color.WHITE);
        NameLabel.setFont(NameLabel.getFont().deriveFont(Font.PLAIN, 5 * NameLabel.getFont().getSize()));
        NamePanel.add(NameLabel);
        add(NamePanel);

        // 3. satır: Ateş image'i ve Streak yazısı
        JPanel streakPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        streakPanel.setBackground(new Color(0, 0, 102));
        JLabel streakLabel = new JLabel("Streak" /*+ profile.getStreak()*/);
        streakLabel.setForeground(Color.WHITE);
        streakLabel.setFont(streakLabel.getFont().deriveFont(Font.PLAIN, 5 * streakLabel.getFont().getSize())); // Yazı büyüklüğünü 5 kat artırır
        ImageIcon fireIcon = new ImageIcon("C:\\Users\\gokkh\\Desktop\\NEW_WORKSPACE\\CS_Project_Profile\\Imagess\\ProjectImages\\Ekran görüntüsü 2024-05-07 135630.png"); // Ateş image dosyasının yolunu belirtin
        JLabel fireLabel = new JLabel(fireIcon);
        streakPanel.add(fireLabel);
        streakPanel.add(streakLabel);
        add(streakPanel);

        // 4. satır: Badges yazısı
        JLabel badgesLabel = new JLabel("Badges");

        badgesLabel.setHorizontalAlignment(JLabel.CENTER); // Badges yazısını ortalar
        badgesLabel.setFont(badgesLabel.getFont().deriveFont(Font.PLAIN, 5 * badgesLabel.getFont().getSize())); // Yazı büyüklüğünü 5 kat artırır
        badgesLabel.setForeground(Color.WHITE);
        add(badgesLabel);

        // 5. satır: 3 tane resim image
       if(friendOrUser == false)  //arkadaşın profili
        {
            JPanel badgesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            badgesPanel.setBackground(new Color(0, 0, 102));
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
        }
        else// Kendi profilimiz
        {
            JPanel badgesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            badgesPanel.setBackground(new Color(0, 0, 102));



            ArrayList<Badge> sortedBadgeList = bubbleSort(BadgeShopGUI.badgeList);
            displayBadgeList(sortedBadgeList);

            public void displayBadgeList(ArrayList<Badge> badgeListToDisplay) {
                if(badgeListToDisplay.size() < 3) 
                {
                    for(int i = 0; i < badgeListToDisplay.size(); i++)
                    {
                        badgesPanel.add(badgeListToDisplay.get(i).getPhoto());
                        
                    }


                    //display here
                }
                else 
                {
                    for(int i = 0; i < 3; i++)
                    {
                        badgesPanel.add(badgeListToDisplay.get(i).getPhoto());
                        
                    }
                }
            
        
        
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

            /*
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
            */

            add(badgesPanel);
        }


        

        /* 
        private void createNewPanel() 
        {
            JPanel newPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            newPanel.setBackground(new Color(0, 0, 102));
    
            // Örnek olarak 5 resmi burada ekleyebilirsiniz
            ImageIcon image4 = new ImageIcon("image4.png");
            ImageIcon image5 = new ImageIcon("image5.png");
            ImageIcon image6 = new ImageIcon("image6.png");
            ImageIcon image7 = new ImageIcon("image7.png");
            ImageIcon image8 = new ImageIcon("image8.png");
    
            JLabel label4 = new JLabel(image4);
            JLabel label5 = new JLabel(image5);
            JLabel label6 = new JLabel(image6);
            JLabel label7 = new JLabel(image7);
            JLabel label8 = new JLabel(image8);
    
            newPanel.add(label4);
            newPanel.add(label5);
            newPanel.add(label6);
            newPanel.add(label7);
            newPanel.add(label8);
    
            JButton kaydetButton = new JButton("Kaydet");
            kaydetButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Kaydet butonuna tıklandığında yeniPanel'i gizle ve badgePanel'i göster
                    newPanel.setVisible(false);
                    currentPanel.setVisible(true);
                }
            });
            newPanel.add(kaydetButton);
    
            add(newPanel);
    
            // Yeni paneli currentPanel'e atayarak sakla
            currentPanel = newPanel;
        }
        */
        

        // 6. satır: Status textbox'u ve altındaki status message textbox'u
        JPanel statusPanel = new JPanel(new GridLayout(2, 1));
        statusPanel.setBackground(new Color(0, 0, 102));
        JLabel label1 = new JLabel("Status");
        label1.setBackground(new Color(0, 0, 102));
        label1.setFont(streakLabel.getFont().deriveFont(Font.PLAIN, 1 * streakLabel.getFont().getSize()));
        label1.setForeground(Color.WHITE); // Yazı rengini beyaz yapar
        label1.setHorizontalAlignment(JLabel.CENTER); // Metni ortalar
        statusPanel.add(label1);

        if(friendOrUser == false) // arkadaşın profiline giriyorsak
        {
            JLabel label2 = new JLabel("MESSAGE" /*profile.getStatus */);
            label2.setForeground(Color.WHITE); // Yazı rengini beyaz yapıyoruz
            label2.setFont(streakLabel.getFont().deriveFont(Font.PLAIN, 1 * streakLabel.getFont().getSize()));
            label2.setHorizontalAlignment(JLabel.CENTER); // Metni ortala
            statusPanel.add(label2);
        }
        else //kendi profilimize giriyoruz yani statusu güncelleyebiliyoruz
        {
            JTextField statusMessageTextBox = new JTextField("Status Message"  + profile.getStatus());
            statusMessageTextBox.setHorizontalAlignment(JTextField.CENTER); // Status message textbox'unu ortalar
            statusMessageTextBox.setFont(statusMessageTextBox.getFont().deriveFont(Font.PLAIN, 5 * statusMessageTextBox.getFont().getSize())); // Yazı büyüklüğünü 5 kat artırır
            JPanel Mainpanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
            Mainpanel2.setBackground(new Color(0, 0, 102));
            Mainpanel2.add(statusMessageTextBox);
            statusPanel.add(Mainpanel2);


            //Burada Yeni statusu kaydetmemiz lazım
            String newStatus = statusMessageTextBox.getText();
            cont.setStatue(profile.getID(), newStatus);


        }
        
        add(statusPanel);
        
        setVisible(true);
    }

    /*
    private void createNewPanel() 
        {
            JFrame newFrame = new JFrame("EDIT BADGES");
            newFrame.setLayout(new FlowLayout());
    
            // Adding 5 images to the new frame
            for (int i = 1; i <= 5; i++) {
                ImageIcon icon = new ImageIcon("path/to/your/image" + i + ".jpg");
                JLabel imageLabel = new JLabel(icon);
                newFrame.add(imageLabel);
            }
    
            JButton saveButton = new JButton("SAVE");
            saveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    newFrame.setVisible(false);
                    setVisible(true);
                }
            });
    
            newFrame.add(saveButton);
            newFrame.setSize(600, 400);
            newFrame.setLocationRelativeTo(null);
            newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            newFrame.setVisible(true);
        }
        */


        private BufferedImage loadImage(String filename, int width, int height) 
     {
        try {
            BufferedImage originalImage = ImageIO.read(new File(filename));
            BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = resizedImage.createGraphics();
            g.drawImage(originalImage, 0, 0, width, height, null);
            g.dispose();
            return resizedImage;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
    public static void main(String[] args) {
        new Profile_GUI(true);
    }
    */
}
