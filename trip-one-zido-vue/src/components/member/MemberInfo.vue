<template>
  <main class="wrapper">
    <h1>회원 정보 수정</h1>
    <table>
      <!-- 아이디 비밀번호 -->
      <tr id="trHeader">
        <td colspan="3" id="updateTdSet">
          <h3>아이디 & 비밀번호</h3>
        </td>
      </tr>
      <tr id="updateArea">
        <td id="id-css">아이디</td>
        <td colspan="2" width="300">{{ userInfo.loginId }}</td>
      </tr>

      <tr id="updateArea">
        <td>비밀번호</td>
        <td>
          <a @click="$router.push('reset-pw')" class="button">비밀번호 변경</a>
        </td>
        <td></td>
      </tr>

      <tr id="trHeader">
        <td colspan="3" id="updateTdSet">
          <h3 id="trTitle">개인정보</h3>
        </td>
      </tr>

      <!-- 이름 -->
      <tr id="updateArea">
        <td>이름</td>
        <td colspan="2">
          <input type="text" v-model="userInfo.name" />
        </td>
      </tr>

      <!-- 주소 -->
      <tr id="updateArea">
        <td>주소</td>
        <td>
          <input type="text" id="zipcode" readonly />
        </td>
        <td>
          <!-- 다음 우편번호 주소검색 서비스 -->
          <button
            type="button"
            class="address button icon fa-search ps-2 pe-2 ms-2"
            @click="searchAddress"
          >
            주소 검색
          </button>
        </td>
      </tr>
      <tr>
        <td></td>
        <td colspan="2">
          <input
            type="text"
            id="address"
            name="address"
            size="70"
            readonly
          />
        </td>
      </tr>

      <!-- 직접입력 주소 -->
      <tr>
        <td>상세주소</td>
        <td colspan="2"><input type="text" v-model="userInfo.address2" /></td>
      </tr>

      <!-- 보안질문 선택 -->
      <tr id="updateArea">
        <td>보안질문</td>
        <td colspan="2">
          <div class="select-wrapper">
            <select name="category" id="security" v-model="userInfo.question">
              <option
                v-for="securityQuestion in securityQuestions"
                :value="securityQuestion.id"
                :selected="securityQuestion.id == userInfo.question"
              >
                {{ securityQuestion.question }}
              </option>
            </select>
          </div>
        </td>
      </tr>
      <tr id="updateArea">
        <td>보안질문답</td>
        <td colspan="2">
          <input type="text" v-model="userInfo.answer" />
        </td>
      </tr>
      <tr id="updateArea">
        <td>생년월일</td>
        <td colspan="2">{{ userInfo.birth }}</td>
      </tr>
      <tr id="updateArea">
        <td>성별</td>
        <td>
          {{ userInfo.gender == 0 ? "남자" : "여자" }}
        </td>
      </tr>
      <tr id="updateArea">
        <td>전화번호</td>
        <td colspan="2">
          <input type="text" v-model="userInfo.phoneNumber" />
        </td>
      </tr>
      <tr id="updateArea">
        <td>이메일 :</td>
        <td colspan="2">
          <input type="email" v-model="userInfo.email" />
        </td>
      </tr>
    </table>

    <div class="gap-2 d-flex justify-content-between">
      <a @click="$router.push('resign')" class="button alt">회원 탈퇴</a>
      <div>
        <button
          @click="updateMemberInfo"
          class="button"
          data-bs-toggle="modal"
          data-bs-target="#alertModal"
        >
          수정
        </button>
        <a @click="$router.push('/config')" class="button alt ms-3">취소</a>
      </div>
    </div>
  </main>

  <!-- 정보수정 성공여부 모달 -->
  <AlertModal :modal="modal" />
</template>

<script>
import AlertModal from "../util/modal/AlertModal.vue";

export default {
  components: {
    AlertModal,
  },
  data() {
    return {
      modal: "updateMemberInfoFail",
      userInfo: {
        address: null,
        address2: null,
        answer: null,
        birth: null,
        email: null,
        gender: null,
        id: null,
        loginId: null,
        name: null,
        password: null,
        phoneNumber: null,
        question: null,
        zipcode: null,
      },
      securityQuestions: null,
    };
  },
  methods: {
    //다음 주소검색 서비스
    searchAddress() {
      new daum.Postcode({
        oncomplete: function (data) {
          document.querySelector("#zipcode").value = data.zonecode;
          document.querySelector("#address").value = data.address;
        },
      }).open();
    },

    //PUT -- api/member
    updateMemberInfo() {
      this.userInfo.zipcode = document.getElementById("zipcode").value;
      this.userInfo.address = document.getElementById("address").value;
      console.log(this.userInfo);
      this.$zido.updateUserInfo(this.userInfo);
    },
  },
  async mounted() {
    try {
      //GET -- api/member
      //GET -- api/member/signup
      this.userInfo = await this.$zido.getUserInfo();
      this.securityQuestions = await this.$zido.getSecurityQuestions();
      console.log(this.userInfo)
    } catch (error) {
      console.error("회원정보 가져오기 오류:", error);
      // 에러 처리 로직 추가
    }
    this.$emit("meta", this.$route.matched[0].meta.isLogin);
    document.getElementById("zipcode").value = this.userInfo.zipcode;
    document.getElementById("address").value = this.userInfo.address;
  },
};
</script>

<style scoped>
main {
  margin: 0 10%;
}
h1 {
  color: #ff928e !important;
  font-family: "Jalnan";
}

#updateTdSet {
  height: 39;
  text-align: center;
}

#trHeader {
  background-color: rgba(144, 144, 144, 0.075);
}

#updateArea {
  background-color: white;
}
h3 {
  margin: 0;
}
label {
  color: black !important;
}

#id-css {
  width: 200px;
}

.address {
  width: 95%;
}

@media (max-width: 980px) {
  .gap-2 {
    margin-bottom: 3%;
  }
}

@media (max-width: 700px) {
  /* 화면이 작아질 때 텍스트 크기를 작게 설정합니다. */
  table,
  table tr,
  table td,
  table th {
    font-size: 0.9rem;
  }
  .button,
  .button.alt {
    font-size: 0.9rem;
  }
  #id-css {
    width: 100px;
  }
}
@media (max-width: 650px) {
  /* 화면이 작아질 때 텍스트 크기를 작게 설정합니다. */
  table,
  table tr,
  table td,
  table th {
    font-size: 0.8rem;
  }
  .button,
  .button.alt {
    font-size: 0.8rem;
  }
}
@media (max-width: 590px) {
  /* 화면이 작아질 때 텍스트 크기를 작게 설정합니다. */
  table,
  table tr,
  table td,
  table th {
    font-size: 0.7rem;
  }
  .button,
  .button.alt {
    font-size: 0.7rem;
  }
}
@media (max-width: 530px) {
  /* 화면이 작아질 때 텍스트 크기를 작게 설정합니다. */
  table,
  table tr,
  table td,
  table th {
    font-size: 0.6rem;
  }
  .button,
  .button.alt {
    font-size: 0.6rem;
  }
}
@media (max-width: 400px) {
  /* 화면이 작아질 때 텍스트 크기를 작게 설정합니다. */
  table,
  table tr,
  table td,
  table th {
    font-size: 0.5rem;
  }
  .button,
  .button.alt {
    font-size: 0.5rem;
  }
}
@media (max-width: 330px) {
  /* 화면이 작아질 때 텍스트 크기를 작게 설정합니다. */
  table,
  table tr,
  table td,
  table th {
    font-size: 0.4rem;
  }
  .button,
  .button.alt {
    font-size: 0.4rem;
  }
}
</style>
