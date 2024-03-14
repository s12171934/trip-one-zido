import zido from "@/assets/zido";

export default {
  userInfo: {
    loginId: "test",
    name: "test",
    password: "test",
    passwordCheck: "test",
    securityQuestion: 3,
    securityAnswer: "test",
    email: "test",
    phoneNumber: "test",
    zipcode: 822,
    address: "test",
    address2: "test",
    birth: "2023-11-12",
    gender: 0,
  },
  securityQuestions: [
    {
      id: 1,
      question: "당신이 태어난 곳은?",
    },
    {
      id: 2,
      question: "졸업한 초등학교명은?",
    },
    {
      id: 3,
      question: "당신의 별명은?",
    },
    {
      id: 4,
      question: "처음으로 국내여행 가본 곳은?",
    },
    {
      id: 5,
      question: "처음으로 해외여행 가본 국가는?",
    },
  ],
  userProfiles: {
    1: {
      id: 1,
      imgSrc: "/images/남자.png",
      loginId: "남자",
      isFollow: true,
    },
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
  communityList: [
    {
      id: 1,
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
      id: 2,
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
      id: 3,
      content_id: 4,
      title: "구로디지털단지 맛집 도장깨기!",
      login_id: "윤지수",
      deadLine: "24/02/10",
      viewCount: 2,
      withCount: 2,
      total: 2,
      status: "마감",
    },
    {
      id: 4,
      content_id: 5,
      title: "구로디지털단지 맛집 도장깨기22!",
      login_id: "윤지수22",
      deadLine: "24/02/10",
      viewCount: 2,
      withCount: 2,
      total: 2,
      status: "마감",
    },
    {
      id: 5,
      content_id: 6,
      title: "구로디지털단지 맛집 도장깨기33!",
      login_id: "윤지수33",
      deadLine: "24/02/10",
      viewCount: 2,
      withCount: 2,
      total: 2,
      status: "마감",
    },
  ],

  communityDetail: [
    {
      id: 1,
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
      id: 2,
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
      id: 3,
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
    },
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
      message: `당신의 아이디는<br />${zido.findId()}<br />입니다.`,
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
    withSuccess: {
      url: "/community/id",
        message: "참여 신청이<br />완료되었습니다.",
        buttonMessage: "확인",
    },
    withFail: {
      url: "/community/id",
        message: "참여 신청이<br />취소되었습니다.",
        buttonMessage: "확인",
    },
    addCommunity: {
      url: "/community",
        message: "게시글 등록이<br />완료되었습니다.",
        buttonMessage: "확인",
    },
    updateCommunity: {
      url: "/community",
        message: "게시글 수정이<br />완료되었습니다.",
        buttonMessage: "확인",
    },
    deleteCommunity: {
      url: "/community",
        message: "게시글 삭제가<br />완료되었습니다.",
        buttonMessage: "확인",
    },
  },
  selectCategories: ["음식점", "관광지"],
  locMap: [
    {
      code: 11,
      color: "#111111",
    },
    {
      code: 21,
      color: "#222222",
    },
    {
      code: 22,
      color: "#333333",
    },
    {
      code: 23,
      color: "#444444",
    },
    {
      code: 24,
      color: "#555555",
    },
    {
      code: 25,
      color: "#666666",
    },
    {
      code: 26,
      color: "#777777",
    },
    {
      code: 29,
      color: "#888888",
    },
    {
      code: 31,
      color: "#999999",
    },
    {
      code: 32,
      color: "#aaaaaa",
    },
    {
      code: 33,
      color: "#bbbbbb",
    },
    {
      code: 34,
      color: "#cccccc",
    },
    {
      code: 35,
      color: "#dddddd",
    },
    {
      code: 36,
      color: "#eeeeee",
    },
    {
      code: 37,
      color: "#ffffff",
    },
    {
      code: 38,
      color: "#000000",
    },
    {
      code: 39,
      color: "#000000",
    },
  ],
  selectNumberOfPeople: [
    1 + " 명",
    2 + " 명",
    3 + " 명",
    4 + " 명",
    5 + " 명",
    6 + " 명",
    7 + " 명",
    8 + " 명",
    9 + " 명",
    10 + " 명",
    11 + " 명",
    12 + " 명",
    13 + " 명",
    14 + " 명",
    15 + " 명",
    16 + " 명",
    17 + " 명",
    18 + " 명",
    19 + " 명",
    20 + " 명"
  ],
  bookmark: {
    1: {
      id: 1,
      tourCount: 10,
      spotPlanCount: 10,
      tourList: [
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
      loginId: "남자",
    },
    2: {
      id: 2,
      tourCount: 10,
      spotPlanCount: 10,
      tourList: [
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
      loginId: "유재석",
    },
    3: {
      id: 3,
      tourCount: 10,
      spotPlanCount: 10,
      tourList: [
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
      loginId: "조세호",
    },
  },
  memberPageData: {
    1: {
      id: 1,
      imgSrc: "/images/남자.png",
      loginId: "남자",
      isFollow: true,
      totalBoard: 10,
      followerCount: 10,
      followingCount: 10,
      bookmarkCount: 10,
      planList: [
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
      spotList: [
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
      sessionId: zido.getMemberId(),
    },
    2: {
      id: 2,
      imgSrc: "/images/유재석.png",
      loginId: "유재석",
      isFollow: true,
      totalBoard: 20,
      followerCount: 20,
      followingCount: 20,
      bookmarkCount: 20,
      planList: [
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
      spotList: [
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
      sessionId: zido.getMemberId(),
    },
    3: {
      id: 3,
      imgSrc: "/images/조세호.png",
      loginId: "조세호",
      isFollow: false,
      totalBoard: 30,
      followerCount: 30,
      followingCount: 30,
      bookmarkCount: 30,
      planList: [
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
      spotList: [
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
      sessionId: zido.getMemberId(),
    },
  },
  configData: {
    imgSrc: "/images/남자.png",
    loginId: "남자",
    totalBoard: 10,
    followerCount: 10,
    followingCount: 10,
    bookmarkCount: 10,
    sessionId: zido.getMemberId(),
  },
  newContent: {
    id: 1,
    imgSrc: "/images/busan2.jpg",
    title: "더미",
    bookmarkCount: 3000,
    grade: 10,
    like: 300,
    myBookmark: true,
    type: "spot",
  },
  searchData: {
    userList: [],
    planList: [],
    spotList: [],
    userCount: 0,
    planCount: 0,
    spotCount: 0,
  },
  tourData: {
    1: {
      id: 1,
      prevId: null,
      nextId: 1,
      locCategory: "부산광역시",
      imgSrc: "/images/busan3.jpg",
      title: "해동용궁사",
      explain:
        "부산광역시 기장군 기장읍 시랑리에 있는 대한불교조계종 제19교구 본사화엄사 소속 사찰.",
      bookmarkCount: 10,
      myBookmark: true,
    },
  },
  spotData: {
    1: {
      id: 1,
      title: "test",
      members: [{ loginId: "test" }],
      photos: [
        "/images/busan1.jpg",
        "/images/busan1.jpg",
        "/images/busan1.jpg",
      ],
      category: "관광지",
      start: "test",
      end: "test",
      grade: 10,
      myBookmark: true,
      myLike: null,
      bookmarkCount: 9,
      likeCount: 10,
      review: "123",
      commentList: [
        {
          id: 1,
          imgSrc: "/images/남자.png",
          loginId: "남자",
          comment: "test",
          commentList: [
            {
              id: 1,
              imgSrc: "/images/남자.png",
              loginId: "남자",
              comment: "test",
            },
            {
              id: 1,
              imgSrc: "/images/남자.png",
              loginId: "남자",
              comment: "test",
            },
          ],
        },
        { id: 1, imgSrc: "/images/남자.png", loginId: "남자", comment: "test" },
      ],
    },
  },
  planData: {
    1: {
      id: 1,
      title: "test",
      members: [
        {
          id: 1,
          imgSrc: "/images/남자.png",
          loginId: "남자",
          isFollow: true,
        },
        {
          id: 2,
          imgSrc: "/images/유재석.png",
          loginId: "유재석",
          isFollow: true,
        },
        {
          id: 3,
          imgSrc: "/images/조세호.png",
          loginId: "조세호",
          isFollow: false,
        },
      ],
      spotList: [{ id: 1, title: 123, start: "2024-04-14T07:30:00+09:00", end: "2024-04-17T07:30:00+09:00" }],
      start: "2024-04-14",
      end: "2024-04-18",
      grade: 10,
      status: "여행중",
      myBookmark: true,
      myLike: null,
      bookmarkCount: 9,
      likeCount: 10,
      review: "123",
      commentList: [
        {
          id: 1,
          imgSrc: "/images/남자.png",
          loginId: "남자",
          comment: "test",
          commentList: [
            {
              id: 1,
              imgSrc: "/images/남자.png",
              loginId: "남자",
              comment: "test",
            },
            {
              id: 1,
              imgSrc: "/images/남자.png",
              loginId: "남자",
              comment: "test",
            },
          ],
        },
        { id: 1, imgSrc: "/images/남자.png", loginId: "남자", comment: "test" },
      ],
    },
  },
};
