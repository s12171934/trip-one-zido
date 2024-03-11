<template>
  <div
    class="modal fade"
    id="securityModal"
    tabindex="-1"
    aria-labelledby="securityModalLabel"
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

        <div class="m-5 modal-body border rounded-3" id="modalBox">
          <input
            type="text"
            name="answer"
            id="answer"
            value="보안질문"
            readonly
          />
          <input
            type="text"
            name="answer"
            id="answer"
            v-model="answer"
            placeholder="보안질문 답"
          />
        </div>

        <div class="modal-footer border border-0">
          <button
            @click="checkSecurity"
            type="button"
            class="rounded-3"
            data-bs-toggle="modal"
            data-bs-target="#alertModal"
          >
            보안답안 제출하기
          </button>
          <!-- <button type="button" class="rounded-3" style="color: aliceblue; background-color:#ff928e;" data-bs-dismiss="modal">아이디찾기</button> -->
        </div>
      </div>
    </div>
  </div>

  <AlertModal :modal="modal" />
</template>

<script>
import AlertModal from "../modal/AlertModal.vue";

export default {
  components: {
    AlertModal,
  },
  data() {
    return {
      answer: "",
      modal: "securityFail"
    };
  },
  methods: {
    checkSecurity() {
      if (this.answer === "정답") {
        this.$router.push('reset-pw');
      }
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
