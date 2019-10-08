var listCompraProduto = [];

$(function () {
    buildCompletes();
    $('#table_produtos').hide();
});

// constrói os completes
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

// adiciona um novo produto na tabela
function addProduto(event) {
    event.preventDefault();
    let idProduto = $('#produto').val().split(" ");
    idProduto = idProduto[0];
    var produto;
    $.get(`produto/${idProduto}`, function (data) {
        if (data != null) {
            produto = data;
            let novoCompraProduto = new CompraProduto(
                1,
                $('#qtde').val(),
                produto.precoCusto,
                produto
            );
            listCompraProduto.push(novoCompraProduto);
            buildLista();
        }
    });
}

// constrói a tabela de produtos
function buildLista() {
    $('tbody>.data').remove();
    if (listCompraProduto.length > 0) {
        listCompraProduto.forEach(compraProduto => {
            $('#dados').append(`
                <tr id="${compraProduto.id}" class="data">
                    <td>${compraProduto.id}</td>
                    <td>${compraProduto.produto.nome}</td>
                    <td>${compraProduto.qtde}</td>
                    <td>R$ ${compraProduto.valor}</td>
                    <td>R$ ${getVlrTotalProduto(compraProduto.valor, compraProduto.qtde)}</td>                
                </tr>
            `);
        });
        $('#table_produtos').show();
    }
}

function getVlrTotalProduto(valorUnit, qtde) {
    return valorUnit * qtde;
}

// função que estou tentando utilizar para salvar..
// queria enviar um json, mas não sei se dá certo, pelo fato do controller ser @Controller ao invés de @RestController
function saveCompra() {
    event.preventDefault();
    let idFornecedor = $('#fornecedor').val().split(" ");
    idFornecedor = idFornecedor[0];
    $.get(`produto/${idFornecedor}`, function (fornecedor) {
        if (fornecedor != null) {
            var compra = new Compra(
                null,
                $('#descricao').val(),
                $('#dataCompra').val(),
                fornecedor,
                listCompraProduto
            );
            console.log();
            $.ajax({
                type: $('#formCompra').attr('method'),
                url: $('#formCompra').attr('action'),
                data: JSON.stringify(compra),
                contentType: "application/json; charset=utf-8",
                success: function (data) {
                    console.log('salvo com sucesso');
                }, error: function (data) {
                    console.log('deu ruim');
                }
            });
        }
    });
}