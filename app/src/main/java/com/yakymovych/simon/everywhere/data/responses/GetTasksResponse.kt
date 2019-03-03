package com.yakymovych.simon.everywhere.data.responses

import com.yakymovych.simon.everywhere.data.Task
import com.yakymovych.simon.everywhere.data.requests.Meta

data class GetTasksResponse(val meta: Meta,val tasks: List<Task>)