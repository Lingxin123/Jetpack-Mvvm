package com.zs.base_library.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zs.base_library.utils.ColorUtils
import com.zs.base_library.utils.StatusUtils

/**
 * des mvvm 基础 activity
 * @date 2020/5/9
 * @author zs
 */
abstract class BaseVmActivity : AppCompatActivity(){

    private var mActivityProvider: ViewModelProvider? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getLayoutId()?.let { setContentView(it) }
        setStatusColor()
        setSystemInvadeBlack()
        initViewModel()
        init(savedInstanceState)
    }

    /**
     * 短 toast
     */
    protected fun showToastShort(msg:String){
        Toast.makeText(this,msg, Toast.LENGTH_SHORT).show()
    }

    /**
     * 长 toast
     */
    protected fun showToastLong(msg:String){
        Toast.makeText(this,msg, Toast.LENGTH_LONG).show()
    }

    /**
     * 设置状态栏背景颜色
     */
    protected open fun setStatusColor() {
        StatusUtils.setUseStatusBarColor(this, ColorUtils.parseColor("#00ffffff"))
    }

    /**
     * 沉浸式状态
     */
    protected open fun setSystemInvadeBlack() {
        //第二个参数是是否沉浸,第三个参数是状态栏字体是否为黑色。
        StatusUtils.setSystemStatus(this, true, true)
    }

    /**
     * 初始化viewModel
     * 之所以没有设计为抽象，是因为部分简单activity可能不需要viewModel
     * observe同理
     */
    open fun initViewModel(){

    }

    /**
     * 注册观察者
     */
    open fun observe(){

    }

    /**
     * 通过activity获取viewModel，跟随activity生命周期
     */
    protected open fun <T : ViewModel?> getActivityViewModel(modelClass: Class<T>): T? {
        if (mActivityProvider == null) {
            mActivityProvider = ViewModelProvider(this)
        }
        return mActivityProvider?.get(modelClass)
    }

    /**
     * activity入口
     */
    abstract fun init(savedInstanceState: Bundle?)

    /**
     * 获取layout布局
     */
    abstract fun getLayoutId():Int?


}