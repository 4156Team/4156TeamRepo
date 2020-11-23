// mock.js

// 引入mockjs
const Mock = require("mockjs");
// 获取 mock.Random 对象
const Random = Mock.Random;
// mock游乐设施数据
const produceFacilityData = function() {
  let newsList = [];
  for (let i = 0; i < 20; i++) {
    let newNewsObject = {
      facility_name: Random.word(),
      facility_status: Mock.mock({
        "boolean|1": true,
      }), // 
      facility_introduction: Random.paragraph(), // Random.cparagraph(min, max) 随机生成一个中文段落，段落里的句子个数默认3-7个
      facility_open_time: Mock.mock('@time("H:m:s")'), // Random.date()指示生成的日期字符串的格式,默认为yyyy-MM-dd；
      facility_close_time: Mock.mock('@time("H:m:s")'),
      queue_status: Mock.mock({
        "number|1-100": 100,
      }),
    };
    newsList.push(newNewsObject);
  }
  return newsList;
};
//mock event 数据
const produceEventData = function() {
  let newsList = [];
  for (let i = 0; i < 20; i++) {
    let newEventObject = {
      Event_name: Random.word(), //名字
      Event_id: i,  //index
      Event_introduction: Random.paragraph(), // intro
      Event_date: Mock.mock('@date("yyyy-MM-dd")'), //时间 2016-09-22
      Event_start_time: Mock.mock('@time("H:m:s")'), // 时间 4:3:24
      Event_end_time: Mock.mock('@time("H:m:s")'), //时间 18:23:8
      Event_location: Random.name(), //地点
      Event_image: Random.dataImage("200x100"), //图片url
      Event_remain_positions: Mock.mock({
        "number|1-100": 100,
      }), // 数字
    };
    newsList.push(newEventObject);
  }
  return newsList;
};

const producelSignleData = function() {
  let newsList = [];
  let newEventObject = {
    Event_name: Random.word(),
    Event_id: 2,
    Event_introduction: Random.paragraph(), // Random.cparagraph(min, max) 随机生成一个中文段落，段落里的句子个数默认3-7个
    Event_date: Mock.mock('@date("yyyy-MM-dd")'),
    Event_start_time: Mock.mock('@time("H:m:s")'), // Random.date()指示生成的日期字符串的格式,默认为yyyy-MM-dd；
    Event_end_time: Mock.mock('@time("H:m:s")'),
    Event_location: Random.name(),
    Event_image: Random.dataImage("200x100"),
    Event_remain_positions: Mock.mock({
      "number|1-100": 100,
    }),
  };
  newsList.push(newEventObject);
  return newsList;
};

Mock.mock("/mock/facility", produceFacilityData); // 后面讲这个api的使用细节
Mock.mock("/mock/event", produceEventData);
Mock.mock("/mock/event/2", producelSignleData);
// 请求该url，就可以返回newsList
