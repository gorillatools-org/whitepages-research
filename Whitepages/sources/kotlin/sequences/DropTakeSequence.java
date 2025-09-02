package kotlin.sequences;

public interface DropTakeSequence extends Sequence {
    Sequence drop(int i);
}
