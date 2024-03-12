<template>
  <div
    class="modal fade"
    id="spotModal"
    tabindex="-1"
    data-bs-backdrop="static"
    data-bs-keyboard="false"
    aria-labelledby="alertModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
      <div class="modal-content">
        <div class="modal-header border border-bottom">
          <h1 class="title mt-3">장소 등록</h1>
          <button
            type="button"
            class="btn-close m-3"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>
        <div class="modal-body">
          <!-- ★왼쪽 -->
          <div class="p-2 d-flex flex-column w-100 border-bottom" id="leftSide">
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
                  v-for="(photo, idx) in photos"
                  class="rounded"
                  id="selectedPic"
                  :src="photo"
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
                  <h4>장소 이름</h4>
                </td>
                <td scope="col-2">
                  <input type="text" v-model="title" />
                </td>
              </tr>
              <tr>
                <td>
                  <h4>카테고리</h4>
                </td>
                <td>
                  <div class="select-wrapper">
                    <select class="local-select" v-model="category">
                      <option value="" selected>카테고리</option>
                      <option value="1">음식점</option>
                      <option value="2">여가(관람/체험/스포츠)</option>
                      <option value="3">관광지(산/바다/계곡/해변)</option>
                      <option value="4">테마파크</option>
                      <option value="5">기타</option>
                    </select>
                  </div>
                </td>
              </tr>

              <tr>
                <td><h4>주소</h4></td>
                <td>
                  <div class="d-flex">
                    <input
                      type="text"
                      id="address"
                      name="address"
                      size="70"
                      v-model="address"
                    />
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
                <td colspan="2"><input type="text" v-model="address2" /></td>
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
                  <textarea id="content" name="content" rows="5" cols="50" />
                </td>
              </tr>
            </table>
          </div>
        </div>

        <div class="modal-footer border border-0">
          <div class="m-0 d-flex justify-content-end gap-2">
            <input
              @click="console.log(start)"
              id="input"
              class="button small"
              type="submit"
              value="등록"
            />
            <input class="button alt small" type="button" value="취소" />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      members: [""],
      photos: [],
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
          this.photos.push(e.target.result);
          console.log(this.photos);
        };
        fileReader.readAsDataURL(file);
      });
    },
    delPhoto(idx) {
      this.photos.splice(idx, 1);
    },
    addMember() {
      this.members.push("");
    },
    delMember(idx) {
      this.members.splice(idx, 1);
    },
    searchAddress() {
      new daum.Postcode({
        oncomplete: function (data) {
          this.address = data.address;
          document.querySelector("#address").value = this.address;
        },
      }).open();
    },
  },
};
</script>

<style scoped>
.modal-dialog {
  max-width: 800px;
}

#photo-container {
  overflow: scroll;
  overflow-y: hidden;
  height: 100%;
  margin: 1%;
}

#selectedPic {
  height: 400px;
  margin: 3%;
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
