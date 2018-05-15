package com.horical.kmvparchitect.ui.main.rating

import android.support.v4.app.FragmentManager
import com.horical.kmvparchitect.ui.base.IBaseView

interface IRateUsView : IBaseView {

    fun show(mFragmentManager: FragmentManager)

    fun dismissDialog()
}