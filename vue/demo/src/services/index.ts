import {message} from "ant-design-vue";
import axios from "axios";

class Services {
    public link: string = "http://localhost:8080/api/v1"

    _getAllEmployee = async (obj: object) => {
        try {
            let res = await axios.get(this.link + '/employees',
                {params: {...obj}}
            )
            return res
        } catch (error) {
            throw error
        }
    }

    _getDetailEmployee = async (id: bigint) => {
        try {
            let res = await axios.get(this.link + `/employees/${id}`)
            return res
        } catch (error) {
            throw error
        }
    }


}

const instance = new Services();
Object.freeze(instance);
export default instance;
