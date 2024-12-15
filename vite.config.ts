import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import tsconfigPathsPlugin from 'vite-tsconfig-paths'
import AutoImport from 'unplugin-auto-import/vite'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    tsconfigPathsPlugin(),
    vue(),
    AutoImport({
      imports: ['vue', 'vue-router'],
      dts: 'src/auto-imports.d.ts', // plugins này sẽ tự động generated ra file auto-imports.d.ts trong source src.
      dirs: [], // chỗ này mình có thể thêm name folder nó sẽ tự động lấy tất cả các tên file trong folder đó và mình có thể gọi bất kì ở trong file Vue nào mà không cần import. (src/stores)
      vueTemplate: true,
    }),
  ],
})
