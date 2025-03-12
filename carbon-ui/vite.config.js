import {defineConfig, loadEnv} from 'vite'
import vue from '@vitejs/plugin-vue'
import inject from "@rollup/plugin-inject";
import path from 'path'
import AutoImport from 'unplugin-auto-import/dist/vite.js'

// https://vitejs.dev/config/
export default defineConfig(({command, mode})=>{
  const env = loadEnv(mode, './')
  const PORT= env.VITE_APP_PORT || 80
  return {
    base: './',
    server: {
      host: '0.0.0.0',
      port: PORT,
      // 需要前后端部署在同一台机器上
      // proxy: {
      //   [env.VITE_APP_BASE_API]: {
      //     target: env.VITE_APP_BACKEND,
      //     changeOrigin: true,
      //     rewrite: (path) => path.replace(new RegExp(`^${env.VITE_APP_BASE_API}`), ''),
      //   },
        // 需要访问后端以获取的文件资源
      // }
    },
    plugins: [
      vue(),
      inject({
        $: "jquery",  // 这里会自动载入 node_modules 中的 jquery   jquery全局变量
        jQuery: "jquery",
        "windows.jQuery": "jquery"
      }),
      // 自动导入vue相关依赖
      AutoImport({
        imports: ['vue', 'vue-router', 'pinia']
      })
    ],
    resolve: {
      //设置路径别名
      alias: {
        '@': path.resolve(__dirname, './src'),
        '*': path.resolve('')
      },
    }
  }
})