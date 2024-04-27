import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import Button from './components/Button'
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom'
import Navbar from "./components/Navbar"
import Home from './components/Home'
import Home1 from './components/Home1'
import CarList from "./components/CarList"
import RecordList from "./components/RecordList"
import AddEditCar from './components/AddEditCar'
import AddEditRecord from './components/AddEditRecord'
import NotFound from './components/NotFound'
import CostList from './components/CostList'
import AddEditCost from './components/AddEditCost'
import CostDetails from './components/CostDetails'

function App() {
  return (
      <Router>
          <div className="container">
          <Navbar></Navbar>
            <Routes>
              <Route path="/" element={<Home/>} />
              <Route path = "/car/list" element = {<CarList/>} />
              <Route path = "/car/add" element = {<AddEditCar/>} />

              {/*registros del auto*/} 
              <Route path = "/record/list" element = {<RecordList/>} />
              <Route path = "/record/add" element = {<AddEditRecord/>} />

              <Route path = "/cost/list" element = {<CostList/>} />
              <Route path = "/cost/add" element = {<AddEditCost/>} />
              <Route path = {"/cost/details/:patent"}  element = {<CostDetails/>} />

              <Route path = "*" element = {<NotFound/>} />
            </Routes>
          </div>

      </Router>
  );
}

export default App
