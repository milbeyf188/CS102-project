package FaceDiaryLoginIlbey.src.loginandsignup;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


//Bu methodların çalışması için bir controller objesi oluşturup methodun başına yazın. Objenin içine bir parametre girmenize gerek yok
public class FDController 
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
            try (PreparedStatement pst = con.prepareStatement(query)) 
            {
                pst.setInt(1, userId);
                try (ResultSet rs = pst.executeQuery()) 
                {
                    if (rs.next()) {
                        return rs.getString("Statue");
                    }
                }
            }
        } 
        catch (SQLException e) 
        {
            System.out.println("Error retrieving user status text: " + e.getMessage());
        }
        return null; 
    }

    public void setUserPoints(int userId, Integer currentPoints) {
        
        try (Connection con = DriverManager.getConnection(url, userName, password)) 
        {
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
        
        try (Connection con = DriverManager.getConnection(url, userName, password)) 
        {
            String query = "UPDATE userinfo SET Streak = ? WHERE ID = ?";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setInt(1, currentStreak);
                pst.setInt(2, userId);
                pst.executeUpdate();
            }
        } catch (SQLException e) 
        {
            System.out.println("Error updating user status text: " + e.getMessage());
        }
    }
    
    public ArrayList<String> getNamesArray()
    {
        ArrayList<String> names = new ArrayList<String>();
        try (Connection con = DriverManager.getConnection(url, userName, password)) 
        {

            String name = null;
            String query = "SELECT Name FROM userinfo WHERE X = 0";
            try (PreparedStatement pst = con.prepareStatement(query)) 
            {
                //pst.setInt(1, userId);
                try (ResultSet rs = pst.executeQuery()) 
                {
                    while(rs.next()) 
                    {
                        name = rs.getString("Name");
                        names.add(name);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving user information: " + e.getMessage());
        }
        return names;
    }
    public ArrayList<Integer> getIDArray()
    {
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        try (Connection con = DriverManager.getConnection(url, userName, password)) 
        {

            int id = 0;
            String query = "SELECT ID FROM userinfo WHERE X = 0";
            try (PreparedStatement pst = con.prepareStatement(query)) 
            {
                //pst.setInt(1, userId);
                try (ResultSet rs = pst.executeQuery()) 
                {
                    while(rs.next()) 
                    {
                        id = rs.getInt("ID");
                        numbers.add(id);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving user information: " + e.getMessage());
        }
        return numbers;
    
    }

    public String booleanToString(boolean[] array) {
        StringBuilder sb = new StringBuilder();
        for (boolean value : array) {
            sb.append(value ? '1' : '0');
        }
        return sb.toString();
    }

        
    public void setFriend(int userId, int friendId) {
        if (areAlreadyFriends(userId, friendId)) {
            System.out.println("Error: Users are already friends.");
            return;
        }
        
        try (Connection con = DriverManager.getConnection(url, userName, password)) {
            String query = "INSERT INTO friends (UserID, FriendID) VALUES (?, ?), (?, ?)";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setInt(1, userId);
                pst.setInt(2, friendId);
                pst.setInt(3, friendId);
                pst.setInt(4, userId);
                pst.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error adding friendship: " + e.getMessage());
        }
    }
    public void removeFriend(int userId, int friendId) {
        try (Connection con = DriverManager.getConnection(url, userName, password)) {
            String query = "DELETE FROM friends WHERE (UserID = ? AND FriendID = ?) OR (UserID = ? AND FriendID = ?)";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setInt(1, userId);
                pst.setInt(2, friendId);
                pst.setInt(3, friendId);
                pst.setInt(4, userId);
                pst.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error removing friendship: " + e.getMessage());
        }
    }
    
    private boolean areAlreadyFriends(int userId, int friendId) {
        boolean alreadyFriends = false;
        try (Connection con = DriverManager.getConnection(url, userName, password)) {
            String query = "SELECT * FROM friends WHERE (UserID = ? AND FriendID = ?) OR (UserID = ? AND FriendID = ?)";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setInt(1, userId);
                pst.setInt(2, friendId);
                pst.setInt(3, friendId);
                pst.setInt(4, userId);
                try (ResultSet rs = pst.executeQuery()) {
                    alreadyFriends = rs.next();
                }
            }
        } catch (SQLException e) {
            System.out.println("Error checking friendship status: " + e.getMessage());
        }
        return alreadyFriends;
    }

    public boolean[] getBadgesArrayById(int userId) {
        String badgesString = null;
        try (Connection con = DriverManager.getConnection(url, userName, password)) {
            String query = "SELECT Badges FROM userinfo WHERE ID = ?";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setInt(1, userId);
                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        badgesString = rs.getString("Badges");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving badges information: " + e.getMessage());
        }
        
        if (badgesString != null && badgesString.matches("[01]+")) {
            boolean[] badgesArray = new boolean[badgesString.length()];
            for (int i = 0; i < badgesString.length(); i++) {
                badgesArray[i] = (badgesString.charAt(i) == '1');
            }
            return badgesArray;
        } 
        else 
        {
            System.out.println("Error:");
            return null;
        }
    }

    public String getBirthday(int userId) {
        
        try (Connection con = DriverManager.getConnection(url, userName, password)) {
            String query = "SELECT Statue FROM userinfo WHERE ID = ?";
            try (PreparedStatement pst = con.prepareStatement(query)) 
            {
                pst.setInt(1, userId);
                try (ResultSet rs = pst.executeQuery()) 
                {
                    if (rs.next()) {
                        return rs.getString("Birthday");
                    }
                }
            }
        } 
        catch (SQLException e) 
        {
            System.out.println("Error retrieving user status text: " + e.getMessage());
        }
        return null; 
    }

    public void setBirthday(int userId, String birthday) {
        
        try (Connection con = DriverManager.getConnection(url, userName, password)) {
            String query = "UPDATE userinfo SET Birthday = ? WHERE ID = ?";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, birthday);
                pst.setInt(2, userId);
                pst.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error updating user status text: " + e.getMessage());
        }
    }

    public ArrayList<String> getFriendsArray(int userID)
    {
        ArrayList<String> friends = new ArrayList<String>();
        try (Connection con = DriverManager.getConnection(url, userName, password)) 
        {

            String name = null;
            String query = "SELECT FriendID FROM userinfo WHERE UserID = ?";
            try (PreparedStatement pst = con.prepareStatement(query)) 
            {
                pst.setInt(1, userID);
                try (ResultSet rs = pst.executeQuery()) 
                {
                    while(rs.next()) 
                    {
                        name = rs.getString("Name");
                        friends.add(name);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving user information: " + e.getMessage());
        }
        return friends;
    }

    public void changePassword (int userID, String newPassword )
    {
        try (Connection con = DriverManager.getConnection(url, userName, password)) {
            String query = "UPDATE userinfo SET Password = ? WHERE ID = ?";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, newPassword);
                pst.setInt(2, userID);
                pst.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error updating user status text: " + e.getMessage());
        }
    }

    

}












