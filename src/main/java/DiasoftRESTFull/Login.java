package DiasoftRESTFull;

import org.hibernate.Query;
import org.hibernate.Session;
import dataBase.dbUtils;
import model.User;

import java.util.Iterator;


public class Login {
    private final long UserID;
    private final String Name;
    private final String Result;

    public Login(String userName) {
        Session session = dbUtils.getSession();
        Query query = session.createSQLQuery("select * from tUser where Brief = :USERNAME")
                .setParameter("USERNAME", userName);
        java.util.List result = query.list();
        if (result != null){
            Object item  =  result.get(0);
            this.UserID = 1;
            this.Name = "eterentev";
            this.Result = item.toString();
        } else {
            this.UserID = 1;
            this.Name = "eterentev";
            this.Result = "";
        }
    }
    public long getUserID() {
        return UserID;
    }

    public String getName() {
        return Name;
    }
    public String getResult() {
        return Result;
    }
}
