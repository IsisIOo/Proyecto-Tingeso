import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import Button from './components/Button'
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom'
import Navbar from "./components/Navbar"
import Home from './components/Home'
import CarList from "./components/CarList"
import AddEditCar from './components/AddEditCar'

function App() {
  return (
      <Router>
          <div className="container">
          <Navbar></Navbar>
            <Routes>
              <Route path="/home" element={<Home/>} />
              <Route path = "/car/list" element = {<CarList/>} />
              <Route path = "/car/add" element = {<AddEditCar/>} />
            </Routes>
          </div>
      </Router>
  );
}

export default App
