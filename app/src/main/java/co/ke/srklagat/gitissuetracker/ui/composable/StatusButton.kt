package co.ke.srklagat.gitissuetracker.ui.composable

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape

@Composable
fun StatusButton(
    text: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color(0xFF2452C9),
    contentColor: Color = Color.White,
    shape: Shape = RoundedCornerShape(percent = 50)
) {
    Button(
        onClick = {},
        modifier = modifier.padding(horizontal = 2.dp, vertical = 2.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = contentColor

        ),
        shape = shape,
        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 2.dp)
    ) {
        Text(text = text,  fontSize = 11.sp , color = Color.White, style = MaterialTheme.typography.bodySmall)
    }
}