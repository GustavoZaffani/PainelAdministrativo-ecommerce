package br.com.utfpr.projeto.projetofinaladministrativo.controller;

import br.com.utfpr.projeto.projetofinaladministrativo.model.Estado;
import br.com.utfpr.projeto.projetofinaladministrativo.model.Fornecedor;
import br.com.utfpr.projeto.projetofinaladministrativo.service.CidadeService;
import br.com.utfpr.projeto.projetofinaladministrativo.service.EstadoService;
import br.com.utfpr.projeto.projetofinaladministrativo.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("fornecedor")
public class FornecedorController {

    @Autowired
    private FornecedorService fornecedorService;
    @Autowired
    private EstadoService estadoService;
    @Autowired
    private CidadeService cidadeService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("fornecedores", fornecedorService.findAll());
        return "fornecedor/list";
    }

    @GetMapping("form")
    public String form(Model model) {
        loadCombos(model);
        model.addAttribute("fornecedor", new Fornecedor());
        return "fornecedor/form";
    }

    @PostMapping
    public ResponseEntity save(@Valid Fornecedor fornecedor,
                               BindingResult result,
                               Model model,
                               RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        fornecedorService.save(fornecedor);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("{id}")
    public String findById(@PathVariable Long id, Model model) {
        loadCombos(model);
        model.addAttribute("fornecedor", fornecedorService.findOne(id));
        return "fornecedor/form";
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        try {
            fornecedorService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    public void loadCombos(Model model) {
        model.addAttribute("estados", estadoService.findAll());
        model.addAttribute("cidades", cidadeService.findAll());
    }

    // TODO necessário verificar maneira de fazer um complete
    @GetMapping("complete/{texto}")
    public String complete(@PathVariable("texto") String texto,
                           Model model) {
        model.addAttribute("cidades", cidadeService.findAll());
        model.addAttribute("estados", estadoService.complete(texto));
        return "fornecedor/form";
    }

}
