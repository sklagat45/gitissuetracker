package co.ke.srklagat.gitissuetracker

import co.ke.srklagat.gitissuetracker.utils.DateUtil
import org.junit.Test
import org.junit.Assert.*

class ExampleUnitTest {

    @Test
    fun testDateFormatting() {
        val inputDate = "2024-10-04T00:00:00Z"
        val expectedFormattedDate = "04 Oct 2024, 12:00 AM"

        val actualFormattedDate = DateUtil(inputDate)

        assertEquals(expectedFormattedDate, actualFormattedDate)
    }
}
