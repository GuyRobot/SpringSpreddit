package com.guysrobot.spreddit.dto

import com.guysrobot.spreddit.model.VoteType

data class VoteDto(val voteType: VoteType, val postId: Long)
