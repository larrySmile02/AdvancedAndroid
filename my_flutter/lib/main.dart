import 'dart:ui';

import 'package:flutter/material.dart';
import 'package:my_flutter/pages/FashionNewsPage.dart';
import 'package:my_flutter/pages/ForgotPwdPage.dart';
import 'package:flutter_localizations/flutter_localizations.dart';
import 'package:my_flutter/widgets/YiFeiLocalizationDelegate.dart';


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

        //多语言支持
        localizationsDelegates:  [
          GlobalMaterialLocalizations.delegate,
          GlobalWidgetsLocalizations.delegate,
          YiFeiLocalizationDelegate.delegate,
        ],
        supportedLocales: [
          const Locale('en', 'US'), // 美国英语
          const Locale('zh', 'CN'), // 中文简体

        ],

        routes: {
          ForgotPwdPage.sName: (context) {
            return ForgotPwdPage();
          },
          FashionNewsPage.sName: (context) {
            return FashionNewsPage(title: 'Fashion News');
          },
          ForgotPwdPage.sDefaut: (context) {
            return ForgotPwdPage();
          }
        });
  }
}
