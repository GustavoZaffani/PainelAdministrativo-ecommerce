var idVenda;
var vendaDetail;

$(function () {
    if (!isNaN(Number(getIdUrl()))) {
        idVenda = getIdUrl();
        buildFormulario();
    }
});

function buildFormulario() {
    $.get(`http://localhost:18025/carrinho/${idVenda}`, function (carrinho) {
        vendaDetail = carrinho;
        $('#id').val(carrinho.id);
        $('#cliente').val(carrinho.cliente.nome);
        $('#dataVenda').val(carrinho.dtVenda);
        $('#taxaFrete').val(carrinho.taxaFrete);
        $('#situacao').val(carrinho.situacao);
        $('#rua').val(carrinho.enderecoEntrega.endereco);
        $('#bairro').val(carrinho.enderecoEntrega.bairro);
        $('#nro').val(carrinho.enderecoEntrega.nro);
        $('#cep').val(carrinho.enderecoEntrega.cep);
        $('#estado').val(carrinho.enderecoEntrega.estado.nome);
        $('#cidade').val(carrinho.enderecoEntrega.cidade.nome);
        buildItens();
    });
}

function buildItens() {
    $('tbody#itemDetail').remove();
    vendaDetail.carrinhoItemList.forEach(item => {
       $('#dados').append(`
            <tr id="itemDetail">
                <td class="text-center">${item.produto.id}</td>
                <td class="text-center">${item.produto.nome}</td>
                <td class="text-center">${item.qtde}</td>
                <td class="text-center">${formataMoeda(item.valor)}</td>
                <td class="text-center">${formataMoeda(item.valor * item.qtde)}</td>
            </tr>                
       `);
    });
    $('#total').text("Valor Total: R$ " + formataMoeda(calculaVlrTotalVenda(vendaDetail)));
}

function updateSituacao() {
    var situacao = $('#situacao').val();
    var idCarrinho = vendaDetail.id;
    $.ajax({
        type: 'GET',
        url: `http://localhost:18025/carrinho/update-situacao/${idCarrinho}/${situacao}`,
        contentType: "application/json; charset=utf-8",
        success: function () {
            swal({
                title: 'Salvo!',
                text: 'Situação atualizada com sucesso!',
                type: 'success'
            }, function () {
                window.location = '/venda';
            });
        }, error: function (data) {
            console.log(data);
            swal(
                'Atenção!',
                'Ocorreu um erro ao finalizar o registro. Por favor, tente novamente!',
                'error'
            );
        }
    });
}