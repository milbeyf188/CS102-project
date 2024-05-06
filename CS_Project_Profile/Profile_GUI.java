package CS_Project_Profile;

import javax.swing.*;
import java.awt.*;

public class Profile_GUI extends JFrame {
    public Profile_GUI() {

        public boolean friendOrUser;
        String userName = "Yiğit";


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
        JButton backButton = new JButton("Geri");
        backButton.setBackground(Color.RED);
        backButton.setPreferredSize(new Dimension(60, 40)); // Geri tuşunun genişliğini ayarlar
        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Geri tuşunu sola hizalar
        //backButton.setBackground(new Color(0, 0, 102));
        backButtonPanel.add(backButton);
        add(backButtonPanel);


        

        // 2. satır: İsim textbox'u
        JPanel NamePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        NamePanel.setBackground(new Color(0, 0, 102));
        JLabel NameLabel = new JLabel(userName);
        NameLabel.setForeground(Color.WHITE);
        NameLabel.setFont(NameLabel.getFont().deriveFont(Font.PLAIN, 5 * NameLabel.getFont().getSize()));
        NamePanel.add(NameLabel);
        add(NamePanel);

        // 3. satır: Ateş image'i ve Streak yazısı
        JPanel streakPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        streakPanel.setBackground(new Color(0, 0, 102));
        JLabel streakLabel = new JLabel("Streak");
        streakLabel.setForeground(Color.WHITE);
        streakLabel.setFont(streakLabel.getFont().deriveFont(Font.PLAIN, 5 * streakLabel.getFont().getSize())); // Yazı büyüklüğünü 5 kat artırır
        ImageIcon fireIcon = new ImageIcon("C:\\Users\\gokkh\\Desktop\\NEW_WORKSPACE\\CS_Project\\Imagess\\ProjectImages\\png-transparent-animation-fire-drawing-frie-orange-computer-wallpaper-cartoon-thumbnail.png"); // Ateş image dosyasının yolunu belirtin
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

        // 6. satır: Status textbox'u ve altındaki status message textbox'u
        JPanel statusPanel = new JPanel(new GridLayout(2, 1));
        statusPanel.setBackground(new Color(0, 0, 102));
        JLabel label1 = new JLabel("Status");
        label1.setBackground(new Color(0, 0, 102));
        label1.setFont(streakLabel.getFont().deriveFont(Font.PLAIN, 1 * streakLabel.getFont().getSize()));
        label1.setForeground(Color.WHITE); // Yazı rengini beyaz yapar
        label1.setHorizontalAlignment(JLabel.CENTER); // Metni ortalar
        statusPanel.add(label1);

        JLabel label2 = new JLabel("Message");
        label2.setForeground(Color.WHITE); // Yazı rengini beyaz yapar
        label2.setFont(streakLabel.getFont().deriveFont(Font.PLAIN, 1 * streakLabel.getFont().getSize()));
        label2.setHorizontalAlignment(JLabel.CENTER); // Metni ortalar
        statusPanel.add(label2);
        add(statusPanel);
        
        setVisible(true);
    }

    public static void main(String[] args) {
        new Profile_GUI();
    }
}
