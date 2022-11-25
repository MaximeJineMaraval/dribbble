package com.jine.dribbble.egarden

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.jine.dribbble.R

// TODO: exporter les SVGS plutôt que les PNGs
val EGardenWhite = Color(0xFFFFFFFF)
val EGardenBlack = Color(0xFF50565A)
val EGardenGreyStrong = Color(0xFF77808D)
val EGardenGreyLight = Color(0xFFBDBCBC)
val EGardenGreyExtraLight = Color(0xFFF8F8F8)
val EGardenGreen = Color(0xFF3DADA9)
val EGardenGradient =
    Brush.linearGradient(colors = listOf(EGardenGreen, Color(0xFF12706D)))
val EGardenMulishFontFamily = FontFamily(
    Font(R.font.mulish_semibold, FontWeight.SemiBold)
)
val EGardenRasaFontFamily = FontFamily(
    Font(R.font.rasa_semibold, FontWeight.SemiBold)
)
val EGardenNunitoFontFamily = FontFamily(
    Font(R.font.nunito_semibold, FontWeight.SemiBold),
    Font(R.font.nunito_bold, FontWeight.Bold),
    Font(R.font.nunito_extrabold, FontWeight.ExtraBold),
    Font(R.font.nunito_black, FontWeight.Black)
)

@Composable
fun EGardenScreen() {
    var screenIndexSelected by remember { mutableStateOf(0) }
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(EGardenWhite)
    ) {
        val (screen, bottomBar) = createRefs()

        val screenModifier = Modifier.constrainAs(screen) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(bottomBar.top)
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
        }
        when (screenIndexSelected) {
            0 -> EGardenStore(modifier = screenModifier)
            1 -> EGardenDashboard(modifier = screenModifier)
        }
        EGardenBottomBar(
            modifier = Modifier.constrainAs(bottomBar) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }, screenIndexSelected = screenIndexSelected,
            onSelectScreen = { index ->
                screenIndexSelected = index
            })
    }
}

@Composable
private fun EGardenBottomBar(
    modifier: Modifier = Modifier,
    screenIndexSelected: Int,
    onSelectScreen: (index: Int) -> Unit
) {
    val context = LocalContext.current
    BottomNavigation(
        modifier = modifier.height(72.dp),
        backgroundColor = EGardenWhite,
        elevation = 12.dp
    ) {
        EGardenBottomBarItem(
            selected = screenIndexSelected == 0,
            onClick = { onSelectScreen(0) },
            iconResId = R.drawable.egarden_nav_leaf,
            label = "Garden"
        )
        EGardenBottomBarItem(
            selected = screenIndexSelected == 1,
            onClick = { onSelectScreen(1) },
            iconResId = R.drawable.egarden_nav_cart,
            label = "Store"
        )
        EGardenBottomBarItem(
            selected = screenIndexSelected == 2,
            onClick = {
                showEGardenNotImplementedToast(context)
            },
            iconResId = R.drawable.egarden_nav_settings,
            label = "Settings"
        )
        EGardenBottomBarItem(
            selected = screenIndexSelected == 3,
            onClick = {
                showEGardenNotImplementedToast(context)
            },
            iconResId = R.drawable.egarden_nav_user,
            label = "Profile"
        )
    }
}

fun showEGardenNotImplementedToast(context: Context) {
    Toast.makeText(context, "Not implemented", Toast.LENGTH_SHORT).show()
}

@Composable
private fun RowScope.EGardenBottomBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    iconResId: Int,
    label: String
) {
    BottomNavigationItem(
        selected = selected,
        onClick = onClick,
        selectedContentColor = EGardenGreen,
        unselectedContentColor = EGardenGreyLight,
        icon = {
            Icon(
                modifier = Modifier
                    .size(32.dp)
                    .padding(4.dp),
                painter = painterResource(id = iconResId),
                contentDescription = label,
            )
        },
        label = {
            Text(
                text = label,
                fontFamily = EGardenMulishFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp,
                color = if (selected) {
                    EGardenGreen
                } else {
                    EGardenWhite
                }
            )
        }
    )
}

@Composable
fun EGardenTitle(modifier: Modifier = Modifier, title: String) {
    Text(
        modifier = modifier,
        text = title,
        color = EGardenBlack,
        fontFamily = EGardenRasaFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 40.sp
    )
}

@Preview
@Composable
private fun EGardenTitlePreview() {
    EGardenTitle(title = "Titre")
}

@Preview
@Composable
private fun EGardenBottomBarPreview() {
    EGardenBottomBar(screenIndexSelected = 0, onSelectScreen = {})
}

@Preview
@Composable
private fun EGardenScreenPreview() {
    EGardenScreen()
}