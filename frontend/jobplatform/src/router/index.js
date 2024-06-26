import { createRouter, createWebHashHistory } from "vue-router";
import MainView from "../views/MainView";
import ApplyListView from "../views/fo/enterprise/apply/ApplyListView";
import ApplyDetailView from "../views/fo/enterprise/apply/ApplyDetailView";
import ApplyInsertView from "../views/fo/enterprise/apply/ApplyInsertView";

const routes = [
  { path: "/", component: MainView },

  // 송영태
  { path: "/applys/apply-list", name: "applyListView", component: ApplyListView },
  { path: "/applys/applyDetail", name: "applyDetailView", component: ApplyDetailView },
  { path: "/applys/applyInsert", component: ApplyInsertView },
];
//createWebHistory 모드로 바꾸고 HTML5 로 해결해야함 createWebHashHistory
const router = createRouter({
  history: createWebHashHistory(process.env.BASE_URL),
  routes,
});

export default router;
