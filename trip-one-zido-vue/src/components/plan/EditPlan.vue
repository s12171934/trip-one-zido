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
                <input class="border-0" type="date" v-model="start_date" />
              </div>
              <h4>~</h4>
              <div class="date-time">
                <input class="border-0" type="date" v-model="end_date" />
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
      <div class="demo-app-main">
        <FullCalendar class="demo-app-calendar" :options="calendarOptions">
          <template v-slot:eventContent="arg">
            <b>{{ arg.timeText }}</b>
            <i>{{ arg.event.title }}</i>
          </template>
        </FullCalendar>
      </div>
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
</template>

<script>
import FullCalendar from "@fullcalendar/vue3";
import dayGridPlugin from "@fullcalendar/daygrid";
import timeGridPlugin from "@fullcalendar/timegrid";
import interactionPlugin from "@fullcalendar/interaction";
import data from "/src/assets/data.js";

export function rateStar(stars) {
  document.getElementById("ratingValue").innerText = "별점: " + stars + "점";
}

let eventGuid = 0;
let todayStr = new Date().toISOString().replace(/T.*$/, ""); // YYYY-MM-DD of today

export const INITIAL_EVENTS = [
  {
    id: createEventId(),
    title: "All-day event",
    start: todayStr,
  },
  {
    id: createEventId(),
    title: "Timed event",
    start: todayStr + "T12:00:00",
  },
];

export function createEventId() {
  return String(eventGuid++);
}

export default {
  components: {
    FullCalendar, // make the <FullCalendar> tag available
  },
  data() {
    return {
      selectLocations: data.selectLocations,
      status: 0,
      members: [""],

      calendarOptions: {
        plugins: [
          dayGridPlugin,
          timeGridPlugin,
          interactionPlugin, // needed for dateClick
        ],
        headerToolbar: {
          left: "prev,next today",
          center: "title",
          right: "dayGridMonth,timeGridWeek,timeGridDay",
        },
        initialView: "timeGridWeek",
        initialEvents: INITIAL_EVENTS, // alternatively, use the `events` setting to fetch from a feed
        editable: true,
        selectable: true,
        selectMirror: true,
        dayMaxEvents: true,
        weekends: true,
        select: this.handleDateSelect,
        eventClick: this.handleEventClick,
        eventsSet: this.handleEvents,
      },
      currentEvents: [],
    };
  },

  methods: {
    handleWeekendsToggle() {
      this.calendarOptions.weekends = !this.calendarOptions.weekends; // update a property
    },

    handleDateSelect(selectInfo) {
      let title = prompt("Please enter a new title for your event");
      let calendarApi = selectInfo.view.calendar;

      calendarApi.unselect(); // clear date selection

      if (title) {
        calendarApi.addEvent({
          id: createEventId(),
          title,
          start: selectInfo.startStr,
          end: selectInfo.endStr,
          allDay: selectInfo.allDay,
        });
      }
    },

    handleEventClick(clickInfo) {
      if (
        confirm(
          `Are you sure you want to delete the event '${clickInfo.event.title}'`
        )
      ) {
        clickInfo.event.remove();
      }
    },

    handleEvents(events) {
      this.currentEvents = events;
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
  },
  mounted(){
    this.$emit("meta", this.$route.matched[0].meta.isLogin);
  }
};
document.addEventListener("DOMContentLoaded", function () {
  var buttons = document.querySelectorAll(".btn-outline-primary");
  buttons.forEach(function (button) {
    button.addEventListener("click", function () {
      // Remove the 'active' class from all buttons
      buttons.forEach(function (btn) {
        btn.classList.remove("active");
      });

      // Add the 'active' class to the clicked button
      button.classList.add("active");
    });
  });
});
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
