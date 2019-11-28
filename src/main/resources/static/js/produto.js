$(function () {
    validaProduto('/produto', '#formProduto');
});

$('#formProduto').submit(function (e) {

        e.preventDefault();
        var formData = new FormData($('#formProduto')[0]);

        $.ajax({
            type: $('#formProduto').attr('method'),
            url: $('#formProduto').attr('action'),
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
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
});