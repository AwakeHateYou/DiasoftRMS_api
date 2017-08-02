package DiasoftRESTFull;

import dataBase.dbUtils;
import org.hibernate.Query;
import org.hibernate.Session;

import java.text.SimpleDateFormat;
import java.util.*;

//Usage tTimeSheet
public class ActualWorks {
    private ArrayList<HashMap> actualWorks = new ArrayList<>();
    private static int DAY_IN_LIST = 6;
    private SimpleDateFormat formatDate = new SimpleDateFormat("dd.MM.yyyy");
    public ActualWorks(String userID) {
        Session session = dbUtils.getSession();
        Query query = session.createSQLQuery("select DateIn, TimeInWork from tTimeSheet where CreateUserID = :USERID and DateIn >= :DATESTART order by DateIn")
                .setParameter("USERID", userID).setParameter("DATESTART",  getFirstDayOfWeek());
        List result = query.list();
        try {
            for (int i = 0; i < result.size(); i++) {
                Object[] item = (Object[]) result.get(i);
                Boolean addFlag = false;
                if (actualWorks != null) {
                    for (int j = 0; j < actualWorks.size(); j++) {
                        int first = getDay(actualWorks.get(j).get("DateIn").toString());
                        int second = getDay(item[0].toString());
                        if (first == second) {
                            actualWorks.get(j).put("TimeInWork",
                                    Double.parseDouble(actualWorks.get(j).get("TimeInWork").toString()) + Double.parseDouble(item[1].toString()));
                            addFlag = true;
                        }
                    }
                }
                if (addFlag == false) {
                    HashMap map = new HashMap();
                    Double timeInWork = Double.parseDouble(item[1].toString());
                    map.put("TimeInWork", timeInWork);
                    map.put("DateIn", item[0].toString());
                    actualWorks.add(map);
                }
            }

            int sz = DAY_IN_LIST - actualWorks.size();
            for (int i = 1; i < sz; i++) {
                    HashMap map = new HashMap();
                    Calendar cal = Calendar.getInstance();
                    cal.add(Calendar.DATE, i);
                    map.put("TimeInWork", 0);
                    map.put("DateIn", formatDate.format(cal.getTime()));
                    actualWorks.add(map);

            }
        } catch (Exception e){

        }
    }
    private Date getFirstDayOfWeek(){
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY){
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            cal.add(Calendar.DATE, -8);
        } else {
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            cal.add(Calendar.DATE, -1);
        }
        return cal.getTime();
    }
    private int getDay(String date){
        String str = date.substring(8, 10);
        return Integer.parseInt(str);
    }

    public ArrayList<HashMap> getActualWorks() {
        return actualWorks;
    }
}
