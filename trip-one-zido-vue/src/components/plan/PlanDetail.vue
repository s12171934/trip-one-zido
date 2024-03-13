<template>
  <main class="wrapper d-flex">
    <div class="p-2 d-flex flex-column border-end" id="leftSide">
      <div class="d-flex justify-content-between me-5 w-100">
        <h1 @click="console.log(status)" class="title">
          일정 제목<span class="date">일정 기간</span>
          <span class="comm"
                ><img id="star" src="/images/star.png" />10</span
              >
        </h1>
        <button class="rounded-5">여행중</button>
      </div>
      <table>
        <tr>
          <td>
            <h1>
              <span class="comm"
                ><img id="bookmark" src="/images/zzim.png" />123</span
              >
              <span class="comm"
                ><img id="like" src="/images/like.png" />345</span
              >
              <span class="comm"
                ><img id="unLike" src="/images/unlike.png" />345</span
              >
            </h1>
          </td>
        </tr>
        <tr>
          <td>
            <h4>참여 인원</h4>
          </td>
        </tr>
        <tr>
          <td>
            <MemberList />
          </td>
        </tr>
        <tr>
          <td>
            <h4>지도</h4>
          </td>
        </tr>
        <tr>
          <td>
            <KakaoMapForEditPlan style="height: 40vh" />
          </td>
        </tr>

        <tr>
          <td>
            <h4>여행한 후기</h4>
          </td>
        </tr>
        <tr>
          <td>
            <textarea id="content" name="content" rows="5" cols="50" />
          </td>
        </tr>
      </table>
    </div>

    <!-- ★오른쪽 -->
    <div class="p-2 d-flex flex-column" id="rightSide">
      <table v-if="$route.params.id === 1">
        <tr>
          <td>
            <div class="select-wrapper" id="security">
              <select class="local-select" name="category">
                <option value="1">공개</option>
                <option value="2">비공개</option>
              </select>
            </div>
          </td>
          <td>
            <div class="m-0 d-flex justify-content-end gap-2">
              <input
                @click="$router.push('/member-page')"
                id="input"
                class="button small"
                type="submit"
                value="수정"
              />
              <input
                @click="$router.push('/member-page')"
                class="button alt small"
                type="button"
                value="삭제"
              />
            </div>
          </td>
        </tr>
      </table>
      <FullCalendar
        class="h-100"
        ref="FullCalendar"
        :options="calendarOptions"
        data-bs-toggle="modal"
        data-bs-target="#spotModal"
      />
      <!-- ★댓글창  -->
      <section id="commentBody">
        <div class="card bg-light">
          <div class="card-body">
            <form class="border-bottom">
              <input
                name="comment"
                type="text"
                class="form-control me-3"
                placeholder="댓글 추가하기"
              />
              <button class="button alt" type="submit" id="comment">
                댓글
              </button>
            </form>
            <!-- Comment with nested comments-->
            <div class="d-flex mb-4">
              <!-- Parent comment-->
              <div class="flex-shrink-0">
                <br />
                <img
                  class="rounded-circle"
                  src="/images/유재석.png"
                  alt="..."
                  id="commentProfilePic"
                />
              </div>
              <div class="ms-3">
                <br />
                <div class="fw-bold">유재석</div>
                부산 먹거리가 너무많네요 ~
                <small
                  ><b><a href="#" id="plusComment">답글</a></b></small
                >
                <div>
                  <small><a href="#" id="addedComment">▼답글 2개</a></small>
                </div>
                <!-- Child comment 1-->
                <div class="d-flex mt-4">
                  <div class="flex-shrink-0">
                    <img
                      class="rounded-circle"
                      src="/images/남자.png"
                      alt="..."
                      id="commentProfilePic"
                    />
                  </div>
                  <div class="ms-3">
                    <div class="fw-bold">
                      대댓글 단사람1
                      <a href="#" id="commentUpdate">수정</a>
                      <a href="#" id="commentDelete">삭제</a>
                    </div>
                    광안리 주변 횟집추천드려요!
                  </div>
                </div>
                <!-- Child comment 2-->
                <div class="d-flex mt-4">
                  <div class="flex-shrink-0">
                    <img
                      class="rounded-circle"
                      src="/images/여자.png"
                      alt="..."
                      id="commentProfilePic"
                    />
                  </div>
                  <div class="ms-3">
                    <div class="fw-bold">대댓글 단사람2</div>
                    거기도 맛있고~ 부산집도 맛있어요
                  </div>
                </div>
              </div>
            </div>
            <!-- Single comment-->
            <div class="d-flex mb-4">
              <div class="flex-shrink-0">
                <img
                  class="rounded-circle"
                  src="/images/조세호.png"
                  alt="..."
                  id="commentProfilePic"
                />
              </div>
              <div class="ms-3">
                <div class="fw-bold">조세호</div>
                ㅎㅎ
              </div>
            </div>
          </div>
        </div>
      </section>
    </div>
  </main>
  <EditSpotModal
    :editData="spotData"
    :mode="editSpotMode"
    :event="calendarEvent"
    @add="addSpot"
    @edit="editSpotDetail"
    @delete="deleteSpot"
  />
</template>

<script>
import EditSpotModal from "./EditSpotModal.vue";
import FullCalendar from "@fullcalendar/vue3";
import timeGridPlugin from "@fullcalendar/timegrid";
import interactionPlugin from "@fullcalendar/interaction";
import koLocale from "@fullcalendar/core/locales/ko";
import data from "/src/assets/data.js";
import KakaoMapForEditPlan from "../util/KakaoMapForEditPlan.vue";
import MemberList from "../util/MemberList.vue";

export default {
  components: {
    FullCalendar,
    EditSpotModal,
    KakaoMapForEditPlan,
    MemberList,
  },
  data() {
    return {
      selectLocations: data.selectLocations,
      status: 0,
      members: [""],
      startDate: "",
      endDate: "",
      editSpotMode: "",
      spotId: 0,
      refresh: 0,
      spots: {},
      calendarEvent: null,
      spotData: null,
      popUpOptions:
        "toolbar=no,resizable=yes,status=no,width=800,height=1000,top=0,left=0",

      calendarOptions: {
        plugins: [timeGridPlugin, interactionPlugin],
        initialView: "timeGridDay",
        views: {
          timeGridDay: {
            type: "timeGrid",
            duration: { days: 4 },
          },
        },
        dayHeaderFormat: {
          weekday: "short",
          month: "numeric",
          day: "numeric",
          omitCommas: true,
        },
        locale: koLocale,
        headerToolbar: false,
        allDaySlot: false,
        selectable: true,
        slotMinTime: "06:00:00",
        select: this.handleDateSelect,
        eventClick: this.handleEventClick,
        eventColor: "#ff928e",
      },
    };
  },

  methods: {
    handleDateSelect(selectInfo) {
      this.editSpotMode = "add";
      this.calendarEvent = selectInfo;

      this.spotData = {
        photos: [],
        title: "",
        category: "",
        address: "",
        address2: "",
        rate: 0,
        review: "",
      };
    },

    handleEventClick(clickInfo) {
      this.editSpotMode = "edit";
      this.calendarEvent = clickInfo;

      const id = clickInfo.event._def.publicId;
      this.spotData = this.spots[id];
    },

    addMember() {
      this.members.push("");
    },
    delMember(idx) {
      this.members.splice(idx, 1);
    },

    toggle(content) {
      content.myBookmark = !content.myBookmark;
    },
    setCalendarByDate() {
      const start = new Date(this.startDate);
      const end = new Date(this.endDate);
      let days = end.getTime() - start.getTime();
      console.log(days);
      days = Math.ceil(days / (1000 * 60 * 60 * 24)) + 1;

      const calendarApi = this.$refs.FullCalendar.getApi();
      this.calendarOptions.views.timeGridDay.duration.days = days;
      this.calendarOptions.firstDay = start.getDay();
      calendarApi.gotoDate(this.startDate);
    },
    checkOtherDate(type) {
      const start = new Date(this.startDate);
      const end = new Date(this.endDate);
      let days = end.getTime() - start.getTime();
      console.log(days);

      if (days < 0 || !days) {
        if (type === "start") {
          this.endDate = this.startDate;
        } else {
          this.startDate = this.endDate;
        }
      }
      this.setCalendarByDate();
    },
    addSpot(calendarEvent, spotData) {
      const calendarApi = calendarEvent.view.calendar;
      calendarApi.addEvent({
        id: ++this.spotId,
        title: spotData.title,
        start: calendarEvent.startStr,
        end: calendarEvent.endStr,
      });
      const newSpot = JSON.parse(JSON.stringify(spotData));
      newSpot.id = this.spotId;
      newSpot.start = calendarEvent.startStr;
      newSpot.end = calendarEvent.endStr;
      this.spots[this.spotId] = newSpot;
    },
    editSpotDetail(calendarEvent, spotData) {
      calendarEvent.event.setProp("title", spotData.title);
      const id = calendarEvent.event._def.publicId;
      const newSpot = JSON.parse(JSON.stringify(spotData));
      this.spots[id].photos = newSpot.photos;
      this.spots[id].title = newSpot.title;
      this.spots[id].category = newSpot.category;
      this.spots[id].address = newSpot.address;
      this.spots[id].address2 = newSpot.address2;
      this.spots[id].rate = newSpot.rate;
      this.spots[id].review = newSpot.review;

      console.log(this.spots);
    },
    deleteSpot(calendarEvent) {
      calendarEvent.event.remove();
      const id = calendarEvent.event._def.publicId;
      delete this.spots[id];

      console.log(this.spots);
    },
  },
  mounted() {
    this.$emit("meta", this.$route.matched[0].meta.isLogin);
  },
};
</script>

<style scoped>
main > div {
  width: 50%;
  padding: 20px;
}

.date {
  font-size: 50%;
  color: grey;
  margin: 1rem;
}

.comm {
  font-size: 70%;
  margin-right: 1rem;
}

#bookmark,
#unLike,
#like,
#star {
  width: 40px;
  height: 40px;
  margin: 0.5rem;
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

.custom-range::-webkit-slider-thumb {
  background: #ff928e;
}

.custom-range::-moz-range-thumb {
  background: #ff928e;
}

.custom-range::-ms-thumb {
  background: #ff928e;
}

#commentBody {
  width: 100%;
  margin-top: 5%;
}

.card-body > form {
  display: flex;
  width: 100%;
  padding-bottom: 1rem;
  margin: 0;
}

#plusComment {
  color: darkgray;
  text-decoration: none;
}

#addedComment {
  text-decoration: none;
}

#commentUpdate,
#commentDelete {
  color: darkgray;
  text-decoration: none;
}

#commentProfilePic {
  width: 75px;
  height: 75px;
}
</style>

<style>
.fc-day-today {
  background-color: inherit !important;
}
.fc-col-header-cell-cushion {
  text-decoration: none;
  color: #ff928e;
}
colgroup col {
  width: 100px !important;
}
.fc-scrollgrid-shrink-cushion {
  margin-right: 3%;
}
</style>
