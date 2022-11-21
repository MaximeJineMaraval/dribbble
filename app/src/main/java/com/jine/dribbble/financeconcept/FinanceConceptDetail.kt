package com.jine.dribbble.financeconcept

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jine.dribbble.R

@Composable
fun FinanceConceptDetail() {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = FinanceConceptPink)
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(FinanceConceptAlmostWhite)
    ) {
        val (headerCard, transferButton, qrcodeButton, historyTitle, historyList) = createRefs()

        Header(modifier = Modifier.constrainAs(headerCard) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            width = Dimension.fillToConstraints
            height = Dimension.wrapContent
        })

        Button(
            modifier = Modifier.constrainAs(transferButton) {
                start.linkTo(parent.start, margin = 24.dp)
                top.linkTo(headerCard.bottom)
                bottom.linkTo(headerCard.bottom)
            },
            imageId = R.drawable.financeconcept_transfer,
            text = "Transfer"
        )

        Button(
            modifier = Modifier.constrainAs(qrcodeButton) {
                end.linkTo(parent.end, margin = 24.dp)
                top.linkTo(headerCard.bottom)
                bottom.linkTo(headerCard.bottom)
            },
            imageId = R.drawable.financeconcept_qrscan,
            text = "QR Scan"
        )

        HistoryTitle(modifier = Modifier.constrainAs(historyTitle) {
            start.linkTo(parent.start, margin = 24.dp)
            end.linkTo(parent.end, margin = 24.dp)
            top.linkTo(transferButton.bottom, margin = 32.dp)
            width = Dimension.fillToConstraints
        })

        HistoryList(modifier = Modifier.constrainAs(historyList) {
            top.linkTo(historyTitle.bottom, margin = 16.dp)
            start.linkTo(parent.start, margin = 24.dp)
            end.linkTo(parent.end, margin = 24.dp)
            width = Dimension.fillToConstraints
        })
    }
}

@Composable
private fun Header(modifier: Modifier) {
    Box(
        modifier = modifier.clip(
            RoundedCornerShape(
                topStart = 0.dp,
                topEnd = 0.dp,
                bottomStart = 24.dp,
                bottomEnd = 24.dp
            )
        )
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .background(FinanceConceptGradiant)
        ) {
            val (avatar, hello, name, menuIcon, cardBackground, bankName, bankLogo, cardUserName, cardNumber, cardDate, cardHeaderDate) = createRefs()

            Image(
                modifier = Modifier
                    .size(38.dp)
                    .constrainAs(avatar) {
                        top.linkTo(parent.top, margin = 32.dp)
                        start.linkTo(parent.start, margin = 24.dp)
                    },
                painter = painterResource(R.drawable.financeconcept_avatar),
                contentDescription = null
            )
            Text(
                modifier = Modifier.constrainAs(hello) {
                    top.linkTo(avatar.top)
                    bottom.linkTo(avatar.bottom)
                    start.linkTo(avatar.end, margin = 16.dp)
                }, text = "Hello, ",
                fontFamily = FinanceConceptFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                color = FinanceConceptWhite
            )
            Text(
                modifier = Modifier.constrainAs(name) {
                    top.linkTo(hello.top)
                    bottom.linkTo(hello.bottom)
                    start.linkTo(hello.end, margin = 0.dp)
                }, text = "Sheldon",
                fontFamily = FinanceConceptFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = FinanceConceptWhite
            )
            IconButton(onClick = { }, Modifier.constrainAs(menuIcon) {
                top.linkTo(avatar.top)
                bottom.linkTo(avatar.bottom)
                end.linkTo(parent.end, margin = 24.dp)
            }) {
                Image(
                    modifier = Modifier.size(24.dp),
                    imageVector = Icons.Default.Menu,
                    contentDescription = "menu",
                    colorFilter = ColorFilter.tint(FinanceConceptWhite)
                )
            }
            Image(
                modifier = Modifier.constrainAs(cardBackground) {
                    top.linkTo(avatar.bottom, margin = 32.dp)
                    start.linkTo(parent.start, margin = 24.dp)
                    end.linkTo(parent.end, margin = 24.dp)
                    bottom.linkTo(parent.bottom, margin = 62.dp)
                    width = Dimension.fillToConstraints
                },
                contentScale = ContentScale.FillWidth,
                painter = painterResource(id = R.drawable.financeconcept_cardbackground),
                contentDescription = null
            )
            Text(
                modifier = Modifier.constrainAs(bankName) {
                    top.linkTo(cardBackground.top, margin = 22.dp)
                    start.linkTo(cardBackground.start, margin = 24.dp)
                }, text = "Capi.",
                color = FinanceConceptWhite,
                fontSize = 25.sp,
                fontFamily = FinanceConceptFontFamily,
                fontWeight = FontWeight.Bold
            )
            Image(
                modifier = Modifier
                    .size(48.dp)
                    .constrainAs(bankLogo) {
                        top.linkTo(cardBackground.top, margin = 22.dp)
                        end.linkTo(cardBackground.end, margin = 24.dp)
                    },
                painter = painterResource(R.drawable.financeconcept_cardlogo),
                contentDescription = null
            )
            Text(
                modifier = Modifier.constrainAs(cardUserName) {
                    bottom.linkTo(cardBackground.bottom, margin = 16.dp)
                    start.linkTo(cardBackground.start, margin = 24.dp)
                }, text = "Sheldon Cooper",
                color = FinanceConceptWhite,
                fontSize = 16.sp,
                fontFamily = FinanceConceptFontFamily,
                fontWeight = FontWeight.Normal
            )
            Text(
                modifier = Modifier.constrainAs(cardNumber) {
                    bottom.linkTo(cardUserName.top, margin = 16.dp)
                    start.linkTo(cardBackground.start, margin = 24.dp)
                }, text = "7812 2139 0823 1265",
                color = FinanceConceptWhite,
                fontSize = 22.sp,
                fontFamily = FinanceConceptFontFamily,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier.constrainAs(cardDate) {
                    bottom.linkTo(cardBackground.bottom, margin = 16.dp)
                    end.linkTo(cardBackground.end, margin = 24.dp)
                }, text = "05/24",
                color = FinanceConceptWhite,
                fontSize = 16.sp,
                fontFamily = FinanceConceptFontFamily,
                fontWeight = FontWeight.Normal
            )
            Text(
                modifier = Modifier.constrainAs(cardHeaderDate) {
                    bottom.linkTo(cardDate.top)
                    end.linkTo(cardBackground.end, margin = 24.dp)
                }, text = "VALID THRU",
                color = FinanceConceptWhite,
                fontSize = 9.sp,
                fontFamily = FinanceConceptFontFamily,
                fontWeight = FontWeight.Normal
            )
        }
    }
}

@Composable
private fun Button(modifier: Modifier, imageId: Int, text: String) {
    val shape = RoundedCornerShape(24.dp)
    Box(
        modifier = modifier
            .clip(shape)
            .size(width = 154.dp, height = 65.dp)
            .background(FinanceConceptWhite.copy(alpha = 0.7f))
            .clickable {  }
    ) {
        Row(
            modifier = Modifier.size(width = 154.dp, height = 65.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = imageId),
                contentDescription = null
            )
            Spacer(modifier = Modifier.size(12.dp))
            Text(
                text = text,
                color = FinanceConceptBlack,
                fontFamily = FinanceConceptFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
            )
        }
    }
}

@Composable
private fun HistoryTitle(modifier: Modifier) {
    Row(modifier = modifier) {
        Text(
            modifier = Modifier.weight(1f),
            text = "History",
            color = FinanceConceptBlack,
            fontSize = 20.sp,
            fontFamily = FinanceConceptFontFamily,
            fontWeight = FontWeight.Bold
        )
        IconButton(onClick = { }) {
            Image(
                modifier = Modifier
                    .size(24.dp),
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Show history",
                colorFilter = ColorFilter.tint(color = FinanceConceptBlack)
            )
        }
    }
}

@Composable
private fun HistoryList(modifier: Modifier) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 16.dp),
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(3) { index ->
            when (index) {
                0 -> HistoryCell(
                    name = "Netflix",
                    date = "Nov 10, 2022",
                    iconId = R.drawable.financeconcept_netflix,
                    debit = 12.5
                )
                1 -> HistoryCell(name = "Amy Fowler", date = "Nov 10, 2022", credit = 40.0)
                2 -> HistoryCell(
                    name = "Netflix",
                    date = "Oct 10, 2022",
                    iconId = R.drawable.financeconcept_netflix,
                    debit = 12.5
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun HistoryCell(
    name: String,
    date: String,
    iconId: Int? = null,
    credit: Double? = null,
    debit: Double? = null
) {
    Card(
        backgroundColor = FinanceConceptAlmostWhite,
        shape = RoundedCornerShape(24.dp),
        elevation = 0.dp,
        onClick = {}) {
        ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
            val (image, nameLabel, dateLabel, nameDateSpace, moneyLabel) = createRefs()

            // Image
            val imageModifier = Modifier
                .size(56.dp)
                .constrainAs(image) {
                    top.linkTo(parent.top, margin = 12.dp)
                    bottom.linkTo(parent.bottom, margin = 12.dp)
                    start.linkTo(parent.start, margin = 12.dp)
                }
            if (iconId != null) {
                Image(
                    modifier = imageModifier,
                    painter = painterResource(id = iconId),
                    contentDescription = null
                )
            } else {
                Box(
                    modifier = imageModifier
                        .clip(RoundedCornerShape(24.dp))
                        .background(
                            FinanceConceptGradiant
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = name.split(" ").map { it.first().toString().toUpperCase() }
                            .joinToString(separator = ""),
                        color = FinanceConceptWhite,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FinanceConceptFontFamily,
                        fontSize = 20.sp
                    )
                }
            }

            // Labels
            Text(
                modifier = Modifier.constrainAs(nameLabel) {
                    start.linkTo(image.end, margin = 16.dp)
                    end.linkTo(moneyLabel.start, margin = 16.dp)
                    width = Dimension.fillToConstraints
                },
                text = name,
                color = FinanceConceptBlack,
                fontSize = 16.sp,
                fontFamily = FinanceConceptFontFamily,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier
                .size(8.dp)
                .constrainAs(nameDateSpace) {})
            Text(
                modifier = Modifier.constrainAs(dateLabel) {
                    start.linkTo(nameLabel.start)
                    end.linkTo(nameLabel.end)
                    width = Dimension.fillToConstraints
                },
                text = date,
                color = FinanceConceptGrey,
                fontSize = 14.sp,
                fontFamily = FinanceConceptFontFamily,
                fontWeight = FontWeight.Normal
            )
            createVerticalChain(nameLabel, nameDateSpace, dateLabel, chainStyle = ChainStyle.Packed)

            //Credit / Debit
            val moneyLabelModifier = Modifier.constrainAs(moneyLabel) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end, margin = 12.dp)
            }
            if (credit != null) {
                Text(
                    modifier = moneyLabelModifier,
                    text = "+$${String.format("%.2f", credit)}",
                    color = FinanceConceptGreen,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FinanceConceptFontFamily
                )
            }
            if (debit != null) {
                Text(
                    modifier = moneyLabelModifier,
                    text = "-$${String.format("%.2f", debit)}",
                    color = FinanceConceptRed,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FinanceConceptFontFamily
                )
            }
        }
    }
}

@Preview
@Composable
private fun HistoryCellPreview1() {
    HistoryCell(
        name = "Maxime Maraval",
        date = "Nov 10, 2022",
        credit = 40.00
    )
}

@Preview
@Composable
private fun HistoryCellPreview2() {
    HistoryCell(
        name = "Netflix",
        iconId = R.drawable.financeconcept_netflix,
        date = "Nov 10, 2022",
        debit = 12.50
    )
}

@Preview
@Composable
private fun ScreenPreview() {
    FinanceConceptDetail()
}

@Preview
@Composable
private fun HeaderPreview() {
    Header(Modifier)
}

@Preview
@Composable
private fun ButtonPreview() {
    Button(Modifier, R.drawable.financeconcept_transfer, "Transfer")
}

@Preview(showBackground = true)
@Composable
private fun HistoryTitlePreview() {
    HistoryTitle(Modifier)
}