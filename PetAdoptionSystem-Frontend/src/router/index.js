import Vue from 'vue'
import Router from 'vue-router'
import Welcome from '@/components/Welcome'
import Login from '@/components/Login'
import Donation from '@/components/Donation'
import Signup from '@/components/Signup'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Welcome',
      component: Welcome
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/donate',
      name: 'Donation',
      component: Donation
    },
    {
      path: '/signup',
      name: 'Signup',
      component: Signup
    }
  ]
})
