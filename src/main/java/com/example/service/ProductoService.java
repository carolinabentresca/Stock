package com.example.service;

import java.util.*;
import com.example.model.Producto;
import com.example.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

    @Autowired
    ProductoRepository repository;

    //List Productos
    public List<Producto> list() {
        return repository.findAll();
    }

    //Save Producto
    public Producto save(Producto producto) {
        return repository.save(producto);
    }

}
