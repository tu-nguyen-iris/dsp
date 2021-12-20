import {message, notification} from "ant-design-vue";
import axios from "axios";
import {h} from "vue";
import {WarningOutlined} from "@ant-design/icons-vue";
import router from "../routes";

class Services {
    private static instance: Services;

    public static getInstance(): Services {
        if (!Services.instance) {
            Services.instance = new Services();
        }

        return Services.instance;
    }

    public link: string = "http://localhost:8762/"
    _exData = async (data: any) => {
        try {
            const reseponse = await data
            if (reseponse.data.code == 0) {
                notification.open({
                    message: reseponse.data.message,
                    icon: h(WarningOutlined, {style: 'color: red'}),
                    description: reseponse.data.message,
                    duration: 3,
                });
                throw reseponse.data.message
            } else if (reseponse.data.code == -1) {
                notification.open({
                    message: reseponse.data.message,
                    description: reseponse.data.message,
                    icon: h(WarningOutlined, {style: 'color: red'}),
                    duration: 3,
                });
                router.push('/login')
            }
            return reseponse
        } catch (e) {
            throw e
        }
    }

    _initHeader<AxiosRequestHeaders>() {
        return {
            "Access-Control-Allow-Origin": "*",
            'Content-Type': 'application/json;charset=UTF-8',
            Authorization: ('Bearer ' + localStorage.getItem('token')) || '',
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
                obj,
                {
                    headers: this._initHeader()
                }
            )
            return this._exData(res)
        } catch (e) {
            throw e
        }
    }
    _addEmployeees = async (obj: object) => {
        try {
            let res = await axios.post(this.link + 'users/employees',
                obj,
                {headers: this._initHeader()}
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
    _register = async (obj: { username: string, password: string }) => {
        try {
            const res = await this._exData(axios.post(this.link + 'register',
                obj
            ))

            return res.data.response
        } catch (e) {
            throw e
        }
    }
    _login = async (obj: { username: string, password: string }) => {
        try {
            const res = await this._exData(axios.post(this.link + 'login',
                obj
            ))
            return res.data
        } catch (e) {
            throw e
        }
    }
}

const instance = Services.getInstance();
Object.freeze(instance);
export default instance;
