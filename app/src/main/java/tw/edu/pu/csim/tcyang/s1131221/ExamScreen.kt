package tw.edu.pu.csim.tcyang.s1131221

import android.graphics.RectF
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlin.math.roundToInt

@Composable
fun ExamScreen(viewModel: ExamViewModel = viewModel()) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow)
    ) {
        val screenHeight = this.maxHeight
        val screenWidth = this.maxWidth
        val screenHeightPx = with(LocalDensity.current) { screenHeight.toPx() }
        val screenWidthPx = with(LocalDensity.current) { screenWidth.toPx() }

        val imageSize = 110.dp // Approximation of 300px

        val roleIconRects = remember { mutableListOf<RectF>() }

        // Start the dropping animation
        LaunchedEffect(Unit) {
            viewModel.resetServiceIcon(screenWidthPx)
            viewModel.startDropping(screenHeightPx, screenWidthPx, roleIconRects)
        }

        // Centered Content
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.happy),
                contentDescription = "Happy face"
            )
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(text = "瑪利亞基金會服務大考驗")
                Text(text = "作者 資管2B謝雨安")
                Text(text = "螢幕大小:1080.0*1920.0")
                Text(text = "成績:0分 ${viewModel.collisionText.value}")
            }
        }

        // Dropping Service Icon
        Image(
            painter = painterResource(id = viewModel.serviceIcon.value),
            contentDescription = "Service Icon",
            modifier = Modifier
                .offset { IntOffset(viewModel.serviceIconX.value.roundToInt(), viewModel.serviceIconY.value.roundToInt()) }
                .size(imageSize)
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        change.consume()
                        viewModel.updateServiceIconX(dragAmount.x)
                    }
                }
        )


        // Role Icons
        val density = LocalDensity.current
        val imageSizePx = with(density) { imageSize.toPx() }

        Image(
            painter = painterResource(id = R.drawable.role0),
            contentDescription = "Infant",
            modifier = Modifier
                .align(Alignment.BottomStart)
                .offset(y = -screenHeight / 2)
                .size(imageSize)
                .onGloballyPositioned {
                    val rect = RectF(it.localToRoot(androidx.compose.ui.geometry.Offset.Zero).x, it.localToRoot(androidx.compose.ui.geometry.Offset.Zero).y, it.localToRoot(androidx.compose.ui.geometry.Offset.Zero).x + imageSizePx, it.localToRoot(androidx.compose.ui.geometry.Offset.Zero).y + imageSizePx)
                    if (roleIconRects.size < 4) roleIconRects.add(rect) else roleIconRects[0] = rect
                }
        )

        Image(
            painter = painterResource(id = R.drawable.role1),
            contentDescription = "Child",
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(y = -screenHeight / 2)
                .size(imageSize)
                .onGloballyPositioned {
                    val rect = RectF(it.localToRoot(androidx.compose.ui.geometry.Offset.Zero).x, it.localToRoot(androidx.compose.ui.geometry.Offset.Zero).y, it.localToRoot(androidx.compose.ui.geometry.Offset.Zero).x + imageSizePx, it.localToRoot(androidx.compose.ui.geometry.Offset.Zero).y + imageSizePx)
                    if (roleIconRects.size < 4) roleIconRects.add(rect) else roleIconRects[1] = rect
                }
        )

        Image(
            painter = painterResource(id = R.drawable.role2),
            contentDescription = "Adult",
            modifier = Modifier
                .align(Alignment.BottomStart)
                .size(imageSize)
                .onGloballyPositioned {
                    val rect = RectF(it.localToRoot(androidx.compose.ui.geometry.Offset.Zero).x, it.localToRoot(androidx.compose.ui.geometry.Offset.Zero).y, it.localToRoot(androidx.compose.ui.geometry.Offset.Zero).x + imageSizePx, it.localToRoot(androidx.compose.ui.geometry.Offset.Zero).y + imageSizePx)
                    if (roleIconRects.size < 4) roleIconRects.add(rect) else roleIconRects[2] = rect
                }
        )

        Image(
            painter = painterResource(id = R.drawable.role3),
            contentDescription = "General Public",
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(imageSize)
                .onGloballyPositioned {
                    val rect = RectF(it.localToRoot(androidx.compose.ui.geometry.Offset.Zero).x, it.localToRoot(androidx.compose.ui.geometry.Offset.Zero).y, it.localToRoot(androidx.compose.ui.geometry.Offset.Zero).x + imageSizePx, it.localToRoot(androidx.compose.ui.geometry.Offset.Zero).y + imageSizePx)
                    if (roleIconRects.size < 4) roleIconRects.add(rect) else roleIconRects[3] = rect
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ExamScreenPreview() {
    ExamScreen()
}
