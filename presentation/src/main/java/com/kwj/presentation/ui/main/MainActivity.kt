package com.kwj.presentation.ui.main

import android.os.Bundle
import androidx.activity.addCallback
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.kwj.presentation.R
import com.kwj.presentation.databinding.ActivityMainBinding
import com.kwj.presentation.util.ext.text
import com.kwj.presentation.util.ext.toastShort
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.launch

/**
 * 메인 화면을 담당하는 MainActivity 클래스입니다.
 * 이 클래스는 Hilt 의존성 주입 프레임워크를 사용하여, 필요한 의존성들을 자동으로 주입받습니다.
 * 또한, 앱의 시작점으로서 사용자 인터페이스를 초기화하고, 주요 상호작용을 관리합니다.
 *
 * @author (김위진)
 * @since (2024-06-13)
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val backPressEvent = MutableSharedFlow<Unit>(
        replay = 0,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initToolbar()

        handleOnBack()
    }

    private fun initToolbar() {
        setSupportActionBar(binding.toolbar)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.setDisplayShowTitleEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(false)
    }

    private fun handleOnBack() {
        onBackPressedDispatcher.addCallback {
            backPressEvent.tryEmit(Unit)
        }

        MainScope().launch {
            backPressEvent
                .scan(listOf(System.currentTimeMillis() - 2000)) { acc, _ ->
                    acc.takeLast(1) + System.currentTimeMillis()
                }
                .drop(1)
                .collectLatest {
                    if (it.last() - it.first() < 2000) {
                        finishAffinity()
                    } else {
                        this@MainActivity.toastShort(this@MainActivity.text(R.string.go_to_finish))
                    }
                }
        }
    }
}