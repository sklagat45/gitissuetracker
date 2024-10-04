package co.ke.srklagat.gitissuetracker.ui.composable

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FilterSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = { /* TODO */ },
            modifier = Modifier.weight(1f).padding(horizontal = 4.dp),
            colors = ButtonDefaults.buttonColors( Color.White),
            elevation = ButtonDefaults.elevatedButtonElevation(0.dp)
        ) {
            Text("Date", color = Color.Black)
        }

        Button(
            onClick = { /* TODO */ },
            modifier = Modifier.weight(1f).padding(horizontal = 4.dp),
            colors = ButtonDefaults.buttonColors(Color.White),
            elevation = ButtonDefaults.elevatedButtonElevation(0.dp)
        ) {
            Text("Filter by", color = Color.Black)
        }

        Button(
            onClick = { /* TODO */ },
            modifier = Modifier.weight(1f).padding(horizontal = 4.dp),
            colors = ButtonDefaults.buttonColors(Color.White),
            elevation = ButtonDefaults.elevatedButtonElevation(0.dp)
        ) {
            Text("iOS", color = Color.Black)
        }

        Button(
            onClick = { /* TODO */ },
            modifier = Modifier.weight(1f).padding(horizontal = 4.dp),
            colors = ButtonDefaults.buttonColors(Color.White),
            elevation = ButtonDefaults.elevatedButtonElevation(0.dp)
        ) {
            Text("Xcode", color = Color.Black)
        }
    }
}