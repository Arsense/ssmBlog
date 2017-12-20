#Contributng to Classie

[![Built with Grunt](https://cdn.gruntjs.com/builtwith.png)](http://gruntjs.com/)

##Dependencies && Tools Used
Node  v0.10.28

Grunt

PhantomJS

CasperJS

JsHint

JsBeautify


Uglify

Browserify

Testem

Plato

Mocha


#Setup

`npm cache clean `

`sudo npm install -g phantomjs`

`sudo npm install -g grunt-cli`

`sudo npm install -g testem`

`sudo npm install`



Project --root

* lib
  *  classie.js
  



* Tests
  * fixtures --html fixtures
  * unit --all unit tests 

##Testem
[Testem-repo](https://github.com/airportyh/testem)

In order to run the test runner for your unit test use the testem command

`testem --or`

`testem ci --continuous intergration mode`

`testem launchers --output the avaiable browsers`

All configuration for the test runner is in

`testem.json`


you can copy http://localhost:7357 url and watch your test run in any browser

##Grunt

Theres 2 grunt commands you can issue

###grunt
`grunt`

This command starts up a watch task


It watch for changes on these folders and runs jshint/jsbeautify/uglify
all scripts/.js
all test/unit/.js

###Grunt coverage
`grunt coverage`

This task runs the plato task in the grunt config..
It will output a test/coverage folder with all your coverage reports for the  JS files you have in your project.

open the index file in test/coverage/index.html
`index.html`

##JsBeautify
`.jsbeautify`

This file takes all your .js files and adds some predefinded styling conventions to the src files.. He helps keep style and continuity within a project of its src file and helps create a convention on how the code should be structured 

##JsHint

`.jshintrc`

This is the file that hold a huge json configuration of all the hint options you would like to configure for the project..

See all possible options here [js-hint](http://www.jshint.com/docs/options/)



