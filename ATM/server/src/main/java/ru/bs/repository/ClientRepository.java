package ru.bs.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.bs.entity.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {

}
