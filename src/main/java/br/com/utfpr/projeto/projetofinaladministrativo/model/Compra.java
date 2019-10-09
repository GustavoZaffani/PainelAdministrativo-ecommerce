package br.com.utfpr.projeto.projetofinaladministrativo.model;

import br.com.utfpr.projeto.projetofinaladministrativo.config.LocalDateDeserializer;
import br.com.utfpr.projeto.projetofinaladministrativo.config.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "compra")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotEmpty(message = "O campo 'Descrição' é de preenchimento obrigatório.")
    @Column(name = "descricao", nullable = false)
    private String descricao;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @NotNull(message = "O campo 'Data de Compra' deve ser selecionado.")
    @Column(name = "data_compra", nullable = false)
    private LocalDate dataCompra;

    @NotNull(message = "O campo 'Fornecedor' deve ser escolhido.")
    @ManyToOne
    @JoinColumn(name = "fornecedor_id", referencedColumnName = "id")
    private Fornecedor fornecedor;

    @NotNull(message = "Deve ser escolhido ao menos 1 produto.")
    @OneToMany(mappedBy = "compra",
            cascade = {CascadeType.ALL, CascadeType.REMOVE})
    private List<CompraProduto> compraProdutos;
}
