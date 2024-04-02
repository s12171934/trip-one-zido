const { defineConfig } = require('@vue/cli-service')
module.exports = {
  devServer: {
    proxy: {
      "/api": {	
        target: "http://43.200.46.110:8080", // 프록시를 설정할 도메인
        changeOrigin: true,
      }, 
    }
  },
  lintOnSave: false,
}