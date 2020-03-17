<template>
    <div class="loginbox">
        <h1>Login </h1><br>
        <form>
            <p>
            <input type="text" name="" placeholder="Enter Username">
            </p><br>
            <p>
            <input type="password" name="" placeholder="Enter Password">
            </p>
            <p><br>
            <button type="button" class="" id="login">
                    <font size="4"><b>Login</b></font>
            </button>
            </p>
            <a href="#"> Don't have an account? </a>
        </form>

      <br>
      <a href="Welcome">Back to the Home Page</a>
      <br>

    </div>
</template>

<style scoped>

body{
    margin: 0;
    padding: 0;
    /* background: url; can put background image here*/
}

h1{
    color: black;
}
p{
    color:black;
}
.loginbox{
    width: 320px;
    height: 380px;
    background:#5F9EA7;
    color: #fff;
    top: 0%;
    left: 0%;
    position: relative;
    transform: translate(150%, 0%);
    box-sizing: border-box;
    box-shadow: 0 0 20px 0 rgba(72, 94, 116, 0.7);

}

</style>

<script>
  export default {
   data(){
       return{

       }
   }
  }
</script>