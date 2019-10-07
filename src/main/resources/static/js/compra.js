$(function () {
    buildCompletes();
    $('#dtDynamicVerticalScrollExample').DataTable({
        "scrollY": "50vh",
        "scrollCollapse": true,
        "language": {
            "url": "//cdn.datatables.net/plug-ins/1.10.13/i18n/Portuguese-Brasil.json"
        }
    });
    $('.dataTables_length').addClass('bs-select');
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