import { createRouter, createWebHistory } from 'vue-router';
import MainView from '../views/MainView';
import ApplyListView from '../views/fo/enterprise/apply/ApplyListView';
import ApplyDetailView from '../views/fo/enterprise/apply/ApplyDetailView';
import ResumeInsertView from '../views/fo/enterprise/resume/ResumeInsertView';
import ResumeListView from '../views/fo/enterprise/resume/ResumeListView';
import ResumeDetailView from '../views/fo/enterprise/resume/ResumeDetailView';

const routes = [
  { path: '/', component: MainView },

  // 송영태
  { path: '/applys/apply-list', name: 'applyListView', component: ApplyListView },
  { path: '/applys/apply-detail', name: 'applyDetailView', component: ApplyDetailView },

  { path: '/resume/resume-detail', component: ResumeDetailView },
  { path: '/resume/resume-insert', component: ResumeInsertView },
  { path: '/resume/resume-list', component: ResumeListView },
];
//createWebHistory 모드로 바꾸고 HTML5 로 해결해야함 createWebHashHistory
const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
