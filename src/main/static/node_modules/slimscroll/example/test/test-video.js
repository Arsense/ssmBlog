(function e(t,n,r){function s(o,u){if(!n[o]){if(!t[o]){var a=typeof require=="function"&&require;if(!u&&a)return a(o,!0);if(i)return i(o,!0);throw new Error("Cannot find module '"+o+"'")}var f=n[o]={exports:{}};t[o][0].call(f.exports,function(e){var n=t[o][1][e];return s(n?n:e)},f,f.exports,e,t,n,r)}return n[o].exports}var i=typeof require=="function"&&require;for(var o=0;o<r.length;o++)s(r[o]);return s})({1:[function(require,module,exports){
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
},{"../../../index":2}],2:[function(require,module,exports){
/*
 * linotype
 * http://github.amexpub.com/modules/linotype
 *
 * Copyright (c) 2013 AmexPub. All rights reserved.
 */

module.exports = require('./lib/linotype');

},{"./lib/linotype":4}],3:[function(require,module,exports){
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
},{"classie":11}],4:[function(require,module,exports){
/**
 * @title linotype
 * @{@link https://github.com/typesettin/linotype}
 * @author Yaw Joseph Etse
 * @copyright Copyright (c) 2014 Typesettin. All rights reserved.
 * @license MIT
 */

'use strict';

var classie = require('classie'),
	extend = require('util-extend'),
	events = require('events'),
	domhelper = require('./domhelper'),
	Slimscroll = require('./slimscroll'),
	util = require('util');

/**
 * A module that represents a linotype object, a linotyper is a page composition tool.
 * @{@link https://github.com/typesettin/linotype}
 * @author Yaw Joseph Etse
 * @copyright Copyright (c) 2014 Typesettin. All rights reserved.
 * @license MIT
 * @module linotype
 * @requires module:classie
 * @requires module:util-extent
 * @requires module:util
 * @requires module:events
 * @todo need to finish the .closet implementation for @linotype.getTableHeight method
 * @todo need @linotype~checkParentForNormalScrollElement to fix parent.isEqualNode to loop through selector of normalscrollelements called from @linotype~touchMoveHandler
 * @todo fix resizeme text sizing
 * @throws {ConfigurationError} If conflicting scrolling options are set  
 */
var linotype = function(config_options){
	/** module default configuration */
	var options,
		linotypeElement,
		defaults = {
			'verticalCentered' : true,
			'resize' : true,
			'slidesColor' : [],
			'anchors':[],
			'scrollingSpeed': 700,
			'easing': 'easeInQuart',
			'menu': false,
			'navigation': false,
			'navigationPosition': 'right',
			'navigationColor': '#000',
			'navigationTooltips': [],
			'slidesNavigation': false,
			'slidesNavPosition': 'bottom',
			'controlArrowColor': '#fff',
			'loopBottom': false,
			'loopTop': false,
			'loopHorizontal': true,
			'autoScrolling': true,
			'scrollOverflow': false,
			'css3': true,
			'paddingTop': 0,
			'paddingBottom': 0,
			'fixedElements': null,
			'normalScrollElements': null,
			'keyboardScrolling': true,
			'touchSensitivity': 5,
			'continuousVertical': false,
			'animateAnchor': true,
			'idSelector' : 'fullpage',
			'normalScrollElementTouchThreshold': 5,

			//events
			'afterLoad': null,
			'onLeave': null,
			'afterRender': null,
			'afterResize': null,
			'afterSlideLoad': null,
			'onSlideLeave': null
		},
		ConfigurationError = function(message){
			this.name = "ConfigurationError";
			this.message = message || "Linotype Configuration Error";
		},
		scrollDelay = (typeof config_options === "object" && typeof config_options.scrollDelay === "number") ? config_options.scrollDelay : 600,
		container,
		slideMoving = false,
		isTablet = navigator.userAgent.match(/(iPhone|iPod|iPad|Android|BlackBerry|Windows Phone)/),
		windowsHeight,
		isMoving = false,
		isResizing = false,
		resizeTimeout = false,
		lastScrolledDestiny,
		lastScrolledSlide,
		scrollId,
		isScrolling = false,
		touchStartY = 0,
		touchStartX = 0,
		touchEndY = 0,
		touchEndX = 0;

	//extend default options
	options = extend( defaults,config_options );

	/**
	 * @exception {ConfigurationError} If conflicting scrolling options are set
	 */
	ConfigurationError.prototype = new Error();
	ConfigurationError.prototype.constructor = ConfigurationError;
	if (options.continuousVertical &&
		(options.loopTop || options.loopBottom)) {
	    options.continuousVertical = false;

		throw new ConfigurationError("Option loopTop/loopBottom is mutually exclusive with continuousVertical; continuousVertical disabled");
	}

	/** @throws Disable mutually exclusive settings */
	if (scrollDelay < 400) {
	    options.continuousVertical = false;

		throw new RangeError("BE CAREFUL! Not recommened to change it under 400 for a good behavior in laptops and Apple devices (laptops, mouses...)");
	}

	/** The current module configuration */
	this.config = function(){
		return options;
	};

	/** The current scroll delay setting */
	this.scrollDelay = function(){
		return scrollDelay;
	};

	/**
	 * set the scrolling behaviour
	 * @param {number} value
	 */
	this.setAutoScrolling = function(value){
		options.autoScrolling = value; //el = document.getElementById( options.idSelector ), //sections = el.getElementsByClassName( options.sectionClass );
		var element = document.getElementsByClassName('section active')[0],
			docElemHTML = document.getElementsByTagName("html")[0],
			docElemBody = document.getElementsByTagName("body")[0],
			elementPosition = getPosition(element);

		if(options.autoScrolling){
			docElemHTML.style.overflow = 'hidden';
			docElemHTML.style.height = '100%';
			docElemBody.style.overflow = 'hidden';
			docElemBody.style.height = '100%';

			if(element.length){
				//moving the container up
				silentScroll(elementPosition.top);
			}
		}
		else{
			docElemHTML.style.overflow = 'auto';
			docElemHTML.style.height = 'auto';
			docElemBody.style.overflow = 'auto';
			docElemBody.style.height = 'auto';

			silentScroll(0);

			//scrolling the page to the section with no animation
			docElemHTML.scrollTop = elementPosition.top;
			docElemBody.scrollTop = elementPosition.top;
		}
	};

	/**
	 * Defines the scrolling speed 
	 * @param {number} value - set the scrolling speed
	 */
	this.setScrollingSpeed = function(value){
	   options.scrollingSpeed = value;
	};

	/**
	 * Adds or remove the possiblity of scrolling through sections by using the keyboard arrow keys
	 * @param {number} value
	 */
	this.setKeyboardScrolling = function (value){
		options.keyboardScrolling = value;
	};

	/**
	 * Adds or remove the possiblity of scrolling through sections by using the mouse wheel or the trackpad. 
	 * @param {number} value
	 */
	this.setMouseWheelScrolling = function (value){
		console.log('setMouseWheelScrolling',value);
		if(value){
			addMouseWheelHandler();
		}else{
			removeMouseWheelHandler();
		}
	};

	/**
	 * Adds or remove the possiblity of scrolling through sections by using the mouse wheel/trackpad or touch gestures. 
	 * @param {number} value
	 */
	this.setAllowScrolling = function (value){
		if(value){
			this.setMouseWheelScrolling(true);
			addTouchHandler();
		}
		else{
			this.setMouseWheelScrolling(false);
			removeTouchHandler();
		}
	};

	/**
	 * Moves to previous section
	 */
	this.moveSectionUp = function(){
		// console.log("moveSectionUp");
		var prev = document.querySelector('.section.active').previousElementSibling;
		// console.log("prev",prev);
		if(prev && classie.hasClass(prev,'section')){
			//looping to the bottom if there's no more sections above
			if (!prev && (options.loopTop || options.continuousVertical)) {
				prev = document.getElementsByClassName('section')[document.getElementsByClassName('section').length - 1];
			}

			if (prev) {
				scrollPage(prev, null, true);
			}
		}
	};

	/**
	 * Moves to next section
	 */
	this.moveSectionDown = function (){
		var next = document.querySelector('.section.active').nextElementSibling;

		if(next && classie.hasClass(next,'section')){
			//looping to the top if there's no more sections below
			if(!next &&
				(options.loopBottom || options.continuousVertical)){
				next = document.getElementsByClassName('section')[0];//$('.section').first();
			}

			if(next){
				scrollPage(next, null, false);
			}
		}
	};

	/**
	 * Moves to specific section
	 * @param {number} section - number of section
	 * @param {number} slide - numder of slide
	 */
	this.moveTo = function (section, slide){
		var destiny = '';

		if(isNaN(section)){
			destiny = document.querySelector('[data-anchor="'+section+'"]');
		}else{
			destiny = document.getElementsByClassName('section')[ (section -1) ];
		}

		if (typeof slide !== 'undefined'){
			scrollPageAndSlide(section, slide);
		}else if(destiny){
			scrollPage(destiny);
		}
	};

	/**
	 * moves slide right
	 */
	this.moveSlideRight = function(){
		moveSlide('next');
	};
	/**
	 * moves slide left
	 */
	this.moveSlideLeft = function(){
		moveSlide('prev');
	};

	/**
	 * intialize a new linotype
	 */
	this.init = function(){
		var docElemBody = document.getElementsByTagName('body')[0];

		windowsHeight = document.documentElement.clientHeight;
		linotypeElement = document.getElementById(options.idSelector);
		container = linotypeElement;

		//activate touch events
		this.setAllowScrolling(true);

		if(options.css3){
			options.css3 = support3d();
		}

		if(linotypeElement){
			container.style.height='100%';
			container.style.position='relative';
			container.style['-ms-touch-action']='none';
		}
		else{
			var oldBodyHTML = document.getElementsByTagName('body')[0].innerHTML;
			document.getElementsByTagName('body')[0].innerHTML='<div id="superContainer">'+oldBodyHTML+'</div>';
			container = document.getElementById('superContainer');
		}

		//creating the navigation dots 
		if (options.navigation) {
			var navHTML = document.createElement('div');
				navHTML.innerHTML = "<ul></ul>";
				navHTML.setAttribute("id", "fullPage-nav");
			docElemBody.appendChild(navHTML);

			var nav = document.getElementById("fullPage-nav");
			nav.style.color = options.navigationColor;
			classie.addClass(nav,options.navigationPosition);
		}

		var sections = container.getElementsByClassName('section');
		for(var index = 0; index < sections.length; index++){
			var that = sections[index];
			var $this = sections[index];
			var slides = ($this !== 'undefined') ? $this.getElementsByClassName('slide') : 0;
			var numSlides = slides.length;

			//if no active section is defined, the 1st one will be the default one
			if(!index && document.getElementsByClassName('section active').length === 0) {
				classie.addClass($this,'active');
			}

			if($this.style !== undefined){
				$this.style.height = windowsHeight + 'px';

				if(options.paddingTop || options.paddingBottom){
					$this.style.padding = options.paddingTop  + ' 0 ' + options.paddingBottom + ' 0';
				}

				if (typeof options.slidesColor[index] !==  'undefined') {
					$this.style['background-color'] = options.slidesColor[index];
				}
			}

			if (typeof options.anchors[index] !== 'undefined' && typeof $this === 'object' ) {
				$this.setAttribute('data-anchor', options.anchors[index]);
			}

			if (options.navigation) {
				var link = (options.anchors.length)? options.anchors[index]:'',
					tooltip = (typeof options.navigationTooltips[index] !== 'undefined')? options.navigationTooltips[index] : '',
					navToSetHTML = document.getElementById("fullPage-nav"),
					navUL = navToSetHTML.getElementsByTagName('ul')[0],
					newLIToAdd = document.createElement('li');

				newLIToAdd.innerHTML='<a href="#' + link + '"><span></span></a></li>';
				if(tooltip){
					newLIToAdd.setAttribute('data-tooltip',tooltip);
				}
				navUL.appendChild(newLIToAdd);
			}

			// if there's any slide
			if (numSlides > 0) {
				var sliderWidth = numSlides * 100;
				var slideWidth = 100 / numSlides;
				var slidesContainerEl = document.createElement("div");
				var slidesContainerForControlsEl = document.createElement("div");
				slidesContainerEl.setAttribute('class','slidesContainer');
				slidesContainerForControlsEl.setAttribute('class','slides');

				elementContentWrapInner($this,slidesContainerEl);

				elementContentWrapInner($this,slidesContainerForControlsEl);

				$this.getElementsByClassName('slidesContainer')[0].style.width = sliderWidth + '%';
				$this.innerHTML += '<div class="controlArrow prev"></div><div class="controlArrow next"></div>';

				if(options.controlArrowColor!=='#fff'){
					$this.getElementsByClassName('controlArrow next')[0].style['border-color'] = 'transparent transparent transparent '+options.controlArrowColor;
					$this.getElementsByClassName('controlArrow prev')[0].style['border-color'] = 'transparent transparent transparent '+options.controlArrowColor;
				}

				if(!options.loopHorizontal){
					elementHideCss($this.getElementsByClassName('controlArrow prev')[0]);
				}

				if(options.slidesNavigation){
					addSlidesNavigation($this, numSlides);
				}

				for(var i = 0; i< numSlides;i++){
					//if the slide won#t be an starting point, the default will be the first one
					if(!i && $this.getElementsByClassName('slide active').length ===0){
						classie.addClass($this.getElementsByClassName('slide')[0],'active');
					}

					$this.getElementsByClassName('slide')[i].style.width=slideWidth + '%';

					if(options.verticalCentered){
						addTableClass($this.getElementsByClassName('slide')[i]);
					}
				}
			}
			else{
				if(options.verticalCentered){
					addTableClass($this);
				}
			}
		}

		this.setup();

	}.bind(this);

	/**
	 * finalizing the module for events
	 */
	this.setup = function(){
		this.setAutoScrolling(options.autoScrolling);
		var nav = document.getElementById("fullPage-nav");

		//the starting point is a slide? 
		var activeSlide = document.getElementsByClassName('section active')[0].getElementsByClassName(' slide active')[0];

		// console.log("activeSlide",activeSlide);
		if( typeof activeSlide !== 'undefined' && activeSlide.length  &&
			( nodelistToArray(document.getElementsByClassName('section'),true).indexOf(activeSlide.outerHTML)/* $('.section.active').index('.section')*/ !== 0 ||
			( nodelistToArray(document.getElementsByClassName('section'),true).indexOf(activeSlide.outerHTML) /* $('.section.active').index('.section')*/ === 0 )/*&& activeSlide.index() !== 0 */)
			){
			var prevScrollingSpeepd = options.scrollingSpeed;
			this.setScrollingSpeed(0);
			landscapeScroll(document.getElementsByClassName('section active').getElementsByClassName('slides') , activeSlide);
			this.setScrollingSpeed(prevScrollingSpeepd);
		}

		// //fixed elements need to be moved out of the plugin container due to problems with CSS3.
		if(options.fixedElements && options.css3){
			document.getElementsByTagName('body')[0].appendChild(document.querySelector(options.fixedElements));
		}

		//vertical centered of the navigation + first bullet active
		if(options.navigation){
			nav.style['margin-top'] = '-' + (nav.offsetHeight/2) + 'px';
			var activeSection = nodelistToArray(document.getElementsByClassName('section'),true).indexOf(document.getElementsByClassName('section active')[0].outerHTML);
			var navigationATag = nav.getElementsByTagName('li')[activeSection].getElementsByTagName('a')[0];
			classie.addClass(navigationATag,'active');
		}

		//moving the menu outside the main container (avoid problems with fixed positions when using CSS3 tranforms)
		if(options.menu && options.css3){
			document.getElementsByTagName('body')[0].appendChild(document.querySelector(options.menu));
		}

		if(options.scrollOverflow){
			//after DOM and images are loaded 
			// if (document.readyState === "complete") { init(); }
			onWindowLoaded(function(){
				var sections = document.getElementsByClassName('section');
				for(var x=0; x < sections.length; x++){
					var slides = sections[x].getElementsByClassName('slide');
					var $this = sections[x];

					if(slides.length){
						for(var y=0; y< slides.length; y++){
							// console.log(x,y,' slides');
							createSlimScrolling(slides[y]);
						}

					}
					else{
						// console.log(x,'no slides');
						createSlimScrolling($this);
					}
				}
				if(typeof options.afterRender === "function"){
					options.afterRender();
				}
			});
		}
		else{
			if(typeof options.afterRender === "function"){
				options.afterRender();
			}
		}


		//getting the anchor link in the URL and deleting the `#`
		var value =  window.location.hash.replace('#', '').split('/');
		var destiny = value[0];

		if(destiny.length){
			var section = document.querySelector('[data-anchor="'+destiny+'"]');

			if(!options.animateAnchor && section.length){
				silentScroll( getPosition(section).top);
				if(typeof options.afterLoad === "function"){
					options.afterLoad( destiny, (nodelistToArray(document.getElementsByClassName('section'),true).indexOf(section.outerHTML) + 1));
				}

				//updating the active class
				var sectionColllection = document.getElementsByClassName('section');
				for(var h =0; h <sectionColllection.length; h++){
					classie.removeClass(sectionColllection[h],'active');
				}
				classie.addClass(section,'active');
			}
		}

		onWindowLoaded(function() {
			scrollToAnchor();
		});

		this.setupEventHandlers();
	}.bind(this);

	/** 
	 * The current scroll delay setting
	 */
	this.setupEventHandlers = function(){
		window.addEventListener("hashchange", windowOnHashChangeEvent, false);
		window.addEventListener("scroll",windowScrollEvent, false);
		window.addEventListener("onorientationchange",browserOnOrientationChange, false);
		window.addEventListener("resize",windowResizeEvent, false);

		document.addEventListener("keydown",keydownEvent,false);
		var sections = document.querySelectorAll('.section');
		for(var t =0; t<sections.length;t++){
			sections[t].addEventListener("click",sectionClickEvent,false);
		}

		var navlinks = document.querySelectorAll('#fullPage-nav a');
		for(var x =0; x<navlinks.length;x++){
			navlinks[x].addEventListener("click",navigationClickEvent,false);
		}

		if(options.slidesNavigation){
			document.querySelector('.fullPage-slidesNav').addEventListener('click',slideNavClickEvent,false);
		}
	};

	/**
	 * Adds the possibility to auto scroll through sections on touch devices.
	 */
	function addTouchHandler(){
		document.removeEventListener('touchstart');
		document.removeEventListener('MSPointerDown');
		document.addEventListener('touchstart',touchStartHandler,false);
		document.addEventListener('MSPointerDown',touchStartHandler,false);
		document.removeEventListener('touchmove');
		document.removeEventListener('MSPointerMove');
		document.addEventListener('touchmove',touchMoveHandler,false);
		document.addEventListener('MSPointerMove',touchMoveHandler,false);
	}

	/**
	 * Removes the auto scrolling for touch devices.
	 */
	function removeTouchHandler(){
		document.removeEventListener('touchmove MSPointerMove');
	}

	var elementHideCss = domhelper.elementHideCss;
	var elementShowCss = domhelper.elementShowCss;
	var elementContentWrapInner = domhelper.elementContentWrapInner;
	var unwrapElement = domhelper.unwrapElement;
	var onWindowLoaded = domhelper.onWindowLoaded;
	var nodelistToArray = domhelper.nodelistToArray;
	var closetElement = domhelper.closetElement;
	var getParentElement = domhelper.getParentElement;
	var insertAllBefore = domhelper.insertAllBefore;
	var insertAllAfter = domhelper.insertAllAfter;
	var getNextElements = domhelper.getNextElements;
	var getPreviousElements = domhelper.getPreviousElements;
	var nodeIndexOfNodeList = domhelper.nodeIndexOfNodeList;
	var getScrollTop = domhelper.getScrollTop;
	var removeAllClassAndToggle = domhelper.removeAllClassAndToggle;
	var getElementSelector = domhelper.getElementSelector;
	var getPosition = domhelper.getPosition;

	function touchStartHandler(e){
		var touchEvents = getEventsPage(e);
		touchStartY = touchEvents.y;
		touchStartX = touchEvents.x;
	}

	/**
	 * Gets the pageX and pageY properties depending on the browser.
	 * https://github.com/alvarotrigo/fullPage.js/issues/194#issuecomment-34069854
	 */
	function getEventsPage(e){
		var events = [];
		if (window.navigator.msPointerEnabled){
			events.y = e.pageY;
			events.x = e.pageX;
		}else{
			events.y = e.touches[0].pageY;
			events.x = e.touches[0].pageX;
		}
		return events;
	}

	/* Detecting touch events 
	 * As we are changing the top property of the page on scrolling, we can not use the traditional way to detect it.
	 * This way, the touchstart and the touch moves shows an small difference between them which is the
	 * used one to determine the direction.
	*/

	var touchMoveHandler = function(e){
		// var e = event;

		if(options.autoScrolling){
			//preventing the easing on iOS devices 
			e.preventDefault();
		}

		// additional: if one of the normalScrollElements isn't within options.normalScrollElementTouchThreshold hops up the DOM chain
		if (!checkParentForNormalScrollElement(e.target)) {

			var touchMoved = false;
			var activeSection = document.querySelector('.section.active');
			var scrollable;

			if (!isMoving && !slideMoving) { //if theres any #
				var touchEvents = getEventsPage(e);
				touchEndY = touchEvents.y;
				touchEndX = touchEvents.x;

				//if movement in the X axys is greater than in the Y and the currect section has slides...
				if (activeSection.getElementsByClassName('slide').length && Math.abs(touchStartX - touchEndX) > (Math.abs(touchStartY - touchEndY))) {

				    //is the movement greater than the minimum resistance to scroll?
				    if (Math.abs(touchStartX - touchEndX) > (window.innerWidth / 100 * options.touchSensitivity)) {
				        if (touchStartX > touchEndX) {
				            this.moveSlideRight(); //next 
				        } else {
				            this.moveSlideLeft(); //prev
				        }
				    }
				}

				//vertical scrolling (only when autoScrolling is enabled)
				else if(options.autoScrolling){

					//if there are landscape slides, we check if the scrolling bar is in the current one or not
					if(activeSection.getElementsByClassName('slide').length){
						scrollable= activeSection.querySelector('.slide.active').querySelector('.scrollable');
					}else{
						scrollable = activeSection.querySelector('.scrollable');
					}

					//is the movement greater than the minimum resistance to scroll?
					if (Math.abs(touchStartY - touchEndY) > (window.innerHeight / 100 * options.touchSensitivity)) {
						if (touchStartY > touchEndY) {
							if(scrollable && scrollable.length > 0 ){
								//is the scrollbar at the end of the scroll?
								if(isScrolled('bottom', scrollable)){
									this.moveSectionDown();
								}else{
									return true;
								}
							}else{
								// moved down
								this.moveSectionDown();
							}
						} else if (touchEndY > touchStartY) {

							if(scrollable && scrollable.length > 0){
								//is the scrollbar at the start of the scroll?
								if(isScrolled('top', scrollable)){
									this.moveSectionUp();
								}
								else{
									return true;
								}
							}else{
								// moved up
								this.moveSectionUp();
							}
						}
					}
				}
			}
		}
	}.bind(this);

	/**
	 * recursive function to loop up the parent nodes to check if one of them exists in options.normalScrollElements
	 * Currently works well for iOS - Android might need some testing
	 * @param  {Element} el  target element / jquery selector (in subsequent nodes)
	 * @param  {int}     hop current hop compared to options.normalScrollElementTouchThreshold 
	 * @return {boolean} true if there is a match to options.normalScrollElements
	 * @todo need to fix parent.isEqualNode to loop through selector of normalscrollelements called from @linotype~touchMoveHandler
	 */
	function checkParentForNormalScrollElement (el, hop) {
		hop = hop || 0;
		var parent = el.parentNode;

		if (hop < options.normalScrollElementTouchThreshold &&
			parent.isEqualNode(document.querySelector(options.normalScrollElements)) ) {
			return true;
		} else if (hop === options.normalScrollElementTouchThreshold) {
			return false;
		} else {
			return checkParentForNormalScrollElement(parent, ++hop);
		}
	}

	/**
	 * Retuns `up` or `down` depending on the scrolling movement to reach its destination
	 * from the current section.
	 */
	function getYmovement(destiny){
		var fromIndex = nodeIndexOfNodeList(document.getElementsByClassName('section'),document.getElementsByClassName('section active')[0]); //$('.section.active').index('.section');
		var toIndex = nodeIndexOfNodeList(document.getElementsByClassName('section'),destiny);//destiny.index('.section');

		if(fromIndex > toIndex){
			return 'up';
		}
		return 'down';
	}

	/**
	 * Retuns `right` or `left` depending on the scrolling movement to reach its destination
	 * from the current slide.
	 */
	function getXmovement(fromIndex, toIndex){
		if( fromIndex === toIndex){
			return 'none';
		}
		if(fromIndex > toIndex){
			return 'left';
		}
		return 'right';
	}

	/**	
	 * Sliding with arrow keys, both, vertical and horizontal
	 */
	var keydownEvent = function(e){
		console.log("keydownEvent",isMoving);
		if (options.keyboardScrolling && !isMoving) {
			switch (e.which) {
				//up
				case 38:
				case 33:
					this.moveSectionUp();
					break;

				//down
				case 40:
				case 34:
					this.moveSectionDown();
					break;

				//left
				case 37:
					this.moveSlideLeft();
					break;

				//right
				case 39:
					this.moveSlideRight();
					break;

				default:
					return; // exit this handler for other keys
			}
		}
	}.bind(this);

	/**
	 * Removes the auto scrolling action fired by the mouse wheel and tackpad.
	 * After this function is called, the mousewheel and trackpad movements won't scroll through sections.
	 */
	function removeMouseWheelHandler(){
		if (document.addEventListener) {
			document.removeEventListener('mousewheel', MouseWheelHandler, false); //IE9, Chrome, Safari, Oper
			document.removeEventListener('wheel', MouseWheelHandler, false); //Firefox
		} else {
			document.detachEvent("onmousewheel", MouseWheelHandler); //IE 6/7/8
		}
	}


	/**
	 * Adds the auto scrolling action for the mouse wheel and tackpad.
	 * After this function is called, the mousewheel and trackpad movements will scroll through sections
	 */
	function addMouseWheelHandler(){
		if (document.addEventListener) {
			document.addEventListener("mousewheel", MouseWheelHandler, false); //IE9, Chrome, Safari, Oper
			document.addEventListener("wheel", MouseWheelHandler, false); //Firefox
		} else {
			document.attachEvent("onmousewheel", MouseWheelHandler); //IE 6/7/8
		}
	}

	/**
	 * Detecting mousewheel scrolling
	 * 
	 * http://blogs.sitepointstatic.com/examples/tech/mouse-wheel/index.html
	 * http://www.sitepoint.com/html5-javascript-mouse-wheel/
	 */
	var MouseWheelHandler = function (e){
		/* jshint debug:true */
		// debugger;
		if(options.autoScrolling){
			// cross-browser wheel delta
			e = window.event || e;
			var delta = Math.max(-1, Math.min(1,
					(e.wheelDelta || -e.deltaY || -e.detail)));
			var scrollable;
			var activeSection = document.querySelector('.section.active');

			if (!isMoving) { //if theres any #

				//if there are landscape slides, we check if the scrolling bar is in the current one or not
				if(activeSection.querySelectorAll('.slides').length){
					console.log("has slides");
					scrollable= activeSection.querySelector('.slide.active').querySelector('.scrollable');
				}else{
					console.log("on section");
					scrollable = activeSection.querySelector('.scrollable');
					// console.log("scrollable.length",(typeof scrollable.length));
				}

				//scrolling down?
				if (delta < 0) {
					if(scrollable){
						//is the scrollbar at the end of the scroll?
						if(isScrolled('bottom', scrollable)){
							this.moveSectionDown();
						}else{
							return true; //normal scroll
						}
					}else{
						this.moveSectionDown();
					}
				}

				//scrolling up?
				else {
					if(scrollable){
						//is the scrollbar at the start of the scroll?
						if(isScrolled('top', scrollable)){
							this.moveSectionUp();
						}else{
							return true; //normal scroll
						}
					}else{
						this.moveSectionUp();
					}
				}
			}

			return false;
		}
	}.bind(this);

	/** handle updating window hash location */
	function windowOnHashChangeEvent(e){
		if(!isScrolling){
			var value =  window.location.hash.replace('#', '').split('/');
			var section = value[0];
			var slide = value[1];

			//when moving to a slide in the first section for the first time (first time to add an anchor to the URL)
			var isFirstSlideMove =  (typeof lastScrolledDestiny === 'undefined');
			var isFirstScrollMove = (typeof lastScrolledDestiny === 'undefined' && typeof slide === 'undefined');

			/*in order to call scrollpage() only once for each destination at a time
			It is called twice for each scroll otherwise, as in case of using anchorlinks `hashChange` 
			event is fired on every scroll too.*/
			if ((section && section !== lastScrolledDestiny) && !isFirstSlideMove || isFirstScrollMove || (!slideMoving && lastScrolledSlide !== slide ))  {
				scrollPageAndSlide(section, slide);
			}
		}
	}

	//window scroll event
	function windowScrollEvent(e){
		// console.log("window scroll");
		var allSections = document.getElementsByClassName('section');
		if(!options.autoScrolling){
			var currentScroll = getScrollTop(window);

			var scrolledSections = [];

			nodelistToArray(document.querySelectorAll('.section')).map(function(mapIndex,index,arr){
				var $this = mapIndex;
				if ($this.offsetTop< (currentScroll + 100)){
					if($this){scrolledSections.push($this);}
				}
			});

			//geting the last one, the current one on the screen
			var currentSectionIndex = scrolledSections.length-1;
			var currentSection = scrolledSections[currentSectionIndex];
			// console.log("currentSection",currentSection);

			//executing only once the first time we reach the section
			if(!classie.hasClass(currentSection,'active')){
				var leavingSection =nodeIndexOfNodeList(document.getElementsByClassName('section'),document.querySelector('.section.active')) +1;

				isScrolling = true;

				var yMovement = getYmovement(currentSection);

				removeAllClassAndToggle(currentSection,allSections,'active');

				var anchorLink  = currentSection.getAttribute('data-anchor');
				if(typeof options.onLeave ==='function'){
					options.onLeave.call(leavingSection, (currentSectionIndex + 1), yMovement);
				}

				activateMenuElement(anchorLink);
				activateNavDots(anchorLink, 0);

				if(options.anchors.length && !isMoving){
					//needed to enter in hashChange event when using the menu with anchor links
					lastScrolledDestiny = anchorLink;

					location.hash = anchorLink;
				}

				//small timeout in order to avoid entering in hashChange event when scrolling is not finished yet
				clearTimeout(scrollId);
				scrollId = setTimeout(function(){
					isScrolling = false;
				}, 100);
			}

		}
	}

	//navigation action 
	function navigationClickEvent(e){
		e.preventDefault();
		var atarget = e.target.parentNode.parentNode;
		var allNavTargets = e.target.parentNode.parentNode.parentNode.children;
		var index = nodeIndexOfNodeList(allNavTargets,atarget);

		scrollPage(document.getElementsByClassName('section')[index]);
	}

	//slidenav click event
	function slideNavClickEvent(e){
		e.preventDefault();
		var etarget = e.target;

		if(etarget.tagName==='SPAN'){
			var slides = document.querySelector('.section.active').querySelectorAll('.slide'),
			destiny;
			var slideLI = etarget.parentNode.parentNode;
			var allSlidesLI = etarget.parentNode.parentNode.parentNode.children;
			classie.addClass(slideLI,'clickedTarget');
			destiny = slides[nodeIndexOfNodeList(allSlidesLI,slideLI)];
			classie.removeClass(slideLI,'clickedTarget');

			landscapeScroll(slides,destiny);
		}
	}

	//click events
	var sectionClickEvent = function(e){
		var eventTarget = e.target;

		if(classie.hasClass(eventTarget,'controlArrow')){
			if(classie.hasClass(eventTarget,'prev')){
				this.moveSlideLeft();
			}
			else{
				this.moveSlideRight();
			}
		}
		else if(classie.hasClass(eventTarget,'toSlide')){
			e.preventDefault();

			console.log("toSlide");
			// var slides = $(this).closest('.section').find('.slides');
			// var currentSlide = slides.find('.slide.active');
			// var destiny = null;

			// destiny = slides.find('.slide').eq( ($(this).data('index') -1) );

			// if(destiny.length > 0){
			// 	landscapeScroll(slides, destiny);
			// }
		}
	}.bind(this);

	//window resize event
	function windowResizeEvent(e){
		clearTimeout(resizeTimeout);
		resizeTimeout = setTimeout(doneResizing, 500);
	}

	//mobile orientation change
	function browserOnOrientationChange(e){
		console.log("browserOnOrientationChange");
		doneResizing();
	}

	/**
	 * When resizing is finished, we adjust the slides sizes and positions
	 */
	function doneResizing() {
		isResizing = true;

		var windowsWidth = window.innerWidth;
		windowsHeight = window.innerHeight;

		//text and images resizing
		if (options.resize) {
			resizeMe(windowsHeight, windowsWidth);
		}

		var allSections = document.getElementsByClassName('section');
		for(var x = 0; x <allSections.length; x++){
			var $this = allSections[x],
				scrollHeight = getScrollHeight($this),
				slides = $this.querySelectorAll('.slide');

			//adjusting the height of the table-cell for IE and Firefox
			if(options.verticalCentered){
				$this.querySelector('.tableCell').style.height = getTableHeight($this) + 'px';
			}

			$this.style.height = windowsHeight + 'px';

			//resizing the scrolling divs
			if(options.scrollOverflow){

				if(slides.length){
					for(var y=0; y< slides.length; y++){
						// console.log(x,y,' slides');
						createSlimScrolling(slides[y]);
					}
				}else{
					createSlimScrolling($this);
				}
			}

			//adjusting the position fo the FULL WIDTH slides...
			if (slides.length) {
				landscapeScroll(slides, $this.querySelector('.slide.active'));
			}
		}

		//adjusting the position for the current section
		var activeSection = document.querySelector('.section.active');
		var destinyPos = getPosition(activeSection);

		//isn't it the first section?
		if(nodeIndexOfNodeList(allSections,activeSection)){
			scrollPage(activeSection);
		}

		isResizing = false;
		if(typeof options.afterResize ==='function') {options.afterResize.call();}
	}

	/**
	 * Resizing of the font size depending on the window size as well as some of the images on the site.
	 */
	function resizeMe(displayHeight, displayWidth) {
		//Standard height, for which the body font size is correct
		var preferredHeight = 825;
		var windowSize = displayHeight;

		/* Problem to be solved

		if (displayHeight < 825) {
			var percentage = (windowSize * 100) / preferredHeight;
			var newFontSize = percentage.toFixed(2);

			$("img").each(function() {
				var newWidth = ((80 * percentage) / 100).toFixed(2);
				$(this).css("width", newWidth + '%');
			});
		} else {
			$("img").each(function() {
				$(this).css("width", '');
			});
		}*/

		if (displayHeight < 825 || displayWidth < 900) {
			// console.log("displayHeight",displayHeight);
			// console.log("displayWidth",displayWidth);
			if (displayWidth < 900) {
				windowSize = displayWidth;
				preferredHeight = 900;
			}
			var percentage = (windowSize * 100) / preferredHeight;
			var newFontSize = percentage.toFixed(2);
			// console.log("percentage",percentage);
			// console.log("newFontSize",newFontSize);

			document.getElementsByTagName("body")[0].style["font-size"] =  newFontSize + '%';
		} else {
			// console.log("displayHeight",displayHeight);
			// console.log("displayWidth",displayWidth);
			document.getElementsByTagName("body")[0].style["font-size"] = '100%';
		}
	}

	function scrollToAnchor(){
		//getting the anchor link in the URL and deleting the `#`
		var value =  window.location.hash.replace('#', '').split('/');
		var section = value[0];
		var slide = value[1];

		if(section){  //if theres any #				
			scrollPageAndSlide(section, slide);
		}
	}

	/**
	 * Scrolls to the given section and slide 
	 */
	function scrollPageAndSlide(destiny, slide){
		// console.log("scrollPageAndSlide");
		var section;
		if (typeof slide === 'undefined') {
		    slide = 0;
		}

		if(isNaN(destiny)){
			section = document.querySelector('[data-anchor="'+destiny+'"]');
		}
		else{
			section = document.getElementsByClassName('section')[(destiny -1)];
		}

		if(section){
			//we need to scroll to the section and then to the slide
			if (destiny !== lastScrolledDestiny && !classie.hasClass(section,'active')){
				scrollPage(section, function(){
					scrollSlider(section, slide);
				});
			}
			//if we were already in the section
			else{
				scrollSlider(section, slide);
			}
		}
	}

	/**
	 * Scrolls the slider to the given slide destination for the given section
	 */
	function scrollSlider(section, slide){
		if(typeof slide !== 'undefined' && slide !== 0){
			var slides = section.querySelectorAll('.slide');
			var destiny =  section.querySelector('.slides').querySelector('[data-anchor="'+slide+'"]');
			if(!destiny){
				destiny = section.querySelectorAll('.slide')[slide];
			}

			if(destiny){
				landscapeScroll(slides, destiny);
			}
		}
	}

	function moveSlide(direction){
	    var activeSection = document.querySelector('.section.active');
	    var slides = activeSection.getElementsByClassName('slide');

	    // more than one slide needed and nothing should be sliding
		if (!slides.length || slideMoving) {
		    return;
		}

	    var currentSlide = activeSection.querySelector('.slide.active');
	    var destiny = null;

	    if(direction === 'prev'){
	        destiny = currentSlide.previousElementSibling;
	    }else{
	        destiny = currentSlide.nextElementSibling;
	    }

	    //isn't there a next slide in the secuence?
		if(!destiny){
			//respect loopHorizontal settin
			if (!options.loopHorizontal) {return;}

		    if(direction === 'prev'){
		        destiny = currentSlide.parentNode.lastElementChild;// currentSlide.siblings(':last');
		    }else{
		        destiny = currentSlide.parentNode.firstElementChild;//currentSlide.siblings(':first');
		    }
		}

	    slideMoving = true;

	    landscapeScroll(slides, destiny);
	}

	function scrollPage(element, callback, isMovementUp){
		var scrollOptions = {}, scrolledElement,
			dest = getPosition(element);

		if(typeof dest === "undefined"){ return; } //there's no element to scroll, leaving the function
		/** @todo  TODO: why does dest.bottom === jquery.position().top */
		var dtop = dest.top,
			yMovement = getYmovement(element),
			anchorLink  = element.getAttribute('data-anchor'),
			sectionIndex = nodelistToArray(document.getElementsByClassName('section'),true).indexOf(element.outerHTML), ///element.index('.section');
			activeSlide = element.querySelector('.slide.active'),
			activeSection = document.querySelector('.section.active'),
			leavingSection = nodelistToArray(document.getElementsByClassName('section'),true).indexOf(activeSection.outerHTML) +1,//activeSection.index('.section') + 1;
			slideAnchorLink = null,
			slideIndex = null,
			localIsResizing = isResizing;//caching the value of isResizing at the momment the function is called 
			//because it will be checked later inside a setTimeout and the value might change



		if(activeSlide !== null){
			slideAnchorLink = activeSlide.getAttribute('data-anchor');
			slideIndex = nodelistToArray(element.getElementsByClassName('slide'),true).indexOf(activeSlide.outerHTML);//activeSlide.index();
		}
		// console.log("slideIndex",slideIndex);

		// If continuousVertical && we need to wrap around
		if (options.autoScrolling && options.continuousVertical && typeof (isMovementUp) !== "undefined" &&
			((!isMovementUp && yMovement === 'up') || // Intending to scroll down but about to go up or
			(isMovementUp && yMovement === 'down'))) { // intending to scroll up but about to go down

			// Scrolling down
			if (!isMovementUp) {
				// Move all previous sections to after the active section
				var muarray =[];
				insertAllAfter(activeSection,getPreviousElements(activeSection,muarray).reverse());
				// $(".section.active").after(activeSection.prevAll(".section").get().reverse());
			}
			else { // Scrolling up
				// Move all next sections to before the active section
				var mdarray =[];
				insertAllBefore(activeSection,getNextElements(activeSection,mdarray));
				// $(".section.active").before(activeSection.nextAll(".section"));
			}

			// Maintain the displayed position (now that we changed the element order)
			silentScroll(getPosition(activeSection).top);

			// save for later the elements that still need to be reordered
			var wrapAroundElements = activeSection;

			// Recalculate animation variables
			dest = getPosition(element);
			dtop = dest.top;
			yMovement = getYmovement(element);
		}

		var elementCol = document.getElementsByClassName('section');
		removeAllClassAndToggle(element,elementCol,'active');

		//preventing from activating the MouseWheelHandler event
		//more than once if the page is scrolling
		isMoving = true;

		if(typeof anchorLink !== 'undefined'){
			setURLHash(slideIndex, slideAnchorLink, anchorLink);
		}

		if(options.autoScrolling){
			scrollOptions.top = -dtop;
			scrolledElement = getElementSelector(container);
		}else{
			scrollOptions.scrollTop = dtop;
			scrolledElement = 'html, body';
		}

		// Fix section order after continuousVertical changes have been animated
		var continuousVerticalFixSectionOrder = function () {
			// If continuousVertical is in effect (and autoScrolling would also be in effect then), 
			// finish moving the elements around so the direct navigation will function more simply
			if (!wrapAroundElements || !wrapAroundElements.length) {
				return;
			}

			if (isMovementUp) {
				insertAllBefore(document.querySelector('.section').parentNode.firstElementChild,wrapAroundElements);
				// $('.section:first').before(wrapAroundElements);
			}
			else {
				insertAllAfter(document.querySelector('.section').parentNode.lastElementChild,wrapAroundElements);
				// $('.section:last').after(wrapAroundElements);
			}

			silentScroll(getPosition(activeSection).top);
		};

		// Use CSS3 translate functionality or...
		if (options.css3 && options.autoScrolling) {

			console.log("translate css3");

			//callback (onLeave) if the site is not just resizing and readjusting the slides
			if((typeof options.onLeave ==='function') && !localIsResizing ){
				options.onLeave.call(leavingSection, (sectionIndex + 1), yMovement);
			}

			classie.addClass( container, 'easing' );
			var translate3d = 'translate3d(0px, -' + dtop + 'px, 0px)';
			transformContainer(translate3d, true);

			var t = setTimeout(function () {
				//fix section order from continuousVertical
				continuousVerticalFixSectionOrder();
				//callback (afterLoad) if the site is not just resizing and readjusting the slides
				if((typeof options.afterLoad ==='function') && !localIsResizing) {
					options.afterLoad( anchorLink, (sectionIndex + 1));
				}

				setTimeout(function () {
					isMoving = false;
					if(typeof callback ==='function'){
						callback(this);
					}
				}, scrollDelay);
			}, options.scrollingSpeed);
		}
		else { // ... use jQuery animate
			console.log("no css3 sub jquery animate");
			//callback (onLeave) if the site is not just resizing and readjusting the slides
			if((typeof options.onLeave ==='function') && !localIsResizing){
				options.onLeave.call(leavingSection, (sectionIndex + 1), yMovement);
			}

			container.style.top = -dtop+'px';
			//fix section order from continuousVertical
			continuousVerticalFixSectionOrder();

			//callback (afterLoad) if the site is not just resizing and readjusting the slides
			if((typeof options.afterLoad ==='function') && !localIsResizing){
				options.afterLoad( anchorLink, (sectionIndex + 1));
			}

			setTimeout(function () {
				isMoving = false;
				if(typeof callback ==='function'){callback(this);}
			}, scrollDelay);
		}

		//flag to avoid callingn `scrollPage()` twice in case of using anchor links
		lastScrolledDestiny = anchorLink;

		//avoid firing it twice (as it does also on scroll)
		if(options.autoScrolling){
			activateMenuElement(anchorLink);
			activateNavDots(anchorLink, sectionIndex);
		}
	}

	/**
	 * Sets the URL hash for a section with slides
	 * @private
	 */
	function setURLHash(slideIndex, slideAnchor, anchorLink){
		var sectionHash = '';

		if(options.anchors.length){

			//isn't it the first slide?
			if(slideIndex){
				if(typeof anchorLink !== 'undefined'){
					sectionHash = anchorLink;
				}

				//slide without anchor link? We take the index instead.
				if(typeof slideAnchor === 'undefined'){
					slideAnchor = slideIndex;
				}

				lastScrolledSlide = slideAnchor;
				location.hash = sectionHash + '/' + slideAnchor;

			//first slide won't have slide anchor, just the section one
			}else if(typeof slideIndex !== 'undefined'){
				lastScrolledSlide = slideAnchor;
				location.hash = anchorLink;
			}

			//section without slides
			else{
				location.hash = anchorLink;
			}
		}
	}

	/**
	 * Activating the website navigation dots according to the given slide name.
	 */
	function activateNavDots(name, sectionIndex){
		if(options.navigation){
			var fullpageNavID = document.getElementById('fullPage-nav');
			classie.removeClass(fullpageNavID.querySelector('.active'),'active');
			if(name){
				classie.addClass(fullpageNavID.querySelector('a[href="#' + name + '"]'),'active');
			}
			else{
				classie.addClass(fullpageNavID.querySelector('li')[sectionIndex].querySelector('a'),'active');
			}
		}
	}

	/**
	 * Return a boolean depending on whether the scrollable element is at the end or at the start of the scrolling
	 * depending on the given type.
	 */
	function isScrolled(type, scrollable){
		if(type === 'top'){
			return !scrollable.scrollTop;
		}else if(type === 'bottom'){
			return scrollable.scrollTop + container.offsetHeight >= scrollable.scrollHeight;
		}
	}

	/**
	 * Activating the website main menu elements according to the given slide name.
	 */
	function activateMenuElement(name){
		if(options.menu){
			if(document.querySelector(options.menu).querySelector('.active')){
				classie.removeClass(document.querySelector(options.menu).querySelector('.active'),'active');
			}
			classie.addClass(document.querySelector(options.menu).querySelector('[data-menuanchor="'+name+'"]'),'active');
		}
	}

	/**
	 * createSlimScrolling
	 */
	function createSlimScrolling(element){
		var section, sectionPaddingTop=0, sectionPaddingBottom=0;
		//needed to make `scrollHeight` work under Opera 12
		element.style.overflow = 'hidden';
		//in case element is a slide
		if(element.className.match(/slide/gi)){
			var parentSectionOfSlide = getParentElement(element,'section');
			// console.log("slide section:",parentSectionOfSlide);
			section = parentSectionOfSlide;//closetElement(parentSectionOfSlide);
		}
		else{
			section = element;//closetElement(element);
		}
		var scrollable = element.getElementsByClassName('scrollable'),contentHeight;

		//if there was scroll, the contentHeight will be the one in the scrollable section
		if(scrollable.length){
			contentHeight = element.getElementsByClassName('scrollable')[0].scrollHeight;
		}
		else{
			if(options.verticalCentered){
				contentHeight = element.getElementsByClassName('tableCell')[0].scrollHeight;
			}
		}

		var scrollHeight = getScrollHeight(section);

		//needs scroll?
		if ( contentHeight > scrollHeight) {
			//was there already an scroll ? Updating it
			if(scrollable.length){
				scrollable[0].style.height= scrollHeight + 'px';
				scrollable[0].parentNode.style.height= scrollHeight + 'px';
			}
			//creating the scrolling
			else{
				var scrollableEl = document.createElement("div");
					scrollableEl.setAttribute('class','scrollable');

				if(options.verticalCentered){
					elementContentWrapInner(element.querySelector('.tableCell'),scrollableEl);
				}else{
					elementContentWrapInner(element,scrollableEl);
				}
				var ss = new Slimscroll({
					height: scrollHeight + 'px',
					size: '10px',
					alwaysVisible: true
				},element.getElementsByClassName('scrollable'));
				ss.init();
				// element.find('.scrollable').slimScroll({
				// 	height: scrollHeight + 'px',
				// 	size: '10px',
				// 	alwaysVisible: true
				// });
			}
		}
		//removing the scrolling when it is not necessary anymore
		else{
			if(element.getElementsByClassName('scrollable').children){
				var scrollablefc = element.getElementsByClassName('scrollable').children.firstChild;
				unwrapElement(scrollablefc);
				unwrapElement(scrollablefc);
				// element.find('.scrollable').children().first().unwrap().unwrap();
			}
			if(element.getElementsByClassName('slimScrollBar').parentNode){
				// element.find('.slimScrollBar').remove();
				var slimScrollBar = element.getElementsByClassName('slimScrollBar');
				slimScrollBar.parentNode.removeChild(slimScrollBar);
			}
			if(element.getElementsByClassName('slimScrollRail').parentNode){
				// element.find('.slimScrollRail').remove();
				var slimScrollRail = element.getElementsByClassName('slimScrollRail');
				slimScrollRail.parentNode.removeChild(slimScrollRail);
			}
		}
		//undo 
		element.style.overflow='';
	}

	function getScrollHeight(element){
		var windowsHeight = window.innerHeight,
			sectionPaddingBottom = (element.style['padding-bottom'])? parseInt(element.style['padding-bottom'],10) :0,
			sectionPaddingTop = (element.style['padding-top'])? parseInt(element.style['padding-top'],10) :0;
		return (windowsHeight - sectionPaddingBottom - sectionPaddingTop);
	}

	/**
	 * Scrolls horizontal sliders.
	 */
	function landscapeScroll(slides, destiny){
		// console.log("landscapeScroll");
		var destinyPos = getPosition(destiny),
			slidesContainer = destiny.parentNode,
			slideIndex = nodeIndexOfNodeList(slides,destiny),
			section = getParentElement(destiny,'section'),
			sectionIndex = nodeIndexOfNodeList(document.getElementsByClassName('section'),section),
			anchorLink = section.getAttribute('data-anchor'),
			slidesNav = section.querySelector('.fullPage-slidesNav'),
			slideAnchor = destiny.getAttribute('data-anchor');

		//caching the value of isResizing at the momment the function is called 
		//because it will be checked later inside a setTimeout and the value might change
		var localIsResizing = isResizing;

		if(options.onSlideLeave){
			var prevSlideIndex = nodeIndexOfNodeList(section.querySelectorAll('.slide'),section.querySelector('.slide.active'));
			var xMovement = getXmovement(prevSlideIndex, slideIndex);

			//if the site is not just resizing and readjusting the slides
			if(!localIsResizing){
				if(typeof options.onSlideLeave ==='function' ){
					options.onSlideLeave.call( anchorLink, (sectionIndex + 1), prevSlideIndex, xMovement);
				}
			}
		}

		var elementCol = section.getElementsByClassName('slide');
		removeAllClassAndToggle(destiny,elementCol,'active');

		if(typeof slideAnchor === 'undefined' ||  slideAnchor === null){
			slideAnchor = slideIndex;
		}

		//only changing the URL if the slides are in the current section (not for resize re-adjusting)
		if(classie.hasClass(section,'active')){

			if(!options.loopHorizontal){
				var prevarrow =section.querySelector('.controlArrow.prev'),
					nextarrow = section.querySelector('.controlArrow.next');
				//hidding it for the fist slide, showing for the rest
				if(slideIndex!==0){
					elementShowCss(prevarrow);
				}
				else{
					elementHideCss(prevarrow);
				}

				//hidding it for the last slide, showing for the rest
				if(nodeIndexOfNodeList(elementCol,destiny) === elementCol.length-1){
					elementHideCss(nextarrow);
				}
				else{
					elementShowCss(nextarrow);
				}
			}

			setURLHash(slideIndex, slideAnchor, anchorLink);

		}

		if(options.css3){
			var translate3d = 'translate3d(-' + destinyPos.left + 'px, 0px, 0px)';

			// slides.find('.slidesContainer').toggleClass('easing', options.scrollingSpeed>0).css(getTransforms(translate3d));

			classie.addClass( slidesContainer, 'easing' );
			transformElement( translate3d, slidesContainer);

			setTimeout(function(){
				//if the site is not just resizing and readjusting the slides
				if(!localIsResizing){
					if(typeof options.afterSlideLoad ==='function'){ options.afterSlideLoad.call( this, anchorLink, (sectionIndex + 1), slideAnchor, slideIndex );
					}
				}

				slideMoving = false;
			}, options.scrollingSpeed, options.easing);
		}else{
			console.log("no css3 sub jquery animate");

			slidesContainer.style.scrollLeft = destinyPos.left;

			//if the site is not just resizing and readjusting the slides
			if(!localIsResizing){
				if(typeof options.afterSlideLoad ==='function' ){ options.afterSlideLoad.call( anchorLink, (sectionIndex + 1), slideAnchor, slideIndex);}
			}
			//letting them slide again
			slideMoving = false;
		}

		if(options.slidesNavigation){
			classie.removeClass(slidesNav.querySelector('.active'),'active');
			classie.addClass(slidesNav.querySelectorAll('li')[slideIndex].querySelector('a'),'active');
		}
	}

	/**
	 * Creates a landscape navigation bar with dots for horizontal sliders.
	 @private
	 @param {object} section - nodeelement to add navigation to
	 @param {number} numSlides - number of slides 
	 */
	function addSlidesNavigation(section, numSlides){
		section.innerHTML+= '<div class="fullPage-slidesNav"><ul></ul></div>';
		var nav = section.getElementsByClassName('fullPage-slidesNav')[0];

		//top or bottom
		classie.addClass(nav,options.slidesNavPosition);

		for(var i=0; i< numSlides; i++){
			nav.getElementsByTagName('ul')[0].innerHTML+='<li><a href="#"><span></span></a></li>';
		}

		//centering it
		nav.style['margin-left'] =  '-' + (nav.offsetWidth/2) + 'px';
		classie.addClass(nav.getElementsByTagName('li')[0].getElementsByTagName('a')[0],'active');
	}

	/**
	 * adds table style to section
	 * @private
	 * @param { string } element - document element
	 */
	function addTableClass(element){
		classie.addClass(element,'table');
		var slidesTableContainerEl = document.createElement("div");
		slidesTableContainerEl.setAttribute('class','tableCell');
		slidesTableContainerEl.setAttribute('style',"height:" + getTableHeight(element) + "px;");

		elementContentWrapInner(element,slidesTableContainerEl);
	}

	/**
	 * get height for table
	 * @private
	 * @param { string } element - document element
	 */
	function getTableHeight(element){
		var sectionHeight = windowsHeight;

		if(options.paddingTop || options.paddingBottom){
			var section = element;
			if(!classie.hasClass(section,'section')){
				section = element.closest('.section');
			}

			var sectionPaddingBottom = (element.style['padding-bottom'])? parseInt(element.style['padding-bottom'],10) :0,
			sectionPaddingTop = (element.style['padding-top'])? parseInt(element.style['padding-top'],10) :0;
			sectionHeight = (windowsHeight - sectionPaddingBottom - sectionPaddingTop);
		}

		return sectionHeight;
	}

	/**
	 * Checks for translate3d support 
	 * @return boolean
	 * http://stackoverflow.com/questions/5661671/detecting-transform-translate3d-support
	 */
	function support3d() {
		var el = document.createElement('p'),
			has3d,
			transforms = {
				'webkitTransform':'-webkit-transform',
				'OTransform':'-o-transform',
				'msTransform':'-ms-transform',
				'MozTransform':'-moz-transform',
				'transform':'transform'
			};

		// Add it to the body to get the computed style.
		document.body.insertBefore(el, null);

		for (var t in transforms) {
			if (el.style[t] !== undefined) {
				el.style[t] = "translate3d(1px,1px,1px)";
				has3d = window.getComputedStyle(el).getPropertyValue(transforms[t]);
			}
		}

		document.body.removeChild(el);

		return (has3d !== undefined && has3d.length > 0 && has3d !== "none");
	}


	/**
	 * return browser preview for transforms.
	 * @private
	 * @param { string } translate3d - css transform propert
	 */
	function getTransforms(translate3d){
		return {
			'-webkit-transform': translate3d,
			'-moz-transform': translate3d,
			'-ms-transform':translate3d,
			'transform': translate3d
		};
	}

	/**
	 * Adds a css3 transform property to the container class with or without animation depending on the animated param.
	 * @private
	 */
	function transformContainer(translate3d, animated){
		// container.toggleClass('easing', animated);
		var transformsObject = getTransforms(translate3d);

		// container.css(getTransforms(translate3d));
		for(var x in transformsObject){
			container.style[x] = transformsObject[x];
		}
	}

	function transformElement(translate3d,element){
		// container.toggleClass('easing', animated);
		var transformsObject = getTransforms(translate3d);

		// container.css(getTransforms(translate3d));
		for(var x in transformsObject){
			element.style[x] = transformsObject[x];
		}
	}

	/**
	 * set the remove scrolling effect
	 * @private
	 * @param {number} top
	 */
	function silentScroll(top){
		console.log("silentScroll");

		if (options.css3) {
			var translate3d = 'translate3d(0px, -' + top + 'px, 0px)';
			transformContainer(translate3d, false);
		}
		else {
			// container.css("top", -top);
			container.style.top = -top+'px';
		}
	}
};

module.exports = linotype;


// If there is a window object, that at least has a document property,
// define linotype
if ( typeof window === "object" && typeof window.document === "object" ) {
	window.linotype = linotype;
}
},{"./domhelper":3,"./slimscroll":5,"classie":11,"events":6,"util":10,"util-extend":13}],5:[function(require,module,exports){
/* jshint debug:true */
/*
 * linotype
 * https://github.com/typesettin/linotype
 * @author yaw joseph etse
 * Copyright (c) 2014 Typesettin. All rights reserved.
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
					console.log("add scrollContent");
					// scrollContent(offset, false, true);
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
		console.log("parentWrapper",parentWrapper);
		console.log("me",me);
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
},{"./domhelper":3,"classie":11,"util-extend":13}],6:[function(require,module,exports){
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

function EventEmitter() {
  this._events = this._events || {};
  this._maxListeners = this._maxListeners || undefined;
}
module.exports = EventEmitter;

// Backwards-compat with node 0.10.x
EventEmitter.EventEmitter = EventEmitter;

EventEmitter.prototype._events = undefined;
EventEmitter.prototype._maxListeners = undefined;

// By default EventEmitters will print a warning if more than 10 listeners are
// added to it. This is a useful default which helps finding memory leaks.
EventEmitter.defaultMaxListeners = 10;

// Obviously not all Emitters should be limited to 10. This function allows
// that to be increased. Set to zero for unlimited.
EventEmitter.prototype.setMaxListeners = function(n) {
  if (!isNumber(n) || n < 0 || isNaN(n))
    throw TypeError('n must be a positive number');
  this._maxListeners = n;
  return this;
};

EventEmitter.prototype.emit = function(type) {
  var er, handler, len, args, i, listeners;

  if (!this._events)
    this._events = {};

  // If there is no 'error' event listener then throw.
  if (type === 'error') {
    if (!this._events.error ||
        (isObject(this._events.error) && !this._events.error.length)) {
      er = arguments[1];
      if (er instanceof Error) {
        throw er; // Unhandled 'error' event
      } else {
        throw TypeError('Uncaught, unspecified "error" event.');
      }
      return false;
    }
  }

  handler = this._events[type];

  if (isUndefined(handler))
    return false;

  if (isFunction(handler)) {
    switch (arguments.length) {
      // fast cases
      case 1:
        handler.call(this);
        break;
      case 2:
        handler.call(this, arguments[1]);
        break;
      case 3:
        handler.call(this, arguments[1], arguments[2]);
        break;
      // slower
      default:
        len = arguments.length;
        args = new Array(len - 1);
        for (i = 1; i < len; i++)
          args[i - 1] = arguments[i];
        handler.apply(this, args);
    }
  } else if (isObject(handler)) {
    len = arguments.length;
    args = new Array(len - 1);
    for (i = 1; i < len; i++)
      args[i - 1] = arguments[i];

    listeners = handler.slice();
    len = listeners.length;
    for (i = 0; i < len; i++)
      listeners[i].apply(this, args);
  }

  return true;
};

EventEmitter.prototype.addListener = function(type, listener) {
  var m;

  if (!isFunction(listener))
    throw TypeError('listener must be a function');

  if (!this._events)
    this._events = {};

  // To avoid recursion in the case that type === "newListener"! Before
  // adding it to the listeners, first emit "newListener".
  if (this._events.newListener)
    this.emit('newListener', type,
              isFunction(listener.listener) ?
              listener.listener : listener);

  if (!this._events[type])
    // Optimize the case of one listener. Don't need the extra array object.
    this._events[type] = listener;
  else if (isObject(this._events[type]))
    // If we've already got an array, just append.
    this._events[type].push(listener);
  else
    // Adding the second element, need to change to array.
    this._events[type] = [this._events[type], listener];

  // Check for listener leak
  if (isObject(this._events[type]) && !this._events[type].warned) {
    var m;
    if (!isUndefined(this._maxListeners)) {
      m = this._maxListeners;
    } else {
      m = EventEmitter.defaultMaxListeners;
    }

    if (m && m > 0 && this._events[type].length > m) {
      this._events[type].warned = true;
      console.error('(node) warning: possible EventEmitter memory ' +
                    'leak detected. %d listeners added. ' +
                    'Use emitter.setMaxListeners() to increase limit.',
                    this._events[type].length);
      console.trace();
    }
  }

  return this;
};

EventEmitter.prototype.on = EventEmitter.prototype.addListener;

EventEmitter.prototype.once = function(type, listener) {
  if (!isFunction(listener))
    throw TypeError('listener must be a function');

  var fired = false;

  function g() {
    this.removeListener(type, g);

    if (!fired) {
      fired = true;
      listener.apply(this, arguments);
    }
  }

  g.listener = listener;
  this.on(type, g);

  return this;
};

// emits a 'removeListener' event iff the listener was removed
EventEmitter.prototype.removeListener = function(type, listener) {
  var list, position, length, i;

  if (!isFunction(listener))
    throw TypeError('listener must be a function');

  if (!this._events || !this._events[type])
    return this;

  list = this._events[type];
  length = list.length;
  position = -1;

  if (list === listener ||
      (isFunction(list.listener) && list.listener === listener)) {
    delete this._events[type];
    if (this._events.removeListener)
      this.emit('removeListener', type, listener);

  } else if (isObject(list)) {
    for (i = length; i-- > 0;) {
      if (list[i] === listener ||
          (list[i].listener && list[i].listener === listener)) {
        position = i;
        break;
      }
    }

    if (position < 0)
      return this;

    if (list.length === 1) {
      list.length = 0;
      delete this._events[type];
    } else {
      list.splice(position, 1);
    }

    if (this._events.removeListener)
      this.emit('removeListener', type, listener);
  }

  return this;
};

EventEmitter.prototype.removeAllListeners = function(type) {
  var key, listeners;

  if (!this._events)
    return this;

  // not listening for removeListener, no need to emit
  if (!this._events.removeListener) {
    if (arguments.length === 0)
      this._events = {};
    else if (this._events[type])
      delete this._events[type];
    return this;
  }

  // emit removeListener for all listeners on all events
  if (arguments.length === 0) {
    for (key in this._events) {
      if (key === 'removeListener') continue;
      this.removeAllListeners(key);
    }
    this.removeAllListeners('removeListener');
    this._events = {};
    return this;
  }

  listeners = this._events[type];

  if (isFunction(listeners)) {
    this.removeListener(type, listeners);
  } else {
    // LIFO order
    while (listeners.length)
      this.removeListener(type, listeners[listeners.length - 1]);
  }
  delete this._events[type];

  return this;
};

EventEmitter.prototype.listeners = function(type) {
  var ret;
  if (!this._events || !this._events[type])
    ret = [];
  else if (isFunction(this._events[type]))
    ret = [this._events[type]];
  else
    ret = this._events[type].slice();
  return ret;
};

EventEmitter.listenerCount = function(emitter, type) {
  var ret;
  if (!emitter._events || !emitter._events[type])
    ret = 0;
  else if (isFunction(emitter._events[type]))
    ret = 1;
  else
    ret = emitter._events[type].length;
  return ret;
};

function isFunction(arg) {
  return typeof arg === 'function';
}

function isNumber(arg) {
  return typeof arg === 'number';
}

function isObject(arg) {
  return typeof arg === 'object' && arg !== null;
}

function isUndefined(arg) {
  return arg === void 0;
}

},{}],7:[function(require,module,exports){
if (typeof Object.create === 'function') {
  // implementation from standard node.js 'util' module
  module.exports = function inherits(ctor, superCtor) {
    ctor.super_ = superCtor
    ctor.prototype = Object.create(superCtor.prototype, {
      constructor: {
        value: ctor,
        enumerable: false,
        writable: true,
        configurable: true
      }
    });
  };
} else {
  // old school shim for old browsers
  module.exports = function inherits(ctor, superCtor) {
    ctor.super_ = superCtor
    var TempCtor = function () {}
    TempCtor.prototype = superCtor.prototype
    ctor.prototype = new TempCtor()
    ctor.prototype.constructor = ctor
  }
}

},{}],8:[function(require,module,exports){
// shim for using process in browser

var process = module.exports = {};

process.nextTick = (function () {
    var canSetImmediate = typeof window !== 'undefined'
    && window.setImmediate;
    var canPost = typeof window !== 'undefined'
    && window.postMessage && window.addEventListener
    ;

    if (canSetImmediate) {
        return function (f) { return window.setImmediate(f) };
    }

    if (canPost) {
        var queue = [];
        window.addEventListener('message', function (ev) {
            var source = ev.source;
            if ((source === window || source === null) && ev.data === 'process-tick') {
                ev.stopPropagation();
                if (queue.length > 0) {
                    var fn = queue.shift();
                    fn();
                }
            }
        }, true);

        return function nextTick(fn) {
            queue.push(fn);
            window.postMessage('process-tick', '*');
        };
    }

    return function nextTick(fn) {
        setTimeout(fn, 0);
    };
})();

process.title = 'browser';
process.browser = true;
process.env = {};
process.argv = [];

function noop() {}

process.on = noop;
process.addListener = noop;
process.once = noop;
process.off = noop;
process.removeListener = noop;
process.removeAllListeners = noop;
process.emit = noop;

process.binding = function (name) {
    throw new Error('process.binding is not supported');
}

// TODO(shtylman)
process.cwd = function () { return '/' };
process.chdir = function (dir) {
    throw new Error('process.chdir is not supported');
};

},{}],9:[function(require,module,exports){
module.exports = function isBuffer(arg) {
  return arg && typeof arg === 'object'
    && typeof arg.copy === 'function'
    && typeof arg.fill === 'function'
    && typeof arg.readUInt8 === 'function';
}
},{}],10:[function(require,module,exports){
(function (process,global){
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

var formatRegExp = /%[sdj%]/g;
exports.format = function(f) {
  if (!isString(f)) {
    var objects = [];
    for (var i = 0; i < arguments.length; i++) {
      objects.push(inspect(arguments[i]));
    }
    return objects.join(' ');
  }

  var i = 1;
  var args = arguments;
  var len = args.length;
  var str = String(f).replace(formatRegExp, function(x) {
    if (x === '%%') return '%';
    if (i >= len) return x;
    switch (x) {
      case '%s': return String(args[i++]);
      case '%d': return Number(args[i++]);
      case '%j':
        try {
          return JSON.stringify(args[i++]);
        } catch (_) {
          return '[Circular]';
        }
      default:
        return x;
    }
  });
  for (var x = args[i]; i < len; x = args[++i]) {
    if (isNull(x) || !isObject(x)) {
      str += ' ' + x;
    } else {
      str += ' ' + inspect(x);
    }
  }
  return str;
};


// Mark that a method should not be used.
// Returns a modified function which warns once by default.
// If --no-deprecation is set, then it is a no-op.
exports.deprecate = function(fn, msg) {
  // Allow for deprecating things in the process of starting up.
  if (isUndefined(global.process)) {
    return function() {
      return exports.deprecate(fn, msg).apply(this, arguments);
    };
  }

  if (process.noDeprecation === true) {
    return fn;
  }

  var warned = false;
  function deprecated() {
    if (!warned) {
      if (process.throwDeprecation) {
        throw new Error(msg);
      } else if (process.traceDeprecation) {
        console.trace(msg);
      } else {
        console.error(msg);
      }
      warned = true;
    }
    return fn.apply(this, arguments);
  }

  return deprecated;
};


var debugs = {};
var debugEnviron;
exports.debuglog = function(set) {
  if (isUndefined(debugEnviron))
    debugEnviron = process.env.NODE_DEBUG || '';
  set = set.toUpperCase();
  if (!debugs[set]) {
    if (new RegExp('\\b' + set + '\\b', 'i').test(debugEnviron)) {
      var pid = process.pid;
      debugs[set] = function() {
        var msg = exports.format.apply(exports, arguments);
        console.error('%s %d: %s', set, pid, msg);
      };
    } else {
      debugs[set] = function() {};
    }
  }
  return debugs[set];
};


/**
 * Echos the value of a value. Trys to print the value out
 * in the best way possible given the different types.
 *
 * @param {Object} obj The object to print out.
 * @param {Object} opts Optional options object that alters the output.
 */
/* legacy: obj, showHidden, depth, colors*/
function inspect(obj, opts) {
  // default options
  var ctx = {
    seen: [],
    stylize: stylizeNoColor
  };
  // legacy...
  if (arguments.length >= 3) ctx.depth = arguments[2];
  if (arguments.length >= 4) ctx.colors = arguments[3];
  if (isBoolean(opts)) {
    // legacy...
    ctx.showHidden = opts;
  } else if (opts) {
    // got an "options" object
    exports._extend(ctx, opts);
  }
  // set default options
  if (isUndefined(ctx.showHidden)) ctx.showHidden = false;
  if (isUndefined(ctx.depth)) ctx.depth = 2;
  if (isUndefined(ctx.colors)) ctx.colors = false;
  if (isUndefined(ctx.customInspect)) ctx.customInspect = true;
  if (ctx.colors) ctx.stylize = stylizeWithColor;
  return formatValue(ctx, obj, ctx.depth);
}
exports.inspect = inspect;


// http://en.wikipedia.org/wiki/ANSI_escape_code#graphics
inspect.colors = {
  'bold' : [1, 22],
  'italic' : [3, 23],
  'underline' : [4, 24],
  'inverse' : [7, 27],
  'white' : [37, 39],
  'grey' : [90, 39],
  'black' : [30, 39],
  'blue' : [34, 39],
  'cyan' : [36, 39],
  'green' : [32, 39],
  'magenta' : [35, 39],
  'red' : [31, 39],
  'yellow' : [33, 39]
};

// Don't use 'blue' not visible on cmd.exe
inspect.styles = {
  'special': 'cyan',
  'number': 'yellow',
  'boolean': 'yellow',
  'undefined': 'grey',
  'null': 'bold',
  'string': 'green',
  'date': 'magenta',
  // "name": intentionally not styling
  'regexp': 'red'
};


function stylizeWithColor(str, styleType) {
  var style = inspect.styles[styleType];

  if (style) {
    return '\u001b[' + inspect.colors[style][0] + 'm' + str +
           '\u001b[' + inspect.colors[style][1] + 'm';
  } else {
    return str;
  }
}


function stylizeNoColor(str, styleType) {
  return str;
}


function arrayToHash(array) {
  var hash = {};

  array.forEach(function(val, idx) {
    hash[val] = true;
  });

  return hash;
}


function formatValue(ctx, value, recurseTimes) {
  // Provide a hook for user-specified inspect functions.
  // Check that value is an object with an inspect function on it
  if (ctx.customInspect &&
      value &&
      isFunction(value.inspect) &&
      // Filter out the util module, it's inspect function is special
      value.inspect !== exports.inspect &&
      // Also filter out any prototype objects using the circular check.
      !(value.constructor && value.constructor.prototype === value)) {
    var ret = value.inspect(recurseTimes, ctx);
    if (!isString(ret)) {
      ret = formatValue(ctx, ret, recurseTimes);
    }
    return ret;
  }

  // Primitive types cannot have properties
  var primitive = formatPrimitive(ctx, value);
  if (primitive) {
    return primitive;
  }

  // Look up the keys of the object.
  var keys = Object.keys(value);
  var visibleKeys = arrayToHash(keys);

  if (ctx.showHidden) {
    keys = Object.getOwnPropertyNames(value);
  }

  // IE doesn't make error fields non-enumerable
  // http://msdn.microsoft.com/en-us/library/ie/dww52sbt(v=vs.94).aspx
  if (isError(value)
      && (keys.indexOf('message') >= 0 || keys.indexOf('description') >= 0)) {
    return formatError(value);
  }

  // Some type of object without properties can be shortcutted.
  if (keys.length === 0) {
    if (isFunction(value)) {
      var name = value.name ? ': ' + value.name : '';
      return ctx.stylize('[Function' + name + ']', 'special');
    }
    if (isRegExp(value)) {
      return ctx.stylize(RegExp.prototype.toString.call(value), 'regexp');
    }
    if (isDate(value)) {
      return ctx.stylize(Date.prototype.toString.call(value), 'date');
    }
    if (isError(value)) {
      return formatError(value);
    }
  }

  var base = '', array = false, braces = ['{', '}'];

  // Make Array say that they are Array
  if (isArray(value)) {
    array = true;
    braces = ['[', ']'];
  }

  // Make functions say that they are functions
  if (isFunction(value)) {
    var n = value.name ? ': ' + value.name : '';
    base = ' [Function' + n + ']';
  }

  // Make RegExps say that they are RegExps
  if (isRegExp(value)) {
    base = ' ' + RegExp.prototype.toString.call(value);
  }

  // Make dates with properties first say the date
  if (isDate(value)) {
    base = ' ' + Date.prototype.toUTCString.call(value);
  }

  // Make error with message first say the error
  if (isError(value)) {
    base = ' ' + formatError(value);
  }

  if (keys.length === 0 && (!array || value.length == 0)) {
    return braces[0] + base + braces[1];
  }

  if (recurseTimes < 0) {
    if (isRegExp(value)) {
      return ctx.stylize(RegExp.prototype.toString.call(value), 'regexp');
    } else {
      return ctx.stylize('[Object]', 'special');
    }
  }

  ctx.seen.push(value);

  var output;
  if (array) {
    output = formatArray(ctx, value, recurseTimes, visibleKeys, keys);
  } else {
    output = keys.map(function(key) {
      return formatProperty(ctx, value, recurseTimes, visibleKeys, key, array);
    });
  }

  ctx.seen.pop();

  return reduceToSingleString(output, base, braces);
}


function formatPrimitive(ctx, value) {
  if (isUndefined(value))
    return ctx.stylize('undefined', 'undefined');
  if (isString(value)) {
    var simple = '\'' + JSON.stringify(value).replace(/^"|"$/g, '')
                                             .replace(/'/g, "\\'")
                                             .replace(/\\"/g, '"') + '\'';
    return ctx.stylize(simple, 'string');
  }
  if (isNumber(value))
    return ctx.stylize('' + value, 'number');
  if (isBoolean(value))
    return ctx.stylize('' + value, 'boolean');
  // For some reason typeof null is "object", so special case here.
  if (isNull(value))
    return ctx.stylize('null', 'null');
}


function formatError(value) {
  return '[' + Error.prototype.toString.call(value) + ']';
}


function formatArray(ctx, value, recurseTimes, visibleKeys, keys) {
  var output = [];
  for (var i = 0, l = value.length; i < l; ++i) {
    if (hasOwnProperty(value, String(i))) {
      output.push(formatProperty(ctx, value, recurseTimes, visibleKeys,
          String(i), true));
    } else {
      output.push('');
    }
  }
  keys.forEach(function(key) {
    if (!key.match(/^\d+$/)) {
      output.push(formatProperty(ctx, value, recurseTimes, visibleKeys,
          key, true));
    }
  });
  return output;
}


function formatProperty(ctx, value, recurseTimes, visibleKeys, key, array) {
  var name, str, desc;
  desc = Object.getOwnPropertyDescriptor(value, key) || { value: value[key] };
  if (desc.get) {
    if (desc.set) {
      str = ctx.stylize('[Getter/Setter]', 'special');
    } else {
      str = ctx.stylize('[Getter]', 'special');
    }
  } else {
    if (desc.set) {
      str = ctx.stylize('[Setter]', 'special');
    }
  }
  if (!hasOwnProperty(visibleKeys, key)) {
    name = '[' + key + ']';
  }
  if (!str) {
    if (ctx.seen.indexOf(desc.value) < 0) {
      if (isNull(recurseTimes)) {
        str = formatValue(ctx, desc.value, null);
      } else {
        str = formatValue(ctx, desc.value, recurseTimes - 1);
      }
      if (str.indexOf('\n') > -1) {
        if (array) {
          str = str.split('\n').map(function(line) {
            return '  ' + line;
          }).join('\n').substr(2);
        } else {
          str = '\n' + str.split('\n').map(function(line) {
            return '   ' + line;
          }).join('\n');
        }
      }
    } else {
      str = ctx.stylize('[Circular]', 'special');
    }
  }
  if (isUndefined(name)) {
    if (array && key.match(/^\d+$/)) {
      return str;
    }
    name = JSON.stringify('' + key);
    if (name.match(/^"([a-zA-Z_][a-zA-Z_0-9]*)"$/)) {
      name = name.substr(1, name.length - 2);
      name = ctx.stylize(name, 'name');
    } else {
      name = name.replace(/'/g, "\\'")
                 .replace(/\\"/g, '"')
                 .replace(/(^"|"$)/g, "'");
      name = ctx.stylize(name, 'string');
    }
  }

  return name + ': ' + str;
}


function reduceToSingleString(output, base, braces) {
  var numLinesEst = 0;
  var length = output.reduce(function(prev, cur) {
    numLinesEst++;
    if (cur.indexOf('\n') >= 0) numLinesEst++;
    return prev + cur.replace(/\u001b\[\d\d?m/g, '').length + 1;
  }, 0);

  if (length > 60) {
    return braces[0] +
           (base === '' ? '' : base + '\n ') +
           ' ' +
           output.join(',\n  ') +
           ' ' +
           braces[1];
  }

  return braces[0] + base + ' ' + output.join(', ') + ' ' + braces[1];
}


// NOTE: These type checking functions intentionally don't use `instanceof`
// because it is fragile and can be easily faked with `Object.create()`.
function isArray(ar) {
  return Array.isArray(ar);
}
exports.isArray = isArray;

function isBoolean(arg) {
  return typeof arg === 'boolean';
}
exports.isBoolean = isBoolean;

function isNull(arg) {
  return arg === null;
}
exports.isNull = isNull;

function isNullOrUndefined(arg) {
  return arg == null;
}
exports.isNullOrUndefined = isNullOrUndefined;

function isNumber(arg) {
  return typeof arg === 'number';
}
exports.isNumber = isNumber;

function isString(arg) {
  return typeof arg === 'string';
}
exports.isString = isString;

function isSymbol(arg) {
  return typeof arg === 'symbol';
}
exports.isSymbol = isSymbol;

function isUndefined(arg) {
  return arg === void 0;
}
exports.isUndefined = isUndefined;

function isRegExp(re) {
  return isObject(re) && objectToString(re) === '[object RegExp]';
}
exports.isRegExp = isRegExp;

function isObject(arg) {
  return typeof arg === 'object' && arg !== null;
}
exports.isObject = isObject;

function isDate(d) {
  return isObject(d) && objectToString(d) === '[object Date]';
}
exports.isDate = isDate;

function isError(e) {
  return isObject(e) &&
      (objectToString(e) === '[object Error]' || e instanceof Error);
}
exports.isError = isError;

function isFunction(arg) {
  return typeof arg === 'function';
}
exports.isFunction = isFunction;

function isPrimitive(arg) {
  return arg === null ||
         typeof arg === 'boolean' ||
         typeof arg === 'number' ||
         typeof arg === 'string' ||
         typeof arg === 'symbol' ||  // ES6 symbol
         typeof arg === 'undefined';
}
exports.isPrimitive = isPrimitive;

exports.isBuffer = require('./support/isBuffer');

function objectToString(o) {
  return Object.prototype.toString.call(o);
}


function pad(n) {
  return n < 10 ? '0' + n.toString(10) : n.toString(10);
}


var months = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep',
              'Oct', 'Nov', 'Dec'];

// 26 Feb 16:19:34
function timestamp() {
  var d = new Date();
  var time = [pad(d.getHours()),
              pad(d.getMinutes()),
              pad(d.getSeconds())].join(':');
  return [d.getDate(), months[d.getMonth()], time].join(' ');
}


// log is just a thin wrapper to console.log that prepends a timestamp
exports.log = function() {
  console.log('%s - %s', timestamp(), exports.format.apply(exports, arguments));
};


/**
 * Inherit the prototype methods from one constructor into another.
 *
 * The Function.prototype.inherits from lang.js rewritten as a standalone
 * function (not on Function.prototype). NOTE: If this file is to be loaded
 * during bootstrapping this function needs to be rewritten using some native
 * functions as prototype setup using normal JavaScript does not work as
 * expected during bootstrapping (see mirror.js in r114903).
 *
 * @param {function} ctor Constructor function which needs to inherit the
 *     prototype.
 * @param {function} superCtor Constructor function to inherit prototype from.
 */
exports.inherits = require('inherits');

exports._extend = function(origin, add) {
  // Don't do anything if add isn't an object
  if (!add || !isObject(add)) return origin;

  var keys = Object.keys(add);
  var i = keys.length;
  while (i--) {
    origin[keys[i]] = add[keys[i]];
  }
  return origin;
};

function hasOwnProperty(obj, prop) {
  return Object.prototype.hasOwnProperty.call(obj, prop);
}

}).call(this,require("FWaASH"),typeof self !== "undefined" ? self : typeof window !== "undefined" ? window : {})
},{"./support/isBuffer":9,"FWaASH":8,"inherits":7}],11:[function(require,module,exports){
/*
 * classie
 * http://github.amexpub.com/modules/classie
 *
 * Copyright (c) 2013 AmexPub. All rights reserved.
 */

module.exports = require('./lib/classie');

},{"./lib/classie":12}],12:[function(require,module,exports){
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
},{}],13:[function(require,module,exports){
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