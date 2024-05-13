package CS_Project_Profile;

import FaceDiaryLoginIlbey.src.loginandsignup.Controller;
import MainMenu.Diary;
import MainMenu.MenuFrame;

public class Profile {
    private String name;
    private int streak;
    private String status;
    // private Badges_Class? badges;
    // private Friends_Class? friends;
    private String birthday;
    private int money;
    private int ID;

    // Bu kısımda databaseden değerler classdaki değerlere atanacak. Fonksiyonlar
    // lazım

    public Profile(int ID, String name, int streak, String status, String birthday, int money)

    {
        this.ID = ID;
        this.name = name;
        this.streak = streak;
        this.status = status;
        this.birthday = birthday;
        this.money = money;
    }

    public String getName() {
        return this.name;
    }

    public int getStreak() {
        return this.streak;
    }

    public String getStatus() {
        return this.status;
    }

    public int getMoney() {
        return money;
    }
    /*
     * public Badges getBadges()
     * {
     * return this.badges;
     * }
     */

    /*
     * public Friends getFriends()
     * {
     * return this.friends;
     * }
     */

    public String getBirthday() {
        return this.birthday;
    }

    public int getID() {
        return this.ID;
    }

    public void setMoney(int newMoney) {
        this.money = newMoney;
    }

    public void setStreak(){
        Controller con = new Controller();

        int isStreaking = MenuFrame.facediary.getDiary().addStreak();

        if(isStreaking < 0)
        {
            this.streak = 0;
            con.setStreak(getID(), 0);
            return;
        }
        else if(isStreaking > 0)
        {
            streak++;
            money += streak;
            con.setStreak(getID(), streak);
            con.setUserPoints(getID(), getMoney());
            con.setLastDay(getID(), Diary.getYesterday());
            return;
        }

        con.setLastDay(getID(), Diary.getYesterday());
    }

}
