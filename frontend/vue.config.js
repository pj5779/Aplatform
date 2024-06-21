module.exports = {
  devServer: {
      proxy: {
          '/': {
            target: 'http://localhost:80',
            ws: false,
            logLevel : 'debug'
          },
          "/oauth2.0" : {
            target : 'https://nid.naver.com/',
            changeOrigin : true,
            logLevel : 'debug'
          },
          "/v1" : {
            target : 'https://openapi.naver.com/',
            changeOrigin : true,
            logLevel : 'debug'
          },
      },
      // port : 8080
  }
}
