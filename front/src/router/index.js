import { createRouter, createWebHistory } from 'vue-router'
import Login from '../views/login.vue'
import User from '../views/user.vue'
import Home from '../views/home.vue'
import Register from '../views/register.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path:'/',
      name: 'Login',
      component: Login
    },
    {
      path: '/register', // Add a route for the registration page
      name: 'Register',
      component: Register
    },
    {
      path: '/user', // Add a route for the registration page
      name: 'User',
      component: User
    },
    {
      path: '/home',
      name: 'home',
      component: Home,
      class: 'el-icon-s-custom',
      children: [
        {
          path: "/home",
          name: "用户数据",
          component: () => import("../views/home/attendeeList.vue")
        },
        {
          path: "/addAttendee",
          name: "添加用户",
          component: () => import("../views/home/addAttendee.vue")
        },
        {
          path: "/editAttendee/:id",
          name: "编辑用户",
          component: () => import("../views/home/addAttendee.vue"),
          hidden: true //隐式路由
        },
        {
          path: "/attendeeInfo",
          name: "用户信息",
          component: () => import("../views/home/attendeeInfoList.vue")
        },
        {
          path: "/tenant",
          name: "租户数据",
          component: () => import("../views/tenant/tenantList.vue")
        },
        {
          path: "/addTenant",
          name: "添加租户",
          component: () => import("../views/tenant/addTenant.vue")
        },
        {
          path: "/editTenant/:id",
          name: "编辑租户",
          component: () => import("../views/tenant/addTenant.vue"),
          hidden: true //隐式路由
        },
        {
          path: "/tenantInfo",
          name: "租户信息",
          component: () => import("../views/tenant/tenantInfoList.vue")
        }
      ],
    },
  ]
})

// const router = createRouter({
//   history: createWebHistory(process.env.BASE_URL),
//   routes
// });

export default router
