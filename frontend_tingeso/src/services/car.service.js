import httpCar from "../http-common";

const getAll = () => {
    return httpCar.get('/api/v1/car/');
}

const create = data => {
    return httpCar.post("/api/v1/car/", data);
}

const get = patent => {
    return httpCar.get(`/api/v1/car/patent/${patent}`);
}

const update = data => {
    return httpCar.put('/api/v1/car/', data);
}

const remove = id => {
    return httpCar.delete(`/api/v1/car/${id}`);
}
export default { getAll, create, get, update, remove };