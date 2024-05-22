package ru.pract.tacocloud.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pract.tacocloud.entity.UserTable;
import ru.pract.tacocloud.security.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserUtils {
    private EntityManager entityManager;

    public UserUtils(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public List<UserTable> buildUsers(){
        return entityManager.createQuery("SELECT u FROM UserTable u", UserTable.class).getResultList();
    }
}
