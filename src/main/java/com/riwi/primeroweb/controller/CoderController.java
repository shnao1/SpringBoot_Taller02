package com.riwi.primeroweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.riwi.primeroweb.entity.Coder;
import com.riwi.primeroweb.service.CoderService;

@Controller
// Crear la ruta donde se activara este controlador
@RequestMapping("/")
public class CoderController {
    // Establecer la inyeccion de dependencias
    @Autowired
    private CoderService objCoderService;

    // Metodo para mostrar la vista y enviarle toda la lista de coders

    @GetMapping
    public String showViewCoder(Model objModel){
        // Obtenemos la lista de coders
        java.util.List<Coder> listCoders = this.objCoderService.findAll();

        // Cargamos la lista en el modelo
        objModel.addAttribute("listCoders", listCoders);
        return "viewCoders";
    }

    // Metodo para mostrar la vista del formulario y ademas
    // enviar una estancia de coder vacia.
    @GetMapping("form")
    public String showViewForm(Model model){
        model.addAttribute("coder", new Coder());
        model.addAttribute("action", "/create-coder");
        return "viewForm";
    }

    // Metodo para recibir toda la informacion del formulario
    // @ModelAttribute lo utilizamos para recibir informacion de la vista

    @PostMapping("create-coder")
    public String createCoder(@ModelAttribute Coder objCoder){
        this.objCoderService.create(objCoder);
        return "redirect:/";
    }

    // Controlador para eliminar, recibira como parametro el id por URL
    // @PathVariable funciona para obtener el valor de una variable en una URL
    // solo si es de tipo path (ejm: /delete/10) donde el 10 es dinamico.
    @GetMapping("/delete/{id}")
    public String deleteCoder(@PathVariable Long id){
        this.objCoderService.delete(id);

        // Redireccionar a la lista de coders.
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editCoder(@PathVariable Long id,Model model){

        Coder objCoder = this.objCoderService.findById(id);
        model.addAttribute("coder", objCoder);
        model.addAttribute("action", "/edit/" + id);
        return "viewForm";
    }


}
