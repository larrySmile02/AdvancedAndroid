
class NewsAllBean{

  NewsAllBean(String reason, Map<String,dynamic> result){
    this.reason = reason;
    this.result = result;
    transformNewsData(result);
  }
  String reason;
  Map result;
  NewsData newsData; //result对应的对象

  void transformNewsData(Map<String , dynamic> map){
    this.newsData = NewsData(map['stat'], map['data']);
  }
}


class NewsData{

  NewsData(String stat,List<dynamic>data){
    this.stat = stat;
    this.data = data;
    transformItems(data);
  }

  String stat;
  List data;
  List<NewsItem> dataNews;

  void transformItems(List<dynamic> list){

    this.dataNews = list.map((i)=>NewsItem.fromMapJson(i)).toList();
  }


}


///具体的每一条新闻的内容
class NewsItem {
  NewsItem(
      {this.uniquekey,
      this.title,
      this.date,
      this.category,
      this.author_name,
      this.url,
      this.thumbnail_pic_s});

  String uniquekey; //":"779e0448503ff134fef798f81170b008",
  String title; //":"亚足联：2023年亚洲杯将由中国承办",
  String date; //":"2019-06-04 17:06",
  String category; //":"头条",
  String author_name; //":"央视网",
  String url; //":"http:\/\/mini.eastday.com\/mobile\/190604170654219.html",
  String
      thumbnail_pic_s; //":"http:\/\/05imgmini.eastday.com\/mobile\/20190604\/20190604170654_6fb492fbe34b25ca811121c7a7ea3c56_1_mwpm_03200403.jpg"

  static NewsItem fromMapJson(Map<String, dynamic> mapjson) {
    return NewsItem(
        uniquekey: mapjson['uniquekey'],
        title: mapjson['title'],
        date: mapjson['date'],
        category: mapjson['category'],
        author_name: mapjson['author_name'],
        url: mapjson['url'],
        thumbnail_pic_s: mapjson['thumbnail_pic_s']);
  }
}
