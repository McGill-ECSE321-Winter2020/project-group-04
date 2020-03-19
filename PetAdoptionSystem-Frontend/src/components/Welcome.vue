<template>
  <div class="welcome">
    <h1>{{msg}}</h1>
    <br />
    <h5>
      <i>
        Unfortunately, in Canada alone, thousands of pets are abandoned every year.
        <br />Our mission here at Covid Shelter is to foster lifelong connections between pets and owners.
        <br />So don't be shy! Come find your best friend and never fear isolation again!
      </i>
    </h5>
    <br />
    <br />
    <br />

    <ul>
      <li>
        <a>
          <button onclick="location.href='/#/login' ">Login</button>
        </a>
      </li>
      <li>
        <a>
          <button onclick="location.href='/#/donate' ">Donate</button>
        </a>
      </li>
    </ul>
  </div>
</template>


<style scoped>
h1,
h3 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
  overflow: hidden;
  background-color: #23495c;
}

li a {
  display: inline-block;
  color: white;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
}

li a:hover {
  background-color: #002041;
}

button {
  background-color: Transparent;
  background-repeat: no-repeat;
  color: white;
  border: none;
  cursor: pointer;
  overflow: hidden;
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

export default {
  name: "Welcome",
  data() {
    return {
      msg: "Welcome to Covid Shelter",
      systemId: null
    };
  }
};

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { "Access-Control-Allow-Origin": frontendUrl }
});

AXIOS.post(`/createSystem`)
  .then(response => {
    // JSON responses are automatically parsed.
    this.systemId = response.data.systemID;
    console.log(response.data.systemID);
  })
  .catch(e => {
    this.errorSystem = e;
  });
</script>
