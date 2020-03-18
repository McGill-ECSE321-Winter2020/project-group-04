<template>
  <div class="signup">
      <h1>Sign Up</h1><br>
        <form>
            <p>
                <input type="text" name="" id = "username" placeholder="Enter Username">
            </p><br>
            <p>
                <input type="password" name="" id="password" placeholder="Enter Password">
            </p>
            <p><br>
                <button type="button" class="" id="login" @click="login()">
                    <font size="4"><b>Login</b></font>
                </button>
            </p>
            <a href="#"> Already have an account? </a>
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
        }
    }
}
</script>

<style>

</style>