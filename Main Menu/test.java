import java.io.File;

public class test {
    public static String pathString;
    public static MenuFrame facediary;
    public static void main(String[] args) {
        File myfile = new File("FaceDiary");
        myfile.mkdir();
        pathString = myfile.getAbsolutePath();
        facediary = new MenuFrame();
    }
}
