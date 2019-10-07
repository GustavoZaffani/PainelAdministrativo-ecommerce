$(function () {
    validaFornecedor('/fornecedor', '#formFornecedor');
    buildCompletes();
    habilitaCidade();
    findDadosOnEdit();
});

function findDadosOnEdit() {
    let cidade = $('#cidade').val();
    let estado = $('#estado').val();
    if (cidade != null && cidade !== "" &&
        estado != null && estado !== "") {
        $.get(`estado/${$('#estado').val()}`, function (data) {
            if (data != null) {
                $('#estado').val(data.id + " - " + data.nome);
            }
        });
        $.get(`cidade/${$('#cidade').val()}`, function (data) {
            if (data != null) {
                $('#cidade').val(data.id + " - " + data.nome);
            }
        });
    }
}

function habilitaCidade() {
    let estado = $('#estado').val();
    if (estado != null && estado !== "") {
        $('#cidade').attr('disabled', false);
    } else {
        $('#cidade').attr('disabled', true);
    }
}

function buildCompletes() {
    $("#estado").autocomplete({
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
                            value: item.id + " - " + item.nome,
                        }
                    }));
                }
            });
        }, select(event, ui) {
            habilitaCidade();
        }
    });

    $("#cidade").autocomplete({
        source: function (request, response) {
            var idEstado = $('#estado').val().split(" ");
            idEstado = idEstado[0];
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
                            value: item.id + " - " + item.nome
                        }
                    }));
                }
            });
        }
    });
}

function saveForn(urlDestino, form) {

    let estado = $('#estado').val().split(" ");
    $('#estado').val(estado[0]);
    let cidade = $('#cidade').val().split(" ");
    $('#cidade').val(cidade[0]);

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
