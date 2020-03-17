<template>
    <div id = "donation">
      <h1> {{msg}}</h1>
      <br>
      <p> The Covid Shelter Foundation is a non-profit organization, whose founds are solely achieved through the kindful donation of its members. <br>
        With your help, the foundation can take care the animals we receive until they can find its new owner. <br>
        Your donation is very important for us, and we are very grateful for any amount that you can provide. <br>
        Please help us donating below. <br>
      </p>

      <br><br>
      <h5>
      Your Donation
      </h5>
      <br>
     <input type="number" placeholder="Amount">
      <br>
      <br>
      <button> Donate!</button>
      <br>
      <br>
      <a href="/">Back to the Home Page</a>
      <br>
      <br>
    </div>
</template>

<script>
    export default {
        name: "Donation",
      data() {
        return{
          msg: 'Help donating to the Covid Shelter Foundation'
        }
      }
    }
</script>

<style scoped>

</style>
