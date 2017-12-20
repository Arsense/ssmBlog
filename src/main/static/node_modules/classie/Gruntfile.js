'use strict';

module.exports = function(grunt) {

    //Project configuration.
    grunt.initConfig({

        jshint: {
            files: ['test/unit/**/*.js', 'test/intergration/**/*.js', 'lib/**/*.js', '!node_modules/*', '!test/helpers/**/*.js'],
            options: {
                jshintrc: '.jshintrc',
                reporter: require('jshint-stylish')
            }
        },

        jsbeautifier: {
            files: ['lib/**/*.js', 'test/unit/**/*.js', 'Gruntfile.js'],
            options: {
                config: '.jsbeautify'
            }
        },

        uglify: {
            options: {
                sourceMap: true,
                compress: {
                    drop_console: true
                }
            },
            prod: {
                files: {
                    'classie.min.js': ['lib/**/*.js']
                }
            }
        },

        browserify: {
            options: {
                debug: true
            },
            test: {
                files: {
                    'test/browserified.js': ['test/unit/**/*.js']
                }
            }
        },

        coverage: {
            options: {
                thresholds: {
                    'statements': 90,
                    'branches': 90,
                    'lines': 90,
                    'functions': 90
                },
                dir: 'coverage',
                root: 'test'
            }
        },

        plato: {
            lint: {
                options: {
                    jshint: grunt.file.readJSON('.jshintrc'),
                    dir: "test/coverage",
                    title: grunt.file.readJSON('package.json').name,
                    complexity: {
                        minmi: true,
                        forin: true,
                        logicalot: false
                    }
                },
                files: {
                    'test/coverage': ['lib/**/*.js']
                }
            },
        },
        watch: {
            options: {
                livereload: true,
            },
            jshint: {
                tasks: ['jshint'],
                files: ['test/unit/**/*.js', 'test/intergration/**/*.js', 'lib/**/*.js', '!node_modules/*', '!test/helpers/**/*.js']
            },
            uglify: {
                tasks: ['uglify'],
                files: ['lib/*.js']
            },
            jsbeautifier: {
                tasks: ['jsbeautifier'],
                files: ['test/unit/**/*.js', 'test/intergration/**/*.js', 'lib/**/*.js', '!node_modules/*', '!test/helpers/**/*.js']
            }
        }

    });

    //automatically load deps from package.jso
    for (var key in grunt.file.readJSON("package.json").devDependencies) {
        if (key.indexOf("grunt") === 0 && key !== "grunt") {
            grunt.loadNpmTasks(key);
        }
    }

    require('time-grunt')(grunt);

    grunt.registerTask('cover', ['plato']);
    grunt.registerTask('test', ['browserify:test']);
    grunt.registerTask('default', ['watch']);
};
