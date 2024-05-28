<template>
  <div class="container mt-3"> 
    <h1 class="display-1 text-center">사용자 목록</h1>
    <div class="btn-group">
      <a href="Create.html" class="btn btn-primary">사용자 추가</a>
    </div>
    <table class="table table-hover mt-3">
      <thead class="table-dark">
        <tr>
          <th>이름</th>
          <th>이메일</th>
          <th>가입날짜</th>
        </tr>
      </thead>
      <tbody>
        <tr class="cursor-pointer" v-for="item in result" :key="item.no" v-on:click="$event => href(item)">
          <td>{{ item.name }}</td>
          <td>{{ item.email }}</td>
          <td>{{ item.regDate }}</td>
        </tr> 
      </tbody>
    </table>
    <nav-view/>
  </div>
</template>

<script>
import axios from 'axios' 
import store from '@/store'
import NavView from '../components/NavView.vue' 

export default {
  components: { NavView},
  name: 'ListView',
  data() {
    return {
      result: []
    }
  },
  created() {
    this.getData()
  },
  methods: {
    getData() {
      axios
        .post('http://localhost:80/findAll') // 포트 번호 수정
        .then((response) => {
          this.result = response.data // 서버로부터 전달된 데이터 전체를 할당
        })
        .catch((error) => {
          console.error(error)
        })
    },
    href(item){
        console.log(item)
        store.commit('setUser',item)
        this.$router.push({ name : 'SelectView' })
    }
  }
}
</script>

<style scoped>
.cursor-pointer {
  cursor: pointer;
}
</style>
