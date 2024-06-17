package com.kwj.presentation.ui.main.news

import com.kwj.common.log.MillieLogger
import com.kwj.domain.base.Result
import com.kwj.domain.usecase.GetNewsUseCase
import com.kwj.presentation.base.BaseViewModel
import com.kwj.presentation.base.DispatcherProvider
import com.kwj.presentation.base.onMain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

/**
 * NewsFragment 화면의 UI 데이터를 관리하기 위한 클래스 입니다.
 * 이 클래스는 화면 상태 변경 시 데이터의 보존을 보장하고, UI 클래스와 분리하여 UI 비즈니스 로직을 처리 합니다.
 *
 * @author (김위진)
 * @since (2024-06-17)
 */
@HiltViewModel
class NewsViewModel @Inject constructor(
    dispatcherProvider: DispatcherProvider,
    private val getNewsUseCase: GetNewsUseCase,
) : BaseViewModel(dispatcherProvider) {

    private val _state = MutableStateFlow<NewsViewState>(NewsViewState.Empty)
    val state: StateFlow<NewsViewState> = _state

    fun getNews() {
        onMain {
            _state.value = NewsViewState.Loading
            getNewsUseCase.invoke()
                .catch { caues ->
                    _state.value = NewsViewState.Error(caues.message)
                    MillieLogger.d("kwj ERROR = $caues")
                }
                .collectLatest { result ->
                    when (result) {
                        is Result.Success -> {
                            if (result.value.isNotEmpty()) {
                                _state.value = NewsViewState.GetNewsList(result.value)
                            } else {
                                _state.value = NewsViewState.Empty
                            }
                        }

                        is Result.Failure -> {
                            _state.value = NewsViewState.Error(result.message)
                        }

                        is Result.Exception -> {
                            _state.value = NewsViewState.Error(result.throwable?.message)
                        }
                    }
                }
        }
    }
}