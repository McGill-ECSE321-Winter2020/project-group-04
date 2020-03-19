<template>
  <div id="app">
    <img src="./assets/covid_shelter_logo.png" width="500" />
    <router-view></router-view>
  </div>
</template>

<script>
import axios from "axios";
import JQuery from "jquery";

let $ = JQuery;
var config = require("../config");

var frontendUrl = "http://" + config.dev.host + ":" + config.dev.port;
var backendUrl =
  "http://" + config.dev.backendHost + ":" + config.dev.backendPort;

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { "Access-Control-Allow-Origin": frontendUrl }
});

export default {
  name: "app",
  computed: {
    loggedIn() {
      if (this.$cookie.get("username") != null) {
        return true;
      }
      return false;
    }
  },
  methods: {
    logout() {
      this.$cookie.delete("username");
      window.location.href = "/";
    }
  }
};
</script>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
