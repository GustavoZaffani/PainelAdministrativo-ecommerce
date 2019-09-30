package br.com.utfpr.projeto.projetofinaladministrativo.controller;

import br.com.utfpr.projeto.projetofinaladministrativo.model.Cidade;
import br.com.utfpr.projeto.projetofinaladministrativo.model.Estado;
import br.com.utfpr.projeto.projetofinaladministrativo.service.CidadeService;
import br.com.utfpr.projeto.projetofinaladministrativo.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UtilController {

    @Autowired
    private EstadoService estadoService;

    @Autowired
    private CidadeService cidadeService;

    @GetMapping("fornecedor/estado/complete")
    public List<Estado> complete(@RequestParam("texto") String texto) {
        return estadoService.complete(texto);
    }

    @GetMapping("fornecedor/cidade/complete/{idEstado}")
    public List<Cidade> complete(@PathVariable("idEstado") Long idEstado,
                                 @RequestParam("texto") String texto) {
        return cidadeService.complete(texto, idEstado);
    }
}
