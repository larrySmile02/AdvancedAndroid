import 'dart:ui';

import 'package:flutter/material.dart';
import 'package:my_flutter/pages/FashionNewsPage.dart';
import 'package:my_flutter/pages/ResetPwdPage.dart';


void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: 'FlutterForYiFei',
        theme: ThemeData(
          primarySwatch: Colors.red,
          hintColor: Color(0x99E6E6E6 ),
          inputDecorationTheme: InputDecorationTheme(
            hintStyle: TextStyle(color: Color(0x99A2A3AA),fontSize: 16)
          )
        ),
        routes: {
          ResetPwdPage.sName: (context) {
            return ResetPwdPage();
          },
          FashionNewsPage.sName: (context) {
            return FashionNewsPage(title: 'Fashion News');
          },
          ResetPwdPage.sDefaut: (context) {
            return ResetPwdPage();
          }
        });
  }
}
