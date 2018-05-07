package com.horical.kmvparchitect.ui.main.about

import android.os.Bundle
import com.horical.kmvparchitect.R
import com.horical.kmvparchitect.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_about.*

class AboutFragment : BaseFragment<AboutPresenter>(), IAboutView {

    companion object {
        internal val TAG: String = AboutFragment::class.java.simpleName

        fun newInstance(): AboutFragment {
            val args = Bundle()
            val fragment = AboutFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_about

    override fun attachView() = mPresenter.onAttach(this)

    override fun setUp() {
        ibBack.setOnClickListener { mActivity?.onFragmentDetached(TAG) }
    }
}