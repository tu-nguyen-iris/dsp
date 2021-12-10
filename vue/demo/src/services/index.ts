import {message, notification} from "ant-design-vue";
import axios from "axios";
import {h} from "vue";
import {WarningOutlined} from "@ant-design/icons-vue";

class Services {
    public link: string = "http://localhost:8762/api/"
    _exData = (data: any) => {
        if (data && data.data.code == 0) {
            notification.open({
                message: data.data.message,
                description:
                    'Has an error. Try again',
                icon: h(WarningOutlined, {style: 'color: red'}),
                duration: 3,
            });
            throw data.data.message
        } else if (data && data.data.code == -1) {
            message.error(data.data.message)
            window.location.href = '/login'
        } else return data
    }

    _initHeader<AxiosRequestHeaders>() {
        return {
            'Content-Type': 'application/json',
            token: localStorage.getItem('token') || ''
        }
    }

    _getAllEmployee = async (obj: object) => {
        try {
            let res = await axios.get(this.link + 'users/employees',
                {
                    params: {...obj},
                    headers: this._initHeader()
                }
            )
            return this._exData(res).data.response
        } catch (error) {
            throw error
        }
    }

    _getDetailEmployee = async (id: bigint) => {
        try {
            let res = await axios.get(this.link + `users/employees/${id}`)
            return this._exData(res).data.response
        } catch (error) {
            throw error
        }
    }
    _getAllJobs = async () => {
        try {
            let res = await axios.get(this.link + 'users/job',
            )
            return this._exData(res).data.response
        } catch (error) {
            throw error
        }
    }
    _getAllDepartments = async () => {
        try {
            let res = await axios.get(this.link + 'users/departments',)
            return this._exData(res).data.response
        } catch (error) {
            throw error
        }
    }
    _editEmployees = async (obj: object) => {
        try {
            let res = await axios.post(this.link + 'users/employees',
                obj
            )
            return this._exData(res)
        } catch (e) {
            throw e
        }
    }
    _addEmployeees = async (obj: object) => {
        try {
            let res = await axios.post(this.link + 'users/employees',
                obj
            )
            return this._exData(res)
        } catch (e) {
            throw e
        }
    }
    _delMultiusers = async (arr: number[]) => {
        try {
            let res = await axios.post(this.link + 'users/employees/delete_employee',
                arr,
                {
                    headers: this._initHeader()
                }
            )
            return this._exData(res)
        } catch (e) {
            throw e
        }
    }

}

const
    instance = new Services();
Object
    .freeze(instance);

export default instance;
