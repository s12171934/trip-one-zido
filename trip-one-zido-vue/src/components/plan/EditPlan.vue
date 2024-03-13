<template>
  <main class="wrapper d-flex">
    <div class="p-2 d-flex flex-column border-end" id="leftSide">
      <div class="d-flex justify-content-between me-5">
        <h1 @click="console.log(status)" class="title">일정 등록</h1>
        <div class="w-50">
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
            v-model="status"
          />
        </div>
      </div>
      <table>
        <tr>
          <td scope="col">
            <h4>일정 제목</h4>
          </td>
          <td scope="col-2">
            <input type="text" v-model="title" />
          </td>
        </tr>

        <tr>
          <td>
            <h4>기간</h4>
          </td>
          <td>
            <div class="d-flex justify-content-between align-items-center">
              <div class="date-time">
                <input
                  @change="checkOtherDate('start')"
                  class="border-0"
                  type="date"
                  v-model="startDate"
                />
              </div>
              <h4>~</h4>
              <div class="date-time">
                <input
                  @change="checkOtherDate('end')"
                  class="border-0"
                  type="date"
                  v-model="endDate"
                />
              </div>
            </div>
          </td>
        </tr>
        <tr>
          <td><h4>동행인</h4></td>
          <td>
            <div class="d-flex align-items-center">
              <div class="member-container d-flex">
                <input
                  @contextmenu.prevent="delMember(idx)"
                  v-for="(_, idx) in members"
                  type="text"
                  v-model="members[idx]"
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
            <h4>여행 장소 카테고리</h4>
          </td>
          <td>
            <div class="select-wrapper">
              <select class="local-select" name="category">
                <option value="" selected>지역 선택</option>
                <option v-for="location in selectLocations" :value="location">
                  {{ location }}
                </option>
              </select>
            </div>
          </td>
        </tr>

        <tr>
          <td colspan="2">
            <h4>지도</h4>
          </td>
        </tr>
        <tr></tr>

        <tr>
          <td>
            <h4>여행한 후기</h4>
          </td>
          <td>
            <div class="rating">
              <span>☆</span>
              <span>☆</span>
              <span>☆</span>
              <span>☆</span>
              <span>☆</span>
            </div>
          </td>
        </tr>
        <tr>
          <td colspan="2">
            <textarea id="content" name="content" rows="5" cols="50" />
          </td>
        </tr>
      </table>
    </div>

    <!-- ★오른쪽 -->
    <div class="p-2 d-flex flex-column" id="rightSide">
      <FullCalendar
        ref="FullCalendar"
        :options="calendarOptions"
        data-bs-toggle="modal"
        data-bs-target="#spotModal"
      />
      <table>
        <tr>
          <td>
            <div class="select-wrapper" id="security">
              <select class="local-select" name="category">
                <option value="" selected>공개 설정</option>
                <option value="1">공개</option>
                <option value="2">비공개</option>
              </select>
            </div>
          </td>
          <td>
            <div class="m-0 d-flex justify-content-end gap-2">
              <input
                @click="console.log(members)"
                id="input"
                class="button small"
                type="submit"
                value="등록"
              />
              <input
                class="button alt small"
                type="button"
                value="취소"
                onclick="location.href='/html-css/main/detail/detail.html'"
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
import EditSpotModal from "./EditSpotModal.vue";
import FullCalendar from "@fullcalendar/vue3";
import timeGridPlugin from "@fullcalendar/timegrid";
import interactionPlugin from "@fullcalendar/interaction";
import koLocale from "@fullcalendar/core/locales/ko";
import data from "/src/assets/data.js";

export default {
  components: {
    FullCalendar,
    EditSpotModal,
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

      this.spotData =  {
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
