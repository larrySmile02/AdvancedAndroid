import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:my_flutter/dialog/ChooseDialog.dart';

class ResetPwdPage extends StatefulWidget {
  static String sName = "ResetPwdPage";
  static final String sDefaut = "/";

  @override
  State<StatefulWidget> createState() {
    // TODO: implement createState
    return NboForgetPwdState();
  }
}

class NboForgetPwdState extends State<ResetPwdPage> {
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Scaffold(
      body: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          Container(
            margin: EdgeInsets.fromLTRB(24, 44, 0, 0),
            child: Text(
              '重设密码',
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
                  hintText: '密码至少6位，需要包含数字和字母',
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
                          return Center(child: ChooseDialog('用户还未注册过，是否直接注册？',(){Navigator.pop(context);},(){}));
                        })


                      },
                      child: Text('完成',
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
