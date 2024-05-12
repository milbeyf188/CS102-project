package MainMenu;

import javax.swing.ImageIcon;

public class Badge {
    private int price;
    private ImageIcon photo;

    public Badge(int price, ImageIcon photo) {
        this.price = price;
        this.photo = photo;
    }

    public int getPrice() {
        return this.price;
    }
}



