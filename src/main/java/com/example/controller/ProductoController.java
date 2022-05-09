package com.example.controller;

import com.example.model.Producto;
import com.example.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.Files;
import org.springframework.stereotype.Controller;

@Controller
public class ProductoController {

    @Autowired
    ProductoService service;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("list", service.list());
        return "list";
    }

    @RequestMapping("/showProducto")
    public String show(Model model) {
        Producto producto = new Producto();
        model.addAttribute("producto", producto);
        return "save";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("producto") Producto producto, @RequestParam("file") MultipartFile imagen) {
        if (!imagen.isEmpty()) {
            Path directorioImagenes = Paths.get("src//main//resources//static/img");
            String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
            try {
                byte[] bytesImg = imagen.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
                Files.write(rutaCompleta, bytesImg);
                producto.setImagen(imagen.getOriginalFilename());
            } catch (IOException e) {
                e.getMessage();
            }
        }
        service.save(producto);
        return "redirect:/list";
    }
}
