package com.ssafy.sellerb.ui.main

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.ssafy.sellerb.R
import com.ssafy.sellerb.databinding.ActivityMainBinding
import com.ssafy.sellerb.di.component.ActivityComponent
import com.ssafy.sellerb.ui.base.BaseActivity
import com.ssafy.sellerb.util.Constants
import javax.inject.Inject


class MainActivity : BaseActivity<MainViewModel>() {

    lateinit var binding: ActivityMainBinding

    companion object {
        const val TAG = "MainActivity"
    }

    lateinit var navController: NavController

    override fun provideLayoutId(): View {
        binding = ActivityMainBinding.inflate(layoutInflater)
        return binding.root
    }

    @Inject
    lateinit var mainSharedViewModel: MainSharedViewModel

    override fun injectDependencies(activityComponent: ActivityComponent) =
        activityComponent.inject(this)

    override fun setUpView(savedInstanceState: Bundle?) {
        navController = findNavController(R.id.container)

        binding.bottomNav.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            onDestinationChanged(destination)
        }

        binding.fab.setOnClickListener {
            navController.navigate(R.id.item_home)
        }

        // FCM 토큰 수신
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "FCM 토큰 얻기에 실패하였습니다.", task.exception)
                return@OnCompleteListener
            }

            mainSharedViewModel.token.postValue(task.result)

            Log.d(TAG, "token: ${task.result ?: "task.result is null"}")
        })
        createNotificationChannel(Constants.CHANNEL_ID, "sellerb")

    }

    @RequiresApi(Build.VERSION_CODES.O)
    // Notification 수신을 위한 채널 추가
    private fun createNotificationChannel(id: String, name: String) {
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(id, name, importance)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    private fun onDestinationChanged(destination: NavDestination) {
        when (destination.id) {
            R.id.item_home,
            R.id.item_consulting_history,
            R.id.item_my_page -> {
                binding.coordinator.visibility = View.VISIBLE
            }
            else -> {
                binding.coordinator.visibility = View.GONE
            }
        }
    }

    override fun onNavigateUp(): Boolean {
        return navController.navigateUp() || super.onNavigateUp()
    }

    override fun onBackPressed() {
        if(navController.currentDestination!!.id != R.id.loginFragment) {
            super.onBackPressed()
        }
    }
}