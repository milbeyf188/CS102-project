package MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;

public class BadgeShopGUI extends JFrame {
    private JLabel titleLabel;
    private JButton backButton;
    private JPanel badgePanel;
    private JTextField moneyTextField;
    public static ArrayList<Badge> badgeList;

    public BadgeShopGUI() {
        badgeList = new ArrayList<>();

        setTitle("Badge Shop");
        setSize(800, 600);
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
                dispose();// Bunun yerine önceki sayfaya dönme eklenecek
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

        BufferedImage moneyImage = loadImage("MoneyIcon.png", 30, 30);

        String[] badgeFilenames = {
                "\\Badge PNGs\\Bronze1.png",
                "\\Badge PNGs\\Bronze2.png",
                "\\Badge PNGs\\Bronze3.png",
                "\\Badge PNGs\\Silver1.png",
                "\\Badge PNGs\\Silver2.png",
                "\\Badge PNGs\\Silver3.png",
                "\\Badge PNGs\\Gold1.png",
                "\\Badge PNGs\\Gold2.png",
                "\\Badge PNGs\\Gold3.png",
                "\\Badge PNGs\\Diamond1.png",
                "\\Badge PNGs\\Diamond2.png",
                "\\Badge PNGs\\Diamond3.png",
                "\\Badge PNGs\\Immortal1.png",
                "\\Badge PNGs\\Immortal2.png",
                "\\Badge PNGs\\Immortal3.png"
        };

        for (int i = 0; i < badgeFilenames.length; i++) {
            BufferedImage badgeImage = loadImage(badgeFilenames[i], 100, 100);

            int badgePrice = (i + 1) * 10; // Burada fiyat ayarlanıyor, değişebilir
            JLabel moneyLabel = new JLabel(new ImageIcon(moneyImage));
            JButton priceButton = new JButton("$" + badgePrice);
            priceButton.setForeground(Color.WHITE);
            priceButton.setBackground(Color.BLACK);
            priceButton.setFont(new Font("Arial", Font.BOLD, 20));

            JPanel badgeInfoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            badgeInfoPanel.setOpaque(false);
            badgeInfoPanel.add(moneyLabel);
            badgeInfoPanel.add(priceButton);

            JPanel badgeWrapperPanel = new JPanel(new BorderLayout());
            badgeWrapperPanel.setOpaque(false);
            badgeWrapperPanel.add(new JLabel(new ImageIcon(badgeImage)), BorderLayout.CENTER);
            badgeWrapperPanel.add(badgeInfoPanel, BorderLayout.SOUTH);

            badgeInfoPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
            badgePanel.add(badgeWrapperPanel);

            priceButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Badge badge = new Badge(badgePrice, badgeImage);
                    if (buy(badge)) {
                        priceButton.setVisible(false);
                        moneyLabel.setIcon(null);
                        moneyLabel.setFont(new Font("Arial", Font.BOLD, 24));
                        moneyLabel.setText("Bought");

                    }
                }
            });
        }
        moneyTextField = new JTextField("Remaining Money: " + 10, 15); // Para burda gösterilecek(10 yerşne para)
        moneyTextField.setEditable(false);
        moneyTextField.setFont(new Font("Arial", Font.BOLD, 18));
        moneyTextField.setHorizontalAlignment(JTextField.CENTER);
        moneyTextField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(moneyTextField, BorderLayout.SOUTH);

        setVisible(true);
    }

    private BufferedImage loadImage(String filename, int width, int height) {
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

    public boolean buy(Badge badge) {
        if (10 < badge.getPrice()) { // Paraya göre yapılacak
            // remaining money -= badge.getPrice() (Parayı al)
            badgeList.add(badge);
            return true;
        } else {
            JOptionPane.showMessageDialog(this, "You don't have enough money to buy this badge!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

    }

    public static void main(String[] args) {
        new BadgeShopGUI();
    }
}
