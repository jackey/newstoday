var restify = require("restify");

var server = restify.createServer({
	name: "newstoday",
	version: "0.0.1"
});

server.get("/", function(rq, res) {
	res.send({
		data: "welcome newstday"
	});
});

server.get("/hello/:name", function (req, res, next) {
	res.send("Hello, ", req.params.name);
});

server.listen(8081, function () {
	console.log("%s listening at %s", server.name, server.url);
});