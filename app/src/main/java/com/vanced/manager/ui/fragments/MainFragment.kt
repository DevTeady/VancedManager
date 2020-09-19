package com.vanced.manager.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.tabs.TabLayout
import com.vanced.manager.R
import com.vanced.manager.databinding.FragmentMainBinding
import kotlin.system.exitProcess

class MainFragment : Fragment() {
    
    private lateinit var binding: FragmentMainBinding
    private lateinit var navHost: NavController
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navHostFragment = childFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        navHost = navHostFragment.navController

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if(!navHost.popBackStack()) {
                    exitProcess(0)
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

        val tabLayout = requireActivity().findViewById<TabLayout>(R.id.main_tablayout)
        
        navHost.addOnDestinationChangedListener { _, currFrag: NavDestination, _ ->
            setDisplayHomeAsUpEnabled(currFrag.id != R.id.home_fragment)
            with (requireActivity()) {
                val tabHide = AnimationUtils.loadAnimation(this, R.anim.tablayout_exit)
                val tabShow = AnimationUtils.loadAnimation(this, R.anim.tablayout_enter)
                if (currFrag.id != R.id.home_fragment) {
                    if (tabLayout.visibility != View.GONE) {
                        tabLayout.startAnimation(tabHide)
                        tabLayout.visibility = View.GONE
                    }
                } else {
                   if (tabLayout.visibility != View.VISIBLE) {
                        tabLayout.visibility = View.VISIBLE
                        tabLayout.startAnimation(tabShow)
                    }
                }
            }
        }

    }
    
    private fun setDisplayHomeAsUpEnabled(isNeeded: Boolean) {
        with(requireActivity()) {
            findViewById<MaterialToolbar>(R.id.home_toolbar).navigationIcon = if (isNeeded) ContextCompat.getDrawable(this, R.drawable.ic_keyboard_backspace_black_24dp) else null
        }
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                requireActivity().onBackPressedDispatcher.onBackPressed()
                return true
            }
            R.id.toolbar_about -> {
                navHost.navigate(HomeFragmentDirections.toAboutFragment())
                return true
            }
            R.id.toolbar_settings -> {
                navHost.navigate(HomeFragmentDirections.toSettingsFragment())
                return true
            }
            R.id.dev_settings -> {
                navHost.navigate(SettingsFragmentDirections.toDevSettingsFragment())
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }

        return false
    }
    
}
