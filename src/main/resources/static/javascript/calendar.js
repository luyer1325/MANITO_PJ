window.addEventListener("DOMContentLoaded", () => {
  const target = document.querySelector(".calendar");
  const pickdate = document.querySelector(".calendar-hidden");

  const modals = document.querySelectorAll(".modal");

  //날짜 객체
  const current = new Date();
  const today = new Date();
  const dates = [];

  const addZero = (number) => {
    if (number < 10) return `0${number}`;
    else return number;
  };
  const format = (value) => {
    return value.getFullYear() + "년 " + addZero(value.getMonth() + 1) + "월";
  };

  const dayKo = ["일", "월", "화", "수", "목", "금", "토"];

  function initCalendarSetting() {
    // 초기화
    dates.splice(0);
    const currentYear = current.getFullYear();
    const currentMonth = current.getMonth();

    const prevMonthLastDate = new Date(currentYear, currentMonth, 0);
    const currMonthFirstDate = new Date(currentYear, currentMonth, 1);
    const currMonthLastDate = new Date(currentYear, currentMonth + 1, 0);

    // 전달 날짜 입력(currMonthFirstDate의 day 갯수만큼 전달 날짜 입력)
    let prevLastDate = prevMonthLastDate.getDate();

    for (i = 0; i < currMonthFirstDate.getDay(); i++) {
      dates.unshift(
        `<div class="calendar-date b-month" data-date="${currentYear}-${addZero(currentMonth)}-${addZero(
          prevLastDate
        )}">${prevLastDate}</div>`
      );
      prevLastDate--;
    }
    // 현재달 날짜 입력
    const firstDate = currMonthFirstDate.getDate();
    const currLastDate = currMonthLastDate.getDate();
    const todayDate = today.getDate();
    for (i = firstDate; i < currLastDate; i++) {
      // 시작시점 설정 시 사용
      if (i < todayDate) {
        dates.push(`<div class="calendar-date" data-date="${currentYear}-${addZero(currentMonth + 1)}-${addZero(i)}">${i}</div>`);
      } else if (i == todayDate) {
        // 오늘날짜 표시
        dates.push(`<div class="calendar-date" data-date="${currentYear}-${addZero(currentMonth + 1)}-${addZero(i)}">${i}</div>`);
      } else {
        dates.push(`<div class="calendar-date" data-date="${currentYear}-${addZero(currentMonth + 1)}-${addZero(i)}">${i}</div>`);
      }
    }
    // 다음달 날짜 입력
    let nextMonthYear;
    if (currentMonth + 2 > 12) {
      nextMonthYear = currentYear + 1;
    } else {
      nextMonthYear = currentYear;
    }
    for (i = 1; i <= 14; i++) {
      dates.push(`<div class="calendar-date a-month" data-date="${currentYear}-${addZero(currentMonth + 2)}-${addZero(i)}">${i}</div>`);
    }
    // console.log(dates);
    // 타이틀 년 월 넣기
    target.querySelector(".year-month").innerHTML = `${format(current)}`;
    // 주 넣기
    target.querySelector(".calendar-body").innerHTML = dayKo.map((v) => `<div class="calendar-day">${v}</div>`).join("");
    // 날짜 넣기 (6주정도 목록만 42개)
    target.querySelector(".calendar-body").innerHTML += dates.map((v, i) => (i < 42 ? v : "")).join("");

    // 이벤트 등록하기
    // 1. 날짜선택
    const dateList = document.querySelectorAll(".calendar-date");
    dateList.forEach((v) =>
      v.addEventListener("click", function (e) {
        const clickTarget = e.currentTarget;
        pickdate.value = clickTarget.dataset.date;
        // 달력모달 끄기
        showModal(modals[1]);
        hideModal(modals[0]);
      })
    );
  }
  // 달력 만들기
  initCalendarSetting();
  // 2. 달력 넘기기
  // 수정사항 : 달력 날짜넣을 때 날짜로 측정되는 부분 수정하기
  const btns = target.querySelectorAll(".calendar-btn");
  // btns[0] 이전, btns[1] 다음
  btns.forEach((v, i) => {
    v.addEventListener("click", () => {
      if (i == 0) {
        // 이전버튼
        current.setMonth(current.getMonth() - 1);
      } else {
        // 다음버튼
        current.setMonth(current.getMonth() + 1);
      }
      initCalendarSetting();
    });
  });
});
