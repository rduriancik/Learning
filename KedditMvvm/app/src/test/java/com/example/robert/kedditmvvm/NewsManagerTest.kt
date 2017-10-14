package com.example.robert.kedditmvvm

import com.example.robert.kedditmvvm.api.*
import io.reactivex.observers.TestObserver
import junit.framework.Assert.assertEquals
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import retrofit2.Call
import retrofit2.Response
import java.util.*

/**
 * Unit test for NewsManager
 */
class NewsManagerTest {

    lateinit var testSub: TestObserver<RedditNews>
    lateinit var apiMock: NewsAPI
    lateinit var callMock: Call<RedditNewsResponse>

    @Before
    fun setUp() {
        testSub = TestObserver<RedditNews>()
        apiMock = mock<NewsAPI>()
        callMock = mock<Call<RedditNewsResponse>>()
        `when`(apiMock.getNews(anyString(), anyString())).thenReturn(callMock)
    }

    @Test
    fun testSuccess_basic() {
        // prepare
        val redditNewsResponse = RedditNewsResponse(RedditDataResponse(listOf(), null, null))
        val response = Response.success(redditNewsResponse)

        `when`(callMock.execute()).thenReturn(response)

        // call
        val newsManager = NewsManager(apiMock)
        newsManager.getNews("").subscribe(testSub)

        // assert
        testSub.assertNoErrors()
        testSub.assertValueCount(1)
        testSub.assertComplete()
    }

    @Test
    fun testSuccess_checkOneNews() {
        // prepare
        val newsData = RedditNewsDataResponse(
                "author",
                "title",
                10,
                Date().time,
                "thumbnail",
                "url"
        )
        val newsResponse = RedditChildrenResponse(newsData)
        val redditNewsResponse = RedditNewsResponse(RedditDataResponse(listOf(newsResponse), null, null))
        val response = Response.success(redditNewsResponse)

        `when`(callMock.execute()).thenReturn(response)

        // call
        val newsManager = NewsManager(apiMock)
        newsManager.getNews("").subscribe(testSub)

        // assert
        testSub.assertNoErrors()
        testSub.assertValueCount(1)
        testSub.assertComplete()

        assertEquals(newsData.author, testSub.values()[0].news[0].author)
        assertEquals(newsData.title, testSub.values()[0].news[0].title)
    }

    @Test
    fun testError() {
        // prepare
        val responseError = Response.error<RedditNewsResponse>(500,
                ResponseBody.create(MediaType.parse("application/json"), ""))

        `when`(callMock.execute()).thenReturn(responseError)

        // call
        val newsManager = NewsManager(apiMock)
        newsManager.getNews("").subscribe(testSub)

        // assert
        assertEquals(1, testSub.errorCount())
    }

    private inline fun <reified T : Any> mock(): T = Mockito.mock(T::class.java)
}