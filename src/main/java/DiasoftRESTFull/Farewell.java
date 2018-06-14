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
public class Farewell {
    private List UserList = null;

    public Farewell(String userName) {
        Session session = dbUtils.getSession();
        Query query = session.createSQLQuery("SELECT TOP(50) name, f_DateTo, PhotoLink from tClient inner join tCltCltRelation on tClient.ClientID = tCltCltRelation.f_ChildClientID where f_DateTo < GETDATE ( )  order by f_DateTo desc");
        UserList = query.list();
    }

    public List getUserList() {
        return UserList;
    }

}