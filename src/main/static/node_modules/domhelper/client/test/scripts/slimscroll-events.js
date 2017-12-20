'use strict';

// console.log("example test wepps!!s");

var Slimscroll = require('../../../lib/slimscroll');

function slimscrollEventHandler(e,pos){
    document.querySelector('#testDivOut').innerHTML +="Reached " + pos + ", ";
}

function slimscrollingEventHandler(e,pos){
	document.querySelector('#testDivOut2').innerHTML +="Scroll: " + pos + "px, ";
}

window.onload =function(){
	window.sscroll3 = new Slimscroll({
		alwaysVisible: true,
        height: '100px',
		idSelector: '.groupofdivs'
	});
	window.sscroll3.init();
	// window.sscroll1 = new Slimscroll({
	// 	alwaysVisible: true,
 //        height: '100px',
	// 	idSelector: '#testDiv'
	// });
	// window.sscroll1.init();
	// window.sscroll2 = new Slimscroll({
	// 		height: '200px',
	// 		idSelector: '#testDiv2'
 //      });
	// window.sscroll2.init();

	// document.querySelector('#testDiv').addEventListener('slimscroll',slimscrollEventHandler);
	// document.querySelector('#testDiv2').addEventListener('slimscrolling',slimscrollingEventHandler);
};