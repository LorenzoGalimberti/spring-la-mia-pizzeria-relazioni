package org.exercises.springlamiapizzeriacrud.controller;

import org.exercises.springlamiapizzeriacrud.model.Pizza;
import org.exercises.springlamiapizzeriacrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.yaml.snakeyaml.events.Event;

import java.util.List;
import java.util.Optional;

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
    public String leNostrePizze(Model model) {



        List<Pizza> pizzeList = pizzaRepository.findAll(); // Altrimenti, recupera la lista di tutte le pizze.
        model.addAttribute("pizze", pizzeList);


        return "pizze-list";
    }

    @GetMapping("/le-nostre-pizze/{slug}")
    public String visualizzaPizzaPerId(@PathVariable("slug") String slug, Model model) {
        // Usa il repository per trovare la pizza per l'ID specifico
        Optional<Pizza> pizzaOptional = pizzaRepository.findPizzaBySlug(slug);

        /*
        *  if (pizzaOptional.isPresent()) {
            Pizza pizza = pizzaOptional.get();
            model.addAttribute("pizza", pizza);
            return "dettagli-pizza"; // Sostituisci "visualizza-pizza" con il nome della tua pagina di visualizzazione pizza.
        } else {
            // Gestisci il caso in cui la pizza non esista
            return "pizza-non-trovata"; // Crea una pagina personalizzata per gestire questa situazione.
        }
        * */
       // Pizza pizza = pizzaOptional.get();
        Pizza pizza = pizzaOptional.orElse(null);

        if (pizza != null) {
            model.addAttribute("pizza", pizza);
            return "dettagli-pizza";
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "pizza with id " + slug + " not found");
        }



    }


}
