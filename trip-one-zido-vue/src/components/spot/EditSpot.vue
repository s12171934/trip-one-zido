<template>
  <main class="wrapper d-flex">
    <!-- ★왼쪽 -->
    <div class="p-2 d-flex flex-column border-end" id="leftSide">
      <h1 class="title">장소 {{ mode == "add" ? "등록" : "수정" }}</h1>

      <div class="p-2 h-100">
        <div class="d-flex justify-content-between">
          <h4 id="font-vertical">사진 등록</h4>
          <label for="addPic"
            ><img
              src="/images/plusbutton.png"
              width="25"
              height="25"
              id="plusMember"
          /></label>
          <input
            type="file"
            id="addPic"
            @change="addPhotos($event.target.files)"
            multiple
          />
        </div>
        <div class="d-flex align-items-center p-3" id="photo-container">
          <img
            @contextmenu.prevent="delPhoto(idx)"
            @click="spotData.profile = idx"
            v-for="(photo, idx) in spotData.photos"
            class="rounded"
            :class="spotData.profile == idx ? 'shadow-lg bg-light' : ''"
            id="selectedPic"
            :src="photo.photo"
            alt=""
          />
        </div>
      </div>
    </div>

    <!-- ★오른쪽 -->
    <div class="p-2 d-flex flex-column" id="rightSide">
      <table>
        <tr>
          <td scope="col">
            <h4>장소 제목</h4>
          </td>
          <td scope="col-2">
            <input type="text" v-model="spotData.title" />
          </td>
        </tr>

        <tr>
          <td>
            <h4>시간</h4>
          </td>
          <td>
            <div class="d-flex justify-content-between align-items-center" id="date-time">
              <div class="date-time">
                <input
                  class="border-0"
                  type="datetime-local"
                  v-model="spotData.startDate"
                />
              </div>
              <h4>~</h4>
              <div class="date-time">
                <input
                  class="border-0"
                  type="datetime-local"
                  v-model="spotData.endDate"
                />
              </div>
            </div>
          </td>
        </tr>
        <tr>
          <td><h4>동행인</h4></td>
          <td>
            <div class="d-flex align-items-center">
              <div class="member-container d-flex">
                <input
                  @contextmenu.prevent="delMember(idx)"
                  v-for="(_, idx) in spotData.members"
                  type="text"
                  v-model="spotData.members[idx].loginId"
                  class="member m-1"
                />
              </div>
              <img
                @click="addMember"
                src="/images/plusbutton.png"
                width="25"
                height="25"
                id="plusMember"
              />
            </div>
          </td>
        </tr>

        <tr>
          <td>
            <h4>카테고리</h4>
          </td>
          <td>
            <div class="select-wrapper">
              <select class="local-select" v-model="spotData.category">
                <option value="" selected>카테고리</option>
                <option
                  v-for="category in selectCategories"
                  :aria-colcount="category"
                  :value="category.category"
                >
                  {{ category.value }}
                </option>
              </select>
            </div>
          </td>
        </tr>

        <tr>
          <td><h4>주소</h4></td>
          <td>
            <div class="d-flex">
              <input type="text" id="address" name="address" size="70" />
              <button
                type="button"
                class="button icon fa-search ps-2 pe-2 ms-2"
                @click="searchAddress"
              >
                주소 검색
              </button>
            </div>
          </td>
        </tr>

        <tr>
          <td><h4>상세주소</h4></td>
          <td colspan="2"><input type="text" v-model="spotData.address2" /></td>
        </tr>

        <tr>
          <td>
            <h4>여행한 후기</h4>
          </td>
          <td>
            <Grade
              @grade="(grade) => (spotData.grade = grade)"
              :grade="spotData.grade"
            />
          </td>
        </tr>
        <tr>
          <td colspan="2">
            <textarea
              v-model="spotData.review"
              id="content"
              name="content"
              rows="5"
              cols="50"
            />
          </td>
        </tr>
        <tr id="rowbutton">
          <td>
            <div class="select-wrapper">
              <select class="local-select" v-model="spotData.visibility" id="security">
                <option value=true>공개</option>
                <option value=false>비공개</option>
              </select>
            </div>
          </td>
          <td>
            <div class="m-0 d-flex justify-content-end gap-2">
              <input
                @click="submitButton($route.params.mode)"
                id="input"
                class="button small"
                type="submit"
                :value="$route.params.mode == 'add' ? '등록' : '수정'"
              />
              <input
                @click="$router.push('/member-page')"
                class="button alt small"
                type="button"
                value="취소"
              />
            </div>
          </td>
        </tr>
      </table>
    </div>
  </main>
</template>

<script>
import data from "@/assets/data";
import Grade from "@/components/util/Grade.vue";

export default {
  components: {
    Grade,
  },
  data() {
    return {
      selectCategories: data.selectCategories,
      spotData: {
        photos: [],
        title: "",
        startDate: "",
        endDate: "",
        members: [{}],
        category: "",
        address: "",
        address2: "",
        grade: 0,
        review: "",
        visibility: true,
        profile: 0,
      },
    };
  },
  methods: {
    async addPhotos(files) {
      for (let file of files) {
        const photo = file;
        await this.base64(photo);
      }
    },
    base64(file) {
      return new Promise((resolve) => {
        let fileReader = new FileReader();
        fileReader.onload = (e) => {
          resolve(e.target.result);
          this.spotData.photos.push({ photo: e.target.result });
          console.log(this.spotData.photos);
        };
        fileReader.readAsDataURL(file);
      });
    },
    delPhoto(idx) {
      this.spotData.photos.splice(idx, 1);
    },
    addMember() {
      this.spotData.members.push({loginId: ""});
    },
    delMember(idx) {
      this.spotData.members.splice(idx, 1);
    },
    searchAddress() {
      new daum.Postcode({
        oncomplete: function (data) {
          document.querySelector("#address").value = data.address;
        },
      }).open();
    },
    async setSpotData() {
      const responseSpotData = await this.$zido.getSpotData(
        this.$route.params.id
      );
      this.spotData = {
        photos: responseSpotData.photos,
        title: responseSpotData.title,
        startDate: responseSpotData.startDate,
        endDate: responseSpotData.endDate,
        members: responseSpotData.members,
        category: responseSpotData.category,
        address: responseSpotData.address,
        address2: responseSpotData.address,
        grade: responseSpotData.grade,
        review: responseSpotData.review,
        visibility: responseSpotData.visibility,
      };
      for (let photo in this.spotData.photos) {
        this.spotData.photos[
          photo
        ].photo = `data:image/jpeg;base64,${this.spotData.photos[photo].photo}`;
      }
      console.log(this.spotData);
      document.querySelector("#address").value = this.spotData.address;
    },
    submitButton(mode) {
      this.spotData.address = document.querySelector("#address").value;
      if (mode == "add") {
        this.$zido.addSpot(this.spotData);
      } else {
        this.$zido.updateSpot(this.$route.params.id, this.spotData);
      }
      this.$router.push("/member-page");
    },
  },
  mounted() {
    this.$emit("meta", this.$route.matched[0].meta.isLogin);
    if (this.$route.params.id) {
      this.setSpotData();
    }
  },
};
</script>

<style scoped>
main > div {
  width: 50%;
  padding: 20px;
}

#photo-container {
  background-color: #dee1e3;
  overflow: scroll;
  overflow-y: hidden;
  height: 90%;
  margin: 1%;
}

#selectedPic {
  width: 75%;
  height: 100%;
  margin-right: 1%;
}

#addPic {
  display: none;
}

h4 {
  margin: 0;
}

td {
  min-width: 170px;
}

.member-container {
  overflow: scroll;
  overflow-y: hidden;
  height: 100%;
  width: 500px;
}

.member {
  width: 200px;
}

.date-time {
  display: flex;
  padding: 2%;
  box-shadow: 0 0 0 1px #dee1e3 inset;
  border-radius: 0.5rem;
 
}

#plusMember {
  margin: 2%;
}

textarea {
  resize: none;
}

.border-0 {
  color: black;
}
button, .button, select, input, textarea {
  border-radius: 10px !important;
}


@media (max-width: 1460px) { /* 원하는 크기로 설정 */
  .wrapper {
    flex-direction: column; /* 화면이 작아지면 컨텐츠를 세로로 배치 */
  }
  #leftSide, #rightSide {
    width: 100%; /*각 요소를 꽉차게 설정 */
  }
  #rightSide {
    order: 1; /*오른쪽 요소를 아래로 이동 */
  }
  #leftSide {
    border-inline: none !important; /* border -end 제거*/ 
  }
  #photo-container {
    height: 500px;
  }
  table {
    width: 100%;
  }
}

@media (max-width: 835px) {
  table {
    width: 100%;
  }
  table tr td:first-child,
  table tr td:last-child {
    width: 100%;
    display: block;
  }
  table tr td:last-child {
    width: calc(100% - 10px); /* 텍스트 입력 필드의 너비 조정 */
  }
  table tr td h4 {
    margin-bottom: 5px; /* 텍스트 입력 필드와의 간격 조절 */
  }
  #security {
    width: 98%;
  }
}

@media (max-width: 660px) {
  table {
    width: 100%;
  }
  table tr td:first-child,
  table tr td:last-child {
    width: 100%;
    display: block;
  }
  table tr td:last-child {
    width: calc(100% - 10px); /* 텍스트 입력 필드의 너비 조정 */
  }
  table tr td h4 {
    margin-bottom: 5px; /* 텍스트 입력 필드와의 간격 조절 */
  }
  #date-time {
    flex-direction: column;
  }
  .date-time, .date-time input {
    width: 100%; /* 입력 필드의 너비를 100%로 설정하여 컬럼으로 변경 */
  }
  #security {
    width: 98%;
  }
}

@media (max-width: 635px) {
  table tr td:last-child {
    width: calc(95% - 10px); /* 텍스트 입력 필드의 너비 조정 */
  }
  #rowbutton .select-wrapper {
    margin-right: 30px; /* select 요소 간의 간격 조절 */
  }
}

@media (max-width: 600px) {
  table tr td:last-child {
    width: calc(90% - 10px); /* 텍스트 입력 필드의 너비 조정 */
  }
  #rowbutton .select-wrapper {
    margin-right: 55px; /* select 요소 간의 간격 조절 */
  }
}

@media (max-width: 570px) {
  table tr td:last-child {
    width: calc(85% - 10px); /* 텍스트 입력 필드의 너비 조정 */
  }
  #rowbutton .select-wrapper {
    margin-right: 80px; /* select 요소 간의 간격 조절 */
  }
}

@media (max-width: 540px) {
  table tr td:last-child {
    width: calc(80% - 10px); /* 텍스트 입력 필드의 너비 조정 */
  }
  #rowbutton .select-wrapper {
    margin-right: 110px; /* select 요소 간의 간격 조절 */
  }
}

@media (max-width: 500px) {
  table tr td:last-child {
    width: calc(75% - 10px); /* 텍스트 입력 필드의 너비 조정 */
  }
  #rowbutton .select-wrapper {
    margin-right: 140px; /* select 요소 간의 간격 조절 */
  }
}

@media (max-width: 450px) {
  table tr td:last-child {
    width: calc(70% - 10px); /* 텍스트 입력 필드의 너비 조정 */
  }
  #rowbutton .select-wrapper {
    margin-right: 170px; /* select 요소 간의 간격 조절 */
  }
}

@media (max-width: 430px) {
  table tr td:last-child {
    width: calc(65% - 10px); /* 텍스트 입력 필드의 너비 조정 */
  }
  #rowbutton .select-wrapper {
    margin-right: 195px; /* select 요소 간의 간격 조절 */
  }
}
@media (max-width: 400px) {
  table tr td:last-child {
    width: calc(60% - 10px); /* 텍스트 입력 필드의 너비 조정 */
  }
  #rowbutton .select-wrapper {
    margin-right: 220px; /* select 요소 간의 간격 조절 */
  }
}

@media (max-width: 370px) {
  table tr td:last-child {
    width: calc(55% - 10px); /* 텍스트 입력 필드의 너비 조정 */
  }
  #rowbutton .select-wrapper {
    margin-right: 250px; /* select 요소 간의 간격 조절 */
  }
}
</style>
