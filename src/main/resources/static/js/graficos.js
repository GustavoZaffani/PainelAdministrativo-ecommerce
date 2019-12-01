$(function () {
    buildGraficoProdMaisVendidos();
    buildGraficoProdMaisComprados();
});

function buildGraficoProdMaisVendidos() {
    google.charts.load('current', {
        'packages': ['corechart']
    });
    google.charts.setOnLoadCallback(drawChart);

    function drawChart() {

        $.get('http://localhost:8025/grafico/produto/ranking', function (list) {
            var data = google.visualization.arrayToDataTable(list);
            var options = {
                title: 'Ranking dos Produtos mais Vendidos'
            };

            var chart = new google.visualization.PieChart(document
                .getElementById('piechart'));

            chart.draw(data, options);
        });

    }
}

function buildGraficoProdMaisComprados() {
    google.charts.load('current', {
        'packages': ['bar']
    });
    google.charts.setOnLoadCallback(drawChart);

    function drawChart() {

        $.get('http://localhost:8025/grafico/produto/comprados/ranking', function (list) {
            var data = google.visualization.arrayToDataTable(list);
            var options = {
                title: 'Ranking dos Produtos mais Comprados'
            };

            var chart = new google.charts.Bar(document.getElementById('bar'));
            chart.draw(data, google.charts.Bar.convertOptions(options));
        });

    }
}
