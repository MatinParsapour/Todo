package linkedlist;


public class CustomLinkedListMain<T> {

    private CustomNode start;
    private CustomNode end;
    public int length;

    public boolean isEmpty() {
        return start == null;
    }

    public void insertAtFirst(T information) {
        CustomNode recentNode = new CustomNode(information);
        if (start == null) {
            start = recentNode;
        } else {
            CustomNode actualNode = start;
            while (actualNode.getNextNode() != null) {
                actualNode = actualNode.getNextNode();
            }
            actualNode.setNextNode(recentNode);
        }
        length++;
    }

    public void insertAtLast(T information){
        if(end == null){
            insertAtFirst(information);
            return;
        }
        CustomNode<T> recentNode = new CustomNode<T>(information);
        recentNode = end.getNextNode();
        recentNode = end;

    }

    public void insertAtIndex(int index, T information){
        CustomNode newNode = new CustomNode(information);
        CustomNode lastNode = start;
        for(int counter = 0 ; counter < index -1 ; counter++){
            lastNode = lastNode.getNextNode();
        }
        newNode.setNextNode(lastNode.getNextNode());
        lastNode.setNextNode(newNode);
    }

    public void deleteFromFirst(int index) {
        CustomNode customNode = start;
        if (index == 0) {
            start = start.getNextNode();
        } else {
            for (int counter = 0; counter < index - 1; counter++) {
                customNode = customNode.getNextNode();
            }
            customNode.getNextNode().setNextNode(customNode.getNextNode().getNextNode());
        }
        length--;
    }

    public int indexOf(T element) {
        int index = -1;
        CustomNode currentNode = start;
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("you have nothing here");
        }
        int temp = 0;
        while (currentNode != null) {
            if (currentNode.getInformation() == element) {
                index = temp;
                break;
            }
            currentNode = currentNode.getNextNode();
            temp++;
        }
        return index;
    }

    @Override
    public String toString() {
        String toString = "(";
        if (start != null) {
            CustomNode currentNode = start;
            while (currentNode != null) {
                toString += "[";
                toString += currentNode.getInformation() + "]";
                currentNode = currentNode.getNextNode();
            }
        }
        toString += ")";
        return toString;
    }
}
