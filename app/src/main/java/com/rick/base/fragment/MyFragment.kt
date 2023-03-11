package com.rick.base.fragment

import com.rick.base.base.BaseFragment
import com.rick.base.databinding.FragmentMyBinding
import com.rick.base.vm.MyViewModel

class MyFragment : BaseFragment<FragmentMyBinding, MyViewModel>() {
    override fun init(binding: FragmentMyBinding, viewModel: MyViewModel) {
        binding.viewModel = viewModel
    }
}