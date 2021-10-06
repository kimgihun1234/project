package com.example.materialmanagement

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

//https://developer.android.com/training/animation/screen-slide-2?hl=ko

class MyPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    private val NUM_PAGES = 4

    override fun getItemCount(): Int = NUM_PAGES

    override fun createFragment(position: Int): Fragment {
        return when (position){
            0 -> { FragmentHome.newInstance("홈", "")}
            //0 -> FragmentHome()
            //1 -> { FragmentHome.newInstance("입출고", "")}
            1 -> FragmentIO()
            2 -> { FragmentHome.newInstance("기록", "")}
            else -> { FragmentHome.newInstance("현황보기", "")}

        }
    }
}