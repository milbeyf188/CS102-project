package CS_Project_Profile;

import loginandsignup.Controller;


public class Profile 
{
    private String name;
    private int streak;
    private String status;
    //private Badges_Class? badges;
    //private Friends_Class? friends;
    private String birthday;
    private int money;

    //Bu kısımda databaseden değerler classdaki değerlere atanacak. Fonksiyonlar lazım





    public Profile(String name, int streak, String status, String birthday,int money)
    {
        this.name = name;
        this.streak = streak;
        this.status = status;
        this.birthday = birthday;
        this.money = money;
    }


    public String getName()
    {
        return this.name;
    }

    public int getStreak()
    {
        return this.streak;
    }

    public String getStatus()
    {
        return this.status;
    }
    public int getMoney()
    {
        return money;
    }
    /*
    public Badges getBadges()
    {
        return this.badges;
    }
    */

    /*
    public Friends getFriends()
    {
        return this.friends;
    }
    */

    public String getBirthday()
    {
        return this.birthday;
    }
    

}
