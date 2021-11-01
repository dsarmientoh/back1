package com.dsarmiento.ciclo3.reto3.service;

import com.dsarmiento.ciclo3.reto3.model.Client;
import com.dsarmiento.ciclo3.reto3.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;
    public List<Client> getAll(){
        return (List<Client>) clientRepository.getAll();
    }
    public Optional<Client> getClient(int id){
        return clientRepository.getClient(id);
    }
    public Client save(Client c){
        if (c.getIdClient()==null){
            return clientRepository.save(c);
        }
        else {
            Optional<Client> paux=clientRepository.getClient(c.getIdClient());
            if (paux.isEmpty()){
                return clientRepository.save(c);
            }
            else {
                return c;
            }
        }
    }
     public Client update(Client c){
        if(c.getIdClient()!=null){
            Optional<Client> e= clientRepository.getClient(c.getIdClient());
            if(!e.isEmpty()){
                if(c.getName()!=null){
                    e.get().setName(c.getName());
                }
                if(c.getAge()!=null){
                    e.get().setAge(c.getAge());
                }
                if(c.getPassword()!=null){
                    e.get().setPassword(c.getPassword());
                }
                clientRepository.save(e.get());
                return e.get();
            }else{
                return c;
            }
        }else{
            return c;
        }
    }

    public boolean deleteClient(int clientId) {
        Boolean aBoolean = getClient(clientId).map(c -> {
            clientRepository.delete(c);
            return true;
        }).orElse(false);
        return aBoolean;
    }    
}
