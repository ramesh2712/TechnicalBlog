package blog.common;

import blog.model.User;
import com.google.common.hash.Hashing;
import org.hibernate.Session;


@SuppressWarnings("unchecked")

public class UserManager extends SessionManager{

    public UserManager() {
    }

    // Create user
    public User registerUser(User user) {
        Session session = openSession();
        session.save(user);
        commitSession(session);
        return user;
    }

    // Delete user
    public void deleteUser (User user) {
        Session session = openSession();
        session.delete(user);
        commitSession(session);
    }

    // Read User
    public User getUser(String userName) {
        Session session = openSession();
        User user = (User) session.get(User.class , userName);
        commitSession(session);
        return user;
    }

    public boolean isValidUser(final String userName , final String password) {

        Session session = openSession();
        User userFromDB = (User) session.get(User.class , userName);
        commitSession(session);

        if(userFromDB == null) return false;

        String hashOfPassword = userFromDB.getPasswordHash();

        String hashofUserEnteredPassword = Hashing.sha256()
                .hashString(password)
                .toString();

        return  hashOfPassword.equals(hashofUserEnteredPassword);

    }
}
