<template>
  <a-button @click="delEmployees(selectedRowKeys)" type="primary">Delete</a-button>
  <a-input-search
      @search="onSearch"
      class="search"
      style="width:240px;"
      v-model:value="name"
      placeholder="Search"
      allow-clear
  />
  <a-table
      :rowSelection="selectedRowKeys"
      :customRow="customRow"
      :scroll="{ y: 500 }"
      :pagination="false"
      :loading="loadingTable"
      :columns="columns"
      :data-source="data"
  >
    <template #bodyCell="{ column, text }">
      <template v-if="column.dataIndex === 'name'">{{ text.first }} {{ text.last }}</template>
    </template>
  </a-table>
  <a-pagination
      class="mt-5"
      v-model:current="panigation.current"
      @change="onChangePage"
      :total="panigation.toTalItem"
      show-less-items
  />
</template>
<script lang="ts">
import {computed, defineComponent, h, onMounted, reactive, watchEffect, toRefs} from "vue";
import Services from "../../services";
import {ref} from "vue";
import {message, notification} from "ant-design-vue";
import router from "../../routes";
import {SmileOutlined, WarningOutlined} from '@ant-design/icons-vue';

type Key = string | number;
const columns = [
  {
    title: "ID",
    dataIndex: "employeeId",
    width: "20%",
  },
  {
    title: "FIRST NAME",
    dataIndex: "firstName",
    width: "20%",
  },
  {
    title: "LAST NAME",
    dataIndex: "lastName",
  },
  {
    title: "EMAIL",
    dataIndex: "email",
  },
  {
    title: "PHONENUMBER",
    dataIndex: "phoneNumber",
  },
  {
    title: "HIDE DATE",
    dataIndex: "hireDate",
  },
  {
    title: "JOB TITLE",
    dataIndex: "jobTitle"
  },
  {
    title: "SALARY",
    dataIndex: "salary",
  },
  {
    title: "DEPARTMENT",
    dataIndex: "departmentName",
  },
];

export default defineComponent({
  setup(props) {
    type APIParams = {
      name: string;
      page?: number;
      [key: string]: any;
    };
    const name = ref<string>("");
    const data = ref();
    const loadingTable = ref(true)
    const panigation = ref({
      current: 1,
      totalPage: 0,
      toTalItem: 0
    })
    const lstDel = ref([])
    const selectedRowKeys = {
      onChange: (selectedRowKey: []) => {
        lstDel.value = selectedRowKey
      },
    }
    const onChangePage = (e: number) => {
      loadingTable.value = true;
      panigation.value = ({
        ...panigation.value,
        current: e
      })
      getAllData({name: name.value, page: e});
    }
    const onSearch = (e: string) => {
      name.value = e
      getAllData({name: e, page: panigation.value.current})
    }

    const getAllData = async (params: APIParams) => {
      try {
        const resp = await Services._getAllEmployee(params);
        loadingTable.value = false
        data.value = resp.data;
        panigation.value = {
          ...panigation.value,
          totalPage: resp.totalPages,
          toTalItem: resp.totalItems
        }
      } catch (error) {
        loadingTable.value = false
        console.log(error);
      }
    };
    const customRow = (record: any) => {
      return {
        onClick: (event: any) => {
          router.push({
            name: 'employeeDetail', params: {
              id: record.employeeId
            }
          })
        }
      };
    }
    onMounted(() => {
      getAllData({name: name.value, page: panigation.value.current});
    });
    console.log(lstDel)
    const delEmployees = async () => {
      try {
        if (lstDel.value.length == 0) {
          notification.open({
            message: 'You must choose at least 1 employee to delete',
            description:
                'This is the content of demo hehe.',
            icon: h(WarningOutlined, {style: 'color: red'}),
            duration: 3,
          });
          return
        }
        let lst_employees = lstDel.value.map((item) => {
          return data.value[item].employeeId
        })
        const res = await Services._delMultiusers(lst_employees)
        notification.open({
          message: 'Delete success',
          description:
              'This is the content of demo hehe.',
          icon: h(SmileOutlined, {style: 'color: #108ee9'}),
          duration: 3,
        });
      } catch (e) {
        console.log(e)
      }
    }

    return {
      name,
      data,
      columns,
      panigation,
      loadingTable,
      onChangePage,
      onSearch,
      customRow,
      delEmployees,
      selectedRowKeys
    };
  },
});
</script>
<style scoped>
.search {
  display: flex;
  margin-left: auto;
  margin-bottom: 30px;
}
</style>
