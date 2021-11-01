package com.dsarmiento.ciclo3.reto3.repository.crud;

import com.dsarmiento.ciclo3.reto3.model.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageCrudRepository extends CrudRepository<Message, Integer> {
}
