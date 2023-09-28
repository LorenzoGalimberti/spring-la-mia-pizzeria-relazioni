package org.exercises.springlamiapizzeriacrud.controller;

import org.exercises.springlamiapizzeriacrud.model.Ingrediente;
import org.exercises.springlamiapizzeriacrud.repository.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/ingredienti")
public class IngredientiController {
    @Autowired
    private IngredienteRepository ingredienteRepository;
    @GetMapping
    String showIngredients(Model model){
        Ingrediente ingredienteObj = new Ingrediente(); // Crea un nuovo oggetto Ingrediente
        model.addAttribute("ingredienteObj", ingredienteObj); // Aggiungi l'oggetto al model
        model.addAttribute("ingredienti",ingredienteRepository.findAll());
        return "ingredienti/index";
    }

    // metodo doCreate che salva la nuova categoria
    @PostMapping("/create")
    public String doCreate(@ModelAttribute("ingredienteObj") Ingrediente ingredienteForm,
                           RedirectAttributes redirectAttributes) {
        // prende l'oggetto Category dalla request
        // lo salva su database
        ingredienteRepository.save(ingredienteForm);
        // aggiungo ai redirectAttributes un flasAttribute con un messaggio di conferma
        redirectAttributes.addFlashAttribute("message", "Ingrediente aggiunto con successo !!");
        // fa la redirect alla pagina index
        return "redirect:/ingredienti";
    }

    // delete dell' ingrediente preso per id

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {

       ingredienteRepository.deleteById(id);
        // faccio la redirect alla index
        return "redirect:/ingredienti";
    }
}
