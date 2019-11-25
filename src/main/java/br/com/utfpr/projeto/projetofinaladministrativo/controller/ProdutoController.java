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
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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

    // método utilizado para buscar os produtos que serão vendidos
    @ResponseBody
    @GetMapping("api/produtos/tipo")
    public List<Produto> findProdutoVenda(@RequestParam("tipo") String tipo) {
        return produtoService.findByTipoEquals(tipo);
    }

    @ResponseBody
    @GetMapping("api/produto")
    public Produto findProdutoById(@RequestParam("id") Long id) {
        return produtoService.findOne(id);
    }

    @GetMapping("form")
    public String form(Model model) {
        loadCombo(model);
        model.addAttribute("produto", new Produto());
        return "produto/form";
    }

    @PostMapping
    public ResponseEntity save(@Valid Produto produto,
                               @RequestParam("anexo") MultipartFile capa,
                               @RequestParam("anexos") MultipartFile[] imagens,
                               BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        if (capa != null && !capa.getOriginalFilename().isEmpty()) {
            saveFile(produto, capa);
        }
        if (imagens != null
                && imagens.length > 0
                && !imagens[0].getOriginalFilename().isEmpty()) {
            saveFiles(produto, imagens);
        }
        produtoService.save(produto);
        return new ResponseEntity(HttpStatus.OK);
    }

    private void saveFile(Produto produto, MultipartFile anexo) {
//        File dir = new File("C:/produto/");
        File dir = new File("D:/projetoFinalJavaWebIParte2/src/main/resources/static/img/capas/");
        if (!dir.exists()) {
            dir.mkdirs();
        }
//        String caminhoAnexo = "C:/produto/";
        String caminhoAnexo = "D:/projetoFinalJavaWebIParte2/src/main/resources/static/img/capas/";
        String extensao = anexo.getOriginalFilename().substring(
                anexo.getOriginalFilename().lastIndexOf(".")
        );

        String nomeArquivo = "capa" + produto.getId() + extensao;
        produto.setImgCapa(nomeArquivo);

        try {
            FileOutputStream fileOut = new FileOutputStream(
                    new File(caminhoAnexo + nomeArquivo)
            );
            BufferedOutputStream stream = new BufferedOutputStream(fileOut);
            stream.write(anexo.getBytes());
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveFiles(Produto produto,
                           MultipartFile[] anexos) {
        File dir = new File("C:/produto/");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String caminhoAnexo = "C:/produto/";

        int i = 0;
        for (MultipartFile anexo : anexos) {
            i++;
            String extensao = anexo.getOriginalFilename().substring(
                    anexo.getOriginalFilename().lastIndexOf(".")
            );

            String nomeArquivo = produto.getId() + "_" + i + extensao;

            try {
                FileOutputStream fileOut = new FileOutputStream(
                        new File(caminhoAnexo + nomeArquivo)
                );
                BufferedOutputStream stream = new BufferedOutputStream(fileOut);
                stream.write(anexo.getBytes());
                stream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
