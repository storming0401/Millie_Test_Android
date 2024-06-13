package com.kwj.data.util

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

/**
 * Retrofit 응답이 비어 있을 경우 null을 반환 하도록 하는 Converter Factory 클래스 입니다.
 * 이 컨버터 팩토리는 Retrofit의 응답을 처리할 때, HTTP 응답 본문이 비어 있는 경우에
 * 자동으로 null을 반환하게 하여, 더 안전하게 응답을 처리할 수 있도록 돕습니다.
 *
 * NullOnEmptyConverterFactory는 Retrofit 구성 시에 컨버터 팩토리 체인에 추가 되어야 합니다.
 * 이를 통해 응답이 비어 있으면 null을 반환 하고, 그렇지 않으면 다음 컨버터 팩토리로 넘어갑니다.
 *
 * 사용 예:
 * val retrofit = Retrofit.Builder()
 *      .baseUrl(BASE_URL)
 *      .client(okHttpClient)
 *      .addConverterFactory(NullOnEmptyConverterFactory())
 *      .addConverterFactory(gsonConverterFactory)
 *      .addCallAdapterFactory(ApiResultAdapterFactory())
 *      .build()
 *
 * @author (김위진)
 * @since (2024-06-13)
 */
class NullOnEmptyConverterFactory : Converter.Factory() {
    override fun responseBodyConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ) = object : Converter<ResponseBody, Any?> {
        val delegate = retrofit.nextResponseBodyConverter<Any?>(
            this@NullOnEmptyConverterFactory,
            type,
            annotations
        )

        override fun convert(body: ResponseBody) =
            if (body.contentLength() == 0L) null else delegate.convert(body)
    }
}