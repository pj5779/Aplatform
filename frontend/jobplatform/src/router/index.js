import { createRouter, createWebHistory } from "vue-router";
import MainView from "../views/MainView";
import ApplyListView from "../views/enterprise/apply/ApplyListView";
import ApplyDetailView from "../views/enterprise/apply/ApplyDetailView";
import ApplyInsertView from "../views/enterprise/apply/ApplyInsertView";

const routes = [
  { path: "/", component: MainView },

  // 송영태
  { path: "/applys/apply-list", component: ApplyListView },
  { path: "/applys/applyDetail", component: ApplyDetailView },
  { path: "/applys/applyInsert", component: ApplyInsertView },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
