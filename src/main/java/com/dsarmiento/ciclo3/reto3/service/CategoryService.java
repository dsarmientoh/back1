package com.dsarmiento.ciclo3.reto3.service;

import com.dsarmiento.ciclo3.reto3.model.Category;
import com.dsarmiento.ciclo3.reto3.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    public List<Category> getAll(){
        return (List<Category>) categoryRepository.getAll();
    }
    public Optional<Category> getCategory(int id){
        return categoryRepository.getCategory(id);
    }
    public Category save(Category c){
        if (c.getId() == null){
            return categoryRepository.save(c);
        }
        else {
            Optional<Category> paux=categoryRepository.getCategory(c.getId());
            if (paux.isEmpty()){
                return categoryRepository.save(c);
            }
            else {
                return c;
            }
        }
    }
    public Category update(Category c){
        if(c.getId()!=null){
            Optional<Category>g=categoryRepository.getCategory(c.getId());
            if(!g.isEmpty()){
                if(c.getDescription()!=null){
                    g.get().setDescription(c.getDescription());
                }
                if(c.getName()!=null){
                    g.get().setName(c.getName());
                }
                return categoryRepository.save(g.get());
            }
        }
        return c;
    }
    public boolean deleteCategoria(int categoriaId){
        Boolean d=getCategory(categoriaId).map(c -> {
            categoryRepository.delete(c);
            return true;
        }).orElse(false);
        return d;
    }    
}
