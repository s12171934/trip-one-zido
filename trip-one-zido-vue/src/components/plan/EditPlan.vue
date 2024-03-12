<template>
      <section id="main" class="wrapper">
        <form id="split-container" class="d-flex flex-row">
            
            <!-- ★왼쪽 -->
            <div class="p-2 d-flex flex-column border-end" id="leftSide">
                <!-- <div style="text-align: left;"> -->
                <div>
                    <h1 class="title" id="title">일정 등록</h1>
                    <br>
                </div>
                <div class="p-2 d-flex flex-row" id="blank">
                    <h4>일정 제목</h4>
                    <input type="text" value="" id="trip-state-button">
                        <div class="btn-group" id="trip-state-button-group" role="group" aria-label="Basic radio toggle button group">
                            <input type="radio" class="btn-check" name="btnradio" id="btnradio1" autocomplete="off" checked>
                            <label class="btn btn-outline-primary" for="btnradio1">여행전</label>
                            
                            <input type="radio" class="btn-check" name="btnradio" id="btnradio2" autocomplete="off">
                            <label class="btn btn-outline-primary" for="btnradio2">여행중</label>
                            
                            <input type="radio" class="btn-check" name="btnradio" id="btnradio3" autocomplete="off">
                            <label class="btn btn-outline-primary" for="btnradio3">여행한</label>
                        </div>
                </div>

                <div class="p-2 d-flex" id="blank"><h4>기간</h4>
                    <label for="start_date"></label>
                    <input class="flex-fill" type="date" id="start_date" name="start_date">
                    <h4>~</h4>
                    <label for="end_date"></label>
                    <input class="flex-fill" type="date" id="end_date" name="end_date">
                </div>

                <div class="p-2 d-flex border-bottom"><h4 id="with">동행인</h4> &nbsp;&nbsp;
                    <!-- <form action="send_email.php" method="POST"> -->
                    <label for="email1"></label>
                    <input class="flex-fill" type="email" id="email" name="email1"><br><br>

                    <label for="email2"></label>
                    <input class="flex-fill" type="email" id="email" name="email2"><br><br>

                    <label for="email3"></label>
                    <input class="flex-fill" type="email" id="email" name="email3"><br><br>
                    <!-- 필요한 만큼 이메일 주소 입력 필드를 추가할 수 있음 -->
                    <!-- </form> -->
                    <div class="actions vertical small" id="plusButton">
                        <button id ="buttonBackground"><img src="/images/plusbutton.png" width="25" height="25"></button>
                    </div>
                </div>
    
                <div class="p-2 mt-3 border-bottom"><h4> 지도 </h4>
                    <!-- Google 지도를 삽입할 영역 -->
                    <iframe
                    id="map"
                    width="600"
                    height="300"
                    frameborder="0"
                    src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3164.529575556133!2d127.02654691567728!3d37.497953624024396!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x357ca31a218db503%3A0x72a372df19cf7b95!2z7ISc7Jq47Yq567OE7IucIOyWtOyKpO2PrCDshozqs6DsiJzsnbTsiqTqtawg67Cg7J2067aAIDQwMC00MA!5e0!3m2!1sko!2skr!4v1645788148025!5m2!1sko!2skr"
                    allowfullscreen>
                    </iframe>
                </div>

                <div class="p-2 d-flex mt-3" id="trip-review"><h4>여행한 후기</h4> 
                    <div class="rating flex-fill" id="star">
                        <span onclick=rateStar(10)>☆</span>
                        <span onclick=rateStar(8)>☆</span>
                        <span onclick=rateStar(6)>☆</span>
                        <span onclick=rateStar(4)>☆</span>
                        <span onclick=rateStar(2)>☆</span>
                    </div>
                    <p class="flex-fill" id="ratingValue">별점을 선택하세요.</p>
                </div>
                <!-- 여행후기 -->
                <div class="p-2">
                    <!-- <form action="submit_post.php" method="POST">             -->
                        <textarea id="content" name="content" rows="5" cols="50"></textarea>
                    <!-- </form> -->
                </div>
            </div>

            <!-- ★오른쪽 -->
            <div class="p-2 d-flex flex-column border-end" id="rightSide">
                <div class="p-2 d-flex flex-row" id="title">
                    <h4 id="title2">여행 중 일정 및 장소</h4>
                    &nbsp;&nbsp;
                        <div class="select-wrapper" >
                            <select class="form-control" name="category">
									<option value="" selected>지역 선택</option>
									<option v-for="location in selectLocations" :value="location">
									{{ location }}
									</option>
                            </select>
						</div>
                    <br>
                </div>

                <div class='demo-app-main'>
                <FullCalendar
                    class='demo-app-calendar'
                    :options='calendarOptions'>
                    
                    <template v-slot:eventContent='arg'>
                    <b>{{ arg.timeText }}</b>
                    <i>{{ arg.event.title }}</i>
                    </template>
                </FullCalendar>
                </div>


                <div class="p-2 d-flex flex-row">
                    <div class="select-wrapper">
                        <select class ="local-select" name="category" id="security">
                            <option value="" selected>공개 설정</option>
                            <option value="1">공개</option>
                            <option value="2">비공개</option>
                        </select>
                    </div>
                    <div class="input-add-cancle">
                        <input id="input" class="button small" type="submit" value="등록">
                        <input class="button alt small" type="button" value="취소"
                        onclick="location.href='/html-css/main/detail.html'">
                    </div>
                </div>
                    
                <!-- Break1 -->
				<div class="p-1 12u$">
					<ul class="actions">
					<!-- Button trigger modal -->
					<button type="button" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#exampleModal">
						게시글등록 모달
					</button>

					<!-- Modal -->
					<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
						<!-- Vertically centered scrollable modal -->
						<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
							<div class="modal-content">
								<div class="modal-header border border-0">
								<!-- <h1 class="modal-title fs-5" id="exampleModalLabel">타이틀 없애고</h1> -->
								<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
								</div>

								<div class="m-5 modal-body border rounded-3" id="modalBox">
								<h3>게시물 등록이</h3>
								<h3>완료되었습니다</h3>
								</div>

								<div class="modal-footer border border-0">
								<!-- <button type="button" class="btn btn-secondary" >로그인하러가기</button> -->
								<button type="button" class="rounded-3" id="modalButton" 
                                    onclick="location.href='/schedule/detail/detail.html'">목록가기</button>
								</div>
							</div>
						</div>
					</div>
					</ul>
				</div>

            </div>

        </form>
    </section>
</template>

<script>
import FullCalendar from '@fullcalendar/vue3'
import dayGridPlugin from '@fullcalendar/daygrid'
import timeGridPlugin from '@fullcalendar/timegrid'
import interactionPlugin from '@fullcalendar/interaction'
import data from "/src/assets/data.js";

export function rateStar(stars) {
    document.getElementById('ratingValue').innerText = '별점: ' + stars + '점';
}

let eventGuid = 0
let todayStr = new Date().toISOString().replace(/T.*$/, '') // YYYY-MM-DD of today

export const INITIAL_EVENTS = [
  {
    id: createEventId(),
    title: 'All-day event',
    start: todayStr
  },
  {
    id: createEventId(),
    title: 'Timed event',
    start: todayStr + 'T12:00:00'
  }
]

export function createEventId() {
  return String(eventGuid++)
}

export default {
    components: {
    FullCalendar // make the <FullCalendar> tag available
    },
    data() {
    return {
        selectLocations: data.selectLocations,

        calendarOptions: {
        plugins: [
          dayGridPlugin,
          timeGridPlugin,
          interactionPlugin // needed for dateClick
        ],
        headerToolbar: {
          left: 'prev,next today',
          center: 'title',
          right: 'dayGridMonth,timeGridWeek,timeGridDay'
        },
        initialView: 'timeGridWeek',
        initialEvents: INITIAL_EVENTS, // alternatively, use the `events` setting to fetch from a feed
        editable: true,
        selectable: true,
        selectMirror: true,
        dayMaxEvents: true,
        weekends: true,
        select: this.handleDateSelect,
        eventClick: this.handleEventClick,
        eventsSet: this.handleEvents
        },
        currentEvents: [],
        }
    },

    methods: {
    handleWeekendsToggle() {
      this.calendarOptions.weekends = !this.calendarOptions.weekends // update a property
    },

    handleDateSelect(selectInfo) {
      let title = prompt('Please enter a new title for your event')
      let calendarApi = selectInfo.view.calendar

      calendarApi.unselect() // clear date selection

      if (title) {
        calendarApi.addEvent({
          id: createEventId(),
          title,
          start: selectInfo.startStr,
          end: selectInfo.endStr,
          allDay: selectInfo.allDay
        })
      }
    },

    handleEventClick(clickInfo) {
      if (confirm(`Are you sure you want to delete the event '${clickInfo.event.title}'`)) {
        clickInfo.event.remove()
      }
    },

    handleEvents(events) {
      this.currentEvents = events
    },

    toggle(content) {
      content.myBookmark = !content.myBookmark;
    },
  }
}
    document.addEventListener('DOMContentLoaded', function () {
        var buttons = document.querySelectorAll('.btn-outline-primary');
        buttons.forEach(function (button) {
            button.addEventListener('click', function () {
                // Remove the 'active' class from all buttons
                buttons.forEach(function (btn) {
                    btn.classList.remove('active');
                });

                // Add the 'active' class to the clicked button
                button.classList.add('active');
            });
        });
    });

</script>

<style scoped>

.select-wrapper {
    font-size: small;
}

h1, h2, h3, h4, h5, body, .button, #security, .rating, #ratingValue {
  font-family: 'Jalnan';
}

h4, #email {
  color: rgb(0, 0, 0) !important;
}


/* 달력 */
html, body {
    margin: 0;
    padding: 0;
    font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
    /* font-size: 14px; */
}

#calendar {
    width: 90%;
    max-width: 1100px;
    margin: 40px auto;
}

/* 별점주기 */
.rating {
    unicode-bidi: bidi-override;
    direction: rtl;
    text-align: left;
}

.rating>span {
    display: inline-block;
    position: relative;
    width: 1.1em;
}

.rating>span:hover:before,
.rating>span:hover~span:before {
    content: "\2605";
    position: absolute;
}
/* 화면 나누기 */
#split-container {
display: flex;
width: 100%;
}
#split-container > div {
    width: 50%;
    padding: 20px;
}
/* 지도 크기 설정 */
#map {
    height: 400px;
    width: 100%;
}

input{
    font-family: 'Jalnan' !important;
}

#input {
    color:white !important;
    background-color: #ff928e !important;
}

.flex-fill {
    color: #ff928e;
}

#start_date, #end_date {
    color: black;
    margin-right: 10px; margin-left: 10px;
}

#content{
        resize: none;
}

/* refactoring */

#main {
    margin-left: 5%;
    margin-right: 5%;
    margin-top: -3%;
}

#leftSide {
    margin-right: auto;
}

#leftSide #title {
    color:#ff928e;
    /* margin-top:5% */
}

#trip-state-button {
    margin-right: 10px; 
    margin-left: 10px;
    width: 40%;
    height: 70%;
}

#email {
    margin-right: 5px; 
    margin-left: 5px; 
    width: 20%; 
    height: 70%;
    margin-top: 2%
}

#with {
    margin-top: 2%
}

#buttonBackground {
    background-color: white;
}

#map {
    border: 0;
}

#rightSide {
    margin-left: 3%;
}

#rightSide #title {
    margin-top: 15%
}

#rightSide #title #title2 {
    margin-top: 2%
}

.select-wrapper {
    width: 20%
}

.input-add-cancle {
    margin-left: auto
}

#star {
    margin-right: 10px;
    margin-left: 100px;
}

#trip-review {
    margin-bottom:-1%
}

/* .btn-check:checked {
    color :#ff928e ;
} */

.btn-check:checked + .btn-outline-primary::before {
    background-color: #ff928e;
}
.btn-check:checked + .btn-outline-primary:focus {
    border-color:#ff928e;
}

#app {
    height: auto;
}
</style>