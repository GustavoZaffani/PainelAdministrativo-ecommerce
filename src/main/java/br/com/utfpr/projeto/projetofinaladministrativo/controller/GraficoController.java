package br.com.utfpr.projeto.projetofinaladministrativo.controller;

import br.com.utfpr.projeto.projetofinaladministrativo.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("grafico")
public class GraficoController {

    @Autowired
    private ProdutoService produtoService;


    @GetMapping
    public String goToGrafico() {
        return "grafico/graficos";
    }

    @GetMapping("produto/ranking")
    @ResponseBody
    public List<Object[]> graficoProdutoMaisVendidos() {
        List<Object[]> list = produtoService.findProdutosMaisVendidos();
        Object[] header = {"Produto", "Total"};
        list.add(0, header);
        return list;
    }

    @GetMapping("produto/comprados/ranking")
    @ResponseBody
    public List<Object[]> graficoProdutoMaisComprados() {
        List<Object[]> list = produtoService.findProdutosMaisComprados();
        Object[] header = {"Produto", "Total"};
        list.add(0, header);
        return list;
    }
}
