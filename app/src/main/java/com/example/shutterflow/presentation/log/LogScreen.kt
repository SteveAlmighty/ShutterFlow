
import android.app.*
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.work.*
import com.example.shutterflow.data.reminderdb.ScheduledShoot
import com.example.shutterflow.presentation.log.ShootViewModel
import com.example.shutterflow.ui.theme.LightTeal
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

@Composable
fun ShootPlannerScreen(viewModel: ShootViewModel = viewModel()) {
    val context = LocalContext.current
    var title by remember { mutableStateOf("") }
    var tag by remember { mutableStateOf("") }
    var calendar by remember { mutableStateOf(Calendar.getInstance()) }
    var repeatWeekly by remember { mutableStateOf(false) }
    val dateFormat = remember { SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()) }
    var showDatePicker by remember { mutableStateOf(false) }
    var showTimePicker by remember { mutableStateOf(false) }
    var editingShoot by remember { mutableStateOf<ScheduledShoot?>(null) }

    val shoots = viewModel.shoots.collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Plan a New Photo Shoot", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Shoot Title") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = tag,
            onValueChange = { tag = it },
            label = { Text("Tag (e.g., Portrait, Night)") },
            modifier = Modifier.fillMaxWidth()
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = repeatWeekly,
                onCheckedChange = { repeatWeekly = it },
                colors = CheckboxDefaults.colors(
                    checkedColor = LightTeal,
                    uncheckedColor = LightTeal
                )
            )
            Text("Repeat Weekly")
        }

        Button(
            onClick = { showDatePicker = true },
            colors = ButtonDefaults.buttonColors(
                containerColor = LightTeal,
                contentColor = Color.White
            )
            ) {
            Text("Select Date")
        }

        Button(
            onClick = { showTimePicker = true },
            colors = ButtonDefaults.buttonColors(
            containerColor = LightTeal,
            contentColor = Color.White
        )) {
            Text("Select Time")
        }

        Text("Selected: ${dateFormat.format(calendar.time)}")

        Button(
            onClick = {
                if (title.isNotBlank()) {
                    val shoot = ScheduledShoot(
                        id = editingShoot?.id ?: 0,
                        title = title,
                        timeMillis = calendar.timeInMillis,
                        repeatWeekly = repeatWeekly,
                        tag = tag
                    )

                    if (editingShoot != null) {
                        viewModel.updateShoot(shoot)
                    } else {
                        viewModel.addShoot(shoot)
                        val delay = calendar.timeInMillis - System.currentTimeMillis()
                        if (delay > 0) {
                            scheduleNotification(context, title, delay, repeatWeekly)
                            Toast.makeText(context, "Reminder Scheduled", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "Please select a future time", Toast.LENGTH_SHORT).show()
                        }
                    }

                    title = ""
                    tag = ""
                    editingShoot = null
                }
            },
            modifier = Modifier.align(Alignment.End),
            colors = ButtonDefaults.buttonColors(
                containerColor = LightTeal,
                contentColor = Color.White
            )
        ) {
            Text(if (editingShoot != null) "Update Shoot" else "Schedule Notification")
        }

        Divider(modifier = Modifier.padding(vertical = 8.dp))
        Text("Scheduled Shoots:", style = MaterialTheme.typography.titleMedium)

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(shoots.value.size) { shoot ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray.copy(alpha = 0.2f))
                        .padding(8.dp)
                        .clickable {
                            title = shoots.value[shoot].title
                            tag = shoots.value[shoot].tag
                            calendar = Calendar.getInstance().apply { timeInMillis = shoots.value[shoot].timeMillis }
                            repeatWeekly = shoots.value[shoot].repeatWeekly
                            editingShoot = shoots.value[shoot]
                        },
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(text = shoots.value[shoot].title)
                        Text(text = dateFormat.format(Date(shoots.value[shoot].timeMillis)), style = MaterialTheme.typography.bodySmall)
                        if (shoots.value[shoot].repeatWeekly) Text("Repeats Weekly", style = MaterialTheme.typography.bodySmall)
                        if (shoots.value[shoot].tag.isNotBlank()) Text("Tag: ${shoots.value[shoot].tag}", style = MaterialTheme.typography.bodySmall)
                    }
                    IconButton(onClick = { viewModel.deleteShoot(shoots.value[shoot]) }) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete")
                    }
                }
            }
        }
    }

    if (showDatePicker) {
        DatePickerDialog(
            context,
            { _, year, month, day ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, day)
                showDatePicker = false
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    if (showTimePicker) {
        TimePickerDialog(
            context,
            { _, hour, minute ->
                calendar.set(Calendar.HOUR_OF_DAY, hour)
                calendar.set(Calendar.MINUTE, minute)
                showTimePicker = false
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        ).show()
    }
}


fun scheduleNotification(context: Context, title: String, delayMillis: Long, repeatWeekly: Boolean) {
    val data = Data.Builder()
        .putString("title", title)
        .putBoolean("repeat", repeatWeekly)
        .build()

    val requestBuilder = OneTimeWorkRequestBuilder<ShootReminderWorker>()
        .setInitialDelay(delayMillis, TimeUnit.MILLISECONDS)
        .setInputData(data)

    WorkManager.getInstance(context).enqueue(requestBuilder.build())
}

class ShootReminderWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    override fun doWork(): Result {
        val title = inputData.getString("title") ?: return Result.failure()
        val channelId = "shoot_reminder_channel"
        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channel = NotificationChannel(
            channelId,
            "Shoot Reminders",
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = "Notifications for scheduled photo shoots"
        }
        notificationManager.createNotificationChannel(channel)

        val notification = NotificationCompat.Builder(applicationContext, channelId)
            .setSmallIcon(android.R.drawable.ic_menu_camera)
            .setContentTitle("Photo Shoot Reminder")
            .setContentText("Don't forget your planned shoot: $title")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setSound(android.provider.Settings.System.DEFAULT_NOTIFICATION_URI)
            .setAutoCancel(true)
            .build()

        return try {
            if (ContextCompat.checkSelfPermission(applicationContext, android.Manifest.permission.POST_NOTIFICATIONS)
                == PackageManager.PERMISSION_GRANTED || Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU
            ) {
                NotificationManagerCompat.from(applicationContext).notify(title.hashCode(), notification)
            }
            Result.success()
        } catch (e: SecurityException) {
            Result.failure()
        }
    }
}
