package DiasoftRESTFull;

import org.hibernate.Query;
import org.hibernate.Session;
import dataBase.dbUtils;
import model.User;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static dataBase.dbUtils.allObjectWithClass;
//Usage tUser
public class Login {
    private long UserID = 0;
    private String Name = "";

    public Login(String userName) {
        Session session = dbUtils.getSession();
        Query query = session.createSQLQuery("select UserID, Name, Brief from tUser where Brief = :USERNAME")
                .setParameter("USERNAME", userName);
        List result = query.list();
        //List<User>  result = dbUtils.allObjectWithClass(User.class);
        if (result != null){
            Object[] item = (Object[]) result.get(0);
            this.UserID = Long.parseLong(item[0].toString());
            this.Name = item[1].toString().trim();
        }
    }
    public long getUserID() {
        return UserID;
    }

    public String getName() {
        return Name;
    }

}
