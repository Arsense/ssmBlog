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
var classList = require('./class_list_ployfill'),
    classie;

function classReg(className) {
    return new RegExp("(^|\\s+)" + className + "(\\s+|$)");
}

function noop() {}

function isArr(classes) {
    if (Array.isArray(classes)) {
        return true;
    } else if (Object.prototype.toString.call(classes) === '[object Array]') {
        return true;
    } else {
        return false;
    }
}

function removeMultiple() {
    var c = arguments[1],
        elem = arguments[0];
    c.forEach(function(value) {
        if (classie.has(elem, value)) {
            noop();
        }
        classie.removeClass(elem, value);
    });
}


function addMultiple() {
    var c = arguments[1],
        elem = arguments[0];
    c.forEach(function(value) {
        if (classie.has(elem, value)) {
            noop();
        }
        classie.addClass(elem, value);
    });
}

function hasClass(elem, c) {
    return elem.classList.contains(c);
}

function addClass(elem, c) {
    if (isArr(c)) {
        addMultiple.apply(this, arguments);
    } else {
        elem.classList.add(c);
    }
}

function removeClass(elem, c) {
    if (isArr(c)) {
        removeMultiple.apply(this, arguments);
    } else {
        elem.classList.remove(c);
    }
}

function toggleClass(elem, c) {
    var fn = hasClass(elem, c) ? removeClass : addClass;
    fn(elem, c);
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

if (typeof module === "object" && module && typeof module.exports === "object") {
    // commonjs / browserify
    module.exports = classie;
} else {
    // AMD
    define(classie);
}
