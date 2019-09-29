$(function () {
    validaFornecedor('/fornecedor', '#formFornecedor');
});


// TODO verificar como fazer autoComplete..
function findEstado() {
    var text = $('#estado').val();
    $.get(`/fornecedor/complete/${text}`, function (data) {
       data.forEach(result => {
           $('#state').append(`
                <option data-value="${result.id}" value="${result.nome}"></option>
           `);
       });
    });
}