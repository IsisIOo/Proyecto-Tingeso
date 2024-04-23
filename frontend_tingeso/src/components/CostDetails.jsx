import { useState, useEffect } from "react";
import { Link, useParams, useNavigate } from "react-router-dom";
import carService from "../services/repair.service";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import FormControl from "@mui/material/FormControl";
import MenuItem from "@mui/material/MenuItem";
import SaveIcon from "@mui/icons-material/Save";
import Paper from "@mui/material/Paper";

const CostDetails = () => {
  const [patent, setPatent] = useState("");
  const [totalOriginal, settotalOriginal] = useState();
  const [discountPerDay, setdiscountPerDay] = useState();
  const [discountPerbonus, setdiscountPerbonus] = useState();
  const [delayCharge, setdelayCharge] = useState();
  const [mileageCharge, setmileageCharge] = useState();
  const [seniorityCharge, setseniorityCharge] = useState();
  const [totalAmount, settotalAmount] = useState();
  const [category, setCategory] = useState("");
  const { id } = useParams();
  const [titleRepairForm, setTitleRepairForm] = useState("");
  const navigate = useNavigate();

  const saveRepair = (e) => {
    e.preventDefault();

    const repair = { patent, totalOriginal, discountPerDay, discountPerbonus, delayCharge, mileageCharge, seniorityCharge, totalAmount, id };
    console.log(repair);

  };

  useEffect(() => {
    if (id) {
      setTitleCarForm("Editar Auto");
      carService
        .get(id)
        .then((repair) => {
          setPatent(repair.data.patent);
          setBrand(repair.data.totalOriginal);
          setModel(repair.data.discountPerDay);
          setType(repair.data.discountPerbonus);
          setProductionyear(repair.data.delayCharge);
          setMotortype(repair.data.mileageCharge);
          setNumberseats(repair.data.seniorityCharge);
          setKilometers(repair.data.totalAmount);
        })
        .catch((error) => {
          console.log("Se ha producido un error.", error);
        });
    } else {
      setTitleRepairForm("Nuevo Auto");
    }
  }, []);

  return (
    <Paper >
    <Box
      display="flex"
      flexDirection="column"
      alignItems="center"
      justifyContent="center"
    >
      <h3> {titleRepairForm} </h3>

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
            id="totalOriginal"
            label="Total reparación"
            value={totalOriginal}
            variant="standard"
            onChange={(e) => settotalOriginal(e.target.value)}
          />
        </FormControl>

        <FormControl fullWidth>
          <TextField
            id="discountPerDay"
            label="Descuento dia"
            value={discountPerDay}
            variant="standard"
            onChange={(e) => setdiscountPerDay(e.target.value)}
          />
        </FormControl>

        <FormControl fullWidth>
          <TextField
            id="discountPerbonus"
            label="Tipo"
            //type="number"
            value={discountPerbonus}
            variant="standard"
            onChange={(e) => setdiscountPerbonus(e.target.value)}
            //helperText="Salario mensual en Pesos Chilenos"
          />
        </FormControl>

        <FormControl fullWidth>
          <TextField
            id="delayCharge"
            label="Año de producción"
            type="delayCharge"
            value={delayCharge}
            variant="standard"
            onChange={(e) => setdelayCharge(e.target.value)}
          />
        </FormControl>

        <FormControl fullWidth>
          <TextField
            id="mileageCharge"
            label="Tipo de motor"
            value={mileageCharge}
            variant="standard"
            onChange={(e) => setmileageCharge(e.target.value)}
          />
        </FormControl>

        <FormControl fullWidth>
          <TextField
            id="seniorityCharge"
            label="Cantidad de asientos"
            value={seniorityCharge}
            select
            variant="standard"
            defaultValue="1"
            onChange={(e) => setseniorityCharge(e.target.value)}
            style={{ width: "25%" }}
          >
          </TextField>
        </FormControl>

        <FormControl fullWidth>
          <TextField
          id="totalAmount"
          label="Kilometros"
          type="number"
          value={totalAmount}
          variant="standard"
          onChange={(e) => settotalAmount(e.target.value)}
          />
        </FormControl>

        <FormControl>
          <br />
          <Button
            variant="contained"
            color="info"
            onClick={(e) => saveRepair(e)}
            style={{ marginLeft: "0.5rem", color  : "white", backgroundColor: "#D6589F"}}
            startIcon={<SaveIcon />}
          >
            Grabar
          </Button>
        </FormControl>
      </form>
      <hr />
      <Link to="/car/list">Back to List</Link>
    </Box>
    </Paper>
  );
};

export default CostDetails;
