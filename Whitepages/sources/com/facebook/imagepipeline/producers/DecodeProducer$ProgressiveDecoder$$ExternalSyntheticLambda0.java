package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.producers.DecodeProducer;
import com.facebook.imagepipeline.producers.JobScheduler;

public final /* synthetic */ class DecodeProducer$ProgressiveDecoder$$ExternalSyntheticLambda0 implements JobScheduler.JobRunnable {
    public final /* synthetic */ DecodeProducer.ProgressiveDecoder f$0;
    public final /* synthetic */ DecodeProducer f$1;
    public final /* synthetic */ int f$2;

    public /* synthetic */ DecodeProducer$ProgressiveDecoder$$ExternalSyntheticLambda0(DecodeProducer.ProgressiveDecoder progressiveDecoder, DecodeProducer decodeProducer, int i) {
        this.f$0 = progressiveDecoder;
        this.f$1 = decodeProducer;
        this.f$2 = i;
    }

    public final void run(EncodedImage encodedImage, int i) {
        DecodeProducer.ProgressiveDecoder._init_$lambda$2(this.f$0, this.f$1, this.f$2, encodedImage, i);
    }
}
