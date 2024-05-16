package FaceDiaryLoginIlbey.src.loginandsignup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import CS_Project_Profile.*;


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

    public void setStatue(int userId, String statue) 
    {
        
        try (Connection con = DriverManager.getConnection(url, userName, password)) 
        {
            String query = "UPDATE userinfo SET Statue = ? WHERE ID = ?";
            try (PreparedStatement pst = con.prepareStatement(query)) 
            {
                pst.setString(1, statue);
                pst.setInt(2, userId);
                pst.executeUpdate();
            }
        } 
        catch (SQLException e) 
        {
            System.out.println("Error updating user status text: " + e.getMessage());
        }
    }

    public String getStatue(int userId) 
    {
        
        try (Connection con = DriverManager.getConnection(url, userName, password)) 
        {
            String query = "SELECT Statue FROM userinfo WHERE ID = ?";
            try (PreparedStatement pst = con.prepareStatement(query)) 
            {
                pst.setInt(1, userId);
                try (ResultSet rs = pst.executeQuery()) 
                {
                    if (rs.next()) 
                    {
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
            try (PreparedStatement pst = con.prepareStatement(query)) 
            {
                pst.setInt(1, currentPoints);
                pst.setInt(2, userId);
                pst.executeUpdate();
            }
        } 
        catch (SQLException e) 
        {
            System.out.println("Error updating user status text: " + e.getMessage());
        }
    }

    public void setStreak(int userId, Integer currentStreak) 
    {
        
        try (Connection con = DriverManager.getConnection(url, userName, password)) 
        {
            String query = "UPDATE userinfo SET Streak = ? WHERE ID = ?";
            try (PreparedStatement pst = con.prepareStatement(query)) 
            {
                pst.setInt(1, currentStreak);
                pst.setInt(2, userId);
                pst.executeUpdate();
            }
        } 
        catch (SQLException e) 
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
        } 
        catch (SQLException e) 
        {
            System.out.println("Error retrieving user information: " + e.getMessage());
        }
        return numbers;
    
    }

    public String booleanToString(boolean[] array) 
    {
        StringBuilder sb = new StringBuilder();
        for (boolean value : array) 
        {
            sb.append(value ? '1' : '0');
        }
        return sb.toString();
    }

        
    public void setFriend(int userId, int friendId) 
    {
        if (areAlreadyFriends(userId, friendId)) 
        {
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
        }
        catch (SQLException e) 
        {
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
    
    private boolean areAlreadyFriends(int userId, int friendId)
    {
        boolean alreadyFriends = false;
        try (Connection con = DriverManager.getConnection(url, userName, password)) 
        {
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
        } 
        catch (SQLException e) 
        {
            System.out.println("Error checking friendship status: " + e.getMessage());
        }
        return alreadyFriends;
    }

    public boolean[] getBadgesArrayById(int userId) 
    {
        String badgesString = null;
        try (Connection con = DriverManager.getConnection(url, userName, password)) 
        {
            String query = "SELECT Badges FROM userinfo WHERE ID = ?";
            try (PreparedStatement pst = con.prepareStatement(query)) 
            {
                pst.setInt(1, userId);
                try (ResultSet rs = pst.executeQuery()) 
                {
                    if (rs.next()) 
                    {
                        badgesString = rs.getString("Badges");
                    }
                }
            }
        } 
        catch (SQLException e) 
        {
            System.out.println("Error retrieving badges information: " + e.getMessage());
        }
        
        if (badgesString != null && badgesString.matches("[01]+")) 
        {
            boolean[] badgesArray = new boolean[badgesString.length()];
            for (int i = 0; i < badgesString.length(); i++) 
            {
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

    public String getBirthday(int userId) 
    {
        
        try (Connection con = DriverManager.getConnection(url, userName, password)) 
        {
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

    public ArrayList<Integer> getFriendsArray(int userID)
    {
        ArrayList<Integer> friends = new ArrayList<Integer>();
        try (Connection con = DriverManager.getConnection(url, userName, password)) 
        {

            Integer ID = null;
            String query = "SELECT FriendID FROM friends WHERE UserID = ?";
            try (PreparedStatement pst = con.prepareStatement(query)) 
            {
                pst.setInt(1, userID);
                try (ResultSet rs = pst.executeQuery()) 
                {
                    while(rs.next()) 
                    {
                        ID = rs.getInt("FriendID");
                        friends.add(ID);
                    }
                }
            }
        } 
        catch (SQLException e) 
        {
            System.out.println("Error retrieving user information: " + e.getMessage());
        }
        return friends;
    }

    public void changePassword(int userID, String newPassword) 
    {
        
        try (Connection con = DriverManager.getConnection(url, userName, password)) 
        {
            String query = "UPDATE userinfo SET Password = ? WHERE ID = ?";
            try (PreparedStatement pst = con.prepareStatement(query)) 
            {
                pst.setString(1, newPassword);
                pst.setInt(2, userID);
                pst.executeUpdate();
            }
        } 
        catch (SQLException e) 
        {
            System.out.println("Error updating user status text: " + e.getMessage());
        }
    }

    public void badgeBought(int userID, int badegNumber)
    {
        boolean[] badges = getBadgesArrayById(userID);
        badges[badegNumber] = true;
        String badgeString = booleanToString(badges);


        try (Connection con = DriverManager.getConnection(url, userName, password)) 
        {
            String query = "UPDATE userinfo SET Badges = ? WHERE ID = ?";
            try (PreparedStatement pst = con.prepareStatement(query)) 
            {
                pst.setString(1, badgeString);
                pst.setInt(2, userID);
                pst.executeUpdate();
            }
        } 
        catch (SQLException e) 
        {
            System.out.println("Error updating user status text: " + e.getMessage());
        }

    }

    /* public String booleanArrToString(int userID,boolean[] badgesArr)
    {
        String badges = null;

        return badges;
    } */

    public void shareDay(int userID, int friendID, String sharedDay) 
    {
        try (Connection con = DriverManager.getConnection(url, userName, password)) 
        {
            String query = "SELECT SharedDays FROM friends WHERE UserID = ? AND FriendID = ?";
            try (PreparedStatement pstSelect = con.prepareStatement(query)) 
            {
                pstSelect.setInt(1, userID);
                pstSelect.setInt(2, friendID);
                try (ResultSet rs = pstSelect.executeQuery()) 
                {
                    if (rs.next()) 
                    {
                        String existingSharedDays = rs.getString("SharedDays");

                        if (existingSharedDays != null && !existingSharedDays.isEmpty())
                        {
                            if (existingSharedDays.contains(sharedDay))
                            {
                                return;
                            }

                            String[] a = existingSharedDays.split("/");

                            String[] arr = new String[a.length + 1];

                            long shared = Long.parseLong(sharedDay.replace("_",""));

                            int i;

                            for (i = a.length - 1; i >= 0; i--)
                            {
                                if(shared < Long.parseLong(a[i].replace("_","")))
                                {
                                    arr[i + 1] = a[i];
                                }
                                else
                                {
                                    break;
                                }
                            }

                            arr[i + 1] = sharedDay;

                            for (int j = 0; j <= i; j++)
                            {
                                arr[j] = a[j];
                            }

                            sharedDay = arr[0];

                            for (int j = 1; j < arr.length; j++)
                            {
                                sharedDay += "/" + arr[j];
                            }
                        }
                    }
                }
            }
            
            query = "UPDATE friends SET SharedDays = ? WHERE UserID = ? AND FriendID = ?";
            try (PreparedStatement pstUpdate = con.prepareStatement(query)) 
            {
                pstUpdate.setString(1, sharedDay);
                pstUpdate.setInt(2, userID);
                pstUpdate.setInt(3, friendID);
                int rowsAffected = pstUpdate.executeUpdate();
                if (rowsAffected > 0) 
                {
                    System.out.println("Shared day updated successfully.");
                } 
                else 
                {
                    System.out.println("No rows were updated. Check if the friendship exists.");
                }
            }
        } 
        catch (SQLException e) 
        {
            System.out.println("Error updating shared day: " + e.getMessage());
        }
    }

    public void UnshareDay(int userID, int friendID, String sharedDay)
    {
        try (Connection con = DriverManager.getConnection(url, userName, password))
        {
            String query = "SELECT SharedDays FROM friends WHERE UserID = ? AND FriendID = ?";
            try (PreparedStatement pstSelect = con.prepareStatement(query))
            {
                pstSelect.setInt(1, userID);
                pstSelect.setInt(2, friendID);
                try (ResultSet rs = pstSelect.executeQuery())
                {
                    if (rs.next())
                    {
                        String existingSharedDays = rs.getString("SharedDays");

                        if (existingSharedDays != null && !existingSharedDays.isEmpty())
                        {

                            if (existingSharedDays.contains(sharedDay))
                            {
                                String[] a = existingSharedDays.split("/");

                                String[] arr = new String[a.length - 1];

                                long shared = Long.parseLong(sharedDay.replace("_", ""));

                                int i;

                                for (i = a.length - 1; i >= 0; i--)
                                {
                                    if (shared <= Long.parseLong(a[i].replace("_", "")))
                                    {
                                        arr[i - 1] = a[i];
                                    } else
                                    {
                                        break;
                                    }
                                }

                                for (int j = 0; j <= i - 1; j++) {
                                    arr[j] = a[j];
                                }

                                sharedDay = arr[0];

                                for (int j = 1; j < arr.length; j++) {
                                    sharedDay += "/" + arr[j];
                                }
                            } else {return;}
                        }
                    }
                }
            }

            query = "UPDATE friends SET SharedDays = ? WHERE UserID = ? AND FriendID = ?";
            try (PreparedStatement pstUpdate = con.prepareStatement(query))
            {
                pstUpdate.setString(1, sharedDay);
                pstUpdate.setInt(2, userID);
                pstUpdate.setInt(3, friendID);
                int rowsAffected = pstUpdate.executeUpdate();
                if (rowsAffected > 0)
                {
                    System.out.println("Shared day updated successfully.");
                }
                else
                {
                    System.out.println("No rows were updated. Check if the friendship exists.");
                }
            }
        }
        catch (SQLException e)
        {
            System.out.println("Error updating shared day: " + e.getMessage());
        }
    }
    
    public String[] getSharedDaysArray(int userID, int friendID)
    {
        String sharedDays = "";
        try (Connection con = DriverManager.getConnection(url, userName, password)) 
        {
            String query = "SELECT SharedDays FROM friends WHERE UserID = ? AND FriendID = ?";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setInt(2, userID);
                pst.setInt(1, friendID);
                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) 
                    {
                        sharedDays = rs.getString("SharedDays");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving shared days: " + e.getMessage());
        }

        return sharedDays != null? sharedDays.split("/"): null;
    }

    public String getLastDay(int userId) 
    {
        String fullName = null;
        try (Connection con = DriverManager.getConnection(url, userName, password)) 
        {

            
            String query = "SELECT LastEnteredDay FROM userinfo WHERE ID = ?";
            try (PreparedStatement pst = con.prepareStatement(query)) 
            {
                pst.setInt(1, userId);
                try (ResultSet rs = pst.executeQuery()) 
                {
                    if (rs.next()) 
                    {
                        fullName = rs.getString("LastEnteredDay");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving user information: " + e.getMessage());
        }
        return fullName;
    }

    public void setLastDay(int userId, String lastDay) 
    {
        
        try (Connection con = DriverManager.getConnection(url, userName, password)) 
        {
            String query = "UPDATE userinfo SET LastEnteredDay = ? WHERE ID = ?";
            try (PreparedStatement pst = con.prepareStatement(query)) 
            {
                pst.setString(1, lastDay);
                pst.setInt(2, userId);
                pst.executeUpdate();
            }
        } 
        catch (SQLException e) 
        {
            System.out.println("Error updating user status text: " + e.getMessage());
        }
    }

    public Profile getProfileByName(String name) 
    {
        Profile profile = null;
        try (Connection con = DriverManager.getConnection(url, userName, password)) 
        {
            String query = "SELECT * FROM userinfo WHERE Name = ?";
            try (PreparedStatement pst = con.prepareStatement(query)) 
            {
                pst.setString(1, name);
                try (ResultSet rs = pst.executeQuery()) 
                {
                    if (rs.next()) 
                    {
                        int ID = rs.getInt("ID");
                        int streak = rs.getInt("Streak");
                        String status = rs.getString("Statue");
                        String birthday = rs.getString("Birthday");
                        int money = rs.getInt("UserPoints");
                        profile = new Profile(ID, name, streak, status, birthday, money);
                    }
                }
            }
        } 
        catch (SQLException e) 
        {
            System.out.println("Error retrieving profile: " + e.getMessage());
        }
        return profile;
    }
}












