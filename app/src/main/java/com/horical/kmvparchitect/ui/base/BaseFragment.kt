package com.horical.kmvparchitect.ui.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseFragment<P : BasePresenter<*, *>> : Fragment(), IBaseView {

    @Inject
    protected lateinit var mPresenter: P
    protected var mActivity: BaseActivity<*>? = null

    interface Callback {
        fun onFragmentAttached()

        fun onFragmentDetached(tag: String)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is BaseActivity<*>) {
            mActivity = context
            mActivity?.onFragmentAttached()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this )
        setHasOptionsMenu(false)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        attachView()
        setUp()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDetach()
    }

    override fun onDetach() {
        mActivity = null;
        super.onDetach()
    }

    abstract fun getLayoutId(): Int

    abstract fun attachView()

    abstract fun setUp()

    override fun showLoading() {
        mActivity?.showLoading()
    }

    override fun hideLoading() {
        mActivity?.hideLoading()
    }

    override fun handleError(errorCode: Int?, throwable: Throwable?) {
        mActivity?.handleThrowable(throwable)
    }

    override fun showMessage(message: String?) {
        mActivity?.showMessage(message)
    }

    override fun showMessage(resId: Int) {
        mActivity?.showMessage(resId)
    }

    override fun openActivityOnTokenExpire() {
        mActivity?.openActivityOnTokenExpire()
    }

    override fun isNetworkConnected(): Boolean {
        return mActivity?.isNetworkConnected()!!
    }

    override fun hideKeyboard() {
        mActivity?.hideKeyboard()
    }
}