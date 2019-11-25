var vendas;

$(function () {
    findVendasCarrinho();
});

function findVendasCarrinho() {
    $('tbody#pedidoVenda').remove();
    $.get('http://localhost:18025/carrinho/find-all', function (carrinhos) {
        if (carrinhos != null) {
            vendas = carrinhos;
            carrinhos.forEach(carrinho => {
                $('#vendas').append(`
                    <tr id="pedidoVenda">
                        <td class="text-center">${carrinho.id}</td>
                        <td class="text-center">${carrinho.dtVenda}</td>
                        <td class="text-center">${carrinho.cliente.nome}</td>
                        <td class="text-center">${formataMoeda(calculaVlrTotalVenda(carrinho))}</td>
                        <td class="text-center">${verificaSituacao(carrinho.situacao)}</td>
                        <td class="text-center">
                            <a onclick="openFormVenda(${carrinho.id})"
                                class="btn btn-primary btn-delete" title="Info">
                                <i class="fas fa-info ml-2"></i>
                            </a>
                        </td>
                    </tr>                
                `);
            });
        }
    })
}

function calculaVlrTotalVenda(carrinho) {
    var vlrTotal = 0;
    carrinho.carrinhoItemList.forEach(carrinhoItem => {
        vlrTotal += (carrinhoItem.valor * carrinhoItem.qtde);
    });
    vlrTotal += carrinho.taxaFrete;
    return vlrTotal;
}

function verificaSituacao(situacao) {
    if (situacao == 'AA') {
        return "Aguardando aprovação";
    } else if (situacao == 'PA') {
        return "Pagamento aprovado";
    } else if (situacao == 'ES') {
        return "Em separação";
    } else if (situacao == 'ET') {
        return "Enviado à transportadora";
    } else if (situacao == 'SE') {
        return "Saiu para Entrega";
    } else if (situacao == 'PE') {
        return "Pedido entrege";
    }
}

function openFormVenda(id) {
    window.location = `venda/form/${id}`;
}