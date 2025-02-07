package com.penguins.educationmultiplatform.android.testsScreen.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.MyApplicationTheme
import com.penguins.educationmultiplatform.android.commonViews.AppBackToolbar
import com.penguins.educationmultiplatform.android.data.model.textQuestion1
import com.penguins.educationmultiplatform.android.testsScreen.common.TextAnswerView
import com.penguins.educationmultiplatform.android.testsScreen.data.EducationTest
import com.penguins.educationmultiplatform.android.ui.neutral0
import com.penguins.educationmultiplatform.android.ui.neutral900Size32Weight700Style

@Composable
fun TextTestView(
    test: EducationTest.TextTest
) {
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        val topPadding = maxHeight.times(0.073F)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(neutral0),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .padding(top = topPadding),
                text = test.question,
                style = neutral900Size32Weight700Style,
                textAlign = TextAlign.Center
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 60.dp)
                    .padding(top = 40.dp),
            ) {
                TextAnswerView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 14.dp),
                    text = test.answers[0].text
                )
                TextAnswerView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 14.dp),
                    text = test.answers[1].text
                )
                TextAnswerView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 14.dp),
                    text = test.answers[2].text
                )
                TextAnswerView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 14.dp),
                    text = test.answers[3].text
                )
            }
        }
    }
}

@Preview
@Composable
fun TextTestViewPreview() {
    MyApplicationTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            TextTestView(test = textQuestion1)
        }
    }
}