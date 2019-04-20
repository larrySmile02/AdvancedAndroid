# AdvancedAndroid

### AdvancedAndroid是什么？
积累在平时工作中实践过的优秀轮子，还有解决过的问题。

### 目前积累了哪些？
- Android手机屏幕适配工具
基于今日头条的屏幕适配方法，加以拓展，可以根据宽、高适配屏幕，也可以消除适配。因为在实践中发现同一个App中有的页面需要依据宽来做适配，有的页面需要依据高来做适配，有的页面无需特别适配。所以写了GlobelPersonUIconfig工具类，可以在BaseActivity中使用，也可以在单独的Activity中使用,但是不推荐在Application中使用。
