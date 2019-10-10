package br.com.utfpr.projeto.projetofinaladministrativo.controller;

import br.com.utfpr.projeto.projetofinaladministrativo.model.Compra;
import br.com.utfpr.projeto.projetofinaladministrativo.service.CompraService;
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
@RequestMapping("compra")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @GetMapping
    public String findAll(@RequestParam("page") Optional<Integer> page,
                          @RequestParam("size") Optional<Integer> size,
                          Model model) {

        int curretPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<Compra> list = this.compraService.findAll(
                PageRequest.of(curretPage - 1, pageSize)
        );
        model.addAttribute("compras", list);
        if (list.getTotalPages() > 0) {
            List<Integer> pageNumbers = IntStream
                    .rangeClosed(1, list.getTotalPages())
                    .boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "compra/list";
    }

    @GetMapping("form")
    public String form(Model model) {
        model.addAttribute("compra", new Compra());
        return "compra/form";
    }

    @PostMapping
    public ResponseEntity save(@RequestBody @Valid Compra compra,
                               BindingResult result) {

        if (result.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        compra.getCompraProdutos().forEach(compraProduto -> compraProduto.setCompra(compra));
        compraService.save(compra);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("getById/{id}")
    @ResponseBody
    public Compra findById(@PathVariable Long id) {
        return compraService.findOne(id);
    }

    @GetMapping("{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("compra", compraService.findOne(id));
        return "compra/form";
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        try {
            compraService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
