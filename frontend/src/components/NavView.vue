<template>
  <div class="main-content"> 
    <!-- Sidebar -->
    <nav class="col-sm-3 sidenav"> 
      <ul class="nav nav-pills nav-stacked">
        <li class="active"><a href="#section1">Home</a></li>
        <li><a href="#section2">Login</a></li>
        <li><a href="#section3">Sign up</a></li>
        <li><a href="#section4">Manage</a></li>
      </ul>
    </nav> 
    <div class="page">
      <!-- Main content goes here -->
    </div>
  </div>
</template>

<script>
export default {
  name: 'NavView'
}
</script>

<style scoped>
.sidenav {
  background-color: #f1f1f1;
  position: fixed;
  width: 200px;
  height: 100%;
  right: 0;
  top: 100px;
}
.nav-pills {
  display: flex;
  flex-direction: column;
  padding-left: 0;
  margin-bottom: 0;
}
.nav-pills > li {
  width: 100%;
}
.nav-pills > li > a {
  display: block;
  width: 100%;
  padding: 10px 15px;
  text-align: left;
  color: #000;
  text-decoration: none;
}
.nav-pills > li.active > a {
  background-color: #007bff;
  color: white;
}
.main-content {
  display: flex;
  flex-direction: row-reverse;
  margin-top: 50px;
}
.page {
  margin-right: 200px;
  flex: 1;
}
</style>
