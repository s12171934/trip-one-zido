// 네이버 로그인 설정
var naverLogin = new naver.LoginWithNaverId({
    clientId: '네이버에서 발급받은 클라이언트 ID',
    callbackUrl: 'http://YOUR_DOMAIN/auth/naver/callback',
    isPopup: false,
    loginButton: {color: 'green', type: 3, height: 60},
    callbackHandle: true
});
// 네이버 로그인 버튼 생성
naverLogin.init();


// 카카오 로그인 설정
Kakao.init('발급받은 클라이언트 ID');

// 카카오톡 아이디로 로그인 버튼 이벤트 처리
document.getElementById('kakao-login-btn').addEventListener('click', function() {
    Kakao.Auth.login({
        success: function(authObj) {
            // 로그인 성공 시 처리할 작업을 여기에 작성
            console.log(authObj);
            // 사용자 정보 받아오기
            Kakao.API.request({
                url: '/v2/user/me',
                success: function(response) {
                    // 사용자 정보 출력
                    console.log(response);
                    // 카카오톡 아이디 출력
                    console.log("카카오톡 아이디:", response.kakao_account.profile.nickname);
                },
                fail: function(error) {
                    console.error(error);
                }
            });
        },
        fail: function(err) {
            // 로그인 실패 시 처리할 작업을 여기에 작성
            console.error(err);
        }
    });
});