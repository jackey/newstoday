#!/usr/bin/env node

var	_ = require("underscore");

function Readlinks () {
	this._links = require("./links");
	this._pers = [];
}

Readlinks.prototype.each = function (cb) {
	var self = this;
	_.each(this._links, function (link, name) {
		try {
			var per = require("./provider/" + name);
			per.setup(name, link)
			self._pers.push(per);
		}
		catch(e) {
			//
			console.log(e);
		}
	})
	return this;
}

Readlinks.prototype.execute = function () {
	_.each(this._pers, function (provider) {
		provider.execute();
	})
	return this;
}

// Do it
var readlinks = new Readlinks();

(readlinks).each().execute();

function Readlinks () {
	this._links = require("./links");
	this._pers = [];
}

Readlinks.prototype.each = function (cb) {
	var self = this;
	_.each(this._links, function (link, name) {
		try {
			var per = require("./provider/" + name);
			per.setup(name, link)
			self._pers.push(per);
		}
		catch(e) {
			//
			console.log(e);
		}
	})
	return this;
}

Readlinks.prototype.execute = function () {
	_.each(this._pers, function (provider) {
		provider.execute();
	})
	return this;
}

// Do it
var readlinks = new Readlinks();

(readlinks).each().execute();
