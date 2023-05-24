import React, {useState} from 'react';
import DateFnsUtils from '@date-io/date-fns';
import { DateTimePicker, TimePicker, MuiPickersUtilsProvider } from "@material-ui/pickers";
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import CircularProgress from '@mui/material/CircularProgress';
import Typography from '@mui/material/Typography';
import { FormControl } from '@mui/material';
import { makeStyles } from '@mui/styles';
import { createTheme } from '@mui/material/styles';
import 'typeface-roboto';
import {useObjectCreation} from '../../components/useObjectCreation';
import {SERVER_URL, FLIGHT_SCHEDULE_URL} from '../../utils/Constants';

const theme = createTheme();

  const useStyles = makeStyles({
    Box: {
      padding: theme.spacing(4),
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

export default function CreateFlightSchedule()  {
  const classes = useStyles();
  const redirectUrl = '/flight-schedules';
    const [flightScheduleFormData, loading, handleFlightScheduleChange, handleFlightScheduleSubmit] = useObjectCreation(`${SERVER_URL}/${FLIGHT_SCHEDULE_URL}`, redirectUrl);
    const [selectedDate1, setSelectedDate1] = useState(new Date());
    const [selectedDate2, setSelectedDate2] = useState(new Date());

    const handleDepartureTimeChange = (date) => {
      console.log(date);
      setSelectedDate1(date);
      const departureTime = new Date(date);
      const timeString = departureTime.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit', hour12: false  });

      const event = {
        target: {
          name:"departureTime",
          value: timeString
        },
      };
      handleFlightScheduleChange(event);
    };

    const handleArrivalTimeChange = (date) => {
      setSelectedDate2(date);
      const arrivalTime = new Date(date);
      const timeString = arrivalTime.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit', hour12: false  });
      const event = {
        target: {
          name:"arrivalTime",
          value: timeString
        },
      };
      handleFlightScheduleChange(event);
    };

return (
  <Box sx={{ flexGrow: 1 }}  container justifyContent="center" className={classes.Box}>
  <form onSubmit={handleFlightScheduleSubmit}>
      <Grid container spacing={2}>
              <Grid   xs={12} className={classes.h4}>
                
                      <Typography variant="h4" gutterBottom>
                      Flight Number
                      </Typography>
              
              </Grid>
              <Grid  xs={4}></Grid>
              <Grid  xs={4}  container  spacing={3}>
              <Grid xs={12}>
              
                <FormControl>
        <TextField id="standard-basic" 
    label="Flight Number" 
    variant="standard" 
    type="text" name="flightNumber" 
    value={flightScheduleFormData.flightNumber} 
    onChange={handleFlightScheduleChange}
      />
      </FormControl>
               
                </Grid>
                <Grid  xs={12}>
                
                  <FormControl>
          <TextField id="standard-basic" 
      label="Departure Airport" 
      variant="standard" 
      type="text" name="departureAirport" 
      value={flightScheduleFormData.departureAirport} 
      onChange={handleFlightScheduleChange}
        />
        </FormControl>
                
                </Grid>
                
                <Grid  xs={12}>
                
                      <FormControl>
          <TextField id="standard-basic" 
      label="Arrival Airport" 
      variant="standard" 
      type="text" name="arrivalAirport" 
      value={flightScheduleFormData.arrivalAirport} 
      onChange={handleFlightScheduleChange}
        />
        </FormControl>
                
                </Grid>
                
                <Grid  xs={12}>
                
                      <FormControl>
          <MuiPickersUtilsProvider utils={DateFnsUtils}>
            <DateTimePicker
        value={selectedDate1} 
        onChange={handleDepartureTimeChange}
        ampm={false}
        format="HH:mm"
        showTimeSelect
        TimePickerComponent={TimePicker}
            />
          </MuiPickersUtilsProvider>
          </FormControl>
                
                </Grid>
                
                
                <Grid  xs={12}>
                
                      <FormControl>
        <MuiPickersUtilsProvider utils={DateFnsUtils}>
          <DateTimePicker
      value={selectedDate2} 
      onChange={handleArrivalTimeChange}
      ampm={false}
      format="HH:mm"
      showTodayButton
      TimePickerComponent={TimePicker}
          />
        </MuiPickersUtilsProvider>
        </FormControl>
                
      </Grid>
                
                
                
      {!loading &&  <Grid  xs={12} className={classes.Submit}>
          <Button variant="outlined" color="primary" type="submit">Submit</Button>
      </Grid>}
      
      {loading &&  <Grid  xs={12} className={classes.Submit}>
          <CircularProgress />
      </Grid>}

      </Grid>
      <Grid  xs={4}></Grid>
      </Grid>
  </form>
</Box>
);
}