package tw.edu.pu.csim.tcyang.s1131221

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ExamScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow),
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
            Text(text = "作者:資管2B謝雨安")
            Text(text = "螢幕大小:1080.0*1920.0")
            Text(text = "成績:0分")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExamScreenPreview() {
    ExamScreen()
}
