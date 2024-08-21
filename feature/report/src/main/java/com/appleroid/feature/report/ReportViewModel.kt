package com.appleroid.feature.report

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.appleroid.model.Report
import com.appleroid.model.VoteStatistics
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReportViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    fun getReportList(): List<Report> {
        return listOf(
            Report(id = 1, title = "내용이 선정적이에요 (19금)"),
            Report(id = 2, title = "내용이 폭력적이에요"),
            Report(id = 3, title = "타인을 비방/조롱하고 있어요"),
            Report(id = 4, title = "광고를 하고 있어요"),
            Report(id = 5, title = "타인을 사칭하고 있어요 (도용)"),
            Report(id = 6, title = "게시글/댓글을 도배하고 있어요"),
            Report(id = 7, title = "부적절한 닉네임을 사용하고 있어요"),
            Report(id = 8, title = "기타")
        )
    }
}