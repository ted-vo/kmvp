package com.horical.kmvparchitect.ui.feed.opensource

import android.os.Bundle
import com.horical.kmvparchitect.R
import com.horical.kmvparchitect.ui.base.BaseFragment

class OpenSourceFragment : BaseFragment<OpenSourcePresenter>(), IOpenSourceContract.View {

    companion object {
        internal val TAG: String = OpenSourceFragment::class.java.simpleName

        fun newInstance(): OpenSourceFragment {
            val args = Bundle()
            val fragment = OpenSourceFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_open_source

    override fun attachView() = mPresenter.onAttach(this)

    override fun setUp() {

    }
}