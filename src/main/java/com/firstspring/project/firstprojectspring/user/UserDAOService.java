package com.firstspring.project.firstprojectspring.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDAOService {

    private static int usersCount = 0;
    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User(++usersCount, "Andrew", LocalDate.now().minusYears(35)));
        users.add(new User(++usersCount, "Emma", LocalDate.now().minusYears(28)));
        users.add(new User(++usersCount, "Ryan", LocalDate.now().minusYears(30)));
    }


    public List<User> findAll() {
        return users;
    }

    public User findOne(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public void deleteOne(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        users.removeIf(predicate);
    }

    public User save(User user) {
        user.setId(++usersCount);
        users.add(user);
        return user;
    }
}
