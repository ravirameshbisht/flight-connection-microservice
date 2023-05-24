import React from 'react';
import { Link } from 'react-router-dom';
import { makeStyles } from '@material-ui/core/styles';
import BottomNavigation from '@material-ui/core/BottomNavigation';
import BottomNavigationAction from '@material-ui/core/BottomNavigationAction';
import HomeIcon from '@mui/icons-material/Home';
//import AddIcon from '@material-ui/icons/Add';
import AddIcon from '@mui/icons-material/Add';
import AddLocationAlt from '@mui/icons-material//AddLocationAlt';
import FlightTakeoffIcon from '@mui/icons-material/FlightTakeoff';
import ScheduleIcon from '@mui/icons-material/Schedule';

const useStyles = makeStyles({
  root: {
    position: 'fixed',
    bottom: 0,
    left: 0,
    right: 0,
  },
});

const Footer = () => {
  const classes = useStyles();
  const [value, setValue] = React.useState(0);

  const handleNavigation = (event, newValue) => {
    setValue(newValue);
  };

  return (
    <BottomNavigation
      value={value}
      onChange={handleNavigation}
      showLabels
      className={classes.root}
    >
      <BottomNavigationAction
        label="Home"
        icon={<HomeIcon />}
        component={Link}
        to="/"
      />
      <BottomNavigationAction
        label="Flight Schedules"
        icon={<ScheduleIcon />}
        component={Link}
        to="/flight-schedules"
      />
      <BottomNavigationAction
        label="Airports"
        icon={<FlightTakeoffIcon />}
        component={Link}
        to="/airports"
      />

    <BottomNavigationAction
        label="Add Airport"
        icon={<AddLocationAlt />}
        component={Link}
        to="/create-airport"
      />

<BottomNavigationAction
        label="Add Flight Schedule"
        icon={<AddIcon />}
        component={Link}
        to="/create-flight-schedule"
      />
    </BottomNavigation>

    
  );
};

export default Footer;
