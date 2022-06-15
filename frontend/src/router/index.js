import Vue from "vue";
import VueRouter from "vue-router";
// import HomeView from "../views/HomeView.vue";
import store from "@/store/index.js";

Vue.use(VueRouter);

// https://router.vuejs.org/kr/guide/advanced/navigation-guards.html
const onlyAuthUser = async (to, from, next) => {
  // console.log(store);
  const checkUserInfo = store.getters["userStore/checkUserInfo"];
  const getUserInfo = store._actions["userStore/getUserInfo"];
  let token = sessionStorage.getItem("access-token");
  if (checkUserInfo == null && token) {
    await getUserInfo(token);
  }
  if (checkUserInfo === null) {
    alert("로그인이 필요한 페이지입니다..");
    next({ name: "signIn" });
    // router.push({ name: "signIn" });
  } else {
    // console.log("로그인 했다.");
    next();
  }
};

const routes = [
  {
    path: "/",
    name: "home",
    redirect: "/overview",
    component: () => import("@/layout/DashboardLayout"),
    children: [
      {
        path: "overview",
        name: "overview",
        component: () => import("@/pages/OverviewPage"),
      },
    ],
  },
  {
    path: "/user",
    name: "user",
    component: () => import("@/layout/DashboardLayout"),
    children: [
      {
        path: "signin",
        name: "signIn",
        component: () => import("@/components/user/UserLogin.vue"),
      },
      {
        path: "signup",
        name: "signUp",
        component: () => import("@/components/user/UserRegister.vue"),
      },
      {
        path: "mypage",
        name: "mypage",
        beforeEnter: onlyAuthUser,
        component: () => import("@/pages/UserProfile"),
      },
    ],
  },
  {
    path: "/board",
    name: "board",
    component: () => import("@/layout/DashboardLayout"),
    redirect: "/board/list",
    children: [
      {
        path: "list",
        name: "boardList",
        component: () => import("@/components/board/BoardList.vue"),
      },
      {
        path: "write",
        name: "boardRegister",
        component: () => import("@/components/board/BoardRegister.vue"),
      },
      {
        path: "detail/:announcementNo",
        name: "boardDetail",
        component: () => import("@/components/board/BoardDetail.vue"),
      },
      {
        path: "modify/:announcementNo",
        name: "boardModify",
        component: () => import("@/components/board/BoardModify.vue"),
      },
      {
        path: "delete/:announcementNo",
        name: "boardDelete",
        component: () => import("@/components/board/BoardDelete.vue"),
      },
    ],
  },
  {
    path: "/house",
    name: "house",
    component: () => import("@/layout/DashboardLayout"),
    redirect: "/house/list",
    beforeEnter: onlyAuthUser,
    children: [
      {
        path: "list",
        name: "houseList",
        component: () => import("@/components/map/MapMain"),
      },
    ],
  },
  {
    path: "/qna",
    name: "qna",
    component: () => import("@/layout/DashboardLayout"),
    redirect: "/qna/list",
    children: [
      {
        path: "list",
        name: "qnaList",
        component: () => import("@/components/qna/BoardList.vue"),
      },
      {
        path: "write",
        name: "qnaRegister",
        component: () => import("@/components/qna/BoardRegister.vue"),
      },
      {
        path: "write/:articleno",
        name: "replyRegister",
        component: () => import("@/components/qna/ReplyRegister.vue"),
      },
      {
        path: "detail/:articleno",
        name: "qnaDetail",
        component: () => import("@/components/qna/QnaDetail.vue"),
      },
      {
        path: "modify/:articleno",
        name: "qnaModify",
        component: () => import("@/components/qna/BoardModify.vue"),
      },
      {
        path: "delete/:articleno",
        name: "qnaDelete",
        component: () => import("@/components/qna/BoardDelete.vue"),
      },
    ],
  },
  // {
  //   path: "/map",
  //   name: "map",
  //   component: () => import("@/layout/DashboardLayout"),
  //   redirect: "/map/kakao",
  //   children: [
  //     {
  //       path: "kakao",
  //       name: "kakaoMap",
  //       component: () => import("@/components/map/MapMain"),
  //     },
  //   ],
  // },
  {
    path: "*",
    name: "NotFound",
    component: () => import("@/pages/NotFoundPage.vue"),
  },
];

export default routes;
