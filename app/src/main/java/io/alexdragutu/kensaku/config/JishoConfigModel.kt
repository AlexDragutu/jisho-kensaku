package io.alexdragutu.kensaku.config

import io.alexdragutu.kensaku.utils.Utils
import java.io.Serializable

data class JishoConfigModel(val urlTemplate: String = Utils.GOO_SEARCH_TEMPLATE, val name: String = "") : Serializable

