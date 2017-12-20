(function e(t,n,r){function s(o,u){if(!n[o]){if(!t[o]){var a=typeof require=="function"&&require;if(!u&&a)return a(o,!0);if(i)return i(o,!0);throw new Error("Cannot find module '"+o+"'")}var f=n[o]={exports:{}};t[o][0].call(f.exports,function(e){var n=t[o][1][e];return s(n?n:e)},f,f.exports,e,t,n,r)}return n[o].exports}var i=typeof require=="function"&&require;for(var o=0;o<r.length;o++)s(r[o]);return s})({1:[function(require,module,exports){
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
},{"../../../lib/slimscroll":3}],2:[function(require,module,exports){
/*
 * linotype
 * https://github.com/typesettin/linotype
 * @author yaw joseph etse
 * Copyright (c) 2014 Typesettin. All rights reserved.
 */

'use strict';

var classie = require('classie');
// 	extend = require('util-extend'),
// 	events = require('events'),
// 	util = require('util');

/**
 * A module that adds simple dom utility functionality.
 * @author yaw joseph etse
 * @constructor
 */

var domhelper = {

	/**
	 * toggles class across nodelist/elementcollection
	 * @param {object} elementCollection - html dom element
	 * @param {object} element - html dom element
	 * @param {string} name of class!
	 * @public
	 */
	removeAllClassAndToggle: function(element,elementCollection,toggleClass){
		//updating the active class
		for(var h =0; h <elementCollection.length; h++){
			classie.removeClass(elementCollection[h],toggleClass);
		}
		classie.addClass(element,toggleClass);
	},
	/**
	 * removes element from dom
	 * @param {object} elementCollection - html dom element
	 * @public
	 */
	removeElement: function(element){
		//updating the active class
		element.parentNode.removeChild(element);
	},
	/**
	 * converts idnex of node in nodelist
	 * @param {object} nodelist - html dom element
	 * @param {object} element - html dom element
	 * @return {number} index of element in nodelist
	 * @method
	 */
	nodeIndexOfNodeList: function(nodelist,element){
		return domhelper.nodelistToArray(nodelist,true).indexOf(element.outerHTML);
    },

	/**
	 * converts nodelists to arrays
	 * @param {node} nl - html dom element
	 * @return { array} array of html nodes
	 * @method
	 */
	nodelistToArray: function(nl,useStrings){
		var arr = [];
		for (var i = 0, ref = arr.length = nl.length; i < ref; i++) {
			arr[i] = (useStrings) ? nl[i].outerHTML : nl[i];
		}
		return arr;
    },

	/**
	 * Returns cloaset DOM element.
	 * @param {node} element - html dom element
	 * @return {node} - closet node element
	 * @method
	 */
    closetElement: function(element){
		if(typeof element.length === 'number'){
			return undefined;
		}
		var matches = domhelper.nodelistToArray(document.querySelectorAll(element.nodeName+'.'+element.className.trim().split(" ").join("."))),
			cleanMatches = [];
		// console.log("matches",matches.length,matches);

		for (var x =0; x < matches.length; x++){
			// console.log('x',x,'element',element,'matches[x]',matches[x],'isEqualNode',matches[x].isEqualNode(element),'compareDocumentPosition',element.compareDocumentPosition(matches[x]));
			if(element.compareDocumentPosition(matches[x])<4 && !matches[x].isEqualNode(element)){
				cleanMatches.push(matches[x]);
			}
		}

		function compareNumbers(a, b) {
			return a.compareDocumentPosition( b ) - b.compareDocumentPosition( a );
		}
		// console.log("matches cleaned",cleanMatches.length,cleanMatches);
		// console.log("matches sorted",cleanMatches.sort(compareNumbers));
		return cleanMatches[0];
	},

	/**
	 * Hides DOM elements.
	 * @method
	 * @param {node} element - html dom element
	 */
	elementHideCss: function(element){
		element.style.display="none";
	},

	/**
	 * Shows DOM elements.
	 * @method
	 * @param {node} element - html dom element
	 */
	elementShowCss: function(element){
		element.setAttribute('style',element.getAttribute('style').replace("display: none;"));
	},

	/**
	 * Wraps inner elements
	 * @method
	 * @param {node} element - html dom element
	 * @param {node} innerElement - element to wrap html dom element
	 */
	elementContentWrapInner: function(element,innerElement){
		var wrapper = element,
			w = innerElement,
			len = element.childElementCount,
			wrapper_clone = wrapper.cloneNode(true);

		wrapper.innerHTML='';
		wrapper.appendChild(w);
		var newFirstChild = wrapper.firstChild;

		newFirstChild.innerHTML=wrapper_clone.innerHTML;
	},

	/**
	 * Wraps element with wrapper
	 * @method
	 * @param {node} element - html dom element
	 * @param {node} wrapperElement - element to wrap html dom element
	 */
	elementWrap: function(element,wrapperElement){
		var elementParent =element.parentNode,
			element_clone = element.cloneNode(true);

		elementParent.replaceChild(wrapperElement,element);
		wrapperElement.appendChild(element);
	},


	/**
	 * get scroll position of element
	 * @method
	 * @param {node} element - html dom element
	 * @return {number} position of scroll
	 */
	getScrollTop: function(element){
		// console.log(typeof element);
		if(element === window && typeof window.pageYOffset!== 'undefined'){
			//most browsers except IE before #9
			return window.pageYOffset;
		}
		else if(typeof element ==="object"){
			return element.scrollTop;
		}
		else {
			var B= document.body; //IE 'quirks'
			var D= document.documentElement; //IE with doctype
			D= (D.clientHeight)? D: B;
			return D.scrollTop;
		}
	},

	/**
	 * get scroll position of element
	 * @method
	 * @param {node} element - html dom element
	 * @return {object} position element
	 */
	getPosition: function(element) {
		var xPosition = 0;
		var yPosition = 0;

		while(element) {
			xPosition += (element.offsetLeft - element.scrollLeft + element.clientLeft);
			yPosition += (element.offsetTop - element.scrollTop + element.clientTop);
			element = element.offsetParent;
		}
		return { x: xPosition, y: yPosition, left: xPosition, top: yPosition };
	},

	/**
	 * get element selector
	 * @method
	 * @param {node} element - html dom element
	 * @return {string} query selector string
	 */
	getElementSelector: function(element){
		var tagSelector = (element.tagName) ? element.tagName:'',
			idSelector = (element.id) ? '#'+element.id+'':'',
			classSelector='';
		if(element.classList){
			for(var x=0; x < element.classList.length; x++){
				classSelector+='.'+element.classList[x]+"";
			}
		}
		return tagSelector+idSelector+classSelector;
	},

	/**
	 * get parent element
	 * @method
	 * @param {node} element - html dom element
	 * @param {string} selector - selector
	 * @param {string} selectorType - selector type (id or class)
	 */
	getParentElement: function(element,selector,selectorType){
		if(element.tagName==='BODY' || element.tagName==='HTML' || selector==='body' || selector==='html' || selector===undefined){
			// console.log('body selected');
			return undefined;
		}
		else if( (selectorType==='id' && element.parentNode.id === selector) || element.parentNode.className.match(new RegExp(selector,'g'))){
			// console.log("parent node");
			return element.parentNode;

			//new RegExp(pattern,modifiers)
		}
		else  {
			// console.log("look up higher");
			return domhelper.getParentElement(element.parentNode,selector,selectorType);
		}
	},

	getPreviousElements: function(element,returnArray){
		if(element.previousElementSibling){
			returnArray.push(element.previousElementSibling);
			return domhelper.getPreviousElements(element.previousElementSibling,returnArray);
		}
		else{
			return returnArray;
		}
	},


	getNextElements: function(element,returnArray){
		if(element.nextElementSibling){
			returnArray.push(element.nextElementSibling);
			return domhelper.getNextElements(element.nextElementSibling,returnArray);
		}
		else{
			return returnArray;
		}
	},

	insertAllBefore: function(element,elementsToInsert){
		var parentElement = element.parentNode;
		// console.log("parentElement",parentElement,"element",element,"elementsToInsert",elementsToInsert);
		if(elementsToInsert.length){
			for(var x =0; x<elementsToInsert.length; x++){
				// console.log(x,"elementsToInsert[x]",elementsToInsert[x])
				parentElement.insertBefore(elementsToInsert[x],element);
			}
		}
		else{
			parentElement.insertBefore(elementsToInsert,element);
		}
	},

	insertAllAfter: function(element,elementsToInsert){
		var parentElement = element.parentNode;
		var nextSibling = element.nextSibling;
		// console.log("parentElement",parentElement,"element",element,"elementsToInsert",elementsToInsert);
		if(elementsToInsert.length){
			for(var x =0; x<elementsToInsert.length; x++){
				// console.log(x,"elementsToInsert[x]",elementsToInsert[x])
				// elementsToInsert[x].style.background="green";
				parentElement.insertBefore(elementsToInsert[x],nextSibling);
			}
		}
		else{
			parentElement.insertBefore(elementsToInsert,nextSibling);
		}
	},

	unwrapElement: function(element){
		var parentNodeElem = element.parentNode;
		if(parentNodeElem.nodeName !== "BODY"){
			var parentParentNodeElem = parentNodeElem.parentNode;
			parentParentNodeElem.innerHTML='';
			parentParentNodeElem.appendChild(element);
		}
	},
	onWindowLoaded: function(callback){
		var readyStateCheckInterval = setInterval(function() {
		    if (document.readyState === "complete") {
		        callback();
		        clearInterval(readyStateCheckInterval);
		    }
		}, 10);
	}

};

module.exports = domhelper;

// If there is a window object, that at least has a document property,
// define linotype
if ( typeof window === "object" && typeof window.document === "object" ) {
	window.domhelper = domhelper;
}
},{"classie":4}],3:[function(require,module,exports){
/*
 * slimscroll
 * http://github.com/yawetse/slimscroll
 *
 * Copyright (c) 2014 Yaw Joseph Etse. All rights reserved.
 */
'use strict';

var classie = require('classie'),
	extend = require('util-extend'),
	domhelper = require('./domhelper');
// 	events = require('events'),
// 	util = require('util');

/**
 * creates slim scrollers.
 * @author yaw joseph etse
 * @module
 */
var slimscroll = function(options,elementsArray){
	var defaults = {
			idSelector: 'body',
			width : 'auto',// width in pixels of the visible scroll area
			height : '250px',// height in pixels of the visible scroll area
			size : '7px',// width in pixels of the scrollbar and rail
			color: '#000',// scrollbar color, accepts any hex/color value
			position : 'right',// scrollbar position - left/right
			distance : '1px',// distance in pixels between the side edge and the scrollbar
			start : 'top',// default scroll position on load - top / bottom / $('selector')
			opacity : 0.4,// sets scrollbar opacity
			alwaysVisible : false,// enables always-on mode for the scrollbar
			disableFadeOut : false,// check if we should hide the scrollbar when user is hovering over
			railVisible : false,// sets visibility of the rail
			railColor : '#333',// sets rail color
			railOpacity : 0.2,// sets rail opacity
			railDraggable : true,// whether  we should use jQuery UI Draggable to enable bar dragging
			railClass : 'slimScrollRail',// defautlt CSS class of the slimscroll rail
			barClass : 'slimScrollBar',// defautlt CSS class of the slimscroll bar
			wrapperClass : 'slimScrollDiv',// defautlt CSS class of the slimscroll wrapper
			allowPageScroll : false,// check if mousewheel should scroll the window if we reach top/bottom
			wheelStep : 20,// scroll amount applied to each mouse wheel step
			touchScrollStep : 200,// scroll amount applied when user is using gestures
			addedOriginalClass: 'originalScrollableElement',
			borderRadius: '7px',// sets border radius
			railBorderRadius : '7px'// sets border radius of the rail
		},
		o = extend( defaults,options ),
		thisElements = (elementsArray) ? elementsArray : document.querySelectorAll(options.idSelector),
		me,
		rail,
		bar,
		barHeight,
		minBarHeight = 30,
		mousedownPageY,
		mousedownT,
		isDragg,
		currentBar,
		currentTouchDif,
		releaseScroll,
		isOverBar,
		percentScroll,
		queueHide,
		lastScroll,
		isOverPanel;

	this.init = function(){
		// do it for every element that matches selector
		for(var x=0; x<thisElements.length;x++){
			var touchDif,
			barHeight,
			divS = '<div></div>';
			releaseScroll = false;
			// used in event handlers and for better minification
			me = thisElements[x];
			classie.addClass(me,o.addedOriginalClass);

			// ensure we are not binding it again
			if( classie.hasClass(me.parentNode,o.wrapperClass) ){
				// start from last bar position
				var offset = me.scrollTop;
				bar = me.parentNode.querSelector('.' + o.barClass),// find bar and rail,
				rail = me.parentNode.querSelector('.' + o.railClass);

				getBarHeight();

				// check if we should scroll existing instance
				if (typeof options==='object'){
					// Pass height: auto to an existing slimscroll object to force a resize after contents have changed
					if ( 'height' in options && options.height === 'auto' ) {
						me.parentNode.style.height='auto';
						me.style.height='auto';
						var height = me.parentNode.parentNode.scrollHeight;
						me.parent.style.height=height;
						me.style.height=height;
					}

					if ('scrollTo' in options){
						// jump to a static point
						offset = parseInt(o.scrollTo,10);
					}
					else if ('scrollBy' in options){
						// jump by value pixels
						offset += parseInt(o.scrollBy,10);
					}
					else if ('destroy' in options){
						// remove slimscroll elements
						domhelper.removeElement(bar);
						domhelper.removeElement(rail);
						domhelper.unwrapElement(me);
						return;
					}

					// scroll content by the given offset
					// console.log("add scrollContent");
					scrollContent(offset, false, true,me);
				}
				return;
			}

			// optionally set height to the parent's height
			o.height = (options.height === 'auto') ? me.parentNode.offsetHeight : options.height;

			// wrap content
			var wrapper = document.createElement("div");
			classie.addClass(wrapper,o.wrapperClass);
			wrapper.style.position= 'relative';
			wrapper.style.overflow= 'hidden';
			wrapper.style.width= o.width;
			wrapper.style.height= o.height;

			// update style for the div
			me.style.overflow= 'hidden';
			me.style.width= o.width;
			me.style.height= o.height;

			// create scrollbar rail
			rail = document.createElement("div");
			classie.addClass(rail,o.railClass);
			rail.style.width= o.size;
			rail.style.height= '100%';
			rail.style.position= 'absolute';
			rail.style.top= 0;
			rail.style.display= (o.alwaysVisible && o.railVisible) ? 'block' : 'none';
			rail.style['border-radius']= o.railBorderRadius;
			rail.style.background= o.railColor;
			rail.style.opacity= o.railOpacity;
			rail.style.zIndex= 90;

			// create scrollbar
			bar =  document.createElement("div");
			classie.addClass(bar,o.barClass);
			bar.style.background= o.color;
			bar.style.width= o.size;
			bar.style.position= 'absolute';
			bar.style.top= 0;
			bar.style.opacity= o.opacity;
			bar.style.display= o.alwaysVisible ? 'block' : 'none';
			bar.style['border-radius'] = o.borderRadius;
			bar.style.BorderRadius= o.borderRadius;
			bar.style.MozBorderRadius= o.borderRadius;
			bar.style.WebkitBorderRadius= o.borderRadius;
			bar.style.zIndex= 99;

			// set position
			if(o.position === 'right'){
				rail.style.right = o.distance;
				bar.style.right = o.distance;
			}
			else{
				rail.style.left = o.distance;
				bar.style.left = o.distance;
			}

			// wrap it
			domhelper.elementWrap(me,wrapper);

			// append to parent div
			me.parentNode.appendChild(bar);
			me.parentNode.appendChild(rail);

			// set up initial height
			getBarHeight();

			// make it draggable and no longer dependent on the jqueryUI
			bar.addEventListener("mousedown",mousedownEventHandler);
			document.addEventListener("mouseup",mouseupEventHandler);
			bar.addEventListener("selectstart",selectstartEventHandler);
			bar.addEventListener("mouseover",mouseoverEventHandler);
			bar.addEventListener("mouseleave",mouseleaveEventHandler);
			bar.addEventListener('touchstart',scrollContainerTouchStartEventHandler);

			rail.addEventListener("mouseover",railMouseOverEventHandler);
			rail.addEventListener("mouseleave",railMouseLeaveEventHandler);

			me.addEventListener("mouseover",scrollContainerMouseOverEventHandler);
			me.addEventListener("mouseleave",scrollContainerMouseLeaveEventHandler);
			me.addEventListener('DOMMouseScroll', mouseWheelEventHandler, false );
			me.addEventListener('mousewheel', mouseWheelEventHandler, false );
		}

		// check start position
		if (o.start === 'bottom'){
			// scroll content to bottom
			bar.style.top= me.offsetHeight - bar.offsetHeight;
			scrollContent(0, true);
		}
		else if (o.start !== 'top'){
			// assume jQuery selector
			scrollContent( domhelper.getPosition(document.querSelector(o.start).top, null, true));

			// make sure bar stays hidden
			if (!o.alwaysVisible) {
				domhelper.elementHideCss(bar);
			}
		}
		document.addEventListener('touchmove',scrollContainerTouchMoveEventHandler);
	}.bind(this);

	function getBarHeight(){
		if(!bar){
			bar = currentBar;
		}
		// calculate scrollbar height and make sure it is not too small
		barHeight = Math.max((me.offsetHeight / me.scrollHeight) * me.offsetHeight, minBarHeight);
		bar.style.height= barHeight + 'px' ;

		// hide scrollbar if content is not long enough
		var display = (me.offsetHeight === barHeight) ? 'none' : 'block';
		bar.style.display= display;
	}

	function scrollContent(y, isWheel, isJump,element,bar,isTouch){
		releaseScroll = false;
		var delta = y;
		me = element;
		bar = (bar)? bar : me.parentNode.querySelector('.'+o.barClass);
		var maxTop = me.offsetHeight - bar.offsetHeight;

		if (isWheel){
			// move bar with mouse wheel
			delta = parseInt(bar.style.
				top,10) + y * parseInt(o.wheelStep,10) / 100 * bar.offsetHeight;

			// move bar, make sure it doesn't go out
			delta = Math.min(Math.max(delta, 0), maxTop);

			// if scrolling down, make sure a fractional change to the
			// scroll position isn't rounded away when the scrollbar's CSS is set
			// this flooring of delta would happened automatically when
			// bar.css is set below, but we floor here for clarity
			delta = (y > 0) ? Math.ceil(delta) : Math.floor(delta);

			// scroll the scrollbar
			bar.style.top= delta + 'px';
		}
		else if(isTouch){
			// calculate actual scroll amount
			percentScroll = parseInt(bar.style.top,10) / (me.offsetHeight - bar.offsetHeight);
			delta = percentScroll * (me.scrollHeight - me.offsetHeight);

			// scroll the scrollbar
			bar.style.top= delta + 'px';
		}

		// calculate actual scroll amount
		percentScroll = parseInt(bar.style.top,10) / (me.offsetHeight - bar.offsetHeight);
		delta = percentScroll * (me.scrollHeight - me.offsetHeight);

		if (isJump){
			delta = y;
			var offsetTop = delta / me.scrollHeight * me.offsetHeight;
			offsetTop = Math.min(Math.max(offsetTop, 0), maxTop);
			bar.style.top= offsetTop + 'px';
		}

		// scroll content
		me.scrollTop=delta;

		// console.log("delta",delta,"~~delta",~~delta);
		// fire scrolling event
		// me.dispatchEvent(slimScrollEvent)
		var newevent = document.createEvent("Event");
		newevent.initEvent('slimscrolling',true,true,"blah");
		me.dispatchEvent(newevent, ~~delta);
		// me.trigger('slimscrolling', ~~delta);

		// ensure bar is visible
		showBar();

		// trigger hide when scroll is stopped
		hideBar();
	}
	function showBar(){
		// recalculate bar height
		getBarHeight();
		clearTimeout(queueHide);

		// when bar reached top or bottom
		if (percentScroll === ~~percentScroll){
			//release wheel
			releaseScroll = o.allowPageScroll;

			// publish approporiate event
			if (lastScroll !== percentScroll){
				var msg = (~~percentScroll === 0) ? 'top' : 'bottom';
				var newevent = document.createEvent("Event");
				newevent.initEvent('slimscroll',true,true);
				me.dispatchEvent(newevent, msg);
			}
		}
		else{
			releaseScroll = false;
		}
		lastScroll = percentScroll;

		// show only when required
		if(barHeight >= me.offsetHeight) {
			//allow window scroll
			releaseScroll = true;
			return;
		}
		bar.style.transition="opacity .5s";
		bar.style.opacity=o.opacity;
		if (o.railVisible) {
			rail.style.transform="opacity .5s";
			rail.style.opacity=1;
		}
	}

	function hideBar(){
		// only hide when options allow it
		if (!o.alwaysVisible){
			queueHide = setTimeout(function(){
				if (!(o.disableFadeOut && isOverPanel) && !isOverBar && !isDragg){
					bar.style.transition="opacity 1s";
					bar.style.opacity=0;
					rail.style.transition="opacity 1s";
					rail.style.opacity=0;
				}
			}, 500);
		}
	}

	function mouseWheelEventHandler(e){
		// use mouse wheel only when mouse is over
		if (!isOverPanel) { return; }

		var delta = 0;
		if (e.wheelDelta) {
			delta = -e.wheelDelta/120;
		}
		if (e.detail) {
			delta = e.detail / 3;
		}

		var target = e.target;
		var parentWrapper = domhelper.getParentElement(target,o.wrapperClass);
		if (parentWrapper /* && parentWrapper.isEqualNode(me.parentNode)*/ ){
			// scroll content
			scrollContent(delta, true,null,parentWrapper.querySelector('.'+o.addedOriginalClass));
		}
		else{
			console.log("not the right parent node");
		}

		// stop window scroll
		if (!releaseScroll) {
			e.preventDefault();
		}
	}

	function mousedownEventHandler(e){
		var eTarget = e.target;
		currentBar = eTarget;
		isDragg = true;
		mousedownT = parseInt(eTarget.style.top,10);
		mousedownPageY = e.pageY;
		if(currentBar){
			currentBar.addEventListener("mousemove",mousemoveEventHandler);
		}
		e.preventDefault();
		return false;
	}

	function mousemoveEventHandler(e){
		var currTop = mousedownT + e.pageY - mousedownPageY;
		if(currentBar){
			currentBar.style.top=currTop;
			scrollContent(0, domhelper.getPosition(currentBar).top, false,me,currentBar);// scroll content
		}
	}

	function mouseupEventHandler(e){
		isDragg = false;
		if(currentBar){
			hideBar(currentBar);
			currentBar.removeEventListener('mousemove',mousemoveEventHandler);
		}
	}

	function mouseoverEventHandler(e){ isOverBar = true; }

	function mouseleaveEventHandler(e){ isOverBar = false; }

	function selectstartEventHandler(e){
		// e.stopPropagation();
		// e.preventDefault();
		return false;
	}

	function railMouseOverEventHandler(e){ showBar(); }

	function railMouseLeaveEventHandler(e){ hideBar(); }

	function scrollContainerMouseOverEventHandler(e){
		isOverPanel = true;
		showBar(bar);
		hideBar(bar);
	}

	function scrollContainerMouseLeaveEventHandler(e){
		isOverPanel = true;
		showBar(bar);
		hideBar(bar);
	}

	function scrollContainerTouchStartEventHandler(e){
		// console.log(e.target);
		if (e.touches.length){
			// record where touch started
			currentTouchDif = e.touches[0].pageY;
		}
	}

	function scrollContainerTouchMoveEventHandler(e){
		// prevent scrolling the page if necessary
		if(!releaseScroll){
			e.preventDefault();
		}
		if(e.touches.length){
			// see how far user swiped
			var diff = (currentTouchDif - e.touches[0].pageY) / o.touchScrollStep;
			// scroll content
			scrollContent(diff, true,null,me,currentBar,true);
			currentTouchDif = e.touches[0].pageY;
		}
	}
};

module.exports = slimscroll;

// If there is a window object, that at least has a document property,
// define linotype
if ( typeof window === "object" && typeof window.document === "object" ) {
	window.slimscroll = slimscroll;
}
},{"./domhelper":2,"classie":4,"util-extend":6}],4:[function(require,module,exports){
/*
 * classie
 * http://github.amexpub.com/modules/classie
 *
 * Copyright (c) 2013 AmexPub. All rights reserved.
 */

module.exports = require('./lib/classie');

},{"./lib/classie":5}],5:[function(require,module,exports){
/*!
 * classie - class helper functions
 * from bonzo https://github.com/ded/bonzo
 * 
 * classie.has( elem, 'my-class' ) -> true/false
 * classie.add( elem, 'my-new-class' )
 * classie.remove( elem, 'my-unwanted-class' )
 * classie.toggle( elem, 'my-class' )
 */

/*jshint browser: true, strict: true, undef: true */
/*global define: false */
'use strict';

  // class helper functions from bonzo https://github.com/ded/bonzo

  function classReg( className ) {
    return new RegExp("(^|\\s+)" + className + "(\\s+|$)");
  }

  // classList support for class management
  // altho to be fair, the api sucks because it won't accept multiple classes at once
  var hasClass, addClass, removeClass;

  if (typeof document === "object" && 'classList' in document.documentElement ) {
    hasClass = function( elem, c ) {
      return elem.classList.contains( c );
    };
    addClass = function( elem, c ) {
      elem.classList.add( c );
    };
    removeClass = function( elem, c ) {
      elem.classList.remove( c );
    };
  }
  else {
    hasClass = function( elem, c ) {
      return classReg( c ).test( elem.className );
    };
    addClass = function( elem, c ) {
      if ( !hasClass( elem, c ) ) {
        elem.className = elem.className + ' ' + c;
      }
    };
    removeClass = function( elem, c ) {
      elem.className = elem.className.replace( classReg( c ), ' ' );
    };
  }

  function toggleClass( elem, c ) {
    var fn = hasClass( elem, c ) ? removeClass : addClass;
    fn( elem, c );
  }

  var classie = {
    // full names
    hasClass: hasClass,
    addClass: addClass,
    removeClass: removeClass,
    toggleClass: toggleClass,
    // short names
    has: hasClass,
    add: addClass,
    remove: removeClass,
    toggle: toggleClass
  };

  // transport

  if ( typeof module === "object" && module && typeof module.exports === "object" ) {
    // commonjs / browserify
    module.exports = classie;
  } else {
    // AMD
    define(classie);
  }

  // If there is a window object, that at least has a document property,
  // define classie
  if ( typeof window === "object" && typeof window.document === "object" ) {
    window.classie = classie;
  }
},{}],6:[function(require,module,exports){
// Copyright Joyent, Inc. and other Node contributors.
//
// Permission is hereby granted, free of charge, to any person obtaining a
// copy of this software and associated documentation files (the
// "Software"), to deal in the Software without restriction, including
// without limitation the rights to use, copy, modify, merge, publish,
// distribute, sublicense, and/or sell copies of the Software, and to permit
// persons to whom the Software is furnished to do so, subject to the
// following conditions:
//
// The above copyright notice and this permission notice shall be included
// in all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
// OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
// MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN
// NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
// DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
// OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE
// USE OR OTHER DEALINGS IN THE SOFTWARE.

module.exports = extend;
function extend(origin, add) {
  // Don't do anything if add isn't an object
  if (!add || typeof add !== 'object') return origin;

  var keys = Object.keys(add);
  var i = keys.length;
  while (i--) {
    origin[keys[i]] = add[keys[i]];
  }
  return origin;
}

},{}]},{},[1])