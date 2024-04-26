import httpCar from "../http-common";
import httpRepair from "../http-common";


const getAll = () => {
    return httpCar.get('/api/v1/car/allCost/');
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


export default { getAll, calculate, discounts, recharges, rechargedelay, rechargeantiguedad, rechargemile, discountday, discountmarca, costorepair };