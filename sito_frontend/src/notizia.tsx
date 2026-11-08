import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import NotiziaDettaglio from './components/NotiziaDettaglio'

const el = document.getElementById('notizia-root')

if (el) {
  const id = Number(el.dataset.id)
  createRoot(el).render(
    <StrictMode>
      <NotiziaDettaglio id={id} />
    </StrictMode>
  )
}