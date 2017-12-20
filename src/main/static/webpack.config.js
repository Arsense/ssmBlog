const CopyWebpackPlugin = require('copy-webpack-plugin')
const path = require('path')
var p = path.resolve(__dirname, '../')
const lib_names = ['jquery/dist' , 'admin-lte/dist' , 'bootstrap/dist' , 'font-awesome/css' ,
    'font-awesome/fonts' , 'select2/dist' ,'vue/dist' , 'axios/dist']
const copyLibDir = []
for(var index in lib_names) {
    var lib_name = lib_names[index]
    copyLibDir.push({ from: __dirname + '/node_modules/' + lib_name  , to : __dirname +'/dist/lib/' + lib_name })
}
copyLibDir.push({from: __dirname + '/src/lib/lib.js' , to :__dirname + '/dist/lib'})
copyLibDir.push({from: __dirname + '/index.html' , to :__dirname + '/dist/'})
copyLibDir.push({from: __dirname + '/static' , to : __dirname + '/dist/lib/vue-adminlte/dist'})
module.exports = {
    entry:  __dirname + "/src/main.js",//已多次提及的唯一入口文件
    output: {
        path: __dirname + "/dist/lib/vue-adminlte/dist/js",//打包后的文件存放的地方
        filename: "vue-adminlte.min.js"//打包后输出文件的文件名
    },
    module: {
        loaders: [
            {
                test: /\.vue$/,
                loader: 'vue-loader'
            }
        ]
    } ,
    plugins: [
        new CopyWebpackPlugin(copyLibDir)
    ]
}
