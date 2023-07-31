import React from 'react';
//import './App.css';
import Home from './Home';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import GrpList from './GrpList';
import GrpEdit from './GrpEdit';

const App = () => {
  return (
    <Router>
      <Routes>
        <Route exact path="/" element={<Home/>}/>
        <Route path='/grocery' exact={true} element={<GrpList/>}/>
        <Route path='/grocery/:id' element = {<GrpEdit/>}/>
      </Routes>
    </Router>
  )
}

export default App;