package com.kwj.domain.usecase

import com.kwj.domain.model.NewsItem
import com.kwj.domain.repository.NewsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

/**
 * 비즈니스 로직을 캡슐화하는 UseCase 클래스 입니다.
 * 이 클래스는 클릭한 아이템을 저장 하는 비즈니스 요구 사항을 구현 합니다.
 *
 * UseCase는 비즈니스 로직을 캠슐화 하고 중앙 집중화 하는데 핵심적인 역할을 합니다.
 * UseCase의 주요 목적은 비즈니스 규칙을 구현하고, 데이터의 흐름과 변환을 제어하는데 있습니다.
 * 이를 통해 비즈니스 로직과 UI 로직을 철저하게 분리할 수 있으며, 애플리케이션의 테스트와 유지보수를 용이하게 만듭니다.
 *
 * @author (김위진)
 * @since (2024-06-19)
 */
class SaveClickedItemUseCase @Inject constructor(
    @Named("IO") private val ioDispatcher: CoroutineDispatcher,
    private val repository: NewsRepository
) {
    suspend operator fun invoke(newsItem: NewsItem) =
        withContext(ioDispatcher) {
            repository.saveClickedItem(newsItem)
        }
}