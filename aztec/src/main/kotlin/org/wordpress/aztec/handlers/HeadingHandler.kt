package org.wordpress.aztec.handlers

import android.text.Spannable
import org.wordpress.aztec.spans.AztecHeadingSpan
import org.wordpress.aztec.watchers.TextDeleter

class HeadingHandler : BlockHandler<AztecHeadingSpan>(AztecHeadingSpan::class.java) {

    override fun handleNewlineAtStartOfBlock() {
        // we got a newline at the start of the block. Let's just push the block after the newline
        block.start = newlineIndex + 1
    }

    override fun handleNewlineAtEmptyLineAtBlockEnd() {
        // just remote the block since it's empty
        block.remove()

        // delete the newline as it's purpose was served (to translate it as a command to close the block)
        TextDeleter.mark(text, newlineIndex, newlineIndex + 1)
    }

    override fun handleNewlineAtEmptyBody() {
        // just remote the block since it's empty
        block.remove()

        // delete the newline as it's purpose was served (to translate it as a command to close the block)
        TextDeleter.mark(text, newlineIndex, newlineIndex + 1)
    }

    // fun handleNewlineAtTextEnd()
    // got a newline while being at the end-of-text. We'll let the current list item engulf it and will wait
    //  for the end-of-text marker event in order to attach the new list item to it when that happens.

    override fun handleNewlineInBody() {
        if (newlineIndex == block.end - 2) {
            // newline added at the end of the block (right before its visual newline) so, just end the block and
            //  not add a new block after it
        } else {
            // newline added at some position inside the block. Let's split the block into two
            cloneHeading(text, block.span, newlineIndex + 1, block.end)
        }

        block.end = newlineIndex + 1
    }

    override fun handleEndOfBufferMarker() {
        if (block.start == markerIndex) {
            // ok, this list item has the marker as its first char so, nothing more to do here.
            return
        }

        // the heading has bled over to the marker so, let's adjust its range to just before the marker.
        //  There's a newline there hopefully :)
        block.end = markerIndex
    }

    companion object {
        fun cloneHeading(text: Spannable, block: AztecHeadingSpan, start: Int, end: Int) {
            set(text, AztecHeadingSpan(block.nestingLevel, block.textFormat, block.attributes), start, end)
        }
    }
}