import { createWebHistory, createRouter } from "vue-router";
import Welcome from "../components/Welcome.vue";
import MainPage from "../components/MainPage.vue";
import Community from "../components/community/Community.vue";
import CommunityDetail from "../components/community/CommunityDetail.vue";
import EditCommunity from "../components/community/EditCommunity.vue";
import Bookmark from "../components/member/Bookmark.vue";
import Config from "../components/member/Config.vue";
import Find from "../components/member/Find.vue";
import Login from "../components/member/Login.vue";
import MemberInfo from "../components/member/MemberInfo.vue";
import MemberPage from "../components/member/MemberPage.vue";
import RecentView from "../components/member/RecentView.vue";
import ResetPassword from "../components/member/ResetPassword.vue";
import Resign from "../components/member/Resign.vue";
import SignUp from "../components/member/SignUp.vue";
import EditPlan from "../components/plan/EditPlan.vue";
import PlanDetail from "../components/plan/PlanDetail.vue";
import DetailSearch from "../components/search/DetailSearch.vue";
import Search from "../components/search/Search.vue";
import EditSpot from "../components/spot/EditSpot.vue";
import SpotDetail from "../components/spot/SpotDetail.vue";
import TourDetail from "../components/tour/TourDetail.vue";
import TourPage from "../components/tour/TourPage.vue";
import ErrorPage from "../components/ErrorPage.vue";

const routes = [
  {
    path: "/welcome",
    component: Welcome,
    meta: { isLogin: false }
  },
  {
    path: "/",
    component: MainPage,
    meta: { isLogin: true }
  },
  //community
  {
    path: "/community/page=:page",
    component: Community,
    meta: { isLogin: true }
  },
  {
    path: "/community",
    component: Community,
    meta: { isLogin: true }
  },
  {
    path: "/community/:id",
    component: CommunityDetail,
    meta: { isLogin: true }
  },
  {
    path: "/community/detail",
    component: CommunityDetail,
    meta: { isLogin: true }
  },
  {
    path: "/community/:mode/:id?",
    component: EditCommunity,
    meta: { isLogin: true }
  },
  //member
  {
    path: "/bookmark/:id?",
    component: Bookmark,
    meta: { isLogin: true }
  },
  {
    path: "/config",
    component: Config,
    meta: { isLogin: true }
  },
  {
    path: "/find",
    component: Find,
    meta: { isLogin: false }
  },
  {
    path: "/login",
    component: Login,
    meta: { isLogin: false }
  },
  {
    path: "/member-info",
    component: MemberInfo,
    meta: { isLogin: true }
  },
  {
    path: "/member-page/:id?",
    component: MemberPage,
    meta: { isLogin: true }
  },
  {
    path: "/recent-view",
    component: RecentView,
    meta: { isLogin: true }
  },
  {
    path: "/reset-pw/:id?",
    component: ResetPassword,
    meta: { isLogin: false }
  },
  {
    path: "/Resign",
    component: Resign,
    meta: { isLogin: true }
  },
  {
    path: "/sign-up",
    component: SignUp,
    meta: { isLogin: false }
  },
  //plan
  {
    path: "/:mode/plan/:id?",
    component: EditPlan,
    meta: { isLogin: true }
  },
  {
    path: "/plan/:id",
    component: PlanDetail,
    meta: { isLogin: true }
  },
  //search
  {
    path: "/search",
    component: Search,
    meta: { isLogin: true }
  },
  {
    path: "/search/detail",
    component: DetailSearch,
    meta: { isLogin: true }
  },
  //spot
  {
    path: "/:mode/spot/:id?",
    component: EditSpot,
    meta: { isLogin: true }
  },
  {
    path: "/spot/:id",
    component: SpotDetail,
    meta: { isLogin: true }
  },
  //tour
  {
    path: "/tour/loc/:locCategory?",
    component: TourPage,
    meta: { isLogin: true }
  },
  {
    path: "/tour/:id",
    component: TourDetail,
    meta: { isLogin: true }
  },
  {
    path: "/:pathMatch(.*)",
    component: ErrorPage,
    meta: { isLogin: false }
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

//로그인 안하면 welcome 페이지로 리다이렉트
router.beforeEach((to, from, next) => {
  if(to.meta.isLogin && !document.cookie.includes("login")) next({path: '/welcome'});
  next();
})

export { router };
