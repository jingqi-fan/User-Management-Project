<template>
  <div class="app" style="width: 100%; height: 100vh; margin: 0 auto;">
    <div class="form" >
      <el-form :model="attendeeList" :rules="rules" ref="attendeeList"
               label-width="100px"
               class="demo-ruleForm">

        <el-form-item label="用户名" prop="username">
          <el-input v-model="attendeeList.username"></el-input>
        </el-form-item>

        <el-form-item label="昵称" prop="nickname">
          <el-input v-model="attendeeList.nickname"></el-input>
        </el-form-item>

        <el-form-item label="密码" prop="password">
          <el-input v-model="attendeeList.password"></el-input>
        </el-form-item>

        <el-form-item label="电话" prop="phone">
          <el-input v-model="attendeeList.phone" type="number"></el-input>
        </el-form-item>

        <el-form-item label="性别" prop="sex">
          <!-- 单选框 -->
          <el-select v-model="attendeeList.sex" placeholder="请选择性别">
            <el-option label="男" value="0"></el-option>
            <el-option label="女" value="1"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="备注" prop="describe">
          <el-input type="textarea" v-model="attendeeList.describe"></el-input>
        </el-form-item>
        <el-form-item>
          <!-- 提交 -->
          <el-button type="primary" @click="submitForm('attendeeList')">提交</el-button>
          <!-- 清空表单 -->
          <el-button @click="resetForm('attendeeList')">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import attendee from '../../api/attendee.js'

export default {
  data() {
    return {
      // 页面状态 false->添加操作，true->编辑操作
      status: false,
      // 用户信息表单
      attendeeList: {
        username: '',
        nickname: '',
        password: '',
        phone: '',
        sex: '',
        describe: '',
      },
      // 校验规则，详细看 element ui 官方教程
      rules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
        ],
        nickname: [
          { required: true, message: '请输入昵称', trigger: 'blur' },
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ],
        phone: [
          { required: true, message: '请输入电话', trigger: 'blur' }
        ],
        sex: [
          { required: true, message: '请选择性别', trigger: 'blur' }
        ],
        describe: [
          { required: true, message: '请输入备注', trigger: 'blur' }
        ],
      },
    }
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if(this.status) {
            // 调用编辑用户接口
            console.log(this.attendeeList);
            attendee.updateAttendee(this.attendeeList).then(_ => {
              this.$message({
                message: '编辑成功',
                type: 'success'
              })
              // 跳转到主页下
              this.$router.push({ path: '/home' })
            })
          }else{
            // 调用添加用户接口
            attendee.addAttendee(this.attendeeList).then((_) => {
              this.$message({
                message: '添加成功',
                type: 'success'
              })
              // 跳转到主页下（attendeeList.vue）
              this.$router.push('/home')
            })
          }
        }
      })
    },
    // 清空表单
    resetForm(formName) {
      this.$refs[formName].resetFields()
    },
    // 判断是编辑还是添加用户
    check() {
      if(this.$route.params && this.$route.params.id) {
        // 路径存在id参数，编辑状态
        this.status = true
        attendee.getAttendeeInfo(this.$route.params.id).then( res => {
          // 通过传递过来的id查询用户数据，覆盖
          this.attendeeList = res.data.attendeeInfo
        })
      }else {
        // 未存在id参数，添加状态
        this.status = false
      }
    }
  },
  created() {
    // 页面未显示前就先判断状态
    this.check()
  },
  watch: {
    // 监听路由变化，不然会有bug
    $route(to, from) {
      this.check()
    }
  }
}
</script>

<style>
.form {
  width: 100%;
}
/* 清除号码框的上下效果 */
.form input::-webkit-outer-spin-button,
.form input::-webkit-inner-spin-button {
  -webkit-appearance: none;
}

.form input[type='number'] {
  -moz-appearance: textfield;
}
/*!* 如果表单元素还有内边距或外边距，也应调整 *!*/
/*.el-form-item {*/
/*  padding: 0;*/
/*  margin: 0;*/
/*}*/
/*.el-form {*/
/*  padding: 0;*/
/*  margin: 0;*/
/*}*/
/* 确保表单和其父元素没有宽度限制 */
#app, .app, .form, .el-form {
  max-width: none;
  width: 100%;
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
.form {
  height: 100vh;
  width: 100%;
  margin: 0 auto;
  background-image: url('../../assets/img/attendeeList.png');
  background-size: cover;
  background-position: center;
}
</style>