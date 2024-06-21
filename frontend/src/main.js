import { createApp } from 'vue'
import App from './App.vue'
import { router } from './router/index.js'
import store from './store'

const app = createApp(App)

app.use(router)  // 라우터 사용
app.use(store)   // Vuex 스토어 사용
app.mount('#app')