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
export default {};
</script>

<style scoped>
img {
  width: 260px;
  height: 260px;
}
</style>
