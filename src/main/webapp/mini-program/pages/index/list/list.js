// pages/index/list/list.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    postsList: [], //总的活动
    postsShowSwiperList: [], //轮播图显示的活动
    currentPage: 0, //要跳过查询的页数
    limitPage: 3,//首先显示3条数据（之后加载时都增加3条数据，直到再次加载不够3条）
    isEmpty: false, //当前查询出来的数据是否为空
    totalCount: 0, //总活动数量
    endPage: 0, //最后一页加载多少条
    totalPage: 0, //总页数
    curIndex: 0,
    windowHeight1: 0,
    windowWidth1: 0,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var self = this;
    var molist = new Array();
    wx.request({
      url: 'https://api.it120.cc/tz/banner/list',
      data: {
        key: 'mallName'
      },
      success: function (res) {
        res = {
          "code": 1,
          "message": "success",
          "data": [
            {
              "id": "1",
              "publisher": "1",
              "title": "title",
              "content": "content",
              "acttype": "acttype",
              "isShow": "isShow",
              "endtime": "endtime",
              "address": "address",
              "addressdetail": "addressdetail",
              "likenum": "likenum",
              "liker": "liker",
              "commentnum": "commentnum",
              "publisher": "publisherName",
              "publisher": "publisher"
            }
          ]
        }
        var results = res.data;
        for (var i = 0; i < results.length; i++) {
          var publisherId = results[i].publisher;
          var title = results[i].title;
          var content = results[i].content;
          var acttype = results[i].acttype;
          var isShow = results[i].isShow;
          var endtime = results[i].endtime;
          var address = results[i].address;
          var addressdetail = results[i].addressdetail;
          var peoplenum = results[i].peoplenum;
          var likenum = results[i].likenum;
          var liker = results[i].liker;
          var isLike = 0;
          var commentnum = results[i].commentnum;

          var id = results[i].id;
          var createdAt = "1";//results[i].createdAt;
          var pubtime = "1";//results[i].createdAt;
          var _url
          var actpic = "";//results[i].createdAt;
          if (actpic) {
            _url = results[i].actpic._url;
          } else {
            _url = "image/1.jpg";
          }
          var publisherName = results[i].publisher.nickname;
          var publisherPic = results[i].publisher.userPic;
          var jsonA;
          jsonA = {
            "title": title || '',
            "content": content || '',
            "acttype": acttype || '',
            "isShow": isShow,
            "endtime": endtime || '',
            "address": address || '',
            "addressdetail": addressdetail || '',
            "peoplenum": peoplenum || '',
            "id": id || '',
            "publisherPic": publisherPic || '',
            "publisherName": publisherName || '',
            "publisherId": publisherId || '',
            "pubtime": pubtime || '',
            "actPic": _url || '',
            "likenum": likenum,
            "commentnum": commentnum,
            "is_liked": isLike || ''
          }
          molist.push(jsonA);
        }
        self.setData({
          postsShowSwiperList: molist
        })
        self.onSetData(molist, self.data.currentPage);
        //self.fetchPostsData(self.data); //加载首页信息
      },
      error: function (error) {
        console.log(error)
      }
    })
  },
  //数据存储
  onSetData: function (data) {
    console.log(data.length);
    let page = this.data.currentPage + 1;
    //设置数据
    data = data || [];
    this.setData({
      postsList: page === 1 || page === undefined ? data : this.data.postsList.concat(data),
    });
    console.log(this.data.postsList, page);
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
  
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
  
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
  
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
  
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
  
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
  
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
  
  }
})