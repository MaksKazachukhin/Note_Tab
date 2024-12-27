package com.example.note_tab.ui.Fragment.onBoard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.note_tab.databinding.FragmentOnBoardBinding
import com.example.note_tab.ui.adapters.OnBoardViewPagerAdapter
import com.example.note_tab.ui.utils.PreferenceHelper

class OnBoardFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardBinding

    companion object {
        val sharedPref = PreferenceHelper()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkFirstTime()
        initialize()
        setUpListener()
    }

    private fun checkFirstTime() {
        sharedPref.unit(requireContext())
        if (sharedPref.isFirstTime) {
            val action = OnBoardFragmentDirections.actionOnBoardFragmentToNoteFragment()
            findNavController().navigate(action)
            onDestroyView()
        }
    }

    private fun initialize() {
        binding.viewPager2.adapter = OnBoardViewPagerAdapter(this)
        binding.dotsIndicator.attachTo(binding.viewPager2)
    }

    private fun setUpListener() = with(binding.viewPager2) {
        registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 2) {
                    binding.tvSkip.visibility = View.INVISIBLE
                    binding.btnStart.visibility = View.VISIBLE
                } else {
                    binding.tvSkip.visibility = View.VISIBLE
                    binding.btnStart.visibility = View.GONE
                    binding.tvSkip.setOnClickListener {
                        setCurrentItem(currentItem + 2, true)
                    }
                }
            }
        })

        binding.btnStart.setOnClickListener {
            if (!sharedPref.isFirstTime) {
                val action = OnBoardFragmentDirections.actionOnBoardFragmentToNoteFragment()
                findNavController().navigate(action)
                sharedPref.isFirstTime = true
            }
        }
    }
}