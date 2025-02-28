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

package io.stream.avengerschat.binding

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yarolegovich.discretescrollview.DiscreteScrollView
import com.yarolegovich.discretescrollview.transform.ScaleTransformer
import io.getstream.chat.android.client.models.User
import io.stream.avengerschat.extensions.adapterPositionOrNull
import io.stream.avengerschat.extensions.parsedColor
import io.stream.avengerschat.extensions.startCircularReveal
import io.stream.avengerschat.model.Avenger
import io.stream.avengerschat.model.LiveRoomInfo
import io.stream.avengerschat.view.custom.StreamGlobalStyles
import io.stream.avengerschat.view.dm.DirectMessageAdapter
import io.stream.avengerschat.view.live.LiveAdapter
import io.stream.avengerschat.view.main.MainAvengersAdapter

object RecyclerViewBinding {
    @JvmStatic
    @BindingAdapter("adapterAvengers")
    fun bindAdapterPosterList(view: DiscreteScrollView, posters: List<Avenger>?) {
        (view.adapter as? MainAvengersAdapter)?.submitList(posters)
        view.setItemTransformer(
            ScaleTransformer.Builder()
                .setMaxScale(1.25f)
                .setMinScale(0.8f)
                .build()
        )
    }

    @JvmStatic
    @BindingAdapter("adapterLiveInfo")
    fun bindAdapterLiveRoomInfo(view: RecyclerView, roomInfo: List<LiveRoomInfo>?) {
        (view.adapter as? LiveAdapter)?.submitList(roomInfo)
    }

    @JvmStatic
    @BindingAdapter("adapterDirectMessage")
    fun bindAdapterQueriedAvengers(view: RecyclerView, user: List<User>?) {
        (view.adapter as? DirectMessageAdapter)?.submitList(user?.reversed())
    }

    @JvmStatic
    @BindingAdapter("bindOnItemChanged", "bindOnItemChangedBackground")
    fun bindOnItemChanged(view: DiscreteScrollView, adapter: MainAvengersAdapter, pointView: View) {
        view.addOnItemChangedListener { viewHolder, _ ->
            val position = viewHolder?.adapterPositionOrNull ?: return@addOnItemChangedListener
            if (position >= 0 && position < adapter.itemCount) {
                val parsedColor = adapter.getAvenger(position).parsedColor
                ViewBinding.bindStatusBarColor(view, parsedColor)
                pointView.startCircularReveal(parsedColor)

                // updates global styles of the stream chat lists.
                StreamGlobalStyles.updateGlobalStylePrimaryColor(parsedColor)
            }
        }
    }
}
