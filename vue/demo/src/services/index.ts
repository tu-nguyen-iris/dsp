import {message, notification} from "ant-design-vue";
import axios from "axios";
import {h} from "vue";
import {WarningOutlined} from "@ant-design/icons-vue";
import router from "../routes";

class Services {
    public link: string = "http://localhost:8762/"
    _exData = async (data: any) => {
        try {
            const resonse = await data
            console.log(resonse)
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
            } else {
                router.push('/login')
            }
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
            "Access-Control-Allow-Origin": "*",
            'Content-Type': 'application/json;charset=UTF-8',
            Authorization: 'Bearer ' + localStorage.getItem('token') || '',
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

            let res = await this._exData(axios.get(this.link + `users/employees/${id}`, {
                headers: this._initHeader()
            }))
            return res.data.response
        } catch (error) {
            throw error
        }
    }
    _getAllJobs = async () => {
        try {
            let res = await this._exData(await axios.get(this.link + 'users/job', {
                headers: this._initHeader()
            }))
            return res.data.response
        } catch (error) {
            throw error
        }
    }
    _getAllDepartments = async () => {
        try {
            let res = await this._exData(axios.get(this.link + 'users/departments', {
                headers: this._initHeader()
            }))
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
