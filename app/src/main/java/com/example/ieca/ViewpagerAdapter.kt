package com.example.ieca

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewpagerAdapter (fm: FragmentManager):FragmentPagerAdapter(fm){
    override fun getCount(): Int {
        return 5;
    }

    override fun getItem(position: Int): Fragment {
       return PageFragment.newInstance(position + 1)
    }
}