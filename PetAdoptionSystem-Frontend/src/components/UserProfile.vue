 <template>
  <div class="userprof">
       <img v-bind:src="img" alt="" width="100" ><br>
        <label> Username</label>
        <p>
            <input type="text" id="username" required>
        </p>
        <label> Phone Number</label>
        <p>
            <input type="text" id="phoneNum" required>
        </p>
        <label> Email </label>
        <p>
            <input type="email" id="email" required>
        </p>
        <label> Home Description </label>
        <p class="full">
            <textarea type="text" rows="4" cols="50" id="homedescription" placeholder="Enter description here..." required></textarea>
        </p>
         <button type="button" class id="update" @click="update()">
          <font size="4">
            <b>Update</b>
          </font>
        </button>
  </div>
</template>

<style scoped>
label{
    color: black;
}
.userprof{
  width: 420px;
  height: 520px;
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
  name: "userProfile",
  data() {
    return { 
    newprofile: {
        username: "",
        phonenumber:"",
        email:"",
        homedescription:"",
        errorMessage: "",
        response: ""
      },
      response: "",
      account: "",
      img: "https://www.amcorro.com/wp-content/uploads/2013/05/user-icon-1.png"
    }
  },
//  created(){
//           AXIOS.get(`/account/username/`, this.$route.params.username)
//         .then(response => {
//           console.log(response.data)
//           this.account = response.data
//         })
//         .catch(e => {
//           var errorMsg = e.message
//           console.log(errorMsg)
//           this.errorPerson = errorMsg
//         })
//       AXIOS.get(`/regularuser/` + this.$route.params.username)
//         .then(response => {
//           console.log(response.data)
        
//         })
//         .catch(e => {
//           var errorMsg = e.message
//           console.log(errorMsg)
//           this.errorPerson = errorMsg
//         })
//     },

    methods: {

  }
}
</script>