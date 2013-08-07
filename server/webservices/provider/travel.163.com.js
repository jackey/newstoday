var feedparser = require("feedparser"),
	request = require("request");

module.exports = (function () {
	function provider () {
		this._name = null;
		this._link = null;
		this._url = null;
		this.reader = new feedparser();
	}

	provider.prototype.setup = function (name, link) {
		this._link = link;
		this._name = name;
		this._url = this._link["url"];
	}

	provider.prototype.onitem = function (item) {
		console.log(item);
	}

	provider.prototype.execute = function () {
		var self = this;
		request(this._url)
		.pipe(this.reader)
		.on("readable", function () {
			var item;
			while (item = this.read()) {
				self.onitem(item);
			} 
		});
	}

	return new provider ();
})();