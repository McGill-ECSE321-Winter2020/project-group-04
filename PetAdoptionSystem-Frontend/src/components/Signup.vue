<template>
  <div class="signup">
      <h1>Sign up</h1><br>
        <form>
            <p>
                <input type="text" name="" id= "username" placeholder="Enter Username">
            </p><br>
            <p>
                <input type="text" name="" id="email" placeholder="Enter Email Address">
            </p><br>
            <p>
                <input type="password" name="" id="password" placeholder="Enter Password">
            </p>
            <p><br>
                <button type="button" class="" id="signup" @click="signup()">
                    <font size="4"><b>Sign up</b></font>
                </button>
            </p>
            <!-- this is a temporary href; need to change with proper routing -->
            <a href="#"> Already have an account? Log in!</a>
        </form>
  </div>
</template>

<script>
export default {
    name: "Signup",
    data() {
        return {
            username: "",
            email: "",
            password: "",
            error: "",
            response: ""
        };
    },
    methods: {
        // TODO
        signup() {
            // TODO
            // 1) validate username
            // 2) validate email address
            // 3) validate password

            // 4) create password hash

            // 5) send createAccount request to backend
                // return and display any errors that occurred in backend
            
            // 6) route to home page
        }
    }
}
</script>

<style>
    body {
        margin: 0;
        padding: 0;
        /* background: url; can put background image here*/
    }

    h1 {
        color: black;
    }
    p {
        color: black;
    }

    .signup{
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