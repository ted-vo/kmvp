package com.horical.kmvparchitect.ui.base

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseDialog<P : BasePresenter<*, *>> : DialogFragment(), IBaseView {

    @Inject
    protected lateinit var mPresenter: P
    protected var mActivity: BaseActivity<*>? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is BaseActivity<*>) {
            mActivity = context
            mActivity?.onFragmentAttached()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(getLayoutId())
        if (dialog.window != null) {
            dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window.setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT)
        }
        dialog.setCanceledOnTouchOutside(false)
        return dialog
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
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
        mActivity = null
        super.onDetach()
    }

    abstract fun getLayoutId(): Int

    abstract fun attachView()

    abstract fun setUp()

    override fun show(manager: FragmentManager, tag: String) {
        val transaction = manager.beginTransaction()
        val prevFragment = manager.findFragmentByTag(tag)
        if (prevFragment != null) {
            transaction.remove(prevFragment)
        }
        transaction.addToBackStack(null)
        show(transaction, tag)
    }

    fun dismissDialog(tag: String) {
        dismiss()
        mActivity?.onFragmentDetached(tag)
    }

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