import 'package:flutter/foundation.dart';
import 'package:flutter/widgets.dart';
import 'YiFeiLocalization.dart';

class YiFeiLocalizationDelegate extends LocalizationsDelegate<YiFeiLocalization>{

  static YiFeiLocalizationDelegate delegate = YiFeiLocalizationDelegate();

  YiFeiLocalizationDelegate();

  @override
  bool isSupported(Locale locale) {
    // TODO: implement isSupported
    return ['en','zh'].contains(locale.languageCode);
  }

  @override
  Future<YiFeiLocalization> load(Locale locale) {
    // TODO: implement load
    return SynchronousFuture<YiFeiLocalization> (new YiFeiLocalization(locale));
  }

  @override
  bool shouldReload(LocalizationsDelegate<YiFeiLocalization> old) {
    // TODO: implement shouldReload
    return false;
  }

}