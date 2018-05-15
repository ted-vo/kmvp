package com.horical.kmvparchitect.ui.main.rating

import android.os.Bundle
import android.support.v4.app.FragmentManager
import butterknife.OnClick
import com.horical.kmvparchitect.R
import com.horical.kmvparchitect.ui.base.BaseDialog
import kotlinx.android.synthetic.main.dialog_rate_us.*

class RateUsDialog : BaseDialog<RateUsPresenter>(), IRateUsView {

    companion object {

        internal var TAG: String = RateUsDialog::class.java.simpleName
        fun newInstance(): RateUsDialog {
            val dialog = RateUsDialog()
            val bundle = Bundle()
            dialog.arguments = bundle
            return dialog
        }
    }

    override fun getLayoutId(): Int = R.layout.dialog_rate_us

    override fun attachView() = mPresenter.onAttach(this)

    override fun setUp() {
        btnLater.setOnClickListener { dismissDialog() }
    }

    @OnClick(R.id.btnSubmit)
    public fun onBtnSubmitClicked() = { dismissDialog() }

    override fun show(mFragmentManager: FragmentManager) {
        show(mFragmentManager, TAG)
    }

    override fun dismissDialog() {
        dismissDialog(TAG)
    }
}