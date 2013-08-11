#!/usr/bin/env node

var rsses = {
	"http://news.163.com": "http://news.163.com/special/00011K6L/rss_newstop.xml"
};

var _ = require("backbone"),
	Parser = require("feedparser"),
	request = require("request");

_.each(resses, function (rss_link) {
	request(rss_link)
	.pipe(new Parser({}))
	.on("error", function () {
		// TODO:
	})
	.on("media", function (meta) {

	})
	.on("readable", function () {
		var stream = this, item;
		while (item = stream.read()) {
			console.log(item);
		}
	});
});