package br.com.utfpr.projeto.projetofinaladministrativo.controller;

import br.com.utfpr.projeto.projetofinaladministrativo.model.*;
import br.com.utfpr.projeto.projetofinaladministrativo.service.CidadeService;
import br.com.utfpr.projeto.projetofinaladministrativo.service.EstadoService;
import br.com.utfpr.projeto.projetofinaladministrativo.service.FornecedorService;
import br.com.utfpr.projeto.projetofinaladministrativo.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Utilizei esse UtilController pois não sabia que dava para fazer o mesmo no @Controller
// portanto, não quis alterar, pois já estava funcionando.. hehe
@RestController
public class UtilController {

    @Autowired
    private EstadoService estadoService;
    @Autowired
    private CidadeService cidadeService;
    @Autowired
    private FornecedorService fornecedorService;
    @Autowired
    private ProdutoService produtoService;

    @GetMapping("fornecedor/estado/complete")
    public List<Estado> completeEstado(@RequestParam("texto") String texto) {
        return estadoService.complete(texto);
    }

    @GetMapping("fornecedor/cidade/complete/{idEstado}")
    public List<Cidade> completeCidade(@PathVariable("idEstado") Long idEstado,
                                       @RequestParam("texto") String texto) {
        return cidadeService.complete(texto, idEstado);
    }

    @GetMapping("fornecedor/estado/{id}")
    public Estado findEstadoById(@PathVariable("id") Long idEstado) {
        return estadoService.findOne(idEstado);
    }

    @GetMapping("fornecedor/cidade/{id}")
    public Cidade findCidadeById(@PathVariable("id") Long idCidade) {
        return cidadeService.findOne(idCidade);
    }

    @GetMapping("compra/fornecedor/complete")
    public List<Fornecedor> completeFornecedor(@RequestParam("texto") String fornecedor) {
        return fornecedorService.complete(fornecedor);
    }

    @GetMapping("compra/produto/complete")
    public List<Produto> completeProduto(@RequestParam("texto") String nomeProduto) {
        return produtoService.complete(nomeProduto);
    }

    @GetMapping("compra/produto/{id}")
    public Produto findProdutoById(@PathVariable("id") Long id) {
        return produtoService.findOne(id);
    }

    @GetMapping("compra/fornecedor/{id}")
    public Fornecedor findFornecedorById(@PathVariable("id") Long id) {
        return fornecedorService.findOne(id);
    }
}
