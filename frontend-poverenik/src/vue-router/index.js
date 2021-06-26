import Vue from 'vue';
import VueRouter from 'vue-router';
import Home from '@/components/Home';
import Login from '@/components/Login';
import Register from '@/components/Register';
import ZalbaNaOdluku from '@/components/gradjanin/zalbe/ZalbaNaOdluku';
import ZalbaNaOdlukuCreate from '@/components/gradjanin/zalbe/ZalbaNaOdlukuCreate';
import ZalbaNaOdlukuView from '@/components/gradjanin/zalbe/ZalbaNaOdlukuView';
import ZalbaNaCutanje from '@/components/gradjanin/zalbe/ZalbaNaCutanje';
import ZalbaNaCutanjeView from '@/components/gradjanin/zalbe/ZalbaNaCutanjeView';
import ZalbaNaCutanjeCreate from '@/components/gradjanin/zalbe/ZalbaNaCutanjeCreate';
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
      name: 'ZalbaNaOdlukuView',
      path: '/zalba_na_odluku/:id',
      component: ZalbaNaOdlukuView
    },
    {
      name: 'ZalbaNaOdlukuCreate',
      path: '/zalba-na-odluku-create/:idObavestenja',
      component: ZalbaNaOdlukuCreate
    },
    {
      name: 'ZalbaNaCutanje',
      path: '/zalba-na-cutanje',
      component: ZalbaNaCutanje
    },
    {
      name: 'ZalbaNaCutanjeView',
      path: '/zalba_cutanja/:id',
      component: ZalbaNaCutanjeView
    },
    {
      name: 'ZalbaNaCutanjeCreate',
      path: '/zalba-na-cutanje-create/:idObavestenja',
      component: ZalbaNaCutanjeCreate
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