'use strict';

// console.log("example test wepps!!s");

var linotype = require('../../../index');

window.onload =function(){
	window.Linotype = new linotype({
		verticalCentered: true,
		slidesColor: ['#1bbc9b', '#4BBFC3', '#7BAABE'],
		afterRender: function(){
			//setting the size
			document.querySelector('video').style.height=window.innerHeight+'px';
			//playing the video
			document.querySelector('video').play();
		}
	});
	window.Linotype.init();
};