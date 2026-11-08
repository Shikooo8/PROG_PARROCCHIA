import { useEffect, useState } from 'react'
import { getNotizia } from '../services/notiziaService'
import type { Notizia } from '../types/index'

interface Props {
  id: number
}

export default function NotiziaDettaglio({ id }: Props) {
  const [notizia, setNotizia] = useState<Notizia | null>(null)
  const [errore, setErrore] = useState<string | null>(null)

  useEffect(() => {
    getNotizia(id)
      .then(setNotizia)
      .catch(() => setErrore('Impossibile caricare la notizia.'))
  }, [id])

  if (errore) {
    return <div className="avviso avviso-errore">{errore}</div>
  }
  if (!notizia) {
    return <p className="dettaglio-meta">Caricamento…</p>
  }

  const dataFormattata = new Date(notizia.data).toLocaleString('it-IT', {
    day: '2-digit',
    month: '2-digit',
    year: 'numeric',
    hour: '2-digit',
    minute: '2-digit',
  })

  return (
    <>
      <div className="dettaglio-meta">
        <span>{dataFormattata}</span>
      </div>
      <h2 className="dettaglio-titolo">{notizia.titolo}</h2>
      <div className="dettaglio-testo">{notizia.descrizione}</div>
    </>
  )
}