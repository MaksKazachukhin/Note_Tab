package com.example.note_tab.ui.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.note_tab.R
import com.example.note_tab.databinding.FragmentOnBoardBinding
import com.example.note_tab.ui.adapters.OnBoardViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator


class OnBoardFragment : Fragment() {
    private lateinit var binding: FragmentOnBoardBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListeners()
        tabLayout()
    }

    private fun tabLayout() {
        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { _, _ ->
        }.attach()
    }

    private fun initialize() {
        binding.viewPager2.adapter = OnBoardViewPagerAdapter(this)
    }

    private fun setupListeners() = with(binding.viewPager2) {
        registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 2) {
                    binding.text11.visibility = View.INVISIBLE
                } else {
                    binding.text11.visibility = View.VISIBLE
                    binding.text11.setOnClickListener {
                        setCurrentItem(currentItem + 2, true)
                    }
                }
            }
        })
        binding.btnstart.setOnClickListener {
            findNavController().navigate(R.id.regFragment)
        }
    }
}