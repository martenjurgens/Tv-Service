import { DataGrid, GridColDef } from '@mui/x-data-grid'
import { useEffect, useState } from 'react'
import { Container } from '@mui/material'

const columns: GridColDef[] = [
  { field: 'id', headerName: 'ID', width: 50 },
  { field: 'name', headerName: 'Package name', width: 120 },
  { field: 'status', headerName: 'Order status', width: 130 },
  { field: 'created_at', headerName: 'Created at', width: 160 },
  { field: 'updated_at', headerName: 'Updated at', width: 160 }
]

export default function OrderList() {
  const [orders, setOrders] = useState([])

  useEffect(() => {
    const fetchOrders = async () => {
      const response = await fetch('http://localhost:8080/api/orders/getAll')
      const data = await response.json()
      setOrders(data)
    }
    fetchOrders()

    const interval = setInterval(() => {
      fetchOrders()
    }, 5000)

    return () => clearInterval(interval)
  }, [])

  return (
    <Container
      component="main"
      sx={{
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        height: '80vh'
      }}>
      <DataGrid
        rows={orders}
        columns={columns}
        pageSize={10}
        rowsPerPageOptions={[10]}
        checkboxSelection
      />
    </Container>
  )
}
