function getIdUrl() {
    var id;
    id = window.location.pathname.slice(1);
    id = id.split("/");
    return id[1];
}

function formataMoeda(valor) {
    let valorReturn = Number(valor);
    valorReturn = valorReturn.toFixed(2);
    valorReturn = valorReturn.replace('.', ',');
    return valorReturn;
}

function save(urlDestino, form) {
    console.log($(form).serialize());
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

function remove(id, url) {
    swal({
            title: "Deseja realmente remover o registro?!",
            text: "Esta ação não poderá ser desfeita!",
            showCancelButton: true,
            confirmButtonColor: "DD6B55",
            cancelButtonText: "Cancelar",
            confirmButtonText: "Remover",
            closeOnConfirm: false
        }, function () {
            var destino = url + '/' + id;
            $.ajax({
                type: 'DELETE',
                url: destino,
                success: function () {
                    swal({
                        title: 'Removido!',
                        text: 'Registro removido com sucesso!',
                        type: 'success'
                    }, function () {
                        $('#row_' + id).remove();
                    });
                },
                error: function () {
                    swal('Erro!',
                        'Falha ao remover registro!',
                        'error');
                }
            });
        }
    );
}


function back() {
    window.history.back();
}

function formataMoeda(valor) {
    let valorReturn = Number(valor);
    valorReturn = valorReturn.toFixed(2);
    valorReturn = valorReturn.replace('.', ',');
    return valorReturn;
}