package loginandsignup;

import CS_Project_Profile.Profile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.util.*;


public class Login extends javax.swing.JFrame 
{

    private javax.swing.JPanel Left;
    private javax.swing.JButton LoginBtn;
    private javax.swing.JPanel Right;
    private javax.swing.JTextField email;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField password;

  
    public Login() 
    {
        initComponents();
    }

    private void initComponents() 
    {

        jPanel1 = new javax.swing.JPanel();
        Right = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        Left = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();
        LoginBtn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LOGIN");
        setPreferredSize(new java.awt.Dimension(800, 500));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 500));
        jPanel1.setLayout(null);

        Right.setBackground(new java.awt.Color(0, 102, 102));
        Right.setPreferredSize(new java.awt.Dimension(400, 500));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/logo.png"))); // NOI18N
        
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("FACEDIARY");

        javax.swing.GroupLayout RightLayout = new javax.swing.GroupLayout(Right);
        Right.setLayout(RightLayout);
        RightLayout.setHorizontalGroup(RightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RightLayout.createSequentialGroup().addGap(0, 81, Short.MAX_VALUE).addComponent(jLabel7).addGap(40, 40, 40)).addGroup(RightLayout.createSequentialGroup().addGroup(RightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(RightLayout.createSequentialGroup().addGap(103, 103, 103).addComponent(jLabel6)).addGroup(RightLayout.createSequentialGroup().addGap(145, 145, 145).addComponent(jLabel5))).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        RightLayout.setVerticalGroup(RightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(RightLayout.createSequentialGroup().addGap(136, 136, 136).addComponent(jLabel5).addGap(26, 26, 26).addComponent(jLabel6).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE).addComponent(jLabel7).addGap(78, 78, 78)));
        jPanel1.add(Right);
        Right.setBounds(0, 0, 400, 500);

        Left.setBackground(new java.awt.Color(255, 255, 255));
        Left.setMinimumSize(new java.awt.Dimension(400, 500));

        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setText("LOGİN");

        jLabel2.setBackground(new java.awt.Color(102, 102, 102));
        
        jLabel2.setText("Email");

        email.setForeground(new java.awt.Color(102, 102, 102));

        jLabel3.setBackground(new java.awt.Color(102, 102, 102));
        
        jLabel3.setText("Password");

        LoginBtn.setBackground(new java.awt.Color(0, 102, 102));
        
        LoginBtn.setForeground(new java.awt.Color(255, 255, 255));
        LoginBtn.setText("Login");
        
        LoginBtn.addActionListener(new java.awt.event.ActionListener() 
        {
            public void actionPerformed(java.awt.event.ActionEvent evt) 
            {
                LoginBtnActionPerformed(evt);
            }
        });

        jLabel4.setText("Don't have an account?");

        jButton2.setForeground(new java.awt.Color(255, 40, 40));
        jButton2.setText("Sign Up");
        jButton2.addActionListener(new java.awt.event.ActionListener() 
        {
            public void actionPerformed(java.awt.event.ActionEvent evt) 
            {
                jButton2ActionPerformed(evt);
            }
        }
        
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(0, 0, Short.MAX_VALUE)));
        layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(0, 0, Short.MAX_VALUE)));
        pack();

        jPanel1.add(Left);
        Left.setBounds(400, 0, 400, 500);

        javax.swing.GroupLayout LeftLayout = new javax.swing.GroupLayout(Left);
        Left.setLayout(LeftLayout);
        LeftLayout.setHorizontalGroup(
        LeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(LeftLayout.createSequentialGroup().addGroup(LeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(LeftLayout.createSequentialGroup().addGap(138, 138, 138).addComponent(jLabel1)).addGroup(LeftLayout.createSequentialGroup().addGap(30, 30, 30).addGroup(LeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(LeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(jLabel2).addComponent(email).addComponent(jLabel3).addComponent(password, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE).addComponent(LoginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(LeftLayout.createSequentialGroup().addComponent(jLabel4).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jButton2))))).addContainerGap(27, Short.MAX_VALUE)));
        LeftLayout.setVerticalGroup(
        LeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(LeftLayout.createSequentialGroup().addGap(51, 51, 51).addComponent(jLabel1).addGap(40, 40, 40).addComponent(jLabel2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addComponent(jLabel3).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(26, 26, 26).addComponent(LoginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(33, 33, 33).addGroup(LeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel4).addComponent(jButton2)).addContainerGap(77, Short.MAX_VALUE)));
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) 
    {
        SignUp SignUpFrame = new SignUp();
        SignUpFrame.setVisible(true);
        SignUpFrame.pack();
        SignUpFrame.setLocationRelativeTo(null); 
        this.dispose();
    }
    private void LoginBtnActionPerformed(java.awt.event.ActionEvent evt) 
    {
        String Email, query, Name;
        String SUrl, SUser, SPass;
        SUrl = "jdbc:MySQL://localhost:3306/facediary";
        SUser = "root";
        SPass = "";
        int notFound = 0;
        char[] userPassword = null; 
        FDController cont = new FDController();
    
        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(SUrl, SUser, SPass);
            Statement st = con.createStatement();
            if ("".equals(email.getText())) 
            {
                JOptionPane.showMessageDialog(new JFrame(), "Email Address is require", "Error", JOptionPane.ERROR_MESSAGE);
            } 
            else if ("".equals(String.copyValueOf(password.getPassword()))) 
            {
                JOptionPane.showMessageDialog(new JFrame(), "Password is require", "Error", JOptionPane.ERROR_MESSAGE);
            } 
            else 
            {
                Email = email.getText();
                char[] Password = password.getPassword();
    
                query = "SELECT * FROM userinfo WHERE email= '" + Email + "'";
    
                ResultSet rs = st.executeQuery(query);
                if (rs.next()) 
                {
                    userPassword = stringToCharArray(rs.getString("password")); 
                    Name = rs.getString("Name");
                    notFound = 1;
                }
                if (notFound == 1 && Arrays.equals(Password, userPassword)) 
                {
                    
                    int userID = rs.getInt("ID");
                    Profile prof = new Profile(userID, cont.getNameById(userID), cont.getUserStreakById(userID),cont.getStatue(userID),cont.getBirthday(userID),cont.getUserPointsById(userID));
                    System.out.println("ANAN");
                    //MenuFrame MenuFrame = new MenuFrame(prof);
                    //MenuFrame.setVisible(true);
                    //MenuFrame.pack();
                    //MenuFrame.setLocationRelativeTo(null); 
                    this.dispose(); 
                } 
                else 
                {
                    JOptionPane.showMessageDialog(new JFrame(), "Incorrect email or password", "Error", JOptionPane.ERROR_MESSAGE);
                }
                password.setText("");
    
            }
        } 
        catch (Exception e) 
        {
            System.out.println("Error!" + e.getMessage());
        }
    }
    
    public static char[] stringToCharArray(String str) 
    {
        char[] charArray = str.toCharArray();
        return charArray;
    } 
}
