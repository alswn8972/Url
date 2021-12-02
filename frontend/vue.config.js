const URL = "localhost:8080";
module.exports = {
  css: {
    // Enable CSS source maps.
    sourceMap: process.env.NODE_ENV !== 'production'
  },
  devServer:{
    overlay:false
  },
  transpileDependencies: [
    'vuetify'
  ],
};
