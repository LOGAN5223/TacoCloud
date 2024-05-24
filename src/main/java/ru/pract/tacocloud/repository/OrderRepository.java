package ru.pract.tacocloud.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import ru.pract.tacocloud.entity.TacoOrder;
import ru.pract.tacocloud.entity.UserTable;

import java.util.List;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
    List<TacoOrder> findByUser_table_idOrderByPlaced_atDesc (UserTable userTable, Pageable pageable);
}
