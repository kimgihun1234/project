package com.example.materialmanagement

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.adapter.FragmentViewHolder
import com.example.materialmanagement.InOutActivity.FragmentIO

//https://developer.android.com/training/animation/screen-slide-2?hl=ko

class MyPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    private val NUM_PAGES = 4

    override fun getItemCount(): Int = NUM_PAGES

    override fun createFragment(position: Int): Fragment {
        return when (position){
            0 -> FragmentIO()
            1 -> { FragmentHome.newInstance("반품", "")}
            2 -> { FragmentHome.newInstance("현황보기", "")}
            else -> { FragmentHome.newInstance("설정", "")}

        }
    }
}