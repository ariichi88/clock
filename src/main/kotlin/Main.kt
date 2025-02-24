import androidx.compose.runtime.*
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import kotlinx.coroutines.delay
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter

fun getNowTime(): String {
    val now = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
    return now.format(formatter)
}

@Composable
@Preview
fun app() {
    var time by remember { mutableStateOf(getNowTime()) }
    MaterialTheme {
        Box(
            modifier = Modifier.fillMaxSize()) {
            val image: Painter = painterResource("bg.jpg")
            Image(
                painter = image,
                contentDescription = "bg",
                contentScale = ContentScale.Crop
            )
            Text(
                text = time,
                modifier = Modifier.align(Alignment.Center),
                fontSize = 32.sp,
                color = Color.Black
            )
        }
    }
    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            time = getNowTime()
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication,
        state = WindowState(width = 300.dp, height = 150.dp),
        title = "Digital Clock",
        alwaysOnTop = true,
    ) {
        app()
    }
}
