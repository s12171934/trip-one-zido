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
        <!-- modal header -->
        <div class="modal-header border border-0">
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>
        <!-- modal body -->
        <div class="m-5 modal-body border rounded-3" id="picBox">
          <img id="configPicUpdate" :src="img" alt="" />
        </div>
        <!-- modal footer -->
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
              @click="submitButton()"
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
      img: "/images/남자.png",
    };
  },
  methods: {
    async getFileName(files) {
      this.img = files[0];
      console.log(this.img);
      await this.base64(this.img);
    },
    base64(file) {
      return new Promise((resolve) => {
        let fileReader = new FileReader();
        fileReader.onload = (e) => {
          resolve(e.target.result);
          this.img = e.target.result;
          console.log(this.img);
          console.log("check");
        };
        fileReader.readAsDataURL(file);
      });
    },
    submitButton() {
      this.$zido.editProfileImg(this.img);
      location.href = "/config";
    },
  },
  mounted() {
    this.$zido
      .getProfileImg()
      .then((res) => (this.img = `data:image/jpeg;base64,${res}`));
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

.modal-content {
  width: 400px;
}

label {
  font-weight: normal !important;
  font-size: 16px;
}

@media (max-width: 415px) {
  button,
  label {
    font-size: 14px;
  }
  .modal-footer {
    width: 374px;
  }
}

@media (max-width: 410px) {
  img {
    width: 250px;
    height: 250px;
  }
}

@media (max-width: 400px) {
  img {
    width: 240px;
    height: 240px;
  }
  .modal-footer {
    width: 365px;
  }
}

@media (max-width: 385px) {
  img {
    width: 230px;
    height: 230px;
  }
  .modal-footer {
    width: 355px;
  }
}

@media (max-width: 380px) {
  img {
    width: 220px;
    height: 220px;
  }
  button,
  label {
    font-size: 12px;
  }
  .modal-footer {
    width: 330px;
  }
}

@media (max-width: 360px) {
  img {
    width: 200px;
    height: 200px;
  }
  .modal-footer {
    width: 320px;
  }
}

@media (max-width: 340px) {
  img {
    width: 180px;
    height: 180px;
  }
  .modal-footer {
    width: 310px;
  }
}
</style>
