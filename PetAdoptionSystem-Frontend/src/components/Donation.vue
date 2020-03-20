<template>
    <div id = "donation">
      <h1> Help donating to the Covid Shelter Foundation</h1>
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
      <p>If you wish to do an anonymous donation, you can leave the Name and E-mail boxes empty.</p>
      <br>
     <input type="String" v-model="newDonation.name" placeholder="Name">
      <br>
      <br>
      <input type="String" v-model="newDonation.email" placeholder="E-mail">
      <br>
      <br>
      <input type="number" v-model="newDonation.amount" placeholder="Amount">
      <br>
      <br>
      <button @click="createDonation(newDonation.amount, newDonation.name, newDonation.email)"> Donate!</button>
      <br>
      <br>
      <a href="/">Back to the Home Page</a>
      <br>
      <br>
    </div>
</template>

<script>

  function DonationDto (amount, name, email, date, time){
      this.amount = amount,
      this.name = name,
      this.email = email,
      this.date = date,
      this.time = time
  }

    export default {
      name: 'donation',
      data (){
        return{
          donations: [],
          newDonation: '',
          errorDonation: '',
          response: []
        }
      },

      created: function (){
        //Test data
        const d1 = new DonationDto(500, 'Jose', 'ja.jijon@')
        const d2 = new DonationDto(1000, 'Antonio', 'ja.vorbeck@')
        //Initial sample content
        this.donations = [d1,d2]
      },
    }

  methods: {
    function createDonation (amount, name, email, date, time){
      var d1 = new DonationDto(amount, name, email, date, time)
        this.donations.push(d),
        //Reset new field for other donations
      this.newDonation  = ''
    }
  }

</script>

<style scoped>

</style>
