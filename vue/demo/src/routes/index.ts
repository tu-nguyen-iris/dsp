import {createWebHistory, createRouter} from "vue-router";
import ListEmployee from "../components/employee/ListEmployee.vue";
import Login from "../components/Login.vue";
import Layout from "../components/layout/Layout.vue";
import Page404 from "../components/Page404.vue";

import DetailEmployee from "../components/employee/DetailEmployee.vue";

const routes = [
    {
        path: "/login",
        name: "Login",
        component: Login,
    },
    {
        path: "/",
        name: "Layout",
        component: Layout,
        redirect: '/employee',
        meta: {
            requiresAuth: true
        },
        children: [
            {
                path: "employee",
                component: ListEmployee,
                name: "ListEmployee",
                meta: {title: 'Home', icon: 'dashboard', affix: true}
            },
            {
                path: "detail/employee/:id",
                component: DetailEmployee,
                name: "employeeDetail",
                // meta: {title: 'Dashboard', icon: 'dashboard', affix: true}
            }
        ]
    },
    {path: '/:pathMatch(.*)*', component: Page404},
    // {
    //     path: "/detail/employee/:id",
    //     name: "employeeDetail",
    //     component: DetailEmployee,
    // },
];

const router = createRouter({
    history: createWebHistory(),
    routes,

});

export default router;
