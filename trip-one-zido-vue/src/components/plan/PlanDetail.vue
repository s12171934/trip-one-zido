<template>
  <main class="wrapper d-flex">
    <div class="p-2 d-flex flex-column border-end" id="leftSide">
      <div class="d-flex justify-content-between me-5 w-100">
        <h1 @click="console.log(status)" class="title">
            {{ planData.title}}
          <span class="date"
            >{{ planData.startDate }}~{{ planData.endDate }}
          </span>
        </h1>
        <button class="rounded-5"> {{ selectedStatus }}</button>
      </div>
      <table>
        <tr>
          <td class="title-css">
            <h1 class="p-2">
              <span class="comm">
                <img 
                  id="star" src="/images/star.png"
                /> {{ planData.grade }}
              </span>
              <span class="comm">
                <img
                  @click="$zido.toggleBookmark(planData)" 
                  id="bookmark"
                  :src="
                    planData.myBookmark
                      ? '/images/zzim.png'
                      : '/images/unzzim.png'
                  "
                /> {{ planData.bookmarkCount }}
              </span>
              <span class="comm" 
                    :class="planData.myGood === true ? 'like' : ''">
                <img
                  @click="$zido.toggleLike(planData, true)"
                  id="like"
                  src="/images/like.png"
                /> {{ planData.goodCount }}
              </span>
              <span
                class="comm"
                :class="planData.myGood === false ? 'like' : ''">
                <img
                  @click="$zido.toggleLike(planData, false)"
                  id="unLike"
                  src="/images/unlike.png"
                />
              </span>
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
            <MemberList :list="planData.members" />
          </td>
        </tr>

        <tr>
          <td>
            <h4>지도</h4>
          </td>
        </tr>

        <tr>
          <td>
            <KakaoMapForPlanDetail style="height: 40vh" />
          </td>
        </tr>

        <tr>
          <td>
            <h4>여행한 후기</h4>
          </td>
        </tr>

        <tr>
          <td>
            <textarea id="content" name="content" rows="5" cols="50" readonly>
              {{ planData.review }}
            </textarea>
          </td>
        </tr>
      </table>
    </div>

    <!-- ★오른쪽 -->
    <div class="p-2 d-flex flex-column" id="rightSide">
      <table>
        <tr>
          <td></td>
          <td>
            <div class="m-0 d-flex justify-content-end gap-2">
              <input
                v-show="planData.mine"
                @click="$router.push(`/edit/plan/${$route.params.id}`)"
                id="input"
                class="button small"
                type="submit"
                value="수정"
              />
              <input
                v-show="planData.mine"
                @click="
                  $zido.deletePlan($route.params.id);
                  $router.push('/member-page');
                "
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
        id="calendar"
        ref="FullCalendar"
        :options="calendarOptions"
      />
      <!-- ★댓글창  -->
      <section id="commentBody">
        <div class="card bg-light">
          <div class="card-body">
            <form
              @submit.prevent="
                $zido.addComment(planData.id, comment);
                reloadComment();
              "
              class="border-bottom mb-3"
            >
              <input
                v-model="comment"
                type="text"
                class="form-control me-3"
                placeholder="댓글 추가하기"
              />
              <button class="button alt" type="submit" id="comment">
                댓글
              </button>
            </form>

            <Comment
              v-for="comment in planData.comments"
              :first="true"
              :data="comment"
              @reload="reloadComment"
            />
          </div>
        </div>
      </section>
    </div>
  </main>
</template>

<script>
import FullCalendar from "@fullcalendar/vue3";
import timeGridPlugin from "@fullcalendar/timegrid";
import interactionPlugin from "@fullcalendar/interaction";
import koLocale from "@fullcalendar/core/locales/ko";
import data from "/src/assets/data.js";
import KakaoMapForPlanDetail from "../util/KakaoMapForPlanDetail.vue";
import MemberList from "../util/MemberList.vue";
import Comment from "../util/Comment.vue";

export default {
  components: {
    FullCalendar,
    KakaoMapForPlanDetail,
    MemberList,
    Comment,
  },
  data() {
    return {
      planData: {
        title: null,
        startDate: null,
        endDate: null,
        grade: 0,
        status: 0,
        myBookmark: null,
        bookmarkCount: null,
        myGood: null,
        goodCount: null,
        writer: [],
        members: [{}],
        review: null,
        comments: null,
        mine: null,
      },
      selectLocations: data.selectLocations,
      selectStatus: data.planStatus,
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
        eventClick: this.handleEventClick,
        eventColor: "#ff928e",
      },
    };
  },
  computed: {
    selectedStatus() {
      const foundStatus = this.selectStatus.find(item => item.status === this.planData.status);
      return foundStatus ? foundStatus.value : "여행할"; 
    }
  },
  methods: {
    handleEventClick(clickInfo) {
      window.open(
        `/spot/${clickInfo.event._def.publicId}`,
        "",
        "toolbar=no,scrollbars=yes,resizable=yes,top=0,left=0,width=1200,height=800"
      );
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
      const start = new Date(this.planData.startDate);
      const end = new Date(this.planData.endDate);
      console.log(this.planData.startDate);
      console.log(end);
      let days = end.getTime() - start.getTime();
      days = Math.ceil(days / (1000 * 60 * 60 * 24)) + 1;

      const calendarApi = this.$refs.FullCalendar.getApi();
      this.calendarOptions.views.timeGridDay.duration.days = days;
      this.calendarOptions.firstDay = start.getDay();
      calendarApi.gotoDate(this.planData.startDate);
    },

    setInitialEvent() {
      const calendarApi = this.$refs.FullCalendar.getApi();
      for (let spot of this.planData.spots) {
        calendarApi.addEvent({
          id: spot.id,
          title: spot.title,
          start: spot.startDate,
          end: spot.endDate,
        });
      }

      console.log(calendarApi.getEvents());
    },
    async reloadComment() {
      //GET -- api/plan/${id}
      this.planData = await this.$zido.getPlanData(this.$route.params.id);
    },
  },
  async mounted() {
    this.$emit("meta", this.$route.matched[0].meta.isLogin);
    //GET -- api/plan/${id}
    this.planData = await this.$zido.getPlanData(this.$route.params.id);
    console.log(this.planData);
    this.setCalendarByDate();
    this.setInitialEvent();
    this.planData.members.push(this.planData.writer)
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
.like {
  border: #ff928e 1px solid;
  border-radius: 1.5rem;
  padding: 2%;
}
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
#status-css {
  cursor:default;
}
.date {
  margin: 0.2rem
}

@media (max-width: 1705px) { 
  .date {
    display: inline-block;
  }
}

@media (max-width: 1460px) { /* 원하는 크기로 설정 */
  .wrapper {
    flex-direction: column; /* 화면이 작아지면 컨텐츠를 세로로 배치 */
  }
  #leftSide, #rightSide {
    width: 100%; /*각 요소를 꽉차게 설정 */
  }
  #rightSide {
    order: 1; /*오른쪽 요소를 아래로 이동 */
  }
  #leftSide {
    border-inline: none !important; /* border -end 제거*/ 
  }
}

@media (max-width: 1023px) {
  #bookmark[data-v-3aaf8480],
  #unLike[data-v-3aaf8480], 
  #like[data-v-3aaf8480], 
  #star[data-v-3aaf8480] {
    width: 30px;
    height: 30px;
    margin: 0.5rem;
  }

  .title-css {
    padding: 0
  }
}

@media (max-width: 500px) {
  #comment {
    width: 30%;
    padding: 0;
  }
  .title {
    font-size: calc(1.0rem + 1.5vw);
  }
  .button {
    width: 30%;
    padding:0;
  }
}
</style>
../util/modal/EditSpotModal.vue../util/KakaoMapForPlanDetail.vue
