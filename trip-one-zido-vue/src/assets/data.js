export default {
  userProfiles: {
    2: {
      id: 2,
      imgSrc: "/images/유재석.png",
      loginId: "유재석",
      isFollow: true,
    },
    3: {
      id: 3,
      imgSrc: "/images/조세호.png",
      loginId: "조세호",
      isFollow: false,
    },
  },
  communityList : [
    {
      content_id: 2,
      title: "부산여행 가실분?",
      login_id: "유재석",
      deadLine: "24/02/14",
      viewCount: 5,
      withCount: 3,
      total: 4,
      status: "모집중",
    },
    {
      content_id: 3,
      title: "서울이 처음인데 남산가실분 ~~",
      login_id: "조세호",
      deadLine: "24/02/13",
      viewCount: 5,
      withCount: 5,
      total: 5,
      status: "마감",
    },
    {
      content_id: 4,
      title: "구로디지털단지 맛집 도장깨기!",
      login_id: "윤지수",
      deadLine: "24/02/10",
      viewCount: 2,
      withCount: 2,
      total: 2,
      status: "마감",
    }
  ],

  communityDetail : [
    {
      content_id: 2,
      status: "모집중",
      title: "부산여행 가실분?",
      login_id: "유재석",
      local: "부산광역시",
      withCount: 3,
      total: 4,
      with1: "유재석",
      with2: "문성기",
      with3: "한희주",
      start_date: "2024/02/07",
      end_date: "2024/02/14",
      deadLine: "2024/02/14",
      created_at: "2024/02/05",
      viewCount: 5,
      content: "부산여행 갑시다!",
    },
    {
      content_id: 3,
      status: "마감",
      title: "서울이 처음인데 남산가실분 ~~",
      login_id: "조세호",
      local: "서울특별시",
      withCount: 4,
      total: 5,
      with1: "조세호",
      with2: "문성기",
      with3: "한희주",
      with: "송준형",
      start_date: "2024/02/05",
      end_date: "2024/02/17",
      deadLine: "2024/02/04",
      created_at: "2024/02/03",
      viewCount: 6,
      content: "서울에 맛집을 잘 아시는분 계신가요~?",
    },
    {
      content_id: 4,
      status: "마감",
      title: "구로디지털단지 맛집 도장깨기!",
      login_id: "윤지수",
      local: "서울특별시",
      withCount: 2,
      total: 2,
      with1: "윤지수",
      with2: "문성기",
      start_date: "2024/02/05",
      end_date: "2024/02/17",
      deadLine: "2024/02/04",
      created_at: "2024/02/01",
      viewCount: 8,
      content: "구로디지털단지에서 간단하게 마녀김밥 어때요?",
    }
  ],
  selectLocations: [
    "서울특별시",
    "인천광역시",
    "대구광역시",
    "울산광역시",
    "대전광역시",
    "광주광역시",
    "부산광역시",
    "경기도",
    "강원도",
    "충청북도",
    "충청남도",
    "경상북도",
    "경상남도",
    "전라북도",
    "전라남도",
    "제주특별자치도",
  ],
  selectCategories: ["음식점", "관광지"],
  sortMenu: ["조회순", "최신순", "가나다순", "사용자 정의순"],
  tourCount: 20,
  spotPlanCount: 14,
  tourList: [
    {
      id: 1,
      imgSrc: "/images/busan2.jpg",
      title: "더미",
      bookmarkCount: 3000,
      myBookmark: true,
      type: "tour",
    },
  ],
  spotPlanList: [
    {
      id: 1,
      imgSrc: "/images/busan2.jpg",
      title: "더미",
      bookmarkCount: 3000,
      grade: 10,
      like: 300,
      myBookmark: true,
      type: "plan",
    },
    {
      id: 1,
      imgSrc: "/images/busan2.jpg",
      title: "더미",
      bookmarkCount: 3000,
      grade: 10,
      like: 300,
      myBookmark: true,
      type: "spot",
    },
  ],
  modalDatas: {
    "": {
      url: "",
      message: "",
      buttonMessage: "",
    },
    findLoginIdSuccess: {
      url: "/login",
      message: "당신의 아이디는<br />테스트<br />입니다.",
      buttonMessage: "로그인 하러 가기",
    },
    findLoginIdFail: {
      url: "/find",
      message: "아이디 찾기에<br />실패했습니다.",
      buttonMessage: "재시도",
    },
    findPasswordFail: {
      url: "/find",
      message: "비밀번호 찾기에<br />실패했습니다.",
      buttonMessage: "재시도",
    },
    loginFail: {
      url: "/login",
      message: "아이디와 비밀번호를<br />확인해주세요.",
      buttonMessage: "다시 로그인 하기",
    },
    updateMemberInfoFail: {
      url: "/config",
      message: "회원정보 수정이<br />완료되었습니다.",
      buttonMessage: "확인",
    },
    resetPasswordSuccess: {
      url: "/config",
          message: "비밀번호가<br />변경되었습니다.",
          buttonMessage: "확인",
    },
    resetPasswordFail: {
      url: "/reset-pw",
          message: "비밀번호와<br />비밀번호 확인이<br />다릅니다.",
          buttonMessage: "비밀번호 재설정 하기",
    },
    checkDuplicationLoginIdSuccess: {
        url: "/sign-up",
        message: "사용가능한 ID입니다.",
        buttonMessage: "확인",
    },
    checkDuplicationLoginIdFail: {
      url: "/sign-up",
          message: "중복된 ID입니다.",
          buttonMessage: "확인",
    },
    signUpSuccess: {
      url: "/login",
      message: "회원가입에<br />성공했습니다.",
      buttonMessage: "확인",
    },
    signUpFail: {
      url: "/login",
      message: "회원가입에<br />성공했습니다.",
      buttonMessage: "확인",
    },
    securityFail: {
      url: "/find",
        message: "비밀번호 찾기에<br />실패했습니다.",
        buttonMessage: "재시도",
    }
  },
  selectCategories: [
    "음식점",
    "관광지",
  ],
  locMap: [
    {
      code: 11,
      color: "#111111"
    },
    {
      code: 21,
      color: "#222222"
    },
    {
      code: 22,
      color: "#333333"
    },
    {
      code: 23,
      color: "#444444"
    },
    {
      code: 24,
      color: "#555555"
    },
    {
      code: 25,
      color: "#666666"
    },
    {
      code: 26,
      color: "#777777"
    },
    {
      code: 29,
      color: "#888888"
    },
    {
      code: 31,
      color: "#999999"
    },
    {
      code: 32,
      color: "#aaaaaa"
    },
    {
      code: 33,
      color: "#bbbbbb"
    },
    {
      code: 34,
      color: "#cccccc"
    },
    {
      code: 35,
      color: "#dddddd"
    },
    {
      code: 36,
      color: "#eeeeee"
    },
    {
      code: 37,
      color: "#ffffff"
    },
    {
      code: 38,
      color: "#000000"
    },
    {
      code: 39,
      color: "#000000"
    },
  ]
};
