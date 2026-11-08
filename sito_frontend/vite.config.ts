import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

export default defineConfig({
  plugins: [react()],
  // in modalità library Vite non sostituisce questa variabile: lo facciamo a mano
  define: {
    'process.env.NODE_ENV': JSON.stringify('production'),
  },
  build: {
    outDir: '../sito_backend/src/main/resources/static/react',
    emptyOutDir: true,
    lib: {
      entry: 'src/notizia.tsx',
      formats: ['es'],
      fileName: () => 'notizia.js',
    },
  },
})