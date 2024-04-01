<template>
  <main class="wrapper d-flex">
    <div class="p-2 d-flex flex-column border-end" id="leftSide">
      <div class="d-flex justify-content-between">
        <h1 @click="console.log(status)" class="title">
          일정 {{ mode === "add" ? "등록" : "수정" }}
        </h1>
        <div class="status-css">
          <div class="d-flex justify-content-between">
            <span>여행할</span>
            <span>여행중</span>
            <span>여행한</span>
          </div>
          <input
            type="range"
            class="form-range custom-range p-3"
            min="0"
            max="2"
            step="1"
            v-model="planData.status"
          />
        </div>
      </div>

      <div class="leftTable">
      <table>
        <tr>
          <td scope="col">
            <h4 class="title-col">일정 제목</h4>
          </td>
          <td scope="col-2">
            <input type="text" class="input" v-model="planData.title" />
          </td>
        </tr>

        <tr>
          <td>
            <h4 class="title-col">기간</h4>
          </td>
          <td>
            <div class="d-flex justify-content-between align-items-center" id="date-time">
              <div class="date-time">
                <input
                  @change="checkOtherDate('start')"
                  class="border-0"
                  type="date"
                  v-model="planData.startDate"
                />
              </div>
              <h4>~</h4>
              <div class="date-time">
                <input
                  @change="checkOtherDate('end')"
                  class="border-0"
                  type="date"
                  v-model="planData.endDate"
                />
              </div>
            </div>
          </td>
        </tr>

        <tr>
          <td><h4 class="title-col">동행인</h4></td>
          <td>
            <div class="d-flex align-items-center" id="with-css">
              <div class="member-container d-flex">
                <input
                  @contextmenu.prevent="delMember(idx)"
                  v-for="(_, idx) in planData.members"
                  type="text"
                  v-model="planData.members[idx].loginId"
                  class="member m-1"
                />
              </div>
              <img
                @click="addMember"
                src="/images/plusbutton.png"
                width="25"
                height="25"
                id="plusMember"
              />
            </div>
          </td>
        </tr>

        <tr>
          <td>
            <h4 class="title-col">여행 장소 카테고리</h4>
          </td>
          <td>
            <div class="select-wrapper">
              <select class="local-select" v-model="planData.locCategory">
                <option value="" selected>지역 선택</option>
                <option v-for="location in selectLocations" :value="location.locCategory">
                  {{ location.value }}
                </option>
              </select>
            </div>
          </td>
        </tr>

        <tr>
          <td colspan="2">
            <h4 class="title-col">지도</h4>
          </td>
        </tr>

        <tr>
          <td colspan="2" class="map">
            <KakaoMapForEditPlan :spots="planData.spots" />
          </td>
        </tr>

        <tr>
          <td>
            <h4 class="title-col">여행한 후기</h4>
          </td>
          <td>
            <Grade id="grade"
              @grade="(grade) => (planData.grade = grade)"
              :grade="planData.grade"
            />
          </td>
        </tr>

        <tr>
          <td colspan="2">
            <textarea
              id="content"
              name="content"
              rows="5"
              cols="50"
              v-model="planData.review"
            />
          </td>
        </tr>
      </table>
      </div>
    </div>

    <!-- ★오른쪽 -->
    <div class="p-2 d-flex flex-column" id="rightSide">
      <FullCalendar
        class="h-100"
        ref="FullCalendar"
        :options="calendarOptions"
        data-bs-toggle="modal"
        data-bs-target="#spotModal"
      />
      <table>
        <tr>
          <td>
            <div class="select-wrapper" id="security">
              <select class="local-select" v-model="planData.visibility">
                <option value=true selected>공개</option>
                <option value=false>비공개</option>
              </select>
            </div>
          </td>
          <td>
            <div class="d-flex" id="add-canlce">
              <input
                @click="submitButton(mode)"
                id="input"
                class="button small"
                type="submit"
                :value="mode === 'add' ? '등록' : '수정'"
              />
              <input
                @click="$router.push('/member-page')"
                id="cancle"
                class="button alt small"
                type="button"
                value="취소"
              />
            </div>
          </td>
        </tr>
      </table>
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
import EditSpotModal from "../util/modal/EditSpotModal.vue";
import FullCalendar from "@fullcalendar/vue3";
import timeGridPlugin from "@fullcalendar/timegrid";
import interactionPlugin from "@fullcalendar/interaction";
import koLocale from "@fullcalendar/core/locales/ko";
import data from "/src/assets/data.js";
import KakaoMapForEditPlan from "../util/KakaoMapForEditPlan.vue";
import Grade from "@/components/util/Grade.vue";

export default {
  components: {
    FullCalendar,
    EditSpotModal,
    KakaoMapForEditPlan,
    Grade,
  },
  data() {
    return {
      mode: this.$route.params.mode,
      selectLocations: data.selectLocations,
      editSpotMode: "",
      spotId: 0,
      refresh: 0,
      calendarEvent: null,
      spotData: null,

      planData: {
        status: 0,
        title: "",
        startDate: "",
        endDate: "",
        members: [{}],
        locCategory: "",
        review: "",
        spots: [],
        visibility: true,
        grade: 0,
        deleteSpots: null,
      },
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
        grade: 0,
        review: "",
      };
    },

    handleEventClick(clickInfo) {
      this.editSpotMode = "edit";
      this.calendarEvent = clickInfo;

      const id = clickInfo.event._def.publicId;
      this.spotData = this.planData.spots.find((spot) => (spot.id == id));
      console.log(this.spotData)
    },

    addMember() {
      this.planData.members.push({loginId: ""});
    },

    delMember(idx) {
      this.planData.members.splice(idx, 1);
    },

    setCalendarByDate() {
      const start = new Date(this.planData.startDate);
      const end = new Date(this.planData.endDate);

      let days = end.getTime() - start.getTime();
      console.log(days);
      days = Math.ceil(days / (1000 * 60 * 60 * 24)) + 1;

      const calendarApi = this.$refs.FullCalendar.getApi();
      this.calendarOptions.views.timeGridDay.duration.days = days;
      this.calendarOptions.firstDay = start.getDay();
      calendarApi.gotoDate(this.planData.startDate);
    },

    checkOtherDate(type) {
      const start = new Date(this.planData.startDate);
      const end = new Date(this.planData.endDate);

      let days = end.getTime() - start.getTime();
      console.log(days);

      if (days < 0 || !days) {
        if (type === "start") {
          this.planData.endDate = this.planData.startDate;
        } else {
          this.planData.startDate = this.planData.endDate;
        }
      }
      this.setCalendarByDate();
    },

    addSpot(calendarEvent, spotData) {
      const calendarApi = calendarEvent.view.calendar;
      calendarApi.addEvent({
        id: this.spotId,
        title: spotData.title,
        start: calendarEvent.startStr,
        end: calendarEvent.endStr,
      });
      
      const newSpot = JSON.parse(JSON.stringify(spotData));
      newSpot.id = this.spotId;
      newSpot.startDate = new Date(calendarEvent.startStr);
      console.log(calendarEvent.startStr)
      console.log(newSpot.startDate)
      newSpot.endDate = new Date(calendarEvent.endStr);
      console.log(calendarEvent.endStr)
      console.log(newSpot.endDate)
      this.planData.spots.push(newSpot);
      // this.spotId++;
    },

    editSpotDetail(calendarEvent, spotData) {
      calendarEvent.event.setProp("title", spotData.title);
      const id = calendarEvent.event._def.publicId;
      const newSpot = JSON.parse(JSON.stringify(spotData));
      const spot = this.planData.spots.find((spot) => (spot.id == id));
      spot.photos = newSpot.photos;
      spot.title = newSpot.title;
      spot.category = newSpot.category;
      spot.address = newSpot.address;
      spot.address2 = newSpot.address2;
      spot.grade = newSpot.grade;
      spot.review = newSpot.review;
    },

    deleteSpot(calendarEvent) {
      calendarEvent.event.remove();
      const id = calendarEvent.event._def.publicId;
      console.log(id)
      delete this.planData.spots[id]
      //deleteSpot 요청시 빈배열 생성
      if (!Array.isArray(this.planData.deleteSpots)) {
          this.planData.deleteSpots = [];
        }
      if(id != 0){
        this.planData.deleteSpots.push(id)
      }
      console.log(this.planData.deleteSpots)
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
    removeDuplicateLoginIds(members) {
      // 중복된 loginId를 저장하기 위한 Set
      const loginIdsSet = new Set(); 
      const uniqueMembers = [];
      for (const member of members) {
        if (!loginIdsSet.has(member.loginId)) {
          loginIdsSet.add(member.loginId);
          uniqueMembers.push(member);
        }
      }
      // 원본 배열을 중복이 없는 배열로 변경
      members.length = 0;
      uniqueMembers.forEach(member => {
        members.push(member);
      });
      // 중복이 제거된 배열 반환
      return members; 
    },
    submitButton(mode) {
      //제출 전 중복 확인
      this.removeDuplicateLoginIds(this.planData.members);
      if (mode == "add") {
        //POST -- /api/plan
        this.$zido.addPlan(this.planData);
      } else {
        //PUT -- /api/plan/${id}
        this.$zido.updatePlan(this.$route.params.id,this.planData);
      }
      location.href = "/member-page";
    },
  },
  async mounted() {
    this.$emit("meta", this.$route.matched[0].meta.isLogin);
    if (this.mode != "add") {
       //GET -- /api/plan/${id}
      this.planData = await this.$zido.getPlanData(this.$route.params.id);
      this.setCalendarByDate();
      this.setInitialEvent();
    }
  },
};
</script>

<style scoped>
main > div {
  width: 50%;
  padding: 20px;
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

.button {
    margin-left: auto;
}
#cancle {
  margin-left:2%;
}
#security {
  width: 70%
}

.p-2 {
  width: 100%;
}

h4 {
      white-space: nowrap; 
      text-overflow: ellipsis; 
  }

.status-css {
  width: 50%;
  white-space: nowrap; 
  text-overflow: ellipsis; 
}
@media (max-width: 1650px) {
  .wrapper {
    flex-direction: column; /* 아이템을 세로로 배치 */
  }
  .p-2 {
    border: none;
  }
  #leftSide {
    border-inline: none !important; /* border -end 제거*/ 
  }
}

/* 작은 화면에서 아이템을 세로로 배치하기 위해 추가된 스타일 */
@media (max-width: 880px) {

  .title-col {
    font-size: 100%;
    display: block; /* 제목을 블록 레벨 요소로 변경하여 다음 줄에 표시 */
  }

  table tr td:first-child,
  table tr td:last-child {
    
    display: block;
  }
  td {
    width: 90%
  }
  #security {
    width: 20%;
  }
}


@media (max-width: 550px) {
  .status-css {
    display: block;
  }
  td {
    width: 450px;
  }
  #date-time {
    display: flex; /* Flex 레이아웃으로 변경 */
    flex-direction: column; /* 세로 방향으로 정렬 */
  }
  .date-time {
    width: 100%;
  }
}

@media (max-width: 480px) {
  td {
    width: 70%;
  }
  #security {
    width: 60%;
  }
}

@media (max-width: 400px) {
  td {
    width: 60%;
  }
}
</style>
../util/KakaoMapForPlanDetail.vue