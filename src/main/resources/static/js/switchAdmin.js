function filterBanned() {
    var table = document.getElementById('info-table');
    var flag = false;
    /*var checkBox = document.getElementById("filter-banned");*/
    var checkBox = document.getElementById("filter-banned");
    for (var i = 1; i < table.rows.length; i++) {
        checkBox = document.getElementById("filter-banned");
        if (!checkBox.checked){
            flag = true;
        }
        console.log(table.rows[i].cells[3]);
        if (flag) {
            if (table.rows[i].cells[3].innerHTML === "Активен")
                table.rows[i].style.display = "";
            else
                table.rows[i].style.display = "none";
        } else {
            if (table.rows[i].cells[3].innerHTML === "Заблокирован")
                table.rows[i].style.display = "";
            else
                table.rows[i].style.display = "none";
        }
    }
}