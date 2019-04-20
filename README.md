# AdvancedAndroid

### AdvancedAndroid是什么？
积累在平时工作中实践过的优秀轮子，还有解决过的问题。

### 目前积累了哪些？
- Android手机屏幕适配工具
基于今日头条的屏幕适配方法，加以拓展，可以根据宽、高适配屏幕，也可以消除适配。因为在实践中发现同一个App中有的页面需要依据宽来做适配，有的页面需要依据高来做适配，有的页面无需特别适配。所以写了GlobelPersonUIconfig工具类，可以在BaseActivity中使用，也可以在单独的Activity中使用,但是不推荐在Application中使用。
- Toast工具
在Android 5.0以上有些手机上，如果通知权限关闭后就会有Toast弹不出的问题，有些手机（比如OPPO）通知权限默认关闭的，遇到这种情况就有bug了。
Toast工具就是为了解决这个问题。非原创，搬运自[Android轮子哥](https://github.com/getActivity/ToastUtils) 向作者致谢
- flutter混合开发。这是纯粹的练手项目，里面有一两个小坑。
<div align="center">
  <img src="https://github.com/larrySmile02/AdvancedAndroid/blob/master/images/%E5%B1%8F%E5%B9%95%E9%80%82%E9%85%8D.gif" width="260" alt="屏幕适配效果">
  <img src="https://github.com/larrySmile02/AdvancedAndroid/blob/master/images/toast.jpg" width="260" alt="Toast效果">
  <img src="https://github.com/larrySmile02/AdvancedAndroid/blob/master/images/native%E8%B7%B3%E8%BD%ACflutter%E9%A1%B5%E9%9D%A2%E6%95%88%E6%9E%9C%E5%9B%BE.gif" width="260" alt="Toast效果">
</div>
