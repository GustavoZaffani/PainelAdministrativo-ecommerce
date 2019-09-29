package br.com.utfpr.projeto.projetofinaladministrativo.controller;

import br.com.utfpr.projeto.projetofinaladministrativo.model.Marca;
import br.com.utfpr.projeto.projetofinaladministrativo.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("marca")
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("marcas", marcaService.findAll());
        return "marca/list";
    }

    @GetMapping("form")
    public String form(Model model) {
        model.addAttribute("marca", new Marca());
        return "marca/form";
    }

    @PostMapping
    public ResponseEntity save(@Valid Marca marca,
                               BindingResult result,
                               Model model,
                               RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        marcaService.save(marca);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("{id}")
    public String findById(@PathVariable Long id, Model model) {
        model.addAttribute("marca", marcaService.findOne(id));
        return "marca/form";
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id,
                         RedirectAttributes attributes) {
        try {
            marcaService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
