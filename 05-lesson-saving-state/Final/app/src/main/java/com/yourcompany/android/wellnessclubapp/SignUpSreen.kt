/*
 * Copyright (c) 2024 Kodeco Inc
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.yourcompany.android.wellnessclubapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SignUpScreen(modifier: Modifier = Modifier,
                 member: ClubMember,
                 onMemberUpdated: (ClubMember) -> Unit) {
  var memberName by rememberSaveable {
    mutableStateOf("")
  }

  var memberEmail by rememberSaveable {
    mutableStateOf("")
  }
  Column(
    modifier = modifier
      .fillMaxSize()
      .padding(32.dp)
      .verticalScroll(state = rememberScrollState())
  ) {
    Text(
      text = "Wellness Club Sign Up",
      style = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp

      )
    )
    Spacer(modifier = Modifier.height(20.dp))

    FieldInputComponent(
      label = "Name",
      inputValue = memberName,
      onInputValueChanged = { newValue ->
        val upperCaseName = newValue.uppercase()
        memberName = upperCaseName
      }
    )
    Spacer(modifier = Modifier.height(16.dp))
    FieldInputComponent(
      label = "Email",
      inputValue = memberEmail,
      onInputValueChanged = { newValue ->
        memberEmail = newValue

      }
    )
    Spacer(modifier = Modifier.height(16.dp))
    Button(
      modifier = Modifier.fillMaxWidth(),
      onClick = {
        val newMember = ClubMember(name = memberName, email = memberEmail)
        onMemberUpdated(newMember)
      }) {
      Text(text = "Submit",
        modifier = Modifier.padding(8.dp))
    }

    Spacer(modifier = Modifier.height(10.dp))
    Divider(
      thickness = 3.dp,
    )
    Spacer(modifier = Modifier.height(10.dp))
    Text(
      text = "New Member Details",
      style = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp
      )
    )

    Text(text = "Name: ${member.name}")
    Text(text = "Email: ${member.email}")

  }
}

@Composable
fun FieldInputComponent(
  modifier: Modifier = Modifier,
  label: String,
  inputValue: String,
  onInputValueChanged: (String) -> Unit
) {
  Column(modifier = modifier) {
    OutlinedTextField(value = inputValue,
      onValueChange = onInputValueChanged,
      shape = RoundedCornerShape(16.dp),
      modifier = Modifier.fillMaxWidth(),
      label = {
        Text(text = "Enter Member's $label")
      })
    Spacer(modifier = Modifier.height(12.dp))
    Text(text = "$label is $inputValue")
  }
}