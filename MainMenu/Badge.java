package MainMenu;

import java.awt.Component;
import java.awt.image.BufferedImage;

public class Badge {
    private int price;
    private BufferedImage photo;

    public Badge(int price, BufferedImage photo) {
        this.price = price;
        this.photo = photo;
    }

    public int getPrice() {
        return this.price;
    }

     public BufferedImage getPhoto() {
        return this.photo;
    } 
} 