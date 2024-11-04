package ufu.piloto.repository;

import ufu.piloto.error.user.InvalidPasswordException;
import ufu.piloto.error.user.UserAlreadyExists;
import ufu.piloto.error.user.UserNotFoundException;
import ufu.piloto.model.user.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private static int autoincrementId = 0;
    private final static List<User> users = new ArrayList<>();

    public User login(String email, String password) throws InvalidPasswordException, UserNotFoundException {
        for (User user : users)
            if (user.getEmail().equals(email)) {
                if (user.getPassword().equals(password))
                    return user;

                throw new InvalidPasswordException(email);
            }

        throw new UserNotFoundException(email);
    }

    public User register(User user) throws UserAlreadyExists {
        for (User registredUser : users)
            if (user.getEmail().equalsIgnoreCase(registredUser.getEmail()))
                throw new UserAlreadyExists(user.getEmail());

        User newUser = new User(
            "" + autoincrementId++,
            user.getName(),
            user.getEmail(),
            user.getPassword(),
            LocalDate.now(),
            LocalDate.now()
        );

        users.add(newUser);

        return newUser;

    }

}
