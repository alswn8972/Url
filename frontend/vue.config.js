const URL = "localhost:8080";
module.exports = {
  css: {
    // Enable CSS source maps.
    sourceMap: process.env.NODE_ENV !== 'production'
  },
  devServer:{
    https: false,
      port: 8080,
      open: true,
      proxy: {
        '/api': {
          target: `http://${URL}/`
        },

  },
  transpileDependencies: [
    'vuetify'
  ],
};
