package com.bonesdev.newstoday;

import android.util.Log;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedList;

class NewsEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String title;
	private String author;
	private String time;
	private String teaser;
    private String imgurl = "http://img4.cache.netease.com/photo/0001/2013-07-25/600x450_94K6NN4456NT0001.jpg";
	
	public NewsEntity () {
		
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	public void setTeaser(String teaser) {
		this.teaser = teaser;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getAuthor() {
		return this.author;
	}
	
	public String getTime() {
		return this.time;
	}
	
	public String getTeaser() {
		return this.teaser;
	}

    public String getImgurl() {
        return this.imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }
	
	public NewsEntity(String title, String author, String time, String teaser) {
		this.title = title;
		this.author = author;
		this.time = time;
		this.teaser = teaser;
	}
	
	public String getUUID() {
		return this.title + this.author;
	}
	
	
	public String get(String name) {
		Field[] fields = this.getClass().getDeclaredFields();
		for (Field f : fields) {
			String n = f.getName();
			if (n.equals(name)) {
				try {
					return (String)f.get(this);
				}
				catch (IllegalAccessException e) {
					return "";
				}
			}
		}
		return "";
	}
}

class NewsEntityArrayList{
    private LinkedList<NewsEntity> list = new LinkedList<NewsEntity>();
    private int _crtpost;
    private int _step = 10;
    private static NewsEntityArrayList _instance = null;
    private NewsEntityArrayList() {
        this._crtpost = 0;
        NewsEntity[] news = {
            new NewsEntity("薄熙来涉嫌受贿、贪污、滥用职权案提起公诉", "新华网", "2013-07-25 10:00:38", "新华网济南7月25日电 薄熙来涉嫌受贿、贪污、滥用职权犯罪一案，经依法指定管辖，今日已由山东省济南市人民检察院向济南市中级人民法院提起公诉。"),
            new NewsEntity("媒体评薄熙来案：党纪国法面前没有特殊公民", "中国广播网(北京)", "2013-07-25 10:03:00", "中广网北京7月25日消息 今天，薄熙来案开庭审理。薄熙来犯下的罪行，将接受法律的审判，他必将受到应得的处罚。这一审判，再一次昭示全党和全国人民：党纪国法面前人人平等，制度执行没有例外，没有享受特权的党员领导干部和特殊公民，违法必究，贪腐必受惩处。"),
            new NewsEntity("习近平：以更大政治勇气突破利益固化藩篱", "新华网", "2013-07-24 15:18:35", "新华网武汉7月24日电 中共中央总书记、国家主席、中央军委主席习近平7月23日在湖北省武汉市主持召开部分省市负责人座谈会，征求对全面深化改革的意见和建议。他强调，必须以更大的政治勇气和智慧，不失时机深化重要领域改革，攻克体制机制上的顽瘴痼疾，突破利益固化的藩篱，进一步解放和发展社会生产力，进一步激发和凝聚社会创造力。"),
            new NewsEntity("月销售不超2万元企业将免征增值税营业税", "新京报(北京)", "2013-07-25 02:23:02", "昨日召开的国务院常务会议决定，从今年8月1日起，对小微企业中月销售额不超过2万元的，暂免征收增值税和营业税，并暂不规定减免期限。"),
            new NewsEntity("四川高官郭永祥落马调查:貌美女干部涉案", "新京报(北京)", "2013-07-25 02:31:55", "核心提示：6月，四川省文联主席郭永祥涉严重违纪接受调查。近日媒体称，四川省国资委一位副处级女干部因郭永祥案被带走调查。据介绍，这位女干部是成都本地人，30多岁，相貌气质俱佳，与郭永祥关系密切。纪检系统官员表示，郭永祥工作严谨务实，但生活作风有问题。"),
            new NewsEntity("总书记总理办公室主任相继揭晓 盘点历任主任", "中国经济网(北京)", "2013-07-25 08:21:43", "近日，官方媒体披露的两条人事变动信息尤为引人瞩目：中共中央办公厅副主任丁薛祥兼任总书记办公室主任，国务院研究室副主任石刚兼任总理办公室主任。进京任职前，丁薛祥一直在上海工作，与习近平在上海共事时间不到一年。而前两任总书记办公室主任，均曾长期跟随江泽民和胡锦涛。担任总理办公室主任前，石刚在国家发改委担任国民经济综合司司长一职长达7年。"),
            new NewsEntity("统筹施策是“李克强经济学”方法论", " 新华网", "2013-07-24 09:27:52", "如果说不刺激、去杠杆和调结构，是“李克强经济学”的基本思想，那么统筹施策就是“李克强经济学”的方法论，直接关乎基本思想的执行效果。"),
            new NewsEntity("中国军机首次穿越第一岛链进入太平洋空域", "新京报(北京)", "2013-07-25 03:35:49", "24日，日本防卫大臣小野寺五典称，中国军方的一架预警机“Y8”当天飞越冲绳主岛和宫古岛之间的公海上空，引发日本战机紧急升空。日媒体称，这是中国军方飞机首次被确认飞经该空域，进入太平洋。"),
            new NewsEntity("李源潮今将访朝出席朝鲜战争停战60周年纪念活动", "中国新闻网(北京)", "2013-07-25 00:20:00", "中新网7月25日电 据中国外交部公布的消息，应朝方邀请，中共中央政治局委员、国家副主席李源潮将率中国代表团于25日至28日访问朝鲜并出席朝鲜战争停战60周年纪念活动。"),
            new NewsEntity("深圳4名收废品人员街边打牌被劳教1年", "南京日报(南京)", "2013-07-25 05:42:23", "核心提示：2009年10月18日，深圳四名收废品人员：崔先兵和老乡彭华生、裴后远、柳井军在路边打牌，突然来了一辆警车把他们带到派出所。四人被拘留三日后被劳教一年。家属称，派出所此举是为完成当年劳教指标。2010年，四人状告深圳市劳教委，双方和解，其中一人获赔4万元。"),
            new NewsEntity("广州农业局长回应和王林蛇绕颈合影：仅玩游戏", "中安在线(合肥)", "2013-07-25 10:00:25", "南都讯记者王去愚实习生陈伊凡“气功大师”王林无疑是当下的网络红人，与他交往的各界知名人士和各级政府官员近日被媒体逐一曝光。现任广州市农业局党委书记、局长的汤锦华昨日也被网友爆料称曾面见王林，并以蛇缠脖与其合影。昨日傍晚，汤锦华回应称是13年前任增城市委书记时的旧事，蛇缠脖合影“只是玩游戏”。")};
        for (NewsEntity news_entity : news) {
            this.list.add(news_entity);
        }
    }

    public static NewsEntityArrayList instance() {
        if (_instance == null) {
            _instance = new NewsEntityArrayList();
        }

        return _instance;

    }

    public void setNumberItemsPerPage(int step) {
        this._step = step;
    }

    /**
     *
     * @return ArrayList<NewsEntity>
     */
    public LinkedList<NewsEntity> nextPager() {
        int endIndex = _crtpost + _step - 1;
        Log.d("Endindex", String.valueOf(endIndex));
        Log.d("CrtPost", String.valueOf(_crtpost));
        if (_crtpost + _step > this.list.size()) {
            endIndex = this.list.size() - 1;
        }
        Log.d("Endindex", String.valueOf(endIndex));
        LinkedList<NewsEntity> _returnList = new LinkedList<NewsEntity>();
        for(int index = _crtpost; index <= endIndex; index++) {
            _returnList.add(this.list.get(index));
            Log.d("Index: ", String.valueOf(index));
        }
        _crtpost = endIndex;
        return _returnList;
    }

    public ArrayList<NewsEntity> prePager() {
        return null;
    }
}