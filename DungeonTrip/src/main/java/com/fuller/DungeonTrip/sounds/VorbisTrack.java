package com.fuller.DungeonTrip.sounds;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.util.concurrent.atomic.AtomicInteger;

import org.lwjgl.stb.STBVorbis;
import org.lwjgl.stb.STBVorbisInfo;
import org.lwjgl.system.MemoryStack;

import com.fuller.DungeonTrip.utils.IOUtil;

public class VorbisTrack implements AutoCloseable {
	private final ByteBuffer encodedAudio;

    private final long handle;

    private final int channels;
    private final int sampleRate;

    final int samplesLength;
    final float samplesSec;

    private final AtomicInteger sampleIndex;

    VorbisTrack(String filePath, AtomicInteger sampleIndex)
    {
        try
        {
            encodedAudio = IOUtil.ioResourceToByteBuffer(filePath, 256 * 1024);
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }

        try(MemoryStack stack = MemoryStack.stackPush())
        {
            IntBuffer error = stack.mallocInt(1);
            handle = STBVorbis.stb_vorbis_open_memory(encodedAudio, error, null);
            
            //if(handle == null)
            //{
                //throw new RuntimeException("Failed to open Ogg Vorbis file. Error: " + error.get(0));
            //}

            STBVorbisInfo info = STBVorbisInfo.mallocStack(stack);
            print(info);
            this.channels = info.channels();
            this.sampleRate = info.sample_rate();
        }

        this.samplesLength = STBVorbis.stb_vorbis_stream_length_in_samples(handle);
        this.samplesSec = STBVorbis.stb_vorbis_stream_length_in_seconds(handle);

        this.sampleIndex = sampleIndex;
        sampleIndex.set(0);
    }

    @Override
    public void close()
    {
    	STBVorbis.stb_vorbis_close(handle);
    }

    void progressBy(int samples)
    {
        sampleIndex.set(sampleIndex.get() + samples);
    }

    void setSampleIndex(int sampleIndex)
    {
        this.sampleIndex.set(sampleIndex);
    }

    void rewind()
    {
        seek(0);
    }

    void skip(int direction)
    {
        seek(Math.min(Math.max(0, STBVorbis.stb_vorbis_get_sample_offset(handle) + direction * sampleRate), samplesLength));
    }

    void skipTo(float offset0to1)
    {
        seek(Math.round(samplesLength * offset0to1));
    }

    // called from audio thread
    synchronized int getSamples(ShortBuffer pcm)
    {
        return STBVorbis.stb_vorbis_get_samples_short_interleaved(handle, channels, pcm);
    }

    // called from UI thread
    private synchronized void seek(int sampleIndex)
    {
    	STBVorbis.stb_vorbis_seek(handle, sampleIndex);
        setSampleIndex(sampleIndex);
    }

    private void print(STBVorbisInfo info)
    {
        System.out.println("stream length, samples: " + STBVorbis.stb_vorbis_stream_length_in_samples(handle));
        System.out.println("stream length, seconds: " + STBVorbis.stb_vorbis_stream_length_in_seconds(handle));

        System.out.println();

        STBVorbis.stb_vorbis_get_info(handle, info);

        System.out.println("channels = " + info.channels());
        System.out.println("sampleRate = " + info.sample_rate());
        System.out.println("maxFrameSize = " + info.max_frame_size());
        System.out.println("setupMemoryRequired = " + info.setup_memory_required());
        System.out.println("setupTempMemoryRequired() = " + info.setup_temp_memory_required());
        System.out.println("tempMemoryRequired = " + info.temp_memory_required());
    }
}
