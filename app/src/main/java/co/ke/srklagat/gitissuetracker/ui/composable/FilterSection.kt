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

// Define the custom color
val CustomBlue = Color(0xFF4E7B9C) // Using the color hex code

@Composable
fun FilterSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Date Dropdown
        DropdownWithIcon(
            label = "Date",
            icon = Icons.Default.DateRange,
            items = listOf("Today", "This Week", "This Month", "This Year"),
            onItemSelected = { selectedDate -> /* Handle date selection */ },
            modifier = Modifier.width(80.dp) // Limit the width of the dropdown
        )

        // Filter by Dropdown
        DropdownWithIcon(
            label = "Filter by",
            icon = Icons.Default.ArrowDropDown,
            items = listOf("Open", "Closed", "In Progress"),
            onItemSelected = { selectedFilter -> /* Handle filter selection */ },
            modifier = Modifier.width(80.dp)
        )

        Button(
            onClick = { /* TODO */ },
            modifier = Modifier
                .width(80.dp)
//                .height(45.dp)
                .padding(horizontal = 4.dp),
            colors = ButtonDefaults.buttonColors(Color.White),
            elevation = ButtonDefaults.elevatedButtonElevation(0.dp)
        ) {
            Text("iOS", color = CustomBlue)
        }

        Button(
            onClick = { /* TODO */ },
            modifier = Modifier
                .width(80.dp)
//                .height(45.dp)
                .padding(horizontal = 4.dp),
            colors = ButtonDefaults.buttonColors(Color.White),
            elevation = ButtonDefaults.elevatedButtonElevation(0.dp)
        ) {
            Text("Xcode", color = CustomBlue)
        }
    }
}

@Composable
fun DropdownWithIcon(
    label: String,
    icon: ImageVector,
    items: List<String>,
    onItemSelected: (String) -> Unit,
    modifier: Modifier = Modifier // Add modifier parameter
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf(items[0]) } // Default to the first item

    Box(modifier = modifier.padding(horizontal = 2.dp)) { // Use the modifier here
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
            Text(text = selectedItem, color = CustomBlue, fontSize = 9.sp) // Change text color to custom blue
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(Color.White, shape = MaterialTheme.shapes.small.copy(all = CornerSize(6.dp)))
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = {
                        Text(text = item, fontSize = 9.sp, color = CustomBlue) // Change text color to custom blue
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
