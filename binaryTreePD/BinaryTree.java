package binaryTreePD;

import java.util.ArrayList;

public class BinaryTree<K extends Comparable<K> ,O> {
	private Node<K,O> root;

	public BinaryTree() {
		root = null;
	}
	
	public void insert(K key, O object) {
		Node<K,O> newNode = new Node<K,O>(key, object);
		
		if(root == null)
			root = newNode;
		else {
			Node<K,O> parentNode = insertNode(key, root);
			
			if(parentNode.getKey().compareTo(key) > 0)
				parentNode.setLeftChild(newNode);
			else
				parentNode.setRightChild(newNode);
			
			newNode.setParent(parentNode);
		}
	}
	
	public O search(K key) {
		Node<K,O> node = nodeSearch(key, root);
		if(node == null)
			return null;
		else
			return node.getObject();
	}
	
	public O remove(K key) {
		Node<K,O> node = nodeSearch(key, root);
		
		if (node == null)
			return null;
		else {
			O object = node.getObject();
			
			if(node.isLeaf()) {
				if(root == node)
					root = null;
				else if (node.getParent().getLeftChild() == node)
					node.getParent().setLeftChild(null);
				else
					node.getParent().setRightChild(null);
			}
			else if(node.getLeftChild() != null && node.getRightChild() == null) {
				object = node.getObject();
				
				node.getLeftChild().setParent(node.getParent());
				
				if(node.getParent().getLeftChild() == node)
					node.getParent().setLeftChild(node.getLeftChild());
				else
					node.getParent().setRightChild(node.getLeftChild());
			}
			else if(node.getRightChild() != null && node.getLeftChild() == null) {
				object = node.getObject();
				
				node.getRightChild().setParent(node.getParent());
				
				if(node.getParent().getLeftChild() == node)
					node.getParent().setLeftChild(node.getRightChild());
				else
					node.getParent().setRightChild(node.getRightChild());
			}
			else {
				Node<K,O> nextNode = successorNode(node.getRightChild());
				
				node.setObject(nextNode.getObject());
				node.setKey(nextNode.getKey());
				
				if(nextNode.isLeaf())
					nextNode.getParent().setLeftChild(null);
				else {
					nextNode.getRightChild().setParent(nextNode);
					nextNode.getParent().setLeftChild(nextNode.getRightChild());
				}
			}
			
			return object;
		}
	}
	
	public int height() {
		if(root == null)
			return 0;
		else
			return root.getHeight();
	}
	
	private Node<K,O> nodeSearch(K key, Node<K,O> node) {
		if(node == null)
			return null;
		else if(node.getKey().compareTo(key) == 0)
			return node;
		else if(node.getKey().compareTo(key) > 0)
			return nodeSearch(key, node.getLeftChild());
		else
			return nodeSearch(key, node.getRightChild());
	}
	
	private Node<K,O> successorNode(Node<K,O> node) {
		if(node.getLeftChild() == null)
			return node;
		else
			return successorNode(node.getLeftChild());
	}
	
	private Node<K,O> insertNode(K key, Node<K,O> node) {
		if(node.getKey().compareTo(key) > 0) {
			if(node.getLeftChild() == null)
				return node;
			else
				return insertNode(key, node.getLeftChild());
		}
		else {
			if(node.getRightChild() == null)
				return node;
			else
				return insertNode(key, node.getRightChild());
		}
	}
	
	public ArrayList<O> inOrderTraversal() {
		if(root == null)
			return null;
		else {
			ArrayList<O> list = new ArrayList<O>();
			
			return recursiveTraversal(list, root);
		}
	}
	
	private ArrayList<O> recursiveTraversal(ArrayList<O> list, Node<K,O> node) {
		if(node.isLeaf())
			list.add(node.getObject());
		else {
			if(node.getLeftChild() != null)
				recursiveTraversal(list, node.getLeftChild());
			
			list.add(node.getObject());
			
			if(node.getRightChild() != null)
				recursiveTraversal(list, node.getRightChild());
			
		}
		
		return list;
	}
	
	private class Node<K extends Comparable<K> ,O> {
		private K key;
		private O object;
		private Node<K,O> leftChild = null;
		private Node<K,O> rightChild = null;
		private Node<K,O> parent = null;
		
		public Node(K inKey, O inObject) {
			setKey(inKey);
			setObject(inObject);
		}

		public K getKey() {
			return key;
		}

		public void setKey(K key) {
			this.key = key;
		}

		public O getObject() {
			return object;
		}

		public void setObject(O object) {
			this.object = object;
		}

		public Node<K,O> getLeftChild() {
			return leftChild;
		}

		public void setLeftChild(Node<K,O> leftChild) {
			this.leftChild = leftChild;
		}

		public Node<K,O> getRightChild() {
			return rightChild;
		}

		public void setRightChild(Node<K,O> rightChild) {
			this.rightChild = rightChild;
		}

		public Node<K,O> getParent() {
			return parent;
		}

		public void setParent(Node<K,O> parent) {
			this.parent = parent;
		}
		
		public int getHeight() {
			
			if(this.isLeaf())
				return 0;
			else if (leftChild != null && rightChild != null) {
				int leftHeight = leftChild.getHeight();
				int rightHeight = rightChild.getHeight();
				
				if(leftHeight > rightHeight)
					return leftHeight + 1;
				else
					return rightHeight + 1;
			}
			else if (leftChild == null)
				return rightChild.getHeight() + 1;
			else
				return leftChild.getHeight() + 1;
		}
		
		public boolean isLeaf() {
			return leftChild == null && rightChild == null;
		}
	}
}