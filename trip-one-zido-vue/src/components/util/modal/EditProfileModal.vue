<template>
  <div
    class="modal fade"
    :class="modalShown ? 'show' : ''"
    id="updatepic"
    tabindex="-1"
    aria-labelledby="exampleModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
      <div class="modal-content">
        <div class="modal-header border border-0">
          <button
          @click="$emit('modal')"
            type="button"
            class="btn-close"
          ></button>
        </div>

        <div class="m-5 modal-body border rounded-3" id="picBox">
          <img id="configPicUpdate" src="/images/조세호.png" alt="" />
        </div>

        <div class="modal-footer border border-0">
          <button type="button" class="rounded-3" id="modalButton">
            사진찾기
          </button>
          <form>
            <button
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
  props: {
    modalShown: Boolean,
  },
};
</script>

<style scoped>
.show {
  opacity: 100;
  display: block;
}
</style>
