package co.kr.deartoday.util

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool
import co.kr.deartoday.R

object MySoundPlayer {
    const val SOUND_BOX = R.raw.sound_box
    const val SOUND_PLAYER = R.raw.sound_player
    private lateinit var soundPool: SoundPool
    private lateinit var soundPoolMap: HashMap<Int, Int>

    // sound media initialize
    fun initSounds(context: Context?) {
        val attributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()
        soundPool = SoundPool.Builder()
            .setAudioAttributes(attributes)
            .build()
        soundPoolMap = HashMap(2)
        soundPoolMap[SOUND_BOX] = soundPool.load(context, SOUND_BOX, 1)
        soundPoolMap[SOUND_PLAYER] = soundPool.load(context, SOUND_PLAYER, 1)
    }

    fun play(raw_id: Int) {
        if (soundPoolMap.containsKey(raw_id)) {
            soundPoolMap[raw_id]?.let {
                soundPool.play(it, 1f, 1f, 1, 0, 1f)
            }
        }
    }
}