package com.example.materialmanagement

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.materialmanagement.InOutActivity.FragmentIO
import com.example.materialmanagement.ReturnActivity.FragmentReturn
import com.example.materialmanagement.StateActivity.FragmentState

//https://developer.android.com/training/animation/screen-slide-2?hl=ko

class MainPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    private val NUM_PAGES = 4

    override fun getItemCount(): Int = NUM_PAGES

    override fun createFragment(position: Int): Fragment {
        return when (position){
            0 -> FragmentIO()
            1 -> FragmentReturn()
            2 -> FragmentState()
            else -> FragmentSetting()

        }
    }
}