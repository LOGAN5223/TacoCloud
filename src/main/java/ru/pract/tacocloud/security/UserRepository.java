package ru.pract.tacocloud.security;

import org.springframework.data.repository.CrudRepository;
import ru.pract.tacocloud.entity.UserTable;

public interface UserRepository extends CrudRepository<UserTable, Long> {
    UserTable findByUsername(String name);
}
