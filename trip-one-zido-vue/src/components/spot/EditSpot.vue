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
        <div class="d-flex align-items-center" id="photo-container">
          <img
            @contextmenu.prevent="delPhoto(idx)"
            v-for="(photo, idx) in spotData.photos"
            class="rounded"
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
            <div class="d-flex justify-content-between align-items-center">
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
                  :aria-colcount="category" :value="category"
                >
                  {{ category }}
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
            <div class="rating">
              <span>☆</span>
              <span>☆</span>
              <span>☆</span>
              <span>☆</span>
              <span>☆</span>
            </div>
          </td>
        </tr>
        <tr>
          <td colspan="2">
            <textarea v-model="spotData.review" id="content" name="content" rows="5" cols="50" />
          </td>
        </tr>
        <tr>
          <td>
            <div class="select-wrapper" id="security">
              <select class="local-select" v-model="spotData.isPublic">
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

export default {
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
        review: "",
        isPublic: true,
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
          this.spotData.photos.push({photo: e.target.result});
          console.log(this.spotData.photos);
        };
        fileReader.readAsDataURL(file);
      });
    },
    delPhoto(idx) {
      this.spotData.photos.splice(idx, 1);
    },
    addMember() {
      this.spotData.members.push("");
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
      const responseSpotData = await this.$zido.getSpotData(this.$route.params.id);
      this.spotData = {
        photos: responseSpotData.photos,
        title: responseSpotData.title,
        startDate: responseSpotData.startDate,
        endDate: responseSpotData.endDate,
        members: responseSpotData.members,
        category: responseSpotData.category,
        address: responseSpotData.address,
        address2: responseSpotData.address,
        review: responseSpotData.review,
        isPublic: responseSpotData.isPublic,
      };
      for(let photo in this.spotData.photos){

        this.spotData.photos[photo].photo = `data:image/jpeg;base64,${this.spotData.photos[photo].photo}`
      }
      console.log(this.spotData)
      document.querySelector("#address").value = this.spotData.address;
    },
    submitButton(mode) {
      this.spotData.address = document.querySelector("#address").value;
      if (mode == "add") {
        this.$zido.addSpot(this.spotData);
      } else {
        this.$zido.updateSpot(this.$route.params.id,this.spotData);
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
  height: 100%;
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
  padding: 2% 4%;
  box-shadow: 0 0 0 1px #dee1e3 inset;
  border-radius: 0.5rem;
}

#plusMember {
  margin: 2%;
}

.rating {
  unicode-bidi: bidi-override;
  direction: rtl;
  text-align: left;
  padding-left: 1rem;
  color: #ff928e;
}

.rating > span {
  display: inline-block;
  position: relative;
  width: 1.1em;
}

.rating > span:hover:before,
.rating > span:hover ~ span:before {
  content: "\2605";
  position: absolute;
}

textarea {
  resize: none;
}
</style>
