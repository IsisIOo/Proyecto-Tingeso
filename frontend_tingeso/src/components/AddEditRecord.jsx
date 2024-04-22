import { useState, useEffect } from "react";
import { Link, useParams, useNavigate } from "react-router-dom";
import recordService from "../services/record.service";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import FormControl from "@mui/material/FormControl";
import MenuItem from "@mui/material/MenuItem";
import SaveIcon from "@mui/icons-material/Save";

const AddEditRecord = () => {
  const [patent, setPatent] = useState("");
  const [admissionDate, setadmissionDate] = useState("");
  const [admissionHour, setadmissionHour] = useState("");
  const [repairType, setrepairType] = useState("");
  const [totalAmount, settotalAmount] = useState();
  const [departureDate, setdepartureDate] = useState("");
  const [departureHour, setdepartureHour] = useState();
  const [clientDate, setclientDate] = useState("");
  const [clientHour, setclientHour] = useState();
  const [category, setCategory] = useState("");
  const { id } = useParams();
  const [titleRecordForm, setTitleRecordForm] = useState("");
  const navigate = useNavigate();

  const saveRecord = (e) => {
    e.preventDefault();

    const record = { patent, admissionDate, admissionHour, repairType, totalAmount, departureDate, departureHour, clientDate, clientHour, id };
    console.log(record);

    if (id) {
      //Actualizar Datos Empelado
      recordService
        .update(record)
        .then((response) => {
          console.log("Historial ha sido actualizado.", response.data);
          navigate("/record/list");
        })
        .catch((error) => {
          console.log(
            "Ha ocurrido un error al intentar actualizar datos del historial.",
            error
          );
        });
    } else {
      //Crear nuevo empleado
      recordService
        .create(record)
        .then((response) => {
          console.log("Historial ha sido añadido.", response.data);
          navigate("/record/list");
        })
        .catch((error) => {
          console.log(
            "Ha ocurrido un error al intentar crear nuevo historial.",
            error
          );
        });
    }
  };

  useEffect(() => {
    if (id) {
      setTitleRecordForm("Editar Historial");
      recordService
        .get(id)
        .then((record) => {
          setPatent(record.data.patent);
          setadmissionDate(record.data.admissionDate);
          setadmissionHour(record.data.admissionHour);
          setrepairType(record.data.repairType);
          settotalAmount(record.data.totalAmount);
          setdepartureDate(record.data.departureDate);
          setclientDate(record.data.clientDate);
          setclientHour(record.data.clientHour);
        })
        .catch((error) => {
          console.log("Se ha producido un error.", error);
        });
    } else {
      setTitleRecordForm("Nuevo Historial");
    }
  }, []);

  return (
    <Box
      display="flex"
      flexDirection="column"
      alignItems="center"
      justifyContent="center"
    >
      <h3> {titleRecordForm} </h3>

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
            id="admissionDate"
            label="Fecha admision"
            value={admissionDate}
            variant="standard"
            onChange={(e) => setadmissionDate(e.target.value)}
          />
        </FormControl>

        <FormControl fullWidth>
          <TextField
            id="admissionHour"
            label="Hora de admision"
            value={admissionHour}
            variant="standard"
            onChange={(e) => setadmissionHour(e.target.value)}
          />
        </FormControl>

        <FormControl fullWidth>
          <TextField
            id="repairType"
            label="Tipo de reparacion"
            //type="number"
            value={repairType}
            variant="standard"
            onChange={(e) => setrepairType(e.target.value)}
            //helperText="Salario mensual en Pesos Chilenos"
          />
        </FormControl>

        <FormControl fullWidth>
          <TextField
            id="totalAmount"
            label="Total a pagar"
            type="totalAmount"
            value={totalAmount}
            variant="standard"
            onChange={(e) => settotalAmount(e.target.value)}
          />
        </FormControl>

        <FormControl fullWidth>
          <TextField
            id="departureDate"
            label="Fecha de retiro"
            value={departureDate}
            variant="standard"
            onChange={(e) => setdepartureDate(e.target.value)}
          />
        </FormControl>

        <FormControl fullWidth>
          <TextField
            id="clientDate"
            label="Fecha retirado"
            value={clientDate}
            variant="standard"
            onChange={(e) => setclientDate(e.target.value)}
          />
        </FormControl>

        <FormControl fullWidth>
          <TextField
          id="clientHour"
          label="Hora retirado"
          value={clientHour}
          variant="standard"
          onChange={(e) => setclientHour(e.target.value)}
          />
        </FormControl>

        <FormControl>
          <br />
          <Button
            variant="contained"
            color="info"
            onClick={(e) => saveRecord(e)}
            style={{ marginLeft: "0.5rem" }}
            startIcon={<SaveIcon />}
          >
            Grabar
          </Button>
        </FormControl>
      </form>
      <hr />
      <Link to="/record/list">Back to List</Link>
    </Box>
  );
};

export default AddEditRecord;
