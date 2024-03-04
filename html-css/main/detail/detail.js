

function zzim(){
    alert("zzim버튼 확인?")
}

function like(){
    alert("like버튼 확인?")
}

function unlike(){
    alert("like버튼 확인?")
}

function deleteimg(){
    alert("delete버튼 확인?")
}

    document.getElementById("mySelect").addEventListener("change", function () {
        var selectedOption = this.options[this.selectedIndex];
        window.location.href = selectedOption.value;
    });
