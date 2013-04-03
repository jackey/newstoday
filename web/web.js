NewsToday = new Meteor.Collection("newstoday")

if (Meteor.isClient) {
	Template.index.newstoday = function () {
		return NewsToday.find({});
	}

	Template.index.events({
		'click #b-nav .nav-1 ul li': function () {
			alert('click');
		}
	});
}

if (Meteor.isServer) {
	Meteor.startup(function () {
		if (NewsToday.find().count() == 0) {
			var newstoday = [{
				title: "news from 1",
				body: "news body"
			}, {
				title: "news from 2",
				body: "news body"
			}];

			for (var i = 0; i < newstoday.length; i++) {
				NewsToday.insert(newstoday[i])
			}
		}
	});
}
