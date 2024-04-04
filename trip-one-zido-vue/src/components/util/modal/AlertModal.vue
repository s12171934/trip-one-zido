<template>
  <div
    class="modal fade"
    id="alertModal"
    tabindex="-1"
    aria-labelledby="alertModalLabel"
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
        <div class="m-5 modal-body border rounded-3" id="modalBox">
          <h3 v-html="modalData.message"></h3>
        </div>
        <!-- modal footer -->
        <div class="modal-footer border border-0">
          <button
            @click="$router.push(modalData.url)"
            type="button"
            class="rounded-3"
            data-bs-dismiss="modal"
          >
            {{ modalData.buttonMessage }}
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import data from "@/assets/data.js";

export default {
  props: {
    modal: String,
  },
  data() {
    return {
      modalData: data.modalDatas[this.modal],
    };
  },
  watch: {
    modal() {
      this.modalData = data.modalDatas[this.modal];
    },
  },
};
</script>

<style scoped>
#modalButton,
#button {
  color: rgb(255, 255, 255) !important;
  background-color: #ff928e !important;
}

#modalBox {
  background-color: #d9d9d9;
}

.show {
  opacity: 100;
  display: block;
}

#button {
  border-radius: 30px;
  font-size: 17px;
}
a button {
  color: rgb(255, 255, 255);
  background-color: #ff928e;
}

h3 {
  text-align: center;
  margin: 0;
  color: gray;
}
</style>
