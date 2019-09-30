package br.com.utfpr.projeto.projetofinaladministrativo.controller;

import br.com.utfpr.projeto.projetofinaladministrativo.model.Produto;
import br.com.utfpr.projeto.projetofinaladministrativo.service.CategoriaService;
import br.com.utfpr.projeto.projetofinaladministrativo.service.MarcaService;
import br.com.utfpr.projeto.projetofinaladministrativo.service.ProdutoService;
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
@RequestMapping("produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private MarcaService marcaService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("produtos", produtoService.findAll());
        return "produto/list";
    }

    @GetMapping("form")
    public String form(Model model) {
        loadCombo(model);
        model.addAttribute("produto", new Produto());
        return "produto/form";
    }

    @PostMapping
    public ResponseEntity save(@Valid Produto produto,
                               BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        produtoService.save(produto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("{id}")
    public String findById(@PathVariable Long id, Model model) {
        loadCombo(model);
        model.addAttribute("produto", produtoService.findOne(id));
        return "produto/form";
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            produtoService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    private void loadCombo(Model model) {
        model.addAttribute("categorias", categoriaService.findAll());
        model.addAttribute("marcas", marcaService.findAll());
    }
}
