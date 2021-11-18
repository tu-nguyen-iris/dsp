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
    <a-form :rules="rules"
            ref="formRef" :model="formState" :label-col="labelCol" :wrapper-col="wrapperCol">
      <a-form-item name="firstName" label="First name">
        <a-input v-model:value="formState.firstName"/>
      </a-form-item>

      <a-form-item name="lastName" label="Last name">
        <a-input v-model:value="formState.lastName"/>
      </a-form-item>

      <a-form-item name="email" label="Email">
        <a-input v-model:value="formState.email"/>
      </a-form-item>

      <a-form-item name="phoneNumber" label="Phone">
        <a-input v-model:value="formState.phoneNumber"/>
      </a-form-item>

      <a-form-item name="hireDate" label="Hire Date">
        <a-date-picker
            v-model:value="formState.hireDate"
            type="date"
            placeholder="Pick a date"
            style="width: 100%"
        />
      </a-form-item>

      <a-form-item name="salary" label="Salary name">
        <a-input v-model:value="formState.salary"/>
      </a-form-item>

      <a-form-item name="jobId" label="Job">
        <a-input v-model:value="formState.jobId"/>
      </a-form-item>

      <a-form-item name="departmentId" label="Department name">
        <a-input v-model:value="formState.departmentId"/>
      </a-form-item>
      <a-form-item :wrapper-col="{ span: 14, offset: 4 }">
        <a-button type="primary" @click.prevent="onSubmit">Create</a-button>
      </a-form-item>
    </a-form>
  </a-drawer>
</template>
<script lang="ts">
import {defineComponent, ref, reactive, toRaw, onMounted} from 'vue';
import type {UnwrapRef} from 'vue';
import {Dayjs} from 'dayjs';
import Services from "../../services";

interface FormState {
  firstName: string,
  lastName: string,
  hireDate: bigint,
  salary: string,
  departmentId: bigint,
  jobId: bigint,
  email: string,
  phoneNumber: string
}

export default defineComponent({
  props: ['id'],
  setup(props) {
    onMounted(() => {
      _getDetailEmployee([props.id])
    })
    const _getDetailEmployee = async (ids: any) => {
      try {
        const res = await Services._getDetailEmployee(ids);
        formState.firstName = res.data.detail.firstName;
        formState.lastName = res.data.detail.lastName;
        formState.hireDate = res.data.detail.hireDate;
        formState.salary = res.data.detail.salary;
        formState.departmentId = res.data.detail.departmentId;
        formState.jobId = res.data.detail.jobId;
        formState.phoneNumber = res.data.detail.phoneNumber;
        formState.email = res.data.detail.email;

      } catch (e) {
        console.log(e)
      }
    }

    const rules = {
      firstName: [
        {
          required: true,
          message: 'Please input',
        },
        {
          min: 2,
          max: 20,
          message: 'Length should be 3 to 5',
          trigger: 'blur',
        },
      ],
      phoneNumber: [
        {
          required: true,
          message: 'Please input',
        },
        {
          min: 2,
          max: 20,
          message: 'Length should be 3 to 5',
          trigger: 'blur',
        },
      ],
      lastName: [
        {
          required: true,
          message: 'Please input',
        },
        {
          min: 2,
          max: 20,
          message: 'Length should be 3 to 5',
          trigger: 'blur',
        },
      ],
      hireDare: [
        {
          required: true,
          message: 'Please typ',
        },
      ],
      salary: [
        {
          required: true,
          message: 'Please typ',
        },
      ],
      departmentId: [
        {
          required: true,
          message: 'Please typ',
        },
      ],
      jobId: [
        {
          required: true,
          message: 'Please typ',
        },
      ],
      email: [
        {
          required: true,
          message: 'Please typ',
        },
        {
          email: true,
          message: 'Shound be email format',
          trigger: 'blur',
        }
      ],
    };
    const formRef = ref();
    const formState: UnwrapRef<FormState> = reactive({
      firstName: null,
      lastName: null,
      hireDate: null,
      salary: null,
      departmentId: null,
      jobId: null,
      phoneNumber: null,
      email: null,
    });
    const onSubmit = () => {
      formRef.value
          .validate()
          .then(() => {
            console.log('values', formState, toRaw(formState));
          })
          .catch((error : object) => {
            console.log('error', error);
          });
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
      rules,
      formRef
    };
  },
});
</script>
