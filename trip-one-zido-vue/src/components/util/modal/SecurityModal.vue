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
        <div
          v-if="securityId"
          class="m-5 modal-body border rounded-3"
          id="modalBox"
        >
          <input
            type="text"
            name="question"
            id="question"
            :value="SecurityQuestion"
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

        <div v-else class="m-5 modal-body border rounded-3" id="modalBox">
          <h3>비밀번호 찾기에<br />실패했습니다.</h3>
        </div>
        <!-- modal footer -->
        <div class="modal-footer border border-0">
          <button
            v-if="securityId"
            @click="checkSecurity"
            type="button"
            class="rounded-3"
            data-bs-toggle="modal"
            data-bs-target="#alertModal"
          >
            보안답안 제출하기
          </button>
          <button
            v-else
            @click="$router.push('/find')"
            type="button"
            class="rounded-3"
            data-bs-dismiss="modal"
          >
            재시도
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import AlertModal from "./AlertModal.vue";

export default {
  props: {
    securityId: Number,
    SecurityQuestion: String,
  },
  components: {
    AlertModal,
  },
  data() {
    return {
      answer: "",
    };
  },
  methods: {
    checkSecurity() {
      this.$zido
        .checkSecurityAnswer(this.securityId, this.answer)
        .then((result) => {
          if (result) {
            location.href = `/reset-pw/${this.securityId}`;
          } else {
            this.$emit("securityFail");
          }
        });
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
