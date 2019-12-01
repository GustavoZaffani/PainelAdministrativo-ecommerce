package br.com.utfpr.projeto.projetofinaladministrativo.repository;

import br.com.utfpr.projeto.projetofinaladministrativo.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByNomeLikeIgnoreCase(String nome);

    List<Produto> findByTipoEquals(String tipo);

    @Query(value = "SELECT * FROM PRODUTO WHERE TIPO LIKE :TIPO AND CATEGORIA_ID = :IDCATEGORIA", nativeQuery = true)
    List<Produto> findByTipoEqualsAndCategoriaIdEquals(@Param("TIPO") String tipo,
                                                       @Param("IDCATEGORIA") Long idCarrinho);

    @Query(value = "SELECT P.NOME," +
            "   SUM(CI.QTDE) TOTAL" +
            " FROM CARRINHO C" +
            " LEFT JOIN CARRINHO_ITEM CI ON CI.CARRINHO_ID = C.ID" +
            " LEFT JOIN PRODUTO P ON P.ID = CI.ID_PRODUTO" +
            " GROUP BY P.NOME",
            nativeQuery = true)
    List<Object[]> findProdutosMaisVendidos();

    @Query(value = "SELECT P.NOME," +
            "   SUM(CP.QTDE) TOTAL" +
            " FROM COMPRA C" +
            " LEFT JOIN COMPRA_PRODUTO CP ON CP.COMPRA_ID = C.ID" +
            " LEFT JOIN PRODUTO P ON P.ID = CP.PRODUTO_ID" +
            " GROUP BY P.NOME",
            nativeQuery = true)
    List<Object[]> findProdutosMaisComprados();
}
