import Vue from "vue";
import Router from "vue-router";
import Hello from "./../components/HelloWorld.vue";
import About from "./../components/About.vue";

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: "/", ///path của route
      name: "Hello", // tên route
      component: Hello, // component route sử dụng
    },
    {
      path: "/about",
      name: "About",
      component: About,
    },
  ],
});
