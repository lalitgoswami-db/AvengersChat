/*
 * Copyright 2021 Stream.IO, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.stream.avengerschat.view.custom

import androidx.annotation.ColorInt
import io.getstream.chat.android.ui.StyleTransformer
import io.getstream.chat.android.ui.TransformStyle

/**
 * StreamGlobalStyles unify the styles of the Stream UI components by using the
 * global style transformer [TransformStyle].
 */
object StreamGlobalStyles {

    init {
        TransformStyle.channelActionsDialogStyleTransformer =
            StyleTransformer { channelActionsDialogStyle ->
                channelActionsDialogStyle.copy(
                    leaveGroupEnabled = false
                )
            }
    }

    /**
     * updates and unifies the Stream UI components color themes.
     */
    fun updateGlobalStylePrimaryColor(@ColorInt color: Int) {
        TransformStyle.channelListStyleTransformer =
            StyleTransformer { channelListStyle ->
                channelListStyle.copy(
                    indicatorReadIcon = channelListStyle.indicatorReadIcon.apply {
                        setTint(color)
                    },
                )
            }

        TransformStyle.messageListItemStyleTransformer =
            StyleTransformer { messageListItemStyle ->
                messageListItemStyle.copy(
                    messageBackgroundColorMine = color,
                    dateSeparatorBackgroundColor = color,
                    textStyleDateSeparator = messageListItemStyle.textStyleDateSeparator,
                    textStyleUserName = messageListItemStyle.textStyleUserName.copy(
                        color = color
                    ),
                    iconIndicatorRead = messageListItemStyle.iconIndicatorRead.apply {
                        setTint(color)
                    }
                )
            }

        TransformStyle.messageInputStyleTransformer =
            StyleTransformer { messageInputStyle ->
                messageInputStyle.copy(
                    sendButtonEnabledIcon = messageInputStyle.sendButtonEnabledIcon.apply {
                        setTint(color)
                    }
                )
            }
    }
}
