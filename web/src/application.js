NewsTodayApp = new Backbone.Marionette.Application();

NewsTodayApp.addRegions({
  sidebarLeftRegion: "#page-sidebar-left",
  contentRegion: "#page-content"
});

// Model
NewsModel = Backbone.Model.extend({
  defaults: {
    id: "",
    title: "",
    content: "",
    author: "",
    posted: "",
    from: "",
    location_x: 0,
    location_y: 0,
    media: "",
  }
});
NewsCollection = Backbone.Collection.extend({
  model: NewsModel,
  intialize: function (news) {
    this.on('add', function () {
      //TODO:
    });
  }
});

// Views
NewsView = Backbone.Marionette.ItemView.extend({
  tagName: "div",
  className: "news",
  template: "#news-template",
  initialize: function () {
    //TODO: empty
  }
});

NewsCollectionItemView = Backbone.Marionette.ItemView.extend({
  tagName: "li",
  className: "news-item",
  template: "#news-collection-item-template",
  events: {
    'click .news-item-title': "newsItemClick",
  },
  newsItemClick: function(event) {
    var id = _.first($(this.$el).find(".news-item-title").attr('class').match(/([\d]+)/g));
    if (id) {
      // trigger global event so that other view can change by this model
      window.router.navigate("!/news/" + id, true);
    }
  }
});

// Controller
AppController = {
  "newsViewRouterHandler": function (id) {
    //TODO
  }
}

// Routes
NewsTodayRouter = Backbone.Marionette.AppRouter.extend({
  appRoutes: {
    "!/news/:id": "newsViewRouterHandler"
  },
  controller: AppController
});

NewsCollectionView = Backbone.Marionette.CollectionView.extend({
  tagName: "ul",
  className: "news-collection",
  template: "#news-collection-template",
  itemView: NewsCollectionItemView
});

// startup when initialize
NewsTodayApp.addInitializer(function (options) {

  if (!_.isUndefined(options["newsCollection"])) {
    var newsCollection = options.newsCollection;
    var newsListView = new NewsCollectionView({
      collection: newsCollection
    });
    // set region content
    NewsTodayApp.sidebarLeftRegion.show(newsListView);
    var newsView = new NewsView({
      model: newsCollection.at(0)
    });
    NewsTodayApp.contentRegion.show(newsView);
  }

  window.router = new NewsTodayRouter();
});

// Start our routes
NewsTodayApp.on("initialize:after", function () {
  if (Backbone.history) {
    Backbone.history.start();
  }
});

NewsTodayApp.on('start', function (options) {
  //TODO:
});

$(document).ready(function () {
  var newsCollection = new NewsCollection([
    new NewsModel({
      id: 1,
      title: "习近平会见美国务卿克里 双方将讨论挑战性话题", 
      content: "新华网北京4月13日电（记者钱彤）中国国家主席习近平13日下午在人民大会堂会见美国国务卿克里。\
                习近平首先回顾了2009年5月克里作为美国参议院外交委员会主席对中国的访问。习近平说，你是美国资深政治家，无论担任参议员还是国务卿，都重视致力于发展中美关系，我对此表示赞赏。\
                习近平说，你是我担任国家主席后一个月内第二次会见美国内阁成员，前不久我会见了美国财政部长雅各布·卢，这说明两国都充分认识到中美关系的重要性。当前，中美关系处于新的时期，开局良好。我当选国家主席当天，就与奥巴马总统通电话，我们都重申要致力于发展中美合作伙伴关系，重申致力于构建中美新型大国关系，确认了两国关系的战略定向和发展方向。相信国务卿此次访华，有助于保持两国关系发展的积极势头。\
                克里表示，他此次访问正处于一个关键性时期，双方将讨论一系列富有挑战性的话题。在这样的关键时期，双方要致力于塑造美中关系，规划未来路线图。\
                访华议题广泛 朝鲜半岛局势或为核心\
                中新网(阚枫) 在国际舆论的分析中，经贸合作、网络安全、东海南海问题，这些都将是克里此次访华可能涉及的议题，但在朝鲜半岛局势不断升级的大背景下，东北亚安全和朝鲜半岛局势料将成为本次中美高层会晤的核心议题。\
                “中美之间需要沟通的议题非常多，但是作为国务卿访华，克里此次应该会将会谈的重点放在外交领域，除了换届之后的中美关系，在朝鲜半岛局势骤然紧张的背景下，朝鲜议题显然是重中之重。”李海东分析。\
                其实，在克里启程访华之前，美国国务院发言人纽兰就已表示，访华期间，朝鲜将是克里与中方讨论的核心议题。\
                中国外交部4月9日发布克里访华的消息时，并未透露克里访华是否会谈到朝鲜半岛局势，但外交部发言人洪磊表示，中方愿同美方一道，“管控好分歧与敏感问题”。\
                李海东表示，朝鲜半岛的紧张局势如果失控，这一地区危机极有可能向区域外扩散，中美两国的利益都会涉及，此时中美高层接触，就半岛局势沟通十分必要。\
                在中国人民大学国际关系学院教授时殷弘看来，中美高层这次在谈及朝鲜议题时，双方会交换彼此对于当前局势的判断。“美国会说服中国向朝鲜方面施加压力，中方也会向美方传达，在当前半岛局势紧张的情况下，美国及其盟国应行动谨慎，举止恰当，避免局势失控。",
      posted: "2013-04-13 16:36",
      media: "新华网",
      from: 'www.qq.com',
      author: "钱彤"
    }),
    new NewsModel({
      id: 2,
      title: "北京确诊首例人感染H7N9病例 患者为7岁女童",
      content: "中新网4月13日电 据北京市官方通报，该市昨日发现的首例人感染H7N9禽流感疑似病例，经中国疾控中心复核，该患者被确诊为人感染H7N9禽流感病例。\
                昨天下午，经北京市疾控中心实验室检测，确认地坛医院收治的一7岁患儿为人感染H7N9禽流感疑似病例。这是北京市发现的首例人感染H7N9禽流感疑似病例。\
                北京市疾控中心对两名密切接触者实施医学观察，未发现流感样症状。按照国家卫生和计划生育委员会规定，北京市卫生局将标本送中国疾控中心进行复核，被最终确诊为感染病例。\
                据介绍，患者为7岁女童，其父母在居住地从事活禽贩卖工作。患儿经H7N9禽流感病毒核酸阳性，目前病情稳定，两名密切接触者未发现异常。\
                国家卫生和计划生育委员会12日公布，截至12日18时，全国共报告人感染H7N9禽流感确诊病例43例，其中死亡11人。病例分布于上海(20例，死亡7例)、江苏(12例，死亡1例)、安徽(2例，死亡1例)、浙江(9例，死亡2例)4省市的23个地市级区域。\
                全国已确诊44例人感染H7N9病例 其中11人死亡\
                加上北京刚刚确认的一例确诊病例，全国共报告人感染H7N9禽流感确诊病例44例，死亡11人。除北京一例外，其余43例分布于上海(20例，死亡7例)、江苏(12例，死亡1例)、安徽(2例，死亡1例)、浙江(9例，死亡2例)4省市的23个地市级区。【详细】\
                卫计委：H7N9禽流感患者救治费用通过医保解决\
                国家卫生和计划生育委员会表示，明确人感染H7N9禽流感患者医疗救治费用通过医保解决，要求各地将H7N9禽流感纳入城乡居民大病保险补助和疾病应急医疗救助基金支付范围，高额医疗救治费用可通过大病保险制度和疾病应急医疗救助基金予以保障。",
      posted: "2013-04-13 09:08",
      media: "中国新闻网",
      from: "www.qq.com",
      author: "",
    })
  ]);
  NewsTodayApp.start({newsCollection: newsCollection});
});
