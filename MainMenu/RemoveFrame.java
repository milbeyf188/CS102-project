package MainMenu;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import CS_Project_Profile.*;
import FaceDiaryLoginIlbey.src.loginandsignup.*;
public class RemoveFrame extends JFrame {
    private static Controller con = new Controller();
    private static ArrayList<Integer> friends;
    private JTextField searchField;
    private ResultBox resultBox;
    private JFrame frame = this;
    protected Color lightblue = new Color(62, 128, 168);
    protected Color backgroundColor = new Color(8, 32, 45);
    protected Font buttonfont = new Font("Messi", 0, 30);
    public RemoveFrame(int id)
    {
        friends = con.getFriendsArray(id);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Removal Frame");
        setSize(1500, 800);
        createcomponents();
        
        setVisible(true);


    }
    private void createcomponents()
    {
        setLayout(new GridLayout(1, 3));
        add(new JPanel());
        Panel panel = new Panel();
        add(panel); 
        add(new JPanel());

    }
    class Panel extends JPanel
    {
        public Panel()
        {
            setLayout(new GridLayout(4, 1));
            JPanel panel1 = new JPanel(new GridLayout(1, 2));
            panel1.add(new JLabel("Enter a name for seaching!"));
            searchField = new JTextField(30);
            panel1.add(searchField);
            add(panel1);
            JPanel panel2 = new JPanel(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            JButton button = new JButton("Seach");
            c.anchor = GridBagConstraints.CENTER;
            button.addActionListener(new Listener());
            panel2.add(button, c);
            add(panel2);
            JPanel panel3 = new JPanel(new GridLayout(1, 3));
            JButton button2 = new JButton("Remove from friends");
            //JButton button2 = new JButton("Remove from friends");
            panel3.add(button2);
            resultBox = new ResultBox();
            panel3.add(resultBox);
            JButton button3 = new JButton("Go the profile");
            panel3.add(button3);
            add(panel3);
        }

    }
    class ResultBox extends JComboBox<String>
    {
        private ArrayList<Item> items = new ArrayList<Item>();
        public void changeresult(ArrayList<Integer> arr)
        {
            items.clear();
            for(int i = 0;i<arr.size();i++)
            {
                items.add(new Item(arr.get(i)));
            }
            for(int i = 0;i<items.size();i++)
            {
                addItem(items.get(i).toString());
            }
        }
        public int getID(String name)
        {
            for(Item item : items)
            {
                if(item.toString().equals(name))
                {
                    return item.getID();
                }
            }
            return 0;
        }
    
    
    }
    class Item
    {
        private int ID;
        private String name;
        public Item(int ID)
        {
            this.ID = ID;
            name = con.getNameById(ID);
        }
        public String toString()
        {
            return name;
        }
        public int getID()
        {
            return ID;
        }
    
    }
    class Listener implements ActionListener//listener for searchbar
    {

        public void actionPerformed(ActionEvent e) {
            resultBox.removeAllItems();
            ArrayList<Integer> result = new ArrayList<Integer>();
            for(int i = 0;i<friends.size();i++)
            {
                if(searchField.getText().contains(con.getNameById(friends.get(i))))
                {
                    result.add(friends.get(i));
                }
            }
            resultBox.changeresult(result);
        }

    }
    class Listener5 implements ActionListener//Listener for going to selected profile
    {
        public void actionPerformed(ActionEvent event)
        {
            String selectedString = (String)resultBox.getSelectedItem();
            int ID = resultBox.getID(selectedString);
            Profile profile = new Profile(ID, con.getNameById(ID), con.getUserStreakById(ID), con.getStatue(ID), con.getBirthday(ID), con.getUserPointsById(ID));
            Profile_GUI profile_GUI = new Profile_GUI(false, profile);
            frame.setVisible(false);
            profile_GUI.setVisible(true);
            
        }
    }
}
