package ru.javaops.topjava2.util;

import lombok.experimental.UtilityClass;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.javaops.topjava2.model.Dish;
import ru.javaops.topjava2.model.Menu;
import ru.javaops.topjava2.model.Role;
import ru.javaops.topjava2.model.User;
import ru.javaops.topjava2.to.DishTo;
import ru.javaops.topjava2.to.UserTo;

@UtilityClass
public class DishUtil {

    public static Dish updateFromTo(DishTo dishTo, Menu menu) {
        return new Dish(dishTo.getName(), dishTo.getPrice(), menu, dishTo.getId());
    }

}