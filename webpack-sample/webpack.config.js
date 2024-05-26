const Path = require('path');

module.exports = {
    entry: './src/index.ts',
    mode: 'development',
    watch: true,
    watchOptions: {
        ignored: ['**/node_modules']
    },
    module: {
        rules: [
            {
                test: /\.ts$/,
                use: 'ts-loader',
                include: Path.resolve(__dirname, 'src'),
                exclude: /node_modules/,
            }
        ]
    },
    output: {
        filename: 'bundle.js',
        path: Path.resolve(__dirname, 'dist'),
        clean: true,
        publicPath: '/assets/'
    },
    resolve: {
        extensions: ['.ts', '.js']
    },
    devServer: {
        static: Path.join(__dirname, "public"),
        compress: true,
        hot: true,
        port: 3000,
    }
};