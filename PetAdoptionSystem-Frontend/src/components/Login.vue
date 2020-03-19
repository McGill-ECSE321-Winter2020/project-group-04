<template>
  <div class="loginbox">
    <h1>Login</h1>
    <br />
    <form>
      <h2>{{errorLogin}}</h2>
      <p>
        <input type="text" name id="username" placeholder="Enter Username" />
      </p>
      <br />
      <p>
        <input type="password" name id="password" placeholder="Enter Password" />
      </p>
      <p>
        <br />
        <button type="button" class id="login" @click="login()">
          <font size="4">
            <b>Login</b>
          </font>
        </button>
      </p>
      <a href="#">Don't have an account?</a>
    </form>

    <br />
    <a href="Welcome">Back to the Home Page</a>
    <br />
  </div>
</template>

<style scoped>
body {
  margin: 0;
  padding: 0;
  /* background: url; can put background image here*/
}
h1 {
  color: black;
}
h2 {
  color: red;
  font-size: 100%;
}
p {
  color: black;
}
.loginbox {
  width: 320px;
  height: 420px;
  background: whitesmoke;
  color: #fff;
  top: 50%;
  left: 50%;
  position: absolute;
  transform: translate(-50%, -50%);
  box-sizing: border-box;
  box-shadow: 0 0 20px 0 rgba(72, 94, 116, 0.7);
}
</style>

<script>
import axios from "axios";
import JQuery from "jquery";
let $ = JQuery;
var config = require("../../config");

var frontendUrl = "http://" + config.dev.host + ":" + config.dev.port;
var backendUrl =
  "http://" + config.dev.backendHost + ":" + config.dev.backendPort;

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { "Access-Control-Allow-Origin": frontendUrl }
});

export default {
  name: "login",
  data() {
    return {
      username: "",
      password: "",
      errorLogin: "",
      response: ""
    };
  },
  methods: {
    login() {
      this.username = $("#username").val();
      this.password = $("#password").val();
      if (this.username == "") {
        var errorMessage = "Username cannot be empty";
        console.log(errorMessage);
        this.errorLogin = errorMessage;
        return;
      }
      if (this.password == "") {
        var errorMessage = "Password cannot be empty";
        console.log(errorMessage);
        this.errorLogin = errorMessage;
        return;
      }
      AXIOS.post(
        "/login/",
        $.param({ username: this.username, password: this.password })
      )
        .then(response => {
          this.response = response.data;
          this.errorLogin = "";
          console.log(response);
          if (this.response != "") {
            localStorage.setItem("loggedIn", this.response.username);
            console.log(this.response.username);
            this.$cookie.set("username", this.response.username, {
              expires: "1h"
            });
            this.$router.push({
              name: "Welcome", //needs to be changed to route to UserPage.
              params: {
                username: this.username
              }
            });
          } else {
            this.errorLogin = "Wrong username and password!";
            console.log(this.errorLogin);
          }
        })
        .catch(e => {
          var errorMessage = e.response;
          console.log(e);
          this.errorLogin = errorMessage;
        });
    }
  }
};
</script>