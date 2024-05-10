
package loginandsignup;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import static javax.swing.JOptionPane.showMessageDialog;
import java.math.BigInteger;  

public class SignUp extends javax.swing.JFrame
{
    public SignUp() 
    {
        inComponents();
    }

    private javax.swing.JButton SignUpBtn;
    private javax.swing.JTextField emailAddress;
    private javax.swing.JTextField fname;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPasswordField pass;

    
    private void inComponents() 
    {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        fname = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        emailAddress = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        pass = new javax.swing.JPasswordField();
        jLabel8 = new javax.swing.JLabel();
        SignUpBtn = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sign Up");
        setPreferredSize(new java.awt.Dimension(800, 500));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 500));
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/logo.png"))); // NOI18N

        
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("FaceDiary");

        

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addGap(137, 137, 137).addComponent(jLabel1)).addGroup(jPanel2Layout.createSequentialGroup().addGap(72, 72, 72).addComponent(jLabel3)).addGroup(jPanel2Layout.createSequentialGroup().addGap(104, 104, 104).addComponent(jLabel2))).addContainerGap(49, Short.MAX_VALUE)));
        jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addGap(129, 129, 129).addComponent(jLabel1).addGap(30, 30, 30).addComponent(jLabel2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 108, Short.MAX_VALUE).addComponent(jLabel3).addGap(64, 64, 64)));

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 400, 500);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setBackground(new java.awt.Color(0, 102, 102));
        
        jLabel4.setText("SİGN UP");

        jLabel5.setBackground(new java.awt.Color(102, 102, 102));
        
        jLabel5.setText("Full name");

        
        fname.setForeground(new java.awt.Color(102, 102, 102));

        jLabel6.setBackground(new java.awt.Color(102, 102, 102));
        
        jLabel6.setText("Email");

        
        emailAddress.setForeground(new java.awt.Color(102, 102, 102));

        jLabel7.setBackground(new java.awt.Color(102, 102, 102));
        
        jLabel7.setText("Password");

        
        pass.setForeground(new java.awt.Color(102, 102, 102));

        jLabel8.setText("I have an account");

        SignUpBtn.setBackground(new java.awt.Color(0, 102, 102));
        SignUpBtn.setForeground(new java.awt.Color(255, 255, 255));
        SignUpBtn.setText("Sign Up");
        SignUpBtn.addActionListener(new java.awt.event.ActionListener() 
        {
            public void actionPerformed(java.awt.event.ActionEvent evt) 
            {
                SignUpBtnActionPerformed(evt);
            }
        });

        jButton2.setForeground(new java.awt.Color(255, 51, 51));
        jButton2.setText("Login");
        jButton2.addActionListener(new java.awt.event.ActionListener() 
        {
            public void actionPerformed(java.awt.event.ActionEvent evt) 
            {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel3Layout.createSequentialGroup().addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel3Layout.createSequentialGroup().addGap(145, 145, 145).addComponent(jLabel4)).addGroup(jPanel3Layout.createSequentialGroup().addGap(44, 44, 44).addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(jLabel5).addComponent(fname).addComponent(jLabel6).addComponent(emailAddress, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE).addComponent(jLabel7).addComponent(pass)).addComponent(SignUpBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE).addGroup(jPanel3Layout.createSequentialGroup().addComponent(jLabel8).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))))).addContainerGap(24, Short.MAX_VALUE)));
        jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel3Layout.createSequentialGroup().addGap(28, 28, 28).addComponent(jLabel4).addGap(29, 29, 29).addComponent(jLabel5).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(fname, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(29, 29, 29).addComponent(jLabel6).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(emailAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(29, 29, 29).addComponent(jLabel7).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(pass, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addComponent(SignUpBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(24, 24, 24).addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel8).addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)).addContainerGap(45, Short.MAX_VALUE)));

        jPanel1.add(jPanel3);
        jPanel3.setBounds(400, 0, 400, 500);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(0, 113, Short.MAX_VALUE)));
        layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(0, 126, Short.MAX_VALUE)));

        pack();
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) 
    {
       
        Login LoginFrame = new Login();
        LoginFrame.setVisible(true);
        LoginFrame.pack();
        LoginFrame.setLocationRelativeTo(null); 
        this.dispose();
    }

    private void SignUpBtnActionPerformed(java.awt.event.ActionEvent evt) {
        String fullName, email, query;
        char[] password;
        String url, userName, SPass;
        url = "jdbc:MySQL://localhost:3306/facediary";
        userName = "root";
        SPass = "";
        BigInteger bigInteger = new BigInteger("1000000000000000"); 
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, userName, SPass);
            Statement st = con.createStatement();
    
            if ("".equals(fname.getText())) 
            {
                JOptionPane.showMessageDialog(new JFrame(), "Full Name is required", "Error", JOptionPane.ERROR_MESSAGE);
            } 
            else if ("".equals(emailAddress.getText())) 
            {
                JOptionPane.showMessageDialog(new JFrame(), "Email Address is required", "Error", JOptionPane.ERROR_MESSAGE);
            } 
            else if ((pass.getPassword()).length == 0) 
            {
                JOptionPane.showMessageDialog(new JFrame(), "Password is required", "Error", JOptionPane.ERROR_MESSAGE);
            } 
            else 
            {
                fullName = fname.getText();
                email = emailAddress.getText();
                password = pass.getPassword();
    
    
                
                int nextUserId = 1; 
                String getMaxIdQuery = "SELECT MAX(ID) AS maxId FROM userinfo";
                try (ResultSet rs = st.executeQuery(getMaxIdQuery)) {
                    if (rs.next()) 
                    {
                        int maxId = rs.getInt("maxId");
                        nextUserId = maxId + 1;
                    }
                }
    
                
                query = "INSERT INTO userinfo(ID, Name, eMail, password, UserPoints, Streak, Statue, X,Badges) VALUES (? ,?,?, ?, ?, ?, ? , ? , ?)";
                try (PreparedStatement pst = con.prepareStatement(query)) 
                {
                    pst.setInt(1, nextUserId);
                    pst.setString(2, fullName);
                    pst.setString(3, email);
                    pst.setString(4, String.valueOf(password));
                    pst.setInt(5, 0);
                    pst.setInt(6,0);
                    pst.setString(7,"New User" );
                    pst.setInt(8,0);
                    pst.setString(9,"100000000000000");
                    pst.executeUpdate();
                }
    
                // Clear input fields
                fname.setText("");
                emailAddress.setText("");
                pass.setText("");
    
                
                JOptionPane.showMessageDialog(null, "New account has been created successfully!");
            }
        } 
        catch (Exception e) 
        {
            System.out.println("Error!" + e.getMessage());
        }
    }
}    