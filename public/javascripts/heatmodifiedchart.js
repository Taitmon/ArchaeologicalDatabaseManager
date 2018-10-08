var data = document.getElementById("script-ASN3").getAttribute("data-values").split(",");
var labels = document.getElementById("script-ASN3").getAttribute("data-labels").split(",");
var colors = document.getElementById("script-ASN3").getAttribute("data-colors").split("|");

var config = {
    type: 'pie',
    data: {
        datasets: [{
            data: data,

            backgroundColor: colors,
            label: 'Dataset 1'
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
    var ctx = document.getElementById('chart-ASN3').getContext('2d');
    new Chart(ctx, config);
//};