import httpCar from "../http-common";
import httpRepair from "../http-common";


const getAll = () => {
    return httpRepair.get('/api/v1/repairs/');
}


const get = () => {
    return httpRepair.get(`/api/v1/repairs/patentRepair/${patent}`);
}

const calculate = patent => {
    return httpRepair.get(`/api/v1/repairs/patent/${patent}`);
}

const discounts = patent => {
    return httpRepair.get(`/api/v1/repairs/discounts/${patent}`);
}

const recharges = patent => {
    return httpRepair.get(`/api/v1/repairs/recharges/${patent}`);
}

const rechargedelay = patent => {
    return httpRepair.get(`/api/v1/repairs/recharge-delay/${patent}`);
}

const rechargeantiguedad = patent => {
    return httpRepair.get(`/api/v1/repairs/recharge-senior/${patent}`);
}

const rechargemile = patent => {
    return httpRepair.get(`/api/v1/repairs/recharge-mile/${patent}`);
}

const discountday = patent => {
    return httpRepair.get(`/api/v1/repairs/discount-dia/${patent}`);
}

const discountmarca = patent => {
    return httpRepair.get(`/api/v1/repairs/discount-marca/${patent}`);
}

const costorepair = patent => {
    return httpRepair.get(`/api/v1/repairs/repaircost/${patent}`);
}

const CALCULATODO = patent => {
    return httpRepair.post(`/api/v1/repairs/${patent}`);
}


//obitene todos los repairs asociado a un solo auto
const getAllrepairs1 = patent => {
    return httpRepair.get(`/api/v1/repairs/all/${patent}`);
}

const remove = id => {
    return httpRepair.delete(`/api/v1/repairs/delete/${id}`);
}


export default { getAll, get, remove, getAllrepairs1, calculate, discounts, recharges, rechargedelay, rechargeantiguedad, rechargemile, discountday, discountmarca, costorepair, CALCULATODO };