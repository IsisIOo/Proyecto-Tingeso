import { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import recordService from "../services/record.service";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell, { tableCellClasses } from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import Button from "@mui/material/Button";
import PersonAddIcon from "@mui/icons-material/PersonAdd";
import EditIcon from "@mui/icons-material/Edit";
import DeleteIcon from "@mui/icons-material/Delete";

const RecordList = () => {
  const [record, setRecord] = useState([]);

  const navigate = useNavigate();

  const init = () => {
    recordService
      .getAll()
      .then((response) => {
        console.log("Mostrando listado de todos los historiales ingresados.", response.data);
        setRecord(response.data);
      })
      .catch((error) => {
        console.log(
          "Se ha producido un error al intentar mostrar listado de todos los historiales.",
          error
        );
      });
  };

  useEffect(() => {
    init();
  }, []);

  const handleDelete = (id) => {
    console.log("Printing id", id);
    const confirmDelete = window.confirm(
      "¿Esta seguro que desea borrar este historial?"
    );
    if (confirmDelete) {
      recordService
        .remove(id)
        .then((response) => {
          console.log("historial ha sido eliminado.", response.data);
          init();
        })
        .catch((error) => {
          console.log(
            "Se ha producido un error al intentar eliminar al historial",
            error
          );
        });
    }
  };

  const handleEdit = (id) => {
    console.log("Printing id", id);
    navigate(`/record/details/${id}`);
  };

  return (
    <TableContainer component={Paper}>
      <br />
      <Link
        to="/record/add"
        style={{ textDecoration: "none", marginBottom: "1rem" }}
      >
        <Button
          variant="contained"
          color="primary"
          style={{ marginLeft: "0.5rem", color  : "white", backgroundColor: "#D6589F"}}
          startIcon={<PersonAddIcon />}
        >
          Añadir Historial
        </Button>
      </Link>
      <br /> <br />
      <Table sx={{ minWidth: 650 }} size="small" aria-label="a dense table">
        <TableHead>
          <TableRow>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Patente
            </TableCell>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Dia admision
            </TableCell>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Mes admision
            </TableCell>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Año admision
            </TableCell>
            <TableCell align="right" sx={{ fontWeight: "bold" }}>
              Hora admision
            </TableCell>
            <TableCell align="right" sx={{ fontWeight: "bold" }}>
              Tipo reparacion
            </TableCell>
            <TableCell align="right" sx={{ fontWeight: "bold" }}>
              Costo total
            </TableCell>
            <TableCell align="right" sx={{ fontWeight: "bold" }}>
              Fecha retiro
            </TableCell>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Hora retiro
            </TableCell>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Fecha retirado
            </TableCell>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Hora retirado
            </TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {record.map((record) => (
            <TableRow key={record.id}>
              <TableCell align="left">{record.patent}</TableCell>
              <TableCell align="left">{record.admissionDateDay}</TableCell>
              <TableCell align="left">{record.admissionDateMonth}</TableCell>
              <TableCell align="left">{record.admissionDateYear}</TableCell>
              <TableCell align="right">{record.admissionHour}</TableCell>
              <TableCell align="right">{record.repairType}</TableCell>
              <TableCell align="right">{record.totalAmount}</TableCell>
              <TableCell align="right">{record.departureDate}</TableCell>
              <TableCell align="right">{record.departureHour}</TableCell>
              <TableCell align="right">{record.clientDate}</TableCell>
              <TableCell align="right">{record.clientHour}</TableCell>
              <TableCell>
                <Button
                  variant="contained"
                  color="info"
                  size="small"
                  onClick={() => handleEdit(record.patent)}
                  style={{ marginLeft: "0.5rem" }}
                  startIcon={<EditIcon />}
                >
                  Detalles
                </Button>
                <Button
                  variant="contained"
                  color="error"
                  size="small"
                  onClick={() => handleDelete(record.patent)}
                  style={{ marginLeft: "0.5rem" }}
                  startIcon={<DeleteIcon />}
                >
                  Eliminar
                </Button>
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
};

export default RecordList;