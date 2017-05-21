package model;

import javax.persistence.*;

@Entity
@Table(name = "tUser")
public class User {
    @Id
    @Column(name = "UserID")
    private int UserID;

    @Column(name = "Brief")
    private String Brief;

    @Column(name = "Name")
    private String Name;

    public int getUserID() {
        return UserID;
    }
    public void setUserID(int UserID) {
        this.UserID = UserID;
    }


    public String getBrief() {
        return Brief;
    }
    public void setBrief(String Brief) {
        this.Brief = Brief;
    }

    public String getName() {
        return Name;
    }
    public void setName(String Name) {
        this.Name = Name;
    }




}
