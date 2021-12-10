import {message, notification} from "ant-design-vue";
import axios from "axios";
import {h} from "vue";
import {WarningOutlined} from "@ant-design/icons-vue";

class Services {
    public link: string = "http://localhost:8762/api/"
    _exData = async (data: any) => {
        try {
            const resonse = await data
            if (resonse.data.code == 0) {
                notification.open({
                    message: data.data.message,
                    description:
                        'Has an error. Try again',
                    icon: h(WarningOutlined, {style: 'color: red'}),
                    duration: 3,
                });
                throw resonse.data.message
            } else if (resonse.data.code == -1) {
                message.error(data.data.message)
                window.location.href = '/login'
            } else return data
            return resonse
        } catch (e) {
            notification.open({
                message: 'Has an error',
                description:
                    'Has an error. Try again',
                icon: h(WarningOutlined, {style: 'color: red'}),
                duration: 3,
            });
            throw e
        }
    }

    _initHeader<AxiosRequestHeaders>() {
        return {
            'Content-Type': 'application/json',
            token: localStorage.getItem('token') || ''
        }
    }

    _getAllEmployee = async (obj: object) => {
        try {
            const res = await this._exData(await axios.get(this.link + 'users/employees',
                {
                    params: {...obj},
                    headers: this._initHeader()
                }
            ))

            return res.data.response
        } catch (error) {
            throw error
        }
    }

    _getDetailEmployee = async (id: bigint) => {
        try {

            let res = await this._exData(axios.get(this.link + `users/employees/${id}`))
            return res.data.response
        } catch (error) {
            throw error
        }
    }
    _getAllJobs = async () => {
        try {
            let res = await this._exData(await axios.get(this.link + 'users/job'))
            return res.data.response
        } catch (error) {
            throw error
        }
    }
    _getAllDepartments = async () => {
        try {
            let res = await this._exData(axios.get(this.link + 'users/departments'))
            return res.data.response
        } catch (error) {
            throw error
        }
    }
    _editEmployees = async (obj: object) => {
        try {
            let res = await axios.post(this.link + 'users/employees/edit',
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
            const res = await this._exData(axios.post(this.link + 'users/employees/delete_employee',
                arr,
                {
                    headers: this._initHeader()
                }
            ))

            return res.data.response
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
