import Vue from 'vue';
import VueRouter from 'vue-router';
import Home from '@/components/Home';
import Login from '@/components/Login';
import Register from '@/components/Register';
import ZalbaNaOdluku from '@/components/gradjanin/zalbe/ZalbaNaOdluku';
import ZalbaNaCutanje from '@/components/gradjanin/zalbe/ZalbaNaCutanje';
import ZalbePoverenik from '@/components/poverenik/zalbe/ZalbePoverenik';
import Izvestaji from '@/components/poverenik/izvestaj/Izvestaji';

Vue.use(VueRouter);

const router = new VueRouter({
  mode: 'history',
  routes: [
    {
      name: 'Home',
      path: '/',
      component: Home
    },
    {
      name: 'Login',
      path: '/login',
      component: Login
    },
    {
      name: 'Register',
      path: '/register',
      component: Register
    },
    {
      name: 'ZalbaNaOdluku',
      path: '/zalba-na-odluku',
      component: ZalbaNaOdluku
    },
    {
      name: 'ZalbaNaCutanje',
      path: '/zalba-na-cutanje',
      component: ZalbaNaCutanje
    },
    {
      name: 'ZalbePoverenik',
      path: 'zalbe-poverenik',
      component: ZalbePoverenik
    },
    {
      name: 'Izvestaji',
      path: '/izvestaji',
      component: Izvestaji
    }
  ]
});

router.beforeEach((to, from, next) => {
  /*
    implement specific logic for client-side routing
    for example, prevent navigation to login component if user is logged in
  */
  if (to.name != 'Login' && to.name != 'Register' && !sessionStorage.getItem('access_token')) {
    next({name: 'Login'});
  }
  next();
});

export default router;