package js.study.study.restfulapi.dao;

import js.study.study.restfulapi.bean.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();

    private static int usersCount = 3;
    static {
        users.add(new User(1, "Jaeseop", new Date(), "password", "901209"));
        users.add(new User(2, "Eunju", new Date(), "password", "901224"));
        users.add(new User(3, "Gamja", new Date(), "password", "201203"));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++usersCount);
        }

        if (user.getJoinDate() == null) {
            user.setJoinDate(new Date());
        }

        users.add(user);
        return user;
    }

    public User findOne(int id) {
//        return users.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }

        return null;
    }

    public User deleteById(int id) {
        Iterator<User> iterator = users.iterator();

        while (iterator.hasNext()) {
            User user = iterator.next();

            if (user.getId() == id) {
                iterator.remove();
                return user;
            }
        }

        return null;
    }
}
