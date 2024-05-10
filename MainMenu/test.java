package MainMenu;



import java.io.File;

import CS_Project_Profile.Profile;

public class test {
    public static String pathString;
    public static MenuFrame facediary;
    public static void main(String[] args) {
        File myfile = new File("FaceDiary");
        myfile.mkdir();
        pathString = myfile.getAbsolutePath();
        Profile profile = new Profile(5,"Emre", 16, "messi", "12/08/2024", 15);
        facediary = new MenuFrame(profile);

    }
}
