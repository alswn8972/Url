const URL = "localhost:8080";
module.exports = {
  devServer: {
    https: false,
    port: 8080,
    open: true,
    proxy: {
      '/api': {
        target: `https://${URL}/`
      },
    },
    historyApiFallback: true,
    hot: true,
  },
  css: {
    sourceMap: process.env.NODE_ENV !== 'production'
  },
  lintOnSave: false,
}
