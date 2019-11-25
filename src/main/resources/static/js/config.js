function updateImpl(impl) {
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
