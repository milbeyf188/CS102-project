package loginandsignup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


//Bu methodların çalışması için bir controller objesi oluşturup methodun başına yazın. Objenin içine bir parametre girmenize gerek yok
public class Controller 
{
    
    private final String url = "jdbc:mysql://localhost:3306/facediary";
    private final String userName = "root";
    private final String password = ""; 

    public String getNameById(int userId) 
    {
        String fullName = null;
        try (Connection con = DriverManager.getConnection(url, userName, password)) 
        {
            String query = "SELECT Name FROM userinfo WHERE ID = ?";
            try (PreparedStatement pst = con.prepareStatement(query)) 
            {
                pst.setInt(1, userId);
                try (ResultSet rs = pst.executeQuery()) 
                {
                    if (rs.next()) 
                    {
                        fullName = rs.getString("Name");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving user information: " + e.getMessage());
        }
        return fullName;
    }

    
    public String getEmailById(int userId) 
    {
        String email = null;
        try (Connection con = DriverManager.getConnection(url, userName, password)) 
        {
            String query = "SELECT eMail FROM userinfo WHERE ID = ?";
            try (PreparedStatement pst = con.prepareStatement(query)) 
            {
                pst.setInt(1, userId);
                try (ResultSet rs = pst.executeQuery()) 
                {
                    if (rs.next())
                    {
                        email = rs.getString("eMail");
                    }
                }
            }
        } 
        catch (SQLException e) 
        {
            System.out.println("Error retrieving user information: " + e.getMessage());
        }
        return email;
    }

   
    public int getUserPointsById(int userId) 
    {
        int userPoints = -1;
        try (Connection con = DriverManager.getConnection(url, userName, password)) 
        {
            String query = "SELECT UserPoints FROM userinfo WHERE ID = ?";
            try (PreparedStatement pst = con.prepareStatement(query)) 
            {
                pst.setInt(1, userId);
                try (ResultSet rs = pst.executeQuery()) 
                {
                    if (rs.next()) 
                    {
                        userPoints = rs.getInt("UserPoints");
                    }
                }
            }
        } 
        catch (SQLException e) 
        {
            System.out.println("Error retrieving user information: " + e.getMessage());
        }
        return userPoints;
    }

    
    public int getUserStreakById(int userId) 
    {
        int userStreak = -1;
        try (Connection con = DriverManager.getConnection(url, userName, password)) 
        {
            String query = "SELECT Streak FROM userinfo WHERE ID = ?";
            try (PreparedStatement pst = con.prepareStatement(query)) 
            {
                pst.setInt(1, userId);
                try (ResultSet rs = pst.executeQuery()) 
                {
                    if (rs.next()) 
                    {
                        userStreak = rs.getInt("Streak");
                    }
                }
            }
        } 
        catch (SQLException e) 
        {
            System.out.println("Error retrieving user information: " + e.getMessage());
        }
        return userStreak;
    }

    public void setStatue(int userId, String statue) {
        
        try (Connection con = DriverManager.getConnection(url, userName, password)) {
            String query = "UPDATE userinfo SET Statue = ? WHERE ID = ?";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, statue);
                pst.setInt(2, userId);
                pst.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error updating user status text: " + e.getMessage());
        }
    }

    public String getStatue(int userId) {
        
        try (Connection con = DriverManager.getConnection(url, userName, password)) {
            String query = "SELECT Statue FROM userinfo WHERE ID = ?";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setInt(1, userId);
                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        return rs.getString("Statue");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving user status text: " + e.getMessage());
        }
        return null; 
    }

    public void setUserPoints(int userId, Integer currentPoints) {
        
        try (Connection con = DriverManager.getConnection(url, userName, password)) {
            String query = "UPDATE userinfo SET UserPoints = ? WHERE ID = ?";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setInt(1, currentPoints);
                pst.setInt(2, userId);
                pst.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error updating user status text: " + e.getMessage());
        }
    }

    public void setStreak(int userId, Integer currentStreak) {
        
        try (Connection con = DriverManager.getConnection(url, userName, password)) {
            String query = "UPDATE userinfo SET Streak = ? WHERE ID = ?";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setInt(1, currentStreak);
                pst.setInt(2, userId);
                pst.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error updating user status text: " + e.getMessage());
        }
    }
    

    


    


    

}