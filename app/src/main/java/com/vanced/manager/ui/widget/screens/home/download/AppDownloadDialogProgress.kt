package com.vanced.manager.ui.widget.screens.home.download

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vanced.manager.R
import com.vanced.manager.ui.component.progressindicator.ManagerProgressIndicator
import com.vanced.manager.ui.resources.managerString

@Composable
fun AppDownloadDialogProgress(
    progress: Float,
    file: String,
    installing: Boolean
) {
    when (installing) {
        true -> ManagerProgressIndicator()
        false -> ManagerProgressIndicator(progress = progress / 100f)
    }
    val animatedProgress by animateIntAsState(targetValue = progress.toInt())
    Row {
        Text(
            modifier = Modifier
                .weight(1f)
                .wrapContentWidth(Alignment.Start),
            text = managerString(
                stringId = R.string.app_download_dialog_downloading_file,
                file
            )
        )
        Text(
            modifier = Modifier
                .padding(start = 4.dp)
                .wrapContentWidth(Alignment.End),
            text = "$animatedProgress%"
        )
    }
}