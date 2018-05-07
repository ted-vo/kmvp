package com.horical.kmvparchitect.ui.main

import com.horical.kmvparchitect.data.db.model.Option
import com.horical.kmvparchitect.data.db.model.Question

data class QuestionCardData(val options: List<Option>, val quest: Question)