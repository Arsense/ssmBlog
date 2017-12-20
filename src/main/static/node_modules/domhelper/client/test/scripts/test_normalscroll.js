'use strict';

// window.onload =function(){
// 	window.Linotype = new linotype({
// 		menu: '#menu',
// 		anchors: ['firstPage', 'secondPage', '3rdPage'],
// 		slidesColor: ['#C63D0F', '#1BBC9B', '#7E8F7C'],
// 		autoScrolling: false,
// 		css3: true
// 	});

// 	window.Linotype.init();
// };

// console.log("example test wepps!!s");

var linotype = require('../../../index');

window.onload =function(){
	window.Linotype = new linotype({
		menu: '#menu',
		anchors: ['firstPage', 'secondPage', '3rdPage'],
		slidesColor: ['#C63D0F', '#1BBC9B', '#7E8F7C'],
		autoScrolling: false,
		css3: true
	});

	window.Linotype.init();
};