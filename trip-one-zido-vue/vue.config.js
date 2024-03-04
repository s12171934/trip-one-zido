const { defineConfig } = require('@vue/cli-service')
module.exports = {
  devServer: {
    proxy: {
      "/api": {	
        target: "http://localhost:8080", // 프록시를 설정할 도메인
        changeOrigin: true,
      }, 
    }
  },
  lintOnSave: false,
}