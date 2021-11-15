<template>


</template>
<script lang="ts">
import {defineComponent, onMounted} from 'vue'
import {useRoute} from 'vue-router';
import Services from "../../services";

export default defineComponent({
  setup() {
    const route = useRoute()
    let id  = route.params.id
    onMounted(() => {
      _getDetailEmployee(id)
    })
    const _getDetailEmployee = async (ids : any) => {
      try {
        const res = await Services._getDetailEmployee(ids)
      } catch (e) {
        console.log(e)
      }
    }
  },
})
</script>
<style scoped>
</style>
