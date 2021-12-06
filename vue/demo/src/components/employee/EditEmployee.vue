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

      <a-form-item label="Jobs">
        <a-select v-model:value="formState.jobId" placeholder="please select your job">
          <a-select-option v-for="item in lstJob" :value="item.key">{{ item.value }}</a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item name="departmentId" label="Department name">
        <a-select v-model:value="formState.departmentId" placeholder="please select your job">
          <a-select-option v-for="item in lstDepartments" :value="item.key">{{ item.value }}</a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item :wrapper-col="{ span: 14, offset: 4 }">
        <a-button type="primary" @click.prevent="onSubmit">Done</a-button>
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
  employeeId: number,
  firstName: string,
  lastName: string,
  hireDate: bigint,
  salary: string,
  departmentId: bigint,
  jobId: bigint,
  email: string,
  phoneNumber: string
}

interface jobState {
  jobId: number,
  jobTitle: string
}

export default defineComponent({
  props: ['id'],
  setup(props) {
    const lstJob = ref<object>()
    const lstDepartments = ref<object>()

    const _getListJob = async () => {
      try {
        const res = await Services._getAllJobs()
        let newData = res.data.data.map((item: jobState) => {
          return {
            key: item.jobId,
            value: item.jobTitle
          }
        })
        lstJob.value = newData
      } catch (e: any) {
        console.log(e)
      }
    }
    const _getLisTDepartments = async () => {
      try {
        const res = await Services._getAllDepartments()
        let newData = res.data.data.map(item => {
          return {
            key: item.id,
            value: item.departmentName
          }
        })
        lstDepartments.value = newData
      } catch (e: any) {
        console.log(e)
      }
    }
    onMounted(() => {
      _getLisTDepartments()
      _getListJob()
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
      employeeId: Number(props.id),
      firstName: '',
      lastName: '',
      hireDate: '',
      salary: '',
      departmentId: '',
      jobId: '',
      phoneNumber: '',
      email: '',
    });
    const onSubmit = () => {
      formRef.value
          .validate()
          .then(async () => {
            const res = await Services._editEmployees(toRaw(formState))
            console.log('values', formState);
          })
          .catch((error: object) => {
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
      formRef,
      lstJob,
      lstDepartments
    };
  },
});
</script>
