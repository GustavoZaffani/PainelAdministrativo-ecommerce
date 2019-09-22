package br.com.utfpr.projeto.projetofinaladministrativo.controller;

import br.com.utfpr.projeto.projetofinaladministrativo.model.Produto;
import br.com.utfpr.projeto.projetofinaladministrativo.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("produtos", produtoService.findAll());
        return "produto/list";
    }

    @GetMapping("form")
    public String form(Model model) {
        model.addAttribute("produto", new Produto());
        return "produto/form";
    }

    @PostMapping
    public String save(@Valid Produto produto,
                       BindingResult result,
                       Model model,
                       RedirectAttributes attributes) {
        if (result.hasErrors()) {
            model.addAttribute("produto", produto);
            return "produto/form";
        }

        produtoService.save(produto);
        attributes.addFlashAttribute("sucesso",
                "Registro salvo com sucesso!");
        return "redirect:/produto";
    }

    @GetMapping("{id}")
    public String findById(@PathVariable Long id, Model model) {
        model.addAttribute("produto", produtoService.findOne(id));
        return "produto/form";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable Long id,
                         RedirectAttributes attributes) {
        try {
            produtoService.delete(id);
            attributes.addFlashAttribute("sucesso",
                    "Registro removido com sucesso!");
        } catch (Exception e) {
            attributes.addFlashAttribute("erro",
                    "Falha ao remover o registro!");
        }
        return "redirect:/produtos";
    }
}
