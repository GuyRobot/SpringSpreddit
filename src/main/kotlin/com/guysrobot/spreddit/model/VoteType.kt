package com.guysrobot.spreddit.model

enum class VoteType(val direction: Int) {
    UPVOTE(1),
    DOWN_VOTE(-1);
    companion object {
        fun lookup(direction: Int): VoteType {
            return values().first { it.direction == direction }
        }
    }
}
