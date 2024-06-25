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
      },
    },
    // port : 8080
  },
};
