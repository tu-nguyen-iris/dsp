<template>
  <a-button class="d-block" style="margin-left: auto" type="primary" @click="showDrawer">Edit</a-button>
  <a-drawer
      :width="800"
      title="Edit Employee"
      :placement="placement"
      :visible="visible"
      @close="onClose"
  >
    <template #extra>
      <a-button style="margin-right: 8px" @click="onClose">Cancel</a-button>
      <a-button type="primary" @click="onClose">Submit</a-button>
    </template>
    <!--   Body-->
    <a-form :model="formState" :label-col="labelCol" :wrapper-col="wrapperCol">
      <a-form-item label="First name">
        <a-input v-model:value="formState.firstName"/>
      </a-form-item>

      <a-form-item label="Last name">
        <a-input v-model:value="formState.lastName"/>
      </a-form-item>

      <a-form-item label="Email">
        <a-input v-model:value="formState.email"/>
      </a-form-item>

      <a-form-item label="Phone">
        <a-input v-model:value="formState.phoneNumber"/>
      </a-form-item>

      <a-form-item label="Hire Date">
        <a-date-picker
            v-model:value="formState.hireDate"
            type="date"
            placeholder="Pick a date"
            style="width: 100%"
        />
      </a-form-item>

      <a-form-item label="Salary name">
        <a-input v-model:value="formState.salary"/>
      </a-form-item>

      <a-form-item label="Job">
        <a-input v-model:value="formState.jobTitle"/>
      </a-form-item>

      <a-form-item label="Department name">
        <a-input v-model:value="formState.departmentName"/>
      </a-form-item>

    </a-form>
  </a-drawer>
</template>
<script lang="ts">
import {defineComponent, ref, reactive, toRaw} from 'vue';
import type {UnwrapRef} from 'vue';
import {Dayjs} from 'dayjs';

interface FormState {
  name: string;
  region: string | undefined;
  date1: Dayjs | undefined;
  delivery: boolean;
  type: string[];
  resource: string;
  desc: string;
}

export default defineComponent({
  props: ['data'],
  setup(props) {
    const data = ref(props.data)
    const formState: UnwrapRef<FormState> = reactive({
      name: '',
      region: undefined,
      date1: undefined,
      delivery: false,
      type: [],
      resource: '',
      desc: '',
    });
    const onSubmit = () => {
      console.log('submit!', toRaw(formState));
    };
    const placement = ref<string>('right');
    const visible = ref<boolean>(false);

    const showDrawer = () => {
      visible.value = true;
    };

    const onClose = () => {
      visible.value = false;
    };
    return {
      placement,
      visible,
      showDrawer,
      onClose,
      labelCol: {span: 4},
      wrapperCol: {span: 14},
      formState,
      onSubmit,
      data
    };
  },
});
</script>
