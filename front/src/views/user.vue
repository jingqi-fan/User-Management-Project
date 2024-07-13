<template>
  <body id="person-page">
  <div class="person" >
    <router-link to="/home" class="back-button">
      <el-button type="primary" round>返回</el-button>
    </router-link>
    <el-tabs v-model="activeTab">
      <el-tab-pane label="基本资料" name="basicInfo">
        <el-form :model="user" ref="userForm" label-width="100px" class="demo-ruleForm">
          <el-form-item label="用户名" prop="userName" :rules="[{ required: true, message: '请输入用户名', trigger: 'blur' }]">
            <el-input v-model="user.userName"></el-input>
          </el-form-item>
          <el-form-item label="手机号码" prop="phone" :rules="[{ required: true, message: '请输入手机号码', trigger: 'blur' }]">
            <el-input v-model="user.phone"></el-input>
          </el-form-item>
          <el-form-item label="邮箱" prop="email" :rules="[{ required: true, message: '请输入邮箱', trigger: 'blur' }]">
            <el-input v-model="user.email"></el-input>
          </el-form-item>
          <el-form-item label="性别" prop="sex">
            <el-radio-group v-model="user.sex">
              <el-radio label="male">男</el-radio>
              <el-radio label="female">女</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm('userForm')">保存</el-button>
            <el-button @click="resetForm('userForm')">重置</el-button>
          </el-form-item>
        </el-form>
      </el-tab-pane>

      <el-tab-pane label="修改密码" name="changePassword">
        <el-form :model="password" ref="passwordForm" label-width="100px" class="demo-ruleForm">
          <el-form-item label="当前密码" prop="currentPassword" :rules="[{ required: true, message: '请输入当前密码', trigger: 'blur' }]">
            <el-input type="password" v-model="password.currentPassword"></el-input>
          </el-form-item>
          <el-form-item label="新密码" prop="newPassword" :rules="[{ required: true, message: '请输入新密码', trigger: 'blur' }]">
            <el-input type="password" v-model="password.newPassword"></el-input>
          </el-form-item>
          <el-form-item label="确认新密码" prop="confirmNewPassword" :rules="[{ required: true, message: '请确认新密码', trigger: 'blur' }, { validator: validateConfirmPassword, trigger: 'blur' }]">
            <el-input type="password" v-model="password.confirmNewPassword"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm('passwordForm')">保存</el-button>
            <el-button @click="resetForm('passwordForm')">重置</el-button>
          </el-form-item>
        </el-form>
      </el-tab-pane>
    </el-tabs>
  </div>
  </body>
</template>

<script>
import { ref, reactive, onMounted } from 'vue';
import { ElMessage } from 'element-plus';
import axios from 'axios';

export default {
  name: "Person",
  setup() {
    const activeTab = ref('basicInfo');

    const user = reactive({
      userName: '',
      phone: '',
      email: '',
      sex: '',
    });

    const password = reactive({
      currentPassword: '',
      newPassword: '',
      confirmNewPassword: '',
    });

    const userForm = ref(null);
    const passwordForm = ref(null);

    const validateConfirmPassword = (rule, value, callback) => {
      if (value !== password.newPassword) {
        callback(new Error('两次输入的新密码不一致'));
      } else {
        callback();
      }
    };

    const fetchUserData = async () => {
      const userId = localStorage.getItem('userId'); // 假设用户ID保存在localStorage中
      if (userId) {
        const response = await axios.get(`http://localhost:5000/user/${userId}`);
        Object.assign(user, response.data);
      }
    };

    const submitForm = async (formName) => {
      const form = formName === 'userForm' ? userForm.value : passwordForm.value;
      form.validate(async (valid) => {
        if (valid) {
          if (formName === 'userForm') {
            try {
              const response = await axios.put('http://localhost:5000/user/update', user, {
                headers: {
                  'Content-Type': 'application/json'
                }
              });
              if (response.data) {
                ElMessage({
                  message: '个人信息已成功更新',
                  type: 'success',
                });
              }

              ElMessage({
                message: '个人信息已成功更新',
                type: 'success',
              });

            } catch (error) {
              console.error('Update failed:', error);
              ElMessage({
                message: '更新失败',
                type: 'error',
              });
            }
          } else {
            // 处理密码更新逻辑
            ElMessage({
              message: '个人信息已成功更新',
              type: 'success',
            });
          }
        } else {
          ElMessage({
            message: '请完善表单信息',
            type: 'warning',
          });
          return false;
        }
      });
    };


    const resetForm = (formName) => {
      const form = formName === 'userForm' ? userForm.value : passwordForm.value;
      form.resetFields();
    };

    onMounted(() => {
      fetchUserData();
    });

    return {
      activeTab,
      user,
      password,
      userForm,
      passwordForm,
      submitForm,
      resetForm,
      validateConfirmPassword,
    };
  }
};
</script>

<style scoped>
.person {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
}

.el-form-item {
  margin-bottom: 20px;
}
body,html{
  height: 100%;
}
.person {
  max-width: 600px;
  margin: 0 auto 0 0; /* 左侧对齐，保持顶部和底部自动间距 */
  padding: 200px 90px 20px; /* 增加顶部内边距 */
  /*background-image: url('../assets/img/bg2.png');*/
  background-size: cover;
  background-position: center;
  height: 100vh; /* 让容器铺满整个屏幕高度 */
  width: 70vh;
}
#person-page {
  display: flex;
  align-items: center;
  justify-content: center;
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: url("../assets/img/bg2.png") no-repeat center center;
  background-size: cover;
}

.back-button {
  position: absolute;
  top: 10px;
  left: 10px;
}

</style>
