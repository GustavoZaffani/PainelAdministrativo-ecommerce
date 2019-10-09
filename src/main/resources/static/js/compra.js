var listCompraProduto = [];
var compra;
var hasProdutoTable = false;

$(function () {
    validaCompra('/compra', '#formCompra');
    buildCompletes();
    $('#table_produtos').hide();
});

function buildCompletes() {
    $("#fornecedor").autocomplete({
        source: function (request, response) {
            $.ajax({
                url: 'fornecedor/complete',
                type: 'GET',
                dataType: 'json',
                data: {
                    'texto': request.term
                }
                , success: function (data) {
                    response($.map(data, function (item) {
                        return {
                            label: item.nomeFantasia,
                            value: item.id + " - " + item.nomeFantasia,
                        }
                    }));
                }
            });
        }
    });

    $("#produto").autocomplete({
        source: function (request, response) {
            $.ajax({
                url: 'produto/complete',
                type: 'GET',
                dataType: 'json',
                data: {
                    'texto': request.term
                }
                , success: function (data) {
                    response($.map(data, function (item) {
                        return {
                            label: item.nome,
                            value: item.id + " - " + item.nome,
                        }
                    }));
                }
            });
        }
    });
}

function addProduto(event) {
    event.preventDefault();
    let idProduto = $('#produto').val().split(" ");
    idProduto = idProduto[0];

    if (hasProduto(idProduto)) {
        listCompraProduto.forEach(value => {
            if (value.produto.id == idProduto) {
                value.qtde += Number($('#qtde').val());
            }
        });
        buildLista();
    } else {
        var produto;
        $.get(`produto/${idProduto}`, function (data) {
            if (data != null) {
                produto = data;
                let novoCompraProduto = new CompraProduto(
                    null,
                    Number($('#qtde').val()),
                    produto.precoCusto,
                    produto
                );
                listCompraProduto.push(novoCompraProduto);
                buildLista();
            }
        });
    }
}

function hasProduto(idProduto) {
    listCompraProduto.forEach(value => {
        if (value.produto.id == idProduto) {
            hasProdutoTable = true;
        } else {
            hasProdutoTable = false;
        }
    });
    return hasProdutoTable;
}

function buildLista() {
    $('tbody>.data').remove();
    if (listCompraProduto.length > 0) {
        listCompraProduto.forEach(compraProduto => {
            $('#dados').append(`
                <tr class="data">
                    <td>${compraProduto.produto.nome}</td>
                    <td>${compraProduto.qtde}</td>
                    <td>R$ ${compraProduto.valor}</td>
                    <td>R$ ${getVlrTotalProduto(compraProduto.valor, compraProduto.qtde)}</td>
                    <td>
                        <a onclick="removeItem(${compraProduto.produto.id})"
                            class="btn btn-danger btn-delete" title="Remover">
                            <i class="fas fa-trash ml-2"></i>
                        </a>
                    </td>                
                </tr>
            `);
        });
        $('#table_produtos').show();
    } else {
        $('#table_produtos').hide();
    }
}

function removeItem(idProduto) {
    listCompraProduto.forEach((value, index) => {
        if (value.produto.id == idProduto) {
            listCompraProduto.splice(listCompraProduto.indexOf(index), 1);
        }
    });
    buildLista();
}

function getVlrTotalProduto(valorUnit, qtde) {
    return valorUnit * qtde;
}

function saveCompra(urlDestino, form) {
    let idFornecedor = $('#fornecedor').val().split(" ");
    idFornecedor = idFornecedor[0];
    $.get(`fornecedor/${idFornecedor}`, function (fornecedor) {
        if (fornecedor != null) {
            console.log(fornecedor);
            compra = new Compra(
                null,
                $('#descricao').val(),
                $('#dataCompra').val(),
                fornecedor,
                null
            );
            compra.compraProdutos = bindingCompraProduto();
            clearRepet();
            $.ajax({
                type: $(form).attr('method'),
                url: $(form).attr('action'),
                data: JSON.stringify(compra),
                contentType: "application/json; charset=utf-8",
                success: function (data) {
                    swal({
                        title: 'Salvo!',
                        text: 'Registro salvo com sucesso!',
                        type: 'success'
                    }, function () {
                        window.location = urlDestino;
                    });
                }, error: function (data) {
                    console.log(data);
                    swal(
                        'Atenção!',
                        'Ocorreu um erro ao salvar o registro. Por favor, tente novamente!',
                        'error'
                    );
                }
            });
        }
    });
}

function bindingCompraProduto() {
    listCompraProduto.forEach(list => {
        list.compra = compra;
    });
    return listCompraProduto;
}

function clearRepet() {
    compra.compraProdutos.forEach(compraProduto => {
        compraProduto.compra = null;
    })
}