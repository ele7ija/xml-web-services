import Vue from 'vue';
import VueRouter from 'vue-router';
import Home from '@/components/Home';
import Login from '@/components/Login';
import Register from '@/components/Register';
import ZahtevCreate from '@/components/gradjanin/zahtev/ZahtevCreate';
import ZahtevAll from '@/components/gradjanin/zahtev/ZahtevAll';
import Zahtev from '@/components/gradjanin/zahtev/Zahtev';
import ObavestenjeAll from '@/components/gradjanin/obavestenje/ObavestenjeAll';
import ObavestenjeCreate from '@/components/sluzbenik/obavestenje/ObavestenjeCreate';
import Obavestenje from '@/components/sluzbenik/obavestenje/Obavestenje';
import ZahtevSluzbenik from '@/components/sluzbenik/zahtev/ZahtevSluzbenik';
import IzvestajAll from '@/components/sluzbenik/izvestaj/IzvestajAll';
import Izvestaj from '@/components/sluzbenik/izvestaj/Izvestaj';
import IzvestajCreate from '@/components/sluzbenik/izvestaj/IzvestajCreate';
import Pretrage from '@/components/sluzbenik/pretrage/Pretrage';
import store from '../vuex';

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
      name: 'Zahtev',
      path: '/zahtev/:id',
      component: Zahtev
    },
    {
      name: 'ObavestenjeAll',
      path: '/obavestenje-all',
      component: ObavestenjeAll
    },
    {
      name: 'ObavestenjeCreate',
      path: '/obavestenje-create/:idZahteva',
      component: ObavestenjeCreate
    },
    {
      name: 'Obavestenje',
      path: '/obavestenje/:id',
      component: Obavestenje
    },
    {
      name: 'ZahtevSluzbenik',
      path: '/sluzbenik-zahtev',
      component: ZahtevSluzbenik
    },
    {
      name: 'Izvestaj',
      path: '/izvestaj/:id',
      component: Izvestaj
    },
    {
      name: 'IzvestajCreate',
      path: '/izvestaj-create',
      component: IzvestajCreate
    },
    {
      name: 'IzvestajAll',
      path: '/izvestaj-all',
      component: IzvestajAll
    },
    {
      name: 'Pretrage',
      path: '/pretrage',
      component: Pretrage
    },

  ]
});

router.beforeEach((to, from, next) => {
  /*
    implement specific logic for client-side routing
    for example, prevent navigation to login component if user is logged in
  */
  if (to.name != 'Login' && to.name != 'Register' && !sessionStorage.getItem('access_token')) {
    store.commit('userOptions/setRedirectUri', to.fullPath, {root: true});
    next({name: 'Login'});
  }
  next();
});

export default router;