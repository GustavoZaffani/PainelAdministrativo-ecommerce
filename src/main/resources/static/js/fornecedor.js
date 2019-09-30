$(function () {
    validaFornecedor('/fornecedor', '#formFornecedor');

    $("#estado" ).autocomplete({
        source: function (request, response) {
            $.ajax({
                url: 'estado/complete',
                type: 'GET',
                dataType: 'json',
                data: {
                    'texto': request.term
                }
            , success: function (data) {
                response($.map(data, function (item) {
                    return {
                        label: item.nome,
                        value: item.id,
                    }
                }));
            }});
        }
    });

    $("#cidade" ).autocomplete({
        source: function (request, response) {
            var idEstado = $('#estado').val();
            $.ajax({
                url: `cidade/complete/${idEstado}`,
                type: 'GET',
                dataType: 'json',
                data: {
                    'texto': request.term
                }
                , success: function (data) {
                    response($.map(data, function (item) {
                        return {
                            label: item.nome,
                            value: item.id
                        }
                    }));
                }});
        }
    });
});

function saveForn(urlDestino, form) {

    $.ajax({
        type: $(form).attr('method'),
        url: $(form).attr('action'),
        data: $(form).serialize(),
        success: function () {
            swal({
                title: 'Salvo!',
                text: 'Registro salvo com sucesso!',
                type: 'success'
            }, function () {
                window.location = urlDestino;
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
