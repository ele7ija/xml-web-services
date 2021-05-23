import Vue from 'vue';
import VueRouter from 'vue-router';
import Home from '@/components/Home';
import Login from '@/components/Login';
import Register from '@/components/Register';
import ZahtevCreate from '@/components/gradjanin/zahtev/ZahtevCreate';
import ZahtevAll from '@/components/gradjanin/zahtev/ZahtevAll';
import ObavestenjeAll from '@/components/gradjanin/obavestenje/ObavestenjeAll';
import ZahtevSluzbenik from '@/components/sluzbenik/zahtev/ZahtevSluzbenik';
import Izvestaj from '@/components/sluzbenik/izvestaj/Izvestaj';
import Pretrage from '@/components/sluzbenik/pretrage/Pretrage';

Vue.use(VueRouter);

const router = new VueRouter({
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
      name: 'ZahtevCreate',
      path: '/zahtev-create',
      component: ZahtevCreate
    },
    {
      name: 'ZahtevAll',
      path: '/zahtev-all',
      component: ZahtevAll
    },
    {
      name: 'ObavestenjeAll',
      path: '/obavestenje-all',
      component: ObavestenjeAll
    },
    {
      name: 'ZahtevSluzbenik',
      path: '/sluzbenik-zahtev',
      component: ZahtevSluzbenik
    },
    {
      name: 'Izvestaj',
      path: '/izvestaj',
      component: Izvestaj
    },
    {
      name: 'Pretrage',
      path: '/pretrage',
      component: Pretrage
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