package ru.pract.tacocloud.repository;

import org.springframework.data.repository.CrudRepository;
import ru.pract.tacocloud.entity.TacoOrder;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {

}
