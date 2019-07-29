import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:my_flutter/style/TYStyle.dart';
import 'package:my_flutter/widgets/CardItem.dart';
import 'package:my_flutter/widgets/YiFeiLocalization.dart';

class ChooseDialog extends StatefulWidget {
  final String dialogTitle;
  final VoidCallback cancelPressed;
  final VoidCallback confirmPressed;


  ChooseDialog(
    this.dialogTitle,
    this.cancelPressed,
    this.confirmPressed,
  );

  @override
  _ChooseDialogState createState() => _ChooseDialogState();
}

class _ChooseDialogState extends State<ChooseDialog> {
  @override
  Widget build(BuildContext context) {
    return new Container(
      height: MediaQuery.of(context).size.height,
      width: MediaQuery.of(context).size.width,
      color: Colors.black12,
      child: new GestureDetector(
        behavior: HitTestBehavior.translucent,
        onTap: () {
          FocusScope.of(context).requestFocus(new FocusNode());
        },
        child: new Center(
          child: new CardItem(
            margin: EdgeInsets.all(20.0),
            shape: new RoundedRectangleBorder(
                borderRadius: BorderRadius.all(Radius.circular(10.0))),
            child: new Padding(
              padding: EdgeInsets.all(12.0),
              child: new Column(
                mainAxisSize: MainAxisSize.min,
                children: <Widget>[
                  ///dialog标题
                  new Padding(
                      padding: new EdgeInsets.only(top: 5.0, bottom: 15.0),
                      child: new Center(
                        child: new Text(widget.dialogTitle,
                            style: TYConstant.normalTextBold),
                      )),

                  ///内容输入框
                  new Container(height: 30.0),
                  ///水平分割线
                  new Row(
                    crossAxisAlignment: CrossAxisAlignment.center,
                    children: <Widget>[

                      ///取消
                      new Expanded(
                          child: new RawMaterialButton(
                              materialTapTargetSize:
                                  MaterialTapTargetSize.shrinkWrap,
                              padding: EdgeInsets.all(4.0),
                              constraints: const BoxConstraints(
                                  minWidth: 0.0, minHeight: 0.0),
                              child: new Text(YiFeiLocalization.of(context).cancel,
                                  style: TYConstant.normalSubText),
                              onPressed: widget.cancelPressed)),
                      new Container(
                          width: 0.3,
                          height: 30.0,
                          color: Color(TYColors.subTextColor)),

                      ///确定
                      new Expanded(
                          child: new RawMaterialButton(
                              materialTapTargetSize:
                                  MaterialTapTargetSize.shrinkWrap,
                              padding: EdgeInsets.all(4.0),
                              constraints: const BoxConstraints(
                                  minWidth: 0.0, minHeight: 0.0),
                              child: new Text(YiFeiLocalization.of(context).confirm,
                                  style: TYConstant.normalTextBold),
                              onPressed: widget.confirmPressed)),
                    ],
                  )
                ],
              ),
            ),
          ),
        ),
      ),
    );
  }
}
