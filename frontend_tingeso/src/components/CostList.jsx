import { useEffect, useState } from "react";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell, { tableCellClasses } from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import costService from "../services/cost.service";
import InfoIcon from '@mui/icons-material/Info';

const CostList = () => {
  const [cost, setCost] = useState([]);

  const init = () => {
    costService
      .getAll()
      .then((response) => {
        console.log(
          "Mostrando planilla de Costos totales.",
          response.data
        );
        setCost(response.data);
      })
      .catch((error) => {
        console.log(
          "Se ha producido un error al intentar mostrar los Costos totales.",
          error
        );
      });
  };

  useEffect(() => {
    init();
  }, []);

  return (
    <TableContainer component={Paper}>
      <h3>Planilla de Costos</h3>
      <hr />
      <Table sx={{ minWidth: 650 }} size="small" aria-label="a dense table">
        <TableHead>
          <TableRow>
            <TableCell align="right" sx={{ fontWeight: "bold" }}>
              Patente
            </TableCell>            
            <TableCell align="right" sx={{ fontWeight: "bold" }}>
              Total reparacion
            </TableCell>
            <TableCell align="right" sx={{ fontWeight: "bold" }}>
            Descuento dia
            </TableCell>
            <TableCell align="right" sx={{ fontWeight: "bold" }}>
              Descuento Bonus
            </TableCell>
            <TableCell align="right" sx={{ fontWeight: "bold" }}>
              Cargo atraso
            </TableCell>
            <TableCell align="right" sx={{ fontWeight: "bold" }}>
              Cargo Kilometraje
            </TableCell>
            <TableCell align="right" sx={{ fontWeight: "bold" }}>
             Cargo antiguedad
            </TableCell>
            <TableCell align="right" sx={{ fontWeight: "bold" }}>
              Total Costo
            </TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {cost.map((cost) => (
            <TableRow
              key={cost.id}
            >
              <TableCell align="right">{cost.patent}</TableCell>
              <TableCell align="right">
                {new Intl.NumberFormat("es-CL", { style: "decimal" }).format(
                  cost.totalreparaciones
                )}
              </TableCell>

              <TableCell align="right">
                {new Intl.NumberFormat("es-CL", { style: "decimal" }).format(
                  cost.discountPerDay
                )}
              </TableCell>
              <TableCell align="right">
                {new Intl.NumberFormat("es-CL", { style: "decimal" }).format(
                  cost.discountPerbonus
                )}
              </TableCell>
              <TableCell align="right">
                {new Intl.NumberFormat("es-CL", { style: "decimal" }).format(
                  cost.delayCharge
                )}
              </TableCell>
              <TableCell align="right">
                {new Intl.NumberFormat("es-CL", { style: "decimal" }).format(
                  cost.mileageCharge
                )}
              </TableCell>
              <TableCell align="right">
                {new Intl.NumberFormat("es-CL", { style: "decimal" }).format(
                  cost.seniorityCharge
                )}
              </TableCell>
              <TableCell align="right">
                {new Intl.NumberFormat("es-CL", { style: "decimal" }).format(
                  cost.totalcost
                )}
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
};

export default CostList;
