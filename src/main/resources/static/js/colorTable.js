function intToHex(i) {
    var hex = parseInt(i).toString(16);
    return (hex.length < 2) ? "0" + hex : hex;
}


function makeColor(value) {
    // value must be between [0, 510]
    value = Math.min(Math.max(0,value), 1) * 510;

    var redValue;
    var greenValue;
    if (value < 255) {
        redValue = 255;
        greenValue = Math.sqrt(value) * 16;
        greenValue = Math.round(greenValue);
    } else {
        greenValue = 255;
        value = value - 255;
        redValue = 256 - (value * value / 255)
        redValue = Math.round(redValue);
    }

    return "#" + intToHex(redValue) + intToHex(greenValue) + "00";
}

function checkDate(){
    let expected_date = document.getElementsByClassName("color_date");
    if (expected_date !== "2022-12-17"){
        expected_date.style.color = makeColor(100);
    }

}