<template>
  <div class="app" style="height: 100vh; width: 100%; margin: 0 auto;">
    <div :class="index > 0 ? 'info' : ''" v-for="(item, index) in attendeeList" :key="index">
      <el-descriptions class="margin-top"
                       :title="item.nickname"
                       :column="3"
                       size="small"
                       border>

        <!-- 用户名 -->
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-user"></i>
            用户名
          </template>
          {{ item.username }}
        </el-descriptions-item>

        <!-- 手机号码 -->
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-mobile-phone"></i>
            手机号
          </template>
          {{ item.phone }}
        </el-descriptions-item>

        <!-- 性别 -->
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-s-check"></i>
            性别
          </template>
          {{ item.sex === '0' ? '男' : '女' }}
        </el-descriptions-item>

        <!-- 注册时间 -->
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-tickets"></i>
            注册时间
          </template>
          {{ item.gmtCreate }}   <!--这里原来是.createTime-->
        </el-descriptions-item>

        <!-- 个人描述 -->
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-edit-outline"></i>
            个人描述
          </template>
          {{ item.describe }}
        </el-descriptions-item>
      </el-descriptions>
    </div>

    <!-- 分页 -->
    <div class="block"
         style="text-align:center;margin-top:10px;">
      <el-pagination background
                     @current-change="handleCurrentChange"
                     :current-page.sync="pageParam.index"
                     :page-size="pageParam.size"
                     :total="pageParam.total"
                     layout="prev, pager, next, jumper">
      </el-pagination>
    </div>
  </div>
</template>

<script>
// 引入接口
import attendee from '../../api/attendee.js'

export default {
  data() {
    return {
      // 用户信息列表
      attendeeList: [],
      // 分页
      pageParam: {
        index: 1,
        size: 4,
        total: 0,
      },
    }
  },
  methods: {
    // 切页
    handleCurrentChange(index) {
      this.pageParam.index = index
      this.getAttendeeList()
    },
    getAttendeeList() {
      // 发送请求，获取用户信息列表
      attendee.findAttendeeList(this.pageParam.index, this.pageParam.size).then((res) => {
        // 赋值
        this.attendeeList = res.data.userList
        // 总数赋值
        this.pageParam.total = res.data.total
      })
    }
  },
  created() {
    this.getAttendeeList()
  },
}
</script>

<style>
.info {
  margin-top: 20px;
}
/* 如果使用Flexbox，确保允许子元素扩展 */
.form {
  display: flex;
  flex-grow: 1;
}
.el-container.is-vertical {
  min-width: 1000px;
  flex:1;
}
.app {
   height: 100vh;
   width: 100%;
   margin: 0 auto;
   background-image: url('../../assets/img/attendeeList.png');
   background-size: cover;
   background-position: center;
}
</style>