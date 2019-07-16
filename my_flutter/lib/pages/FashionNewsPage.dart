

import 'dart:convert';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:my_flutter/bean/NewsBean.dart';
import 'package:my_flutter/network/HttpUtil.dart';

class FashionNewsPage extends StatefulWidget {
  FashionNewsPage({Key key, this.title}) : super(key: key);

  static final String sName = "FashionNewsPage";
  final String title;

  @override
  State<StatefulWidget> createState() {
    // TODO: implement createState
    return NewsState();
  }
}

class NewsState extends State<FashionNewsPage> {
  List<NewsItem> _list = new List();

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
//    getNewsData();
    getNativeData();
    getSimpleNativeData();

  }

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Scaffold(
      appBar: AppBar(title: Text(widget.title)),
      body: ListView.builder(
          itemCount: _list.length,
          itemBuilder: (context, index) {
            return _buildRow(index);
          }),
    );
  }

  void getNativeData() async {
    print("NativeData: Get-->");
    const String NEWS_DATA = "news_data";
    const String CHANNEL = "com.mmd.flutterapp/plugin";
    const MethodChannel channel = MethodChannel(CHANNEL);
    Map<String ,String> map = {"data":"data from dart"};
    Map<String,dynamic> result = await channel.invokeMapMethod(NEWS_DATA,map);
    print("NativeData: result = "+result.toString());
    Map dataMap = json.decode(result['result'])  ;
    List<NewsItem> nativeItems = await HttpUtil.convertFromNative(dataMap);

    setCountValue(nativeItems);


  }

  void getSimpleNativeData() async{
    const String CHAOYUE = "chao_yue";
    const String CHANNEL = "com.mmd.flutterapp/plugin";
    const MethodChannel simpleChannel =  MethodChannel(CHANNEL);
    Map<String ,String> requestMap = {"data":"simple"};
    Map<String ,dynamic> simpleMap = await simpleChannel.invokeMapMethod(CHAOYUE,requestMap);
    print("SIMPLE_DATA = "+simpleMap.toString());
  }

  void getNewsData() async {
    List<NewsItem> items = await HttpUtil.getDioNewsItems();
    setCountValue(items);
  }

  void setCountValue(List<NewsItem> items) {
    setState(() {
      _list.addAll(items);
    });
  }

  Widget _buildRow(int index) {
    return Container(
      height: 120,
      margin: EdgeInsets.fromLTRB(15, 10, 15, 0),
      child: Column(
        verticalDirection: VerticalDirection.down,
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          Row(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: <Widget>[
              Image.network(
                _list[index].thumbnail_pic_s,
                height: 90,
                width: 135,
                fit: BoxFit.fitWidth,
              ),
              Expanded(
                  flex: 1,
                  child: Container(
                    margin: EdgeInsets.fromLTRB(10, 0, 0, 0),
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: <Widget>[
                        Text(
                          _list[index].title,
                          textAlign: TextAlign.left,
                          softWrap: true,
                          maxLines: 3,
                          overflow: TextOverflow.ellipsis,
                          style: TextStyle(
                            fontSize: 14,
                          ),
                        ),
                        Container(
                            margin: EdgeInsets.fromLTRB(0, 10, 0, 0),
                            child: Text(_list[index].date)),
                      ],
                    ),
                  ))
            ],
          ),
//          Text(_list[index].title)
        ],
      ),
    );
  }
}