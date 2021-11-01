package com.dsarmiento.ciclo3.reto3.repository.crud;


import com.dsarmiento.ciclo3.reto3.model.Reservation;
import org.springframework.data.repository.CrudRepository;

public interface ReservationCrudRepository extends CrudRepository<Reservation, Integer> {
}
