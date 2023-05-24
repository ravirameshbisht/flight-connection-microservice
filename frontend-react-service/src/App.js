import React from 'react';
//import { BrowserRouter as Router } from "react-router-dom";
//import { Routes, Route } from "react-router-dom";
import { BrowserRouter as Router, useRoutes} from "react-router-dom";
import ConnectingFlights from './features/connecting-flights/ConnectingFlights';
import CreateAirport  from './features/airport/CreateAirport';
import AirportList  from './features/airport/AirportList';
import CreateFlightSchedule from './features/flight-schedule/CreateFlightSchedule';
import FlightScheduleList from './features/flight-schedule/FlightScheduleList';
import Footer from './components/Footer';

const App = () => {
  let routes = useRoutes([
    { path: "/", element: <ConnectingFlights /> },
    { path: "create-airport", element: <CreateAirport /> },
    { path: "airports", element: <AirportList /> }, 
    { path: "create-flight-schedule", element: <CreateFlightSchedule /> },
    { path: "flight-schedules", element: <FlightScheduleList /> },
    // ...
  ]);
  //return routes;
  return (
    <>
      {routes}
      <Footer />
    </>
  );
};

const AppWrapper = () => {
  return (
    <Router>
      <App ></App>
    </Router>
  );
};

export default AppWrapper;