// <!-- Daum 우편번호 서비스 API 스크립트 -->

function searchAddress() {
    new daum.Postcode({
        oncomplete: function(data) {
            document.getElementById('zipcode').value = data.zonecode; // 우편번호 입력란에 우편번호 넣기
            document.getElementById('address').value = data.address; // 주소 입력란에 주소 넣기
        }
    }).open();
}