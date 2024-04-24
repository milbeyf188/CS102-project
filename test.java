import java.io.File;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class test
{
    public static String pathString;
    public static JPanel diary;
    public static MenuFrame facediary;
    public static void main(String[] args) 
    {
        File myFile = new File("FaceDiary");
        myFile.mkdir();
        pathString = myFile.getAbsolutePath();
        facediary = new MenuFrame();
    }
}
