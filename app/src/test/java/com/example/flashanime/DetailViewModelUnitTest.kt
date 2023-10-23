package com.example.flashanime

import com.example.flashanime.data.AnimeInfo
import com.example.flashanime.data.MockFlashAnimeRepository
import com.example.flashanime.detail.DetailViewModel
import com.google.android.gms.tasks.Task
import com.google.common.truth.Truth.assertThat
import com.google.firebase.firestore.DocumentReference
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.RobolectricTestRunner
import java.io.IOException

@RunWith(RobolectricTestRunner::class)
class DetailViewModelUnitTest {

    private lateinit var viewModel: DetailViewModel

    private val mockFlashAnimeRepository = MockFlashAnimeRepository()

    private val mockAnimeInfo = AnimeInfo()

    @Before
    fun setup() {
        viewModel = DetailViewModel(mockFlashAnimeRepository, mockAnimeInfo)
    }

    /**
     * by the rules of equivalent classes, test timeToMillis() is functional if ...
     * ... have complete format like "2:15:30.50"
     * ... doesn't have millisecond like "2:15:30"
     * ... only have second and millisecond like "0:00:30.50"
     * ... have incomplete format or wrong format like "2:15" or "abc:def:ghi.jkl"
     * ... edge case like "999:59:59.99"
     */
    @Test
    fun `test normal input conversion`() {
        val originalTime = "2:15:30.50"
        val convertTime = viewModel.timeToMillis(originalTime)

        assertThat(convertTime).isEqualTo(8130500)  // 2 hours, 15 minutes, 30 secs, 50 millis
    }

    @Test
    fun `test input without milliseconds`() {
        val originalTime = "2:15:30"
        val convertTime = viewModel.timeToMillis(originalTime)
        assertThat(convertTime).isEqualTo(8130000)  // 2 hours, 15 minutes, 30 secs
    }

    @Test
    fun `test only seconds and milliseconds`() {
        val originalTime = "0:00:30.50"
        val convertTime = viewModel.timeToMillis(originalTime)
        assertThat(convertTime).isEqualTo(30500)  // 30 secs, 50 millis
    }

    @Test(expected = Exception::class)
    fun `test invalid input format`() {
        val originalTime = "hello"
        viewModel.timeToMillis(originalTime)  // This should throw an exception
    }

    @Test
    fun `test edge case`() {
        val originalTime = "999:59:59.99"
        val convertTime = viewModel.timeToMillis(originalTime)
        assertThat(convertTime).isEqualTo(3599999990)  // 999 hours, 59 minutes, 59 secs, 99 millis
    }


    /**
     * test down below assert that when AIP is no working then _wordInfoSelected will be set with null
     * test getWordInfo() is functional if ...
     * ... set _wordInfoSelected correctly
     * ... when API has no connection, it will throw exception
     */

    @Test
    fun `getWordInfo sets _wordInfoSelected correctly`() = runBlockingTest {
        viewModel.getWordInfo("養う")

        val result = viewModel.wordInfoSelected.value

        assertNotNull(result)
    }

    @Test
    fun `getWordInfo throws exception when no connection`() = runBlockingTest {
        mockFlashAnimeRepository.shouldThrowNoConnectionException = true

        viewModel.getWordInfo("養う")

        assertNull(viewModel.wordInfoSelected.value)
    }


}