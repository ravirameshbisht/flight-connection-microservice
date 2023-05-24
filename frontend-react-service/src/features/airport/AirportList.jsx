import React from 'react';
import {useList} from '../../components/useList';
import {SERVER_URL, AIRPORT_URL} from '../../utils/Constants';
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
    fontFamily: 'Roboto, sans-serif',
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

const AirportList = () => {
    const classes = useStyles();
    const [airportList, handleDelete] = useList(`${SERVER_URL}/${AIRPORT_URL}`);
  
    return (
      <>
        <Typography variant="h2" className={classes.TableContainer}> Airports </Typography>

        <TableContainer component={Paper} className={[classes.h2, classes.TableContainer]}>
        <Table sx={{ minWidth: 650 }} aria-label="simple table">
          <TableHead className={[classes.h2, classes.TableContainer]}>
            <TableRow>
              <TableCell align="left">Airport Code</TableCell>
              <TableCell align="left">Airport Name</TableCell>
              <TableCell align="left">City Name</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {airportList.length ? airportList.map((airport) => (
              <TableRow
                key={airport.id}
                sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
              >
                <TableCell align="left">{airport.airportCode}</TableCell>
                <TableCell align="left">{airport.airportName}</TableCell>
                <TableCell align="left">{airport.cityName}</TableCell>
                <TableCell align="left"><Button variant="outlined" color="error" onClick={() => {handleDelete(airport.id)}}>Delete</Button></TableCell>
              </TableRow>
            )): <p>There are no Airport</p>}
          </TableBody>
        </Table>
      </TableContainer>

      </>
    );
  };
  
  export default AirportList;