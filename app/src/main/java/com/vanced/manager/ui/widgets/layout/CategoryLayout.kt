package com.vanced.manager.ui.widgets.layout

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.vanced.manager.ui.utils.defaultContentPaddingHorizontal
import com.vanced.manager.ui.utils.defaultContentPaddingVertical
import com.vanced.manager.ui.components.text.CategoryTitleText

@Composable
fun CategoryLayout(
    categoryName: String,
    contentPaddingHorizontal: Dp = defaultContentPaddingHorizontal,
    categoryNameSpacing: Dp = defaultContentPaddingVertical,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(categoryNameSpacing),
    ) {
        CategoryTitleText(text = categoryName)
        Box(
            modifier = Modifier.padding(horizontal = contentPaddingHorizontal)
        ) {
            content()
        }
    }
}