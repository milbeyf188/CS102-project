package MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import CS_Project_Profile.*;
import FaceDiaryLoginIlbey.src.loginandsignup.*;

public class BadgeShopGUI extends JFrame {
    private JLabel titleLabel;
    private JButton backButton;
    private JPanel badgePanel;
    private JTextField moneyTextField;
    public static ArrayList<Integer> badgePriceList = new ArrayList<Integer>();
    public static MenuFrame menuFrame;
    static Profile profile;
    private static Controller cont = new Controller();
    private int remainingMoney;

    public BadgeShopGUI(MenuFrame frame, Profile profile) {
        createComponents(frame, profile);
    }

    public void createComponents(MenuFrame frame, Profile profile) {

        menuFrame = frame;
        this.profile = profile;
        remainingMoney = profile.getMoney();

        setTitle("Badge Shop");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(173, 210, 246));

        titleLabel = new JLabel("Badge Shop", SwingConstants.CENTER);
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        add(titleLabel, BorderLayout.NORTH);

        backButton = new JButton("<");
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.RED);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.setOpaque(true);
        backButton.setPreferredSize(new Dimension(50, 50));
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                menuFrame.setVisible(false);// Bunu denemek için koydum
                new MenuFrame(profile);
            }
        });

        JPanel backButtonPanel = new JPanel(null);
        backButtonPanel.setOpaque(false);
        backButtonPanel.setPreferredSize(new Dimension(150, 50));
        backButton.setBounds(0, 0, 100, 50);
        backButtonPanel.add(backButton);
        add(backButtonPanel, BorderLayout.WEST);

        badgePanel = new JPanel(new GridLayout(3, 5, 10, 10));
        badgePanel.setOpaque(false);
        add(badgePanel, BorderLayout.CENTER);

        ImageIcon moneyImage = new ImageIcon(getClass().getResource("/MainMenu/MoneyIcon.png"));
        Image scaledMoneyImage = moneyImage.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon scaledMoneyIcon = new ImageIcon(scaledMoneyImage);

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

        Badge[] allBadges = new Badge[16];
        allBadges[0] = null;
        

        for (int i = 1; i < badgeFilenames.length; i++) {
            ImageIcon badgeImage = new ImageIcon(getClass().getResource(badgeFilenames[i]));
            Image scaledBadgeImage = badgeImage.getImage().getScaledInstance(140, 140, Image.SCALE_SMOOTH);
            ImageIcon scaledBadgeIcon = new ImageIcon(scaledBadgeImage);
            int badgePrice = (int) Math.pow(i, 4);
            allBadges[i] = new Badge(badgePrice, scaledBadgeIcon);
        }

        for (int m = 1; m < badgeFilenames.length; m++) {
            ImageIcon badgeImage = new ImageIcon(getClass().getResource(badgeFilenames[m]));
            Image scaledBadgeImage = badgeImage.getImage().getScaledInstance(140, 140, Image.SCALE_SMOOTH);
            ImageIcon scaledBadgeIcon = new ImageIcon(scaledBadgeImage);
            int badgePrice = (int) Math.pow(m, 4);

            JLabel moneyLabel = new JLabel(scaledMoneyIcon);
            JButton priceButton = new JButton("$" + badgePrice);

   
            if (cont.getBadgesArrayById(profile.getID())[m]) 
            {
                priceButton.setVisible(false);
                moneyLabel.setIcon(null);
                moneyLabel.setFont(new Font("Arial", Font.BOLD, 24));
                moneyLabel.setText("Bought");
            }

            priceButton.setForeground(Color.WHITE);
            priceButton.setBackground(Color.BLACK);
            priceButton.setFont(new Font("Arial", Font.BOLD, 20));

            JPanel badgeInfoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            badgeInfoPanel.setOpaque(false);
            badgeInfoPanel.add(moneyLabel);
            badgeInfoPanel.add(priceButton);

            JPanel badgeWrapperPanel = new JPanel(new BorderLayout());
            badgeWrapperPanel.setOpaque(false);
            badgeWrapperPanel.add(new JLabel(scaledBadgeIcon), BorderLayout.CENTER);
            badgeWrapperPanel.add(badgeInfoPanel, BorderLayout.SOUTH);

            badgeInfoPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
            badgePanel.add(badgeWrapperPanel);

            priceButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (buy(new Badge(badgePrice, scaledBadgeIcon))) {

                        priceButton.setVisible(false);
                        moneyLabel.setIcon(null);
                        moneyLabel.setFont(new Font("Arial", Font.BOLD, 24));
                        moneyLabel.setText("Bought");
                        Badge tryBadge = new Badge(badgePrice, scaledBadgeIcon);
                        for (int k = 1; k < allBadges.length; k++) {
                            if (allBadges[k].getPrice() == tryBadge.getPrice()) {
                                cont.badgeBought(profile.getID(), k);
                            }
                        }

                    }
                }
            });

        }

        moneyTextField = new JTextField("Remaining Money: " + remainingMoney, 15);
        moneyTextField.setEditable(false);
        moneyTextField.setFont(new Font("Arial", Font.BOLD, 18));
        moneyTextField.setHorizontalAlignment(JTextField.CENTER);
        moneyTextField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(moneyTextField, BorderLayout.SOUTH);

        setVisible(true);
    }

    public boolean buy(Badge badge) {
        int badgePrice = badge.getPrice();
        if (profile.getMoney() >= badgePrice) {
            badgePriceList.add(badge.getPrice());
            int profID = profile.getID();
            remainingMoney = profile.getMoney() - badgePrice;
            profile.setMoney(remainingMoney);
            cont.setUserPoints(profID, remainingMoney);
            moneyTextField.setText("Remaining Money: " + remainingMoney);
            return true;
        } else {
            JOptionPane.showMessageDialog(this, "You don't have enough money to buy this badge!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

}
