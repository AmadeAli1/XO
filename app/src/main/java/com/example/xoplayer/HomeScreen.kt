package com.example.xoplayer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.xoplayer.ui.theme.*

@Composable
fun Header() {
    Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
        Image(painter = painterResource(id = R.drawable.ic_gametype_x), contentDescription = null)
        Image(painter = painterResource(id = R.drawable.ic_gametype_o), contentDescription = null)
    }
}

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    horizontalPadding: Dp,
    color: Color, text: String,
    onClick: () -> Unit,
) {
    Button(
        onClick = { onClick() },
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = horizontalPadding),
        colors = ButtonDefaults.buttonColors(backgroundColor = color)
    ) {
        Text(text = text, textAlign = TextAlign.Center)
    }
}

@Composable
fun MainCard(horizontalPadding: Dp) {
    val textPadding = 8.dp
    Card(
        backgroundColor = firstCardColor,
        elevation = 5.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = horizontalPadding),
        shape = RoundedCornerShape(5)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(12.dp)
        ) {
            Row(modifier = Modifier.padding(all = textPadding)) {
                Text(
                    text = "PICK PLAYER 1'S MARK",
                    textAlign = TextAlign.Justify,
                    color = color200
                )
            }
            Row(
                modifier = Modifier
                    .background(color = mainColor)
                    .padding(10.dp)
                    .height(50.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_gametype_x),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(color = color200),
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp)
                )
                Button(
                    enabled = false,
                    onClick = {},
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = color200)
                ) {
                    IconButton(onClick = {}) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_gametype_o),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(color = mainColor),
                        )
                    }
                }
            }
            Text(
                text = "REMEMBER: X GOES FIRST",
                color = color200,
                modifier = Modifier.padding(all = textPadding)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
//@Preview(showBackground = true, widthDp = 640, heightDp = 480)
fun Screen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = mainColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val horizontalParentPadding: Dp = 16.dp
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(bottom = 8.dp)
        ) {
            Header()
        }
        MainCard(horizontalPadding = horizontalParentPadding)
        Spacer(modifier = Modifier.padding(vertical = 16.dp))
        CustomButton(
            modifier = Modifier.height(40.dp),
            horizontalPadding = horizontalParentPadding,
            color = buttonColor1,
            text = "NEW GAME (VS CPU)", onClick = {}
        )
        Spacer(modifier = Modifier.padding(vertical = 16.dp))
        CustomButton(
            modifier = Modifier.height(40.dp),
            horizontalPadding = horizontalParentPadding,
            color = blueColor,
            text = "NEW GAME (VS FRIEND)", onClick = {}
        )
    }
}