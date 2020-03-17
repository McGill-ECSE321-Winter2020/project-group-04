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
    height: 420px;
    background:#C0C0C0;
    color: #fff;
    top: 50%;
    left:50%;
    position: absolute;
    transform: translate(-50%, -50%);
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