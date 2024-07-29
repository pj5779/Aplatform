import React from 'react';
import ReactDOM from 'react-dom/client';
import Test from './App';
import {BrowserRouter} from "react-router-dom";
// import MainLayout from './common/Mainlayout';
import Header from "./common/MainHeader";
// import './common/MainLayout.css';
import Footer from "./layout/Footer";
import './styles/index.css';


const root = ReactDOM.createRoot(document.getElementById('app'));
root.render(
  <BrowserRouter>
    <Header/>
    <div role="main" className="main" style={{ minHeight: '700px' }}>
     <Test/>
     </div>
    <Footer/>
  </BrowserRouter>
);