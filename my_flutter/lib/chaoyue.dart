
import 'dart:convert';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

import 'bean/NewsBean.dart';
import 'network/HttpUtil.dart';

class ChaoyueApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {


    return MaterialApp(
      title: 'FlutterChaoyue',
      theme: ThemeData(
        // This is the theme of your application.
        //
        // Try running your application with "flutter run". You'll see the
        // application has a blue toolbar. Then, without quitting the app, try
        // changing the primarySwatch below to Colors.green and then invoke
        // "hot reload" (press "r" in the console where you ran "flutter run",
        // or press Run > Flutter Hot Reload in a Flutter IDE). Notice that the
        // counter didn't reset back to zero; the application is not restarted.
        primarySwatch: Colors.red,
      ),
      home: Scaffold(
        appBar: AppBar(
          title: Text(
            "超越小妹妹",
          ),
          centerTitle: true,
        ),
        body: MainNewsPage(title: '超越MM'),
      ),
    );
  }


}



class MainNewsPage extends StatefulWidget {
  MainNewsPage({Key key, this.title}) : super(key: key);

  final String title;

  @override
  State<StatefulWidget> createState() {
    // TODO: implement createState
    return NewsState();
  }
}

class NewsState extends State<MainNewsPage> {
  List<NewsItem> _list = new List();

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
//    getNewsData();
    getNativeData();

  }

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Scaffold(
//      appBar: AppBar(title: Text(widget.title)),
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