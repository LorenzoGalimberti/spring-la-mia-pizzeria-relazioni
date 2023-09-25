package org.exercises.springlamiapizzeriacrud.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.exercises.springlamiapizzeriacrud.Utils;
import org.exercises.springlamiapizzeriacrud.model.Pizza;
import org.exercises.springlamiapizzeriacrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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


    @GetMapping("/pizze")
    public String listaPizze(@RequestParam(name = "orderBy", defaultValue = "price") String orderBy, Model model) {
        List<Pizza> pizze;

        if ("price".equals(orderBy)) {
            pizze = pizzaRepository.findAllByOrderByPriceAsc(); // Ordina per prezzo ascendente
        } else {
            pizze = pizzaRepository.findAllByOrderByPriceDesc(); // Ordina per nome (valore predefinito)
        }

        model.addAttribute("pizze", pizze);
        return "pizze-list";
    }

    @GetMapping("/le-nostre-pizze")
    public String leNostrePizze(@RequestParam(name = "orderBy",required = false) String orderBy,@RequestParam(name = "keyword", required = false) String keyword,Model model) {
        List<Pizza> pizzeList;
        String searchKeyword = "";

        if ("price".equals(orderBy)) {
            pizzeList = pizzaRepository.findAllByOrderByPriceAsc(); // Ordina per prezzo ascendente
        } else if ("priceup".equals(orderBy)) {
            pizzeList = pizzaRepository.findAllByOrderByPriceDesc(); // Ordina per nome
        } else {
            pizzeList = pizzaRepository.findAll(); // Nessun ordinamento specificato, recupera tutte le pizze.
        }

        if (keyword != null && !keyword.isEmpty()) {
            searchKeyword=keyword;
            // Se è stata fornita una parola chiave, esegui la ricerca delle pizze con quella parola chiave.
            pizzeList = pizzaRepository.findByDescriptionOrNameContainingIgnoreCase( keyword);
        }


        //List<Pizza> pizzeList = pizzaRepository.findAll(); // Altrimenti, recupera la lista di tutte le pizze.
        model.addAttribute("pizze", pizzeList);
        model.addAttribute("keyword",searchKeyword);


        return "pizze-list";
    }

    @GetMapping("/le-nostre-pizze/{slug}")
    public String visualizzaPizzaPerId(@PathVariable("slug") String slug, Model model) {
        // Usa il repository per trovare la pizza per l'ID specifico
        Optional<Pizza> pizzaOptional = pizzaRepository.findBySlug(slug);

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


    // controller che mostra la pagina di creazione di un form

    @GetMapping("/le-nostre-pizze/create")
    public  String create(Model model){
        model.addAttribute("pizza",new Pizza());
        return "crea-pizza";
    }

    // metodo per la gestione del form

    @PostMapping("/le-nostre-pizze/create")
    // valid per la validazione biningresult è la mappa degli errori eventuali della validation
    public  String doCreate(@Valid @ModelAttribute("pizza") Pizza formPizza , BindingResult bindingResult){
        // verifico errori di validazione

        if (! bindingResult.hasErrors()){
            // set dello slug
            formPizza.setSlug(Utils.getSlug(formPizza.getName()));
            // salva l' elemento nel database
            pizzaRepository.save(formPizza);
        }else{
            return "crea-pizza";
        }


        // per la validazione guarda la classe Pizza

        return("redirect:/le-nostre-pizze");
    }

    // get del form


    @GetMapping("/le-nostre-pizze/{slug}/edit")
    public  String update(@PathVariable("slug") String slug,Model model){
        // cerco su database il libro con quell'id
        Optional<Pizza> result = pizzaRepository.findBySlug(slug);

        // verifico se il book è presente
        if (result.isPresent()) {
            // passo il Book al model come attributo
            model.addAttribute("pizza", result.get());
            // ritorno il template con il form di edit
            return "edit";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza " + slug + " not found");
        }
    }


    // postmapping che riceve il submit
    @PostMapping("/le-nostre-pizze/{slug}/edit")
    public String doEdit(@PathVariable("slug") String slug, @Valid @ModelAttribute("pizza") Pizza formPizza,
                         BindingResult bindingResult) {
        Optional<Pizza> pizzaResult=pizzaRepository.findBySlug(slug);
        formPizza.setId(pizzaResult.get().getId());
        if (bindingResult.hasErrors()) {
            // si sono verificati degli errori di validazione
            return "edit"; // nome del template per ricreare la view
        }
        // salvo il Book
        pizzaRepository.save(formPizza);
        return "redirect:/le-nostre-pizze";
    }

// delete
    @GetMapping("/le-nostre-pizze/{slug}/delete")

    public String doDelete(@PathVariable("slug") String slug){
        Optional<Pizza> pizzaResult=pizzaRepository.findBySlug(slug);
        pizzaRepository.deleteById(pizzaResult.get().getId());

        return "redirect:/le-nostre-pizze";
    }

}
