package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import java.sql.SQLException;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }
    @Override
    public void createUsersTable() throws SQLException {
        Util.openTransaction();
        Session session = Util.getSession();
        session.createSQLQuery("CREATE TABLE IF NOT EXISTS myUsers" +
                "(id INT NOT NULL AUTO_INCREMENT," +
                "name VARCHAR(30)," +
                "lastname VARCHAR(30)," +
                "age TINYINT(100)," +
                "PRIMARY KEY (id))").executeUpdate();
        Util.closeTransaction();
    }
    @Override
    public void dropUsersTable() throws SQLException {
        Util.openTransaction();
        Session session = Util.getSession();
        session.createSQLQuery("DROP TABLE IF EXISTS myUsers").executeUpdate();
        Util.closeTransaction();
    }
    @Override
    public void saveUser(String name, String lastName, byte age) throws SQLException {
        Util.openTransaction();
        Session session = Util.getSession();
        session.save(new User(name, lastName, age));
        Util.closeTransaction();
    }
    @Override
    public void removeUserById(long id) throws SQLException {
        Util.openTransaction();
        Session session = Util.getSession();
        session.delete(session.load(User.class, id));
        Util.closeTransaction();
    }
    @Override
    public List<User> getAllUsers() throws SQLException {
        Util.openTransaction();
        Session session = Util.getSession();
        List<User> users = null;

        users = session.createQuery("from User").list();
        Util.closeTransaction();
        return users;
    }
    @Override
    public void cleanUsersTable() throws SQLException {
        Util.openTransaction();
        Session session = Util.getSession();
        session.createSQLQuery("TRUNCATE TABLE myUsers ").executeUpdate();
        Util.closeTransaction();
    }
}