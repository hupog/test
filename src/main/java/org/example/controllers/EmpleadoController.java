package org.example.controllers;

import jakarta.validation.Valid;
import org.example.domain.Empleado;
import org.example.services.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping({ "/", "/list"})
    public String showList(@RequestParam(required = false)Integer numMsg, Model model) {
        if(numMsg != null){
            switch(numMsg){
                case 1 -> model.addAttribute("msg", "Empleado no encontrado");
                case 2 -> model.addAttribute("msg", "Formulario incorrecto");
            }
        }
        model.addAttribute("listaEmpleados", empleadoService.obtenerTodos());
        return "listView";
    }

    @GetMapping("/{id}")
    public String showElement(@PathVariable Long id, Model model) {
        Empleado empleado = empleadoService.obtenerPorId(id);
        if(empleado != null)return "redirect:/?numMsg=1";
        model.addAttribute("empleado", empleado);
        return "listOneView";
    }

    @GetMapping("/nuevo")
    public String showNew(Model model) {
        model.addAttribute("empleadoForm", new Empleado());
        return "newFormView";
    }

    @PostMapping("/nuevo/submit")
    public String showNewSubmit(@Valid Empleado empleadoForm, BindingResult result) {
        if(result.hasErrors()) return "redirect:/?numMsg=2";
        Empleado emp = empleadoService.añadir(empleadoForm);
        if(emp == null) return "redirect:/?numMsg=2";
        return "redirect:/list";
    }

    @GetMapping("/editar/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Empleado emp = empleadoService.obtenerPorId(id);
        if(emp == null) return "redirect:/?numMsg=1";
        model.addAttribute("empleadoForm", emp);
        return "editFormView";
    }

    @PostMapping("/editar({id}/submit")
    public String showEditSubmit(@PathVariable Long id, @Valid Empleado empleadoForm, BindingResult result) {
        if(result.hasErrors()) return "redirect:/?numMsg=2";
        Empleado emp = empleadoService.editar(empleadoForm);
        if(emp == null) return "redirect:/?numMsg=2";
        return "redirect:/list";
    }

    @GetMapping("/borrar/{id}")
    public String showDelete(@PathVariable long id) {
        empleadoService.borrar(id);
        return "redirect:/list";
    }

}
