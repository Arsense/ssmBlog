'use strict';

// console.log("example test wepps!!s");

var Slimscroll = require('../../../lib/Slimscroll');

window.onload =function(){
	window.sscroll1 = new Slimscroll({
		height: 'auto',
		idSelector: '#testDiv'
	});
	window.sscroll1.init();
	window.sscroll2 = new Slimscroll({
			height: '100px',
			width: '300px',
			idSelector: '#testDiv2'
      });
	window.sscroll2.init();
	window.sscroll3 = new Slimscroll({
		idSelector: '#testDiv3'
	});
	window.sscroll3.init();
};
