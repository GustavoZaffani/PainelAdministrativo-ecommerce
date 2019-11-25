package br.com.utfpr.projeto.projetofinaladministrativo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("venda")
public class VendaController {

    @GetMapping
    public String goToVendas(){
        return "venda/list";
    }

    @GetMapping("form/{id}")
    public String goToFormVenda(@PathVariable("id") Long id) {
        return "venda/form";
    }

}
