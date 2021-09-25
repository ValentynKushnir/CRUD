package hillel.util;

import static java.util.Arrays.asList;

import java.util.*;

import hillel.model.User;

public class UserUtil {

    public static final Long TEN_YEARS_IN_MILLIS = 315_360_000_000L;

    public static final List<String> bankOfNames = new ArrayList<>();

    static {
        bankOfNames.addAll(asList(
            "Fred Clark", "Rosemary Clooney", "Alan Curtis", "Laird Cregar", "Laird Cregar",
            "Neil Diamond", "Joan Davis", "Hal David", "Gloria DeHaven", "Bruce Dern",
            "Ken Ehrlich", "Dale Evans", "Dick Foran", "Eddie Foy", "Eddie Fisher", "Al Goodman"));
    }

    public static User newUser(String name) {
        User user = new User();
        user.setName(name);
        user.setCreatedDate(new Date((long) (System.currentTimeMillis() - new Random().nextDouble() * TEN_YEARS_IN_MILLIS * 1.0)));
        user.setIsAdmin(new Random().nextBoolean());
        user.setAge(new Random().nextInt(20) + 20);
        return user;
    }

}
