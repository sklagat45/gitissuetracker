package co.ke.srklagat.gitissuetracker.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val CustomBlue = Color(0xFF4E7B9C)

@Composable
fun FilterSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        DropdownWithIcon(
            label = "Date",
            icon = Icons.Default.DateRange,
            items = listOf("Today", "This Week", "This Month", "This Year"),
            onItemSelected = { selectedDate -> },
            modifier = Modifier.width(80.dp)
        )

        DropdownWithIcon(
            label = "Filter by",
            icon = Icons.Default.ArrowDropDown,
            items = listOf("Open", "Closed", "In Progress"),
            onItemSelected = { selectedFilter -> },
            modifier = Modifier.width(80.dp)
        )

        Button(
            onClick = { /* TODO */ },
            modifier = Modifier
                .width(80.dp)
                .padding(horizontal = 4.dp),
            colors = ButtonDefaults.buttonColors(Color.White),
            elevation = ButtonDefaults.elevatedButtonElevation(0.dp)
        ) {
            Text("iOS", color = CustomBlue, fontSize = 14.sp)
        }

        Button(
            onClick = { /* TODO */ },
            modifier = Modifier
                .width(80.dp)
                .padding(horizontal = 4.dp),
            colors = ButtonDefaults.buttonColors(Color.White),
            elevation = ButtonDefaults.elevatedButtonElevation(0.dp)
        ) {
            Text("Xcode", color = CustomBlue, fontSize = 14.sp)
        }
    }
}

@Composable
fun DropdownWithIcon(
    label: String,
    icon: ImageVector,
    items: List<String>,
    onItemSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf(items[0]) }

    Box(modifier = modifier.padding(horizontal = 2.dp)) {
        Row(
            modifier = Modifier
                .clickable(onClick = { expanded = true })
                .padding(3.dp)
                .background(Color.White, shape = MaterialTheme.shapes.small.copy(all = CornerSize(6.dp))) // Set corner radius
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = icon, contentDescription = null, modifier = Modifier.size(24.dp), tint = CustomBlue) // Change icon color to custom blue
            Spacer(modifier = Modifier.width(3.dp))
            Text(text = selectedItem, color = CustomBlue, fontSize = 14.sp)
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(Color.White, shape = MaterialTheme.shapes.small.copy(all = CornerSize(6.dp)))
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = {
                        Text(text = item, fontSize = 12.sp, color = CustomBlue) // Set font size to 12 sp
                    },
                    onClick = {
                        selectedItem = item
                        expanded = false
                        onItemSelected(item)
                    }
                )
            }
        }
    }
}
