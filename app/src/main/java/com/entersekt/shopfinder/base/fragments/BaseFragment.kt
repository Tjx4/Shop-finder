package com.entersekt.shopfinder.base.fragments

import android.content.Context
import androidx.fragment.app.Fragment
import com.entersekt.shopfinder.MyDrawerController

abstract class BaseFragment : Fragment() {
    protected lateinit var myDrawerController: MyDrawerController

    override fun onAttach(context: Context) {
        super.onAttach(context)
        myDrawerController = activity as MyDrawerController
    }
}