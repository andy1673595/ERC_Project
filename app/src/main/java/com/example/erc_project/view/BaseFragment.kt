package com.example.erc_project.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.kaopiz.kprogresshud.KProgressHUD

abstract class BaseFragment: Fragment(), BaseViewModelOwner {

    private lateinit var progressDialog: KProgressHUD

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressDialog = KProgressHUD(context)
    }

    override fun refreshProgressDialog(isLoading: Boolean) {
        if(isLoading) {
            progressDialog.show()
        } else {
            progressDialog.dismiss()
        }
    }

    override fun showToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
}