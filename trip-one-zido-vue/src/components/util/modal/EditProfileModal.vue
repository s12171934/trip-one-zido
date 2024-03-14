<template>
  <div
    class="modal fade"
    id="profileModal"
    tabindex="-1"
    aria-labelledby="profileModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
      <div class="modal-content">
        <div class="modal-header border border-0">
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>

        <div class="m-5 modal-body border rounded-3" id="picBox">
          <img id="configPicUpdate" :src="img" alt="" />
        </div>

        <div class="modal-footer border border-0">
          <label for="file"
            ><a class="button rounded-3 m-0">사진 찾기</a></label
          >
          <input
            type="file"
            name="file"
            id="file"
            @change="getFileName($event.target.files)"
          />
          <form>
            <button
              @click="this.$zido.editProfileImg(img)"
              type="button"
              class="rounded-3"
              id="modalButton"
              data-bs-dismiss="modal"
            >
              사진등록
            </button>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      img: this.$zido.getProfileImg(),
    };
  },
  methods: {
    async getFileName(files) {
      this.img = files[0];
      await this.base64(this.img);
    },
    base64(file) {
      return new Promise((resolve) => {
        let fileReader = new FileReader();
        fileReader.onload = (e) => {
          resolve(e.target.result);
          this.img = e.target.result;
          console.log(this.img);
        };
        fileReader.readAsDataURL(file);
      });
    },
  },
};
</script>

<style scoped>
img {
  width: 260px;
  height: 260px;
}

#file {
  display: none;
}
</style>
