package com.example.flashanime.network

import com.example.flashanime.data.JLPTWord
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//https://jlpt-vocab-api.vercel.app/api/words?word=養う
private const val VOCAB_HOST_NAME = "jlpt-vocab-api.vercel.app"
private const val VOCAB_BASE_URL = "https://$VOCAB_HOST_NAME/api/"

internal val moshi = Moshi.Builder()
    .addLast(KotlinJsonAdapterFactory())
    .build()


// Retrofit builder
private val retrofitMoshi = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(VOCAB_BASE_URL)
    .build()


interface FlashAnimeApiService {


    // get words from JLPt Api
    @GET("words")
    suspend fun getWordInfo(@Query("word") word: String): JLPTWord

//    /**
//     * Returns a Coroutine [Deferred] [ProductListResult] which can be fetched with await() if in a Coroutine scope.
//     * The @GET annotation indicates that the "products/{catalogType}" endpoint will be requested with the GET
//     * HTTP method (catalogType: men, women, accessories)
//     * The @Query annotation indicates that it will be added "?paging={pagingKey}" after endpoint
//     */
//    @GET("products/{catalogType}")
//    suspend fun getProductList(
//        @Path("catalogType") type: String,
//        @Query("paging") paging: String? = null
//    ): ProductListResult
//
//    /**
//     * Returns a Coroutine [Deferred] [UserProfileResult] which can be fetched with await() if in a Coroutine scope.
//     * The @GET annotation indicates that the "user/profile" endpoint will be requested with the GET HTTP method
//     * The @Header annotation indicates that it will be added "Authorization" header
//     */
//    @GET("user/profile")
//    suspend fun getUserProfile(@Header("Authorization") token: String): UserProfileResult
//    /**
//     * Returns a Coroutine [Deferred] [UserSignInResult] which can be fetched with await() if in a Coroutine scope.
//     * The @POST annotation indicates that the "user/signin" endpoint will be requested with the POST HTTP method
//     * The @Field annotation indicates that it will be added "provider", "access_token" key-pairs to the body of
//     * the POST HTTP method, and it have to use @FormUrlEncoded to support @Field
//     */
//    @FormUrlEncoded
//    @POST("user/signin")
//    suspend fun userSignIn(
//        @Field("provider") provider: String = "facebook",
//        @Field("access_token") fbToken: String
//    ): UserSignInResult
//
//    @POST("user/signin")
//    suspend fun userSignIn(
//        @Body nativeSignInBody: NativeSignInBody
//    ): UserSignInResult
//
//    @POST("user/signup")
//    suspend fun userSignUp(
//        @Body nativeSignUpBody: NativeSignUpBody
//    ): UserSignUpResult
//
//    /**
//     * Returns a Coroutine [Deferred] [CheckoutOrderResult] which can be fetched with await() if in a Coroutine scope.
//     * The @POST annotation indicates that the "user/signin" endpoint will be requested with the POST HTTP method
//     * The @Header annotation indicates that it will be added "Authorization" header
//     * The @Body annotation indicates that it will be added [OrderDetail] to the body of the POST HTTP method
//     */
//    @POST("order/checkout")
//    suspend fun checkoutOrder(
//        @Header("Authorization") token: String,
//        @Body orderDetail: OrderDetail
//    ): CheckoutOrderResult
}



object FlashAnimeMoshiApi {
    val retrofitService: FlashAnimeApiService by lazy { retrofitMoshi.create(FlashAnimeApiService::class.java) }
}
