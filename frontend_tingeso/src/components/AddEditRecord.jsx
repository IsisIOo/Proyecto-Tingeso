import { useState, useEffect } from "react";
import { Link, useParams, useNavigate } from "react-router-dom";
import recordService from "../services/record.service";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import FormControl from "@mui/material/FormControl";
import MenuItem from "@mui/material/MenuItem";
import SaveIcon from "@mui/icons-material/Save";
import Paper from "@mui/material/Paper";

const AddEditRecord = () => {
  const [patent, setPatent] = useState("");
  const [admissionDateDay, setadmissionDateDay] = useState();
  const [admissionDateMonth, setadmissionDateMonth] = useState();
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

    const record = { patent, admissionDateDay, admissionDateMonth, admissionHour, repairType, totalAmount, departureDate, departureHour, clientDate, clientHour, id };
    console.log(record);

    if (id) {
      //Actualizar Datos 
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
          setadmissionDateDay(record.data.admissionDateDay);
          setadmissionDateMonth(record.data.admissionDateMonth);
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
    <Paper >
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
            id="admissionDateDay"
            label="Dia admisión"
            value={admissionDateDay}
            select
            variant="standard"
            
            onChange={(e) => setadmissionDateDay(e.target.value)}
            style={{ width: "25%" }}
          >
          
            <MenuItem value={1}>1</MenuItem>
            <MenuItem value={2}>2</MenuItem>
            <MenuItem value={3}>3</MenuItem>
            <MenuItem value={4}>4</MenuItem>
            <MenuItem value={5}>5</MenuItem>
            <MenuItem value={6}>6</MenuItem>
            <MenuItem value={7}>7</MenuItem>
            <MenuItem value={8}>8</MenuItem>
            <MenuItem value={9}>9</MenuItem>
            <MenuItem value={10}>10</MenuItem>
            <MenuItem value={11}>11</MenuItem>
            <MenuItem value={12}>12</MenuItem>
            <MenuItem value={13}>13</MenuItem>
            <MenuItem value={14}>14</MenuItem>
            <MenuItem value={15}>15</MenuItem>
            <MenuItem value={16}>16</MenuItem>
            <MenuItem value={17}>17</MenuItem>
            <MenuItem value={18}>18</MenuItem>
            <MenuItem value={19}>19</MenuItem>
            <MenuItem value={20}>20</MenuItem>
            <MenuItem value={21}>21</MenuItem>
            <MenuItem value={22}>22</MenuItem>
            <MenuItem value={23}>23</MenuItem>
            <MenuItem value={24}>24</MenuItem>
            <MenuItem value={25}>25</MenuItem>
            <MenuItem value={26}>26</MenuItem>
            <MenuItem value={27}>27</MenuItem>
            <MenuItem value={28}>28</MenuItem>
            <MenuItem value={29}>29</MenuItem>
            <MenuItem value={30}>30</MenuItem>
            <MenuItem value={31}>31</MenuItem>
          </TextField >
        </FormControl>
        
        <FormControl fullWidth>
          <TextField
            id="admissionDateMonth"
            label="Mes admisión" 
            value={admissionDateMonth}
            select
            variant="standard"
            
            onChange={(e) => setadmissionDateMonth(e.target.value)}
            style={{ width: "25%" }}
          >
          
            <MenuItem value={1}>1</MenuItem>
            <MenuItem value={2}>2</MenuItem>
            <MenuItem value={3}>3</MenuItem>
            <MenuItem value={4}>4</MenuItem>
            <MenuItem value={5}>5</MenuItem>
            <MenuItem value={6}>6</MenuItem>
            <MenuItem value={7}>7</MenuItem>
            <MenuItem value={8}>8</MenuItem>
            <MenuItem value={9}>9</MenuItem>
            <MenuItem value={10}>10</MenuItem>
            <MenuItem value={11}>11</MenuItem>
            <MenuItem value={12}>12</MenuItem>
          </TextField >
        </FormControl>



        <FormControl fullWidth>
          <TextField
            id="admissionHour"
            label="Hora de admisión"
            value={admissionHour}
            variant="standard"
            onChange={(e) => setadmissionHour(e.target.value)}
          />
        </FormControl>

        <FormControl fullWidth>
          <TextField
            id="repairType"
            label="Tipo de reparación"
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
            readOnly // Hace que el campo de texto sea de solo lectura
            disabled // Deshabilita la edición del campo de texto

            
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
            readOnly // Hace que el campo de texto sea de solo lectura
            disabled // Deshabilita la edición del campo de texto}
          />
        </FormControl>

        <FormControl fullWidth>
          <TextField
          id="clientHour"
          label="Hora retirado"
          value={clientHour}
          variant="standard"
          readOnly // Hace que el campo de texto sea de solo lectura
          disabled // Deshabilita la edición del campo de texto
          />
        </FormControl>






        <FormControl>
          <br />
          <Button 
            variant="contained"
            //color="info"
            onClick={(e) => saveRecord(e)}
            style={{ marginLeft: "0.5rem", color  : "white", backgroundColor: "#D6589F"}}
            startIcon={<SaveIcon />}
          >
            Grabar
          </Button>
        </FormControl>
      </form>
      <hr />
      <Link to="/record/list">Back to List</Link>
    </Box>
    </Paper>
  );
};

export default AddEditRecord;
