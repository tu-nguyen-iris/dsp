import {message} from "ant-design-vue";
import axios from "axios";

class Services {
    public link: string = "http://localhost:8081/api/v1"
    _exData = (data: any) => {
        if (data && data.data.code !== 1) {
            message.error(data.data.message)
        }
    }
    _getAllEmployee = async (obj: object) => {
        try {
            let res = await axios.get(this.link + '/employees',
                {params: {...obj}}
            )
            this._exData(res)
            return res.data
        } catch (error) {
            throw error
        }
    }

    _getDetailEmployee = async (id: bigint) => {
        try {
            let res = await axios.get(this.link + `/employees/${id}`)
            this._exData(res)
            return res.data
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
            let res = await axios.get(this.link + '/departments',
            )
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
