package kotlinx.coroutines;

public final class InactiveNodeList implements Incomplete {
    private final NodeList list;

    public boolean isActive() {
        return false;
    }

    public InactiveNodeList(NodeList nodeList) {
        this.list = nodeList;
    }

    public NodeList getList() {
        return this.list;
    }

    public String toString() {
        return super.toString();
    }
}
