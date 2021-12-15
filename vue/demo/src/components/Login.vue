<template>
  <div class="vue-tempalte">
    <a-form ref="formRef" :model="formState">
      <h3>Sign In</h3>
      <a-form-item name='username' class="form-group">
        <label>User name</label>
        <a-input v-model:value="formState.username" class="form-control form-control-lg"/>
      </a-form-item>

      <a-form-item name="password" class="mt-3 form-group">
        <label>Password</label>
        <a-input type="password" v-model:value="formState.password" class="form-control form-control-lg"/>
      </a-form-item>

      <div class="d-flex justify-content-between mt-3">
        <button class="btn mt-3 btn-dark btn-lg btn-block" @click="signIn">Sign In</button>
        <button class="btn mt-3 btn-dark btn-lg btn-block" @click="register">Register</button>
      </div>

      <p class="forgot-password text-right mt-2 mb-4">
        <router-link to="/forgot-password">Forgot password ?</router-link>
      </p>
    </a-form>
  </div>
</template>

<script lang="ts">
import {reactive, ref, toRaw} from "vue";
import Services from '../services/index';

interface formState {
  username: string,
  password: string
}

export default {
  name: "Login",
  setup() {
    const formRef = ref();
    const formState = reactive<formState>({
      username: "",
      password: ""
    })
    const signIn = async () => {
      const res = await Services._login(toRaw(formState))
      console.log(res)
    }
    const register = async () => {
      const res = await Services._register(toRaw(formState))
      console.log(res)
    }
    return {
      formState,
      signIn,
      register
    }
  }
}
</script>

<style scoped>

.vue-tempalte {
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
}

form {
  padding: 30px;
  box-shadow: rgba(0, 0, 0, 0.25) 0px 14px 28px, rgba(0, 0, 0, 0.22) 0px 10px 10px;
}

.navbar-light {
  background-color: #ffffff;
  box-shadow: 0px 14px 80px rgba(34, 35, 58, 0.2);
}

.vertical-center {
  display: flex;
  text-align: left;
  justify-content: center;
  flex-direction: column;
}

.inner-block {
  width: 450px;
  margin: auto;
  background: #ffffff;
  box-shadow: 0px 14px 80px rgba(34, 35, 58, 0.2);
  padding: 40px 55px 45px 55px;
  border-radius: 15px;
  transition: all .3s;
}

.vertical-center .form-control:focus {
  border-color: #2554FF;
  box-shadow: none;
}

.vertical-center h3 {
  text-align: center;
  margin: 0;
  line-height: 1;
  padding-bottom: 20px;
}

label {
  font-weight: 500;
}

.forgot-password,
.forgot-password a {
  text-align: right;
  font-size: 13px;
  padding-top: 10px;
  color: #7a7a7a;
  margin: 0;
}

.forgot-password a {
  color: #2554FF;
}

.social-icons {
  text-align: center;
  font-family: "Open Sans";
  font-weight: 300;
  font-size: 1.5em;
  color: #222222;
}

.social-icons ul {
  list-style: none;
  margin: 0;
  padding: 0;
}

.social-icons ul li {
  display: inline-block;
  zoom: 1;
  width: 65px;
  vertical-align: middle;
  border: 1px solid #e3e8f9;
  font-size: 15px;
  height: 40px;
  line-height: 40px;
  margin-right: 5px;
  background: #f4f6ff;
}

.social-icons ul li a {
  display: block;
  font-size: 1.4em;
  margin: 0 5px;
  text-decoration: none;
}

.social-icons ul li a i {
  -webkit-transition: all 0.2s ease-in;
  -moz-transition: all 0.2s ease-in;
  -o-transition: all 0.2s ease-in;
  -ms-transition: all 0.2s ease-in;
  transition: all 0.2s ease-in;
}

.social-icons ul li a:focus i,
.social-icons ul li a:active i {
  transition: none;
  color: #222222;
}
</style>
