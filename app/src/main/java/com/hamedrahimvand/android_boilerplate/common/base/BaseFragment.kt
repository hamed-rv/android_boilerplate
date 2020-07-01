package com.hamedrahimvand.android_boilerplate.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.hamedrahimvand.android_boilerplate.common.base.BaseActivity
import dagger.android.AndroidInjection
import javax.inject.Inject

/**
 *
 *@author Hamed.Rahimvand
 *@since 6/12/20
 */
abstract class BaseFragment<V : BaseViewModel>: Fragment() {

    abstract val viewModel: V

    abstract fun layoutId(): Int
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    inline fun <reified T : BaseViewModel> getLazyViewModel(): Lazy<T> =
        lazy {
            ViewModelProvider(this, viewModelFactory)[T::class.java]
        }

    inline fun <reified T : BaseViewModel> getLazySharedViewModel(): Lazy<T> =
        lazy {
            (this.activity as BaseActivity<T>).viewModel
        }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId(),container,false)
    }

}