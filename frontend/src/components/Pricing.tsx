import Box from '@mui/material/Box'
import Button from '@mui/material/Button'
import Card from '@mui/material/Card'
import CardActions from '@mui/material/CardActions'
import CardContent from '@mui/material/CardContent'
import CardHeader from '@mui/material/CardHeader'
import CssBaseline from '@mui/material/CssBaseline'
import Grid from '@mui/material/Grid'
import StarIcon from '@mui/icons-material/StarBorder'
import Typography from '@mui/material/Typography'
import GlobalStyles from '@mui/material/GlobalStyles'
import Container from '@mui/material/Container'
import { useState, useEffect } from 'react'
import LoadingCircle from './LoadingCircle'
import BasicSnackBar from './BasicSnackbar'

type Package = {
  id: number
  name: string
  description: []
  price: number
}

function PricingContent() {
  const [packages, setPackages] = useState<Package[]>([])
  const [open, setOpen] = useState(false)
  const [severity, setSeverity] = useState('')
  const [msg, setMsg] = useState('')

  const handleClose = () => {
    setOpen(false)
  }

  const handleOpen = (test: string) => {
    if (test === 'success') {
      setSeverity('success')
      setMsg('Purchase successful!')
    } else {
      setSeverity('error')
      setMsg('Purchase failed!')
    }
    setOpen(true)
  }

  const handlePurchase = (purchasedPackage: Package) => {
    console.log(JSON.stringify(purchasedPackage))

    fetch('http://localhost:8080/api/orders/add', {
      method: 'POST',
      body: JSON.stringify({
        tvPackage: purchasedPackage
      }),
      headers: {
        'Content-type': 'application/json'
      }
    })
      .then((response) => {
        handleOpen('success')
      })

      .catch((err) => {
        handleOpen('error')
      })
  }

  useEffect(() => {
    const fetchPackages = async () => {
      const response = await fetch('http://localhost:8080/api/tvpackages/getAll')
      const data = await response.json()
      console.log(data)
      setPackages(data)
    }
    fetchPackages()
  }, [])

  if (!packages.length) {
    return (
      <Container
        component="main"
        sx={{
          display: 'flex',
          justifyContent: 'center',
          height: '70vh',
          alignItems: 'center',
          mb: 2
        }}>
        <LoadingCircle />
      </Container>
    )
  }

  return (
    <>
      <GlobalStyles styles={{ ul: { margin: 0, padding: 0, listStyle: 'none' } }} />
      <CssBaseline />

      {/* Hero unit */}
      <Container disableGutters maxWidth="sm" component="main" sx={{ pt: 8, pb: 2, mb: 4 }}>
        <Typography component="h1" variant="h2" align="center" color="text.primary" gutterBottom>
          Pricing
        </Typography>
      </Container>
      {/* End hero unit */}

      <Container maxWidth="md" component="main" sx={{ pb: 6, mb: 5 }}>
        <Grid container spacing={5} alignItems="flex-end">
          {packages.map((tier: Package) => (
            // Enterprise card is full width at sm breakpoint
            <Grid item key={tier.id} xs={12} sm={tier.name === 'Premium' ? 12 : 6} md={4}>
              <Card>
                <CardHeader
                  title={tier.name}
                  titleTypographyProps={{ align: 'center' }}
                  action={tier.name === 'Standard' ? <StarIcon /> : null}
                  subheaderTypographyProps={{
                    align: 'center'
                  }}
                  sx={{
                    backgroundColor: (theme) =>
                      theme.palette.mode === 'light'
                        ? theme.palette.grey[200]
                        : theme.palette.grey[700]
                  }}
                />
                <CardContent>
                  <Box
                    sx={{
                      display: 'flex',
                      justifyContent: 'center',
                      alignItems: 'baseline',
                      mb: 2
                    }}>
                    <Typography component="h2" variant="h3" color="text.primary">
                      ${tier.price}
                    </Typography>
                    <Typography variant="h6" color="text.secondary">
                      /mo
                    </Typography>
                  </Box>
                  <ul>
                    {tier.description.map((line: string) => (
                      <Typography component="li" variant="subtitle1" align="center" key={line}>
                        {line}
                      </Typography>
                    ))}
                  </ul>
                </CardContent>
                <CardActions>
                  <Button fullWidth variant={'contained'} onClick={() => handlePurchase(tier)}>
                    BUY NOW
                  </Button>
                  <BasicSnackBar
                    open={open}
                    onClose={handleClose}
                    severity={severity}
                    message={msg}
                  />
                </CardActions>
              </Card>
            </Grid>
          ))}
        </Grid>
      </Container>
    </>
  )
}

export default function Pricing() {
  return <PricingContent />
}
