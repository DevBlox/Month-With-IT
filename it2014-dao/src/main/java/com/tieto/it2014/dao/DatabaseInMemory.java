package com.tieto.it2014.dao;

import com.tieto.it2014.domain.user.entity.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class DatabaseInMemory {

    public static final List<User> users = new ArrayList<>(Arrays.asList(
            new User(1L, "Simas", 1992),
            new User(2L, "Živilė", 1994),
            new User(3L, "Tomas", 1993),
            new User(4L, "Jolanta", 1997)
    ));

    public static final AtomicLong lastUserId = new AtomicLong(users.size());

}
