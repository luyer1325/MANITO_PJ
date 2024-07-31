// 모달 위치 계산
function popupCenter(domain, url, title, w, h) {
    document.domain = domain;
    // 팝업 창의 위치를 계산하여 중앙에 위치시킵니다.
    var left = screen.width / 2 - w / 2;
    var top = screen.height / 2 - h / 2; // window.open 함수로 팝업 창을 엽니다.
    window.open(url, title, "width=" + w + ", height=" + h + ", top=" + top + ", left=" + left + ", scrollbars=yes");
  }
  // 모달 열기
  function showModal(modalTarget) {
    modalTarget.style.opacity = 1;
    modalTarget.style.zIndex = 999;
  }
  // 모달 닫기
  function hideModal(modalTarget) {
    modalTarget.style.opacity = 0;
    modalTarget.style.zIndex = -999;
  }

  // 모달 위치 설정
  function initPosSetting(modalTarget, clickElement) {
    let posX = clickElement.getBoundingClientRect().left + clickElement.getBoundingClientRect().width + 20;
    let posY =
      clickElement.getBoundingClientRect().top +
      clickElement.getBoundingClientRect().height -
      modalTarget.getBoundingClientRect().height;
    modalTarget.style.top = posY + "px";
    modalTarget.style.left = posX + "px";
    // 대상 data-target true로 변경
    clickElement.dataset.target = "true";
  }
  // 달력 열기
  function openCal() {
    event.preventDefault();
    event.stopPropagation();
    const modal = document.querySelector(".modal-bg");
    if (modal !== null) {
      showModal(modal);
    }
    const tg = event.currentTarget;
    const modalCal = modal.querySelector("#cal");
    const modalTimer = modal.querySelector("#timer");
    // 모달창 위치 설정
    initPosSetting(modalCal, tg);
    initPosSetting(modalTimer, tg);
    // 달력모달 켜기
    showModal(modalCal);
    hideModal(modalTimer);
  }

  function showFormDate(date) {
    return dateList[0] + "년" + dateList[1] + "월" + dateList[2] + "일 " + timeList[0] + ":" + timeList[1];
  }
  // 2. 선택완료
  function dateSetting() {
    const time = document.querySelector(".time-picker-view").innerText;
    const date = document.querySelector(".calendar-hidden").value;
    const modal = document.querySelector(".modal-bg");
    const inputTargetParent = document.querySelector("button[data-target='true']");
    const inputTarget = inputTargetParent.querySelector("input[type='text']");
    const dateFormat = (date, time) => {
      time = time.replace(/\s/g, ""); // 공백제거
      let dateList = date.split("-");
      let timeList = time.split(" : ");
      let dateVal = date + " " + time;
      return dateVal;
    };
    // input 값 입력
    inputTarget.value = dateFormat(date, time);
    inputTarget.dataset.pick = dateFormat(date, time);
    // 대상 data-target 초기화
    inputTargetParent.dataset.target = "false";
    // 모달 닫기
    hideModal(modal);
  }
  //모달 타겟 리셋
  function modalTargetReset() {
    const modalBtn = document.querySelector(".modal-open button[data-target='true']");
    modalBtn.dataset.target = "false";
  }
  window.addEventListener("DOMContentLoaded", () => {
    // 모달 외부창 클릭시 꺼짐
    const modal = document.querySelector(".modal-bg");
    modal.addEventListener("click", () => {
      hideModal(modal);
      modalTargetReset(); //타겟 설정 초기화
    });
    // 달력, 시계 닫기이벤트 방지
    const modalCal = modal.querySelector("#cal");
    const modalTimer = modal.querySelector("#timer");
    modalCal.addEventListener("click", function (e) {
      e.stopPropagation();
    });
    modalTimer.addEventListener("click", function (e) {
      e.stopPropagation();
    });
  });