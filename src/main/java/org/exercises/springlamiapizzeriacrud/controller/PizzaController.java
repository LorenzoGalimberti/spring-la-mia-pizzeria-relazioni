package org.exercises.springlamiapizzeriacrud.controller;

import org.exercises.springlamiapizzeriacrud.model.Pizza;
import org.exercises.springlamiapizzeriacrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class PizzaController {
    @Autowired
    private PizzaRepository pizzaRepository;
    @GetMapping
    public String index(){
        return "homepage";
    }

    @GetMapping("/le-nostre-pizze")
    public String leNostrePizze(Model model){
        List<Pizza> pizzeList = pizzaRepository.findAll(); // questa è la lista di libri presa da database
        model.addAttribute("pizze", pizzeList); // passo la lista di libri al model

        return "pizze-list";
    }

}
