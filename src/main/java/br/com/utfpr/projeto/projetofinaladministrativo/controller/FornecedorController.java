package br.com.utfpr.projeto.projetofinaladministrativo.controller;

import br.com.utfpr.projeto.projetofinaladministrativo.model.Fornecedor;
import br.com.utfpr.projeto.projetofinaladministrativo.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("fornecedor")
public class FornecedorController {

    @Autowired
    private FornecedorService fornecedorService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("fornecedores", fornecedorService.findAll());
        return "fornecedor/list";
    }

    @GetMapping("form")
    public String form(Model model) {
        model.addAttribute("fornecedor", new Fornecedor());
        return "fornecedor/form";
    }

    @PostMapping
    public String save(@Valid Fornecedor fornecedor,
                       BindingResult result,
                       Model model,
                       RedirectAttributes attributes) {
        if (result.hasErrors()) {
            model.addAttribute("fornecedor", fornecedor);
            return "fornecedor/form";
        }

        fornecedorService.save(fornecedor);
        attributes.addFlashAttribute("sucesso",
                "Registro salvo com sucesso!");
        return "redirect:/fornecedor";
    }

    @GetMapping("{id}")
    public String findById(@PathVariable Long id, Model model) {
        model.addAttribute("fornecedor", fornecedorService.findOne(id));
        return "fornecedor/form";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable Long id,
                         RedirectAttributes attributes) {
        try {
            fornecedorService.delete(id);
            attributes.addFlashAttribute("sucesso",
                    "Registro removido com sucesso!");
        } catch (Exception e) {
            attributes.addFlashAttribute("erro",
                    "Falha ao remover o registro!");
        }
        return "redirect:/fornecedor";
    }
}
