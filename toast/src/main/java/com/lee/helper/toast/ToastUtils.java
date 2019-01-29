package com.lee.helper.toast;

import android.app.Activity;
import android.app.AppOpsManager;
import android.app.Application;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.v4.app.NotificationManagerCompat;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lee.helper.toast.style.ToastBlackStyle;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/ToastUtils
 *    time   : 2018/09/01
 *    desc   : Toast 工具类
 */
public final class ToastUtils {

    private static ToastHandler sToastHandler;

    private static IToastStyle sDefaultStyle;

    private static Toast sToast;

    private static String SAMSUNG = "samsung";

    public static void preToast(Activity activity){
        if (!NotificationManagerCompat.from(activity).areNotificationsEnabled() &&
                !"SupportToast".equals(ToastUtils.getToast().getClass().getSimpleName())) {
            // 因为吐司只有初始化的时候才会判断通知权限有没有开启，根据这个通知开关来显示原生的吐司还是兼容的吐司
           init(activity);
        }
    }

    /**
     * 初始化ToastUtils，在Application中初始化
     *
     * @param activity      当前Activity
     */
    public static void init(Activity activity) {
        Application application = activity.getApplication();
        // 检查默认样式是否为空，如果是就创建一个默认样式
        if (sDefaultStyle == null) {
            sDefaultStyle = new ToastBlackStyle();
        }

        // 判断有没有通知栏权限
        if (isNotificationEnabled(application)) {
            // Android 7.1 上发现主线程被阻塞后吐司会报错的问题
            if (Build.VERSION.SDK_INT == Build.VERSION_CODES.N_MR1) {
                sToast = new SafeToast(application);
            }else {
                sToast = new BaseToast(application);
            }
        }else {
//            sToast = new SupportToast(application);
            sToast = new SupportToast(activity);
        }

        final int gravity;
        // 适配 Android 4.2 新特性，布局反方向（开发者选项 - 强制使用从右到左的布局方向）
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            gravity = Gravity.getAbsoluteGravity(sDefaultStyle.getGravity(), application.getResources().getConfiguration().getLayoutDirection());
        } else {
            gravity = sDefaultStyle.getGravity();
        }
        setGravity(gravity, sDefaultStyle.getXOffset(), sDefaultStyle.getYOffset());

        setView(createTextView(application.getApplicationContext()));

        // 创建一个吐司处理类
        sToastHandler = new ToastHandler(sToast);
    }

    public static void init(Application application) {
        // 检查默认样式是否为空，如果是就创建一个默认样式
        if (sDefaultStyle == null) {
            sDefaultStyle = new ToastBlackStyle();
        }

        //
        String brand = Build.BRAND;
        // 判断有没有通知栏权限
        if(!TextUtils.isEmpty(brand) && SAMSUNG.equals(brand) && Build.VERSION.SDK_INT == Build.VERSION_CODES.N_MR1){
            sToast = new SupportToast(application);
        }else if (isNotificationEnabled(application)) {
            // Android 7.1 上发现主线程被阻塞后吐司会报错的问题
            if (Build.VERSION.SDK_INT == Build.VERSION_CODES.N_MR1) {
                sToast = new SafeToast(application);
            }else {
                sToast = new BaseToast(application);
            }
        }else {
            sToast = new SupportToast(application);
        }

        final int gravity;
        // 适配 Android 4.2 新特性，布局反方向（开发者选项 - 强制使用从右到左的布局方向）
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            gravity = Gravity.getAbsoluteGravity(sDefaultStyle.getGravity(), application.getResources().getConfiguration().getLayoutDirection());
        } else {
            gravity = sDefaultStyle.getGravity();
        }
        setGravity(gravity, sDefaultStyle.getXOffset(), sDefaultStyle.getYOffset());

        setView(createTextView(application.getApplicationContext()));

        // 创建一个吐司处理类
        sToastHandler = new ToastHandler(sToast);
    }

    /**
     * 初始化 ToastUtils 及样式
     */
    public static void init(Activity activity, IToastStyle style) {
        initStyle(style);
        init(activity);
    }

    /**
     * 初始化 ToastUtils 及样式
     */
    public static void init(Application application, IToastStyle style) {
        initStyle(style);
        init(application);
    }

    /**
     * 显示一个对象的吐司
     *
     * @param object      对象
     */
    public static void show(Object object) {
        show(object != null ? object.toString() : "null");
    }

    /**
     * 显示一个吐司
     *
     * @param id      如果传入的是正确的string id就显示对应字符串
     *                如果不是则显示一个整数的string
     */
    public static void show(int id) {

        checkToastState();

        try {
            // 如果这是一个资源id
            show(sToast.getView().getContext().getResources().getText(id));
        } catch (Resources.NotFoundException ignored) {
            // 如果这是一个int类型
            show(String.valueOf(id));
        }
    }

    /**
     * 显示一个吐司
     *
     * @param text      需要显示的文本
     */
    public static void show(CharSequence text) {

        checkToastState();

        if (text == null || text.equals("")) return;

        sToastHandler.add(text);
        sToastHandler.show();
    }

    /**
     * 取消吐司的显示
     */
    public static void cancel() {
        checkToastState();
        sToastHandler.cancel();
    }

    /**
     * 设置吐司的位置
     *
     * @param gravity           重心
     * @param xOffset           x轴偏移
     * @param yOffset           y轴偏移
     */
    public static void setGravity(int gravity, int xOffset, int yOffset) {
        checkToastState();
        sToast.setGravity(gravity, xOffset, yOffset);
    }

    /**
     * 给当前Toast设置新的布局，具体实现可看{@link BaseToast#setView(View)}
     */
    public static void setView(int layoutId) {
        checkToastState();

        setView(View.inflate(sToast.getView().getContext().getApplicationContext(), layoutId, null));
    }
    public static void setView(View view) {

        checkToastState();

         // 这个 View 不能为空
        if (view == null) {
            throw new IllegalArgumentException("Views cannot be empty");
        }

        // 当前必须用 Application 的上下文创建的 View，否则可能会导致内存泄露
        if (view.getContext() != view.getContext().getApplicationContext()) {
            throw new IllegalArgumentException("The view must be initialized using the context of the application");
        }

        // 如果吐司已经创建，就重新初始化吐司
        if (sToast != null) {
            // 取消原有吐司的显示
            sToast.cancel();
            sToast.setView(view);
        }
    }

    /**
     * 统一全局的Toast样式，建议在{@link android.app.Application#onCreate()}中初始化
     *
     * @param style         样式实现类，框架已经实现三种不同的样式
     *                      黑色样式：{@link ToastBlackStyle}
     *                      白色样式：{@link ToastWhiteStyle}
     *                      仿QQ样式：{@link ToastQQStyle}
     */
    public static void initStyle(IToastStyle style) {
        ToastUtils.sDefaultStyle = style;
        // 如果吐司已经创建，就重新初始化吐司
        if (sToast != null) {
            // 取消原有吐司的显示
            sToast.cancel();
            sToast.setView(createTextView(sToast.getView().getContext().getApplicationContext()));
            sToast.setGravity(sDefaultStyle.getGravity(), sDefaultStyle.getXOffset(), sDefaultStyle.getYOffset());
        }
    }

    /**
     * 获取当前Toast对象
     */
    public static Toast getToast() {
        return sToast;
    }

    /**
     * 检查吐司状态，如果未初始化请先调用{@link ToastUtils#init(Activity)}
     */
    private static void checkToastState() {
        // 吐司工具类还没有被初始化，必须要先调用init方法进行初始化
        if (sToast == null) {
            throw new IllegalStateException("ToastUtils has not been initialized");
        }
    }

    /**
     * 生成默认的 TextView 对象
     */
    private static TextView createTextView(Context context) {

        GradientDrawable drawable = new GradientDrawable();
        // 设置背景色
        drawable.setColor(sDefaultStyle.getBackgroundColor());
        // 设置圆角大小
        drawable.setCornerRadius(dp2px(context, sDefaultStyle.getCornerRadius()));

        TextView textView = new TextView(context);
        textView.setId(android.R.id.message);
        textView.setTextColor(sDefaultStyle.getTextColor());
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, sp2px(context, sDefaultStyle.getTextSize()));
        textView.setPadding(dp2px(context, sDefaultStyle.getPaddingLeft()), dp2px(context, sDefaultStyle.getPaddingTop()),
                dp2px(context, sDefaultStyle.getPaddingRight()), dp2px(context, sDefaultStyle.getPaddingBottom()));
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        // setBackground API版本兼容
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            textView.setBackground(drawable);
        }else {
            textView.setBackgroundDrawable(drawable);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 设置 Z 轴阴影
            textView.setZ(sDefaultStyle.getZ());
        }

        if (sDefaultStyle.getMaxLines() > 0) {
            // 设置最大显示行数
            textView.setMaxLines(sDefaultStyle.getMaxLines());
        }

        return textView;
    }

    /**
     * dp转px
     *
     * @param context       上下文
     * @param dpValue       dp值
     * @return              px值
     */
    private static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * sp转px
     *
     * @param context       上下文
     * @param spValue       sp值
     * @return              px值
     */
    private static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 检查通知栏权限有没有开启
     * 参考SupportCompat包中的： NotificationManagerCompat.from(context).areNotificationsEnabled();
     */
    private static boolean isNotificationEnabled(Context context){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return ((NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE)).areNotificationsEnabled();
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            AppOpsManager appOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
            ApplicationInfo appInfo = context.getApplicationInfo();
            String pkg = context.getApplicationContext().getPackageName();
            int uid = appInfo.uid;

            try {
                Class<?> appOpsClass = Class.forName(AppOpsManager.class.getName());
                Method checkOpNoThrowMethod = appOpsClass.getMethod("checkOpNoThrow", Integer.TYPE, Integer.TYPE, String.class);
                Field opPostNotificationValue = appOpsClass.getDeclaredField("OP_POST_NOTIFICATION");
                int value = (Integer) opPostNotificationValue.get(Integer.class);
                return (Integer) checkOpNoThrowMethod.invoke(appOps, value, uid, pkg) == 0;
            } catch (NoSuchMethodException | NoSuchFieldException | InvocationTargetException | IllegalAccessException | RuntimeException | ClassNotFoundException ignored) {
                return true;
            }
        } else {
            return true;
        }
    }
}