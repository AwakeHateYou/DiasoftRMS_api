package dataBase;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.io.File;
import java.util.List;

public class dbUtils {
    /**
     * Фабрика сессий
     */
    private static SessionFactory ourSessionFactory;

    /**
     * Инициализация фабрики сессий
     */
    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure(new File("E:\\workspace\\Diasoft RMS RESTFull\\src\\resources\\hibernate.cfg.xml"));
            ServiceRegistry serviceRegistry =
                    new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            ourSessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Возвращает активную сессию
     * @return активная сессия
     * @throws HibernateException ошибка hibernate
     */
    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    /**
     * Сохранение объекта в базе
     * @param object объект для сохранения
     */
    public static void save(Object object) {
        Session saveSession = getSession();
        Transaction transaction = saveSession.beginTransaction();

        try {
            saveSession.saveOrUpdate(object);
            saveSession.flush();
            transaction.commit();
        } catch (Throwable ex) {
            System.err.println(ex.getLocalizedMessage());
            transaction.rollback();
            throw ex;
        } finally {
            saveSession.close();
        }
    }

    /**
     * Возвращает список объектов указанного класса
     * @param aClass нужный класс объектов
     * @return список объектов указанного класса
     */
    public static List allObjectWithClass(Class aClass) {
        Session retrieveSession = getSession();
        List list = retrieveSession.createCriteria(aClass).list();
        retrieveSession.close();

        return list;
    }

    /**
     * Удаляет объект из базы
     * @param object объект для удаления
     */
    public static void deleteObject(Object object) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(object);
            session.flush();
            transaction.commit();
        } catch (Throwable ex) {
            System.err.println(ex.getLocalizedMessage());
            transaction.rollback();
            throw ex;
        } finally {
            session.close();
        }
    }
}
