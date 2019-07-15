import 'dart:convert';
import 'dart:io';
import 'package:dio/dio.dart';
import 'package:my_flutter/bean/NewsBean.dart';


import 'Apis.dart';
class HttpUtil{

  ///使用HttpClient获取数据，原生处理数据
  static void getNewsItems() async{
    var httpClient = new HttpClient();
    String responseString;
    try{
      var request = await httpClient.getUrl(Uri.parse(Apis.NewsApi));
      HttpClientResponse response = await request.close();
      if(response.statusCode == HttpStatus.ok){
        responseString = await response.transform(utf8.decoder).join();
        print("Myhttp : ok, response = "+responseString);

        Map map = json.decode(responseString);
        Map result = map['result'];
        print("Myhttp : result = "+result.toString());

        var list = result['data'] as List;
        List<NewsItem> items = list.map((i)=>NewsItem.fromMapJson(i)).toList();
        print("Myhttp : items.size = "+ items.length.toString());

      }

    }catch(exception){
      print("Myhttp :"+exception.toString());
    }
  }

  ///使用Dio库获取新闻数据
  static Future<List<NewsItem>> getDioNewsItems() async{
    Dio dio = new Dio();
    Response response = await dio.get(Apis.NewsApi);
    print("Myhttp : response.data = "+ response.data.toString());

    Map dataMap = response.data;
    print("Myhttp : dataMap  = "+ dataMap.toString());
    NewsAllBean allBean = NewsAllBean(dataMap['reason'], dataMap['result']);
    List<NewsItem> items = allBean.newsData.dataNews;
    print("Myhttp : items.size = "+ items.length.toString()+"  items[0].title = "+items[0].title);
    return items;
  }

  static Future<List<NewsItem>> convertFromNative(Map dataMap) async{
    print("nativeDataMap = "+dataMap.toString());
    NewsAllBean allBean = NewsAllBean(dataMap['reason'], dataMap['result']);
    List<NewsItem> items = allBean.newsData.dataNews;
    print("nativeDataMap : items.size = "+ items.length.toString()+"  items[0].title = "+items[0].title);
    return items;
  }



}