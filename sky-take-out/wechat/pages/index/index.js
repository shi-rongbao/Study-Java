// index.js
Page({
    data:{
        msg:"Fuck You",
        nickName: '',
        url:'',
        code:''
    },
    // 获取微信用户的头像和昵称
    getUserInfo(){
        wx.getUserProfile({
          desc: '获取用户信息',
          success: (res) => {
              console.log(res.userInfo)
              this.setData({
                  nickName: res.userInfo.nickName,
                  url: res.userInfo.avatarUrl
              })
          }
        })
    },

    // 微信登录，获取微信用户的授权码
    wxLogin(){
        wx.login({
          success: (res) => {
            console.log(res.code)
            this.setData({
                code: res.code
            })
          },
        })
    }

})
