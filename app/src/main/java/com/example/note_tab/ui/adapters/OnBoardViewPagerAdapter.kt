package com.example.note_tab.ui.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.note_tab.ui.Fragment.OnBoardPagingFragment
import com.example.note_tab.ui.Fragment.OnBoardPagingFragment.Companion.ARG_ONBOARD_POSITION

class OnBoardViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return OnBoardPagingFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_ONBOARD_POSITION, position)
            }
        }
    }
}
