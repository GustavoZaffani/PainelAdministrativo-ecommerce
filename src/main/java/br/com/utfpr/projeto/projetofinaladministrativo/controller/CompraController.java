package br.com.utfpr.projeto.projetofinaladministrativo.controller;

import br.com.utfpr.projeto.projetofinaladministrativo.model.Compra;
import br.com.utfpr.projeto.projetofinaladministrativo.model.CompraProduto;
import br.com.utfpr.projeto.projetofinaladministrativo.service.CompraService;
import br.com.utfpr.projeto.projetofinaladministrativo.service.FornecedorService;
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
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("compra")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @Autowired
    private FornecedorService fornecedorService;

    @Autowired
    private ProdutoService produtoService;

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
        compra.getCompraProdutos().stream().forEach(compraProduto -> compraProduto.setCompra(compra));
        compraService.save(compra);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("{id}")
    public String findById(@PathVariable Long id, Model model) {
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

    @GetMapping("teste")
    public String buildCompra() {
        Compra compra = new Compra();
        compra.setDescricao("teste");
        compra.setDataCompra(LocalDate.now());
        compra.setFornecedor(fornecedorService.findOne(1L));

        CompraProduto compraProduto = new CompraProduto();
        compraProduto.setCompra(compra);
        compraProduto.setProduto(produtoService.findOne(1L));
        compraProduto.setQtde(1);
        compraProduto.setValor(new BigDecimal(3));

        compra.setCompraProdutos(Arrays.asList(compraProduto));
        return "compra/list";
    }
}
