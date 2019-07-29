import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:my_flutter/dialog/ChooseDialog.dart';
import 'package:my_flutter/widgets/YiFeiLocalization.dart';

class ForgotPwdPage extends StatefulWidget {
  static String sName = "ForgotPwdPage";
  static final String sDefaut = "/";

  @override
  State<StatefulWidget> createState() {
    // TODO: implement createState
    return NboForgetPwdState();
  }
}

class NboForgetPwdState extends State<ForgotPwdPage> {
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Scaffold(
      appBar: PreferredSize(
          child: AppBar(
            backgroundColor: Colors.white,
            leading: Builder(builder: (BuildContext context){
//              return Image.asset('assets/3.0x/yf_hb_back_icon.png',height: 12,width: 12,fit: BoxFit.contain,);
              return IconButton(
                  icon: Icon(Icons.arrow_back));
            }),), 
          preferredSize: Size.fromHeight(44.0)),
      body: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          Container(
            margin: EdgeInsets.fromLTRB(24, 24, 0, 0),
            child: Text(
              YiFeiLocalization.of(context).forgetPwd,
              style: TextStyle(
                  color: Colors.black,
                  fontSize: 34,
                  fontWeight: FontWeight.bold),
            ),
          ),
          Container(
            margin: EdgeInsets.fromLTRB(24, 24, 24, 0),
            child: TextField(
              decoration: InputDecoration(
                  hintText: YiFeiLocalization.of(context).forgetHint,
                  hintStyle: TextStyle(color: Color(0x99A2A3AA))),
            ),
          ),
          Row(
            children: <Widget>[
              Expanded(
                flex: 1,
                child: Container(
                  height: 48,
                    margin: EdgeInsets.fromLTRB(24, 38, 24, 0),
                    child: FlatButton(
                      color: Color(0x99DEDEE0),
                      highlightColor: Color(0x99658FBC),
                      onPressed: () => {
                        showDialog(context: context,
                        builder: (BuildContext context){
                          return Center(child: ChooseDialog(YiFeiLocalization.of(context).unRegister,(){Navigator.pop(context);},(){}));
                        })


                      },
                      child: Text(YiFeiLocalization.of(context).verifyCode,
                          style: TextStyle(color: Colors.white, fontSize: 16)),
                      shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(24.0)),
                    )),
              )
            ],
          )
        ],
      ),
    );
  }
}
