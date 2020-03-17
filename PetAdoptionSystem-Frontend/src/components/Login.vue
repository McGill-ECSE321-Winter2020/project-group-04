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

//    import axios from 'axios'
//   var config = require('../../config')
//   /* AXIOS object setup */
//   var frontendUrl = 'https://' + config.dev.host + ':' + config.dev.port
//   var backendUrl = 'https://' + config.dev.backendHost //+ ':' + config.dev.backendPort
//   var AXIOS = axios.create({
//     baseURL: backendUrl
//   })

   export default {
   data(){
       return{

       }
   }
   }
//   var checkInput = async (input) => {
//     if (input.trim().search(/\d{9}/) == -1) {
//       return 1; // Bad input
//     } else {
//       try {
//         let response = await AXIOS.get(/students/ + input);
//         if (response.data !== {}) {
//           return 0; // Student records found
//         } else {
//           return 2; // No matching records
//         }
//       } catch (e) {
//         return 3; // Error while making request
//       }
//     }
//     return false;
//   }
  
//      methods: {
//       printOut: async function() {
//         var result = await created();
//         console.log(result);
//       },
//       getRandomMessage: function() {
//         if (randomDisplay === 'puns') {
//           return puns[Math.floor(Math.random() * puns.length)]
//         } else if (randomDisplay === 'quotes') {
//           return quotes[Math.floor(Math.random() * quotes.length)]
//         }
//       },
//       goToDashboard: async function() {
//         var input = document.getElementById("usr").value;
//         var result = await checkInput(input);
//         if (result == 0) {
//           this.$router.push({
//             name: 'Dashboard',
//             params: {
//               id: input
//             }
//           })
//         } else {
//           document.getElementById("usr").className = 'form-control form-control-lg is-invalid'
//           document.getElementById("demo").style.color = 'red'
//           if (result == 1) {
//             document.getElementById("demo").innerHTML = "Please Enter A Valid Student ID";
//           } else if (result == 2 || result == 3) {
//             document.getElementById("demo").innerHTML = "There Are No Records For Student ID " + input;
//           }
//         };
//         //var result = checkInput(input).then(ret);
//         console.log("hey")
//       },
//     }
//   }


</script>