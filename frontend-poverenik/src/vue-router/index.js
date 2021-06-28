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
import ZalbeNaOdlukuPoverenik from '@/components/poverenik/zalbe/ZalbeNaOdlukuPoverenik';
import ZalbeNaCutanjePoverenik from '@/components/poverenik/zalbe/ZalbeNaCutanjePoverenik';
import ResenjeZalbaCutanjaCreate from '@/components/poverenik/resenje/ResenjeZalbaCutanjaCreate';
import ResenjeZalbaNaOdlukuCreate from '@/components/poverenik/resenje/ResenjeZalbaNaOdlukuCreate';
import ResenjaAll from '@/components/poverenik/resenje/ResenjaAll';
import ResenjeView from '@/components/poverenik/resenje/ResenjeView';
import Izvestaji from '@/components/poverenik/izvestaj/Izvestaji';
import Izvestaj from '@/components/poverenik/izvestaj/Izvestaj';


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
      name: 'ZalbeNaOdlukuPoverenik',
      path: 'zalbe-na-odluku-poverenik',
      component: ZalbeNaOdlukuPoverenik
    },
    {
      name: 'ZalbeNaCutanjePoverenik',
      path: 'zalbe-na-cutanje-poverenik',
      component: ZalbeNaCutanjePoverenik
    },
    {
      name: 'ResenjeZalbaNaOdlukuCreate',
      path: '/resenje-zalba-na-odluku-create/:idZalbe',
      component: ResenjeZalbaNaOdlukuCreate
    },
    {
      name: 'ResenjeZalbaCutanjaCreate',
      path: '/resenje-zalba-cutanja-create/:idZalbe',
      component: ResenjeZalbaCutanjaCreate
    },
    {
      name: 'ResenjaAll',
      path: '/resenja',
      component: ResenjaAll
    },
    {
      name: 'ResenjeView',
      path: '/resenje/:id',
      component: ResenjeView
    },
    {
      name: 'Izvestaji',
      path: '/izvestaji',
      component: Izvestaji
    },
    {
      name: 'Izvestaj',
      path: '/izvestaj/:id',
      component: Izvestaj
    },
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