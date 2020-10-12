/*
Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree

    3
   / \
  9   20
  \   /  \
   7 15   7
   
1. 3
  / \
   
2. null
3. 3
	/
 2
/
1

4. 3
		\
     20
     	\
      10
      
5. max_int
		/
min_int


return its level order traversal as:

[
  [3],
  [9,20],
  [7,15,7]
]
*/

class node {
  int data;
  node left;
  node right;
  
}

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world");
    }
  
  	public static List<List<Integer>> getLevelOrder(node root){
      List<List<Integer>> result = new ArrayList<>();
      result.add(Collection.singletonlist(root.data));
      
      getList(root.right, root.left, result);
      
      
      return result;
      
    }
  
  	public static List<Integer> getList(node right, node left, List<List<Integer>> result){
      List<Integer> lvlList = new ArrayList<>();
      
      while(right != null){
        lvlList.add(right.data);
        node right = right.right();
        node left = left.left();
        getList(right, left, result);
      
      }
      
      while(left != null){
        lvlList.add(left.data);
        node right = left.right();
        node left = left.left();
        getList(right, left, result);
      }
    }
  
}
