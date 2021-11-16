<template>
  <EditEmployee :data="data"/>
  <h1 class='mb-5 text-center'>
    Detail Employee
  </h1>
  <a-row :gutter="[16,16]">
    <a-col :span="12">
      <h5 class="font-weight-bold">First name</h5>
      <p>{{ data.firstName || '-' }}</p>
    </a-col>
    <a-col :span="12">
      <h5 class="font-weight-bold">Last name</h5>
      <p>{{ data.lastName || '-' }}</p>
    </a-col>
    <a-col :span="12">
      <h5 class="font-weight-bold">Email</h5>
      <p>{{ data.email || '-' }}</p>
    </a-col>
    <a-col :span="12">
      <h5 class="font-weight-bold">Hire Date</h5>
      <p>{{ new Date(data.hireDate).getDate() || '-' }}</p>
    </a-col>
    <a-col :span="12">
      <h5 class="font-weight-bold">Job Name</h5>
      <p>{{ data.jobTitle || '-' }}</p>
    </a-col>
    <a-col :span="12">
      <h5 class="font-weight-bold">Department Name</h5>
      <p>{{ data.departmentName || '-' }}</p>
    </a-col>
    <a-col :span="12">
      <h5 class="font-weight-bold">Salary</h5>
      <p>{{ data.salary || '-' }}</p>
    </a-col>
  </a-row>

</template>
<script lang="ts">
import {defineComponent, onMounted, ref} from 'vue'
import {useRoute} from 'vue-router';
import Services from "../../services";
import EditEmployee from "./EditEmployee.vue";

export default defineComponent({
  components: {EditEmployee},
  setup() {
    const data = ref<Object>({});
    const route = useRoute()
    let id = route.params.id
    onMounted(() => {
      _getDetailEmployee(id)
    })
    const _getDetailEmployee = async (ids: any) => {
      try {
        const res = await Services._getDetailEmployee(ids);
        data.value = res.data.detail
      } catch (e) {
        console.log(e)
      }
    }
    return {
      data
    }
  },
})
</script>
<style scoped>
</style>
