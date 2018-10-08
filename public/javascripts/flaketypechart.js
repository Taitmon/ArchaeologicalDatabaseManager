var data = document.getElementById("script-flaketype").getAttribute("data-values").split(",");
var labels = document.getElementById("script-flaketype").getAttribute("data-labels").split(",");

var config = {
    type: 'horizontalBar',
    data: {
        datasets: [{
            data: data,

            backgroundColor:
               'rgb(23,239,116)',
            label: 'Number of Flakes'
        }],
        labels: labels
    },
    options: {
        legend: {
                position: 'right',
        },
        responsive: true

    }
};

//window.onload = function() {
    var ctx = document.getElementById('chart-flaketype').getContext('2d');
    new Chart(ctx, config);
//};