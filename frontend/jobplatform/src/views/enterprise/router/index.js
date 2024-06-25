import { createRouter,createWebHistory} from 'vue-router'


//연결할 컴포넌트 import views폴더 컴포넌트들 사용
import MainView from '../views/MainView'
import GeneralBoardView from '../views/board/GeneralBoardView'
import FeedbackBoardView from '../views/board/FeedbackBoardView'
import QnABoardView from '../views/board/QnABoardView'
import MemberLogin from '../components/member/MemberLogin'
import MemberRegisterAndUpdate from '../components/member/MemberRegisterAndUpdate'
import MemberFindIdAndPassword from '../components/member/MemberFindIdAndPassword'
import MemberLoginNCallback from '@/components/member/MemberLoginNCallback'
import InsertView from '../views/board/InsertView'
import GeneralBoardDetailView from '../views/board/GeneralBoardDetailView'
import UpdateView from '../views/board/UpdateView'
import MemberMyPage from '../components/member/MemberMyPage.vue' 
import ApplyListView from '../views/apply/ApplyListView'
import ApplyDetailView from '../views/apply/ApplyDetailView'
import ApplyInsertView from '../views/apply/ApplyInsertView'

// 라우터 설계
const routes = [
    { path : '/', component:MainView},
    { path : '/board/list/general', component:GeneralBoardView},
    { path : '/board/list/feedback', component:FeedbackBoardView},
    { path : '/board/list/qna', component:QnABoardView},
    { path : '/member/login', component:MemberLogin},
    { path : '/member/register', component:MemberRegisterAndUpdate},
    { path : '/member/find', component:MemberFindIdAndPassword},
    { path : '/member/loginNCallback', component:MemberLoginNCallback},
    { path : '/board/insert/:brdCtgryCd', component:InsertView},
    { path : '/board/detail/:brdSq', name: 'GeneralBoardDetailView', component:GeneralBoardDetailView},
    { path : '/board/update/:brdSq', name: 'UpdateView', component:UpdateView},
    { path : '/member/mypage', component:MemberMyPage},

         // 송영태
  { path: "/applys/apply-list", component: ApplyListView },
  { path: "/applys/applyDetail", component: ApplyDetailView },
  { path: "/applys/applyInsert", component: ApplyInsertView },
]

// 라우터 생성
const router = createRouter ({
    history : createWebHistory(),
    routes
})

// 라우터 추출 main.js에서 import
export {router}