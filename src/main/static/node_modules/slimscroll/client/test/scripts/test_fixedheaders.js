'use strict';

// console.log("example test wepps!!s");

var linotype = require('../../../index');

window.onload =function(){
	window.Linotype = new linotype({
		anchors: ['firstPage', 'secondPage', '3rdPage'],
		slidesColor: ['#C63D0F', '#1BBC9B', '#7E8F7C'],
		css3: true
	});
	window.Linotype.init();
};