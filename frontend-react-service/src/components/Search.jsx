import React, { useState} from 'react';
import {SERVER_URL, AIRPORT_URL} from '../utils/Constants';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import Autocomplete from '@mui/material/Autocomplete';
import CircularProgress from '@mui/material/CircularProgress';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import { makeStyles} from '@mui/styles';
import { createTheme } from '@mui/material/styles';
import 'typeface-roboto';

  const theme = createTheme();

  const useStyles = makeStyles({
    Box: {
    padding: theme.spacing(4),
    color: theme.palette.text.secondary,
    fontFamily: 'Roboto, sans-serif',
    fontSize: '1rem',
    fontWeight: 50,
    textAlign: 'center',
    direction: 'column',
    alignItems: 'center',
    justifyContent: 'center',
    },
    h4: {
      fontFamily: 'Roboto, sans-serif',
      fontSize: '1rem',
      fontWeight: 50,
      textAlign: 'center',
      paddingTop: theme.spacing(4),
      paddingBottom: theme.spacing(4)
    },
    FormControl: {
      fontFamily: 'Roboto, sans-serif',
      fontSize: '1rem',
      fontWeight: 50,
      textAlign: 'center',
      paddingTop: theme.spacing(8),
      paddingBottom: theme.spacing(8)
    },
    Grid: {
      paddingTop: theme.spacing(8),
      paddingBottom: theme.spacing(8)
    },
    Submit: {
      paddingTop: theme.spacing(4),
      paddingBottom: theme.spacing(4)
    }
  });

const Search = ({onSubmit}) => {
  const classes = useStyles();
    const [formData, setFormData] = useState({
        departureAirportCode: '',
        arrivalAirportCode: ''
      });

  const [openDeparture, setOpenDeparture] = useState(false);
  const [openArrival, setOpenArrival] = useState(false);
  const [optionsDeparture, setOptionsDeparture] = useState([]);
  const [optionsArrival, setOptionsArrival] = useState([]);
  const loadingDeparture = openDeparture && optionsDeparture.length === 0;
  const loadingArrival = openArrival && optionsArrival.length === 0;
  const [inputValueDeparture, setInputValueDeparture] = useState('');
  const [inputValueArrival, setInputValueArrival] = useState('');

    // Setting the logic for the asynchronous function on page reload
    React.useEffect(() => {
      let activeDeparture = true;
  
      if (!loadingDeparture) {
        return undefined;
      }
  
      (async () => {
        
          const response = await fetch(`${SERVER_URL}/${AIRPORT_URL}`);
          const data = await response.json();
          //const dataCodes = data.map((airport) => airport.airportCode);
        
        if (activeDeparture) {
          setOptionsDeparture(data);
          //setAirportCodes(dataCodes);
        }
      })();
  
      return () => {
        activeDeparture = false;
      };
    }, [loadingDeparture]);

    React.useEffect(() => {
      let active = true;
  
      if (!loadingArrival) {
        return undefined;
      }
  
      (async () => {
        
          const response = await fetch(`${SERVER_URL}/${AIRPORT_URL}`);
          const data = await response.json();
        
        if (active) {
          setOptionsArrival(data);
        }
      })();
  
      return () => {
        active = false;
      };
    }, [loadingArrival]);
  
    React.useEffect(() => {
      if (!openDeparture) {
        setOptionsDeparture([]);
      }
    }, [openDeparture]);
    React.useEffect(() => {
      if (!openArrival) {
        setOptionsArrival([]);
      }
    }, [openArrival]);

      const handleSubmit = (event) => {
        event.preventDefault();
        onSubmit({ ...formData});
      };

  return (
    
    <Box sx={{ flexGrow: 1 }}  container justifyContent="center" className={classes.Box}>
              <form onSubmit={handleSubmit}>
              <Grid container spacing={2}>
                <Grid   xs={12} className={classes.h4}>
                  
                        <Typography variant="h4" gutterBottom>
                        Search Connection Flights
                        </Typography>
                
                </Grid>
                <Grid  xs={4}>
                  
                </Grid>
                <Grid  xs={4}  container  spacing={3}>
                <Grid xs={12}>
            
            <Autocomplete
            id="asynchronous-demo"
            sx={{ width: 300 }}
            value={formData.departureAirportCode}
            onChange={(event, newValue) => {
              console.log('departure newValue is', newValue);
              setFormData({

                'arrivalAirportCode': formData.arrivalAirportCode,
                'departureAirportCode': newValue
            });
            }}
            inputValue={inputValueDeparture}
            onInputChange={(event, newInputValue) => {
              setInputValueDeparture(newInputValue);
          }}

            open={openDeparture}
            onOpen={() => {
              setOpenDeparture(true);
            }}
            onClose={() => {
              setOpenDeparture(false);
            }}
          
            getOptionLabel={(option) => option?.airportCode || 'Select Departure Airport'} 
            options={optionsDeparture}
            loading={loadingDeparture}
            renderInput={(params) => (
              <TextField
                {...params}
                label="From Airport"
                InputProps={{
                  ...params.InputProps,
                  endAdornment: (
                    <React.Fragment>
                      {loadingDeparture ? <CircularProgress color="inherit" size={20} /> : null}
                      {params.InputProps.endAdornment}
                    </React.Fragment>
                  ),
                }}
              />
            )}
          />
                
                  
                 
                  </Grid>
                  <Grid  xs={12}>
                
            <br />
            <br />
            <br />

            <Autocomplete
            id="combo-box-demo"
            sx={{ width: 300 }}
            value={formData.arrivalAirportCode}
            onChange={(event, newValue) => {
              setFormData({
                'departureAirportCode': formData.departureAirportCode,
                'arrivalAirportCode': newValue
            });
            }}
            inputValue={inputValueArrival}
            onInputChange={(event, newInputValue) => {
              setInputValueArrival(newInputValue);
          }}

            open={openArrival}
            onOpen={() => {
              setOpenArrival(true);
            }}
            onClose={() => {
              setOpenArrival(false);
            }}
            getOptionLabel={(option) => option?.airportCode || 'Select Arrival Airport'}
            options={optionsArrival}
            loading={loadingArrival}
            renderInput={(params) => (
              <TextField
                {...params}
                label="To Airport"
                InputProps={{
                  ...params.InputProps,
                  endAdornment: (
                    <React.Fragment>
                      {loadingArrival ? <CircularProgress color="inherit" size={20} /> : null}
                      {params.InputProps.endAdornment}
                    </React.Fragment>
                  ),
                }}
              />
            )}
          />
                  
                  </Grid>
                  <Grid  xs={12} className={classes.Submit}>
                 
                  <Button variant="outlined" color="primary" type="submit">Submit</Button>
                  
                </Grid>
                </Grid>
                <Grid  xs={4}>
                  
                </Grid>
                </Grid>
                </form>
            </Box>
          
  );
}

export default Search;