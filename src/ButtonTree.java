public class ButtonTree {

	private TeamButton root;

	public ButtonTree() {
		this.root = null;
	}

	public void insert(TeamButton node){
		if(root == null) {
			node.setButtonParent(null);
			node.setLeft(null);
			node.setRight(null);
			root = node;	
			return;
		}

		TeamButton current = root;
		TeamButton parent = null;

		while(true){	
			parent = current;
			if(node.getButtonValue() < current.getButtonValue() ){				
				current = current.getLeft();
				if(current == null){
					parent.setLeft(node);
					node.setButtonParent(parent);
					return;
				}
			} else {
				current = current.getRight();
				if(current == null){
					parent.setRight(node);
					node.setButtonParent(parent);
					return;
				}
			}
		}
	}

	public TeamButton getRoot(){
		return root;
	}
}
