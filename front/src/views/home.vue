<template>
  <div class="app" style="width: 100%; height: 100vh; margin: 0 auto;">
    <el-container style="height: 100vh; width: 100%; margin: 0 auto;">
      <!-- 左侧导航栏 -->
      <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
<!--        <el-menu :default-openeds="['0']">-->
<!--          <el-sub-menu v-for="(item, index) in routes" :key="index" :index="String(index)">-->
<!--            <template #title><i :class="item.class"></i>{{ item.name }}</template>-->
<!--            <el-menu-item v-for="(item1, index1) in item.children" :key="String(index1)" :index="item1.path"-->

<!--                          :class="isActive(item1.path) ? 'is-active' : ''"><router-link :to="item1.path">{{ item1.name }}</router-link>-->

<!--            </el-menu-item>-->
<!--          </el-sub-menu>-->
<!--        </el-menu>-->

        <el-menu :default-openeds="['0']">
          <el-sub-menu v-for="(item, index) in routes" :key="index" :index="String(index)">
            <template #title>
              <i :class="item.class"></i>
              {{ item.name }}
            </template>
            <el-menu-item-group>
              <el-menu-item v-for="(item1, index1) in item.children" :key="String(index1)" :index="item1.path">
                <router-link :to="item1.path"
                             :class="item1.name.includes('用户') ? 'red-text' : item1.name.includes('租户') ? 'blue-text' : ''">
                  {{ item1.name }}
                </router-link>
              </el-menu-item>
            </el-menu-item-group>
          </el-sub-menu>
        </el-menu>


      </el-aside>



      <!-- 顶部右侧工具栏 -->
      <el-container>
        <el-header style="text-align: right; font-size: 12px;background-color: #deebff">
          <el-dropdown>
            <i class="el-icon-setting" style="margin-right: 15px"></i>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item>功能一</el-dropdown-item>
                <el-dropdown-item>功能二</el-dropdown-item>
                <el-dropdown-item>功能三</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
<!--          <span>管理员</span>-->
          <router-link to="/user">
            <el-button type="primary" round>管理员</el-button>
          </router-link>
        </el-header>

        <!-- 中间区域内容页面的切换 -->
        <el-main>
          <router-view />
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import { useRoute, useRouter } from 'vue-router'
import { computed } from 'vue'
export default {
  setup() {
    const router = useRouter();
    const route = useRoute();
    // const routes = router.options.routes;

    const routes = computed(() => {
      // 过滤出 'home' 及其子菜单项，其他如 'Login' 和 'Register' 将不显示在导航栏
      // return router.options.routes.filter(r => r.name === 'home');
      let filteredRoutes = router.options.routes.filter(r => r.name === 'home');
      if (filteredRoutes.length > 0) {
        filteredRoutes[0].name = '用户与租户管理';  // 改变显示的名称
      }
      return filteredRoutes;
    });

    function isActive(path) {

      return route.path === path;
    }

    return { routes, isActive };
  }
}
</script>
<style>
/*.el-menu-item a {*/
/*  color: black; !* 所有非活动链接为黑色 *!*/
/*  text-decoration: none; !* 去除下划线 *!*/
/*}*/
.red-text {
  color: #619fff;
}

.blue-text {
  color: #60b777;
}
.app {
  height: 100vh;
  width: 100%;
  margin: 0 auto;
  background-image: url('../assets/img/attendeeList.png');
  background-size: cover;
  background-position: center;
}
</style>