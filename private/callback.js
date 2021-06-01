
function checkGang(count, link, good) {
  count < 3 ? link() : good();
}

function linkGang() {
  console.log('1일 3깡은 기본입니다. 아래 링크를 통해 깡을 시청해주세요');
  console.log('https://youtu.be/xqFvYsy4wE4');
}

function goodGang() {
  console.log('오늘 할당량은 모두 채우셨습니다! :)')
}

checkGang(2, linkGang, goodGang);
