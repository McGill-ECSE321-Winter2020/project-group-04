<template>
  <div class="welcome">
    <h1>{{msg}}</h1>
    <br>
    <h5><i>Unfortunately, in Canada alone, thousands of pets are abandoned every year. <br>
      Out mission here at Covid Shelter is to foster lifelong connections between pets and owners. <br>
      So don't be shy! Come find your best friend and never fear isolation again!</i></h5>
    <br><br><br>

    <ul>
      <li><a href="http://127.0.0.1:8087/#/login">Login</a></li>
      <li><a href="Create an Account">Create an Account</a></li>
      <li><a href="http://127.0.0.1:8087/#/donate">Donate to Help Us Go Viral!</a></li>
    </ul>

  </div>
</template>


<script>
  export default {
    name: "Welcome",
    data() {
      return {
        msg: 'Welcome to Covid Shelter'
      }
    }
  }
</script>

<style scoped>
  h1, h3 {
    font-weight: normal;
  }
  ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    overflow: hidden;
    background-color: #23495c;
  }

  li {
    float: none;
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

</style>
