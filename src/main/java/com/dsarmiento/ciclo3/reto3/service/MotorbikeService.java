package com.dsarmiento.ciclo3.reto3.service;

import com.dsarmiento.ciclo3.reto3.model.Motorbike;
import com.dsarmiento.ciclo3.reto3.repository.MotorbikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MotorbikeService {
    @Autowired
    private MotorbikeRepository motorbikeRepository;
    public List<Motorbike> getAll(){
        return motorbikeRepository.getAll();
    }
    public Optional<Motorbike> getMotorbike(int id){
        return motorbikeRepository.getMotorbike(id);
    }
    public Motorbike save(Motorbike m){
        if(m.getId() == null){
            return motorbikeRepository.save(m);
        }
        else {
            Optional<Motorbike> paux=motorbikeRepository.getMotorbike(m.getId());
            if (paux.isEmpty()){
                return motorbikeRepository.save(m);
            }else {
                return m;
            }
        }
    }
    public Motorbike update(Motorbike m){
        if(m.getId()!=null){
            Optional<Motorbike> e=motorbikeRepository.getMotorbike(m.getId());
            if(!e.isEmpty()){
                if(m.getName()!=null){
                    e.get().setName(m.getName());
                }
                if(m.getBrand()!=null){
                    e.get().setBrand(m.getBrand());
                }
                if(m.getYear()!=null){
                    e.get().setYear(m.getYear());
                }
                if(m.getDescription()!=null){
                    e.get().setDescription(m.getDescription());
                }
                if(m.getCategory()!=null){
                    e.get().setCategory(m.getCategory());
                }
                motorbikeRepository.save(e.get());
                return e.get();
            }else{
                return m;
            }
        }else{
            return m;
        }
    }

    public boolean deleteFinca(int motoId) {
        Boolean aBoolean = getMotorbike(motoId).map(m -> {
            motorbikeRepository.delete(m);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
