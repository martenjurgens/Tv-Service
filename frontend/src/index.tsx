import React from 'react'
import ReactDOM from 'react-dom/client'
import './index.css'
import App from './App'
import { BrowserRouter, Routes, Route } from 'react-router-dom'
import OrderList from './components/OrderList'
import Pricing from './components/Pricing'

const root = ReactDOM.createRoot(document.getElementById('root') as HTMLElement)
root.render(
  <React.StrictMode>
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<App />}>
          <Route index element={<Pricing />} />
          <Route path="pricing" element={<Pricing />} />
          <Route path="orders" element={<OrderList />} />
        </Route>
      </Routes>
    </BrowserRouter>
  </React.StrictMode>
)
