<template>
  <a-button class="d-block" style="margin-left: auto" type="primary" @click="isAdd">Add</a-button>
  <a-drawer
      title="Create employees"
      :width="800"
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
import {defineComponent, ref, reactive, toRaw, onMounted, h} from 'vue';
import type {UnwrapRef} from 'vue';
import {Dayjs} from 'dayjs';
import Services from "../../../services";
import {notification} from "ant-design-vue";
import {SmileOutlined} from "@ant-design/icons-vue";
import router from '../../../routes';

interface FormState {
  firstName: string,
  lastName: string,
  hireDate: number,
  salary: string,
  departmentId: number,
  jobId: number,
  email: string,
  phoneNumber: string
}

interface jobState {
  jobId: number,
  jobTitle: string
}


export default defineComponent({
  setup(props) {
    const lstJob = ref<object>()
    const lstDepartments = ref<object>()
    const _getListJob = async () => {
      try {
        const res = await Services._getAllJobs()
        let newData = res.data.map((item: jobState) => {
          return {
            key: item.jobId,
            value: item.jobTitle
          }
        })
        formState.jobId = newData[0].key
        lstJob.value = newData
      } catch (e: any) {
        console.log(e)
      }
    }
    const _getLisTDepartments = async () => {
      try {
        const res = await Services._getAllDepartments()
        let newData = res.data.map((item: any) => {
          return {
            key: item.id,
            value: item.departmentName
          }
        })
        formState.departmentId = newData[0].key
        lstDepartments.value = newData
      } catch (e: any) {
        console.log(e)
      }
    }
    onMounted(() => {
      _getLisTDepartments()
      _getListJob()
    })

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
    const formState = reactive<FormState>({
      firstName: '',
      lastName: '',
      hireDate: 0,
      salary: '',
      departmentId: 0,
      jobId: 0,
      phoneNumber: '',
      email: '',
    });
    const onSubmit = () => {
      formRef.value
          .validate()
          .then(async () => {
                const res = await Services._addEmployeees(toRaw(formState))
                notification.open({
                  message: 'Create employee success',
                  description:
                      'This is the content of demo hehe.',
                  icon: h(SmileOutlined, {style: 'color: #108ee9'}),
                  duration: 2,
                });
                router.push({
                  name: 'ListEmployee'
                })
                visible.value = false
              }
          )
          .catch((error: object) => {
            console.log('error', error);
          });
    };
    const placement = ref<string>('right');
    const visible = ref<boolean>(false);
    const isAdd = () => {
      visible.value = true;
    }
    const onClose = () => {
      visible.value = false;
    };
    return {
      placement,
      visible,
      onClose,
      labelCol: {span: 4},
      wrapperCol: {span: 14},
      formState,
      onSubmit,
      rules,
      formRef,
      lstJob,
      lstDepartments,
      isAdd,
    };
  },
})
;
</script>
