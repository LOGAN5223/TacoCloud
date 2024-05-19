package ru.pract.tacocloud.repository;

import org.springframework.data.repository.CrudRepository;
import ru.pract.tacocloud.entity.TacoOrder;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {

}
