<template>
  <a-layout style="min-height: 100vh">
    <a-layout-sider v-model:collapsed="collapsed" collapsible>
      <div class="logo"/>
      <a-menu v-model:selectedKeys="selectedKeys" theme="dark" mode="inline">
        <a-menu-item key="1">
          <pie-chart-outlined/>
          <span> <router-link to="/"/>Home</span>
        </a-menu-item>
      </a-menu>
    </a-layout-sider>
    <a-layout>
      <a-layout-header style="background: #fff; padding: 0;box-shadow: #00000059 0px 5px 15px;margin-bottom: 15px;"/>
      <a-layout-content style="margin: 0 16px">
        <div :style="{ padding: '24px', background: '#fff', minHeight: '360px' }">
          <router-view :key="key"/>
        </div>
      </a-layout-content>
      <a-layout-footer style="text-align: center">
        Demo
      </a-layout-footer>
    </a-layout>
  </a-layout>
</template>

<script>
export default {
  name: "Layout",
  computed: {
    // cachedViews() {
    //   return this.$store.state.tagsView.cachedViews
    // },
    key() {
      return this.$route.path
    }
  }
}
</script>

<style scoped>

</style>