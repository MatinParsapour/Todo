package linkedlist;

public class CustomNode<T> {
    private T information;
    private CustomNode nextNode;

    public CustomNode(T information) {
        this.information = information;
    }

    public T getInformation() {
        return information;
    }

    public void setInformation(T information) {
        this.information = information;
    }

    public CustomNode getNextNode() {
        return nextNode;
    }

    public void setNextNode(CustomNode nextNode) {
        this.nextNode = nextNode;
    }
}
