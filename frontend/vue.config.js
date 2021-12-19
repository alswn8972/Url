module.exports = {
  devServer:{
    overlay: false,
    https: true,
    port: 8083,
    open: true,
    proxy: {
      '/api': {
        target: 'https://localhost:8080/'
      },
    }
  },
  css: {
    sourceMap: process.env.NODE_ENV !== 'production'
  },
  lintOnSave: false,
}