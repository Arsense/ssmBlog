'use strict';

// console.log("example test wepps!!s");

var linotype = require('../../../index');

window.onload =function(){
	window.Linotype = new linotype({
		anchors: ['firstPage', 'secondPage', '3rdPage', '4thPage'],
		'slidesColor': ['#4A6FB1', '#939FAA', '#323539'],
		'scrollOverflow': true,
		'fixedElements':'#demosMenu'
	});

	window.Linotype.init();
};