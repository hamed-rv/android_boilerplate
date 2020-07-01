package com.hamedrahimvand.android_boilerplate.presentation.main

import android.os.Bundle
import com.hamedrahimvand.android_boilerplate.R
import com.hamedrahimvand.android_boilerplate.common.base.BaseActivity

/**
 *
 *@author Hamed.Rahimvand
 *@since 6/12/20
 */

class MainActivity : BaseActivity<MainViewModel>() {
    override val viewModel: MainViewModel by getLazyViewModel()
    override fun layoutId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}
