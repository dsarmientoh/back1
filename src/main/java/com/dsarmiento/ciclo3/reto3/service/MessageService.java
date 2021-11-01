package com.dsarmiento.ciclo3.reto3.service;

import com.dsarmiento.ciclo3.reto3.model.Message;
import com.dsarmiento.ciclo3.reto3.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;
    public List<Message> getAll(){
        return (List<Message>) messageRepository.getAll();
    }
    public Optional<Message> getMessage(int id){
        return messageRepository.getMessage(id);
    }
    public Message save(Message m){
        if(m.getIdMessage()== null){
            return messageRepository.save(m);
        }
        else {
            Optional<Message> paux=messageRepository.getMessage(m.getIdMessage());
            if (paux.isEmpty()){
                return messageRepository.save(m);
            }
            else {
                return m;
            }
        }
    }
    public Message update(Message m){
        if(m.getIdMessage()!=null){
            Optional<Message> e= messageRepository.getMessage(m.getIdMessage());
            if(!e.isEmpty()){
                if(m.getMessageText()!=null){
                    e.get().setMessageText(m.getMessageText());
                }
                messageRepository.save(e.get());
                return e.get();
            }else{
                return m;
            }
        }else{
            return m;
        }
    }

    public boolean deleteMessage(int messageId) {
        Boolean aBoolean = getMessage(messageId).map(message -> {
            messageRepository.delete(message);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
