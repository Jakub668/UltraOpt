package pl.jakub.ultraopt.edit.batch;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class BatchQueue {

    public static final Queue<BlockTask> QUEUE = new ConcurrentLinkedQueue<>();
}