function updateModels(brand) {
    $.getJSON(`/devices/brands/${brand}/models`, function (result) {
        console.log(result);

        const arrOptions = [];
        $(result).each(function (key, value) {
            arrOptions.push("<option value='" + value + "'>" + value + "</option>");
        });
        console.log(arrOptions);
        document.getElementById("model-selector").innerHTML = arrOptions.join();

        if(result[0] !== undefined) {
            updateChart(result[0])
        }
    });
}

function updateChart(model) {
    var brandSelector = document.getElementById("brand-selector");
    var brand = brandSelector.options[brandSelector.selectedIndex].text;

    $.getJSON(`/devices/brands/${brand}/models/${model}/firmware/distribution`, function (result) {
        var resultKeys = [];
        var resultValues = [];
        for(var key in result){
            resultKeys.push(key);
            resultValues.push(result[key]);
        }
        var ctx = document.getElementById('firmwareDistributionChart');
        var firmwareDistributionChart = new Chart(ctx, {
            type: 'doughnut',
            data: {
                datasets: [{
                    data: resultValues,
                    backgroundColor: poolColors(resultKeys.length),
                }],
                labels: resultKeys,
            }
        });
    });
}

function getRandomColor() {
    var letters = '0123456789ABCDEF'.split('');
    var color = '#';
    for (var i = 0; i < 6; i++ ) {
        color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
}

function poolColors(length) {
    var defaultColor = ['#00FFFF','#FF00FF','#FFFF00','#F0F0F0','#0F0F0F','#FF0000', '#00FF00','#0000FF'];
    var pool = [];
    for(i = 0; i < length; i++) {
        if(i<defaultColor.length) {
            pool.push(defaultColor[i])
        } else {
            pool.push(getRandomColor());
        }
    }
    return pool;
}