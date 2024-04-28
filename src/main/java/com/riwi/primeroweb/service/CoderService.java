package com.riwi.primeroweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.riwi.primeroweb.entity.Coder;
import com.riwi.primeroweb.repository.CoderRepository;

@Service
public class CoderService {

    // Autowired Registra la inyeccion de dependencias en SPRING BOOT
    @Autowired
    private CoderRepository objCoderRepository;

    public List<Coder> findAll(){

        return this.objCoderRepository.findAll();
    }

    // Metodo para crear un nuevo coder, se hace uso del respositorio
    // y del metodo save, el cual se encarga de insertar en la Base de Datos.

    public Coder create(Coder objCoder){
        return this.objCoderRepository.save(objCoder);
    }

    // Metodo para eliminar un Coder (deleteById)
    public void delete(Long id){
        // Llamar al repositorio
        this.objCoderRepository.deleteById(id);
    }

    // Metodo para obtener por Id
    public Coder findById(Long id){
        // Busca un coder por ID y en caso de no ser encontrado, devuelve un null.
        return this.objCoderRepository.findById(id).orElse(null);
    }
}
