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
import AlertModal from "@/components/util/modal/AlertModal.vue";

const routes = [
  {
    path: "/welcome",
    component: Welcome
  },
  {
    path: "/",
    component: MainPage
  },
  //community
  {
    path: "/community/page=:page",
    component: Community
  },
  {
    path: "/community",
    component: Community
  },
  {
    path: "/community/:id",
    component: CommunityDetail
  },
  {
    path: "/community/:mode/:id?",
    component: EditCommunity
  },
  //member
  {
    path: "/bookmark/:id?",
    component: Bookmark
  },
  {
    path: "/config",
    component: Config
  },
  {
    path: "/find",
    component: Find
  },
  {
    path: "/login",
    component: Login
  },
  {
    path: "/member-info",
    component: MemberInfo
  },
  {
    path: "/:id",
    component: MemberPage
  },
  {
    path: "/recent-view",
    component: RecentView
  },
  {
    path: "/reset-pw",
    component: ResetPassword
  },
  {
    path: "/Resign",
    component: Resign
  },
  {
    path: "/sign-up",
    component: SignUp
  },
  //plan
  {
    path: "/plan/:mode/:id?",
    component: EditPlan
  },
  {
    path: "/plan/:id",
    component: PlanDetail
  },
  //search
  {
    path: "/search",
    component: Search
  },
  {
    path: "/search/detail",
    component: DetailSearch
  },
  //spot
  {
    path: "/spot/:mode/:id?",
    component: EditSpot
  },
  {
    path: "/spot/:id",
    component: SpotDetail
  },
  //tour
  {
    path: "/tour/loc/:locCategory?",
    component: TourPage
  },
  {
    path: "/tour/:id",
    component: TourDetail
  },
  {
    path: "/modal",
    component: AlertModal
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

//로그인 안하면 welcome 페이지로 리다이렉트
// router.beforeEach((to, from, next) => {
//   if(to.fullPath != '/welcome') next({path: '/welcome'});
//   next();
// })

export { router };
