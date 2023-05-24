import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import React, { useState } from 'react';
import Search from '../../components/Search';
import {SERVER_URL, CONNECTION_FLIGHTS} from '../../utils/Constants';

export default function ConnectingFlights() {

  const [data, setData] = useState(null);
  const handleSubmit = async (searchData) => {
    console.log('searchData = ', searchData);
    const response = await fetch(`${SERVER_URL}/${CONNECTION_FLIGHTS}`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      //body: JSON.stringify({...searchData})
      body: JSON.stringify(
        {'departureAirportCode':searchData.departureAirportCode.airportCode,
          'arrivalAirportCode': searchData.arrivalAirportCode.airportCode
        }
      )
    });
    const json = await response.json();
    setData(json);
  };

 return (
        <>
    <Search onSubmit={handleSubmit} />
    {data &&  <TableContainer component={Paper}>
        <Table sx={{ minWidth: 650 }} aria-label="simple table">
          <TableHead>
            <TableRow>
              <TableCell> Onward Flight</TableCell>
              <TableCell align="right">Departure Airport</TableCell>
              <TableCell align="right">Arrival Airport</TableCell>
              <TableCell align="right">Departure Time</TableCell>
              <TableCell align="right">Arrival Time</TableCell>
  
              <TableCell> Connecting Flight</TableCell>
              <TableCell align="right">Departure Airport</TableCell>
              <TableCell align="right">Arrival Airport</TableCell>
              <TableCell align="right">Departure Time</TableCell>
              <TableCell align="right">Arrival Time</TableCell>
  
  
            </TableRow>
          </TableHead>
          <TableBody>
            {data.map((row, i) => (
              <TableRow
                key={i}
                sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
              >
                <TableCell align="right">{row.onwardFlighttNumber}</TableCell>
                <TableCell align="right">{row.onwardDepartureAirport}</TableCell>
                <TableCell align="right">{row.onwardArrivalAirport}</TableCell>
                <TableCell align="right">{row.onwardDepartureTime}</TableCell>
                <TableCell align="right">{row.onwardArrivalTime}</TableCell>
  
                <TableCell align="right">{row.connectionFlightNumber}</TableCell>
                <TableCell align="right">{row.connectionDepartureAirport}</TableCell>
                <TableCell align="right">{row.connectionArrivalAirport}</TableCell>
                <TableCell align="right">{row.connectionDepartureTime}</TableCell>
                <TableCell align="right">{row.connArrivalTime}</TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
      }
      </>
    );
  }