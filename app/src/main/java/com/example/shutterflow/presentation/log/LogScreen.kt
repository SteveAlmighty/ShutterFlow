package com.example.shutterflow.presentation.log

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shutterflow.R
import com.example.shutterflow.presentation.profile.components.AlbumCard
import com.example.shutterflow.presentation.profile.sampleAlbumDataList
import com.example.shutterflow.ui.theme.TealBlue
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlin.random.Random


@Composable
fun LogScreen(){

    val sampleCalendarData: List<Pair<Date, Boolean>> =
        generateCalendarDataForMonth(year, month)

    val year = LocalDate.now().year
    val monthValue = LocalDate.now().monthValue-1

    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(TealBlue)
                    .padding(horizontal = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton (
                    onClick = {},
                    modifier = Modifier
                        .padding(start = 7.dp),

                    ){
                    Icon(
                        imageVector =  Icons.Filled.ArrowBack,
                        contentDescription = "navigate back"
                    )
                }

                Text(
                    text = "Calendar",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold)

                IconButton (
                    onClick = {},
                    modifier = Modifier
                        .padding(end = 7.dp),

                    ){
                    Icon(
                        imageVector =  Icons.Filled.Menu,
                        contentDescription = "Menu"
                    )
                }

            }

            Spacer(modifier = Modifier.height(20.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CalendarView(
                    date = sampleCalendarData, // Pass the generated list
                    displayNext = true,
                    displayPrev = true,
                    onClickNext = {},
                    onClickPrev = {},
                    onClick = {},
                    startFromSunday = isMonthStartingWithSundayCalendar(year = year, month = monthValue),
                    month = Date()
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "My Album",
                    color = Color.DarkGray,
                    modifier = Modifier.padding(start = 10.dp),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "See All",
                    modifier = Modifier.padding(end = 10.dp),
                    fontSize = 12.sp,
                    color = TealBlue
                )
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                userScrollEnabled = false // only show top 6
            ) {
                items(6) { index ->
                    AlbumCard(item = sampleAlbumDataList[index])
                }
            }

        }
    }
}

private fun isMonthStartingWithSundayCalendar(year: Int, month: Int): Boolean {
    // Month in Calendar is 0-indexed (0 for January, 11 for December)
    if (month < 0 || month > 11) {
        throw IllegalArgumentException("Month must be between 0 and 11 for Calendar API. Received: $month")
    }

    val calendar = Calendar.getInstance()
    // Clear existing fields to avoid unexpected behavior from previous state
    calendar.clear()
    // Set the date to the first day of the specified month and year
    calendar.set(year, month, 1)

    // Get the day of the week for the first day of the month
    val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)

    // Check if the day of the week is Sunday
    // Calendar.SUNDAY is a constant (typically 1)
    return dayOfWeek == Calendar.SUNDAY
}


private fun Date.formatToCalendarDay(): String = SimpleDateFormat("d", Locale.getDefault()).format(this)

@Composable
private fun CalendarCell(
    date: Date,
    signal: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val text = date.formatToCalendarDay()
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .width(6.dp)
            .height(7.dp)
            .padding(bottom = 1.dp)
            .clip(RoundedCornerShape(CornerSize(8.dp)))
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Column (verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
            Text(
                text = text,
                color = Color.DarkGray,

            )
            if (signal) {
                Image(
                    painter = painterResource(id = R.drawable.bluecamera),
                    contentDescription = "Camera",
                    modifier = Modifier
                        .size(12.dp)

                )
            }
        }
    }
}

private fun Int.getDayOfWeek3Letters(): String? = Calendar.getInstance().apply {
    set(Calendar.DAY_OF_WEEK, this@getDayOfWeek3Letters)
}.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault())

@Composable
private fun WeekdayCell(weekday: Int, modifier: Modifier = Modifier) {
    val text = weekday.getDayOfWeek3Letters()
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .fillMaxSize()
    ) {
        Text(
            text = text.orEmpty(),
            color = Color.DarkGray,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
private fun CalendarGrid(
    date: List<Pair<Date, Boolean>>,
    onClick: (Date) -> Unit,
    startFromSunday: Boolean,
    modifier: Modifier = Modifier,
) {
    val weekdayFirstDay = date.first().first.formatToCalendarDay().toInt()
    val weekdays = getWeekDays(startFromSunday)
    CalendarCustomLayout(modifier = modifier) {
        weekdays.forEach {
            WeekdayCell(weekday = it)
        }
        // Adds Spacers to align the first day of the month to the correct weekday
        repeat(if (!startFromSunday) weekdayFirstDay - 2 else weekdayFirstDay - 1) {
            Spacer(modifier = Modifier)
        }
        date.forEach {
            CalendarCell(date = it.first, signal = it.second, onClick = { onClick(it.first) })
        }
    }
}

fun getWeekDays(startFromSunday: Boolean): List<Int> {
    val lista = (1..7).toList()
    return (if (startFromSunday) lista else lista.drop(1) + lista.take(1)).toList()
}


@Composable
private fun CalendarCustomLayout(
    modifier: Modifier = Modifier,
    horizontalGapDp: Dp = 2.dp,
    verticalGapDp: Dp = 2.dp,
    content: @Composable () -> Unit,
) {
    val horizontalGap = with(LocalDensity.current) {
        horizontalGapDp.roundToPx()
    }
    val verticalGap = with(LocalDensity.current) {
        verticalGapDp.roundToPx()
    }
    Layout(
        content = content,
        modifier = modifier,
    ) { measurables, constraints ->
        val totalWidthWithoutGap = constraints.maxWidth - (horizontalGap * 6)
        val singleWidth = totalWidthWithoutGap / 7

        val xPos: MutableList<Int> = mutableListOf()
        val yPos: MutableList<Int> = mutableListOf()
        var currentX = 0
        var currentY = 0
        measurables.forEach { _ ->
            xPos.add(currentX)
            yPos.add(currentY)
            if (currentX + singleWidth + horizontalGap > totalWidthWithoutGap) {
                currentX = 0
                currentY += singleWidth + verticalGap
            } else {
                currentX += singleWidth + horizontalGap
            }
        }

        val placeables: List<Placeable> = measurables.map { measurable ->
            measurable.measure(constraints.copy(maxHeight = singleWidth, maxWidth = singleWidth))
        }

        layout(
            width = constraints.maxWidth,
            height = currentY + singleWidth + verticalGap,
        ) {
            placeables.forEachIndexed { index, placeable ->
                placeable.placeRelative(
                    x = xPos[index],
                    y = yPos[index],
                )
            }
        }
    }
}


@Composable
fun CalendarView(
    month: Date,
    date: List<Pair<Date, Boolean>>?,
    displayNext: Boolean,
    displayPrev: Boolean,
    onClickNext: () -> Unit,
    onClickPrev: () -> Unit,
    onClick: (Date) -> Unit,
    startFromSunday: Boolean,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Box(modifier = Modifier.fillMaxWidth()) {
            if (displayPrev)
                IconButton (
                    onClick = onClickPrev,
                    modifier = Modifier.align(Alignment.CenterStart),

                ){
                    Icon(
                        imageVector =  Icons.Filled.KeyboardArrowLeft,
                        contentDescription = "navigate to previous month"
                    )
                }
            if (displayNext)
                IconButton (
                    onClick = onClickNext,
                    modifier = Modifier.align(Alignment.CenterEnd),
                ){
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowRight,
                        contentDescription = "navigate to next month"
                    )

                }
            Text(
                text = month.formatToMonthString(),
                style = typography.headlineMedium,
                color = Color.DarkGray,
                modifier = Modifier.align(Alignment.Center),
            )
        }
        Spacer(modifier = Modifier.size(16.dp))
        if (!date.isNullOrEmpty()) {
            CalendarGrid(
                date = date,
                onClick = onClick,
                startFromSunday = startFromSunday,
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(horizontal = 16.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}

fun Date.formatToMonthString(): String = SimpleDateFormat("MMMM", Locale.getDefault()).format(this)




fun generateCalendarDataForMonth(year: Int, month: Int): List<Pair<Date, Boolean>> {
    val calendarData = mutableListOf<Pair<Date, Boolean>>()
    val calendar = Calendar.getInstance()
    calendar.set(year, month - 1, 1) // Set to the first day of the specified month (month is 0-indexed)

    val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

    for (day in 1..daysInMonth) {
        calendar.set(Calendar.DAY_OF_MONTH, day)
        val currentDate = calendar.time
        val signal = Random.nextBoolean() // Random boolean for the signal
        calendarData.add(Pair(currentDate, signal))
    }
    return calendarData
}

// Usage:
val currentCalendar = Calendar.getInstance()
val year = currentCalendar.get(Calendar.YEAR)
val month = currentCalendar.get(Calendar.MONTH) + 1 // Calendar.MONTH is 0-indexed

val monthDataInstance: List<Pair<Date, Boolean>>? = generateCalendarDataForMonth(year, month)

//to get sample data
//val currentCalendar = Calendar.getInstance()
//val year = currentCalendar.get(Calendar.YEAR)
//val month = currentCalendar.get(Calendar.MONTH) + 1 // For 1-indexed month
//
//// Example: Generate data for the current month
//val sampleCalendarData: List<Pair<Date, Boolean>> =
//    generateCalendarDataForMonth(year, month)



 @Preview(showBackground = true)
 @Composable
 fun LearnTabPrev() {
     LogScreen()
 }