var path = require('path');

module.exports = {
    entry: { home: './src/main/js/index.js' },
    devtool: 'sourcemaps',
    debug: true,
    output: {
        path: __dirname,
        filename: './src/main/resources/static/dist/sensor-app-bundle.js'
    },
    module: {
        loaders: [
            {
                exclude: /(node_modules)/,
                loader: 'babel',
                query: {
                    presets: ['react', 'es2015', 'stage-1']
                }
            }
        ]
    },
    resolve: {
        extensions: ['', '.js', '.jsx']
    },
    devServer: {
        historyApiFallback: true,
        contentBase: './src/main/resources/static',
        publicPath: '/dist',
        port: 8088,
        watchOptions: {
            aggregateTimeout: 300,
            poll: 1000
        }
    }
};