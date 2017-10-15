package com.example.robert.kedditmvvm

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>
    private val fragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        if (savedInstanceState == null) {
            changeFragment(NewsFragment())
        }
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector

    private fun changeFragment(fragment: Fragment, cleanStack: Boolean = false) {
        if (cleanStack) {
            clearBackStack()
        }

        fragmentManager.beginTransaction().also {
            it.setCustomAnimations(
                    R.anim.abc_fade_in, R.anim.abc_fade_out, R.anim.abc_popup_enter, R.anim.abc_popup_exit)
            it.replace(main_container.id, fragment)
            it.addToBackStack(null)
        }.commit()
    }

    private fun clearBackStack() {
        if (fragmentManager.backStackEntryCount > 0) {
            val first = fragmentManager.getBackStackEntryAt(0)
            fragmentManager.popBackStack(first.id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }

    /**
     * Finish activity when reaching the last fragment.
     */
    override fun onBackPressed() {
        if (fragmentManager.backStackEntryCount > 1) {
            fragmentManager.popBackStack()
        } else {
            finish()
        }
    }
}
