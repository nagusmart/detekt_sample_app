/*
 * Copyright (c) 2021 Razeware LLC
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
 * This project and source code may use libraries or frameworks that are
 * released under various Open-Source licenses. Use of those libraries and
 * frameworks are governed by their own individual licenses.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.android.detektsampleapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.raywenderlich.android.detektsampleapp.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Main Screen
 */
class MainActivity : AppCompatActivity() {
  private lateinit var binding: ActivityMainBinding
  private val rulesAdapter = RulesAdapter { rulesModel ->
    onRuleClick(rulesModel)


  }

  override fun onCreate(savedInstanceState: Bundle?) {
    // Switch to AppTheme for displaying the activity
    setTheme(R.style.AppTheme)

    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    initView()

    // TODO 6 : Add a call to coroutineTestRule function
    runBlocking { coroutineTestRules() }
  }

  // TODO 5 : Add the coroutineTestRule function
  private suspend fun coroutineTestRules() {
    GlobalScope.launch {
      delay(2000)
    }
  }

  // TODO 8 : Add more functions to break more rules
  @Suppress("LongParameterList")
  private fun complexMethod(name: String, email: String, phone: String,
                            address: String, zipCode: String, city: String, country: String): String {
    return name
  }

  private fun emptyMethod() {}

  override fun toString(): String {
    throw IllegalStateException()
  }

  fun performanceIssues() {
    (1..19).forEach {
      print(it.toString())
    }
  }

  fun potentialBugs() {
    val test = when ("type") {
      "main" -> 1
      "main" -> 2
      else -> 3
    }
  }

  @Suppress("EmptyFunctionBlock")
  private fun suppressedWarning() {
  }

  private fun onRuleClick(rulesModel: RulesModel) {
    val ruleIntent = Intent(applicationContext, RulesWebViewActivity::class.java)
    ruleIntent.putExtra("rulesUrl", rulesModel.url)
    startActivity(ruleIntent)
  }


  private fun initView() {
    rulesAdapter.submitList(detektRules)
    binding.rvRules.adapter = rulesAdapter

  }

}
