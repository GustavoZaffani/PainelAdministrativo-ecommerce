package br.com.utfpr.projeto.projetofinaladministrativo.controller;

import br.com.utfpr.projeto.projetofinaladministrativo.model.Categoria;
import br.com.utfpr.projeto.projetofinaladministrativo.service.CategoriaService;
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
@RequestMapping("categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public String findAll(@RequestParam("page") Optional<Integer> page,
                          @RequestParam("size") Optional<Integer> size,
                          Model model) {

        int curretPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<Categoria> list = this.categoriaService.findAll(
                PageRequest.of(curretPage - 1, pageSize)
        );
        model.addAttribute("categorias", list);

        if (list.getTotalPages() > 0) {
            List<Integer> pageNumbers = IntStream
                    .rangeClosed(1, list.getTotalPages())
                    .boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "categoria/list";
    }

    @GetMapping("form")
    public String form(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "categoria/form";
    }

    @PostMapping
    public ResponseEntity save(@Valid Categoria categoria,
                               BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        categoriaService.save(categoria);
        return ResponseEntity.status(HttpStatus.OK).body(result.getAllErrors());
    }

    @GetMapping("{id}")
    public String findById(@PathVariable Long id, Model model) {
        model.addAttribute("categoria", categoriaService.findOne(id));
        return "categoria/form";
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            categoriaService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("api/find-all")
    @ResponseBody
    public List<Categoria> findCategorias() {
        return categoriaService.findAll();
    }
}
