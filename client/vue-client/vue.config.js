// vue.config.js
module.exports = {
    // options...
    devServer: {
        proxy: 'http://pacyorky.ee:3000',
    },
    css: {
        loaderOptions: {
            less: {
                prependData: '@import "@/styles/_variables.less";'
            }
        }
    }
}