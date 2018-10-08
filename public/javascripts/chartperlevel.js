var data = document.getElementById("script-perlevel").getAttribute("data-values").split(",");
var labels = document.getElementById("script-perlevel").getAttribute("data-labels").split(",");

var config = {
    type: 'line',
    data: {
        datasets: [{
            data: data,
            fill: false,
            backgroundColor: 'rgb(242,64,200)',
            borderColor: 'rgb(242,64,200)',
            label: 'Artifacts per level'
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
    var ctx = document.getElementById('chart-perlevel').getContext('2d');
    new Chart(ctx, config);
//};