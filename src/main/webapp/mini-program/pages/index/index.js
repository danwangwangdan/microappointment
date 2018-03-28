//index.js
//获取应用实例
const app = getApp()

Page({
  data: {
    motto: 'Login',
    userInfo: {},
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    indicatorDots: true,
    autoplay: true,
    interval: 3000,
    duration: 1000,
    loadingHidden: false, // loading
    userInfo: {},
    swiperCurrent: 0,
    selectCurrent: 0,
    categories: [],
    activeCategoryId: 0,
    goods: [],
    scrollTop: "0",
    loadingMoreHidden: true,

    hasNoCoupons: true,
    coupons: [],
    searchInput: '',
  },
  //事件处理函数
  bindViewTap: function () {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },
  onLoad: function () {
    if (app.globalData.userInfo) {
      this.setData({
        userInfo: app.globalData.userInfo,
        hasUserInfo: true
      })
    } else if (this.data.canIUse) {
      // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
      // 所以此处加入 callback 以防止这种情况
      app.userInfoReadyCallback = res => {
        this.setData({
          userInfo: res.userInfo,
          hasUserInfo: true
        })
      }
    } else {
      // 在没有 open-type=getUserInfo 版本的兼容处理
      wx.getUserInfo({
        success: res => {
          app.globalData.userInfo = res.userInfo
          this.setData({
            userInfo: res.userInfo,
            hasUserInfo: true
          })
        }
      })
    }
  },
  getUserInfo: function (e) {
    console.log(e)
    app.globalData.userInfo = e.detail.userInfo
    this.setData({
      userInfo: e.detail.userInfo,
      hasUserInfo: true
    })
  },

  tabClick: function (e) {
    this.setData({
      activeCategoryId: e.currentTarget.id
    });
    this.getGoodsList(this.data.activeCategoryId);
  },
  //事件处理函数
  swiperchange: function (e) {
    //console.log(e.detail.current)
    this.setData({
      swiperCurrent: e.detail.current
    })
  },
  toDetailsTap: function (e) {
    wx.navigateTo({
      //url: "/pages/goods-details/index?id=" + e.currentTarget.dataset.id
      url: "/pages/index/list/list"
    })
  },
  tapBanner: function (e) {
    if (e.currentTarget.dataset.id != 0) {
      wx.navigateTo({
        url: "/pages/goods-details/index?id=" + e.currentTarget.dataset.id
      })
    }
  },
  bindTypeTap: function (e) {
    this.setData({
      selectCurrent: e.index
    })
  },
  scroll: function (e) {
    //  console.log(e) ;
    var that = this, scrollTop = that.data.scrollTop;
    that.setData({
      scrollTop: e.detail.scrollTop
    })
    // console.log('e.detail.scrollTop:'+e.detail.scrollTop) ;
    // console.log('scrollTop:'+scrollTop)
  },
  onLoad: function () {
    var that = this
    // wx.setNavigationBarTitle({
    //   title: wx.getStorageSync('mallName')
    // })
    /*
    //调用应用实例的方法获取全局数据
    app.getUserInfo(function(userInfo){
      //更新数据
      that.setData({
        userInfo:userInfo
      })
    })
    */
    wx.request({
      url: 'https://api.it120.cc/tz/banner/list',
      data: {
        key: 'mallName'
      },
      success: function (res) {
        if (res.data.code == 404) {
          wx.showModal({
            title: '提示',
            content: '请在后台添加 banner 轮播图片',
            showCancel: false
          })
        } else {
          that.setData({
            banners: res.data.data
          });
        }
      }
    })
    // wx.request({
    //   url: 'https://api.it120.cc/tz/shop/goods/category/all',
    //   success: function (res) {
    //     res = {
    //       "data": {
    //         "code": 0,
    //         "data": [
    //           {
    //             "dateAdd": "2017-09-12 11:07:32",
    //             "icon": "",
    //             "id": 1872,
    //             "isUse": true,
    //             "key": "1",
    //             "level": 1,
    //             "name": "手游",
    //             "paixu": 0,
    //             "pid": 0,
    //             "type": "",
    //             "userId": 951
    //           },
    //           {
    //             "dateAdd": "2017-09-12 11:07:32",
    //             "icon": "",
    //             "id": 1873,
    //             "isUse": true,
    //             "key": "2",
    //             "level": 1,
    //             "name": "网游",
    //             "paixu": 0,
    //             "pid": 0,
    //             "type": "",
    //             "userId": 951
    //           },
    //           {
    //             "dateAdd": "2017-09-12 11:07:32",
    //             "icon": "",
    //             "id": 1874,
    //             "isUse": true,
    //             "key": "3",
    //             "level": 1,
    //             "name": "体育",
    //             "paixu": 0,
    //             "pid": 0,
    //             "type": "",
    //             "userId": 951
    //           },
    //           {
    //             "dateAdd": "2017-09-12 11:07:32",
    //             "icon": "",
    //             "id": 1875,
    //             "isUse": true,
    //             "key": "4",
    //             "level": 1,
    //             "name": "聊天",
    //             "paixu": 0,
    //             "pid": 0,
    //             "type": "",
    //             "userId": 951
    //           },
    //           {
    //             "dateAdd": "2017-09-12 11:07:32",
    //             "icon": "",
    //             "id": 1876,
    //             "isUse": true,
    //             "key": "5",
    //             "level": 1,
    //             "name": "生活娱乐",
    //             "paixu": 0,
    //             "pid": 0,
    //             "type": "",
    //             "userId": 951
    //           },
    //           {
    //             "dateAdd": "2017-09-12 11:07:32",
    //             "icon": "",
    //             "id": 1877,
    //             "isUse": true,
    //             "key": "6",
    //             "level": 1,
    //             "name": "美食",
    //             "paixu": 0,
    //             "pid": 0,
    //             "type": "",
    //             "userId": 951
    //           },
    //           {
    //             "dateAdd": "2017-09-12 11:07:32",
    //             "icon": "",
    //             "id": 1878,
    //             "isUse": true,
    //             "key": "7",
    //             "level": 1,
    //             "name": "旅游",
    //             "paixu": 0,
    //             "pid": 0,
    //             "type": "",
    //             "userId": 951
    //           },
    //         ],
    //         "msg": "success"
    //       },
    //       "statusCode": 200,
    //       "errMsg": "request:ok",
    //       "header": ""

    //     }
    //     var categories = [{ id: 0, name: "全部" }];
    //     if (res.data.code == 0) {
    //       for (var i = 0; i < res.data.data.length; i++) {
    //         categories.push(res.data.data[i]);
    //       }
    //     }
    //     that.setData({
    //       categories: categories,
    //       activeCategoryId: 0
    //     });
    //     that.getGoodsList(0);
    //   }
    // })
    wx.request({
      url: 'https://www.daliandaxue.cn/microappointment/activity/category',
      success: function (res) {
        console.log("first debug res",res.data.data);
        // var categories = [{ id: 0, name: "全部" }];
        // if (res.data.code == 0) {
        //   for (var i = 0; i < res.data.data.length; i++) {
        //     categories.push(res.data.data[i]);
        //   }
        // }
        that.setData({
          categories: res.data.data,
          activeCategoryId: 0
        });
        that.getGoodsList(0);
      }
    })
    that.getNotice();
  },
  getGoodsList: function (categoryId) {
    if (categoryId == 0) {
      categoryId = "";
    }
    console.log(categoryId)
    var that = this;
    wx.request({
      url: 'https://api.it120.cc/tz/shop/goods/list',
      data: {
        categoryId: categoryId,
        nameLike: that.data.searchInput
      },
      success: function (res) {
        res = {
          "data": {
            "code": 0,
            "data": [
              {
                "barCode": "",
                "categoryId": 2246,
                "characteristic": "",
                "commission": 5,
                "commissionType": 2,
                "dateAdd": "2017-10-30 10:51:08",
                "dateStart": "2017-10-30 10:44:34",
                "dateUpdate": "2018-01-22 07:14:14",
                "id": 6765,
                "logisticsId": 386,
                "minPrice": 5,
                "name": "王者荣耀",
                "numberFav": 0,
                "numberGoodReputation": 1,
                "numberOrders": 1,
                "originalPrice": 0,
                "paixu": 0,
                "pic": "/image/1.JPEG",
                "recommendStatus": 0,
                "recommendStatusStr": "普通",
                "shopId": 0,
                "status": 0,
                "statusStr": "上架",
                "stores": 700000,
                "userId": 951,
                "videoId": "c4c6e38eeb3a428e80f1a8b32c6de587",
                "views": 5294,
                "weight": 0
              },
              {
                "barCode": "",
                "categoryId": 2246,
                "characteristic": "",
                "commission": 5,
                "commissionType": 2,
                "dateAdd": "2017-10-30 10:51:08",
                "dateStart": "2017-10-30 10:44:34",
                "dateUpdate": "2018-01-22 07:14:14",
                "id": 6765,
                "logisticsId": 386,
                "minPrice": 5,
                "name": "王者荣耀",
                "numberFav": 0,
                "numberGoodReputation": 1,
                "numberOrders": 1,
                "originalPrice": 0,
                "paixu": 0,
                "pic": "/image/1.JPEG",
                "recommendStatus": 0,
                "recommendStatusStr": "普通",
                "shopId": 0,
                "status": 0,
                "statusStr": "上架",
                "stores": 700000,
                "userId": 951,
                "videoId": "c4c6e38eeb3a428e80f1a8b32c6de587",
                "views": 5294,
                "weight": 0
              },
              {
                "barCode": "",
                "categoryId": 2246,
                "characteristic": "",
                "commission": 5,
                "commissionType": 2,
                "dateAdd": "2017-10-30 10:51:08",
                "dateStart": "2017-10-30 10:44:34",
                "dateUpdate": "2018-01-22 07:14:14",
                "id": 6765,
                "logisticsId": 386,
                "minPrice": 5,
                "name": "王者荣耀",
                "numberFav": 0,
                "numberGoodReputation": 1,
                "numberOrders": 1,
                "originalPrice": 0,
                "paixu": 0,
                "pic": "/image/1.JPEG",
                "recommendStatus": 0,
                "recommendStatusStr": "普通",
                "shopId": 0,
                "status": 0,
                "statusStr": "上架",
                "stores": 700000,
                "userId": 951,
                "videoId": "c4c6e38eeb3a428e80f1a8b32c6de587",
                "views": 5294,
                "weight": 0
              }
            ],
            "msg": "success"
          },
          "statusCode": 200,
          "errMsg": "request:ok",
          "header": ""

        }
        console.log("debuging res");
        console.log(res);
        that.setData({
          goods: [],
          loadingMoreHidden: true
        });
        var goods = [];
        if (res.data.code != 0 || res.data.data.length == 0) {
          that.setData({
            loadingMoreHidden: false,
          });
          return;
        }
        for (var i = 0; i < res.data.data.length; i++) {
          goods.push(res.data.data[i]);
        }
        console.log("debuging goods");
        console.log(goods);
        that.setData({
          goods: goods,
        });
      }
    })
  },

  getNotice: function () {
    var that = this;
    wx.request({
      url: 'https://api.it120.cc/tz/notice/list',
      data: { pageSize: 5 },
      success: function (res) {
        res = {
          "data": {
            "code": 0,
            "data": {
              "totalRow": 1,
              "totalPage": 1,
              "dataList": [
                {
                  "dateAdd": "2017-09-16 17:20:43",
                  "id": 161,
                  "isShow": true,
                  "title": "欢迎新老用户积极预约活动！",
                  "userId": 951
                }
              ]
            },
            "msg": "success"
          },
          "statusCode": 200,
          "errMsg": "request:ok",
          "header": ""

        }
        if (res.data.code == 0) {
          that.setData({
            noticeList: res.data.data
          });
        }
      }
    })
  },
  listenerSearchInput: function (e) {
    this.setData({
      searchInput: e.detail.value
    })

  },
  toSearch: function () {
    this.getGoodsList(this.data.activeCategoryId);
  }
})
