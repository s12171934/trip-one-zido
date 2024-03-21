count: 1,export default {
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

  sortMenu: [
    {
      value: 'created_at DESC',
      view: '최신순',
    },
    {
      value: 'bookmark_count DESC',
      view: '찜갯수순',
    },
    {
      value: 'title',
      view: '사전순',
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
      message: "",
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
      url: "/sign-up",
      message: "회원가입에<br />실패했습니다.",
      buttonMessage: "확인",
    },
    withSuccess: {
      url: '',
      message: "참여 신청이<br />완료되었습니다.",
      buttonMessage: "확인",
    },
    withFail: {
      url: '',
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

  locMap: [
    {
      code: 11,
      count: 20,
    },
    {
      code: 21,
      count: 6,
    },
    {
      code: 22,
      count: 10,
    },
    {
      code: 23,
      count: 15,
    },
    {
      code: 24,
      count: 1,
    },
    {
      code: 25,
      count: 1,
    },
    {
      code: 26,
      count: 1,
    },
    {
      code: 29,
      count: 1,
    },
    {
      code: 31,
      count: 1,
    },
    {
      code: 32,
      count: 1,
    },
    {
      code: 33,
      count: 1,
    },
    {
      code: 34,
      count: 1,
    },
    {
      code: 35,
      count: 15,
    },
    {
      code: 36,
      count: 1,
    },
    {
      code: 37,
      count: 1,
    },
    {
      code: 38,
      count: 1,
    },
    {
      code: 39,
      count: 1,
    },
  ],

  locFrequencyColor: [
    '#EED0CE',
    '#FFBBB8',
    '#FF928E',
    '#F36864',
    '#BB3530',
  ]
};
