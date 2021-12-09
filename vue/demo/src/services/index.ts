import {message} from "ant-design-vue";
import axios from "axios";

class Services {
    public link: string = "http://localhost:8762/api/"
    _exData = (data: any) => {
        if (data && data.data.code == 0) {
            message.error(data.data.message)
        } else if (data && data.data.code == -1) {
            message.error(data.data.message)
            window.location.href = '/login'
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
            let res = await axios.get(this.link + 'users/employees',
                {
                    params: {...obj},
                    headers: this._initHeader()
                }
            )
            return res.data.response
        } catch (error) {
            throw error
        }
    }

    _getDetailEmployee = async (id: bigint) => {
        try {
            let res = await axios.get(this.link + `users/employees/${id}`)
            this._exData(res)
            return res.data.response
        } catch (error) {
            throw error
        }
    }
    _getAllJobs = async () => {
        try {
            let res = await axios.get(this.link + '/job',
            )
            this._exData(res)
            return res.data
        } catch (error) {
            throw error
        }
    }
    _getAllDepartments = async () => {
        try {
            let res = await axios.get(this.link + '/departments',)
            this._exData(res)
            return res.data
        } catch (error) {
            throw error
        }
    }
    _editEmployees = async (obj: object) => {
        try {
            let res = await axios.post(this.link + '/employees',
                obj
            )
            this._exData(res)
            return res.data
        } catch (e) {
            throw e
        }
    }
    _addEmployeees = async (obj: object) => {
        try {
            let res = await axios.post(this.link + '/employees',
                obj
            )
            this._exData(res)
            return res.data
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
