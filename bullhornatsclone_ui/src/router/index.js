import Vue from "vue";
import VueRouter from "vue-router";
import DefaultLayout from "@/layouts/DefaultLayout.vue";
import Jobs from  '@/pages/Jobs.vue';
import JobDetail from  '@/pages/JobDetail.vue';
import Candidates from  '@/pages/Candidates.vue';
import CandidateDetail from  '@/pages/CandidateDetail.vue';
import Matchs from  '@/pages/Matchs.vue';
import MatchDetail from  '@/pages/MatchDetail.vue';
import Clients from  '@/pages/Clients.vue';
import ClientDetail from  '@/pages/ClientDetail.vue';
import Users from  '@/pages/Users.vue';
import UserDetail from  '@/pages/UserDetail.vue';
import Applications from  '@/pages/Applications.vue';
import ApplicationDetail from  '@/pages/ApplicationDetail.vue';

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: () => import("../views/HomeView.vue"),
			redirect: '/jobs',
						  },
  {
    path: "/pricing",
    name: "PricingView",
    component: () => import("../views/PricingView.vue"),
  },
  {
    path: "/arts-gallery",
    name: "ArtsGalleryView",
    component: () => import("../views/ArtsGalleryView.vue"),
  },
  {
    path: "/checkout/:id",
    name: "CheckoutView",
    component: () => import("../views/CheckoutView.vue"),
  },
  {
    path: "/stripe-checkout",
    name: "StripeCheckoutView",
    component: () => import("../views/StripeCheckoutView.vue"),
  },
	{
		path: '/jobs',
		name: 'Jobs',
		layout: DefaultLayout,
		component: Jobs,
	},
	{
	    path: '/job/:jobId', 
	    name: 'JobDetail',
		layout: DefaultLayout,
	    component: JobDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/candidates',
		name: 'Candidates',
		layout: DefaultLayout,
		component: Candidates,
	},
	{
	    path: '/candidate/:candidateId', 
	    name: 'CandidateDetail',
		layout: DefaultLayout,
	    component: CandidateDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/matchs',
		name: 'Matchs',
		layout: DefaultLayout,
		component: Matchs,
	},
	{
	    path: '/match/:matchId', 
	    name: 'MatchDetail',
		layout: DefaultLayout,
	    component: MatchDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/clients',
		name: 'Clients',
		layout: DefaultLayout,
		component: Clients,
	},
	{
	    path: '/client/:clientId', 
	    name: 'ClientDetail',
		layout: DefaultLayout,
	    component: ClientDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/users',
		name: 'Users',
		layout: DefaultLayout,
		component: Users,
	},
	{
	    path: '/user/:userId', 
	    name: 'UserDetail',
		layout: DefaultLayout,
	    component: UserDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/applications',
		name: 'Applications',
		layout: DefaultLayout,
		component: Applications,
	},
	{
	    path: '/application/:applicationId', 
	    name: 'ApplicationDetail',
		layout: DefaultLayout,
	    component: ApplicationDetail,
	    props: true // Pass route params as props to the component
  	},
];

const router = new VueRouter({
  mode: "hash",
  base: process.env.BASE_URL,
  routes,
});

export default router;
