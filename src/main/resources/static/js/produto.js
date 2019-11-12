$(function () {
    validaProduto('/produto', '#formProduto');
});

function saveProduto(e) {
    e.preventDefault();

    var formData = new FormData($('#formProduto')[0]);

    $.ajax({
        type: $('#formProduto').attr('method'),
        url: $('#formProduto').attr('action'),
        data: formData,
        success: function () {
            swal({
                title: 'Salvo!',
                text: 'Registro salvo com sucesso!',
                type: 'success'
            }, function () {
                window.location = '/';
            });
        },
        error: function (data) {
            console.log(data);
            swal(
                'Atenção!',
                'Ocorreu um erro ao salvar o registro. Por favor, tente novamente!',
                'error'
            );
        }
    });
    return false;
}
