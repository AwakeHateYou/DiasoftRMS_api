package model;

import javax.persistence.*;

@Entity
@Table(name = "tUser", schema = "rms")
public class User {
    private int UserID;
    private String Brief;
    private String Name;

    @Id
    @GeneratedValue
    @Column(name = "UserID")
    public int getId() {
        return UserID;
    }
    public void setUserID(int userID) {
        this.UserID = userID;
    }

    @Basic
    @Column(name = "Brief")
    public String getBrief() {
        return Brief;
    }
    public void setBrief(String brief) {
        this.Brief = brief;
    }

    @Basic
    @Column(name = "Name")
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        this.Name = name;
    }




}
