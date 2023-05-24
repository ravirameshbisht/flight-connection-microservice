import React from 'react';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import CircularProgress from '@mui/material/CircularProgress';
import { FormControl } from '@mui/material';
import { makeStyles } from '@mui/styles';
import { createTheme } from '@mui/material/styles';
import 'typeface-roboto';
import {useObjectCreation} from '../../components/useObjectCreation';
import {SERVER_URL, AIRPORT_URL} from '../../utils/Constants';

const theme = createTheme();

  const useStyles = makeStyles({
    Box: {
      padding: theme.spacing(4),
      //color: theme.palette.text.secondary,
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

export default function CreateAirport()  {
  const classes = useStyles();
  const redirectUrl = '/airports';
  const [airportFormData, loading, handleAirportChange, handleAirportSubmit] = useObjectCreation(`${SERVER_URL}/${AIRPORT_URL}`, redirectUrl);
return (
  <Box sx={{ flexGrow: 1 }}  container justifyContent="center" className={classes.Box}>
    <form onSubmit={handleAirportSubmit}>
        <Grid container spacing={2}>
            <Grid   xs={12} className={classes.h4}>
                  <Typography variant="h4" gutterBottom> Create Airport </Typography>
            </Grid>
            <Grid  xs={4}>
            </Grid>
            <Grid  xs={4}  container  spacing={3}>
                <Grid xs={12}>
                    <FormControl>
		                    <TextField id="standard-basic" 
			                      label="Airport Code" 
			                      variant="standard" 
			                      type="text" name="airportCode" 
			                      value={airportFormData.airportCode} 
			                      onChange={handleAirportChange}
                        />
		                </FormControl>
                </Grid>
                  
                <Grid  xs={12}>
                    <FormControl>
                        <TextField id="standard-basic" 
                            label="Airport Name" 
                            variant="standard" 
                            type="text" name="airportName" 
                            value={airportFormData.airportName} 
                            onChange={handleAirportChange}
                        />
                      </FormControl>
                </Grid>

                <Grid  xs={12}>
                    <FormControl>
                        <TextField id="standard-basic" 
                            label="City Name" 
                            variant="standard" 
                            type="text" name="cityName" 
                            value={airportFormData.cityName} 
                            onChange={handleAirportChange}
                        />
                    </FormControl>
                </Grid>
                  
                {!loading && <Grid  xs={12} className={classes.Submit}>
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