package tw.edu.pu.csim.tcyang.s1131221

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class ExamViewModel : ViewModel() {

    val serviceIcon = mutableStateOf(R.drawable.service0)
    val serviceIconX = mutableStateOf(0f)
    val serviceIconY = mutableStateOf(0f)

    private val serviceIcons = listOf(
        R.drawable.service0,
        R.drawable.service1,
        R.drawable.service2,
        R.drawable.service3
    )

    fun startDropping(screenHeight: Float, screenWidth: Float) {
        viewModelScope.launch {
            while (true) {
                serviceIconY.value += 20f
                if (serviceIconY.value > screenHeight) {
                    resetServiceIcon(screenWidth)
                }
                delay(100)
            }
        }
    }

    fun resetServiceIcon(screenWidth: Float) {
        serviceIconY.value = 0f
        serviceIconX.value = screenWidth / 2
        serviceIcon.value = serviceIcons[Random.nextInt(serviceIcons.size)]
    }

    fun updateServiceIconX(dragAmount: Float) {
        serviceIconX.value += dragAmount
    }
}
