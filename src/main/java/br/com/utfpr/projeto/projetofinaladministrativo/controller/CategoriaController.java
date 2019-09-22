package br.com.utfpr.projeto.projetofinaladministrativo.controller;

import br.com.utfpr.projeto.projetofinaladministrativo.model.Categoria;
import br.com.utfpr.projeto.projetofinaladministrativo.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("categorias", categoriaService.findAll());
        return "categoria/list";
    }

    @GetMapping("form")
    public String form(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "categoria/form";
    }

    @PostMapping
    public String save(@Valid Categoria categoria,
                       BindingResult result,
                       Model model,
                       RedirectAttributes attributes) {
        if (result.hasErrors()) {
            model.addAttribute("categoria", categoria);
            return "categoria/form";
        }

        categoriaService.save(categoria);
        attributes.addFlashAttribute("sucesso",
                "Registro salvo com sucesso!");
        return "redirect:/categoria";
    }

    @GetMapping("{id}")
    public String findById(@PathVariable Long id, Model model) {
        model.addAttribute("categoria", categoriaService.findOne(id));
        return "categoria/form";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable Long id,
                         RedirectAttributes attributes) {
        try {
            categoriaService.delete(id);
            attributes.addFlashAttribute("sucesso",
                    "Registro removido com sucesso!");
        } catch (Exception e) {
            attributes.addFlashAttribute("erro",
                    "Falha ao remover o registro!");
        }
        return "redirect:/categoria";
    }
}
