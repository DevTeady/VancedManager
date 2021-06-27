package com.vanced.manager.ui.components.preference

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.vanced.manager.ui.components.checkbox.ManagerCheckbox
import com.vanced.manager.ui.preferences.ManagerPreference
import kotlinx.coroutines.launch

@Composable
fun CheckboxPreference(
    preferenceTitle: String,
    preferenceDescription: String? = null,
    preference: ManagerPreference<Boolean>,
    onCheckedChange: (isChecked: Boolean) -> Unit = {}
) {
    var isChecked by preference
    val coroutineScope = rememberCoroutineScope()

    val onClick: () -> Unit = {
        coroutineScope.launch {
            isChecked = !isChecked
            onCheckedChange(isChecked)
        }
    }
    Preference(
        preferenceTitle = preferenceTitle,
        preferenceDescription = preferenceDescription,
        onClick = onClick,
        trailing = {
            ManagerCheckbox(
                isChecked = isChecked,
                onCheckedChange = { onClick() }
            )
        }
    )
}