LazyLoad = function(global) {

    var isIE = !1
    var hash = {}
    var doc  = global.document
    var head = doc.head || doc.getElementsByTagName('head')[0]
    var noop = function() {}

    function createEl(type, attrs) {
        var el = doc.createElement(type), attr
        for (attr in attrs) {
            el.setAttribute(attr, attrs[attr])
        }
        return el
    }
    function done(list, obj) {
        hash[obj.url] = true
        list.shift()
        if (!list.length) {
            obj.callback.call(obj.scope)
        }
    }
    function load(type, urls, config) {
        var charset = config.charset
        var obj = {
            scope: config.scope || global,
            callback: config.callback || noop
        }
        var list = [].concat(urls)
        for (var i = 0, len = urls.length; i < len; i++) {
            var el, url = urls[i]

            // 已经加载的不再加载
            if (hash[url]) {
                throw new Error('warning: ' + url + ' has loaded!')
            }

            if (type === 'js') {
                el = createEl('script', {
                    src: url,
                    async: 'async'
                })

            } else {
                el = createEl('link', {
                    href: url,
                    rel: 'stylesheet',
                    type: 'text/css'
                })
            }

            if (charset) {
                el.setAttribute('charset', charset)
            }

            (function(url) {
                if (isIE) {
                    el.onreadystatechange = function() {
                        var readyState = this.readyState
                        if(readyState === 'loaded' || readyState === 'complete') {
                            obj.url = url
                            this.onreadystatechange = null
                            done(list, obj)
                        }
                    }

                } else {
                    if (type == 'js') {
                        el.onload = el.onerror = function() {
                            obj.url = url
                            done(list, obj)
                        }
                    } else {
                        setTimeout(function() {
                            obj.url = url
                            done(list, obj)
                        }, 100)
                    }
                }
            })(url);

            if (type === 'js') {
                head.insertBefore(el, head.firstChild)
            } else {
                head.appendChild(el)
            }
        }

    }

    function _dep_js(libs  ,callback  , index) {
        var _libs = libs[index]
        var type = typeof  _libs
        var mlibs = []
        if(type == 'string') {
            mlibs.push(_libs)
        } else {
            mlibs = _libs
        }
        load('js' , mlibs , {
            callback: function () {
                if(index == libs.length - 1) {
                    callback()
                } else {
                    _dep_js(libs, callback, index + 1)
                }
            }
        })

    }



    return {
        js: function(urls, callback, scope) {
            load('js', urls, callback, scope)
        },
        css: function(urls, callback, scope) {
            load('css', urls, callback, scope)
        } ,
        dep_js : function (urls, callback, scope) {
            _dep_js(urls , callback , 0)
        }
    }
}(this);



(function() {

    var load_css = function (css) { document.write('<link href="'  + css + '" rel="stylesheet">') }

    var el = document.getElementsByTagName('v-app')
    if(el.length != 0) {
        el[0].setAttribute("id", "app")
        var body_el = document.getElementsByTagName('body')
        body_el[0].setAttribute('class', 'hold-transition skin-purple sidebar-mini')
    }else{
        el = document.getElementsByTagName('v-login')
        var body_el = document.getElementsByTagName('body')
        body_el[0].setAttribute('class', 'hold-transition login-page')
    }
    function tag_is_exist(tagName) {
        return document.getElementsByTagName(tagName).length > 0 ;
    }
    var arr_js = ['/lib/jquery/dist/jquery.min.js' ,
        '/lib/vue/dist/vue.min.js' ,
        '/lib/axios/dist/axios.min.js']

    if(tag_is_exist('v-select2')) {
        load_css('/lib/select2/dist/css/select2.min.css')
        arr_js.push('/lib/select2/dist/js/select2.full.min.js')
    }

    var js = [
        '/lib/vue-adminlte/dist/js/vue-adminlte.min.js',
        '/lib/bootstrap/dist/js/bootstrap.min.js' ,
        '/lib/admin-lte/dist/js/adminlte.js' ,
    ];

    var css = [
        '/lib/bootstrap/dist/css/bootstrap' ,
        '/lib/font-awesome/css/font-awesome' ,
        '/lib/admin-lte/dist/css/AdminLTE' ,
        '/lib/admin-lte/dist/css/skins/_all-skins'
    ];

    for(var i = 0; i < css.length; i++) {
        load_css(css[i] + ".min.css")
    }
    for(var i = 0; i < js.length; i++) {
        arr_js.push(js[i])
    }

    LazyLoad.dep_js(arr_js ,  function () {
        axios.interceptors.request.use(function (config) {
            // Do something before request is sent
            return config;
        }, function (error) {
            // Do something with request error
            return Promise.reject(error);
        });

        // Add a response interceptor
        axios.interceptors.response.use(function (response) {

            if(response.data.is_logon == false) {
                window.location.href = response.data.logon_url ;
            }
            return response;
        }, function (error) {
            // Do something with response error
            return Promise.reject(error);
        });

        axios.get('/get_app_info').then(function (response) {
            new Vue({
                el: '#app',
                data: function(){
                    return { data: response.data  ,ajax_url:"/get_table_data"};
                } ,

                mounted: function () {
                    $('body').data('lte.layout').fix()
                    $('body').data('lte.layout').fixSidebar()
                }
            })

        }).catch(function (error) {
            console.log(error);
        })
    })

})();
