'use strict';

// console.log("example test wepps!!s");

var linotype = require('../../../index');

window.onload =function(){
	window.Linotype = new linotype({
		verticalCentered: false
	});
	window.Linotype.init();
};