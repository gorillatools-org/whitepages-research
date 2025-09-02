package bolts;

public interface Continuation {
    Object then(Task task);
}
