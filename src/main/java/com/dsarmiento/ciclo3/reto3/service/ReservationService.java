package com.dsarmiento.ciclo3.reto3.service;

import com.dsarmiento.ciclo3.reto3.model.Reservation;
import com.dsarmiento.ciclo3.reto3.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    public List<Reservation> getAll(){
        return (List<Reservation>) reservationRepository.getAll();
    }
    public Optional<Reservation> getReservation(int id){
        return reservationRepository.getReservation(id);
    }
    public Reservation save(Reservation r){
        if (r.getIdReservation()==null){
            return reservationRepository.save(r);
        }
        else {
            Optional<Reservation> paux=reservationRepository.getReservation(r.getIdReservation());
            if (paux.isEmpty()){
                return reservationRepository.save(r);
            }
            else {
                return r;
            }
        }
    }
    public Reservation update(Reservation r){
        if(r.getIdReservation()!=null){
            Optional<Reservation> e= reservationRepository.getReservation(r.getIdReservation());
            if(!e.isEmpty()){

                if(r.getStartDate()!=null){
                    e.get().setStartDate(r.getStartDate());
                }
                if(r.getDevolutionDate()!=null){
                    e.get().setDevolutionDate(r.getDevolutionDate());
                }
                if(r.getStatus()!=null){
                    e.get().setStatus(r.getStatus());
                }
                reservationRepository.save(e.get());
                return e.get();
            }else{
                return r;
            }
        }else{
            return r;
        }
    }

    public boolean deleteReservation(int reservationId) {
        Boolean aBoolean = getReservation(reservationId).map(reservation -> {
            reservationRepository.delete(reservation);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
