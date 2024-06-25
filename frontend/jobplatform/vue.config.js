// const { defineConfig } = require('@vue/cli-service')
// module.exports = defineConfig({
//   transpileDependencies: true
// })

module.exports = {
  devServer: {
    proxy: {
      "/": {
        target: "http://localhost:80",
        ws: false,
        logLevel: "debug",
        //changeOrigin: true,
      },
    },
    // port : 8080
  },
};
