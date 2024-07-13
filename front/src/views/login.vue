<template>
  <body id="login-page">
  <el-form class="login-container" label-position="left" label-width="0px">
    <h3 class="login_title">系统登录</h3>
    <el-form-item>
      <el-input
          type="text"
          v-model="loginForm.loginName"
          auto-complete="off"
          placeholder="账号"
      ></el-input>
    </el-form-item>
    <el-form-item>
      <el-input
          type="password"
          v-model="loginForm.password"
          auto-complete="off"
          placeholder="密码"
      ></el-input>
    </el-form-item>
    <el-form-item style="width: 100%">
      <el-button
          type="primary"
          style="width: 100%;  border: none"
          @click="login"
      >登录</el-button>
      <!-- Register button -->
      <el-button
          type="default"
          style="width: 100%; margin-top: 10px; border: none"
          @click="navigateToRegister"
      >注册</el-button>
    </el-form-item>
  </el-form>
  </body>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      loginForm: {
        loginName: "",
        password: "",
      },
      responseResult: [],
    };
  },
  methods: {
    login () {
      console.log("login")
      this.$axios
          .post('http://localhost:5000/login', {
            loginName: this.loginForm.loginName,
            password: this.loginForm.password
          })
          .then(successResponse => {
            if (successResponse.data.code === 200) {
              this.$router.replace({path: '/home'})
            } else {
              // 根据后端具体返回的错误码显示不同的错误信息
              this.$message.error(successResponse.data.message || "登录失败，请注册");
            }
          })
    },
    navigateToRegister() {
      this.$router.push({ path: '/register' }); // Ensure your router is configured for this path
    }
  },

};
</script>

<style scoped>
#login-page {
  display: flex;
  align-items: center;
  justify-content: center;
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: url("../assets/img/bg3.jpg") no-repeat center center;
  background-size: cover;
}
.login-container {
  border-radius: 15px;
  background-clip: padding-box;
  padding: 100px 100px 90px 90px;  /* 调整了内边距以更好地适应内容 */
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
  width: 500px;  /* 设置一个固定宽度或者根据实际需求调整 */
}
.login_title {
  margin-bottom: 40px;
  text-align: center;
  color: #505458;
  font-size: 30px;  /* 增大标题文字大小 */
}
.login-container input {
  height: 50px;  /* 增大输入框高度 */
  font-size: 18px;  /* 增大文字大小 */
  padding: 0 10px;  /* 调整文字内边距 */
  width: 100%;  /* 使输入框宽度填充其容器 */
  box-sizing: border-box;  /* 添加 box-sizing 以包含内边距和边框在内的宽度计算 */
}

.login-container button {
  height: 40px;  /* 增大按钮高度 */
  font-size: 18px;  /* 增大按钮文字大小 */
  width: 100%;  /* 让按钮宽度填满容器 */
}
</style>


