import { createWebHistory, createRouter } from "vue-router";
import Home from "../components/Home.vue";
import DetailEmployee from "../components/employee/DetailEmployee.vue";
const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/detail/employee/:id",
    name: "employeeDetail",
    component: DetailEmployee,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
