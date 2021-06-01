
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


//결과 : 1일 3깡은 기본입니다. 아래 링크를 통해 깡을 시청해주세요. (링크)


// 1)
function findUser(id) {
  const user = {
    id: id,
    name: "User" + id,
    email: id + "@test.com",
  };
  return user;
}

const user = findUser(1);
console.log("user:", user);

// 2)
function findUserAndCallBack(id, cb) {
  const user = {
    id: id,
    name: "User" + id,
    email: id + "@test.com",
  };
  cb(user);
}

findUserAndCallBack(1, function (user) {
  console.log("user:", user);
});

//결과 : user: {id: 1, name: "User1", email: "1@test.com"}
