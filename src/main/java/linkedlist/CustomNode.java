package linkedlist;

public class CustomNode<T> {
     T information;
    CustomNode nextNode;
    CustomNode previousNode;

    public CustomNode getPreviousNode() {
        return previousNode;
    }

    public void setPreviousNode(CustomNode previousNode) {
        this.previousNode = previousNode;
    }

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
