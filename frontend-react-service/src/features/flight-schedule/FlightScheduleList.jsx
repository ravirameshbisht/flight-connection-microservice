import React from 'react';
import {useList} from '../../components/useList';
import {SERVER_URL, FLIGHT_SCHEDULE_URL} from '../../utils/Constants';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import Button from '@mui/material/Button';
import { makeStyles } from '@mui/styles';
import Typography from '@mui/material/Typography';
import { createTheme } from '@mui/material/styles';
import 'typeface-roboto';

  const theme = createTheme();

const useStyles = makeStyles({
  h2: {
    fontFamily: 'Roboto',
    fontSize: '1rem',
    fontWeight: 50,
    textAlign: 'center',
  },
  TableContainer: {
    fontFamily: 'Roboto, sans-serif',
    fontSize: '1rem',
    fontWeight: 50,
    textAlign: 'center',
    paddingBottom: theme.spacing(5),
  }
});

const FlightScheduleList = () => {
    const classes = useStyles();
    const [flightScheduleList, handleDelete] = useList(`${SERVER_URL}/${FLIGHT_SCHEDULE_URL}`);
  
    return (
      <>
      {console.log('inside FlightScheduleList flightScheduleList is = ', flightScheduleList)}
        <Typography variant="h2" className={classes.h2}> Flight Schedules </Typography>

        <TableContainer component={Paper}>
        <Table sx={{ minWidth: 650 }} aria-label="simple table">
          <TableHead>
            <TableRow>
              <TableCell align="left">Flight Number</TableCell>
              <TableCell align="left">Departure Airport</TableCell>
              <TableCell align="left">Arrival Airport</TableCell>
              <TableCell align="left">Departure Time</TableCell>
              <TableCell align="left">Arrival Time</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {flightScheduleList.length ? flightScheduleList.map((flightSchedule, i) => (
              <TableRow
                key={i}
                sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
              >
                <TableCell align="left">{flightSchedule.flightNumber}</TableCell>
                <TableCell align="left">{flightSchedule.departureAirport && flightSchedule.departureAirport.airportName}</TableCell>
                <TableCell align="left">{flightSchedule.arrivalAirport && flightSchedule.arrivalAirport.airportName}</TableCell>
                <TableCell align="left">{flightSchedule.departureTime}</TableCell>
                <TableCell align="left">{flightSchedule.arrivalTime}</TableCell>
                <TableCell align="left"><Button variant="outlined" color="error" onClick={() => {handleDelete(flightSchedule.id)}}>Delete</Button></TableCell>
              </TableRow>
            )) : <p>There are no Flight Schedules</p>}
          </TableBody>
        </Table>
      </TableContainer>

      </>
    );
  };
  
  export default FlightScheduleList;