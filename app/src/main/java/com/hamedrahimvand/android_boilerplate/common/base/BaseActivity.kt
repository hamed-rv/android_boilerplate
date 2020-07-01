package com.hamedrahimvand.android_boilerplate.common.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hamedrahimvand.android_boilerplate.common.base.BaseViewModel
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject


/**
 *
 *@author Hamed.Rahimvand
 *@since 6/12/20
 */
abstract class BaseActivity<V : BaseViewModel> : AppCompatActivity() {

    abstract val viewModel: V

    abstract fun layoutId(): Int
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    inline fun <reified T : ViewModel> getLazyViewModel(): Lazy<T> =
        lazy {
            ViewModelProvider(this, viewModelFactory)[T::class.java]
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        layoutId().takeIf { it > 0 }?.let { validLayoutId ->
            setContentView(validLayoutId)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

}