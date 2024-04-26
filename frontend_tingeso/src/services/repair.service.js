import httpCommon from "../http-common";

const getAll = () => {
    return httpCommon.get('/api/v1/repairs/');
}

const getTotalAmount = patent => {
    return httpCommon.get(`/api/v1/repairs/patent/${patent}`);
}

const CreateRepair = patente => {
    return httpRepair.post(`/api/v1/repairs/${patente}`);}

export default { getAll, getTotalAmount, CreateRepair};