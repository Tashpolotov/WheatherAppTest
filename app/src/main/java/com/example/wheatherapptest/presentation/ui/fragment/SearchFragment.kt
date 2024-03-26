package com.example.wheatherapptest.presentation.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.wheatherapptest.R
import com.example.wheatherapptest.databinding.FragmentSearchBinding
import com.example.wheatherapptest.presentation.ui.viewmodel.WeatherViewModel
import com.example.wheatherapptest.utils.base.BaseFragment
import com.example.wheatherapptest.utils.sharefpref.SharedPref
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment(R.layout.fragment_search) {

    private lateinit var binding:FragmentSearchBinding
    private lateinit var sharedPref: SharedPref
    private val viewModel by viewModels<WeatherViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPref = SharedPref(requireContext())


        binding.buttonSearch.setOnClickListener {
            val cityName = binding.editTextSearch.text.toString().trim()
                sharedPref.cityy = cityName
                val bundle = Bundle().apply {
                    putString("cityName", cityName)
                }
                findNavController().navigate(R.id.action_searchFragment_to_weatgerFragment, bundle)
        }
    }
}