const { defineConfig } = require('@vue/cli-service')
module.exports = {
  devServer: {
    proxy: {
      "/api": {	
        target: "http://13.60.3.28:8080", // 프록시를 설정할 도메인
        changeOrigin: true,
      }, 
    }
  },
  lintOnSave: false,
}