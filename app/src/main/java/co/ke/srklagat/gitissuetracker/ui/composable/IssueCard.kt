package co.ke.srklagat.gitissuetracker.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MailOutline
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.ke.srklagat.gitissuetracker.data.local.models.IssueEntity
import co.ke.srklagat.gitissuetracker.utils.DateUtil

val CardBackgroundColor = Color(0xFFF7F8FA)

@Composable
fun IssueCard(
    issue: IssueEntity,
    modifier: Modifier = Modifier
) {
    val createdAtFormatted = DateUtil(issue.createdAt)

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(3.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Opened at $createdAtFormatted",
                    color = Color.Gray,
                    fontWeight = FontWeight.Light,
                    fontSize = 12.sp
                )
                StatusButton(
                    text = issue.status
                )
            }

            Column {
                Text(
                    text = issue.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = issue.description,fontSize = 11.sp
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Rounded.Person, contentDescription = "")
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = issue.assignedTo, fontSize = 12.sp
                    )

                }

                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Rounded.MailOutline, contentDescription = "")
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = "2 comments", fontSize = 12.sp
                    )
                }
            }
        }
    }
}

