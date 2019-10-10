package br.com.utfpr.projeto.projetofinaladministrativo.controller;

import br.com.utfpr.projeto.projetofinaladministrativo.model.Produto;
import br.com.utfpr.projeto.projetofinaladministrativo.service.CategoriaService;
import br.com.utfpr.projeto.projetofinaladministrativo.service.MarcaService;
import br.com.utfpr.projeto.projetofinaladministrativo.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    public String findAll(@RequestParam("page") Optional<Integer> page,
                          @RequestParam("size") Optional<Integer> size,
                          Model model) {

        int curretPage = page.orElse(1);
        int pageSize = size.orElse(5);
        Page<Produto> list = this.produtoService.findAll(
                PageRequest.of(curretPage - 1, pageSize)
        );
        model.addAttribute("produtos", list);
        if (list.getTotalPages() > 0) {
            List<Integer> pageNumbers = IntStream
                    .rangeClosed(1, list.getTotalPages())
                    .boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
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
