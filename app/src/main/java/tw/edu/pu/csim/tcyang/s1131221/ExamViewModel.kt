package tw.edu.pu.csim.tcyang.s1131221

import android.graphics.RectF
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
    val collisionText = mutableStateOf("")

    private val serviceIcons = listOf(
        R.drawable.service0,
        R.drawable.service1,
        R.drawable.service2,
        R.drawable.service3
    )

    fun startDropping(screenHeight: Float, screenWidth: Float, roleIconRects: List<RectF>) {
        viewModelScope.launch {
            while (true) {
                serviceIconY.value += 20f

                val serviceIconRect = RectF(
                    serviceIconX.value,
                    serviceIconY.value,
                    serviceIconX.value + 110.dpToPx(), // Approximate size
                    serviceIconY.value + 110.dpToPx()
                )

                // Check for collision with role icons
                for ((index, roleRect) in roleIconRects.withIndex()) {
                    if (RectF.intersects(serviceIconRect, roleRect)) {
                        collisionText.value = when (index) {
                            0 -> "(碰撞嬰幼兒圖示)"
                            1 -> "(碰撞兒童圖示)"
                            2 -> "(碰撞成人圖示)"
                            else -> "(碰撞一般民眾圖示)"
                        }
                        resetServiceIcon(screenWidth)
                        continue
                    }
                }

                // Check for collision with bottom of the screen
                if (serviceIconY.value > screenHeight) {
                    collisionText.value = "(掉到最下方)"
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

// Extension function to convert dp to px (you might need to adjust this based on your project setup)
fun Int.dpToPx(): Float {
    // This is a simplified conversion, for a more accurate one, you'd use Density
    return this * 3.5f // Example density
}
