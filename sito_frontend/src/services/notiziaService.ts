import type { Notizia } from '../types'

export async function getNotizia(id: number): Promise<Notizia> {
  const res = await fetch(`/api/notizie/${id}`)
  if (!res.ok) {
    throw new Error(`Errore ${res.status}`)
  }
  return res.json()
}