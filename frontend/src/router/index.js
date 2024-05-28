import { createRouter, createWebHistory } from 'vue-router'  
import ContentView from '../views/ContentView.vue'

const routes = [
{
    path : '/',
    name : 'ContentView',
    component: () => ContentView

},
{
    path: '/user',
    name: 'ListView',
    component: () => import('../views/ListView.vue') 
},
{
    path: '/user/findById',
    name: 'SelectView',
    component: () => import('../views/SelectView.vue') 
}
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export {router}