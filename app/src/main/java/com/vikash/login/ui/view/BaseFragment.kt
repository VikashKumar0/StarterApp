package com.vikash.login.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding


open class BaseFragment<T : ViewBinding>(private val inflateMethod : (LayoutInflater, ViewGroup?, Boolean) -> T) : Fragment(){

    private var _binding : T? = null
    val binding : T get() = _binding!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = inflateMethod.invoke(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}