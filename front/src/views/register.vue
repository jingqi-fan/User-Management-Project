<template>
  <body id="register-page">
  <el-form class="register-container" label-position="left" label-width="0px">
    <h3 class="register_title">用户注册</h3>
    <el-form-item>
      <el-input
          type="text"
          v-model="registerForm.username"
          auto-complete="off"
          placeholder="用户名"
      ></el-input>
    </el-form-item>
    <el-form-item>
      <el-input
          type="email"
          v-model="registerForm.email"
          auto-complete="off"
          placeholder="电子邮件"
      ></el-input>
    </el-form-item>
    <el-form-item>
      <el-input
          type="password"
          v-model="registerForm.password"
          auto-complete="off"
          placeholder="密码"
      ></el-input>
    </el-form-item>
    <el-form-item>
      <el-input
          type="password"
          v-model="registerForm.confirmPassword"
          auto-complete="off"
          placeholder="确认密码"
      ></el-input>
    </el-form-item>
    <!-- Captcha image and input field -->
    <el-form-item>
      <img src="../assets/img/captcha.png" width="100" alt="Captcha Image">
      <el-input
          type="text"
          v-model="registerForm.captcha"
          auto-complete="off"
          placeholder="输入上图中的字母"
      ></el-input>
    </el-form-item>
    <el-form-item style="width: 100%">
      <el-button
          type="primary"
          style="width: 100%; border: none"
          @click="register"
      >注册</el-button>
    </el-form-item>
  </el-form>
  </body>
</template>

<script>
export default {
  name: "Register",
  data() {
    return {
      registerForm: {
        username: "",
        email: "",
        password: "",
        confirmPassword: "",
        captcha: ""  // Added for captcha input
      },
      responseResult: [],
    };
  },
  methods: {
    register() {
      console.log("register")
      // Example post request to a registration endpoint
      this.$axios
          .post('http://localhost:5000/register', this.registerForm)
          .then(successResponse => {
            if (successResponse.data.code === 200) {
              this.$router.replace({ path: '/' }) // Redirect to login page after successful registration
            } else {
              console.error(successResponse.data.message);
            }
          })
          .catch(failResponse => {
            console.error("Registration failed", failResponse);
          });
    }
  },
};
</script>

<style scoped>
#register-page {
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
.register-container {
  border-radius: 15px;
  background-clip: padding-box;
  padding: 100px 100px 90px 90px;  /* Similar padding for consistent design */
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
  width: 500px;
}
.register_title {
  margin-bottom: 40px;
  text-align: center;
  color: #505458;
  font-size: 30px;
}
.register-container input {
  height: 50px;
  font-size: 18px;
  padding: 0 10px;
  width: 100%;
  box-sizing: border-box;
}

.register-container button {
  height: 40px;
  font-size: 18px;
  width: 100%;
}
</style>
