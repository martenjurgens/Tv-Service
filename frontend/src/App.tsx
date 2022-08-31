import ResponsiveAppBar from './components/ResponsiveAppbar'
import { Outlet } from 'react-router-dom'
import CssBaseline from '@mui/material/CssBaseline'

import { useTheme, ThemeProvider, createTheme } from '@mui/material/styles'
import { useState, useMemo, createContext, useContext } from 'react'
import LightModeOutlinedIcon from '@mui/icons-material/LightModeOutlined'
import DarkModeOutlinedIcon from '@mui/icons-material/DarkModeOutlined'
import IconButton from '@mui/material/IconButton'

export const darkTheme = createTheme({
  palette: {
    mode: 'dark',
    primary: {
      main: '#3f51b5'
    },
    secondary: {
      main: '#f50057'
    }
  }
})

// eslint-disable-next-line @typescript-eslint/no-empty-function
const ColorModeContext = createContext({ toggleColorMode: () => {} })

export function DarkToggle() {
  const theme = useTheme()
  const colorMode = useContext(ColorModeContext)
  return (
    <IconButton
      sx={{ ml: 1, width: 'auto', color: 'text.primary' }}
      aria-label="Toggle color mode"
      onClick={colorMode.toggleColorMode}
      color="inherit">
      {theme.palette.mode === 'dark' ? <LightModeOutlinedIcon /> : <DarkModeOutlinedIcon />}
    </IconButton>
  )
}

export default function App() {
  const [mode, setMode] = useState<'light' | 'dark'>('light')
  const colorMode = useMemo(
    () => ({
      toggleColorMode: () => {
        setMode((prevMode) => (prevMode === 'light' ? 'dark' : 'light'))
      }
    }),
    []
  )

  const theme = useMemo(
    () =>
      createTheme({
        palette: {
          mode
        }
      }),
    [mode]
  )

  return (
    <ColorModeContext.Provider value={colorMode}>
      <ThemeProvider theme={theme}>
        <CssBaseline />
        <ResponsiveAppBar />
        <Outlet />
      </ThemeProvider>
    </ColorModeContext.Provider>
  )
}
