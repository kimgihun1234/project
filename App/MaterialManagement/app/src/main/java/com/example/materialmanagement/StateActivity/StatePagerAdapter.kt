package com.example.materialmanagement.StateActivity

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.materialmanagement.StateActivity.TabFragments.*

//https://developer.android.com/training/animation/screen-slide-2?hl=ko

class StatePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    var fragments : ArrayList<Fragment> = ArrayList()
    private val NUM_PAGES = 5

    override fun getItemCount(): Int = NUM_PAGES

    override fun createFragment(position: Int): Fragment {
        return when (position){
            0 -> FragmentInState()
            1 -> FragmentOutState()
            2 -> FragmentInReturnState()
            3 -> FragmentOutReturnState()
            else -> FragmentCurrentState()

        }
    }
}