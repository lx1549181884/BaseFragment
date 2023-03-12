package com.rick.base.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.ReflectUtils
import com.rick.base.util.TypeUtil

abstract class BaseFragment<B : ViewDataBinding, Vm : ViewModel> : Fragment() {

    lateinit var binding: B
    lateinit var viewModel: Vm

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // 创建 ViewDataBinding 实例，并绑定生命周期
        binding = createBinding()
        binding.lifecycleOwner = viewLifecycleOwner
        // 创建 ViewModel 实例
        viewModel = createViewModel()
        // 初始化
        init(binding, viewModel)
        return binding.root
    }

    /**
     * 创建 ViewDataBinding 实例
     */
    private fun createBinding(): B {
        // 获取 ViewDataBinding 具体类型
        val bindingClass = getBindingClass()
        // 反射 ViewDataBinding.inflate(LayoutInflater) 方法创建实例
        return ReflectUtils.reflect(bindingClass).method("inflate", layoutInflater).get()
    }

    /**
     * 获取 ViewDataBinding 具体类型
     */
    private fun getBindingClass(): Class<B> {
        return TypeUtil.getClass(this, BaseFragment::class.java, 0)
    }

    /**
     * 创建 ViewModel 实例
     */
    private fun createViewModel(): Vm {
        // 获取 ViewModel 具体类型
        val viewModelClass = getViewModelClass()
        // 通过 ViewModelProvider 获取 ViewModel 实例
        return ViewModelProvider(this)[viewModelClass]
    }

    /**
     * 获取 ViewModel 具体类型
     */
    private fun getViewModelClass(): Class<Vm> {
        return TypeUtil.getClass(this, BaseFragment::class.java, 1)
    }

    /**
     * 初始化
     */
    abstract fun init(binding: B, viewModel: Vm)
}