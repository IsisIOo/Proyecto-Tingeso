import { useState, useEffect } from "react";
import { Link, useParams, useNavigate } from "react-router-dom";
import carService from "../services/car.service";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import FormControl from "@mui/material/FormControl";
import MenuItem from "@mui/material/MenuItem";
import SaveIcon from "@mui/icons-material/Save";

const AddEditCar = () => {
  const [patent, setPatent] = useState("");
  const [brand, setBrand] = useState("");
  const [model, setModel] = useState("");
  const [type, setType] = useState("");
  const [productionYear, setProductionyear] = useState();
  const [motorType, setMotortype] = useState("");
  const [numberSeats, setNumberseats] = useState();
  const [kilometers, setKilometers] = useState();
  const [category, setCategory] = useState("");
  const { id } = useParams();
  const [titleCarForm, setTitleCarForm] = useState("");
  const navigate = useNavigate();

  const saveCar = (e) => {
    e.preventDefault();

    const car = { patent, brand, model, type, productionYear, motorType, numberSeats, kilometers, id };
    console.log(car);

    if (id) {
      //Actualizar Datos Empelado
      carService
        .update(car)
        .then((response) => {
          console.log("Auto ha sido actualizado.", response.data);
          navigate("/car/list");
        })
        .catch((error) => {
          console.log(
            "Ha ocurrido un error al intentar actualizar datos del auto.",
            error
          );
        });
    } else {
      //Crear nuevo empleado
      carService
        .create(car)
        .then((response) => {
          console.log("Auto ha sido añadido.", response.data);
          navigate("/car/list");
        })
        .catch((error) => {
          console.log(
            "Ha ocurrido un error al intentar crear nuevo auto.",
            error
          );
        });
    }
  };

  useEffect(() => {
    if (id) {
      setTitleCarForm("Editar Auto");
      carService
        .get(id)
        .then((car) => {
          setPatent(car.data.patent);
          setBrand(car.data.brand);
          setModel(car.data.model);
          setType(car.data.type);
          setProductionyear(car.data.productionYear);
          setMotortype(car.data.motorType);
          setNumberseats(car.data.numberSeats);
          setKilometers(car.data.kilometers);
        })
        .catch((error) => {
          console.log("Se ha producido un error.", error);
        });
    } else {
      setTitleCarForm("Nuevo Auto");
    }
  }, []);

  return (
    <Box
      display="flex"
      flexDirection="column"
      alignItems="center"
      justifyContent="center"
    >
      <h3> {titleCarForm} </h3>

      <form>
        <FormControl fullWidth>
          <TextField
            id="patent"
            label="Patente"
            value={patent}
            variant="standard"
            onChange={(e) => setPatent(e.target.value)}
            helperText="Ej. ABC123"
          />
        </FormControl>

        <FormControl fullWidth>
          <TextField
            id="brand"
            label="Marca"
            value={brand}
            variant="standard"
            onChange={(e) => setBrand(e.target.value)}
          />
        </FormControl>

        <FormControl fullWidth>
          <TextField
            id="model"
            label="Modelo"
            value={model}
            variant="standard"
            onChange={(e) => setModel(e.target.value)}
          />
        </FormControl>

        <FormControl fullWidth>
          <TextField
            id="type"
            label="Tipo"
            //type="number"
            value={type}
            variant="standard"
            onChange={(e) => setType(e.target.value)}
            //helperText="Salario mensual en Pesos Chilenos"
          />
        </FormControl>

        <FormControl fullWidth>
          <TextField
            id="productionYear"
            label="Año de producción"
            type="productionYear"
            value={productionYear}
            variant="standard"
            onChange={(e) => setProductionyear(e.target.value)}
          />
        </FormControl>

        <FormControl fullWidth>
          <TextField
            id="motorType"
            label="Tipo de motor"
            value={motorType}
            variant="standard"
            onChange={(e) => setMotortype(e.target.value)}
          />
        </FormControl>

        <FormControl fullWidth>
          <TextField
            id="numberSeats"
            label="Cantidad de asientos"
            value={numberSeats}
            select
            variant="standard"
            defaultValue="1"
            onChange={(e) => setNumberseats(e.target.value)}
            style={{ width: "25%" }}
          >
            <MenuItem value={1}>1</MenuItem>
            <MenuItem value={2}>2</MenuItem>
            <MenuItem value={3}>3</MenuItem>
            <MenuItem value={4}>4</MenuItem>
          </TextField>
        </FormControl>

        <FormControl fullWidth>
          <TextField
          id="kilometers"
          label="Kilometros"
          type="number"
          value={kilometers}
          variant="standard"
          onChange={(e) => setKilometers(e.target.value)}
          helperText="Kilometros del auto."
          />
        </FormControl>

        <FormControl>
          <br />
          <Button
            variant="contained"
            color="info"
            onClick={(e) => saveCar(e)}
            style={{ marginLeft: "0.5rem" }}
            startIcon={<SaveIcon />}
          >
            Grabar
          </Button>
        </FormControl>
      </form>
      <hr />
      <Link to="/car/list">Back to List</Link>
    </Box>
  );
};

export default AddEditCar;
