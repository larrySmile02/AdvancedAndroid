import 'dart:ui';

import 'package:flutter/widgets.dart';

class YiFeiLocalization{

  final Locale locale;

  YiFeiLocalization(this.locale);

  static Map<String , Map<String, String>> _localizedValues = {
    'en':{
      'yf_forgot_pwd':'Forgot password','yf_forgot_hint':'Mobile number/e-mail address',  'yf_obtain_verify_code':'Obtain verification code',
      'yf_user_unregister':'User is not yet registered.Would you like to register now','yf_cancel':'Cancel','yf_confirm':'Confirm'
    },

    'zh':{
      'yf_forgot_pwd':'忘记密码','yf_forgot_hint':'手机号 / 邮箱', 'yf_obtain_verify_code':'获取验证码',
      'yf_user_unregister':'用户还未注册过，是否直接注册','yf_cancel':'取消','yf_confirm':'确认'
    }
  };

  get forgetPwd{
    return _localizedValues[locale.languageCode]['yf_forgot_pwd'];
  }

  get forgetHint{
    return _localizedValues[locale.languageCode]['yf_forgot_hint'];
  }

  get verifyCode{
    return _localizedValues[locale.languageCode]['yf_obtain_verify_code'];
  }

  get unRegister{
    return _localizedValues[locale.languageCode]['yf_user_unregister'];
  }

  get cancel{
    return _localizedValues[locale.languageCode]['yf_cancel'];
  }

  get confirm{
    return _localizedValues[locale.languageCode]['yf_confirm'];
  }


  static YiFeiLocalization of(BuildContext context){
    return Localizations.of(context, YiFeiLocalization);
  }
}