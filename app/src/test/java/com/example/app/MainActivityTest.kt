package com.example.app

import android.widget.Button
import android.widget.EditText
import com.example.app.MainActivity
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MainActivityTest {

    private lateinit var activity: MainActivity

    @Mock
    private lateinit var mockEditText: EditText

    @Mock
    private lateinit var mockButton: Button

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        activity = Robolectric.buildActivity(MainActivity::class.java).create().get()
        activity.et_city = mockEditText
        activity.btn_search = mockButton
    }

    @Test
    fun testActivityNotNull() {
        assertNotNull(activity)
    }

    @Test
    fun testButtonClick() {
        val cityName = "New York"
        `when`(mockEditText.text).thenReturn(cityName)

        activity.btn_search.performClick()

        verify(activity).fetchWeatherData(cityName)
    }

    @Test
    fun testButtonClickWithEmptyCityName() {
        // Mock EditText and set up desired behavior
        val mockEditText = mock(EditText::class.java)
        `when`(mockEditText.text).thenReturn("")

        // Mock activity and set the mocked EditText
        val activity = MainActivity()
        activity.et_city = mockEditText

        // Perform button click
        activity.btn_search.performClick()

        // Verify that fetchWeatherData is not called
        verify(activity, Mockito.never()).fetchWeatherData(Mockito.anyString())
    }

    @Test
    fun testButtonClickWithNullCityName() {
        // Mock EditText and set up desired behavior
        val mockEditText = mock(EditText::class.java)
        `when`(mockEditText.text).thenReturn(null)

        // Mock activity and set the mocked EditText
        val activity = MainActivity()
        activity.et_city = mockEditText

        // Perform button click
        activity.btn_search.performClick()

        // Verify that fetchWeatherData is not called
        verify(activity, Mockito.never()).fetchWeatherData(Mockito.anyString())
    }
}