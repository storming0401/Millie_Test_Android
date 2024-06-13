package com.kwj.millie

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Application 클래스를 상속 받은 MainApplication 클래스 입니다.
 * 이 클래스는 애플리케이션 전체의 상태 관리를 위해 사용되며,
 * 애플리케이션의 생명주기에 걸쳐 필요한 전역 리소스의 초기화 및 설정을 담당합니다.
 *
 * @author (김위진)
 * @since (2024-06-13)
 */
@HiltAndroidApp
class MainApplication : Application() {
}